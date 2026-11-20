package com.lilithsthrone.game.dialogue.npcDialogue.elemental;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class ElementalDialogue {
	
	private static Elemental getElemental() {
		return Main.game.getPlayer().getElemental();
	}

	public static final DialogueNode UTIL_NO_CONTENT = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELEMENTAL_START = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Взаимодействие";
			} else if(index==1) {
				return UtilText.parse("[style.colourCompanion(Управление)]");
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) { // Interactions:
				if(index==1) {
					if(!getElemental().isActive()) {
						return new Response("Осмотреть", "Чтобы осмотреть [el.herHim], вам нужно, чтобы [el.name] приняла [el.her] активную форму.", null);
					}
					return new Response("Осмотреть", "Внимательно осмотрите [el.name].", ELEMENTAL_INSPECT);
					
				} else if(index==2) {
					return new Response("Поговорить", "Поговорите с [el.name] какое-то время.", ELEMENTAL_TALK);
					
				} else if(index==3) {
					if(getElemental().isActive()) {
						return new Response("Питомец", "Чтобы погладить [el.herHim], [el.Name] должна находиться в [el.her] пассивной форме...", null);
					}
					return new Response("Питомец", "Хорошенько погладьте [el.name].", ELEMENTAL_PETTING);
					
				}
				// index==5 is transform toggle defined below
				if(index==6) {
					if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("Доминирующий секс", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("Доминирующий секс",
								"Так как у вас нет [style.colourFetish(\""+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" фетиша)], мысль о сексе с [el.name] в месте, где вас могут увидеть, кажется вам неуютной!",
								null);
						
					} else if(!getElemental().isActive()) {
						return new Response("Доминирующий секс", "Чтобы заняться сексом с [el.herHim], вам нужно, чтобы [el.name] приняла [el.her] активную форму.", null);
						
					} else if(getElemental().isSummonerServant() && !getElemental().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
						return new Response("Доминирующий секс",
								"Поскольку вы поклялись в верности школе [el.school], а у [el.name] нет фетиша \""+Fetish.FETISH_SUBMISSIVE.getName(getElemental())+"\", [el.name] не позволит вам доминантно трахать [el.herHim]...",
								null);
					}
					return new ResponseSex("Доминирующий секс",
							"Заняться [el.name] в доминантной позиции.",
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getElemental(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								public boolean isCharacterAbleToStopSex(GameCharacter character) {
									return character.isPlayer();
								}
							},
							null,
							null,
							ELEMENTAL_AFTER_SEX,
							UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START_SEX_DOMINANT"));
					
				} else if(index==7) {
					if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("Субмиссивный секс", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("Субмиссивный секс",
								"Так как у вас нет [style.colourFetish(\""+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" фетиша)], мысль о сексе с [el.name] в месте, где вас могут увидеть, кажется вам неуютной!",
								null);
						
					} else if(!getElemental().isActive()) {
						return new Response("Субмиссивный секс", "Чтобы заняться сексом с [el.herHim], вам нужно, чтобы [el.name] приняла [el.her] активную форму.", null);
					}
					return new ResponseSex("Субмиссивный секс",
							"Позволить [el.name] взять инициативу и заняться сексом с [el.herHim] в субмиссивной позиции.",
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getElemental(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))),
							null,
							null,
							ELEMENTAL_AFTER_SEX,
							UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START_SEX_SUBMISSIVE"));
					
				}
			
			} else if(responseTab==1) { // Management:
				if(index==1) {
					if(getElemental().isActive()) {
						return new Response("Пассивная трансформация", "Нужно, чтобы [el.name] приняла [el.her] пассивную форму, тогда [el.herHim] сможет изменить её на что-то другое...", null);
					}
					return new Response("Пассивная трансформация", "Спросить [el.name] о пассивной форме [el.her] и о том, сможет ли [el.she] изменить её на что-то другое...", ELEMENTAL_PASSIVE_FORM);
					
				} else if(index==2) {
					if(!getElemental().isActive()) {
						return new Response("Активная трансформация", "Нужно, чтобы [el.name] приняла [el.her] активную форму, тогда [el.herHim] сможет изменить её на что-то другое...", null);
					}
					return new Response("Активная трансформация",
							"Заставить [el.name] превратить [el.herself] в другую форму...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getElemental());
						}
					};
					
				} else if(index==3) {
					return new Response("Перки", "Назначить [el.namePos] очков перков.", ELEMENTAL_PERKS);
					
				} else if(index==4) {
					return new Response("Фетиши", "Используйте свою интимную психическую связь с [el.name], чтобы изменять фетиши [el.her] в зависимости от ваших собственных фетишей.", ELEMENTAL_FETISHES);
					
				}
				// index==5 is transform toggle defined below
				if(index==6) {
					return new Response("Боевые приемы", "Настроить приемы, которые [el.name] может выполнять в бою.", CombatMovesSetup.COMBAT_MOVES_CORE) {
						@Override
						public void effects() {
							CombatMovesSetup.setTarget(getElemental(), ELEMENTAL_START);
						}
					};
					
				} else if(index==7) {
					if(!getElemental().isActive()) {
						return new Response("Инвентарь", "Чтобы управлять [el.her] инвентарем, [el.name] должна принять [el.her] активную форму...", null);
					}
					return new ResponseEffectsOnly("Инвентарь", "Управлять [el.namePos] инвентарем.") {
						@Override
						public void effects() {
							Main.mainController.openInventory((NPC) getElemental(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if(index==8) {
					return new ResponseEffectsOnly("Самоочистка: "+(!getElemental().hasFlag(NPCFlagValue.elementalStayDirty)?"[style.colourGood(Вкл)]":"[style.colourBad(Выкл)]"),
							!getElemental().hasFlag(NPCFlagValue.elementalStayDirty)
								?"[el.Name] сейчас использует свои самотрансформирующие способности [el.her], чтобы очистить тело или одежду [el.her] от любых жидкостей.<br/>[style.colourMinorBad(Нажмите, чтобы выключить самоочистку.)]"
								:"[el.Name] сейчас использует [el.her] самотрансформирующие силы, чтобы очищать любую жидкость, пачкающую [el.her] тело или одежду.<br/>[style.colourMinorGood(Нажмите, чтобы включить самоочищение.)]") {
						@Override
						public void effects() {
							if(getElemental().hasFlag(NPCFlagValue.elementalStayDirty)) {
								getElemental().removeFlag(NPCFlagValue.elementalStayDirty);
							} else {
								getElemental().addFlag(NPCFlagValue.elementalStayDirty);
							}
							Main.game.updateResponses();
						}
					};
					
				} else if(index==9) {
					return new Response("Установить имена", "Изменить имя [el.namePos] или попросить [el.herHim] называть вас другим именем.", ELEMENTAL_CHOOSE_NAME);
					
				}
			}
			
			if(index==5) {
				if(getElemental().isActive()) {
					return new Response("Форма: <b style='color:"+getElemental().getCurrentSchool().getColour().toWebHexString()+";'>Активная</b>",
							"Приказать [el.name] вернуться в [el.her] пассивную форму.",
							UTIL_NO_CONTENT) {
						@Override
						public void effects() {
							getElemental().returnToHome();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TAKE_FORM_PASSIVE"));
						}
					};
					
				} else {
					return new Response("Форма: <i style='color:"+getElemental().getCurrentSchool().getColour().toWebHexString()+";'>Пассивная</i>",
							"Приказать [el.name] перейти в [el.her] активную форму.",
							UTIL_NO_CONTENT) {
						@Override
						public void effects() {
							getElemental().setLocation(Main.game.getPlayer(), false);
							UtilText.addSpecialParsingString(String.valueOf(Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TAKE_FORM_ACTIVE"));
						}
					};
				}
				
			} else if(index==10) {
				return new Response("Рассеять", "Рассеять [el.name]...",  Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getElemental().returnToHome();
						Main.game.getPlayer().setElementalSummoned(false);
					}
				};
			}
			
			if(index==0) {
				return new Response("Завершено", "Сказать [el.name], что разговор с [el.herHim] окончен, и позволить [el.herHim] вернуться в [el.her] пассивную форму.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						getElemental().returnToHome();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ELEMENTAL_AFTER_SEX = new DialogueNode("Завершено", "Насладившись [el.her] весельем с тобой, [el.name] решает закончить секс...", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_AFTER_SEX");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_INSPECT = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_INSPECT")
					+ getElemental().getCharacterInformationScreen(false);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_INSPECT) {
				return new Response("Осмотреть", "Ты уже вовсю разглядываешь [el.name].", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_TALK = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TALK");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_TALK) {
				return new Response("Поговорить", "Ты уже разговариваешь с [el.name].", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_PETTING = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PETTING");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_PETTING) {
				return new Response("Ласкать", "Ты уже ласкаешь [el.name]!", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELEMENTAL_PASSIVE_FORM = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Назад", "Решить не позволять [el.name] принять другую пассивную форму.", UTIL_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_BACK"));
					}
				};
			}
			
			List<Response> responses = new ArrayList<>();
			List<AbstractSubspecies> subspecies = new ArrayList<>();
			subspecies.addAll(Subspecies.getAllSubspecies());
			subspecies.removeIf(s -> !s.getRace().isFeralPartsAvailable());
			subspecies.removeIf(s -> s.getRace()==Race.DEMON || s.getRace()==Race.ELEMENTAL || s.getRace()==Race.SLIME);
//			subspecies.removeIf(s -> s.isNonBiped());
			subspecies.removeIf(s -> s==Subspecies.CENTAUR || s==Subspecies.PEGATAUR || s==Subspecies.ALITAUR || s==Subspecies.UNITAUR); // Centaurs are a special case, as we don't want centaur ferals
			
			if(getElemental().getPassiveForm()==null) {
				responses.add(new Response("Огонек", "[el.Name] уже принимает пассивную форму элементального огонька!", null));
			} else {
				responses.add(new Response("Огонек", "Приказать [el.name] принять пассивную форму элементального огонька.", UTIL_NO_CONTENT) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("wisp", true);
						getElemental().setPassiveForm(null);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_CHANGE"));
					}
				});
			}
			
			List<String> nameDuplicates = new ArrayList<>(); // Do not populate responses with duplicates. This should remove repeated instances of the same feral animal, e.g. "snake" 3 times from snake, lamia, and melusine
			
			for(AbstractSubspecies sub : subspecies) {
				String feralName = sub.getFeralName(getElemental().getBody());
				if(!nameDuplicates.contains(feralName)) {
					nameDuplicates.add(feralName);
					if(getElemental().getPassiveForm()==sub) {
						responses.add(new Response(Util.capitaliseSentence(feralName), "[el.Name] уже принимает пассивную форму маленького, дикого "+feralName+"!", null));
						
					} else {
						responses.add(new Response(Util.capitaliseSentence(feralName), "Приказать [el.name] принять пассивную форму маленького, дикого "+feralName+".", UTIL_NO_CONTENT) {
							@Override
							public void effects() {
								UtilText.addSpecialParsingString(UtilText.generateSingularDeterminer(feralName)+" "+feralName, true);
								UtilText.addSpecialParsingString(feralName, false);
								getElemental().setPassiveForm(sub);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_CHANGE"));
							}
						});
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode ELEMENTAL_PERKS = new DialogueNode("Древо перков [el.NamePos]", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(),
					"<details><summary>[style.boldPerk(Информация о Перках и Чертах)]</summary>[style.colourPerk(Перки)] (круглые иконки) дают постоянные бонусы к атрибутам [npc.namePos].<br/>[style.colourPerk(Черты)] (квадратные иконки) дают уникальные эффекты для [npc.name]. В отличие от перков, <b>черты не будут влиять на [npc.name], пока их не вставят в панель [npc.her] «Активные черты»</b>.<br/>Для разблокировки перков требуются очки перков. [npc.Name] получает одно очко перка каждый раз, когда [npc.she] повышает уровень, и получает дополнительные два очка перка каждые пять уровней.<br/><br/>В дополнение к перкам, которые можно купить за очки перков, существуют также несколько специальных скрытых перков, которые открываются через особые события.</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(getElemental(), true));
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_PERKS) {
				return new Response("Перки", "Вы уже распределяете очки перков [el.namePos]!", null);
			}
			if(responseTab==1 && index==11) {
				return new Response("Сбросить перки", "Сбросить все перки и черты, вернув все потраченные очки. (Это временная мера, так как древо перков всё еще находится в разработке.)", ELEMENTAL_PERKS) {
					@Override
					public void effects() {
						getElemental().resetPerksMap(false, false);
					}
				};
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_FETISHES = new DialogueNode("Фетиши [el.NamePos]", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb = new StringBuilder(
					"<details><summary>[style.boldFetish(Информация о фетишах)]</summary>Вы можете выбрать [el.namePos] [style.colourLust(желание)] для каждого фетиша [style.colourArcane(бесплатно)], либо выбрать соответствующий [style.colourFetish(фетиш)] за [style.colourArcane(арканные эссенции)].<br/><br/>Выбор желания повлияет на бонусный прирост похоти во время секса, в то время как получение фетиша навсегда закрепит [el.namePos] желание как «любовь», а также даст [el.herHim] особые бонусы. Фетиши можно убрать только с помощью зачарованных зелий.<br/><br/>[el.NamePos] текущее выбранное желание имеет "+PresetColour.FETISH.getName()+" границы, но [el.her] истинное желание (обозначенное цветной иконкой желания) может изменяться зачарованной одеждой или другими предметами.<br/><br/>[el.name] будет получать опыт для каждого фетиша, совершая соответствующие действия во время секса. Опыт начисляется независимо от того, есть ли у [el.she] соответствующий фетиш. Фетиши более высокого уровня заставляют как [el.name], так и [el.her] партнера получать больше возбуждения от соответствующих сексуальных действий, а также увеличивают бонусы самого фетиша.<br/><br/>Наконец, производные фетиши нельзя разблокировать напрямую — они применяются автоматически, когда [el.name] соответствует их требованиям.</details>");
			
			// Normal fetishes:

			sb.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>Фетиши</h6></div>");
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_PENIS_GIVING, Fetish.FETISH_PENIS_RECEIVING));
			if(Main.game.isAnalContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF));
			if(Main.game.isLactationContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			if(Main.game.isFootContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING));
			}
			if(Main.game.isArmpitContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ARMPIT_GIVING, Fetish.FETISH_ARMPIT_RECEIVING));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_KINK_GIVING, Fetish.FETISH_KINK_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_SADIST, Fetish.FETISH_MASOCHIST));
			if(Main.game.isNonConEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_NON_CON_SUB));
			}

			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BONDAGE_APPLIER, Fetish.FETISH_BONDAGE_VICTIM));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DENIAL, Fetish.FETISH_DENIAL_SELF));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_VOYEURIST, Fetish.FETISH_EXHIBITIONIST));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BIMBO, Fetish.FETISH_CROSS_DRESSER));
			if(Main.game.isIncestEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, Fetish.FETISH_INCEST));
				if(Main.game.isPenetrationLimitationsEnabled()) {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_SIZE_QUEEN, null));
				}
			} else {
				if(Main.game.isPenetrationLimitationsEnabled()) {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, Fetish.FETISH_SIZE_QUEEN));
				} else {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, null));
				}
			}
			
			// Derived fetishes:

			sb.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Производные фетиши</h6></div>");
			sb.append("<div class='fetish-container'>");
			
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					sb.append(
							"<div id='FETISH_" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " есть' style='border:2px solid " + PresetColour.FETISH.getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " разблокирован' style='border:2px solid " +  PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " заблокирован' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString(Main.game.getPlayer())+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
									? ""
									: (fetish.isAvailable(Main.game.getPlayer())
											? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.5); border-radius:5px;'></div>"
											: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.7); border-radius:5px;'></div>"))
							+ "</div>");
				}
			}
			
			sb.append("</div>");
			
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_FETISHES) {
				return new Response("Фетиши", "Вы уже назначаете [el.namePos] фетишей!", null);
			}
			//TODO
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_CHOOSE_NAME = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(), 
				"<p>В данный момент [npc.nameIsFull] называет тебя «[npc.pcName]», и ты гадаешь, стоит ли попросить [npc.herHim] называть тебя как-то иначе или использовать другой титул. Будучи [npc.sheIs], ты также могла бы изменить [npc.her] имя на любое другое, какое пожелаешь...</p>"));
			
			UtilText.nodeContentSB.append(
				"<div class='container-full-width' style='padding:8px 16px;'><div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Имя</div><div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>Фамилия</div><div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
						+ UtilText.parse(getElemental(), "Как тебя называет [npc.she]")
					+ "</div>"
					
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(getElemental().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+getElemental().getId()+"_RENAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button' id='"+getElemental().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput' value='"+ UtilText.parseForHTMLDisplay(getElemental().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+getElemental().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div><div class='normal-button' id='"+getElemental().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>&#127922;</div><form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(getElemental().getPetName(Main.game.getPlayer()))
						+ "' style='width:100%; margin:0; padding:0;'></form><div class='normal-button' id='"+getElemental().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>&#10003;</div> <div class='normal-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:12%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>Все рабыни</div>");
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(), 
						"<p style='text-align:center; margin-top:4px;'><i>Если сказать [npc.name] называть вас «Мамой» или «Папой», «Мамочкой» или «Папочкой», «Госпожой» или «Господином», либо «Мадам» или «Сэр», то [npc.she] автоматически переключится на соответствующее парное имя в зависимости от пола вашего персонажа.</i></p></div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_CHOOSE_NAME) {
				return new Response("Установить имена", "Вы уже говорите [el.name], какие имена должны использовать [el.she]!", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
}
