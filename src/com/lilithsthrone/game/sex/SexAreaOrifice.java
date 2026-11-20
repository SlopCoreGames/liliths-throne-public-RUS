package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;

/**
 * Natural ordering is roughly in assumed order of severity of penetration, with associated areas next to one another (but all urethras and the spinneret are at the end as they're exceptional orifices).
 * 
 * 
 * @since 0.1.78
 * @version 0.4.10.12
 * @author Innoxia
 */
public enum SexAreaOrifice implements SexAreaInterface {

	VAGINA(4,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "киска";
			}
			return owner.getVaginaName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
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
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getVaginaStretchedCapacity();
			}
			return owner.getVaginaRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getVaginaMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getVaginaMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.pussy+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.pussy+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижал(а) [npc.her] [npc.pussy+] к [npc.her] собственному(ой) "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], прижимая [npc.her] [npc.pussy+] к [npc.her] собственному(ой) "+targetArea.getName(performer)+".");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc2.namePos] [npc2.clit+] вонзался(ась) в [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Потирая [npc.her] [npc.labia+] по [npc2.clitTip+] [npc2.namePos] [npc2.clit], [npc.name] [npc.sexPaceVerb] выгибал(а) [npc.her] [npc.hips] и заставлял(а) [npc2.herHim] проникать в [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) вырваться, но не смог(ла) помешать [npc2.name] втискивать [npc2.her] [npc2.clit+] в [npc.her] [npc.pussy+] и начать трахать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, пока [npc2.her] [npc2.clit] был глубоко в [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] с силой вгонял [npc2.her] [npc2.clit+] в [npc.namePos] [npc.pussy+], и вскоре [npc2.name] [npc2.was] начала [npc2.moaning] от удовольствия, пока [npc2.she] трахал [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.clit] из [npc.namePos] [npc.pussy], и ей оставалось только плакать, пока [npc2.she] использовали [npc2.her] [npc2.clit] против воли [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], когда ей трахали клитор [npc2.name].");
						}
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] глубоко вгонял [npc2.her] [npc2.fingers+] в [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] за [npc2.hand+], [npc.name] начал водить [npc2.her] [npc2.fingers+] вверх и вниз по [npc.her] [npc.labia+], прежде чем заставить [npc2.herHim] скользнуть внутрь и начать пальцевать [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] вгонять [npc2.her] [npc2.fingers+] в [npc.her] [npc.pussy+] и начинать пальцевать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, пока [npc2.her] [npc2.fingers] был глубоко засунут в [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] сворачивается [npc2.her] [npc2.fingers] внутри [npc.namePos] [npc.pussy], [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] перебирает [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась), но не смог(ла) вытащить [npc2.her] [npc2.fingers] из [npc.namePos] [npc.pussy], и не оставалось ничего другого, кроме как рыдать, пока [npc2.she] заставляли пальцами ласкать [npc.herHim] вопреки [npc2.her] воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] перебирает(ет) [npc.name].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не отреагировал(а), когда [npc2.namePos] [npc2.cock+] глубоко вошел(ла) в [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Потирая [npc.her] [npc.labia+] о [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] [npc.sexPaceVerb] выгибался(лась) от [npc.her] [npc.hips], заставляя [npc2.herHim] проникать в [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не смог(ла) помешать [npc2.name] втиснуть [npc2.her] [npc2.cock] в [npc.her] [npc.pussy+] и начать трахать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался(ась) совершенно неподвижно, пока [npc2.her] [npc2.cock] был(а) по самую рукоять в [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вонзает [npc2.her] [npc2.cock+] в [npc.namePos] [npc.pussy+], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] трахает [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] попытался, но не смог вытащить [npc2.her] [npc2.cock] из [npc.namePos] [npc.pussy], и не оставалось ничего другого, кроме как рыдать, пока [npc2.she] [npc2.her] [npc2.cock] использовали вопреки воле [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], которую [npc2.name] трахает.");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] [npc2.tail+] глубоко [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tail+], [npc.name] потер ее вверх и вниз по [npc.her] [npc.labia+], прежде чем заставить [npc2.herHim] проникнуть и начать хвостом трахать [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] вгонять [npc2.her] [npc2.tail+] в [npc.her] [npc.pussy+] и начинать трахать хвостом [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, удерживая [npc2.her] [npc2.tail] глубоко в [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] [npc2.her] [npc2.tail] вгоняет в [npc.namePos] [npc.pussy], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от удовольствия, пока [npc2.she] трахает хвостом [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.tail] из [npc.namePos] [npc.pussy], и ей оставалось только рыдать, пока [npc2.she] использовал ее по своему усмотрению против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], которую трахает хвостом [npc2.name].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] [npc2.tentacle+] глубоко [npc.her] [npc.pussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] потер ее вверх и вниз по [npc.her] [npc.labia+], прежде чем заставить [npc2.herHim] проникнуть и начать щупальцами трахать [npc.her] [npc.pussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] попытался(ась) вырваться, но не смог(ла) помешать [npc2.name] втискивать [npc2.her] [npc2.tentacle+] в [npc.her] [npc.pussy+] и начать щупальцевый трах [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался(ась) совершенно неподвижным(ой), пока [npc2.tentacle] [npc2.her] [npc.namePos] [npc.pussy+] был(а) внутри.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] с силой вгонял(а) [npc2.her] [npc2.tentacle] в [npc.namePos] [npc.pussy], и [npc2.name] [npc2.was] вскоре начал(а) [npc2.moaning] от удовольствия, пока [npc2.she] щупальцево трахал(а) [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] попытался(ась), но не смог(ла) вытащить [npc2.her] [npc2.tentacle] из [npc.namePos] [npc.pussy], и не оставалось ничего другого, кроме как рыдать, пока [npc2.she] использовал(а) это по воле [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], которую/кого щупальцево трахает [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] занимался(ась) куннилингусом с [npc.herHim].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижал(а) [npc.her] [npc.labia+] к [npc2.namePos] [npc2.face], прежде чем [npc.sexPaceVerb] заставил(а) [npc2.herHim] начать поедать [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) сопротивляться, но [npc.was] не смог(ла) помешать [npc2.name] прижать [npc2.her] [npc2.face] к [npc.her] [npc.pussy], а затем начать поедать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" при этом удерживая [npc2.her] [npc2.tongue] внутри [npc.namePos] [npc.pussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая серию [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] прижала [npc2.her] [npc2.lips+] к [npc.namePos] [npc.pussy+] и продолжила заниматься куннилингусом для [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась сопротивляться, но [npc2.was] не смогла помешать [npc.name] впиться [npc.her] [npc.pussy+] в [npc2.her] [npc2.face], а [npc.sexPaceVerb] заставила [npc2.herHim] делать куннилингус для [npc.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] занимается куннилингусом для [npc.name].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},

	ANUS(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 4/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "анус";
			}
			return owner.getAnusName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ANUS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.ANUS;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getAssStretchedCapacity();
			}
			return owner.getAssRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getAssMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getAssMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.asshole+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.asshole+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижала [npc.her] [npc.asshole+] к собственной [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимая [npc.her] [npc.asshole+] к собственной [npc.her] "+targetArea.getName(performer)+".");
					}
				}
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не отреагировала, пока [npc2.namePos] [npc2.clit+] входил в [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Прижимая [npc.her] [npc.ass] к самому [npc2.namePos] паху, [npc.name] [npc.sexPaceVerb] вжимается в [npc2.her] [npc2.clit+], заставляя [npc2.herHim] проникать в [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] втиснуть [npc2.her] [npc2.clit+] в [npc.her] [npc.asshole+] и начать анальный трах [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, удерживая [npc2.her] [npc2.clit] глубоко в [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] выгибается, вбивая [npc2.her] [npc2.hips] в [npc.namePos] [npc.ass], пока [npc2.name] [npc2.was] вскоре не начинает [npc2.moaning] от восторга, когда [npc2.she] трахает [npc.namePos] [npc.asshole+] клитором.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но безуспешно вытащить [npc2.her] [npc2.clit] из [npc.namePos] [npc.asshole], и ей оставалось только рыдать, пока [npc2.she] по своей воле [npc2.clit] [npc2.her] против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs], которой [npc2.name] трахает анус [npc.sexPaceVerb] клитором.");
						}
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] вжимал [npc2.her] [npc2.fingers+] глубоко в [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] за [npc2.hand], [npc.name] [npc.sexPaceVerb] втискивает [npc2.her] [npc2.fingers] в [npc.her] [npc.asshole], заставляя [npc2.herHim] анально играть пальцами с [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался вырваться, но не смог помешать [npc2.name] заталкивать [npc2.her] [npc2.fingers] в [npc.her] [npc.asshole+] и начать анально перебирать пальцами [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался совершенно неподвижным, пока [npc2.fingers] [npc.namePos] [npc.asshole+] глубоко сидел в [npc2.her].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вталкивает [npc2.her] [npc2.fingers] в [npc.namePos] [npc.asshole], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от удовольствия, пока [npc2.she] перебирает пальцами [npc.namePos] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался, но не смог вытащить [npc2.her] [npc2.fingers] из [npc.namePos] [npc.asshole], и ему оставалось только рыдать, пока [npc2.she] заставляли анально перебирать пальцами [npc.namePos] [npc.asshole+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs], которому [npc2.name] анально перебирает пальцами [npc.sexPaceVerb].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал, пока [npc2.namePos] [npc2.cock+] глубоко входил в [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Приставив [npc.her] [npc.ass] к паху [npc2.namePos], [npc.name] [npc.sexPaceVerb] толкнулся навстречу [npc2.her] [npc2.cock], заставляя [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался вырваться, но не смог помешать [npc2.name] заталкивать [npc2.her] [npc2.cock] в [npc.her] [npc.asshole+] и начать анально трахать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался совершенно неподвижным, пока [npc2.her] [npc2.cock] был по самую рукоять в [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] выгибается, [npc2.her] [npc2.hips] в [npc.namePos] [npc.ass], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] трахает [npc.namePos] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.cock] из [npc.namePos] [npc.asshole], и ей оставалось только рыдать, пока [npc2.she] использовал [npc2.her] [npc2.cock] по своей воле против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs], которую [npc.sexPaceVerb] трахает в задницу [npc2.name].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] [npc2.tail+] глубоко входил в [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tail+], [npc.name] прицелился к [npc.her] [npc.ass+] и заставил [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] впихивать [npc2.her] [npc2.tail+] в [npc.her] [npc.asshole+] и начать анал-хвостотрах [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, удерживая [npc2.her] [npc2.tail] торчащим в [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вонзает [npc2.her] [npc2.tail] в [npc.namePos] [npc.asshole], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] анально трахает [npc.name] хвостом.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.tail] из [npc.namePos] [npc.asshole], и ей оставалось только рыдать, пока [npc2.she] использовал это по своей воле против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], которую анально трахает [npc2.name] хвостом.");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] [npc2.tentacle+] глубоко входил в [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] прицелился к [npc.her] [npc.ass+] и заставил [npc2.herHim] проникнуть в [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] впихнуть [npc2.her] [npc2.tentacle+] в [npc.her] [npc.asshole+] и начать анально трахать [npc.herHim] щупальцем.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, удерживая [npc2.her] [npc2.tentacle] торчащей в [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вгоняет [npc2.her] [npc2.tentacle] в [npc.namePos] [npc.asshole], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] анально трахает [npc.name] щупальцем.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.tentacle] из [npc.namePos] [npc.asshole], и ей оставалось только рыдать, пока [npc2.she] использовал это против [npc2.her] по своему усмотрению.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], которую анально трахает [npc2.name] щупальцем.");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] ласкает языком [npc.her] [npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.her] [npc.ass+] обратно к [npc2.namePos] [npc2.face], прежде чем [npc.sexPaceVerb] заставил [npc2.herHim] начать облизывать [npc.her] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] прижать [npc2.her] [npc2.face] к [npc.her] [npc.ass], а затем начать вылизывать [npc.her] [npc.asshole+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" при этом удерживая [npc2.her] [npc2.tongue] вжатым в [npc.namePos] [npc.asshole+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая серию [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] прижала [npc2.her] [npc2.lips+] к [npc.namePos] [npc.asshole+] и продолжила анилингус на [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name] пыталась сопротивляться, но [npc2.was] не смогла помешать [npc.name] впиться [npc.her] [npc.ass] в [npc2.her] [npc2.face], а [npc.sexPaceVerb] заставил(а) [npc2.herHim] заняться анилингусом на [npc.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] занимается анилингусом на [npc.name].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},

	ASS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			// Changed from "ass cheeks" to "ass" in v0.4.10.8
			if(standardName) {
				return "задница";
			}
			return "задница";
//			return owner.getAssName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ASS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы ласкать [npc.her] [npc.ass+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы ласкать [npc.her] [npc.ass+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижал(а) [npc.her] [npc.ass+] к собственному [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимающий(ая) [npc.her] [npc.ass+] к собственному [npc.her] "+targetArea.getName(performer)+".");
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
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не отреагировал(а), пока [npc2.name] ощупывал(а) [npc.her] [npc.ass+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.ass+], [npc.name] [npc.sexPaceVerb] заставлял(а) [npc2.name] ощупывать и сжимать её.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) вырваться, но не мог(ла) помешать [npc2.name] ощупывать и сжимать [npc.her] [npc.ass+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")+", и продолжал(а) удерживать [npc.namePos] [npc.ass+] в [npc2.her] [npc2.hands], не двигаясь.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Выпустив [npc2.a_moan+], [npc2.name] прижал(а) [npc2.her] [npc2.hands] к [npc.namePos] [npc.ass], и [npc2.sexPaceVerb] продолжал(а) играть с ней.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась), но не смог(ла) отстраниться от [npc.name], и оставалось только плакать, пока [npc2.she] [npc2.was] заставлял(а) играть с [npc.her] [npc.ass+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] ощупывает и сжимает [npc.namePos] [npc.ass+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.cock+] скользил(а) вверх и вниз между ягодицами [npc.her].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Сжимая ягодицы [npc.her], [npc.name] заставлял(а) [npc2.name] двигать [npc2.her] [npc2.cock+] вверх и вниз в образовавшейся щели.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пытался(ась) сопротивляться, [npc.name] сжал(а) ягодицы [npc.her], после чего устроил(а) ей «горячую сосиску» с помощью [npc2.namePos] [npc2.cock+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] продолжал [npc2.her] [npc2.cock] между ягодицами [npc.namePos].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.a_moan+], [npc2.name] [npc2.sexPaceVerb] вгонял [npc2.her] [npc2.cock+] вверх и через [npc.namePos] [npc.asshole+].");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] вовсю выставлял напоказ [npc.namePos] задницу.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] целовал и лизал [npc.her] [npc.ass+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] втиснул [npc.her] [npc.ass+] в [npc2.namePos] [npc2.face], прежде чем заставить [npc2.herHim] начать целовать и лизать это.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но не смогла помешать [npc2.name] прижимать [npc2.her] [npc2.lips] к [npc.her] [npc.ass+] и начинать целовать и лизать это.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" при этом удерживая [npc2.her] [npc2.lips+] прижатой к [npc.namePos] [npc.ass].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенный [npc2.moans], [npc2.name] прижала [npc2.her] [npc2.lips+] к [npc.namePos] [npc.ass+] и продолжила [npc2.sexPaceVerb] целовать его.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась сопротивляться, но [npc2.was] не могла помешать [npc.name] вжимать [npc.her] [npc.ass+] в [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] целует [npc.namePos] [npc.ass+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	MOUTH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			2/60f, 15/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "рот";
			}
			return owner.getMouthName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this) && Main.sex.isPenetrationTypeFree(owner, SexAreaPenetration.TONGUE);
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
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getFaceStretchedCapacity();
			}
			return owner.getFaceRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getFaceMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getFaceMaximumPenetrationDepthUncomfortable();
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
						sb.append("[npc.Name] [npc.sexPaceVerb] поцеловала и облизала свой собственный [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] целует и лижет свой собственный [npc.her] "+targetArea.getName(performer)+".");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] продолжала [npc.her] [npc.lips+] обернутыми вокруг [npc2.namePos] [npc2.clit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Поднеся [npc.her] [npc.face] к паху [npc2.namePos], [npc.name] [npc.sexPaceVerb] обхватила [npc.her] [npc.lips+] вокруг [npc2.her] [npc2.clit+] и занялась оральным сексом.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] впихивать [npc2.her] [npc2.clit+] в рот [npc.her], заставляя [npc.herHim] заняться оральным сексом.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")+", и совсем не реагировала на то, как [npc2.she] [npc2.her] [npc2.clit] сосала.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] выгибается, [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, ведь [npc2.she] еще никогда не [npc2.her] [npc2.clit] так, как сейчас.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.clit] изо рта [npc.namePos], и ей оставалось только плакать, пока [npc2.she] [npc2.her] [npc2.clit] сосала вопреки [npc2.her] воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] сосет [npc2.namePos] [npc2.clit+].");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спит":"Не шевелясь")+", [npc.name] продолжала прижимать [npc.her] [npc.lips+] к [npc2.namePos] [npc2.feet], чтобы орально им поклоняться.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Опуская [npc.her] [npc.face] к самым кончикам [npc2.namePos] [npc2.legs], [npc.name] [npc.sexPaceVerb] прижала [npc.her] [npc.lips+] к [npc2.her] [npc2.feet] и принялась в них орально поклоняться.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] запихнуть [npc2.her] [npc2.feet] в рот [npc.her], заставляя [npc.herHim] орально им поклоняться.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] продолжала удерживать [npc2.her] [npc2.feet] втиснутым в [npc.namePos] [npc.face].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] втискивал(а) [npc2.her] [npc2.feet] в [npc.namePos] [npc.face], и вскоре [npc2.name] [npc2.was] [npc2.moaning] от восторга, пока [npc2.she] орально поклонялся(ась) им.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась), но безуспешно оттянуть [npc2.her] [npc2.feet] от рта [npc.namePos], и не оставалось ничего, кроме как рыдать, пока [npc2.she] вопреки [npc2.her] воле заставляли их орально боготворить.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] целовал(а) и лизал(а) [npc2.namePos] [npc2.feet].");
						}
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"крепко спит":"неподвижный(ая), словно статуя")+", [npc.name] продолжал(а) держать [npc.her] [npc.lips+] обхваченными вокруг [npc2.namePos] [npc2.cock].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Подняв [npc.her] [npc.face] к паху [npc2.namePos], [npc.name] [npc.sexPaceVerb] обхватил(а) [npc.her] [npc.lips+] вокруг [npc2.her] [npc2.cock] и отсосал(а) [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) вырваться, но не смог(ла) помешать [npc2.name] втискивать [npc2.her] [npc2.cock] в рот [npc.her], заставляя [npc.herHim] делать [npc2.herHim] минет.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) спать":"совсем не реагировал(а) и не двигался(ась)")+" пока [npc2.she] держал(а) [npc2.her] [npc2.cock+], погруженным(ой) в [npc.namePos] горло.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] выгибается, [npc2.her] [npc2.hips] в [npc.namePos] [npc.face], [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, ведь [npc2.she] еще никогда не [npc2.her] [npc2.cock] так, как сейчас.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась) и не смог(ла) вытащить [npc2.her] [npc2.cock] изо рта [npc.namePos], и не оставалось ничего другого, кроме как рыдать, пока [npc2.she] [npc2.her] [npc2.cock] сосал(а) вопреки [npc2.her] воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] делает [npc2.name] минет.");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"крепко спит":"неподвижный(ая), словно статуя")+", [npc.name] продолжал(а) [npc.her] [npc.lips+] обхватывать вокруг [npc2.namePos] [npc2.tail].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] обхватил(а) [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.tail] и начал(а) сосать его.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не смог(ла) помешать [npc2.name] запихивать [npc2.her] [npc2.tail] в рот [npc.her], заставляя [npc.herHim] сосать его.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) спать":"совсем не реагировал(а) и не двигался(ась)")+" пока [npc2.she] продолжал(а) [npc2.her] [npc2.tail+] глубоко вгонять в [npc.namePos] горло.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.was] вскоре [npc2.moaning] от восторга, когда [npc2.she] [npc2.sexPaceVerb] вгонял(а) [npc2.her] [npc2.tail] в [npc.namePos] горло.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась) и не смог(ла) вытащить [npc2.her] [npc2.tail] изо рта [npc.namePos], и не оставалось ничего другого, кроме как рыдать, пока его сосали вопреки [npc2.her] воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] сосет [npc2.namePos] [npc2.tail].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"крепко спит":"неподвижный(ая), словно статуя")+", [npc.name] продолжал [npc.her] [npc.lips+], обхватив ими [npc2.namePos] [npc2.tentacle].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] обхватил(а) [npc.her] [npc.lips+] вокруг [npc2.namePos] [npc2.tentacle] и начал(а) сосать его.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не смог(ла) помешать [npc2.name] заталкивать [npc2.her] [npc2.tentacle] в рот [npc.her], заставляя [npc.herHim] сосать его.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) спать":"совсем не реагировал(а) и не двигался(ась)")+" пока [npc2.she] продолжал(а) [npc2.her] [npc2.tentacle+], глубоко проталкивая их в глотку [npc.namePos].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] [npc2.was] вскоре [npc2.moaning] от удовольствия, пока [npc2.she] [npc2.sexPaceVerb] [npc2.her] [npc2.tentacle] проталкивал(а) в глотку [npc.namePos].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась), но безуспешно вытащить [npc2.her] [npc2.tentacle] изо рта [npc.namePos], и не оставалось ничего другого, кроме как плакать, пока их сосали вопреки воле [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] сосет(ет) [npc2.namePos] [npc2.tentacle].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" прижимая [npc.her] [npc.lips+] к рту [npc2.namePos].");
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
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" при этом удерживая [npc2.her] [npc2.tongue] внутри [npc.namePos] [npc.mouth].");
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
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	NIPPLE(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "сосок";
			}
			return owner.getNippleName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NIPPLES;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.NIPPLE;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getNippleStretchedCapacity();
			}
			return owner.getNippleRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getNippleMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getNippleMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.nipples+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.nipples+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижал(а) [npc.her] [npc.nipples+] к [npc.her] собственному "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимает [npc.her] [npc.nipples+] к [npc.her] собственной "+targetArea.getName(performer)+".");
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
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не отреагировал(а), пока [npc2.name] щипал(а) и сжимал(а) [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] заставил(а) [npc2.name] щипать и сжимать [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не смог(ла) помешать [npc2.name] щипать и сжимать [npc.her] [npc.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")+", и продолжал(а) держать [npc.namePos] [npc.nipples+] зажатым(ой) между [npc2.her] [npc2.fingers], не двигаясь.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.a_moan+], [npc2.name] прижал(а) [npc2.her] [npc2.hands] к [npc.namePos] [npc.breasts], а [npc2.sexPaceVerb] играл(а) с [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась) — и безуспешно — отстраниться от [npc.name], и не мог(ла) ничего, кроме как рыдать, пока [npc2.she] [npc2.was] заставляли играть с [npc.her] [npc.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] щипает и ласкает [npc.namePos] [npc.nipples+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.cock+] глубоко входил(а) в [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] направил(а) [npc2.namePos] [npc2.cock+] к [npc.her] [npc.nipples+] и заставил(а) [npc2.herHim] начать трахать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не смог(ла) помешать [npc2.name] прижать [npc2.her] [npc2.cock] к [npc.her] [npc.nipples+] и приняться их трахать.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] держал(а) [npc2.her] [npc2.cock] глубоко в [npc.namePos] [npc.nipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] вскоре начал(а) [npc2.sexpaceVerb], выгибая [npc2.her] [npc2.hips] в [npc.namePos] торс, [npc2.moaning] в восторге, пока [npc2.she] трахал(а) [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вырваться из рук [npc.name], и ей оставалось лишь плакать, пока [npc2.she] [npc2.was] заставлял [npc.her] [npc.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает [npc.namePos] [npc.nipples+].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.tail+] глубоко входил в [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] направлял(а) [npc2.namePos] [npc2.tail+] к [npc.her] [npc.nipples+] и заставил(а) [npc2.herHim] начать трахать их хвостом.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] прижать [npc2.her] [npc2.tail] к [npc.her] [npc.nipples+] и приняться трахать их хвостом.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] держал(а) [npc2.her] [npc2.tail] глубоко в [npc.namePos] [npc.nipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Вскоре [npc2.Name] начал(а) [npc2.sexpaceVerb] вгонять [npc2.her] [npc2.tail] в [npc.namePos] [npc.breasts+], [npc2.moaning] в восторге наблюдая, как [npc2.she] трахает [npc.her] [npc.nipples+] хвостом.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вырваться из рук [npc.name], и ей оставалось лишь плакать, пока [npc2.she] [npc2.was] заставлял [npc.her] [npc.nipples+] трахаться хвостом.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает [npc.namePos] [npc.nipples+] хвостом.");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.tentacle+] глубоко входил(а) в [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] направлял(а) [npc2.namePos] [npc2.tentacle+] к [npc.her] [npc.nipples+] и заставил(а) [npc2.herHim] начать щупальцевый секс с ними.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не смог(ла) помешать [npc2.name] прижимать [npc2.her] [npc2.tentacle] к [npc.her] [npc.nipples+] и продолжать щупальцевый секс с ними.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] держал(а) [npc2.her] [npc2.tentacle] глубоко в [npc.namePos] [npc.nipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Вскоре [npc2.Name] начал(а) [npc2.sexpaceVerb] вгонять [npc2.her] [npc2.tentacle] в [npc.namePos] [npc.breasts+], [npc2.moaning] в восторге от того, как [npc2.she] щупальцево трахало(а) [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась), но не смог(ла) отстраниться от [npc.name], и не оставалось ничего другого, кроме как рыдать, пока [npc2.she] [npc2.was] насильно занимался(лась) щупальцевым сексом с [npc.her] [npc.nipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb], занимающийся(аяся) щупальцевым сексом с [npc.namePos] [npc.nipples+].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] сосал(а) [npc.her] [npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] втиснул(а) [npc.her] [npc.breasts+] в [npc2.namePos] [npc2.face], прежде чем заставив(а) [npc2.herHim] начать сосать [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) сопротивляться, но не смог(ла) помешать [npc2.name] прижимать [npc2.her] [npc2.lips] к [npc.her] [npc.breasts+] и начинать сосать [npc.her] [npc.nipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" удерживая [npc2.her] [npc2.lips+] обхваченным(ой) вокруг [npc.namePos] [npc.nipples].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенные [npc2.moans], [npc2.name] прижала [npc2.her] [npc2.lips+] к [npc.namePos] [npc.breasts+] и [npc2.sexPaceVerb] продолжала сосать и целовать [npc.her] [npc.nipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась сопротивляться, но [npc2.was] не смогла помешать [npc.name] прижать [npc.her] [npc.nipples+] к [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] сосет [npc.namePos] [npc.nipples+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	BREAST(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				if(owner.hasBreasts()) {
					return "груди";
				} else {
					return "грудные мышцы";
				}
			}
			return owner.getBreastName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.CHEST;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" трахать [npc.her] [npc.breasts+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" трахать [npc.her] [npc.breasts+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижала [npc.her] [npc.breasts+] к своей собственной [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимающая [npc.her] [npc.breasts+] к своей собственной [npc.her] "+targetArea.getName(performer)+".");
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
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.name] ощупывала и сжимала [npc.her] [npc.breasts+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.breasts+], [npc.name] [npc.sexPaceVerb] заставляла [npc2.name] ощупывать и сжимать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] ощупывать и сжимать [npc.her] [npc.breasts+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")+", и продолжал крепко сжимать [npc.namePos] [npc.breasts+] в [npc2.her] [npc2.hands], не двигаясь.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.a_moan+], [npc2.name] прижал [npc2.her] [npc2.hands] к [npc.namePos] [npc.breasts], и [npc2.sexPaceVerb] продолжал ласкать их.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался, но безуспешно отстраниться от [npc.name], и не мог ничего делать, кроме как плакать, пока [npc2.she] [npc2.was] заставляли играть с [npc.her] [npc.breasts+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] ощупывал и сжимал [npc.namePos] [npc.breasts+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						boolean paizuri = performer.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.cock+] скользил вверх и вниз между [npc.her] [npc.breasts+].");
								} else {
									sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.cock+] скользил вверх и вниз по [npc.her] [npc.breasts+].");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("Прижимая [npc.her] [npc.breasts+] друг к другу, [npc.name] [npc.was] мог скользить [npc2.namePos] [npc2.cock+] вверх и вниз между ними.");
										} else {
											sb.append("Грудь [npc.NamePos] была слишком плоской для пайзури на [npc2.name], но это не помешало [npc.herHim] тереть [npc.her] [npc.breasts+] вверх и вниз по [npc2.her] [npc2.cock+].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("Прижимая [npc.her] [npc.breasts+] друг к другу, [npc2.name] игнорировал протестующие крики [npc.namePos] и заставлял [npc.herHim] делать пайзури на [npc2.herHim].");
										} else {
											sb.append("Не смущаясь тем, что грудь [npc.her] плоская, [npc2.name] игнорировал протестующие крики [npc.namePos] и продолжал тереть [npc.her] [npc.cock+] вверх и вниз по [npc2.her] [npc2.breasts+].");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								if(paizuri) {
									sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] продолжал [npc2.her] [npc2.cock] между [npc.namePos] [npc.breasts+].");
								} else {
									sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] продолжал [npc2.her] [npc2.cock] прижатым к [npc.namePos] [npc.breasts+].");
								}
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append(" Издавая череду [npc2.moans], [npc2.name] с удовольствием принимал пайзури от [npc.name].");
										} else {
											sb.append(" Издавая череду [npc2.moans], [npc2.name] с удовольствием принимал найзури от [npc.name].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append(" Рыдая и всхлипывая, [npc2.name] пытался, но так и не смог сопротивляться пайзури от [npc.name].");
										} else {
											sb.append(" Рыдая и всхлипывая, [npc2.name] пытался, но так и не смог сопротивляться найзури от [npc.name].");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] делает пайзури [npc2.name].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] делает найзури [npc2.name].");
							}
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] целовал и лизал [npc.her] [npc.breasts+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижал [npc.her] [npc.breasts+] к [npc2.namePos] [npc2.face], прежде чем заставить [npc2.herHim] начать целовать и лизать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался сопротивляться, но не смог помешать [npc2.name] прижать [npc2.her] [npc2.lips] к [npc.her] [npc.breasts+] и начать целовать и лизать их.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" при этом прижимая [npc2.her] [npc2.lips+] к [npc.namePos] [npc.breasts].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенные [npc2.moans], [npc2.name] прижала [npc2.her] [npc2.lips+] к [npc.namePos] [npc.breasts+] и продолжила [npc2.sexPaceVerb] целовать их.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался сопротивляться, но [npc2.was] не смог остановить [npc.name], который вжимал [npc.her] [npc.breasts+] в [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] целует [npc.namePos] [npc.breasts+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	NIPPLE_CROTCH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "сосок";
			}
			return owner.getNippleCrotchName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NIPPLES_CROTCH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.STOMACH;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getNippleCrotchStretchedCapacity();
			}
			return owner.getNippleCrotchRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getNippleCrotchMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getNippleCrotchMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.crotchNipples+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.crotchNipples+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижал [npc.her] [npc.crotchNipples+] к собственным [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимает [npc.her] [npc.crotchNipples+] к собственным [npc.her] "+targetArea.getName(performer)+".");
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
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал, пока [npc2.name] щипал и сжимал [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] заставлял(а) [npc2.name] сжимать и тискать [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не мог(ла) помешать [npc2.name] сжимать и тискать [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и продолжал(а) держать [npc.namePos] [npc.crotchNipples+] зажатым(ой) между [npc2.her] [npc2.fingers], не двигаясь.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.a_moan+], [npc2.name] вдавливал(а) [npc2.her] [npc2.hands] в [npc.namePos] [npc.crotchBoobs], а [npc2.sexPaceVerb] играл(а) с [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась), но безуспешно отстраниться от [npc.name], и не мог(ла) ничего не делать, кроме как рыдать, пока [npc2.she] [npc2.was] заставляли играть с [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] сжимает(ет) и тискает(ет) [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.cock+] глубоко входил(а) в [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] направлял(а) [npc2.namePos] [npc2.cock+] к [npc.her] [npc.crotchNipples+] и заставлял(а) [npc2.herHim] начать трахать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не мог(ла) помешать [npc2.name] прижать [npc2.her] [npc2.cock] к [npc.her] [npc.crotchNipples+] и приняться трахать их.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] держал(а) [npc2.her] [npc2.cock] глубоко в [npc.namePos] [npc.crotchNipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] вскоре начала [npc2.sexpaceVerb] выгибаться, [npc2.her] [npc2.hips] в [npc.namePos] пах, [npc2.moaning] в восторге от того, как [npc2.she] трахали [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла отстраниться от [npc.name], и ей оставалось только плакать, пока [npc2.she] [npc2.was] заставляли трахать [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.tail+] глубоко входил в [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] направил(а) [npc2.namePos] [npc2.tail+] к [npc.her] [npc.crotchNipples+], заставляя [npc2.herHim] начать трахать их хвостом.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] прижать [npc2.her] [npc2.tail] к [npc.her] [npc.crotchNipples+] и начать трахать их хвостом.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] держал(а) [npc2.her] [npc2.tail] глубоко в [npc.namePos] [npc.crotchNipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] вскоре начала [npc2.sexpaceVerb] вгонять [npc2.her] [npc2.tail] в [npc.namePos] [npc.crotchBoobs+], [npc2.moaning] в восторге от того, как [npc2.she] трахали [npc.her] [npc.crotchNipples+] хвостом.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла отстраниться от [npc.name], и ей оставалось только плакать, пока [npc2.she] [npc2.was] заставляли трахать [npc.her] [npc.crotchNipples+] хвостом.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает хвостом [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.tentacle+] глубоко вколачивает [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] направил(а) [npc2.namePos] [npc2.tentacle+] к [npc.her] [npc.crotchNipples+] и заставил(а) [npc2.herHim] начать трахать их щупальцами.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] попытался(лась) вырваться, но не смог(ла) помешать [npc2.name] прижимать [npc2.her] [npc2.tentacle] к [npc.her] [npc.crotchNipples+] и продолжать трахать их щупальцами.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] держал(а) [npc2.her] [npc2.tentacle] глубоко в [npc.namePos] [npc.crotchNipples+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name] вскоре начал [npc2.sexpaceVerb] вгонять [npc2.her] [npc2.tentacle] в [npc.namePos] [npc.crotchBoobs+], [npc2.moaning] в восторге от того, как [npc2.she] щупальце-трахал [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] попытался(лась) отстраниться от [npc.name], но безуспешно, и не мог(ла) ничего не делать, кроме как рыдать, пока [npc2.she] [npc2.was] принуждал(а) трахать [npc.her] [npc.crotchNipples+] щупальцами.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает щупальцами [npc.namePos] [npc.crotchNipples+].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] сосал(а) [npc.her] [npc.crotchNipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] втиснул(а) [npc.her] [npc.crotchBoobs+] в [npc2.namePos] [npc2.face], прежде чем заставив [npc2.herHim] начать сосать [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался сопротивляться, но не смог помешать [npc2.name] прижать [npc2.her] [npc2.lips] к [npc.her] [npc.crotchBoobs+] и начать сосать [npc.her] [npc.crotchNipples+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" при этом удерживая [npc2.her] [npc2.lips+] вокруг [npc.namePos] [npc.crotchNipples].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенные [npc2.moans], [npc2.name] прижал [npc2.her] [npc2.lips+] к [npc.namePos] [npc.crotchBoobs+], а [npc2.sexPaceVerb] продолжал сосать и целовать [npc.her] [npc.crotchNipples+].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался сопротивляться, но [npc2.was] не смог помешать [npc.name] прижимать [npc.her] [npc.crotchNipples+] к [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] сосет [npc.namePos] [npc.crotchNipples+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	BREAST_CROTCH(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "сиськи на лобке";
			}
			return owner.getBreastCrotchName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS_CROTCH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.STOMACH;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.crotchBoobs+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.crotchBoobs+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижал [npc.her] [npc.crotchBoobs+] к собственной [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимает [npc.her] [npc.crotchBoobs+] к собственной [npc.her] "+targetArea.getName(performer)+".");
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
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал, пока [npc2.name] ощупывал и сжимал [npc.her] [npc.crotchBoobs+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Выталкивая [npc.her] [npc.crotchBoobs+], [npc.name] [npc.sexPaceVerb] заставлял(а) [npc2.name] ощупывать и сжимать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) вырваться, но не смог(ла) помешать [npc2.name] ощупывать и сжимать [npc.her] [npc.crotchBoobs+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")+", и продолжал(а) удерживать [npc.namePos] [npc.crotchBoobs+] в [npc2.her] [npc2.hands], не двигаясь.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.a_moan+], [npc2.name] прижимал(а) [npc2.her] [npc2.hands] к [npc.namePos] [npc.crotchBoobs], и [npc2.sexPaceVerb] продолжал(а) играть с ними.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(ась), но безуспешно отстраниться от [npc.name], и не мог(ла) ничего, кроме как рыдать, пока [npc2.she] [npc2.was] заставлял(а) играть с [npc.her] [npc.crotchBoobs+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] ощупывает(ет) и сжимает(ет) [npc.namePos] [npc.crotchBoobs+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						boolean paizuri = performer.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.cock+] скользит(ят) вверх и вниз между [npc.her] [npc.crotchBoobs+].");
								} else {
									sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.cock+] скользит(ят) вверх и вниз по [npc.her] [npc.crotchBoobs+].");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("Сжимая [npc.her] [npc.crotchBoobs+] вместе, [npc.name] [npc.was] может(ет) скользить [npc2.namePos] [npc2.cock+] вверх и вниз между ними.");
										} else {
											sb.append("[npc.NamePos] [npc.crotchBoobs] были слишком плоскими для кунилингуса грудью на [npc2.name], но это не помешало [npc.herHim] тереть [npc.her] [npc.crotchBoobs+] вверх и вниз о [npc2.her] [npc2.cock+].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("Сжимая [npc.her] [npc.crotchBoobs+] вместе, [npc2.name] игнорировала протестующие крики [npc.namePos] и заставляла [npc.herHim] делать кротч-пайзури на [npc2.herHim].");
										} else {
											sb.append("Не смущаясь того, что [npc.her] [npc.crotchBoobs] плоские, [npc2.name] игнорировала протестующие крики [npc.namePos] и, уставившись, терла [npc.her] [npc.cock+] вверх и вниз по [npc2.her] [npc2.crotchBoobs+].");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								if(paizuri) {
									sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] продолжала [npc2.her] [npc2.cock] между [npc.namePos] [npc.crotchBoobs+].");
								} else {
									sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] продолжала [npc2.her] [npc2.cock] прижатыми к [npc.namePos] [npc.crotchBoobs+].");
								}
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием принимала кротч-пайзури от [npc.name].");
										} else {
											sb.append(" Издавая серию [npc2.moans], [npc2.name] с удовольствием принимала кротч-найзури от [npc.name].");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append(" Рыдая и плача, [npc2.name] пыталась, но не смогла сопротивляться кротч-пайзури от [npc.name].");
										} else {
											sb.append(" Рыдая и плача, [npc2.name] пыталась, но не смогла сопротивляться кротч-найзури от [npc.name].");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] делает кротч-пайзури на [npc2.name].");
							} else {
								sb.append("[npc.NameIs] [npc.sexPaceVerb] делает кротч-найзури на [npc2.name].");
							}
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] целовал и лизал [npc.her], [npc.crotchBoobs+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] прижал(а) [npc.her] [npc.crotchBoobs+] к [npc2.namePos] [npc2.face], прежде чем заставить [npc2.herHim] начать целовать и облизывать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) сопротивляться, но не смог(ла) помешать [npc2.name] прижимать [npc2.her] [npc2.lips] к [npc.her] [npc.crotchBoobs+] и начать их целовать и облизывать.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" удерживая [npc2.her] [npc2.lips+] прижатым(ой) к [npc.namePos] [npc.crotchBoobs].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенные [npc2.moans], [npc2.name] прижал(а) [npc2.her] [npc2.lips+] к [npc.namePos] [npc.crotchBoobs+] и продолжил(а) [npc2.sexPaceVerb] целовать их.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась) сопротивляться, но [npc2.was] не смог(ла) остановить [npc.name], который(ая) [npc.her] [npc.crotchBoobs+] к [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] целует(ет) [npc.namePos] [npc.crotchBoobs+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	
	
	THIGHS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "бедра";
			}
			return "бедра";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.THIGHS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" ласкать бедра [npc.her].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" ласкать бедра [npc.her].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] [npc.her] прижимает свои бедра к бедрам [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимает [npc.her] бедра к [npc.her] собственным "+targetArea.getName(performer)+".");
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
								sb.append((performer.isAsleep()?"Все еще крепко спит":"Ведя себя как безжизненная секс-игрушка")
										+", [npc.name] держал(а) [npc.her] [npc.legs] плотно прижатыми, пока [npc2.namePos] [npc.her] [npc2.cock+] входил(а) и выходил(а) из образовавшейся щели.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Сжимая [npc.her] [npc.legs] вместе, [npc.name] заставил(а) [npc2.name] водить [npc2.her] [npc2.cock+] туда-сюда в образовавшейся щели.");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пытался(лась) сопротивляться, [npc.name] сжал(а) [npc.her] [npc.legs] вместе, после чего его/ее начали трахать бедрами [npc2.namePos] [npc2.cock+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] продолжал(а) [npc2.her] [npc2.cock] вбивать между бедрами [npc.namePos].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.a_moan+], [npc2.name] [npc2.sexPaceVerb] втиснул(а) [npc2.her] [npc2.cock+] в ложбинку между бедрами [npc.namePos].");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает [npc.name] бедрами.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	ARMPITS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "подмышки";
			}
			return "подмышки";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ARMPITS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.TORSO_UNDER;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" ласкать [npc.her] [npc.armpits+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" ласкать [npc.her] [npc.armpits+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижимает [npc.her] [npc.armpits+] к своим [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимает [npc.her] [npc.armpits+] к своим [npc.her] "+targetArea.getName(performer)+".");
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
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Ведя себя как безжизненная секс-игрушка, [npc.name] оставался(ась) совершенно неподвижным(ой),")
											+" пока [npc2.namePos] [npc2.cock+] скользит вверх и вниз по [npc.her] [npc.armpit+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Подняв [npc.her] [npc.arm(true)], [npc.name] заставила [npc2.name] водить [npc2.her] [npc2.cock+] вверх и вниз по [npc.her] [npc.armpit+].");
										break;
									case SUB_RESISTING:
										sb.append("Хотя [npc.she] пытался сопротивляться, [npc.name] заставила [npc.her] [npc.arm(true)] подняться, а затем [npc2.namePos] [npc2.cock+] терлась вверх и вниз по [npc.her] [npc.armpit+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"даже не подавала признаков пробуждения":"оставалась совершенно неподвижной")+" пока [npc2.she] продолжал прижимать [npc2.her] [npc2.cock] к своей [npc.namePos] подмышке.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая [npc2.a_moan+], [npc2.name] [npc2.sexPaceVerb] с силой [npc2.her] [npc2.cock+] в [npc.namePos] подмышку.");
										break;
									case SUB_RESISTING:
										sb.append(" Sobbing and crying, [npc2.name] did [npc2.her] best to pull away from [npc.name], but ended up being held in place and having [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает [npc.namePos] в подмышку.");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] целовала и лизала [npc.her] [npc.armpits+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] [npc.sexPaceVerb] втиснул(а) [npc.her] [npc.armpits+] в [npc2.namePos] [npc2.face], прежде чем заставить [npc2.herHim] начать целовать и лизать их.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но не смогла помешать [npc2.name] прижимать [npc2.her] [npc2.lips] к [npc.her] [npc.armpits+] и начинать их целовать и лизать.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" при этом удерживая [npc2.her] [npc2.lips+] прижатой к [npc.namePos] [npc.armpit].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая приглушенный [npc2.moans], [npc2.name] прижал(а) [npc2.her] [npc2.lips+] к [npc.namePos] [npc.armpits+] и продолжил(а) [npc2.sexPaceVerb] целовать их.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась сопротивляться, но [npc2.was] не смог(ла) остановить [npc.name], который(ая) [npc.her] [npc.armpits+] прижимал(а) к [npc2.her] [npc2.lips+].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] целует(а) [npc.namePos] [npc.armpits+].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	URETHRA_VAGINA(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "женская уретра";
			}
			return "уретра";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
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
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getVaginaUrethraStretchedCapacity();
			}
			return owner.getVaginaUrethraRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getVaginaUrethraMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getVaginaUrethraMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" трахать [npc.her] [npc.vaginaUrethra+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" трахать [npc.her] [npc.vaginaUrethra+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижал(а) [npc.her] [npc.vaginaUrethra+] к своим [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимающий(ая) [npc.her] [npc.vaginaUrethra+] к своим [npc.her] "+targetArea.getName(performer)+".");
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
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировал(а), пока [npc2.namePos] [npc2.cock+] глубоко входил(а) в [npc.her] [npc.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Потирая [npc.her] [npc.labia+] о [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] [npc.sexPaceVerb] выгибался(ась) [npc.her] [npc.hips] и заставлял(а) [npc2.herHim] проникать в [npc.her] [npc.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(ась) вырваться, но не смог(ла) помешать [npc2.name] втиснуть [npc2.her] [npc2.cock] в [npc.vaginaUrethra+] влагалища [npc.her] и начать трахать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался(ась) совершенно неподвижным(ой), удерживая [npc2.her] [npc2.cock] глубоко в [npc.namePos] [npc.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] с силой вгонял(а) [npc2.her] [npc2.cock+] в [npc.namePos] [npc.vaginaUrethra+], и вскоре [npc2.name] [npc2.was] начал(а) [npc2.moaning] от удовольствия, пока [npc2.she] трахал(а) [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.cock] из [npc.namePos] [npc.vaginaUrethra], и ей оставалось только рыдать, пока [npc2.she] использовал [npc2.her] [npc2.cock] по своему усмотрению против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] чертовски [npc.namePos] [npc.vaginaUrethra].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] [npc2.tail+] глубоко входил в [npc.her] [npc.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tail+], [npc.name] потер ее вверх и вниз по [npc.her] [npc.labia+], прежде чем заставить [npc2.herHim] проникнуть внутрь и начать хвостом трахать [npc.her] [npc.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] втиснуть [npc2.her] [npc2.tail+] в [npc.vaginaUrethra+] киски [npc.her] и начать трахать [npc.herHim] хвостом.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, пока [npc2.her] [npc2.tail] был по самую рукоять в [npc.namePos] [npc.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вонзает [npc2.her] [npc2.tail] в [npc.namePos] [npc.vaginaUrethra], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] трахает [npc.name] хвостом.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.tail] из [npc.namePos] [npc.vaginaUrethra], и ей оставалось только рыдать, пока [npc2.she] использовал это против [npc2.her] по своей воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает [npc.namePos] [npc.vaginaUrethra] хвостом.");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] совсем не реагировала, пока [npc2.namePos] [npc2.tentacle+] глубоко [npc.her] [npc.vaginaUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] потер ее вверх и вниз по [npc.her] [npc.labia+], прежде чем заставить [npc2.herHim] проникнуть и начать щупальцевый секс с [npc.her] [npc.vaginaUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] втискивать [npc2.her] [npc2.tentacle+] в [npc.vaginaUrethra+] киски [npc.her] и начинать щупальцевый секс с [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, удерживая [npc2.her] [npc2.tentacle] глубоко в [npc.namePos] [npc.vaginaUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вонзает [npc2.her] [npc2.tentacle] в [npc.namePos] [npc.vaginaUrethra], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] трахает [npc.name] щупальцем.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.tentacle] из [npc.namePos] [npc.vaginaUrethra], и ей оставалось только рыдать, пока [npc2.she] использовал это по своей воле против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb], совершающий щупальцевый секс с [npc.namePos] [npc.vaginaUrethra].");
						}
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	URETHRA_PENIS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "мужская уретра";
			}
			return "уретра";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this) && Main.sex.isPenetrationTypeFree(owner, SexAreaPenetration.PENIS);
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
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getPenisStretchedCapacity();
			}
			return owner.getPenisRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getUrethraMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getUrethraMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.penisUrethra+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.penisUrethra+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижал(а) [npc.her] [npc.penisUrethra+] к собственной [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимает [npc.her] [npc.penisUrethra+] к [npc.her] "+targetArea.getName(performer)+".");
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
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагирует, пока [npc2.namePos] [npc2.cock+] глубоко [npc.her] [npc.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Потирая [npc2.cockHead+] [npc2.namePos] [npc2.cock] о кончик [npc.her], [npc.name] [npc.sexPaceVerb] выгнулся [npc.her] [npc.hips] вперед, заставляя [npc2.herHim] проникнуть в [npc.her] [npc.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] попытался вырваться, но не смог помешать [npc2.name] втиснуть [npc2.her] [npc2.cock] в [npc.penisUrethra+] [npc.her] члена и начать трахать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался совершенно неподвижным, удерживая [npc2.her] [npc2.cock] глубоко в [npc.namePos] [npc.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вгоняет [npc2.her] [npc2.cock+] в [npc.namePos] [npc.penisUrethra+], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от удовольствия, пока [npc2.she] трахает [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался, но тщетно вытянуть [npc2.her] [npc2.cock] из [npc.namePos] [npc.penisUrethra], и не мог ничего сделать, кроме как рыдать, пока [npc2.she] [npc2.her] [npc2.cock] использовали вопреки [npc2.her] воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает [npc.namePos] [npc.penisUrethra].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагирует, пока [npc2.namePos] [npc2.tail+] глубоко [npc.her] [npc.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tail+], [npc.name] потер ее вверх и вниз по [npc.cockHead+] [npc.her] [npc.cock], прежде чем заставить [npc2.herHim] войти внутрь и начать ритмично трахать [npc.her] [npc.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась вырваться, но не смогла помешать [npc2.name] впихивать [npc2.her] [npc2.tail+] в [npc.her] cock's [npc.penisUrethra+] и начать ритмично трахать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, удерживая [npc2.her] [npc2.tail] глубоко в [npc.namePos] [npc.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] с силой вгонял [npc2.her] [npc2.tail] в [npc.namePos] [npc.penisUrethra], и вскоре [npc2.name] [npc2.was] начал [npc2.moaning] от удовольствия, пока [npc2.she] ритмично трахал [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.tail] из [npc.namePos] [npc.penisUrethra], и ей оставалось только рыдать, пока [npc2.she] использовал ее по своему усмотрению против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] ритмично трахает [npc.namePos] [npc.penisUrethra].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] [npc2.tentacle+] глубоко входил в [npc.her] [npc.penisUrethra+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] потер ее вверх и вниз по [npc.cockHead+] [npc.her] [npc.cock], прежде чем заставить [npc2.herHim] войти внутрь и начать щупальцевое трахание [npc.her] [npc.penisUrethra+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] попыталась вырваться, но не смогла помешать [npc2.name] втискивать [npc2.her] [npc2.tentacle+] в [npc.penisUrethra+] члена [npc.her] и начинать щупальцевый трах [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, пока [npc2.her] [npc2.tentacle] был зажат в [npc.namePos] [npc.penisUrethra+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] с силой вгоняла [npc2.her] [npc2.tentacle] в [npc.namePos] [npc.penisUrethra], и [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] трахала [npc.name] щупальцами.");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но безуспешно вытащить [npc2.her] [npc2.tentacle] из [npc.namePos] [npc.penisUrethra], и не могла ничего делать, кроме как рыдать, когда [npc2.she] использовал это по своей воле против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] трахает щупальцем [npc.namePos] [npc.penisUrethra].");
						}
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	SPINNERET(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 4/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			return "паутинная железа";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			if(owner!=null && owner.hasTailSpinneret()) {
				return CoverableArea.TAIL;
			}
			return CoverableArea.ASS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			if(owner!=null && owner.hasTailSpinneret()) {
				return InventorySlot.TAIL;
			}
			return InventorySlot.ANUS;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getSpinneretStretchedCapacity();
			}
			return owner.getSpinneretRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getSpinneretMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getSpinneretMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] использовал(а) [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.spinneret+].");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb], используя [npc.her] собственный(ую) "+targetArea.getName(performer)+" чтобы трахать [npc.her] [npc.spinneret+].");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name] [npc.sexPaceVerb] прижимала [npc.her] [npc.spinneret+] к собственной [npc.her] "+targetArea.getName(performer)+".");
					} else {
						sb.append("[npc.NameIs] [npc.sexPaceVerb] прижимающая [npc.her] [npc.spinneret+] к собственной [npc.her] "+targetArea.getName(performer)+".");
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
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировала, пока [npc2.namePos] вталкивал [npc2.her] [npc2.fingers+] глубоко в паутину [npc.her].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.hand+], [npc.name] с нажимом водил(а) [npc2.her] [npc2.fingers+] вверх и вниз по [npc.her] паучьему веретену, прежде чем [npc2.herHim] заставил(а) их скользнуть внутрь и приняться пальцевать [npc.her] плетущее паутину отверстие.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался(лась) вырваться, но не смог(ла) помешать [npc2.name] втискивать [npc2.her] [npc2.fingers+] в [npc.her] веретено и начинать пальцевать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался(лась) совершенно неподвижно, пока [npc2.her] [npc2.fingers] глубоко засунуты в [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] сворачивает(ет) [npc2.her] [npc2.fingers] внутри [npc.namePos] веретена, и вскоре [npc2.name] [npc2.was] [npc2.moaning] от удовольствия, пока [npc2.she] пальцует(ет) [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался(лась), но безуспешно вытащить [npc2.her] [npc2.fingers] из [npc.namePos] веретена, и не мог(ла) ничего не делать, кроме как рыдать, когда [npc2.she] заставил(а) пальцевать [npc.herHim] вопреки [npc2.her] воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] пальцует(ет) [npc.namePos] веретено.");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал(а), пока [npc2.namePos] [npc2.cock+] глубоко входил(а) в [npc.her] [npc.spinneret+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Водя [npc.her] веретеном по [npc2.cockHead+] [npc2.namePos] [npc2.cock], [npc.name] [npc.sexPaceVerb] выгибался(лась) под [npc.her] [npc.hips], заставляя [npc2.herHim] проникать в [npc.her] плетущее паутину отверстие.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался вырваться, но не смог помешать [npc2.name] впихнуть [npc2.her] [npc2.cock] в [npc.her] паучье веретено и начать трахать [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался совершенно неподвижным, пока [npc2.her] [npc2.cock] торчал в [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вонзает [npc2.her] [npc2.cock+] в [npc.namePos] паучье веретено, и вскоре [npc2.name] [npc2.was] начинает [npc2.moaning] от удовольствия, пока [npc2.she] трахает [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пытался, но не смог вытащить [npc2.her] [npc2.cock] из [npc.namePos] паучьего веретена, и ему оставалось только рыдать, пока [npc2.she] использовал [npc2.her] [npc2.cock] по своей воле против [npc2.her].");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb], которую [npc2.name] трахает.");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не реагировал, пока [npc2.namePos] [npc2.tail+] глубоко входил в [npc.her] [npc.spinneret+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tail+], [npc.name] потер его вверх и вниз по [npc.her] паучьему веретену, прежде чем заставить [npc2.herHim] проникнуть и начать трахать хвостом [npc.her] паучье веретено.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пытался вырваться, но не смог помешать [npc2.name] впихнуть [npc2.her] [npc2.tail+] в [npc.her] паучье веретено и начать трахать хвостом [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставался совершенно неподвижным, пока [npc2.her] [npc2.tail] торчал в [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вонзает [npc2.her] [npc2.tail] в [npc.namePos] паучье веретено, и вскоре [npc2.name] [npc2.was] начинает [npc2.moaning] от удовольствия, пока [npc2.she] трахает хвостом [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.tail] из [npc.namePos] паутины, и ей оставалось лишь рыдать, пока [npc2.she] использовал это вопреки [npc2.her] воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs], чьи [npc.sexPaceVerb] [npc.her] подверглись щупальцевому траху в паутину через [npc2.name].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Остальное "+(performer.isAsleep()?"спящий":"неподвижно")+", [npc.name] никак не отреагировала, пока [npc2.namePos] [npc2.tentacle+] глубоко входила в [npc.her] [npc.spinneret+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Схватив [npc2.namePos] [npc2.tentacle+], [npc.name] потер ее вверх и вниз по [npc.her] паутине, прежде чем заставить [npc2.herHim] проникнуть и начать щупальцевый трах в [npc.her] отверстие для плетения паутины.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] попытался вырваться, но не смог помешать [npc2.name] втиснуть [npc2.her] [npc2.tentacle+] в [npc.her] паучье веретено и начать щупальцевый секс с [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"продолжала вести себя как неодушевленная секс-игрушка")
										+", и оставалась совершенно неподвижной, удерживая [npc2.her] [npc2.tentacle] глубоко в [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] вгоняла [npc2.her] [npc2.tentacle] в [npc.namePos] паутину, и [npc2.name] [npc2.was] вскоре [npc2.moaning] от восторга, пока [npc2.she] трахала щупальцами [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась, но не смогла вытащить [npc2.her] [npc2.tentacle] из [npc.namePos] паутины, и ей оставалось лишь рыдать, пока [npc2.she] использовал это вопреки [npc2.her] воле.");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs], чьи [npc.sexPaceVerb] [npc.her] подверглись щупальцевому траху в паутину через [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Все еще крепко спящий(ая), [npc.name] не подавал(а) никаких признаков того, что вот-вот проснется":"Продолжая вести себя как неодушевленная секс-кукла, [npc.name] сохранял(а) полную неподвижность, ")
											+" пока [npc2.name] занималась оральным сексом с [npc.her] отверстием для плетения паутины.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] прижала [npc.her] паутинный орган к [npc2.namePos] [npc2.face], после чего [npc.sexPaceVerb] заставила [npc2.herHim] начать отсасывать [npc.her] паутинный орган.");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] пыталась сопротивляться, но [npc.was] не смогла помешать [npc2.name] прижать [npc2.her] [npc2.face] к [npc.her] паутинному органу, а затем заставить [npc.her] начать отсасывать этот паутинный орган.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"продолжал(а) крепко спать":"совершенно не шевелилась")+" при этом удерживая [npc2.her] [npc2.tongue], вонзенный в [npc.namePos] [npc.spinneret+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Издавая серию [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] прижала [npc2.her] [npc2.lips+] к [npc.namePos] паутинному органу и продолжила отсасывать [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] пыталась сопротивляться, но [npc2.was] не смогла помешать [npc.name] приставить [npc.her] паутинный орган к [npc2.her] [npc2.face], заставляя [npc.sexPaceVerb] совершать оральный секс с [npc2.herHim] через [npc.herHim].");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] отсасывает [npc.namePos] паутинный орган.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	};

	private float baseArousalWhenPenetrated;
	private float arousalChangePenetratedStretching;
	private float arousalChangePenetratedTooLoose;
	private float arousalChangePenetratedDry;
	private float arousalChangePenetratingStretching;
	private float arousalChangePenetratingTooLoose;
	private float arousalChangePenetratingDry;
	private float cumLossPerSecond;
	private float cumAbsorptionPerSecond;
	private boolean takesPenisVirginity;

	/**
	 * @param baseArousalWhenPenetrated
	 * @param arousalChangePenetratedStretching
	 * @param arousalChangePenetratedTooLoose
	 * @param arousalChangePenetratedDry
	 * @param arousalChangePenetratingStretching
	 * @param arousalChangePenetratingTooLoose
	 * @param arousalChangePenetratingDry
	 * @param cumLossPerSecond The amount of cum or other fluids that leak out of this orifice every second.
	 * @param cumAbsorptionPerSecond The amount of cum or other fluids that are absorbed into the character's body through this orifice every second.
	 * @param takesPenisVirginity
	 */
	private SexAreaOrifice(float baseArousalWhenPenetrated,
			float arousalChangePenetratedStretching,
			float arousalChangePenetratedTooLoose,
			float arousalChangePenetratedDry,
			float arousalChangePenetratingStretching,
			float arousalChangePenetratingTooLoose,
			float arousalChangePenetratingDry,
			float cumLossPerSecond,
			float cumAbsorptionPerSecond,
			boolean takesPenisVirginity) {
		this.baseArousalWhenPenetrated = baseArousalWhenPenetrated;
		this.arousalChangePenetratedStretching = arousalChangePenetratedStretching;
		this.arousalChangePenetratedTooLoose = arousalChangePenetratedTooLoose;
		this.arousalChangePenetratedDry = arousalChangePenetratedDry;
		this.arousalChangePenetratingStretching = arousalChangePenetratingStretching;
		this.arousalChangePenetratingTooLoose = arousalChangePenetratingTooLoose;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.cumLossPerSecond = cumLossPerSecond;
		this.cumAbsorptionPerSecond = cumAbsorptionPerSecond;
		this.takesPenisVirginity = takesPenisVirginity;
	}

	@Override
	public boolean isOrifice() {
		return true;
	}
	
	public float getBaseArousalWhenPenetrated() {
		return baseArousalWhenPenetrated;
	}
	
	public float getArousalChangePenetratedStretching() {
		return arousalChangePenetratedStretching;
	}

	public float getArousalChangePenetratedTooLoose() {
		return arousalChangePenetratedTooLoose;
	}

	public float getArousalChangePenetratedDry() {
		return arousalChangePenetratedDry;
	}

	public float getArousalChangePenetratingStretching() {
		return arousalChangePenetratingStretching;
	}

	public float getArousalChangePenetratingTooLoose() {
		return arousalChangePenetratingTooLoose;
	}

	public float getArousalChangePenetratingDry() {
		return arousalChangePenetratingDry;
	}

	public float getCumLossPerSecond() {
		return cumLossPerSecond;
	}
	
	public float getCumAbsorptionPerSecond() {
		return cumAbsorptionPerSecond;
	}
	
	/**
	 * @return true If this orifice is a fully internal orifice, capable of taking penile virginity.<br/>
	 * Mouth, vagina, anus, urethras, and nipple are considered internal orifices.<br/>
	 * Ass, breasts, and thighs are not.
	 */
	public boolean isInternalOrifice() {
		return takesPenisVirginity;
	}
	
	public abstract float getCapacity(GameCharacter owner, boolean currentlyStretchedValue);
	
	public float getCharactersCumLossPerSecond(GameCharacter target) {
		if(target.hasCreampieRetentionArea(this)) {
			return 0;
		}
		
		float cumLost = this.getCumAbsorptionPerSecond();
		float fluidInArea = target.getTotalFluidInArea(this);
		// The rate obviously decreases as the fluid drains out, but assuming if the drain was applied all at once, it would take about 5.5 hours to all drain out (not factoring in absorption or natural loss):
		float secondPercentageLoss = fluidInArea/20_000;
		
		if(!target.isOrificePlugged(this)) {
			cumLost += this.getCumLossPerSecond() + secondPercentageLoss;
		}
		return cumLost;
	}
	
	public abstract int getMaximumPenetrationDepthComfortable(GameCharacter target);
	
	public abstract int getMaximumPenetrationDepthUncomfortable(GameCharacter target);
}
