package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.65
 * @version 0.4
 * @author Innoxia
 */
public enum PregnancyDescriptor {
	
	ALREADY_PREGNANT {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(characterBeingImpregnated.isPlayer() && cumInPussy){
				if(characterProvidingCum==null) {
					sb.append("Ты чувствуешь, как сперма ");
					if(isSlime) {
						sb.append("разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
					} else {
						sb.append("глубоко внутри твоей [pc.pussy+]");
					}
					sb.append(", но так как [style.boldSex(ты уже беременна, тебе не о чем беспокоиться)]!");
					
				} else {
					sb.append("Ты чувствуешь, ");
					if(selfcest) {
						sb.append("как твоя собственная [npc.cum+]");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
					if(isSlime) {
						sb.append(" разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
					} else {
						sb.append(" глубоко внутри твоей [pc.pussy+]");
					}
					sb.append(", но так как [style.boldSex(ты уже беременна, тебе не о чем беспокоиться)]!");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("[npc.NameIsFull] уже беременна, [style.boldSex(так что нет шансов, что ");
					if(selfcest) {
						sb.append("[npc.sheHasFull] обрюхатил [npc.herself]");
					} else {
						sb.append("[npc.sheHasFull] забеременела");
					}
					sb.append(")]!");
					
				} else {
					sb.append("[npc.NameIsFull] уже беременна, [style.boldSex(так что нет шансов, что ");
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("[npc.sheHasFull] обрюхатил [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] обрюхатил [npc.herHim]");
						}
					} else {
						if(selfcest) {
							sb.append("[npc.sheHasFull] оплодотворил [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] оплодотворил [npc.herHim]");
						}
					}
					sb.append(")]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},

	ALREADY_PREGNANT_EGGS {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(characterBeingImpregnated.isPlayer() && cumInPussy){
				if(characterProvidingCum==null) {
					sb.append("Ты чувствуешь, как сперма ");
					if(isSlime) {
						sb.append("разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
					} else {
						sb.append("глубоко внутри твоей [pc.pussy+]");
					}
					sb.append(", но так как [style.boldSex(твоя утроба уже полна яйцеклеток, тебе не о чем беспокоиться)]!");
					
				} else {
					sb.append("Ты чувствуешь, ");
					if(selfcest) {
						sb.append("как твоя собственная [npc.cum+]");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
					if(isSlime) {
						sb.append(" разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
					} else {
						sb.append(" глубоко внутри твоей [pc.pussy+]");
					}
					sb.append(", но так как [style.boldSex(твоя утроба уже полна яйцеклеток, тебе не о чем беспокоиться)]!");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("Утроба [npc.NamePos] уже полна яйцеклеток, [style.boldSex(так что нет шансов, что ");
					if(selfcest) {
						sb.append("[npc.sheHasFull] обрюхатил [npc.herself]");
					} else {
						sb.append("[npc.sheHasFull] забеременела");
					}
					sb.append(")]!");
					
				} else {
					sb.append("Утроба [npc.NamePos] уже полна яйцеклеток, [style.boldSex(так что нет шансов, что ");
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("[npc.sheHasFull] обрюхатил [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] обрюхатил [npc.herHim]");
						}
					} else {
						if(selfcest) {
							sb.append("[npc.sheHasFull] оплодотворил [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] оплодотворил [npc.herHim]");
						}
					}
					sb.append(")]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	NO_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Хотя утроба [npc.namePos] свободна, [style.boldSex(нет никаких шансов, что ");
				if(characterProvidingCum==null) {
					if(selfcest) {
						sb.append("[npc.sheHasFull] обрюхатил [npc.herself]");
					} else {
						sb.append("[npc.sheHasFull] забеременела");
					}
					sb.append(")]!");
					
				} else {
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("[npc.sheHasFull] обрюхатил [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] обрюхатил [npc.herHim]");
						}
					} else {
						if(selfcest) {
							sb.append("[npc.sheHasFull] оплодотворил [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] оплодотворил [npc.herHim]");
						}
					}
					sb.append(")]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				if(characterProvidingCum==null) {
					sb.append("несмотря на ощущение спермы ");
					if(isSlime) {
						sb.append("разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
					} else {
						sb.append("глубоко в твоей утробе");
					}
					sb.append(", ты чувствуешь, что [style.boldSex(от этого ты не забеременеешь)].");
					
				} else {
					sb.append("Несмотря на ощущение ");
					if(selfcest) {
						sb.append("как твоя собственная [npc.cum+]");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
					if(isSlime) {
						sb.append(" разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
					} else {
						sb.append(" глубоко в твоей утробе");
					}
					sb.append(", ты чувствуешь, что [style.boldSex(от этого ты не забеременеешь)].");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("Несмотря на то, что тело [npc.her], ");
					if(isSlime) {
						sb.append("скользкое и");
					} else {
						sb.append("матку");
					}
					if(selfcest) {
						sb.append(" наполненное собственным семенем [npc.her], [style.boldSex(нет никаких шансов, что [npc.nameHasFull] оплодотворил(а) [npc.herself])].");
					} else {
						sb.append(" наполненная спермой, [style.boldSex(шансов на то, что [npc.nameHasFull] забеременела, нет)].");
					}
					
				} else {
					sb.append("Несмотря на то, что тело [npc.her], ");
					if(isSlime) {
						sb.append("скользкое и");
					} else {
						sb.append("матку");
					}
					if(selfcest) {
						sb.append(" наполненное собственным семенем [npc.her], [style.boldSex(нет никаких шансов, что [npc.nameHasFull] оплодотворил(а) [npc.herself])].");
					} else {
						sb.append(" наполненная [npc2.namePos] [npc2.cum+], [style.boldSex(шансов на то, что [npc.nameHasFull] забеременела, нет)].");
					}
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	LOW_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Издавая судорожный вздох,");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(если [npc.she] [npc.is] нет уже беременной, есть небольшой шанс, что [npc.she] [npc.is] прямо сейчас)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(есть небольшой шанс, что [npc.she] забеременеет от этого)]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("Ты чувствуешь, ");
				if(selfcest) {
					sb.append("как твоя собственная [npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("сперма");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append(" разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
				} else {
					sb.append(" глубоко в твоей утробе");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(", и ты понимаешь, что [style.boldSex(если ты еще не беременна, то теперь есть небольшой шанс, что ты забеременела)]!");
				} else {
					sb.append(", и ты понимаешь, что [style.boldSex(есть небольшой шанс, что ты забеременеешь)]!");
				}
				
			} else {
				sb.append("После того как [npc.her] ");
				if(isSlime) {
					sb.append("скользкое и");
				} else {
					sb.append("матку");
				}
				if(selfcest) {
					sb.append(" наполненная [npc.her] собственным семенем,");
				} else {
					if(characterProvidingCum==null) {
						sb.append(" наполненная спермой,");
					} else {
						sb.append(" наполнен(а) [npc2.namePos] [npc2.cum+],");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)], [style.boldSex(если [npc.she] еще не беременна, то теперь есть небольшой шанс, что [npc.she] забеременела)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)], при этом [style.boldSex(есть небольшой шанс, что [npc.she] забеременеет)]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	AVERAGE_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Издавая судорожный вздох,");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(если [npc.she] [npc.is] нет уже беременной, есть шанс, что [npc.she] [npc.is] прямо сейчас)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)], при этом [style.boldSex(есть шанс, что от этого [npc.she] забеременеет)]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("Ты чувствуешь, ");
				if(selfcest) {
					sb.append("как твоя собственная [npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("сперма");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append(" разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
				} else {
					sb.append(" глубоко в твоей утробе");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(", и ты понимаешь, что [style.boldSex(если ты еще не беременна, есть шанс, что ты только что забеременела)]!");
				} else {
					sb.append(", и ты понимаешь, что [style.boldSex(есть шанс, что ты забеременеешь)]!");
				}
				
			} else {
				sb.append("После того как [npc.her] ");
				if(isSlime) {
					sb.append("скользкое и");
				} else {
					sb.append("матку");
				}
				if(selfcest) {
					sb.append(" наполненная [npc.her] собственным семенем,");
				} else {
					if(characterProvidingCum==null) {
						sb.append(" наполненная спермой,");
					} else {
						sb.append(" наполнен(а) [npc2.namePos] [npc2.cum+],");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(если [npc.she] еще не беременна, то теперь [npc.she] может оказаться в положении)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)], при этом [style.boldSex(есть шанс, что [npc.she] забеременеет)]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	HIGH_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Издавая судорожный вздох,");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(если [npc.she] [npc.is]n't уже не беременна, то есть большой шанс, что [npc.she] [npc.is] прямо сейчас)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(есть большая вероятность, что от этого [npc.she] забеременеет)]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("Ты чувствуешь, ");
				if(selfcest) {
					sb.append("как твоя собственная [npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("сперма");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append(" разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
				} else {
					sb.append(" глубоко в твоей утробе");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(", и ты понимаешь, что [style.boldSex(если ты еще не беременна, то сейчас ты наверняка забеременела)]!");
				} else {
					sb.append(", и ты понимаешь, что [style.boldSex(есть большая вероятность, что ты забеременеешь)]!");
				}
				
			} else {
				sb.append("После того как [npc.her] ");
				if(isSlime) {
					sb.append("скользкое и");
				} else {
					sb.append("матку");
				}
				if(selfcest) {
					sb.append(" наполненная [npc.her] собственным семенем,");
				} else {
					if(characterProvidingCum==null) {
						sb.append(" наполненная спермой,");
					} else {
						sb.append(" наполнен(а) [npc2.namePos] [npc2.cum+],");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(если [npc.she] еще не беременна, то теперь [npc.she], скорее всего, зачинает)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(есть большая вероятность, что [npc.she] забеременеет)]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	CERTAINTY {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Издавая судорожный вздох,");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)], если [style.boldSex([npc.she] [npc.is] еще не беременна, то [npc.she] теперь точно [npc.is])]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(нет сомнений, что [npc.sheHas] была оплодотворена этим)]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("Ты чувствуешь, ");
				if(selfcest) {
					sb.append("как твоя собственная [npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("сперма");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append(" разливается по твоему склизкому телу, стремясь оплодотворить твою суть");
				} else {
					sb.append(" глубоко в твоей утробе");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(", и ты понимаешь, что [style.boldSex(если ты еще не беременна, то теперь ты точно беременна)]!");
				} else {
					sb.append(", и ты понимаешь, что [style.boldSex(нет сомнений, что ты была оплодотворена)]!");
				}
				
			} else {
				sb.append("После того как [npc.her] ");
				if(isSlime) {
					sb.append("скользкое и");
				} else {
					sb.append("матку");
				}
				if(selfcest) {
					sb.append(" наполненная [npc.her] собственным семенем,");
				} else {
					if(characterProvidingCum==null) {
						sb.append(" наполненная спермой,");
					} else {
						sb.append(" наполнен(а) [npc2.namePos] [npc2.cum+],");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(если [npc.she] еще не была беременна, то теперь [npc.she] точно забеременела)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)], что [style.boldSex(нет сомнений: [npc.sheHas] была оплодотворена)]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	};
	
	private static boolean isCumInPussy(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
		return characterBeingImpregnated.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f -> {
			try {
				return f.isCum() && f.getFluidCharacter()==characterProvidingCum;
			} catch (Exception e) {
				return false;
			}
		});
	}
	
	public abstract String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination);
	
	public static PregnancyDescriptor getPregnancyDescriptorBasedOnProbability(float probability) {
		if (probability <= 0) {
			return PregnancyDescriptor.NO_CHANCE;
		} else if (probability <= 0.15f) {
			return PregnancyDescriptor.LOW_CHANCE;
		} else if (probability <= 0.3f) {
			return PregnancyDescriptor.AVERAGE_CHANCE;
		} else if (probability < 1) {
			return PregnancyDescriptor.HIGH_CHANCE;
		} else {
			return PregnancyDescriptor.CERTAINTY;
		}
	}
}
