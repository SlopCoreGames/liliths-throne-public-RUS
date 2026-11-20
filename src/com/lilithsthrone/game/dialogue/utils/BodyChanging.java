package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.90
 * @version 0.3.9.1
 * @author Innoxia
 */
public class BodyChanging {
	
	private static GameCharacter target;
	private static DialogueNode coreNode;
	private static int defaultResponseTab;
	private static boolean debugMenu;
	
	public static boolean isDebugMenu() {
		return debugMenu;
	}

	public static GameCharacter getTarget() {
		if(target==null) {
			return Main.game.getPlayer();
		}
		return target;
	}

	public static void setTarget(GameCharacter target) {
		BodyChanging.target = target;
		BodyChanging.coreNode = null;
		BodyChanging.debugMenu = false;
	}

	public static void setTarget(GameCharacter target, DialogueNode coreNode, int defaultResponseTab) {
		BodyChanging.target = target;
		BodyChanging.coreNode = coreNode;
		BodyChanging.defaultResponseTab = defaultResponseTab;
		BodyChanging.debugMenu = false;
	}
	
	/**
	 * @param target Character being changed
	 * @param debugMenu If this menu is accessed from the debug menu.
	 */
	public static void setTarget(GameCharacter target, boolean debugMenu) {
		BodyChanging.target = target;
		BodyChanging.coreNode = null;
		BodyChanging.debugMenu = debugMenu;
	}
	
	private static Response getBodyChangingResponse(int responseTab, int index) {
		if (index == 0) {
			if (isDebugMenu()) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", DebugDialogue.DEBUG_MENU);

			} else if (coreNode != null) {
				return new Response("Назад", "Вернуться к предыдущему экрану.", coreNode) {
					@Override
					public void effects() {
						Main.game.setResponseTab(defaultResponseTab);
					}
				};

			} else {
				return new ResponseEffectsOnly("Назад", "Вернуться к предыдущему экрану.") {
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
			}
		}

		if (responseTab==0) {
			if (index == 1) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_CORE) {
					return new Response("Основа", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Основа",
						UtilText.parse(getTarget(), "Изменить основные черты тела [npc.namePos]."),
						BODY_CHANGING_CORE);

			} else if (index == 2) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_EYES) {
					return new Response("Глаза", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Глаза",
						UtilText.parse(getTarget(), "Изменить глаза [npc.namePos]."),
						BODY_CHANGING_EYES);

			} else if (index == 3) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_HAIR) {
					return new Response("Волосы", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Волосы",
						UtilText.parse(getTarget(), "Изменить волосы [npc.namePos]."),
						BODY_CHANGING_HAIR);

			} else if (index == 4) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_HEAD) {
					return new Response("Голова", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Голова",
						UtilText.parse(getTarget(), "Изменить лицо и голову [npc.namePos]."),
						BODY_CHANGING_HEAD);

			} else if (index == 5) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_ASS) {
					return new Response("Задница", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Задница",
						UtilText.parse(getTarget(), "Изменить параметры задницы [npc.namePos]."),
						BODY_CHANGING_ASS);

			} else if (index == 6) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_BREASTS) {
					return new Response("Грудь", "Вы уже находитесь на этом экране!", null);
				} else if (!getTarget().hasNipples()) {
					return new Response("Грудь",
							UtilText.parse(getTarget(), "У [npc.Name] [npc.do] нет груди!"),
							null);
				}
				return new Response("Грудь",
						UtilText.parse(getTarget(), "Изменить параметры груди [npc.namePos]."),
						BODY_CHANGING_BREASTS);

			} else if (index == 7) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_VAGINA) {
					return new Response("Киска", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Киска",
						UtilText.parse(getTarget(), "Изменить параметры влагалища [npc.namePos]."),
						BODY_CHANGING_VAGINA);

			} else if (index == 8) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_PENIS) {
					return new Response("Пенис", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Пенис",
						UtilText.parse(getTarget(), "Изменить параметры пениса [npc.namePos]."),
						BODY_CHANGING_PENIS);

			} else if (index == 9) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_SPINNERET) {
					return new Response("Паутинный аппарат", "Вы уже находитесь на этом экране!", null);
				} else if (!getTarget().hasSpinneret()) {
					return new Response("Паутинный аппарат",
							UtilText.parse(getTarget(), "У [npc.Name] [npc.do] нет паутинного аппарата!<br/><i>Паутинные аппараты приобретаются с определенными типами хвостов или ног.</i>"),
							null);
				}
				return new Response("Паутинный аппарат",
						UtilText.parse(getTarget(), "Изменить параметры паутинного аппарата [npc.namePos]."),
						BODY_CHANGING_SPINNERET);

			} else if (index == 10) {
				String title = getTarget().getBreastCrotchShape() == BreastShape.UDDERS?"Вымя":"Лобковые соски";
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_BREASTS_CROTCH) {
					return new Response(title, "Вы уже находитесь на этом экране!", null);
				}
				return new Response(title,
						UtilText.parse(getTarget(), "Изменить параметры [npc.namePos] [npc.crotchBoobs]."),
						BODY_CHANGING_BREASTS_CROTCH);

			} else if (index == 11) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_SAVE_LOAD) {
					return new Response("Сохранить/Загрузить", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Сохранить/Загрузить",
						UtilText.parse(getTarget(), "Сохранить или загрузить пресеты трансформации, позволяя быстро менять свой облик."),
						BODY_CHANGING_SAVE_LOAD) {
					@Override
					public void effects() {
						initSaveLoadMenu();
					}
				};

			} else if (index == 12 && isDebugMenu()) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_MAKEUP) {
					return new Response("Макияж (отладка)", "Вы уже находитесь на этом экране!", null);
				}
				return new Response("Макияж (отладка)",
						UtilText.parse(getTarget(), "Изменить аспекты внешности [npc.namePos]. (Это меню трансформации доступно только в режиме отладки.)"),
						BODY_CHANGING_MAKEUP);
				
			} else {
				return null;
			}
		} else if (responseTab == 1 && isDebugMenu()) {
			if (index == 1) {
				if (getTarget() == Main.game.getPlayer()) {
					return new Response(Main.game.getPlayer().getName(), "Вы — текущая цель.", null);
				} else {
					return new Response(Main.game.getPlayer().getName(), "Выбрать себя.", BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							setTarget(Main.game.getPlayer(), isDebugMenu());
						}
					};
				}
			} else {
				index-=2;
			}

			if (index >= Main.game.getCharactersPresent().size() || index<0) {
				return null;
			}
			GameCharacter gc = Main.game.getCharactersPresent().get(index);
			if (!gc.isUnique()) {
				if (getTarget() == gc) {
					return new Response(gc.getName(), gc.getName()+" — текущая цель.", null);
				} else {
					return new Response(gc.getName(), "Сменить цель на "+gc.getName(), BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							setTarget(gc, isDebugMenu());
						}
					};
				}
			}
		}
		return null;
	}

	private static String getBodyChangingTabTitle(int index) {
		if (!isDebugMenu()) {
			return null;
		}
		if (index == 0) {
			return "Трансформация";
		} else if (index == 1) {
			return "Цель";
		}
		return null;
	}

	private static final List<AbstractRace> allRaces = new ArrayList<>(Race.getAllRaces());

	private static List<AbstractRace> getTFRaces(AbstractRace forceAllowedRace) {
		return getTFRaces(Util.newArrayListOfValues(forceAllowedRace), false, false, false);
	}

	private static List<AbstractRace> getTFRaces(AbstractRace forceAllowedRace, boolean forceDemon, boolean allowHDDemon, boolean allowHDHuman) {
		return getTFRaces(Util.newArrayListOfValues(forceAllowedRace), forceDemon, allowHDDemon, allowHDHuman);
	}
	
	private static List<AbstractRace> getTFRaces(List<AbstractRace> forceAllowedRaces, boolean forceDemon, boolean allowHDDemon, boolean allowHDHuman) {
		List<AbstractRace> allowedRaces = Util.newArrayListOfValues();
		if (forceAllowedRaces != null) {
			allowedRaces.addAll(forceAllowedRaces);
		}
		GameCharacter target = getTarget();
		
		if (isDebugMenu()) {
			return allRaces;
		} else if (target.isDoll()) {
			return Util.newArrayListOfValues(Race.NONE, target.getFleshSubspecies().getRace());
		} else if(target.isYouko()) {
			allowedRaces.add(Race.FOX_MORPH);
			allowedRaces.add(Race.HUMAN);
		} else if(isSlimeTFMenu() || target.isElemental()) {
			for (AbstractRace race : allRaces) {
				if (race!=Race.NONE
						&& Main.getProperties().isAdvancedRaceKnowledgeDiscovered(AbstractSubspecies.getMainSubspeciesOfRace(race))
//						&& Main.getProperties().isRaceDiscovered(AbstractSubspecies.getMainSubspeciesOfRace(race))
						) {
					allowedRaces.add(race);
				}
			}
		} else if (isHalfDemon()) {
			if (forceDemon) {
				return Util.newArrayListOfValues(Race.DEMON);
			}
			allowedRaces.add(target.getHalfDemonSubspecies().getRace());
			if (allowHDDemon) {
				allowedRaces.add(Race.DEMON);
			}
			if (allowHDHuman) {
				allowedRaces.add(Race.HUMAN);
			}
		} else if (ScarlettsShop.isSlaveCustomisationMenu()) {
			for (AbstractRace race : allRaces) {
				if(!race.isAbleToSelfTransform()
						&& (Subspecies.getWorldSpecies(WorldType.DOMINION, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false).keySet().stream().anyMatch(s->s.getRace()==race)
						|| Subspecies.getWorldSpecies(WorldType.SUBMISSION, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false).keySet().stream().anyMatch(s->s.getRace()==race))) {
					allowedRaces.add(race);
				}
			}
		} else if (isDemonTFMenu()) {
			allowedRaces.add(Race.DEMON);
			for (AbstractRace race : allRaces) {
				if (race == Race.HUMAN) {
					if(target.hasPerkAnywhereInTree(Perk.POWER_OF_LYSSIETH_4_DEMON)) {
						allowedRaces.add(race);
					}
					continue;
				}
				switch (race.getRacialClass()) {
					case MAMMAL:
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LUNETTE_5_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case BIRD:
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LYXIAS_6_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case REPTILE:
					case AMPHIBIAN:
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LISOPHIA_7_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case FISH:
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case INSECT:
						// Inno you overlooked this one...
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LOVIENNE_2_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case OTHER:
						// Elementals, Slimes and Dolls
						break;
				}
			}
		}
		allowedRaces.add(Race.NONE);
		
		return allowedRaces;
	}
	
	private static boolean removeNoneFromTailChoices() {
		if(isHalfDemon() && !(getTarget().isElemental())) {
			return !RacialBody.valueOfRace(getTarget().getHalfDemonSubspecies().getRace()).getTailType().contains(TailType.NONE);
		}
		return false;
	}
	
	private static boolean removeNoneFromWingChoices() {
		if(isHalfDemon() && !(getTarget().isElemental())) {
			return !RacialBody.valueOfRace(getTarget().getHalfDemonSubspecies().getRace()).getWingTypes().contains(WingType.NONE);
		}
		return false;
	}
	
	private static boolean isDemonTFMenu() {
		return !isDebugMenu()
				&& (getTarget().getSubspeciesOverride()==Subspecies.IMP
					|| getTarget().getSubspeciesOverride()==Subspecies.IMP_ALPHA
					|| getTarget().getSubspeciesOverride()==Subspecies.HALF_DEMON
					|| getTarget().getSubspeciesOverride()==Subspecies.DEMON
					|| getTarget().getSubspeciesOverride()==Subspecies.LILIN
					|| getTarget().getSubspeciesOverride()==Subspecies.ELDER_LILIN);
	}

	private static boolean isSelfTFMenu() {
		return !isDebugMenu()
				&& !isDemonTFMenu()
				&& getTarget().getBodyMaterial()!=BodyMaterial.SLIME
				&& getTarget().getTrueSubspecies().isAbleToSelfTransform();
	}

	private static boolean isSlimeTFMenu() {
		return !isDebugMenu()
//				&& !isDemonTFMenu()
				&& !isSelfTFMenu()
				&& getTarget().getBodyMaterial()==BodyMaterial.SLIME;
	}

	private static boolean isHalfDemon() {
		return getTarget().getSubspeciesOverride()==Subspecies.HALF_DEMON;
	}
	
	private static boolean isNonHumanHalfDemon() {
		return getTarget().getHalfDemonSubspecies().getRace() == Race.HUMAN;
	}

	private static String getSelfTransformDescription(String area) {
		if(ScarlettsShop.isSlaveCustomisationMenu()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("<div class='container-full-width' style='text-align:center;'>");
			if(isDemonTFMenu()) {
				sb.append("<i>[npc.Name] может использовать силу демонической формы [npc.her], чтобы изменять аспекты [npc.her] "+area+".</i>");

			} else if(isSelfTFMenu()) {
				sb.append("<i>[npc.Name] может использовать врожденные силы [npc.her], чтобы изменять аспекты [npc.her] "+area+".</i>");
				if(target.isElemental()) {
					sb.append("<br/>[style.italicsMinorBad(Вы можете превращать [npc.name] только в те расы, о которых у вас есть глубокие знания.)]");
				}

			} else if(isDebugMenu()) {
				sb.append("<i>[npc.Name] может использовать мощь инструмента отладки, чтобы изменять аспекты [npc.her] "+area+".</i>");

			} else if(getTarget().isDoll()) {
				sb.append("<i>Подключив кабель D.E.C.K. к порту на затылке [npc.namePos], вы получаете возможность настраивать [npc.her] "+area+"...</i>");

			} else {
				sb.append("<i>[npc.NamePos] морфируемое, склизкое тело, которое [npc.verb(allow)] [npc.herHim] для самотрансформации аспектов [npc.her] "+area+".</i>");
				if(getTarget().isPlayer()) {
					sb.append("<br/>[style.italicsMinorBad(Вы можете самотрансформироваться только в те расы, о которых у вас есть расширенные знания.)]");
				} else {
					sb.append("<br/>[style.italicsMinorBad(Вы можете превращать [npc.name] только в те расы, о которых у вас есть глубокие знания.)]");
				}
			}
		sb.append("</div>");

		return UtilText.parse(getTarget(), sb.toString());
	}

	public static final DialogueNode BODY_CHANGING_CORE = new DialogueNode("Основа", "", true) {
		@Override
		public void applyPreParsingEffects() {
			SuccubisSecrets.initCoveringsMap(getTarget());
		}

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			if (ScarlettsShop.isSlaveCustomisationMenu()) {
				SuccubisSecrets.initCoveringsMap(getTarget());
			}
			UtilText.nodeContentSB.append(getSelfTransformDescription("тело"));

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getAgeAppearanceChoiceDiv()
							+"</div>"

							+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformFemininityChoiceDiv()
								+CharacterModificationUtils.getHeightChoiceDiv(false)
							+"</div>");

			if (getTarget().isElemental()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getSelfTransformBodyMaterialChoiceDiv(getTarget()));
			}

			UtilText.nodeContentSB.append(
					"<div class='cosmetics-container' style='background:transparent;'>"
							+CharacterModificationUtils.getBodySizeChoiceDiv()
							+CharacterModificationUtils.getMuscleChoiceDiv()
							+"<div class='container-full-width' style='text-align:center;'>"
							+UtilText.parse(getTarget(), "[npc.NamePos] значения мышц и размера тела придают [npc.herHim] форму тела: <b style='color:"+getTarget().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(getTarget().getBodyShape().getName(false))+"</b>")
							+"</div>"
							+"</div>");

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformFaceChoiceDiv(getTFRaces(getTarget().getFaceRace(), false, false, true))
								+CharacterModificationUtils.getSelfTransformBodyChoiceDiv(getTFRaces(getTarget().getTorsoType().getRace(), false, false, true))
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformArmChoiceDiv(getTFRaces(getTarget().getArmRace()))
								+CharacterModificationUtils.getSelfTransformLegChoiceDiv(getTFRaces(getTarget().getLegRace()), isDebugMenu())
								+"</div>");

				if (getTarget().isYouko()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
									+CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
									+"</div>");
				} else {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformArmCountDiv()
									+CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
									+"</div>"

									+"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
									+CharacterModificationUtils.getSelfTransformGenitalArrangementChoiceDiv()
									+"</div>");
				}

				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTailChoiceDiv(
								getTFRaces(Util.newArrayListOfValues(getTarget().getTailRace(), getTarget().getLegRace()), isNonHumanHalfDemon(), false, false),
								removeNoneFromTailChoices())
								+CharacterModificationUtils.getSelfTransformTailLengthDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTailCountDiv()
								+CharacterModificationUtils.getSelfTransformTailGirthDiv()
								+"</div>");

				if (!getTarget().isYouko()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformTentacleLengthDiv()
									+CharacterModificationUtils.getSelfTransformTentacleGirthDiv()
									+"</div>"

									+"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformWingChoiceDiv(
									getTFRaces(Util.newArrayListOfValues(getTarget().getWingRace(), getTarget().getTorsoType().getRace()), isNonHumanHalfDemon(), false, false),
									removeNoneFromWingChoices())
									+CharacterModificationUtils.getSelfTransformWingSizeDiv()
									+"</div>");
				}
			} else {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformArmCountDiv()
								+CharacterModificationUtils.getSelfTransformWingSizeDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+ CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
								+ CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTailLengthDiv()
								+CharacterModificationUtils.getSelfTransformTailGirthDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTentacleLengthDiv()
								+CharacterModificationUtils.getSelfTransformTentacleGirthDiv()
								+"</div>");
			}

			for (Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()) {
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();

				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(getTarget(), bct, entry.getValue().getValue());

				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(getTarget(), titleDescription.getValue()),
						true,
						true));
			}

			if (Main.game.isBodyHairEnabled() && (!getTarget().isDoll() || isDebugMenu())) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivUnderarmHair(false, "Волосы под мышками",
						UtilText.parse(getTarget(), "Изменить количество волос в подмышках [npc.namePos]."))

						+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getUnderarmHairType().getType(), "Цвет волос в подмышках",
						UtilText.parse(getTarget(), "Цвет волос в подмышках [npc.namePos]."),
						true, true));
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_EYES = new DialogueNode("Глаза", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("глаза"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformEyeChoiceDiv(
								getTFRaces(getTarget().getEyeRace(), true, false, false)));

				if (!getTarget().isYouko() || isDebugMenu()) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformEyeCountDiv());
				}

				UtilText.nodeContentSB.append("</div>");
			}

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getEyeType().getRace(), getTarget().getCovering(getTarget().getEyeCovering()).getType(),
							"Цвет радужки",
							UtilText.parse(getTarget(), "Цвет и узор радужки глаз [npc.namePos]."),
							true, true)

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getEyeType().getRace(), getTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(),
							"Цвет зрачков",
							UtilText.parse(getTarget(), "Цвет и узор зрачков [npc.namePos]."),
							true, true)

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getEyeType().getRace(), getTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(),
							"Цвет склер",
							UtilText.parse(getTarget(), "Цвет и узор склер [npc.namePos]."),
							true, true));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_HAIR = new DialogueNode("Волосы", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("волосы"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformHairChoiceDiv(
						getTFRaces(getTarget().getHairRace())));
			}

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformHairLengthDiv()
							+CharacterModificationUtils.getNeckFluffDiv()
							+"</div>"

							+CharacterModificationUtils.getSelfDivHairStyles("Прическа", UtilText.parse(getTarget(), "Изменить прическу [npc.namePos]."))

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getHairRace(),
							getTarget().getCovering(getTarget().getHairCovering()).getType(), "Цвет волос",
							UtilText.parse(getTarget(), "Изменить цвет волос [npc.her]."), true, true));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_HEAD = new DialogueNode("Голова", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("голова и лицо"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformEarChoiceDiv(
						getTFRaces(getTarget().getEarRace())));
			}

			if (!getTarget().isYouko() || isDebugMenu()) {
				if (!getTarget().isDoll() || isDebugMenu()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformHornChoiceDiv(
									getTFRaces(getTarget().getHornRace(), false, true, false))
									+CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(
									getTFRaces(getTarget().getAntennaRace()))
									+"</div>");
				}

				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformHornSizeDiv()
								+CharacterModificationUtils.getSelfTransformAntennaSizeDiv()
								+"</div>");

				if (!getTarget().isDoll() || isDebugMenu()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformHornCountDiv()
									+CharacterModificationUtils.getSelfTransformAntennaCountDiv()
									+"</div>"

									+"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
									+CharacterModificationUtils.getSelfTransformAntennaePerRowCountDiv()
									+"</div>");
				}

				if (getTarget().hasHorns()) {
					UtilText.nodeContentSB.append(
							CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getHornRace(),
									getTarget().getCovering(getTarget().getHornCovering()).getType(), "Цвет рогов",
									UtilText.parse(getTarget(), "Цвет рогов [npc.namePos]."),
									true, true));
				}
			}

			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getSelfTransformLipSizeDiv()

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
							+CharacterModificationUtils.getSelfTransformThroatWetnessDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformThroatCapacityDiv()
							+CharacterModificationUtils.getSelfTransformThroatDepthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformThroatElasticityDiv()
							+CharacterModificationUtils.getSelfTransformThroatPlasticityDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformTongueSizeDiv()
							+CharacterModificationUtils.getSelfTransformTongueModifiersDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getMouthType().getRace(), getTarget().getCovering(BodyCoveringType.MOUTH).getType(),
							"Цвет губ и горла",
							UtilText.parse(getTarget(),
									"Естественный цвет [npc.namePos] "+(getTarget().getFaceType() == FaceType.HARPY?"клюв":"губы")+" (верхние настройки) и горла [npc.her] (нижние настройки). Помаду можно использовать, чтобы скрыть естественный цвет губ [npc.her]."),
							true, true)

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getTongueType().getRace(), getTarget().getCovering(BodyCoveringType.TONGUE).getType(),
							"Цвет языка",
							(getTarget().isPlayer()
									?"Цвет вашего языка."
									:UtilText.parse(getTarget(), "Цвет языка [npc.namePos].")),
							true, true));

			if (Main.game.isFacialHairEnabled() && (!getTarget().isFeminine() || Main.game.isFemaleFacialHairEnabled())) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "Длина бороды",
						UtilText.parse(getTarget(), "Изменить длину бороды [npc.namePos]."))

						+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getFacialHairType().getType(), "Цвет бороды",
						UtilText.parse(getTarget(), "Цвет бороды [npc.namePos]."),
						true, true));
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_ASS = new DialogueNode("Задница", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("ягодицы и бедра"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformAssChoiceDiv(
						getTFRaces(getTarget().getAssRace(), true, false, false)));
			}

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformAssSizeDiv()
							+CharacterModificationUtils.getSelfTransformHipSizeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformAnusModifiersDiv()
							+CharacterModificationUtils.getSelfTransformAnusWetnessDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformAnusCapacityDiv()
							+CharacterModificationUtils.getSelfTransformAnusDepthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformAnusElasticityDiv()
							+CharacterModificationUtils.getSelfTransformAnusPlasticityDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false,
							getTarget().getAssRace(),
							getTarget().getCovering(BodyCoveringType.ANUS).getType(),
							"Цвет ануса",
							UtilText.parse(getTarget(), "Изменить цвет ануса [npc.namePos]."),
							true, true));

			if (Main.game.isAssHairEnabled() && (!getTarget().isDoll() || isDebugMenu())) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivAssHair(false, "Волосы на заднице",
						UtilText.parse(getTarget(), "Изменить количество волос вокруг ануса [npc.namePos]."))

						+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getAssHairType().getType(), "Цвет волос на заднице",
						UtilText.parse(getTarget(), "Цвет волос на заднице [npc.namePos]."),
						true, true));
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_BREASTS = new DialogueNode("Грудь", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("груди"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformBreastChoiceDiv(
						getTFRaces(getTarget().getBreastRace(), true, false, false)));
			}

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformBreastSizeDiv()
							+CharacterModificationUtils.getSelfTransformBreastShapeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformBreastRowsDiv()
							+CharacterModificationUtils.getSelfTransformNippleModifiersDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformLactationDiv()
							+CharacterModificationUtils.getSelfTransformLactationRegenerationDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformLactationFlavourDiv()
							+CharacterModificationUtils.getSelfTransformLactationModifiersDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformNippleCountDiv()
							+CharacterModificationUtils.getSelfTransformNippleShapeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformNippleSizeDiv()
							+CharacterModificationUtils.getSelfTransformAreolaeSizeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformNippleCapacityDiv()
							+CharacterModificationUtils.getSelfTransformNippleDepthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformNippleElasticityDiv()
							+CharacterModificationUtils.getSelfTransformNipplePlasticityDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false,
							getTarget().getBreastRace(),
							getTarget().getCovering(BodyCoveringType.NIPPLES).getType(),
							"Цвет сосков",
							UtilText.parse(getTarget(), "Изменить цвет сосков [npc.namePos]."),
							true, true)

							+CharacterModificationUtils.getKatesDivCoveringsNew(false,
							Race.NONE,
							getTarget().getCovering(BodyCoveringType.MILK).getType(),
							"Цвет молока",
							UtilText.parse(getTarget(), "Изменить цвет [npc.milk] [npc.namePos]."),
							true, true));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_VAGINA = new DialogueNode("Киска", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("вагина"));

			//if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformVaginaChoiceDiv(
						getTFRaces(getTarget().getVaginaRace(), true, false, false)));
			//}

			if (getTarget().hasVagina()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getSelfTransformGirlcumFlavourDiv()
								+CharacterModificationUtils.getSelfTransformGirlcumModifiersDiv());

				if (!getTarget().isDoll() || isDebugMenu()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformVaginaSquirterDiv()
									+CharacterModificationUtils.getSelfTransformVaginaHymenDiv()
									+"</div>"
									+"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformLabiaSizeDiv()
									+CharacterModificationUtils.getSelfTransformVaginaEggLayerDiv()
									+"</div>");
				} else {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformVaginaSquirterDiv()
									+CharacterModificationUtils.getSelfTransformLabiaSizeDiv()
									+"</div>");
				}

				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaModifiersDiv()
								+CharacterModificationUtils.getSelfTransformVaginaWetnessDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaCapacityDiv()
								+CharacterModificationUtils.getSelfTransformVaginaDepthDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaElasticityDiv()
								+CharacterModificationUtils.getSelfTransformVaginaPlasticityDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformClitorisSizeDiv()
								+CharacterModificationUtils.getSelfTransformClitorisGirthDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformClitorisModifiersDiv()
								+CharacterModificationUtils.getSelfTransformVaginaUrethraModifiersDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaUrethraCapacityDiv()
								+CharacterModificationUtils.getSelfTransformVaginaUrethraDepthDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaUrethraElasticityDiv()
								+CharacterModificationUtils.getSelfTransformVaginaUrethraPlasticityDiv()
								+"</div>"

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								getTarget().getVaginaRace(),
								getTarget().getCovering(BodyCoveringType.VAGINA).getType(),
								"Цвет влагалища",
								UtilText.parse(getTarget(), "Изменить цвет [npc.namePos] вагины."),
								true, true)

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								Race.NONE,
								getTarget().getCovering(BodyCoveringType.GIRL_CUM).getType(),
								"Цвет девичьей смазки",
								UtilText.parse(getTarget(), "Изменить цвет [npc.namePos] [npc.girlcum]."),
								true, true));

				if (Main.game.isPubicHairEnabled() && (!getTarget().isDoll() || isDebugMenu())) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "Лобковые волосы",
							UtilText.parse(getTarget(), "Изменить количество волос вокруг гениталий [npc.namePos]."))

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getPubicHairType().getType(), "Цвет лобковых волос",
							UtilText.parse(getTarget(), "Цвет лобковых волос [npc.namePos]."),
							true, true));
				}
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_PENIS = new DialogueNode("Пенис", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("пенис"));

			//if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformPenisChoiceDiv(
						getTFRaces(getTarget().getPenisRace(), true, false, false), false));
			//}
			
			if (getTarget().hasPenis()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformPenisSizeDiv()
								+CharacterModificationUtils.getSelfTransformPenisGirthDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformPenisModifiersDiv()
								+CharacterModificationUtils.getSelfTransformCumExplusionDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformCumProductionDiv()
								+CharacterModificationUtils.getSelfTransformCumRegenerationDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformCumFlavourDiv()
								+CharacterModificationUtils.getSelfTransformCumModifiersDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTesticleCountDiv()
								+CharacterModificationUtils.getSelfTransformInternalTesticleDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTesticleSizeDiv()
								+CharacterModificationUtils.getSelfTransformUrethraModifiersDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformUrethraCapacityDiv()
								+CharacterModificationUtils.getSelfTransformUrethraDepthDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformUrethraElasticityDiv()
								+CharacterModificationUtils.getSelfTransformUrethraPlasticityDiv()
								+"</div>"

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								getTarget().getPenisRace(),
								getTarget().getCovering(BodyCoveringType.PENIS).getType(),
								"Цвет пениса",
								UtilText.parse(getTarget(), "Изменить цвет пениса [npc.namePos]."),
								true, true)

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								Race.NONE,
								getTarget().getCovering(BodyCoveringType.CUM).getType(),
								"Цвет спермы",
								UtilText.parse(getTarget(), "Изменить цвет [npc.namePos] [npc.cum]."),
								true, true));
				
				if (Main.game.isPubicHairEnabled() && (!getTarget().isDoll() || isDebugMenu())) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "Лобковые волосы",
							UtilText.parse(getTarget(), "Изменить количество волос вокруг гениталий [npc.namePos]."))
							+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getPubicHairType().getType(), "Цвет лобковых волос",
							UtilText.parse(getTarget(), "Цвет лобковых волос [npc.namePos]."),
							true, true));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_BREASTS_CROTCH = new DialogueNode("Лобковые соски", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSelfTransformDescription("[npc.crotchBoobs]"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformBreastCrotchChoiceDiv(
						getTFRaces(getTarget().getBreastCrotchRace(), true, false, false)));
			}
			
			if (getTarget().hasBreastsCrotch()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformBreastCrotchSizeDiv()
								+CharacterModificationUtils.getSelfTransformBreastCrotchShapeDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformBreastCrotchRowsDiv()
								+CharacterModificationUtils.getSelfTransformNippleCrotchModifiersDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformLactationCrotchDiv()
								+CharacterModificationUtils.getSelfTransformLactationCrotchRegenerationDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformLactationCrotchFlavourDiv()
								+CharacterModificationUtils.getSelfTransformLactationCrotchModifiersDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformNippleCrotchCountDiv()
								+CharacterModificationUtils.getSelfTransformNippleCrotchShapeDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformNippleCrotchSizeDiv()
								+CharacterModificationUtils.getSelfTransformAreolaeCrotchSizeDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformNippleCrotchCapacityDiv()
								+CharacterModificationUtils.getSelfTransformNippleCrotchDepthDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformNippleCrotchElasticityDiv()
								+CharacterModificationUtils.getSelfTransformNippleCrotchPlasticityDiv()
								+"</div>"

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								getTarget().getBreastCrotchRace(),
								getTarget().getCovering(BodyCoveringType.NIPPLES_CROTCH).getType(),
								"Цвет сосков",
								UtilText.parse(getTarget(), "Изменить цвет [npc.namePos] [npc.crotchNipples]."),
								true, true)

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								Race.NONE,
								getTarget().getCovering(BodyCoveringType.MILK).getType(),
								"Цвет молока",
								UtilText.parse(getTarget(), "Изменить цвет [npc.milk] [npc.namePos]."),
								true, true));
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_SPINNERET = new DialogueNode("Паутинный аппарат", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					getSelfTransformDescription("паутинная железа")

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformSpinneretModifiersDiv()
							+CharacterModificationUtils.getSelfTransformSpinneretWetnessDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformSpinneretCapacityDiv()
							+CharacterModificationUtils.getSelfTransformSpinneretDepthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformSpinneretElasticityDiv()
							+CharacterModificationUtils.getSelfTransformSpinneretPlasticityDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false,
							getTarget().hasLegSpinneret()
									?getTarget().getLegRace()
									:getTarget().getTailRace(),
							getTarget().getCovering(BodyCoveringType.SPINNERET).getType(),
							"Цвет отверстия",
							UtilText.parse(getTarget(), "Изменить цвет отверстия [npc.namePos]."),
							true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode BODY_CHANGING_MAKEUP = new DialogueNode("Макияж", "", true) {
		@Override
		public String getHeaderContent() {
			return getSelfTransformDescription("makeup")

					+CharacterModificationUtils.getKatesDivCoveringsNew(
					false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Румяна", "Румяна (также называемые руж) используются для придания щекам цвета, чтобы придать лицу более юный вид и подчеркнуть скулы.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
					false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Помада", "Помада используется для придания губам цвета, текстуры и защиты.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
					false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Подводка для глаз", "Подводка наносится по контуру глаз, чтобы подчеркнуть их форму или выделить отдельные черты.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
					false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Тени для век", "Тени для век используются для того, чтобы сделать глаза ярче или привлекательнее.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
					false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Лак для ногтей", "Лак для ногтей используется для окрашивания и защиты ногтей на твоих [pc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
					false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Лак для ногтей на ногах", "Лак для ногтей на ногах используется для окрашивания и защиты ногтей на твоих [pc.feet].", true, true);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	// Save/Load dialogue and associated methods/variables:
	public static void initSaveLoadMenu() {
		presetTransformationsMap = new HashMap<>();
		
		for(File f : getSavedBodies()) {
			try {
				String name = Util.getFileIdentifier(f);
				String nameReadable = Util.capitaliseSentence(name.replaceAll("_", " "));
				Body loadedBody = loadBody(name);
				Femininity fem = Femininity.valueOf(loadedBody.getFemininity());
				AbstractSubspecies subspecies = loadedBody.getLoadedSubspecies();
				String subspeciesName = loadedBody.isFeminine()?subspecies.getSingularFemaleName(loadedBody):subspecies.getSingularMaleName(loadedBody);
				String displayName;
				if(isPresetTransformationAvailable(loadedBody)) {
					displayName = "<b>"+nameReadable+"</b>"
							+ " (<span style='color:"+fem.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fem.getNames().get(0))+"</span>"
							+ " <span style='color:"+subspecies.getColour(null).toWebHexString()+";'>"+subspeciesName+"</span>)";
				} else {
					displayName = "[style.boldDisabled("+nameReadable+")] [style.colourDisabled(("+Util.capitaliseSentence(fem.getNames().get(0))+" "+subspeciesName+"))]";
				}
				presetTransformationsMap.put(name, new Value<>(displayName, loadedBody));
			} catch(Exception ex) {
			}
		}
	}
	
	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";
	/** Mapping file location to a Value of saved name and Body. */
	private static Map<String, Value<String, Body>> presetTransformationsMap = new HashMap<>();
	
	public static Map<String, Value<String, Body>> getPresetTransformationsMap() {
		return presetTransformationsMap;
	}

	public static final DialogueNode BODY_CHANGING_SAVE_LOAD = new DialogueNode("Сохранить файлы трансформаций", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			saveLoadSB.append(
					"<div class='container-full-width' style='padding:0; margin:0 0 8px 0;'><p>Для имен файлов сохранения подходят только стандартные символы (буквы и цифры).<br/>Наведите курсор на иконку любого пресета трансформации, чтобы увидеть обзор тела."
							+ UtilText.parse(getTarget(), "<br/>Если имя [style.colourDisabled(серое)], значит, [npc.name] [npc.do]не может трансформироваться в этот пресет; вы можете навести курсор на серую кнопку загрузки, чтобы узнать причину.")
						+ "</p></div><div class='container-full-width' style='padding:0; margin:0;'><div class='container-full-width' style='width:calc(75% - 16px); text-align:center; background:transparent;'>Имя</div><div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>Сохранить | Загрузить | Удалить</div></div>");

			int i=0;
			
			saveLoadSB.append(getSaveLoadRow(null, "", null, i%2==0));
			i++;
			
			for(Entry<String, Value<String, Body>> entry : presetTransformationsMap.entrySet()) {
				saveLoadSB.append(getSaveLoadRow(entry.getKey(), entry.getValue().getKey(), entry.getValue().getValue(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Подтверждения: ",
						"Включить или выключить подтверждение при нажатии кнопок загрузки, перезаписи или удаления сохраненной трансформации. Если включено, для выполнения действия потребуется два клика. Если выключено — только один.",
							BODY_CHANGING_SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "Подтверждения: "+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ВКЛ</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>ВЫКЛ</span>");
					}
					
					@Override
					public void effects() {
						loadConfirmationName = "";
						overwriteConfirmationName = "";
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if (index == 0) {
				return new Response("Назад", "Назад в меню трансформации.", BODY_CHANGING_CORE);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static List<File> getSavedBodies() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/transformation_presets");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparing(File::getName).reversed());
		
		return filesList;
	}

	private static String getSaveLoadRow(String baseName, String displayName, Body body, boolean altColour) {
		
		if(body!=null){
			String fileName = (baseName+".xml");
			
			boolean canTransform = isPresetTransformationAvailable(body);
			
//			System.out.println(body.getLoadedSubspecies().getName(body));
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+" position:relative;'><div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'><div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'><div class='inventoryImage' style='width:100%;'><div class='inventoryImage-content'"+(canTransform?"":" style='opacity:0.25;'")+">"
										+ body.getLoadedSubspecies().getSVGStringFromBody(body)
									+ "</div><div class='overlay no-pointer' id='LOADED_BODY_" + baseName + "'></div>"
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+ "<p style='margin:0; padding:2px;'>"+displayName+"</p><p style='margin:0; padding:2px;'>[style.colourDisabled(data/transformation_presets/)]"+baseName+"[style.colourDisabled(.xml)]</p></div></div><div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.game.isStarted()
									?(fileName.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (canTransform
									? (fileName.equals(loadConfirmationName)
										?"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
									:"<div class='square-button saveIcon disabled' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadDisabled()+"</div></div>")
	
	
							+ (fileName.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else {
			String svgString = getTarget().getSubspecies().getSVGString(getTarget());
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
					
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'>"
										+ svgString
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_BODY_CURRENT'></div>"
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' placeholder='Enter File Name' style='padding:0;margin:0;width:100%;'></form>"
							+"</div>"
							
						+ "</div>"
					
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "<div class='square-button saveIcon' id='NEW_SAVE' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
						+ "</div>"
					+ "</div>";
		}
	}

	public static void saveBody(String name, boolean allowOverwrite) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		Body body = getTarget().getBody();
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/transformation_presets");
		dir.mkdir();

		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().equals(name+".xml")){
						if(!allowOverwrite) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "Такое имя уже существует!");
							return;
						}
					}
				}
			}
		}

		try {
			// Starting stuff:
			Document doc = Main.getDocBuilder().newDocument();
			
			Element coreElement = doc.createElement("body");
			doc.appendChild(coreElement);

			body.saveAsXML(coreElement, doc);
			
			// Ending stuff:
			
			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			String saveLocation = "data/transformation_presets/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		
		BodyChanging.initSaveLoadMenu();
		Main.game.setContent(new Response("", "", BodyChanging.BODY_CHANGING_SAVE_LOAD));
	}

	public static Body loadBody(String name) {
		if(isLoadBodyAvailable(name)) {
			File file = new File("data/transformation_presets/"+name+".xml");

			if (file.exists()) {
				try {
					Document doc = Main.getDocBuilder().parse(file);
					
					// Cast magic:
					doc.getDocumentElement().normalize();
					
					Body body = Body.loadFromXML(null, (Element) doc.getDocumentElement(), doc);
					body.calculateRace(null);
					
					return body;
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isLoadBodyAvailable(String name) {
		File file = new File("data/transformation_presets/"+name+".xml");

		if(!file.exists()) {
			return false;
		}
		
		return true;
	}

	public static void deleteBody(String name) {
		File file = new File("data/transformation_presets/"+name+".xml");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Файл не найден...");
		}
	}

	public static boolean isPresetTransformationAvailable(Body body) {
		String unavailabilityText = getPresetTransformationUnavailabilityText(body);
		return unavailabilityText==null || unavailabilityText.isEmpty();
	}
	
	public static String getPresetTransformationUnavailabilityText(Body body) {
		if(isDebugMenu()) {
			return "";
		}
		
		// Height limitation:
		if(getTarget().isShortStature()!=body.isShortStature()) {
			if(getTarget().isShortStature()) {
				return UtilText.parse(getTarget(), "[npc.NameIsFull] слишком низкий, чтобы превратиться в эту форму!");
			} else {
				return UtilText.parse(getTarget(), "[npc.NameIsFull] слишком высокий, чтобы превратиться в эту форму!");
			}
		}
		
		// Material limitations:
		Set<BodyMaterial> materialsAllowed = Util.newHashSetOfValues(getTarget().getBodyMaterial());
		if(getTarget() instanceof Elemental) {
			switch(getTarget().getBodyMaterial()) {
				// Air:
				case AIR:
					break;
				// Arcane:
				case ARCANE:
					break;
				// Fire:
				case FIRE:
					break;
				// Earth:
				case RUBBER:
					materialsAllowed.add(BodyMaterial.STONE);
					break;
				case STONE:
					materialsAllowed.add(BodyMaterial.RUBBER);
					break;
				// Water:
				case WATER:
					materialsAllowed.add(BodyMaterial.ICE);
					break;
				case ICE:
					materialsAllowed.add(BodyMaterial.WATER);
					break;
				// Non-elemental materials:
				case FLESH:
				case SLIME:
				case SILICONE:
					break;
			}
		}
		if(!materialsAllowed.contains(body.getBodyMaterial())) {
			BodyMaterial matCurrent = getTarget().getBodyMaterial();
			BodyMaterial matTarget = body.getBodyMaterial();
			return UtilText.parse(getTarget(),
					"[npc.Name] не может превратиться в тело, материал которого отличается от текущего материала [npc.her]!<br/>Текущий материал: <span style='color:"+matCurrent.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(matCurrent.getName())+"</span>"
					+ "<br/>Transformation target's material: <span style='color:"+matTarget.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(matTarget.getName())+"</span>");
		}
				
		// Feral limitations:
		if(body.isFeral()!=getTarget().isFeral()) {
			if(body.isFeral()) {
				return UtilText.parse(getTarget(), "[npc.Name] не может превратиться в [style.colourFeral(дикое тело)], так как текущее тело [npc.her] не является диким!");
			}
			return UtilText.parse(getTarget(), "[npc.Name] не может превратиться в [style.colourHuman(недикое тело)], так как текущее тело [npc.her] является диким!");
		}

		StringBuilder sb = new StringBuilder();
		List<String> partsList = new ArrayList<>();

		for (BodyPartInterface currentPart : getTarget().getBody().getAllBodyParts()) {
			List<AbstractRace> availableRaces;
			if (currentPart instanceof AbstractAssType
					|| currentPart instanceof AbstractBreastType
					|| currentPart instanceof AbstractEyeType
					|| currentPart instanceof AbstractPenisType
					|| currentPart instanceof AbstractVaginaType) {
				availableRaces = getTFRaces(currentPart.getType().getRace(), true, false, false);
			} else if (currentPart instanceof AbstractHornType) {
				availableRaces = getTFRaces(currentPart.getType().getRace(), false, true, false);
			} else if (currentPart instanceof AbstractFaceType
					|| currentPart instanceof AbstractTorsoType) {
				availableRaces = getTFRaces(currentPart.getType().getRace(), false, false, true);
			} else if (currentPart instanceof AbstractTailType) {
				availableRaces = getTFRaces(Util.newArrayListOfValues(getTarget().getTailRace(), getTarget().getLegRace()), isNonHumanHalfDemon(), false, false);
				if (removeNoneFromTailChoices()) {
					availableRaces.remove(Race.NONE);
				}
			} else if (currentPart instanceof AbstractWingType) {
				availableRaces = getTFRaces(Util.newArrayListOfValues(getTarget().getWingRace(), getTarget().getTorsoType().getRace()), isNonHumanHalfDemon(), false, false);
				if (removeNoneFromWingChoices()) {
					availableRaces.remove(Race.NONE);
				}
			} else {
				availableRaces = getTFRaces(currentPart.getType().getRace());
			}
			BodyPartInterface presetPart = currentPart;
			for (BodyPartInterface part : body.getAllBodyParts()) {
				if (currentPart.getType() == part.getType()) {
					presetPart = part;
					break;
				}
			}
			if (!availableRaces.contains(presetPart.getType().getRace())) {
				if (sb.length() == 0) {
					sb.append("[npc.NameIsFull] не может превратиться в соответствующую расу для следующих частей:");
					sb.append("<br/>");
				}
				partsList.add(presetPart.getType().getName(getTarget()));
			}
		}
		if(sb.length()>0) {
			sb.append(Util.capitaliseSentence(Util.stringsToStringList(partsList, false)));
			sb.append(".");
			return UtilText.parse(getTarget(), sb.toString());
		}

		if(getTarget().isYouko()) { // Youko self-TF limitations:
			if(body.getArm().getArmRows()!=getTarget().getArmRows()) {
				sb.append("<br/>Количество рядов рук");
			}
			if(body.getGenitalArrangement()!=getTarget().getGenitalArrangement()) {
				sb.append("<br/>Расположение гениталий");
			}
			if(body.getTentacle().getType()!=getTarget().getTentacleType()) {
				sb.append("<br/>Тип щупалец");
			}
			if(body.getWing().getType()!=getTarget().getWingType()) {
				sb.append("<br/>Тип крыльев");
			}
			if(body.getEye().getEyePairs()!=getTarget().getEyePairs()) {
				sb.append("<br/>Количество глаз");
			}
			if(body.getHorn().getType()!=getTarget().getHornType()) {
				sb.append("<br/>Тип рогов");
			}
			if(body.getAntenna().getType()!=getTarget().getAntennaType()) {
				sb.append("<br/>Тип антенн");
			}
			if(sb.length()>0) {
				sb.insert(0, "Ограниченные способности Юкос к трансформации не позволяют [npc.name] превратиться в:");
				return UtilText.parse(getTarget(), sb.toString());
			}
		}
		return "";
	}

	/**
	 * Sets the supplied body as the getTarget()'s new body.
	 * <br/>Retains all getTarget()'s covering colours which are not actively used by the new body, replacing the loaded body's unused covering colours.
	 * <br/>If the getTarget() does not have covering colours saved which are present in the loaded body, then these loaded body's covering colours are retained.
	 */
	public static void applyLoadedBody(Body body) {
		AbstractSubspecies subspeciesOverride = getTarget().getSubspeciesOverride();
		Map<AbstractBodyCoveringType, Covering> oldCoverings = getTarget().getBody().getCoverings();
		
		getTarget().setBody(body, false);
		
		getTarget().setSubspeciesOverride(subspeciesOverride);
		List<AbstractBodyCoveringType> currentlyActiveCoverings = new ArrayList<>();
		for(BodyPartInterface part : body.getAllBodyPartsWithAllOrifices()) {
			AbstractBodyCoveringType bct = getTarget().getCovering(part);
			if(bct==null) {
//				System.out.println(part.getName(getTarget()));
				continue;
			}
			if(BodyCoveringType.getAllMakeupTypes().contains(bct) || bct==BodyCoveringType.DILDO) {
				continue;
			}
			if(body.getBodyMaterial()!=BodyMaterial.FLESH) {
				currentlyActiveCoverings.add(BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), bct.getCategory()));
			}
			currentlyActiveCoverings.add(bct);
		}
		
		for(Entry<AbstractBodyCoveringType, Covering> entry: oldCoverings.entrySet()) {
			if(!currentlyActiveCoverings.contains(entry.getKey())) {
				getTarget().getBody().getCoverings().put(entry.getKey(), entry.getValue());
			}
		}
		
	}
}
