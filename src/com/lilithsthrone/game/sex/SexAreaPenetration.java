package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public enum SexAreaPenetration implements SexAreaInterface {
	
	PENIS(4, -2f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "член";
			}
			return owner.getPenisName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getPenisRawSizeValue();
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			return owner.getPenisDiameter();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, SexAreaOrifice.URETHRA_PENIS) && Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.PENIS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.PENIS;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] тер[npc.her] [npc.cock+] о [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] тер[npc.her] [npc.cock+] о [npc.her] "+targetArea.getName(performer)+".");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] траха[npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] трахающий [npc.her] "+targetArea.getName(performer)+".");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] продолжал(а) [npc.her] [npc.cock+] прижатым(ой) к [npc2.namePos] [npc2.clit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.Name] [npc.sexPaceVerb] тер[npc.cockHead] [npc.her] [npc.cock+] между [npc2.namePos] [npc2.labia] и по поверхности [npc2.her] [npc2.clit], дразня [npc2.herHim] обещанием проникновения.");
										break;
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] силой втирал(а) [npc.cockHead] [npc.her] [npc.cock+] между [npc2.namePos] [npc2.labia] и по поверхности [npc2.her] [npc2.clit], жестоко дразня [npc2.herHim] обещанием проникновения.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) сопротивляться, пока [npc.she] [npc.was] заставлял(а) тер[npc.her] [npc.cock+] о [npc2.namePos] [npc2.pussy] и [npc2.clit].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")+" как [npc.namePos] [npc.her] скользнул(а) [npc.cock] по [npc2.her] [npc2.clit].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Вскоре [npc2.name] обнаружил(а), что [npc2.herself] в отчаянии возбужден(а), а [npc2.was] [npc2.moaning] ради того, чтобы [npc.name] начал(а) трахать [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append(" Вскоре [npc2.name] обнаружил(а), что [npc2.herself] в отчаянии возбужден(а), а [npc2.was] приказывает [npc.name] не двигаться, пока [npc2.she] втирает [npc2.her] [npc2.clit+] в [npc.namePos] [npc.cock+].");
										break;
									case SUB_RESISTING:
										sb.append(" Плача и умоляя оставить её в покое, [npc2.name] отчаянно пыталась вырваться, но тщетно.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] потирает [npc.cockHead] [npc.her] [npc.cock+] между [npc2.namePos] [npc2.labia] и поверх [npc2.her] [npc2.clit].");
						}
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc2.namePos] обхватывал(а) [npc2.her] [npc2.fingers+] вокруг [npc.her] [npc.cock+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] получил(а) мастурбацию от [npc2.name].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) отстраниться, пока [npc2.name] делал(а) [npc.herHim] мастурбацию.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" при этом [npc2.her] [npc2.fingers] обвивал(а) [npc.namePos] [npc.cock+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.SexPaceVerb], обвивавший(ая) [npc2.her] [npc2.fingers+] вокруг [npc.namePos] [npc.cock], с огромным удовольствием [npc2.name] дрочил(а) [npc.herHim].");
										break;
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] обхватила [npc2.her] [npc2.fingers+] вокруг [npc.namePos] [npc.cock], и [npc2.name] властно приказала [npc.herHim] не двигаться, пока [npc2.she] ласкала [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" Издавая жалкие всхлипы, [npc2.name] пытался(ась) умолять [npc.name] оставить [npc2.herHim] в покое, но [npc.she] просто игнорировал(а) [npc2.herHim] и продолжал(а) [npc.herHim] дрочить.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] получает мастурбацию от [npc2.name].");
						}
						break;
					case FOOT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спит":"Не шевелясь")+", [npc.name] продолжал(а) прижимать [npc.her] [npc.cock+] к [npc2.namePos] [npc2.feet].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] получал(а) огромное удовольствие от того, как [npc.sexPaceVerb] тер(ла) [npc.her] [npc.cock] по [npc2.namePos] [npc2.feet+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) сопротивляться и отстраниться, пока [npc2.name] тер(ла) [npc2.her] [npc2.feet+] вверх и вниз по [npc.her] [npc.cock].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"оставалась совершенно неподвижной")+" пока [npc2.her] [npc2.feet+] использовались для ласк [npc.name].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.moans+], [npc2.name] очень наслаждался(ась) ощущением того, как [npc2.her] [npc2.feet+] доставляет удовольствие [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.her] изо всех сил пытался сопротивляться, но [npc2.name] в конечном счете не смог помешать [npc.name] использовать [npc2.her] [npc2.feet] столь похотливо.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] получает [npc2.footjob] от [npc2.name].");
						}
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спит":"Замирая, словно статуя")+", [npc.name] продолжал [npc.her] [npc.cock+], прижатый к собственной [npc2.cock] [npc2.namePos].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] терзал(а) [npc.her] [npc.cock+] вверх и вниз о собственную [npc2.cock] [npc2.namePos].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) сопротивляться, пока [npc.she] [npc.was] заставлял(а) [npc.her] [npc.cock+] тереться о собственную [npc2.cock] [npc2.namePos].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совсем не реагировал(а) и не двигался(ась)")+" пока [npc2.her] [npc2.cock+] использовали таким образом.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] не мог(ла) сдержать череду похотливых [npc2.moans], пока [npc2.her] [npc2.cock+] использовали таким образом.");
										break;
									case SUB_RESISTING:
										sb.append(" Плача и умоляя оставить её в покое, [npc2.name] отчаянно пытался(ась), но так и не смог(ла) вырваться из рук [npc.name].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs], трущий(ая) [npc.her] [npc.cock+] вверх и вниз о собственную [npc2.cock] [npc2.namePos].");
						}
						break;
					case TAIL:
						boolean multipleTails = target.getTailCount()>1;
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не отреагировал(а), когда [npc2.namePos] обвил(а) [npc2.her] [npc2.tail+] вокруг [npc.her] [npc.cock+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] получил(а) хвостом по[npc2.name].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] попытался(ась) отстраниться, пока [npc2.name] делал(а) [npc.herHim] хвостом.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" обвивая [npc2.her] "+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+" вокруг [npc.namePos] [npc.cock+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.SexPaceVerb] обвивает [npc2.her] "+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+" вокруг [npc.namePos] [npc.cock], [npc2.name] с огромным удовольствием использовал(а) "+(multipleTails?"их":"это")+" чтобы довести [npc.herHim] до оргазма.");
										break;
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] обвивает [npc2.her] "+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+" вокруг [npc.namePos] [npc.cock], [npc2.name] властно приказал(а) [npc.herHim] не двигаться, пока [npc2.she] использовал(а) "+(multipleTails?"их":"это")+" чтобы довести [npc.herHim] до оргазма.");
										break;
									case SUB_RESISTING:
										sb.append(" Издавая жалкие всхлипы, [npc2.name] пыталась умолять [npc.name] оставить [npc2.herHim] в покое, но [npc.she] просто игнорировал [npc2.herHim] и продолжал [npc.her] "+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+" чтобы довести [npc.herHim] до оргазма.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] получает хвостом в [npc2.name].");
						}
						break;
					case TENTACLE:
						boolean multipleTentacles = target.getTentacleCount()>1;
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] обвивал [npc2.her] [npc2.tentacle+] вокруг [npc.her] [npc.cock+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] получает щупальцевой секс от [npc2.name].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась отстраниться, пока [npc2.name] делал [npc.herHim] щупальцевой секс.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" обвивая [npc2.her] "+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+" вокруг [npc.namePos] [npc.cock+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.SexPaceVerb] обвивает [npc2.her] "+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+" вокруг [npc.namePos] [npc.cock], [npc2.name] с огромным удовольствием использовал(а) "+(multipleTentacles?"их":"это")+" чтобы довести [npc.herHim] до оргазма.");
										break;
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] обвивает [npc2.her] "+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+" вокруг [npc.namePos] [npc.cock], [npc2.name] властно приказал(а) [npc.herHim] не двигаться, пока [npc2.she] использовал(а) "+(multipleTentacles?"их":"это")+" чтобы довести [npc.herHim] до оргазма.");
										break;
									case SUB_RESISTING:
										sb.append(" Издавая жалкие всхлипы, [npc2.name] пыталась умолять [npc.name] оставить [npc2.herHim] в покое, но [npc.she] просто игнорировал [npc2.herHim] и продолжал [npc.her] "+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+" чтобы довести [npc.herHim] до оргазма.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] получает щупальцевой секс от [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящая, [npc.name] была и близка к тому, чтобы проснуться":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность,")
										+" пока [npc2.namePos] [npc2.tongue+] лизала и обслюнявливала [npc.her] [npc.cock+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Вскоре [npc.Name] обнаружила, что [npc.herself] [npc.moaning] в восторге, пока [npc2.namePos] [npc2.tongue+] лизал и обслюнявливал [npc.her] [npc.cock+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] плакал и вырывался против [npc2.name], пока [npc2.she] использовал [npc2.her] [npc2.tongue+], чтобы вылизывать и слизывать [npc.her] [npc.cock+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" удерживая [npc2.her] [npc2.tongue] прижатым к [npc.namePos] [npc.cock].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Часто, когда рот [npc2.her] был полон члена, [npc2.name] давал понять, что [npc2.she] [npc2.was] получает удовольствие, издавая серию приглушенных [npc2.moans].");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] пытался сопротивляться, в итоге [npc2.name] пришлось обслуживать [npc.namePos] [npc.cock] таким образом.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs], у которого [npc.her] [npc.cock] вылизывал [npc2.name].");
						}
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал, пока [npc.she] продолжал держать [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал [npc.cockHead] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.asshole+], прежде чем толкнуться вперед и начать [npc.sexPaceVerb] трахать [npc2.her] [npc2.ass+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] отчаянно пытался отстраниться, пока [npc2.name] прижимал [npc2.her] [npc2.asshole+] обратно к [npc.cockHead] [npc.her] [npc.cock+], и в отчаянии вскрикивал, когда [npc2.she] толкался в ответ, заставляя [npc.herHim] начать трахать [npc2.her] [npc2.ass+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахал [npc2.her] [npc2.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] выгибался, [npc2.her] [npc2.hips] прижимаясь спиной к [npc.herHim], а [npc2.name] издавал серию [npc2.moans+], пока [npc2.she] помогал погружать [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.asshole].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.cock+] входил и выходил из [npc2.her] [npc2.asshole+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахал [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case ASS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал, пока [npc.she] продолжал вдавливать [npc.her] [npc.cock+] между [npc2.namePos] ягодицами.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Сжимая ягодицы [npc2.namePos], [npc.name] [npc.was] удавалось скользить [npc.her] [npc.cock+] вверх и вниз между ними.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пытался сопротивляться, [npc.name] [npc.was] заставлял скользить [npc.her] [npc.cock+] вверх и вниз между [npc2.namePos] ягодицами.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] использовал [npc2.her] [npc2.ass+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Поднимая и опуская [npc2.her] [npc2.hips+], [npc2.name] [npc2.was] вскоре [npc2.moaning], когда [npc2.she] [npc2.sexPaceVerb] заставил [npc.namePos] [npc.cock] поверх [npc2.her] [npc2.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.ass] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] заигрывал с [npc2.namePos] задницей.");
						}
						break;
					case ARMPITS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжала [npc.her] [npc.cock+], прижатыми к [npc2.namePos] [npc2.armpit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Заставив [npc2.name] держать [npc2.her] [npc2.arm] наверху, [npc.name] [npc.was] удалось скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] подмышке.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пыталась сопротивляться, [npc.name] [npc.was] заставили скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.namePos] подмышке.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] использовала [npc2.her] [npc2.armpit+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Удерживая [npc2.her] [npc2.arm] наверху, [npc2.name] [npc2.was] вскоре [npc2.moaning], когда [npc2.she] [npc2.sexPaceVerb] заставили [npc.namePos] [npc.cock] через [npc2.her] [npc2.armpit+].");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.armpits] fucked against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] скользит [npc.her] [npc.cock+] по [npc2.namePos] подмышке.");
						}
						break;
					case BREAST:
						boolean paizuri = target.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжала прижимать [npc.her] [npc.cock+] между [npc2.namePos] [npc2.breasts+].");
								} else {
									sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжала прижимать [npc.her] [npc.cock+] к [npc2.namePos] [npc2.breasts+].");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("Сдавив [npc2.namePos] [npc2.breasts+] вместе, [npc.name] [npc.was] удалось скользить [npc.her] [npc.cock+] вверх и вниз между ними.");
										} else {
											sb.append("Грудь [npc2.NamePos] была слишком плоской, чтобы [npc.name] мог ею трахаться, но это не помешало [npc.herHim] скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] [npc2.breasts+].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("Сжимая [npc2.her] [npc2.breasts+] вместе, [npc2.name] игнорировал протестующие крики [npc.namePos] и заставлял [npc.herHim] скользить [npc.her] [npc.cock+] вверх и вниз между ними.");
										} else {
											sb.append("Не смущаясь того, что грудь [npc2.her] плоская, [npc2.name] игнорировал протестующие крики [npc.namePos] и заставлял [npc.herHim] скользить [npc.her] [npc.cock+] вверх и вниз по [npc2.her] [npc2.breasts+].");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] использовал [npc2.her] [npc2.breasts+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием делал паизури [npc.name].");
										} else {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием делал наизури [npc.name].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append(" Рыдая и плача, [npc2.name] пыталась, но так и не смогла сопротивляться, делая паизури [npc.name].");
										} else {
											sb.append(" Рыдая и плача, [npc2.name] пыталась, но так и не смогла сопротивляться, делая наизури [npc.name].");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] [npc2.breasts+].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] теребит [npc.her] [npc.cock] о плоскую грудь [npc2.namePos].");
							}
						}
						break;
					case BREAST_CROTCH:
						boolean crotchPaizuri = target.isBreastCrotchFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(crotchPaizuri) {
									sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжал [npc.her] [npc.cock+], вдавливая их между [npc2.namePos] [npc2.crotchBoobs+].");
								} else {
									sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжал [npc.her] [npc.cock+], прижимая их к [npc2.namePos] [npc2.crotchBoobs+].");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(crotchPaizuri) {
											sb.append("Прижав [npc2.namePos] [npc2.crotchBoobs+] друг к другу, [npc.name] [npc.was] может скользить [npc.her] [npc.cock+] вверх и вниз между ними.");
										} else {
											sb.append("[npc2.NamePos] и [npc2.crotchBoobs] были слишком плоскими, чтобы [npc.name] мог ими трахаться, но это не помешало [npc.herHim] скользить [npc.her] [npc.cock+] вверх и вниз по ним.");
										}
										break;
									case SUB_RESISTING:
										if(crotchPaizuri) {
											sb.append("Прижимая [npc2.her] и [npc2.crotchBoobs+] друг к другу, [npc2.name] игнорировал протестующие крики [npc.namePos] и заставлял [npc.herHim] скользить [npc.her] [npc.cock+] вверх и вниз между ними.");
										} else {
											sb.append("Не смущаясь того, что [npc2.her] и [npc2.crotchBoobs] плоские, [npc2.name] игнорировал протестующие крики [npc.namePos] и заставлял [npc.herHim] скользить [npc.her] [npc.cock+] вверх и вниз по ним.");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] использовала [npc2.her] как [npc2.crotchBoobs+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(crotchPaizuri) {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием делала паизури между ног для [npc.name].");
										} else {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием делала наизури между ног для [npc.name].");
										}
										break;
									case SUB_RESISTING:
										if(crotchPaizuri) {
											sb.append(" Рыдая и всхлипывая, [npc2.name] пыталась, но так и не смогла сопротивляться, когда ей делали паизури между ног на [npc.name].");
										} else {
											sb.append(" Всхлипывая и рыдая, [npc2.name] пыталась, но так и не смогла сопротивляться, когда ей навязали кротч-найзури на [npc.name].");
										}
										break;
								}
							}
							
						} else {
							if(crotchPaizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] [npc2.crotchBoobs+].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] втирает [npc.her] [npc.cock] в плоскую [npc2.crotchBoobs] [npc2.namePos].");
							}
						}
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.cock+], прижатым к [npc2.namePos] горлу.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.cockHead] [npc.her] [npc.cock+] к [npc2.namePos] [npc2.lips+], прежде чем [npc.sexPaceVerb] вошел ей прямо в [npc2.her] рот.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] обхватить [npc2.her] [npc2.lips+] вокруг [npc.her] [npc.cock] и заглотить его в [npc2.her] рот.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала [npc2.her] в лицо.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Пока [npc2.SexPaceVerb] сосала и лизала [npc.cock+] перед [npc2.herHim], [npc2.name] продолжала издавать приглушенные [npc2.moans], пока [npc2.she] делал ей [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" Издавая приглушенные всхлипы и крики, [npc2.name] пыталась отстраниться от [npc.cock+] перед [npc2.herHim], но в итоге ее заставили отсосать [npc.name].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] в лицо.");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.cock+], вонзенным в одну из [npc2.namePos] [npc2.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижал [npc.cockHead+] [npc.her] [npc.cock] к одной из [npc2.namePos] [npc2.nipples+], после чего резко толкнулся вперед и трахнул [npc2.her] [npc2.breast+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and fuck [npc2.her] [npc2.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] глубоко погружал [npc.her] [npc.cock+] в [npc2.her] [npc2.nipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] при каждом толчке выталкивала [npc2.her] грудь, [npc2.moaning+] в то время как [npc2.she] помогала [npc.namePos] [npc.cock+] глубоко погружаться в [npc2.her] [npc2.nipple(true)].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.cock+] входил и выходил из [npc2.her] [npc2.nipple+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] [npc2.nipple+].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.cock+], вонзенным в одну из [npc2.namePos] [npc2.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижал [npc.cockHead+] [npc.her] [npc.cock] к одной из [npc2.namePos] [npc2.crotchNipples+], после чего резко толкнулся вперед и трахнул [npc2.her] [npc2.crotchBoob+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] войти и трахать [npc2.her] [npc2.crotchNipple(true)], совершая [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] глубоко погружала [npc.her] [npc.cock+] в [npc2.her] [npc2.crotchNipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] при каждом толчке выпирал [npc2.her] в паху, [npc2.moaning+] в то время как [npc2.she] помогала вгонять [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.crotchNipple(true)].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удалось удержать [npc2.herHim] на месте, даже когда [npc.her] [npc.cock+] входил и выходил из [npc2.her] [npc2.crotchNipple(true)].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] [npc2.crotchNipple+].");
						}
						break;
					case THIGHS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал(а) удерживать [npc.her] [npc.cock+] между [npc2.namePos] бёдер.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc2.namePos] [npc2.legs+] друг к другу, [npc.name] [npc.was] удавалось скользить [npc.her] [npc.cock+] туда-сюда между [npc2.her] бёдер.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пыталась сопротивляться, [npc.name] [npc.was] заставил(а) скользить [npc.her] [npc.cock+] туда-сюда между [npc2.namePos] бёдер.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала [npc2.her] в бёдра.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Поднимая и опуская [npc2.her] [npc2.hips+], [npc2.name] [npc2.was] вскоре [npc2.moaning], так как [npc2.she] [npc2.sexPaceVerb] заставил(а) [npc.namePos] [npc.cock] между [npc2.her] [npc2.legs+].");
										break;
									case SUB_RESISTING:
										sb.append(" Всхлипывая и плача, [npc2.name] [npc2.her] изо всех сил пыталась вырваться из рук [npc.name], но в итоге ее удерживали на месте, вонзая [npc2.her] бедра в [npc2.her] вопреки ее воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] бедра.");
						}
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал держать [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc.cockHead+] [npc.her] [npc.cock] к собственному [npc2.cock] [npc2.namePos], [npc.name] [npc.sexPaceVerb] резко подался вперед и начал трахать [npc2.her] [npc2.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("Извиваясь и плача, [npc.name] пыталась отстраниться, пока [npc2.name] заставлял [npc.herHim] проникать и начинал трахать [npc2.her] [npc2.penisUrethra+] члена.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала [npc2.her] [npc2.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] вытолкнул [npc2.her] [npc2.hips] против [npc.name], помогая [npc.herHim] погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.penisUrethra].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась вырваться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.cock+] входил и выходил из [npc2.her] [npc2.penisUrethra+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] [npc2.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал держать [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc.cockHead+] [npc.her] [npc.cock] к [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] резко подался вперед и начал трахать [npc2.her] [npc2.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("В борьбе и слезах [npc.name] пыталась отстраниться, пока [npc2.name] заставлял [npc.herHim] проникать и начинать трахать [npc2.vaginaUrethra+] [npc2.her] киски.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахал [npc2.her] [npc2.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] вытолкнул [npc2.her] [npc2.hips] против [npc.name], помогая [npc.herHim] погрузить [npc.her] [npc.cock+] глубоко в [npc2.her] [npc2.vaginaUrethra].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.cock+] входил и выходил из [npc2.her] [npc2.vaginaUrethra+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] [npc2.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] держал [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Скользя [npc.cockHead+] [npc.her] [npc.cock] вверх и вниз между [npc2.namePos] [npc2.labia+], [npc.name] [npc.sexPaceVerb] резко подался вперед, погружая [npc.her] [npc.cock+] в [npc2.her] [npc2.pussy+], прежде чем начать трахать [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Грубо растирая [npc.cockHead+] [npc.her] [npc.cock] вверх и вниз между [npc2.namePos] [npc2.labia+], [npc.name] яростно толкнулся вперед, вонзая [npc.her] [npc.cock+] в [npc2.her] [npc2.pussy+], прежде чем начать с силой трахать [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] извивалась и кричала, умоляя оставить её в покое, но [npc.was] в конечном итоге не смогла помешать тому, как [npc.her] [npc.cock+] схватили [npc2.name] и направили в [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахал [npc2.her] [npc2.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] в восторге, [npc2.name] получала огромное удовольствие от того, что [npc2.sexPaceVerb] оседлала [npc.namePos] [npc.cock+].");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.Moaning] в восторге, [npc2.name] [npc2.sexPaceVerb] выгибал(а) [npc2.her] бедра, помогая вгонять [npc.namePos] [npc.cock+] глубоко в [npc2.her] [npc2.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] плакала и умоляла оставить её в покое, [npc2.name] [npc2.was] не смогла остановить [npc.name], трахающего [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал удерживать [npc.her] [npc.cock+] глубоко в [npc2.namePos] [npc2.spinneret+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Водя [npc.cockHead+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] паутине, [npc.name] [npc.sexPaceVerb] толкнулся вперед, вонзая [npc.her] [npc.cock+] в [npc2.her] плетущее паутину отверстие, прежде чем начать трахать [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Грубо растирая [npc.cockHead+] [npc.her] [npc.cock] вверх и вниз по [npc2.namePos] паутинному отверстию, [npc.name] яростно thrust forwards, вонзая [npc.her] [npc.cock+] в [npc2.her] паутинное отверстие, прежде чем начать с силой трахать [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] извивалась и кричала, умоляя оставить её в покое, но [npc.was] в конечном итоге не смогла помешать тому, как [npc2.name] схватил(а) [npc.her] [npc.cock+] и направил(а) его в [npc2.her] паутинное отверстие.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахает [npc2.her] [npc2.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append(" В восторге, [npc2.Moaning+], [npc2.name] получала огромное удовольствие, используя [npc.her] паутинное отверстие, чтобы [npc2.sexPaceVerb] оседлать [npc.namePos] [npc.cock+].");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" В восторге, [npc2.Moaning], [npc2.name] [npc2.sexPaceVerb] вгонял(а) [npc.namePos] [npc.cock+] глубоко в [npc2.her] паутинное отверстие.");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] плакала и умоляла оставить её в покое, [npc2.name] [npc2.was] не смогла остановить [npc.name], трахающего [npc2.her] паутинное отверстие.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] паутинное отверстие.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	CLIT(4, -2f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "клитор";
			}
			return owner.getClitorisName(false);
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getVaginaRawClitorisSizeValue();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.VAGINA;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.VAGINA;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] тер(ла) [npc.her] [npc.clit+] о [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] трет(ся) [npc.her] [npc.clit+] о [npc.her] "+targetArea.getName(performer)+".");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] трахает клитором [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает клитор [npc.her] "+targetArea.getName(performer)+".");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] продолжал прижимать [npc.her] [npc.clit+] к [npc2.namePos] [npc2.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] терзал(а) [npc.her] [npc.pussy+] об [npc2.nameHers], [npc.moaning+] пока [npc.her] [npc.clit+] бились о [npc2.hers].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) отстраниться, когда [npc2.name] начал(а) тереть [npc2.her] [npc2.pussy+] об [npc.hers], но [npc.her] усилия оказались тщетными.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")
										+" пока [npc.namePos] скользил(а) [npc.her] [npc.clit] по [npc2.her] [npc2.pussy].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Вскоре [npc2.Name] начал(а) издавать [npc2.moans+], так как [npc2.she] [npc2.sexPaceVerb] отвечал(а) на [npc.namePos] движения и терся(лась) с [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" Умоляя, чтобы его/ее оставили в покое, [npc2.name] отчаянно пытался(ась) вырваться, но [npc2.was] не мог(ла) помешать [npc.name] тереться об [npc2.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трется(тся) об [npc2.name].");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) вдавливать [npc.her] [npc.clit+] в [npc2.namePos] [npc2.armpit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Заставив [npc2.name] держать [npc2.her] [npc2.arm] наверху, [npc.name] [npc.was] смог(ла) скользить [npc.her] [npc.clit+] вверх и вниз по [npc2.her] подмышке.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пыталась сопротивляться, [npc.name] [npc.was] заставил [npc.her] [npc.clit+] вверх и вниз по [npc2.namePos] подмышке.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] использовала [npc2.her] [npc2.armpit+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Удерживая [npc2.her] [npc2.arm], [npc2.name] вскоре [npc2.was] [npc2.moaning], когда [npc2.she] [npc2.sexPaceVerb] заставил [npc.namePos] [npc.clit] через [npc2.her] [npc2.armpit+].");
										break;
									case SUB_RESISTING:
										sb.append(" Всхлипывая и рыдая, [npc2.name] изо всех сил пыталась вырваться из рук [npc.name], но в итоге ее удерживали на месте, вопреки [npc2.her], заставляя [npc2.her] [npc2.armpits] [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] скользит [npc.her] [npc.clit+] по [npc2.namePos] подмышке.");
						}
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал [npc.her] [npc.clit+] к [npc2.namePos] [npc2.asshole+], прежде чем рвануть вперед и начать [npc.sexPaceVerb] трахать [npc2.her] [npc2.ass+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] отчаянно пыталась вырваться, когда [npc2.name] прижал [npc2.her] [npc2.asshole+] обратно к [npc.her] [npc.clit+], и в отчаянии вскрикнула, когда [npc2.she] толкнулся навстречу и заставил [npc.herHim] начать трахать [npc2.her] [npc2.ass+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахал [npc2.her] [npc2.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Когда [npc2.SexPaceVerb] выгибалась, отбрасывая [npc2.her] [npc2.hips] назад на [npc.herHim], [npc2.name] издала серию [npc2.moans+], пока [npc2.she] помогал погрузить [npc.namePos] [npc.clit+] глубоко в [npc2.her] [npc2.asshole].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удалось удержать [npc2.herHim] на месте, даже когда [npc.her] [npc.clit+] входил и выходил из [npc2.her] [npc2.asshole+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] клитор-трахающий [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case ASS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал вдавливать [npc.her] [npc.clit+] между [npc2.namePos] ягодицами.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Сжимая ягодицы [npc2.namePos], [npc.name] [npc.was] смог скользить [npc.her] [npc.clit+] вверх и вниз между ними.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пыталась сопротивляться, [npc.name] [npc.was] заставил [npc.her] [npc.clit+] скользить вверх и вниз между [npc2.namePos] ягодицами.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] использовал [npc2.her] [npc2.ass+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Поднятие и опускание [npc2.her] [npc2.hips+], [npc2.name] [npc2.was] вскоре [npc2.moaning], как [npc2.she] [npc2.sexPaceVerb] заставил [npc.namePos] [npc.clit] через [npc2.her] [npc2.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.ass] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] клитор-хотдоггинг [npc2.namePos] задницы.");
						}
						break;
					case BREAST:
						boolean paizuri = target.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал вдавливать [npc.her] [npc.clit+] между [npc2.namePos] [npc2.breasts+].");
								} else {
									sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал прижимать [npc.her] [npc.clit+] к [npc2.namePos] [npc2.breasts+].");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("Прижав [npc2.namePos] [npc2.breasts+] друг к другу, [npc.name] [npc.was] удалось скользить [npc.her] [npc.clit+] вверх и вниз между ними.");
										} else {
											sb.append("Грудь [npc2.NamePos] была слишком плоской, чтобы [npc.name] мог ею трахаться, но это не помешало [npc.herHim] скользить [npc.her] [npc.clit+] вверх и вниз по [npc2.her] [npc2.breasts+].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("Прижав [npc2.her] [npc2.breasts+] друг к другу, [npc2.name] проигнорировал протестующие крики [npc.namePos] и заставил [npc.herHim] скользить [npc.her] [npc.clit+] вверх и вниз между ними.");
										} else {
											sb.append("Не смущаясь того, что грудь [npc2.her] плоская, [npc2.name] проигнорировал протестующие крики [npc.namePos] и заставил [npc.herHim] скользить [npc.her] [npc.clit+] вверх и вниз по [npc2.her] [npc2.breasts+].");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] использовал [npc2.her] [npc2.breasts+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием делал паизури [npc.name].");
										} else {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием делал наизури [npc.name].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append(" Рыдая и плача, [npc2.name] пыталась, но так и не смогла сопротивляться, делая паизури [npc.name].");
										} else {
											sb.append(" Рыдая и плача, [npc2.name] пыталась, но так и не смогла сопротивляться, делая наизури [npc.name].");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает клитор [npc2.namePos] [npc2.breasts+].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] втирает [npc.her] [npc.clit] в плоскую грудь [npc2.namePos].");
							}
						}
						break;
					case BREAST_CROTCH:
						boolean crotchPaizuri = target.isBreastCrotchFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(crotchPaizuri) {
									sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал вдавливать [npc.her] [npc.clit+] между [npc2.namePos] [npc2.crotchBoobs+].");
								} else {
									sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал прижимать [npc.her] [npc.clit+] к [npc2.namePos] [npc2.crotchBoobs+].");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(crotchPaizuri) {
											sb.append("Прижав [npc2.namePos] [npc2.crotchBoobs+] друг к другу, [npc.name] [npc.was] удалось скользить [npc.her] [npc.clit+] вверх и вниз между ними.");
										} else {
											sb.append("[npc2.NamePos] [npc2.crotchBoobs] были слишком плоскими, чтобы [npc.name] мог ими трахаться, но это не помешало [npc.herHim] скользить [npc.her] [npc.clit+] вверх и вниз по ним.");
										}
										break;
									case SUB_RESISTING:
										if(crotchPaizuri) {
											sb.append("Сжимая [npc2.her] [npc2.crotchBoobs+] вместе, [npc2.name] игнорировал протестующие крики [npc.namePos] и заставлял [npc.herHim] скользить [npc.her] [npc.clit+] вверх и вниз между ними.");
										} else {
											sb.append("Не смущенный тем, что [npc2.her] [npc2.crotchBoobs] плоские, [npc2.name] игнорировал протестующие крики [npc.namePos] и заставлял [npc.herHim] скользить [npc.her] [npc.clit+] вверх и вниз по ним.");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] делал паизури [npc2.herHim].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(crotchPaizuri) {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием делала паизури между ног для [npc.name].");
										} else {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием делала наизури между ног для [npc.name].");
										}
										break;
									case SUB_RESISTING:
										if(crotchPaizuri) {
											sb.append(" Рыдая и всхлипывая, [npc2.name] пыталась, но так и не смогла сопротивляться, когда ей делали паизури между ног на [npc.name].");
										} else {
											sb.append(" Всхлипывая и рыдая, [npc2.name] пыталась, но так и не смогла сопротивляться, когда ей навязали кротч-найзури на [npc.name].");
										}
										break;
								}
							}
							
						} else {
							if(crotchPaizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] клитор-трах [npc2.namePos] [npc2.crotchBoobs+].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] терзает [npc.her] [npc.clit] о плоскую [npc2.namePos] [npc2.crotchBoobs].");
							}
						}
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал, пока [npc.she] продолжал [npc.her] [npc.clit+] глубоко в [npc2.namePos] горло.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал [npc.her] [npc.clit+] к [npc2.namePos] [npc2.lips+], прежде чем [npc.sexPaceVerb] толкнулся вперед прямо в [npc2.her] рот.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался сопротивляться, но [npc.was] не мог помешать [npc2.name] обхватить [npc2.her] [npc2.lips+] вокруг [npc.her] [npc.clit] и взять его в [npc2.her] рот.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала [npc2.her] в лицо.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] сосал и лизал [npc.clit+] на глазах у [npc2.herHim], а [npc2.name] продолжал издавать приглушенные [npc2.moans], пока [npc2.she] делал [npc.name] минет.");
										break;
									case SUB_RESISTING:
										sb.append(" Издавая приглушенные всхлипы и крики, [npc2.name] пыталась отстраниться от [npc.clit+] перед [npc2.herHim], но в итоге ее заставили делать [npc.name] минет.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает клитором лицо [npc2.namePos].");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал держать [npc.her] [npc.clit+] глубоко в одном из [npc2.namePos] [npc2.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижал [npc.her] [npc.clit] к одному из [npc2.namePos] [npc2.nipples+], прежде чем резко толкнуться вперед и трахнуть [npc2.her] [npc2.breast+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смог помешать [npc2.name] заставить [npc.herHim] проникнуть и трахать [npc2.her] [npc2.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала [npc2.her] [npc2.nipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] при каждом толчке выталкивал [npc2.her] грудь, [npc2.moaning+], пока [npc2.she] помогал погружать [npc.namePos] [npc.clit+] глубоко в [npc2.her] [npc2.nipple(true)].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.clit+] входил и выходил из [npc2.her] [npc2.nipple+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает клитором [npc2.namePos] [npc2.nipple+].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал держать [npc.her] [npc.clit+] глубоко в одном из [npc2.namePos] [npc2.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижал [npc.her] [npc.clit] к одной из [npc2.namePos] [npc2.crotchNipples+], прежде чем резко толкнуться вперед и оттрахать [npc2.her] [npc2.crotchBoob+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist, but [npc.was] unable to stop [npc2.name] from making [npc.herHim] penetrate and fuck [npc2.her] [npc2.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахал [npc2.her] [npc2.crotchNipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] при каждом толчке вжимал [npc2.her] пах, [npc2.moaning+], пока [npc2.she] помогал погружать [npc.namePos] [npc.clit+] глубоко в [npc2.her] [npc2.crotchNipple(true)].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удалось удержать [npc2.herHim] на месте, даже когда [npc.her] [npc.clit+] входил и выходил из [npc2.her] [npc2.crotchNipple(true)+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает клитор [npc2.namePos] [npc2.crotchNipple+].");
						}
						break;
					case THIGHS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] удерживал [npc.her] [npc.clit+] зажатым между [npc2.namePos] бедер.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc2.namePos] [npc2.legs+] друг к другу, [npc.name] [npc.was] мог скользить [npc.her] [npc.clit+] туда-сюда между [npc2.her] бедер.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пыталась сопротивляться, [npc.name] [npc.was] заставил [npc.her] [npc.clit+] скользить туда-сюда между [npc2.namePos] бедер.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] использовал [npc2.her] [npc2.legs+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Поднимая и опуская [npc2.her] [npc2.hips+], [npc2.name] [npc2.was] вскоре [npc2.moaning], когда [npc2.she] [npc2.sexPaceVerb] заставил [npc.namePos] [npc.clit] между [npc2.her] [npc2.legs+].");
										break;
									case SUB_RESISTING:
										sb.append(" Всхлипывая и плача, [npc2.name] [npc2.her] изо всех сил пыталась вырваться из рук [npc.name], но в итоге ее удерживали на месте, вонзая [npc2.her] бедра в [npc2.her] вопреки ее воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает клитор [npc2.namePos] бедер.");
						}
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал держать [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc.her] [npc.clit] к собственной [npc2.namePos] [npc2.clit], [npc.name] [npc.sexPaceVerb] толкнулся вперед и начал трахать [npc2.her] [npc2.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("В борьбе и слезах, [npc.name] пыталась отстраниться, пока [npc2.name] заставлял [npc.herHim] проникать и начинал трахать [npc2.her] клиторную [npc2.penisUrethra+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала [npc2.her] [npc2.penisUrethra].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] выталкивал [npc2.her] [npc2.hips] против [npc.name], помогая [npc.herHim] погрузить [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.penisUrethra].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.clit+] входил и выходил из [npc2.her] [npc2.penisUrethra+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает клитор [npc2.namePos] [npc2.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал держать [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижавшись [npc.her] [npc.clit] к собственному [npc2.clit] [npc2.namePos], [npc.name] [npc.sexPaceVerb] резко подался вперед и начал трахать [npc2.her] [npc2.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("В борьбе и слезах [npc.name] пыталась отстраниться, пока [npc2.name] заставлял [npc.herHim] проникать и начинать трахать [npc2.vaginaUrethra+] [npc2.her] киски.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахал [npc2.her] [npc2.vaginaUrethra].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] вытолкнул [npc2.her] [npc2.hips] навстречу [npc.name], помогая [npc.herHim] вогнать [npc.her] [npc.clit+] глубоко в [npc2.her] [npc2.vaginaUrethra].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.clit+] входил и выходил из [npc2.her] [npc2.vaginaUrethra+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] [npc2.vaginaUrethra+] клитором.");
						}
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] удерживал [npc.her] [npc.clit+] глубоко в [npc2.namePos] [npc2.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Скользя [npc.her] [npc.clit] вверх и вниз между [npc2.namePos] [npc2.labia+], [npc.name] [npc.sexPaceVerb] толкнулся вперед, вонзая [npc.her] [npc.clit+] в [npc2.her] [npc2.pussy+], прежде чем начать трахать [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Грубо двигая [npc.her] [npc.clit] вверх и вниз между [npc2.namePos] [npc2.labia+], [npc.name] яростно толкнулся вперед, вонзая [npc.her] [npc.clit+] в [npc2.her] [npc2.pussy+], прежде чем начать с силой трахать [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] отчаянно сопротивлялась и кричала, умоляя оставить её в покое, но [npc.was] в конечном итоге не смогла помешать тому, как [npc2.name] схватила [npc.her] [npc.clit+] и направила её в [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала [npc2.her] [npc2.pussy].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] в восторге, [npc2.name] получала огромное удовольствие от того, что [npc2.sexPaceVerb] оседлала [npc.namePos] [npc.clit+].");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] плакала и умоляла оставить её в покое, [npc2.name] [npc2.was] не смогла остановить [npc.name], трахающего [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] клитор-трахающая [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжала удерживать [npc.her] [npc.clit+] глубоко в [npc2.namePos] паутинном паху.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Скользя [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] паутинному паху, [npc.name] [npc.sexPaceVerb] толкнулась вперед, погружая [npc.her] [npc.clit+] в [npc2.her] паутинное отверстие, прежде чем начать трахать [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Грубо толкая [npc.her] [npc.clit] вверх и вниз по [npc2.namePos] паутинному паху, [npc.name] яростно рванулась вперед, вонзая [npc.her] [npc.clit+] в [npc2.her] паутинное отверстие, прежде чем начать с силой трахать [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] отчаянно сопротивлялась и кричала, умоляя оставить её в покое, но [npc.was] в конечном итоге не смогла помешать тому, как [npc2.name] схватила [npc.her] [npc.clit+] и направила её в [npc2.her] паутинный пах.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала [npc2.her] паутинный пах.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] в восторге, [npc2.name] получала огромное удовольствие, используя [npc.her] паучий аппарат, чтобы [npc2.sexPaceVerb] оседлать [npc.namePos] [npc.clit+].");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] плакала и умоляла оставить её в покое, [npc2.name] [npc2.was] не смогла остановить [npc.name], трахающего [npc2.her] паутинное отверстие.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает клитор [npc2.namePos].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TONGUE(2, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "язык";
			}
			return owner.getTongueName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getTongueLengthValue();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, SexAreaOrifice.MOUTH) && Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.MOUTH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.MOUTH;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] сосет свой собственный [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] сосет свой собственный [npc.her] "+targetArea.getName(performer)+".");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] лизала и целовала [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] лижет и целует [npc.her] "+targetArea.getName(performer)+".");
					}
				}
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.tongue+], прижатый к [npc2.namePos] [npc2.armpit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.her] [npc.lips+] к [npc2.namePos] [npc2.armpits+], прежде чем начать [npc.sexPaceVerb] целовать и лизать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не могла помешать [npc2.name] затащить [npc.herHim] в [npc2.her] [npc2.armpits+] и заставить [npc.herHim] целовать и лизать их.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] лизала [npc2.her] [npc2.armpit+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенные [npc2.moans], [npc2.name] [npc2.sexPaceVerb] втиснул(а) [npc2.her] [npc2.armpits] в [npc.namePos] [npc.face] и подгонял(а) [npc.herHim] продолжать доставлять оральное удовольствие.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc2.was] не могла помешать [npc.name] прижимать [npc.her] [npc.face] к [npc2.her] [npc2.armpits].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целует [npc2.namePos] [npc2.armpits].");
						}
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжал [npc.her] [npc.lips+], прижатый к [npc2.namePos] [npc2.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.her] [npc.lips+] к [npc2.namePos] [npc2.ass], запечатлев поцелуй на [npc2.her] [npc2.asshole+], прежде чем начать [npc.sexPaceVerb] заниматься анилингусом с [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] сопротивлялась и плакала, пока [npc2.name] вжимал [npc2.her] [npc2.ass+] в [npc.her] [npc.face] и заставлял [npc.herHim] заниматься анилингусом с [npc2.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] занимался анилингусом с [npc2.herHim].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая серию [npc2.moans+], [npc2.name] протолкнул [npc2.her] [npc2.hips] обратно в [npc.namePos] [npc.face], помогая вогнать [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.asshole].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась оттянуть [npc2.her] [npc2.ass] от нежеланных [npc.tongue] [npc.namePos], но усилия [npc2.her] оказались напрасными.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], занимающийся анилингусом с [npc2.name].");
						}
						break;
					case ASS:
						break;
					case BREAST:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжал прижимать [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breast+(true)].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breasts+], прежде чем начать [npc.sexPaceVerb] целовать и лизать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не мог помешать [npc2.name] заталкивать [npc.herHim] в [npc2.her] [npc2.breasts+] и заставлять [npc.herHim] целовать и лизать их.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] целовал(а) [npc2.her], [npc2.breasts+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенные [npc2.moans], [npc2.name] [npc2.sexPaceVerb] вдавливал(а) [npc2.her] [npc2.breasts] в [npc.namePos] [npc.face] и подгонял(а) [npc.herHim] продолжать доставлять им оральное удовольствие.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) отстраниться, но [npc2.was] не могла(мог) остановить(ь) [npc.name], который(ая) продолжал(а) вдавливать [npc.her] [npc.face] в [npc2.her] [npc2.breasts].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целует(ет) [npc2.namePos] [npc2.breasts].");
						}
						break;
					case BREAST_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) прижимать [npc.her] [npc.lips+] к [npc2.namePos] [npc2.crotchBoob+(true)].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.lips+] к [npc2.namePos] [npc2.crotchBoobs+], прежде чем начать [npc.sexPaceVerb] целовать и лизать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) сопротивляться, но [npc.was] не могла(мог) остановить(ь) [npc2.name], который(ая) затаскивал(а) [npc.herHim] в [npc2.her] [npc2.crotchBoobs+] и заставлял(а) [npc.herHim] целовать и лизать их.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] [npc2.crotchBoobs+] [npc2.her].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенные [npc2.moans], [npc2.name] [npc2.sexPaceVerb] вдавливал(а) [npc2.her] [npc2.crotchBoobs] в [npc.namePos] [npc.face] и подгонял(а) [npc.herHim] продолжать доставлять им оральное удовольствие.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) отстраниться, но [npc2.was] не могла(мог) остановить(ь) [npc.name], который(ая) продолжал(а) вдавливать [npc.her] [npc.face] в [npc2.her] [npc2.crotchBoobs].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целует [npc2.namePos] [npc2.crotchBoobs].");
						}
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагирует, пока [npc.she] продолжает прижимать [npc.her] [npc.lips+] к [npc2.namePos] рту.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.lips+] к [npc2.namePos] [npc2.mouth], прежде чем вовлечь [npc2.herHim] в поцелуй и начать страстно [npc.sexPaceVerb] с [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) сопротивляться, но [npc.was] не смог(ла) помешать [npc2.name] втянуть [npc.herHim] в поцелуй и начать страстно [npc2.sexPaceVerb] с [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] [npc2.her] [npc2.tongue] вгоняет в [npc.her] [npc.mouth].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенные [npc2.moans], [npc2.name] прильнула к [npc.name], и [npc2.sexPaceVerb] начал с силой вгонять [npc2.her] [npc2.tongue] в [npc.her] [npc.mouth].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался сопротивляться, но [npc2.was] не могла остановить [npc.name], который с силой втискивал [npc.her] [npc.tongue] в [npc2.her] [npc2.mouth].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целует [npc2.name].");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагирует, пока [npc.she] продолжает обхватывать [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.nipple+(true)].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижимает [npc.her] [npc.lips+] к [npc2.namePos] [npc2.breasts], прежде чем начать [npc.sexPaceVerb] целовать и сосать [npc2.her] [npc2.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытается сопротивляться, но [npc.was] не в силах помешать [npc2.name] заталкивать [npc.herHim] в [npc2.her] [npc2.breasts], а [npc2.sexPaceVerb] заставляет [npc.herHim] целовать [npc2.her] [npc2.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] целует и лижет [npc2.her] [npc2.nipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] выпячивает [npc2.her] грудь навстречу [npc.namePos] [npc.face] и умоляет [npc.herHim] продолжать играть с [npc2.her] [npc2.nipples+].");
										break;
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] выпячивает [npc2.her] грудь навстречу [npc.namePos] [npc.face] и приказывает [npc.herHim] продолжать играть с [npc2.her] [npc2.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытается сопротивляться, но [npc2.was] не в силах помешать [npc.name] продолжать играть с [npc2.her] [npc2.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целует [npc2.namePos] [npc2.nipples].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагирует, пока [npc.she] продолжает обхватывать [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.crotchNipples+(true)].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.lips+] к [npc2.namePos] [npc2.crotchBoobs], прежде чем начать [npc.sexPaceVerb] целовать и сосать [npc2.her] [npc2.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) сопротивляться, но [npc.was] не смог(ла) помешать [npc2.name] затащить [npc.herHim] в [npc2.her] [npc2.crotchBoobs], а [npc2.sexPaceVerb] заставил(а) [npc.herHim] целовать [npc2.her] [npc2.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] целовал(а) и лизал(а) [npc2.her] [npc2.crotchNipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] вытолкнул(а) [npc2.her] [npc2.crotchBoobs] наружу в [npc.namePos] [npc.face] и умолял(а) [npc.herHim] продолжать играть с [npc2.her] [npc2.crotchNipples+].");
										break;
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] вытолкнул(а) [npc2.her] [npc2.crotchBoobs] наружу в [npc.namePos] [npc.face] и приказал(а) [npc.herHim] продолжать играть с [npc2.her] [npc2.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) сопротивляться, но [npc2.was] не смог(ла) помешать [npc.name] продолжать играть с [npc2.her] [npc2.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целует [npc2.namePos] [npc2.crotchNipples].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагирует, пока [npc.she] продолжает прижимать [npc.her] [npc.lips+] к [npc2.namePos] [npc2.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.lips+] к [npc2.namePos] [npc2.labia+], прежде чем [npc.sexPaceVerb] начал(а) лизать [npc2.her] [npc2.clit] и принялся(ась) вылизывать [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) сопротивляться, но [npc.was] не смог(ла) помешать [npc2.name] прижать [npc2.her] [npc2.labia+] к [npc.her] [npc.lips], заставляя [npc.herHim] вылизывать [npc2.hreHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] занимался(ась) куннилингусом с [npc2.herHim].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.Moaning+], [npc2.name] [npc2.sexPaceVerb] втиснул(а) [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], заставляя [npc.herHim] вгонять [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.pussy+].");
										break;
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+], [npc2.name] грубо впечатал(а) [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], заставляя [npc.herHim] вгонять [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) отстраниться от [npc.name], но [npc2.was] не смог(ла) помешать [npc.herHim] вгонять [npc.her] [npc.tongue] глубоко в [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целует(ет) [npc2.namePos] [npc2.vagina].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) прижимать [npc.her] [npc.lips+] к паутинному аппарату [npc2.namePos].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.lips+] к паутинному аппарату [npc2.namePos], прежде чем [npc.sexPaceVerb] начал(а) лизать его и принялся(ась) вылизывать [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] прижать [npc2.her] паучье брюшко к [npc.her] [npc.lips], заставляя [npc.herHim] вылизывать [npc2.hreHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] во рту боготворила [npc2.her] паучье отверстие.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.Moaning+], [npc2.name] [npc2.sexPaceVerb] втиснула [npc2.her] паучье брюшко в [npc.namePos] [npc.face], заставляя [npc.herHim] загнать [npc.her] [npc.tongue] глубоко в [npc2.her] паучье отверстие.");
										break;
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+], [npc2.name] грубо впечатала [npc2.her] паучье брюшко в [npc.namePos] [npc.face], заставляя [npc.herHim] вогнать [npc.her] [npc.tongue] глубоко в [npc2.her] паучье отверстие.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться от [npc.name], но [npc2.was] не смогла помешать [npc.herHim] вогнать [npc.her] [npc.tongue] глубоко в [npc2.her] паучье брюшко.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целует [npc2.namePos] паучье брюшко.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	FINGER(1, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "пальцы";
			}
			return owner.getArmType().getFingersNamePlural(owner);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			System.err.println("Warning: Finger length is being called!");
			return 8;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.HANDS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.FINGER;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] ласкает [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] ласкает [npc.her] "+targetArea.getName(performer)+".");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] перебирает пальцами [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] перебирает пальцами [npc.her] "+targetArea.getName(performer)+".");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] продолжала держать [npc.her] [npc.fingers+] обернутыми вокруг [npc2.namePos] [npc2.hand+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.SexPaceVerb] обернула [npc.her] [npc.fingers] вокруг [npc2.namePos] [npc2.hand+], и [npc.name] начала сжимать [npc2.her] [npc2.hand].");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пыталась сопротивляться, [npc.name] [npc.was] не смогла помешать [npc2.name] удерживать [npc.her] [npc.hand].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")+" пока [npc.name] держала [npc2.her] [npc2.hand].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] сжимала [npc2.her] [npc2.fingers+] вокруг [npc.namePos] [npc.hand], и [npc2.name] начала издавать [npc2.moans+], пока [npc2.she] наслаждалась ласками руки.");
										break;
									case SUB_RESISTING:
										sb.append(" Отчаянно пытаясь вырваться, [npc2.name] умоляла [npc.name] оставить [npc2.herHim] в покое, но усилия [npc2.her] оказались напрасными, и [npc2.her] [npc2.hand] продолжали удерживать.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] держала [npc2.namePos] [npc2.hand].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] продолжала держать [npc.her] [npc.fingers+] обернутыми вокруг [npc2.namePos] [npc2.cock+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.SexPaceVerb] обернула [npc.her] [npc.fingers] вокруг [npc2.namePos] [npc2.cock+], и [npc.name] начала делать [npc2.herHim] мастурбацию рукой.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пыталась сопротивляться, [npc.name] [npc.was] не смогла помешать [npc2.name] заставить [npc.herHim] делать [npc2.herHim] мастурбацию рукой.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")+" пока [npc.name] ласкал [npc2.herHim].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вколачивал [npc2.her] [npc2.hips] в такт движениям [npc.namePos], а [npc2.name] начал издавать [npc2.moans+], пока [npc2.her] [npc2.cock] ласкали и перебирали.");
										break;
									case SUB_RESISTING:
										sb.append(" Отчаянно пытаясь отстраниться, [npc2.name] умолял, чтобы [npc.name] оставили [npc2.herHim] в покое, но [npc2.her] усилия оказались напрасными, и [npc2.her] [npc2.cock] продолжали ласкать и перебирать.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] делает мастурбацию [npc2.name].");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал, пока [npc.she] продолжал глубоко засовывать [npc.her] [npc.fingers+] в [npc2.namePos] [npc2.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.ass], [npc.name] провел [npc.her] [npc.fingers+] вниз к [npc2.her] [npc2.asshole+] и, просунув их внутрь, начал [npc.sexPaceVerb] пальцами [npc2.her] [npc2.ass].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] сопротивлялся и кричал, пока [npc2.name] схватил [npc.her] [npc.hand] и силой вогнал [npc.her] [npc.fingers] в [npc2.her] [npc2.asshole+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] перебирал пальцами [npc2.her] [npc2.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая серию [npc2.moans+], [npc2.name] прижал [npc2.her] [npc2.hips] обратно к [npc.namePos], помогая вогнать [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.asshole].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался оттащить [npc2.her] [npc2.ass] от нежеланных [npc.namePos] [npc.fingers], но [npc2.her] усилия оказались напрасными.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] ласкает [npc2.namePos] [npc2.asshole].");
						}
						break;
					case ASS:
						break;
					case BREAST:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагирует, пока [npc.she] продолжает сжимать [npc2.namePos] [npc2.breasts+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.her] [npc.hands+] к [npc2.namePos] [npc2.breasts], после чего начала [npc.sexPaceVerb] ощупывать и сжимать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] затащить [npc.her] [npc.hands] в [npc2.her] [npc2.breasts], а [npc2.sexPaceVerb] заставила [npc.herHim] ласкать и сжимать их.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] ощупывала [npc2.her] [npc2.breasts].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] выпятила [npc2.her] грудь навстречу [npc.namePos] касаниям и умоляла [npc.herHim] продолжать играть с [npc2.her] [npc2.breasts+].");
										break;
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] выпятила [npc2.her] грудь навстречу [npc.namePos] касаниям и приказала [npc.herHim] продолжать играть с [npc2.her] [npc2.breasts+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась сопротивляться, но [npc2.was] не могла заставить [npc.name] перестать играть с [npc2.her] [npc2.breasts+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] ощупывает и сжимает [npc2.namePos] [npc2.breasts].");
						}
						break;
					case BREAST_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагирует, пока [npc.she] продолжает сжимать [npc2.namePos] [npc2.crotchBoobs+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.hands+] к [npc2.namePos] [npc2.crotchBoobs], прежде чем начать [npc.sexPaceVerb] ощупывать и сжимать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] затащить [npc.her] [npc.hands] в [npc2.her] [npc2.crotchBoobs], а [npc2.sexPaceVerb] заставила [npc.herHim] ласкать и сжимать их.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] ощупывал(а) [npc2.her] [npc2.crotchBoobs].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] выставил(а) [npc2.her] пах навстречу [npc.namePos] прикосновениям и умолял(а) [npc.herHim] продолжать играть с [npc2.her] [npc2.crotchBoobs+].");
										break;
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] выставил(а) [npc2.her] пах навстречу [npc.namePos] прикосновениям и приказал(а) [npc.herHim] продолжать играть с [npc2.her] [npc2.crotchBoobs+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) сопротивляться, но [npc2.was] не смог(ла) помешать [npc.name] продолжать играть с [npc2.her] [npc2.crotchBoobs+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] ощупывает(ет) и сжимает(ет) [npc2.namePos] [npc2.crotchBoobs].");
						}
						break;
					case MOUTH:
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) сжимать [npc.her] [npc.fingers+] вокруг [npc2.namePos] [npc2.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.hands+] к [npc2.namePos] [npc2.breasts], после чего сосредоточился(ась) на [npc2.her] [npc2.nipples+] и начал(а) [npc.sexPaceVerb] щипать и сжимать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] затащить [npc.herHim] в [npc2.her] [npc2.breasts], а [npc2.sexPaceVerb] заставил(а) [npc.herHim] щипать и ласкать [npc2.her] [npc2.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] играл(а) с [npc2.her] [npc2.nipples].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] выпятил(а) [npc2.her] грудь навстречу [npc.namePos] касаниям и умолял(а) [npc.herHim] продолжать играть с [npc2.her] [npc2.nipples+].");
										break;
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] выпятил(а) [npc2.her] грудь навстречу [npc.namePos] касаниям и приказал(а) [npc.herHim] продолжать играть с [npc2.her] [npc2.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытается сопротивляться, но [npc2.was] не в силах помешать [npc.name] продолжать играть с [npc2.her] [npc2.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] игриво щипает [npc2.namePos] [npc2.nipples].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) [npc.her] [npc.fingers+], сжимая их вокруг [npc2.namePos] [npc2.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.hands+] к [npc2.namePos] [npc2.crotchBoobs], прежде чем сосредоточиться на [npc2.her] [npc2.crotchNipples+] и начать [npc.sexPaceVerb] щипать и сжимать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] затащить [npc.herHim] в [npc2.her] [npc2.crotchBoobs], а [npc2.sexPaceVerb] заставил(а) [npc.herHim] щипать и ласкать [npc2.her] [npc2.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] играл(а) с [npc2.her] [npc2.crotchNipples].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] вытолкнул(а) [npc2.her] [npc2.crotchBoobs] навстречу [npc.namePos] прикосновениям и умолял(а) [npc.herHim] продолжать играть с [npc2.her] [npc2.crotchNipples+].");
										break;
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] вытолкнул(а) [npc2.her] [npc2.crotchBoobs] навстречу [npc.namePos] прикосновениям и приказал(а) [npc.herHim] продолжать играть с [npc2.her] [npc2.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) сопротивляться, но [npc2.was] не смог(ла) помешать [npc.name] продолжать играть с [npc2.her] [npc2.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] игриво щипает [npc2.namePos] [npc2.crotchNipples].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) [npc.her] [npc.fingers+] глубоко внутри [npc2.namePos] [npc2.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Опустив [npc.her] руки между [npc2.namePos] [npc2.legs], [npc.name] провел(ла) [npc.her] [npc.fingers+] по [npc2.her] [npc2.labia+] и, просунув их внутрь, начал(а) [npc.sexPaceVerb] пальцами [npc2.her] [npc2.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] сопротивлялся(ась) и плакал(а), когда [npc2.name] схватил(а) [npc.her] [npc.hand] и силой вогнал(а) [npc.her] [npc.fingers] в [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] ласкает пальцами [npc2.her] [npc2.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая череду [npc2.moans+], [npc2.name] прижал(а) [npc2.her] [npc2.hips] обратно к [npc.namePos] прикосновениям, помогая вогнать [npc.her] [npc.fingers] глубоко в [npc2.her] [npc2.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) оттянуть [npc2.her] [npc2.hips] от нежелательного [npc.namePos] [npc.fingers], но [npc2.her] усилия оказались напрасными.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] пальцами исследует [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.fingers+], засунутый глубоко в [npc2.namePos] паутинное веретено.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Опустив [npc.her] руки к [npc2.namePos] веретену, [npc.name] провел [npc.her] [npc.fingers+] по [npc2.her] паутинному отверстию и, просунув их внутрь, начал [npc.sexPaceVerb] пальцами исследовать [npc2.herHim]");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] сопротивлялась и кричала, когда [npc2.name] схватил [npc.her] [npc.hand] и силой вогнал [npc.her] [npc.fingers] в [npc2.her] веретено.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] пальцами ласкала [npc2.her] паутинное отверстие.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая серию [npc2.moans+], [npc2.name] прижала [npc2.her] веретено обратно к [npc.namePos], помогая вогнать [npc.her] [npc.fingers] глубоко в [npc2.her] паутинное отверстие.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась оттянуть [npc2.her] веретено от нежеланного [npc.namePos] [npc.fingers], но [npc2.her] усилия оказались напрасными.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] пальцами исследует [npc2.namePos] веретено.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	FOOT(1, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "ступня";
			}
			return owner.getLegType().getFootNameSingular(owner);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			System.err.println("Warning: Foot length is being called!");
			return 8;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.FEET;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.FOOT;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] двигал [npc.her] [npc.feet+] вверх и вниз вокруг собственной [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] двигает [npc.her] [npc.feet+] вверх и вниз вокруг собственной [npc.her] "+targetArea.getName(performer)+".");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижал(а) [npc.her] [npc.feet+] к [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимает(а) [npc.her] [npc.feet+] к [npc.her] "+targetArea.getName(performer)+".");
					}
				}
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] продолжал(а) [npc.her] [npc.feet+], прижатый(ую) к [npc2.namePos] [npc2.cock+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.SexPaceVerb] прижимая(а) [npc.her] [npc.feet+] к [npc2.namePos] [npc2.cock+], [npc.name] начал(а) издавать [npc2.herHim] [npc.a_footjob].");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] и пытался(ась) сопротивляться, [npc.name] [npc.was] не смог(ла) помешать [npc2.name] заставить [npc.herHim] отдать [npc2.herHim] [npc.a_footjob].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")
										+" пока [npc.namePos] давал(а) [npc2.herHim] [npc.a_footjob].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вгонял(а) [npc2.her] [npc2.hips] в такт движениям [npc.namePos], и [npc2.name] начал(а) издавать [npc2.moans+], пока [npc2.she] трахал(а) [npc.her] [npc.feet].");
										break;
									case SUB_RESISTING:
										sb.append(" Отчаянно пытаясь отстраниться от [npc.namePos] [npc.feet], [npc2.name] умолял(а), чтобы его/ее оставили в покое, но усилия [npc2.her] оказались напрасными.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] отдает(ает) [npc.a_footjob] к [npc2.name].");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) прижимать [npc.her] [npc.feet+] к лицу [npc2.namePos].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.SexPaceVerb] [npc.her] прижимает [npc.feet+] к [npc2.namePos] лицу, заставляя [npc.name] [npc2.herHim] орально поклоняться им.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пыталась сопротивляться, [npc.name] [npc.was] не смогла помешать [npc2.name] орально поклоняться [npc.her] [npc.feet+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.namePos] [npc.feet+] задыхается, закрыв [npc2.her] лицо.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] вскоре начала издавать [npc2.moans+], пока [npc2.she] [npc2.sexPaceVerb] лизала и целовала [npc.namePos] [npc.feet+].");
										break;
									case SUB_RESISTING:
										sb.append(" Отчаянно пытаясь отстраниться от [npc.name], [npc2.name] пыталась, но не смогла прекратить орально обслуживать [npc.her] [npc.feet].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] заставляет [npc2.name] орально поклоняться [npc.her] [npc.feet+].");
						}
						break;
					case NIPPLE:
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						break;
					case SPINNERET:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TAIL(2, -1f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName || owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return "хвост";
			}
			return owner.getTailName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return owner.getLegTailLength(penetrationLength);
			}
			return owner.getTailLength(penetrationLength);
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return owner.getLegTailDiameter(atLength);
			}
			return owner.getTailDiameter(atLength);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.TAIL;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] трет [npc.her] [npc.tail+] о [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] трещая [npc.her] [npc.tail+] о [npc.her] "+targetArea.getName(performer)+".");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] трахает [npc.her] хвостом "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc.her] хвостом "+targetArea.getName(performer)+".");
					}
				}
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] продолжал [npc.her] [npc.tail+], прижимая его между [npc2.namePos] [npc2.labia] и поглаживая поверх [npc2.her] [npc2.clit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.Name] [npc.sexPaceVerb] терзал [npc.her] [npc.tail+] между [npc2.namePos] [npc2.labia] и поверх [npc2.her] [npc2.clit], дразня [npc2.herHim] обещанием проникновения.");
										break;
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] силой втирал [npc.her] [npc.tail+] между [npc2.namePos] [npc2.labia] и поверх [npc2.her] [npc2.clit], жестоко дразня [npc2.herHim] обещанием проникновения.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался сопротивляться, пока [npc.she] [npc.was] заставлял [npc.her] [npc.tail+] тереться о [npc2.namePos] [npc2.pussy] и [npc2.clit].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")
										+" пока [npc.name] готовился к хвостовому сексу с [npc2.herHim].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Вскоре [npc2.name] обнаружил, что [npc2.herself] в отчаянии возбужден(а), а [npc2.was] [npc2.moaning] готов(а) к тому, чтобы [npc.name] начал(а) хвостовой секс с [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append(" Вскоре [npc2.name] обнаружил, что [npc2.herself] в отчаянии возбужден(а), а [npc2.was] приказывает [npc.name] не двигаться, пока [npc2.she] вжимает [npc2.her] [npc2.clit+] в [npc.namePos] [npc.tail+].");
										break;
									case SUB_RESISTING:
										sb.append(" Плача и умоляя оставить её в покое, [npc2.name] отчаянно пыталась вырваться, но тщетно.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] потирает [npc.her] [npc.tail+] между [npc2.namePos] [npc2.labia] и поверх [npc2.her] [npc2.clit].");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] заставлял [npc.her] [npc.tail+] обвивать [npc2.namePos] [npc2.cock+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] обвил(а) [npc.her] [npc.tail+] вокруг [npc2.namePos] [npc2.cock], прежде чем начать делать [npc2.herHim] хвостовую мастурбацию.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, пока [npc.she] [npc.was] силой обвивал(а) [npc.her] [npc.tail+] вокруг [npc2.namePos] [npc2.cock], чтобы сделать [npc2.herHim] тейлджоб.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")
										+" пока [npc.namePos] делал(а) [npc2.herHim] тейлджоб.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] не мог(ла) сдержать череду похотливых [npc2.moans], пока [npc2.her] [npc2.cock+] использовали таким образом.");
										break;
									case SUB_RESISTING:
										sb.append(" Плача и умоляя оставить её в покое, [npc2.name] отчаянно пытался(ась), но так и не смог(ла) вырваться из рук [npc.name].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] делает [npc2.name] тейлджоб.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] вообще никак не реагировал(а), пока [npc.she] продолжал(а) [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.tail+] к [npc2.namePos] [npc2.asshole+], прежде чем толкнуться вперед и начать [npc.sexPaceVerb] тейлфáкать [npc2.her] [npc2.ass+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] отчаянно пытался(ась) отстраниться, когда [npc2.name] схватил(а) [npc.her] [npc.tail] и заставил(а) [npc.herHim] начать тейлфáкать [npc2.her] [npc2.ass+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] тейлфáкал(а) [npc2.her] [npc2.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] подбрасывал(а) [npc2.her] [npc2.hips] назад к [npc.herHim], и [npc2.name] издал(а) серию [npc2.moans+], пока [npc2.she] помогал(а) вогнать [npc.namePos] [npc.tail+] глубоко в [npc2.her] [npc2.asshole].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.tail+] [npc2.her] [npc2.asshole+] входил(а) и выходил(а).");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] тейлфáкает [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case ASS:
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжала [npc.her] [npc.tail+] глубоко в [npc2.namePos] горло.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.her] [npc.tail+] к [npc2.namePos] [npc2.lips+], прежде чем [npc.sexPaceVerb] толкнулась вперед и вогнала его в [npc2.her] рот.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не могла помешать [npc2.name] обхватить [npc2.her] [npc2.lips+] вокруг [npc.her] [npc.tail] и заглотить его в [npc2.her] рот.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала лицом [npc2.her] своим хвостом.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] продолжала издавать приглушенные [npc2.moans], пока [npc2.she] [npc2.sexPaceVerb] сосала и лизала [npc.tail+] прямо перед [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" Издавая приглушенные рыдания и крики, [npc2.name] пыталась, но не смогла отстраниться от [npc.tail+] перед [npc2.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] в лицо.");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжала [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.nipple(true)].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижала [npc.her] [npc.tail] к одной из [npc2.namePos] [npc2.nipples+], прежде чем толкнуться вперед и трахнуть хвостом [npc2.her] [npc2.breast+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не могла помешать [npc2.name] заставить [npc.herHim] проникнуть и трахнуть хвостом [npc2.her] [npc2.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала хвостом [npc2.nipple(true)] [npc2.her].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" При каждом толчке [npc2.Name] [npc2.sexPaceVerb] выпячивал [npc2.her] грудь, [npc2.moaning+], пока [npc2.she] помогал погружать [npc.namePos] [npc.tail+] глубоко в [npc2.her] [npc2.nipple(true)].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.tail+] входил и выходил из [npc2.her] [npc2.nipple+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахал хвостом [npc2.namePos] [npc2.nipple+].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.crotchNipple(true)].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижал [npc.her] [npc.tail] к одной из [npc2.namePos] [npc2.crotchNipples+], прежде чем рвануть вперед и трахнуть хвостом [npc2.her] [npc2.crotchBoob+(true)].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] войти в [npc.herHim] и начать трахать [npc2.her] сзади, используя хвост, [npc2.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахал хвостом [npc2.her] [npc2.crotchNipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" При каждом толчке [npc2.Name] [npc2.sexPaceVerb] выпячивал [npc2.her] пах, [npc2.moaning+], пока [npc2.she] помогал погружать [npc.namePos] [npc.tail+] глубоко в [npc2.her] [npc2.crotchNipple(true)].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.tail+] входил и выходил из [npc2.her] [npc2.crotchNipple+(true)].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает хвостом [npc2.namePos] [npc2.crotchNipple+].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжал [npc.her] [npc.tail+] глубоко внутри [npc2.namePos] [npc2.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc.her] [npc.tail] к [npc2.cockHead] [npc2.namePos] [npc2.cock], [npc.name] [npc.sexPaceVerb] толкнулся вперед и начал tail-fucking [npc2.her] [npc2.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("В попытках вырваться и со слезами на глазах, [npc.name] пыталась отстраниться, пока [npc2.name] заставлял [npc.herHim] проникать и начинать tail-fucking [npc2.her] cock's [npc2.penisUrethra+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] tail-fucked [npc2.her] [npc2.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] вытолкнул [npc2.her] [npc2.hips] против [npc.name], помогая [npc.herHim] погрузить [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.penisUrethra].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.tail+] входил и выходил из [npc2.her] [npc2.penisUrethra+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает хвостом [npc2.namePos] [npc2.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc.she] продолжал [npc.her] [npc.tail+] глубоко внутри [npc2.namePos] [npc2.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc.her] [npc.tail] к [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] толкнулся вперед и начал tail-fucking [npc2.her] [npc2.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("Извиваясь и плача, [npc.name] пыталась отстраниться, пока [npc2.name] заставлял [npc.herHim] проникать и начинал трахать хвостом [npc2.vaginaUrethra+] [npc2.her].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахала хвостом [npc2.her] [npc2.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] вгонял [npc2.her] [npc2.hips] наружу против [npc.name], помогая [npc.herHim] погрузить [npc.her] [npc.tail+] глубоко в [npc2.her] [npc2.vaginaUrethra].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.tail+] входил и выходил из [npc2.her] [npc2.vaginaUrethra+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает хвостом [npc2.namePos] [npc2.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал вгонять [npc.her] [npc.tail+] глубоко в [npc2.namePos] [npc2.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Скользя [npc.her] [npc.tail] вверх и вниз между [npc2.namePos] [npc2.labia+], [npc.name] [npc.sexPaceVerb] толкнулся вперед, погружая [npc.her] [npc.tail+] в [npc2.her] [npc2.pussy+], прежде чем начать трахать хвостом [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Грубо толкая [npc.her] [npc.tail] вверх и вниз между [npc2.namePos] [npc2.labia+], [npc.name] яростно рванулся вперед, вонзая [npc.her] [npc.tail+] в [npc2.her] [npc2.pussy+], прежде чем начать с силой трахать хвостом [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] извивалась и кричала, умоляя оставить её в покое, но [npc.was] в конечном счете не смогла помешать тому, как [npc2.name] схватила [npc.her] [npc.tail+] и направила её в [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] [npc2.pussy+] tail-fucking [npc2.her].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] в восторге, [npc2.name] получала огромное удовольствие от того, как [npc2.sexPaceVerb] оседлала [npc.namePos] [npc.tail+].");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] плакала и умоляла оставить её в покое, [npc2.name] [npc2.was] не смогла помешать тому, как [npc.name] tail-fucking [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает хвостом [npc2.namePos] [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжала [npc.her] [npc.tail+] глубоко внутри [npc2.namePos] паучьего веретена.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Скользя [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] паучьему веретену, [npc.name] [npc.sexPaceVerb] резко подалась вперед, погружая [npc.her] [npc.tail+] в [npc2.her] отверстие для плетения паутины, прежде чем начать tail-fucking [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Грубо толкая [npc.her] [npc.tail] вверх и вниз по [npc2.namePos] паучьему веретену, [npc.name] яростно подалась вперед, вонзая [npc.her] [npc.tail+] в [npc2.her] отверстие для плетения паутины, прежде чем начать с силой tail-fucking [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] извивалась и кричала, умоляя оставить её в покое, но [npc.was] в конечном счете не смогла помешать тому, как [npc.her] [npc.tail+] была схвачена [npc2.name] и направлена в [npc2.her] паучье веретено.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] tail-fucking [npc2.her] spinneret.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] в восторге, [npc2.name] получала огромное удовольствие, используя [npc.her] spinneret, чтобы [npc2.sexPaceVerb] ride [npc.namePos] [npc.tail+].");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.Moaning] в восторге, [npc2.name] [npc2.sexPaceVerb] вгонял(а) [npc.namePos] [npc.tail+] глубоко в [npc2.her] spinneret.");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] плакал(а) и умолял(а) оставить его/её в покое, [npc2.name] [npc2.was] не мог(ла) остановить [npc.name] от tail-fucking [npc2.her] spinneret.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает хвостом [npc2.namePos] паутинную железу.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TENTACLE(3, -1.5f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "щупальце";
			}
			return owner.getTentacleName(false);
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getTentacleLength(penetrationLength);
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			return owner.getTentacleDiameter(atLength);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] тер(ла) [npc.her] [npc.tentacle+] о [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] тер(ла) [npc.her] [npc.tentacle+] о [npc.her] "+targetArea.getName(performer)+".");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] оттрахана щупальцами [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] секс щупальцами [npc.her] "+targetArea.getName(performer)+".");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] продолжал(а) [npc.her] [npc.tentacle+] прижатым(ой) между [npc2.namePos] [npc2.labia] и поверх [npc2.her] [npc2.clit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.Name] [npc.sexPaceVerb] [npc.her] [npc.tentacle+] между [npc2.namePos] [npc2.labia] и поверх [npc2.her] [npc2.clit], дразня [npc2.herHim] обещанием проникновения.");
										break;
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] силой втирал [npc.her] [npc.tentacle+] между [npc2.namePos] [npc2.labia] и поверх [npc2.her] [npc2.clit], жестоко дразня [npc2.herHim] обещанием проникновения.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, пока [npc.she] [npc.was] силой [npc.her] [npc.tentacle+] поверх [npc2.namePos] [npc2.pussy] и [npc2.clit].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")
										+" пока [npc.name] готовился оттрахать [npc2.herHim] щупальцами.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" Вскоре [npc2.name] обнаружил, что [npc2.herself] в отчаянии возбуждена, а [npc2.was] [npc2.moaning], чтобы [npc.name] начал трахать [npc2.herHim] щупальцами.");
										break;
									case DOM_ROUGH:
										sb.append(" Вскоре [npc2.name] обнаружил, что [npc2.herself] в отчаянии возбуждена, а [npc2.was] приказывает [npc.name] не двигаться, пока [npc2.she] втирает [npc2.her] [npc2.clit+] в [npc.namePos] [npc.tentacle+].");
										break;
									case SUB_RESISTING:
										sb.append(" Плача и умоляя оставить её в покое, [npc2.name] отчаянно пыталась вырваться, но тщетно.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], потирающий [npc.her] [npc.tentacle+] между [npc2.namePos] [npc2.labia] и поверх [npc2.her] [npc2.clit].");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] держал [npc.her] [npc.tentacle+] обернутыми вокруг [npc2.namePos] [npc2.cock+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] обернул [npc.her] [npc.tentacle+] вокруг [npc2.namePos] [npc2.cock], прежде чем начать делать [npc2.herHim] тентакль-джойб.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, пока [npc.she] [npc.was] силой обвивал(а) [npc.her] [npc.tentacle+] вокруг [npc2.namePos] [npc2.cock], чтобы устроить [npc2.herHim] тентакль-джойб.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"крепко спал(а) и даже не думал(а) просыпаться":"продолжал(а) вести себя как неодушевленная секс-игрушка и совершенно не реагировал(а)")
										+" пока [npc.namePos] делал(а) [npc2.herHim] тентакль-джойб.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] не мог(ла) сдержать череду похотливых [npc2.moans], пока [npc2.her] [npc2.cock+] использовали таким образом.");
										break;
									case SUB_RESISTING:
										sb.append(" Плача и умоляя оставить её в покое, [npc2.name] отчаянно пытался(ась), но так и не смог(ла) вырваться из рук [npc.name].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] делает [npc2.name] тентакль-джойб.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] вообще никак не реагировала, пока [npc.she] продолжал(а) [npc.her] [npc.tentacle+] глубоко вгонять в [npc2.namePos] [npc2.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.asshole+], а затем толкнулся вперед, начиная [npc.sexPaceVerb] щупальцевым сексом с [npc2.her] [npc2.ass+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] отчаянно пыталась вырваться, пока [npc2.name] схватил(а) [npc.her] [npc.tentacle] и заставил(а) [npc.herHim] начать тентакль-трахать [npc2.her] [npc2.ass+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] тентакль-трахал(а) [npc2.her] [npc2.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вжимал(а) [npc2.her] [npc2.hips] обратно в [npc.herHim], и [npc2.name] издал(а) серию [npc2.moans+], пока [npc2.she] помогал(а) вогнать [npc.namePos] [npc.tentacle+] глубоко в [npc2.her] [npc2.asshole].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.tentacle+] входил(а) и выходил(а) из [npc2.her] [npc2.asshole+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] тентакль-трахает [npc2.namePos] [npc2.asshole+].");
						}
						break;
					case ASS:
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] горло.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.her] [npc.tentacle+] к [npc2.namePos] [npc2.lips+], прежде чем [npc.sexPaceVerb] толкнул его вперед и вогнал в [npc2.her] рот.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не могла помешать [npc2.name] обхватить [npc2.her] [npc2.lips+] вокруг [npc.her] [npc.tentacle] и заглотить его в [npc2.her] рот.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] щупальцевым сексом трахала [npc2.her] в лицо.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] продолжала издавать приглушенные [npc2.moans], пока [npc2.she] [npc2.sexPaceVerb] сосал и лизал [npc.tentacle+] перед [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" Издавая приглушенные рыдания и крики, [npc2.name] пыталась, но не смогла отстраниться от [npc.tentacle+] перед [npc2.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахает [npc2.namePos] в лицо.");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.nipple(true)].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижала [npc.her] [npc.tentacle] к одному из [npc2.namePos] [npc2.nipples+], прежде чем толкнуться вперед и начать щупальцевым сексом трахать [npc2.her] [npc2.breast+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не могла помешать [npc2.name] заставить [npc.herHim] проникнуть и щупальцевым сексом трахать [npc2.her] [npc2.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] щупальцевым сексом трахала [npc2.her] [npc2.nipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] выталкивал(а) [npc2.her] грудь при каждом толчке, [npc2.moaning+], пока [npc2.she] помогал(а) погрузить [npc.namePos] [npc.tentacle+] глубоко в [npc2.her] [npc2.nipple(true)].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) отстраниться, но [npc.name] [npc.was] удавалось удерживать(,) [npc2.herHim] на месте, даже когда [npc.her] [npc.tentacle+] входил(а) и выходил(а) из [npc2.her] [npc2.nipple+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] щупальцевым трахом [npc2.namePos] [npc2.nipple+].");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировал(а), пока [npc.she] продолжал(а) [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.crotchNipple(true)].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижал(а) [npc.her] [npc.tentacle] к одной из [npc2.namePos] [npc2.crotchNipples+], прежде чем рвануться вперед и заняться щупальцевым трахом [npc2.her] [npc2.crotchBoob+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] пронзить и оттрахать [npc2.her] щупальцами [npc.herHim] [npc2.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] трахался(ась) щупальцами в [npc2.her] [npc2.crotchNipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] выталкивал(а) [npc2.her] пах при каждом толчке, [npc2.moaning+], пока [npc2.she] помогал(а) погрузить [npc.namePos] [npc.tentacle+] глубоко в [npc2.her] [npc2.crotchNipple].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) отстраниться, но [npc.name] [npc.was] удавалось удерживать(,) [npc2.herHim] на месте, даже когда [npc.her] [npc.tentacle+] входил(а) и выходил(а) из [npc2.her] [npc2.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] щупальцевым трахом [npc2.namePos] [npc2.crotchNipple+].");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) [npc.her] [npc.tentacle+], глубоко вбивая в [npc2.namePos] [npc2.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc.her] [npc.tentacle] к [npc2.cockHead] [npc2.namePos] [npc2.cock], [npc.name] [npc.sexPaceVerb] рванулся(ась) вперед и начал(а) щупальцевый трах [npc2.her] [npc2.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("Извиваясь и плача, [npc.name] пытался(ась) вырваться, пока [npc2.name] заставлял(а) [npc.herHim] проникать и начинал(а) щупальцевый трах в [npc2.her] член [npc2.penisUrethra+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] щупальцевым трахом трахал(а) [npc2.her] [npc2.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] вытолкнул(а) [npc2.her] [npc2.hips] против [npc.name], помогая [npc.herHim] погрузить [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.penisUrethra].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась) отстраниться, но [npc.name] [npc.was] удалось(ась) удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.tentacle+] входил(а) и выходил(а) из [npc2.her] [npc2.penisUrethra+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] щупальцевым трахом [npc2.namePos] [npc2.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc.she] продолжал(а) [npc.her] [npc.tentacle+], глубоко вбивая в [npc2.namePos] [npc2.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc.her] [npc.tentacle] к [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] рванулся(ась) вперед и начал(а) щупальцевый трах [npc2.her] [npc2.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("Извиваясь и плача, [npc.name] пыталась вырваться, пока [npc2.name] заставлял [npc.herHim] проникать и начинать щупальцевый трах в [npc2.vaginaUrethra+] киски [npc2.her].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] щупальцево трахала [npc2.her] [npc2.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.sexPaceVerb] толкнул [npc2.her] [npc2.hips] наружу против [npc.name], помогая [npc.herHim] погрузить [npc.her] [npc.tentacle+] глубоко в [npc2.her] [npc2.vaginaUrethra].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась отстраниться, но [npc.name] [npc.was] удавалось удерживать [npc2.herHim] на месте, даже когда [npc.her] [npc.tentacle+] входил и выходил из [npc2.her] [npc2.vaginaUrethra+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] щупальцево трахала [npc2.namePos] [npc2.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжал вгонять [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] [npc2.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Скользя [npc.her] [npc.tentacle] вверх и вниз между [npc2.namePos] [npc2.labia+], [npc.name] [npc.sexPaceVerb] толкнулся вперед, погружая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.pussy+], прежде чем начать щупальцевый трах [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Грубо толкая [npc.her] [npc.tentacle] вверх и вниз между [npc2.namePos] [npc2.labia+], [npc.name] яростно рванулся вперед, вонзая [npc.her] [npc.tentacle+] в [npc2.her] [npc2.pussy+], прежде чем начать с силой щупальцево трахать [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] извивалась и кричала, умоляя оставить её в покое, но [npc.was] в конечном итоге не смогла помешать [npc.her], когда [npc2.name] схватила [npc.tentacle+] и направила её в [npc2.her] [npc2.pussy+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] щупальцевым сексом трахала [npc2.her] [npc2.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] в восторге, [npc2.name] получала огромное удовольствие от того, что [npc2.sexPaceVerb] оседлала [npc.namePos] [npc.tentacle+].");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] плакала и умоляла оставить её в покое, [npc2.name] [npc2.was] не смогла помешать [npc.name] трахать [npc2.her] щупальцами [npc2.pussy+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахала [npc2.namePos] щупальцами [npc2.pussy+].");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc.she] продолжала вбивать [npc.her] [npc.tentacle+] глубоко в [npc2.namePos] паучье веретено.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Скользя [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] веретену, [npc.name] [npc.sexPaceVerb] резко подалась вперед, погружая [npc.her] [npc.tentacle+] в [npc2.her] паучье отверстие для плетения паутины, прежде чем начать трахать [npc2.herHim] щупальцами.");
										break;
									case DOM_ROUGH:
										sb.append("Грубо толкая [npc.her] [npc.tentacle] вверх и вниз по [npc2.namePos] веретену, [npc.name] яростно рванулась вперед, вонзая [npc.her] [npc.tentacle+] в [npc2.her] паучье отверстие для плетения паутины, прежде чем начать с силой трахать [npc2.herHim] щупальцами.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] извивалась и кричала, умоляя оставить её в покое, но [npc.was] в конечном итоге не смогла помешать тому, как [npc2.name] схватил(а) [npc.her] [npc.tentacle+] и направил(а) её в [npc2.her] паутину.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать и не подавал(а) никаких признаков скорого пробуждения":"продолжал(а) вести себя как неодушевленная секс-игрушка, оставаясь совершенно неподвижным(ой)")
										+" пока [npc.name] щупальцевым сексом трахала [npc2.her] паутину.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] в восторге, [npc2.name] получала огромное удовольствие, используя [npc.her] паутину, чтобы [npc2.sexPaceVerb] оседлать [npc.namePos] [npc.tentacle+].");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.Moaning] в восторге, [npc2.name] [npc2.sexPaceVerb] вгонял(а) [npc.namePos] [npc.tentacle+] глубоко в [npc2.her] паутину.");
										break;
									case SUB_RESISTING:
										sb.append(" Хотя [npc2.she] плакала и умоляла оставить её в покое, [npc2.name] [npc2.was] не смогла помешать тому, как [npc.name] трахала [npc2.her] паутину щупальцами.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] трахала [npc2.namePos] паутину щупальцами.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	};
	
//	TOY(4, -2f, false) {
//		@Override
//		public String getName(GameCharacter owner, boolean standardName) {
//			// Cannot know which orifice is being penetrated, so always return "toy":
//			return "toy";
//		}
//		@Override
//		public int getLength(GameCharacter owner, boolean penetrationLength) {
//			System.err.println("Warning: Toy length is being called!");
//			return 8;
//		}
//		@Override
//		public boolean isFree(GameCharacter owner) {
//			return Main.sex.isPenetrationTypeFree(owner, this);
//		}
//		@Override
//		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
//			return CoverableArea.NONE;
//		}
//		@Override
//		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
//			StringBuilder sb = new StringBuilder();
//			if(performer==target) {
//				System.err.println("SexAreaPenetration.TOY getSexDescription() error: Does not support self actions!");
//				return "";
//			}
//			
//			if(targetArea.isPenetration()) {
//				switch((SexAreaPenetration)targetArea) {
//					case CLIT:
//						break;
//					case FINGER:
//						break;
//					case FOOT:
//						break;
//					case PENIS:
//						break;
//					case TAIL:
//						break;
//					case TENTACLE:
//						break;
//					case TONGUE:
//						break;
//					case TOY:
//						break;
//				}
//				
//			} else {
//				switch((SexAreaOrifice)targetArea) {
//					case ASS:
//						break;
//					case BREAST:
//						break;
//					case BREAST_CROTCH:
//						break;
//					case THIGHS:
//						break;
//					case ANUS:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] pressed the tip of a toy against [npc2.namePos] [npc2.asshole+], before thrusting forwards and starting to [npc.sexPaceVerb] fuck [npc2.her] [npc2.ass+] with it.");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.ass+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] back against [npc.herHim],"
//											+ " [npc2.name] let out a series of [npc2.moans+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.asshole].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place while [npc.she] thrust the toy in and out of [npc2.her] [npc2.asshole+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.asshole+].");
//						}
//						break;
//					case MOUTH:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] pressed the tip of a toy against [npc2.namePos] [npc2.lips+], before [npc.sexPaceVerb] pushing it forwards into [npc2.her] mouth.");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] throat using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] kept on letting out muffled [npc2.moans] as [npc2.she] sucked and licked the toy.");
//									break;
//								case SUB_RESISTING:
//									sb.append(" Letting out muffled sobs and cries, [npc2.name] tried to pull away from the toy, but ended up being forced to take it deep down [npc2.her] throat.");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] face.");
//						}
//						break;
//					case NIPPLE:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] [npc.sexPaceVerb] pushed the tip of a toy against one of [npc2.namePos] [npc2.nipples+], before thrusting forwards and using it to fuck [npc2.her] [npc2.breast+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.nipples+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] chest on every thrust, [npc2.moaning+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.nipple(true)].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while the toy thrust in and out of [npc2.her] [npc2.nipple+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.nipple+].");
//						}
//						break;
//					case NIPPLE_CROTCH:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] [npc.sexPaceVerb] pushed the tip of a toy against one of [npc2.namePos] [npc2.crotchNipples+], before thrusting forwards and using it to fuck [npc2.her] [npc2.crotchBoob+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.crotchNipples+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] groin on every thrust, [npc2.moaning+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.crotchNipple].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while the toy thrust in and out of [npc2.her] [npc2.crotchNipple+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.crotchNipple+].");
//						}
//						break;
//					case URETHRA_PENIS:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("Pressing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] own [npc2.cock], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.penisUrethra+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] cock's [npc2.penisUrethra+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink a toy deep into [npc2.her] [npc2.penisUrethra].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while a toy thrust in and out of [npc2.her] [npc2.penisUrethra+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.penisUrethra+].");
//						}
//						break;
//					case URETHRA_VAGINA:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("Pressing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.vaginaUrethra+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] pussy's [npc2.vaginaUrethra+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink a toy deep into [npc2.her] [npc2.vaginaUrethra].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while a toy thrust in and out of [npc2.her] [npc2.vaginaUrethra+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.vaginaUrethra+].");
//						}
//						break;
//					case VAGINA:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//									sb.append("Sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down between [npc2.namePos] [npc2.labia+],"
//											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking a toy into [npc2.namePos] [npc2.pussy+] before starting to fuck [npc2.herHim].");
//									break;
//								case DOM_ROUGH:
//									sb.append("Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down between [npc2.namePos] [npc2.labia+],"
//											+ " [npc.name] violently thrust forwards, sinking a toy into [npc2.namePos] [npc2.pussy+] before starting to forcefully fuck [npc2.herHim].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent a toy from being grabbed by [npc2.name] and guided into [npc2.her] [npc2.pussy+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in [npc2.sexPaceVerb] riding [npc.namePos] [npc.cock+].");
//									break;
//								case SUB_EAGER:
//								case SUB_NORMAL:
//									sb.append(" [npc2.Moaning] in delight, [npc2.name] [npc2.sexPaceVerb] bucked [npc2.her] hips to help drive [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.pussy+].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] [npc2.pussy+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.pussy+].");
//						}
//						break;
//				}
//			}
//			return UtilText.parse(performer, target, sb.toString());
//		}
//	};

	
	private float baseArousalWhenPenetrating;
	private float arousalChangePenetratingDry;
	private boolean takesVirginity;

	private SexAreaPenetration(float baseArousalWhenPenetrating, float arousalChangePenetratingDry, boolean takesVirginity) {
		this.baseArousalWhenPenetrating = baseArousalWhenPenetrating;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.takesVirginity = takesVirginity;
	}

	@Override
	public boolean isOrifice() {
		return false;
	}
	
	public boolean appliesStretchEffects(GameCharacter owner) {
		return getDiameter(owner, 0)!=-1;
	}

	public abstract int getLength(GameCharacter owner, boolean penetrationLength);

	/** The diameter of the owner's SexAreaPenetration at the length specified, measured from the base. Diameter is the unit of measurement for all Capacity values. */
	public float getDiameter(GameCharacter owner, int atLength) {
		return -1;
	};
	
	public float getBaseArousalWhenPenetrating() {
		return baseArousalWhenPenetrating;
	}
	
	public float getArousalChangePenetratingDry() {
		return arousalChangePenetratingDry;
	}
	
	public boolean isTakesVirginity() {
		return takesVirginity;
	}
	
}
