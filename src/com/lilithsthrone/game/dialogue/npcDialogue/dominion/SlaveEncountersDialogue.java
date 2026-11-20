package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEvent;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.9.2
 * @version 0.3.9.2
 * @author Innoxia
 */
public class SlaveEncountersDialogue {

	private static NPC slave;
	private static NPC characterForSex;

	private static NPC getSlave() {
		return slave;
	}
	
	public static DialogueNode getSlaveUsesYou(NPC targetedSlave) {
		slave = targetedSlave;
		Main.game.setActiveNPC(targetedSlave);
		targetedSlave.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_YOU;
	}

	public static DialogueNode getSlaveUsesYouDungeon(NPC targetedSlave) {
		slave = targetedSlave;
		Main.game.setActiveNPC(targetedSlave);
		targetedSlave.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_YOU_DUNGEON;
	}
	
	public static DialogueNode getSlaveUsesYouStreet(NPC targetedSlave) {
		slave = targetedSlave;
		Main.game.setActiveNPC(targetedSlave);
		targetedSlave.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_YOU_STREETS;
	}
	
	public static DialogueNode getSlaveUsesYouAlleyway(NPC targetedSlave) {
		slave = targetedSlave;
		Main.game.setActiveNPC(targetedSlave);
		targetedSlave.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_YOU_ALLEYWAY;
	}
	
	public static DialogueNode getSlaveUsingOtherSlaveAlleyway(Value<NPC, NPC> slaves) {
		slave = slaves.getKey();
		characterForSex = slaves.getValue();
		Main.game.setActiveNPC(slave);
		slave.setLocation(Main.game.getPlayer(), false);
		characterForSex.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_OTHER_SLAVE_ALLEYWAY;
	}
	
	public static DialogueNode getSlaveUsingOtherSlaveLilayaCorridor(Value<NPC, NPC> slaves) {
		slave = slaves.getKey();
		characterForSex = slaves.getValue();
		Main.game.setActiveNPC(slave);
		slave.setLocation(Main.game.getPlayer(), false);
		characterForSex.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR;
	}
	
	private static SMGeneric getSlaveOnSlaveSexManager(List<GameCharacter> doms, List<GameCharacter> subs, List<GameCharacter> spectators) {
		return new SMGeneric(
				doms,
				subs,
				null,
				spectators) {
			@Override
			public boolean isHidden(GameCharacter character) {
				return character.isPlayer() && Main.sex.isSpectator(character);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				GameCharacter fuckingTarget = 
						Main.sex.getOngoingCharactersUsingAreas(character, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).isEmpty()
							?null
							:Main.sex.getOngoingCharactersUsingAreas(character, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).iterator().next();
				if(character.isSlave() && fuckingTarget!=null) {
					if(!character.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE) || (fuckingTarget.isSlave() && !fuckingTarget.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED))) {
						return OrgasmBehaviour.PULL_OUT;
					}
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return Util.newHashMapOfValues(
						new Value<>(slave, Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.PENIS)),
						new Value<>(characterForSex, Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.PENIS)));
			}
		};
	}
	
	private static final DialogueNode SLAVE_USES_YOU = new DialogueNode("Засада", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean isRape = getSlave().isWillingToRape(Main.game.getPlayer()) && getSlave().hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST);
			if(index == 1) {
				return new ResponseSex("Секс",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						SLAVE_USES_YOU_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_SEX", slave));
				
			} else if(index == 2) {
				return new ResponseSex("Жажда секса",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_EAGER),
						SLAVE_USES_YOU_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_SEX_EAGER", slave));
				
			} else if(index == 3 && isRape) {
				return new ResponseSex("Сопротивляться сексу",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
						SLAVE_USES_YOU_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_SEX_RESIST", slave));
				
			} else if(index==5) {
				if(getSlave().isWillingToRape(Main.game.getPlayer()) && getSlave().hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)) {
					return new Response("Отказаться",
							UtilText.parse(getSlave(), "Раз уж ты дал(а) [npc.herHim] разрешение на изнасилование, [npc.nameIsFull] не примет отказ!"),
							null);
					
				} else {
					return new Response("Отказаться",
							UtilText.parse(getSlave(), "У тебя сейчас совсем нет настроения, так что решительно прикажи [npc.name] остановиться."),
							SLAVE_USES_YOU_REFUSED);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_YOU_POST_SEX = new DialogueNode("Использовано", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Теперь, когда [npc.sheHas] вдоволь на[npc.her], [npc.name] начинает отступать...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_POST_SEX", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_YOU_REFUSED = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_REFUSED", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode SLAVE_USES_YOU_DUNGEON = new DialogueNode("Засада", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean isRape = getSlave().isWillingToRape(Main.game.getPlayer()) && getSlave().hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST);
			if(index == 1) {
				return new ResponseSex("Секс",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						SLAVE_USES_YOU_DUNGEON_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_SEX", slave));
				
			} else if(index == 2) {
				return new ResponseSex("Жажда секса",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_EAGER),
						SLAVE_USES_YOU_DUNGEON_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_SEX_EAGER", slave));
				
			} else if(index == 3 && isRape) {
				return new ResponseSex("Сопротивляться сексу",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
						SLAVE_USES_YOU_DUNGEON_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_SEX_RESIST", slave));
				
			} else if(index==5) {
				if(getSlave().isWillingToRape(Main.game.getPlayer()) && getSlave().hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)) {
					return new Response("Отказаться",
							UtilText.parse(getSlave(), "Раз уж ты дал(а) [npc.herHim] разрешение на изнасилование, [npc.nameIsFull] не примет отказ!"),
							null);
					
				} else {
					return new Response("Отказаться",
							UtilText.parse(getSlave(), "У тебя сейчас совсем нет настроения, так что решительно прикажи [npc.name] остановиться."),
							SLAVE_USES_YOU_DUNGEON_REFUSED);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_YOU_DUNGEON_POST_SEX = new DialogueNode("Использовано", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Теперь, когда [npc.sheHas] вдоволь на[npc.her], [npc.name] начинает отступать...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_POST_SEX", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_YOU_DUNGEON_REFUSED = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_REFUSED", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode SLAVE_USES_YOU_STREETS = new DialogueNode("Засада", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean isRape = getSlave().isWillingToRape(Main.game.getPlayer()) && getSlave().hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST);
			if(index == 1) {
				return new ResponseSex("Секс",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						SLAVE_USES_YOU_POST_SEX_STREETS,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS_SEX", slave));
				
			} else if(index == 2) {
				return new ResponseSex("Жажда секса",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_EAGER),
						SLAVE_USES_YOU_POST_SEX_STREETS,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS_SEX_EAGER", slave));
				
			} else if(index == 3 && isRape) {
				return new ResponseSex("Сопротивляться сексу",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
						SLAVE_USES_YOU_POST_SEX_STREETS,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS_SEX_RESIST", slave));
				
			} else if(index==5) {
				if(getSlave().isWillingToRape(Main.game.getPlayer()) && getSlave().hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)) {
					return new Response("Отказаться",
							UtilText.parse(getSlave(), "Раз уж ты дал(а) [npc.herHim] разрешение на изнасилование, [npc.nameIsFull] не примет отказ!"),
							null);
					
				} else {
					return new Response("Отказаться",
							UtilText.parse(getSlave(), "У тебя сейчас совсем нет настроения, так что решительно прикажи [npc.name] остановиться."),
							SLAVE_USES_YOU_STREETS_REFUSED);
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode SLAVE_USES_YOU_POST_SEX_STREETS = new DialogueNode("Использовано", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Теперь, когда [npc.sheHas] вдоволь на[npc.her], [npc.name] начинает отступать...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_POST_SEX_STREETS", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_YOU_STREETS_REFUSED = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS_REFUSED", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode SLAVE_USES_YOU_ALLEYWAY = new DialogueNode("Засада", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			boolean isRape = getSlave().isWillingToRape(Main.game.getPlayer()) && getSlave().hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST);
			if(index == 1) {
				return new ResponseSex("Секс",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						SLAVE_USES_YOU_POST_SEX_ALLEYWAY,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY_SEX", slave));
				
			} else if(index == 2) {
				return new ResponseSex("Жажда секса",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_EAGER),
						SLAVE_USES_YOU_POST_SEX_ALLEYWAY,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY_SEX_EAGER", slave));
				
			} else if(index == 3 && isRape) {
				return new ResponseSex("Сопротивляться сексу",
						"[npc.Name] навязывает вам [npc.herself]...",
						!isRape, !isRape,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
						SLAVE_USES_YOU_POST_SEX_ALLEYWAY,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY_SEX_RESISTING", slave));
				
			} else if(index==5) {
				if(getSlave().isWillingToRape(Main.game.getPlayer()) && getSlave().hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)) {
					return new Response("Отказаться",
							UtilText.parse(getSlave(), "Раз уж ты дал(а) [npc.herHim] разрешение на изнасилование, [npc.nameIsFull] не примет отказ!"),
							null);
					
				} else {
					return new Response("Отказаться",
							UtilText.parse(getSlave(), "У тебя сейчас совсем нет настроения, так что решительно прикажи [npc.name] остановиться."),
							SLAVE_USES_YOU_REFUSED);
					
				}
			}
			return null;
		}
	};

	public static final DialogueNode SLAVE_USES_YOU_POST_SEX_ALLEYWAY = new DialogueNode("Использовано", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Теперь, когда [npc.sheHas] вдоволь на[npc.her], [npc.name] начинает отступать...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_POST_SEX_ALLEYWAY", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_YOU_ALLEYWAY_REFUSED = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY_REFUSED", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить свой путь.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	
	// Slaves having sex in alleyway:
	
	private static final DialogueNode SLAVE_USES_OTHER_SLAVE_ALLEYWAY = new DialogueNode("", "", true) {
		@Override
		public String getAuthor() {
			return "PoyntFury";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Игнорировать", "Тебе не очень-то и хочется выяснять, кто издает эти похотливые звуки.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = OccupancyUtil.applySlaveSexWithOtherSlave(Main.game.getDayNumber(), Main.game.getHourOfDay(), slave, characterForSex);
						if(event!=null) {
							Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_IGNORE", slave, characterForSex));
						slave.returnToHome();
						characterForSex.returnToHome();
					}
				};
				
			} else if(index == 2) {
				return new Response("Проверить", "Продолжить путь за следующий угол и выяснить, кто издает эти похотливые звуки.", SLAVE_USES_OTHER_SLAVE_ALLEYWAY_CHECK);
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_OTHER_SLAVE_ALLEYWAY_CHECK = new DialogueNode("", "", true, true) {
		@Override
		public String getAuthor() {
			return "PoyntFury";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_CHECK", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Присоединиться (дом)",
						UtilText.parse(slave, characterForSex, "Заявить о своем присутствии и взять под контроль трах [npc.name] и [npc2.name]."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(Main.game.getPlayer()), Util.newArrayListOfValues(slave, characterForSex), null),
						SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_START_SEX_DOM", slave, characterForSex));
				
			} else if(index == 2) {
				if(!slave.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Присоединиться (саб)", UtilText.parse(slave, "Так как [npc.name] к тебе не тянет, ты не можешь заставить [npc.herHim] доминировать над тобой..."), null);
				}
				return new ResponseSex("Присоединиться (саб)",
						UtilText.parse(slave, characterForSex, "Заявить о себе и подчиниться [npc.name], позволяя [npc.herHim] доминировать как над тобой, так и над [npc2.name]."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(slave), Util.newArrayListOfValues(Main.game.getPlayer(), characterForSex), null),
						SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_START_SEX_SUB", slave, characterForSex));
					
			} else if(index == 3) {
				return new ResponseSex("Смотреть",
						UtilText.parse(slave, characterForSex, "Остаться незамеченным за углом и наблюдать, как [npc.name] трахает [npc2.name]..."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(slave), Util.newArrayListOfValues(characterForSex), Util.newArrayListOfValues(Main.game.getPlayer())),
						SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_START_SEX_WATCHING", slave, characterForSex));
				
			} else if(index == 4) {
				return new Response("Уйти", "Оставить своих рабынь развлекаться вместе и продолжить свой путь...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = OccupancyUtil.applySlaveSexWithOtherSlave(Main.game.getDayNumber(), Main.game.getHourOfDay(), slave, characterForSex);
						if(event!=null) {
							Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_CHECK_LEAVE", slave, characterForSex));
						slave.returnToHome();
						characterForSex.returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Теперь, когда [npc.sheHas] наставила [npc.her] на путь наслаждения, [npc.name] [npc.steps] обратно...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = new SlaveryEventLogEntry(Main.game.getHourOfDay(),
									slave,
									Util.newArrayListOfValues(characterForSex.getId()),
									SlaveEvent.SLAVE_SEX,
									null,
									Util.newArrayListOfValues(UtilText.parse(slave,  characterForSex, "Ты застал [npc.name] за сексом с [npc2.name] в одном из переулков Dominion.")),
									true);
						Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						slave.returnToHome();
						characterForSex.returnToHome();
					}
				};
			}
			return null;
			
		}
	};
	
	
	// Slaves having sex in Lilaya's house corridor:
	
	private static void returnSlavesToHomeLilayasCorridor() {
		if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.CLEANING) {
			SlaveJob.CLEANING.sendToWorkLocation(slave);
		} else {
			slave.returnToHome();
		}
		if(characterForSex.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.CLEANING) {
			SlaveJob.CLEANING.sendToWorkLocation(characterForSex);
		} else {
			characterForSex.returnToHome();
		}
	}
	
	private static final DialogueNode SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR = new DialogueNode("", "", true) {
		@Override
		public String getAuthor() {
			return "PoyntFury";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Игнорировать", "Тебе не очень-то и хочется выяснять, кто издает эти похотливые звуки.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = OccupancyUtil.applySlaveSexWithOtherSlave(Main.game.getDayNumber(), Main.game.getHourOfDay(), slave, characterForSex);
						if(event!=null) {
							Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_IGNORE", slave, characterForSex));
						returnSlavesToHomeLilayasCorridor();
					}
				};
				
			} else if(index == 2) {
				return new Response("Проверить", "Подойти ближе и выяснить, кто издает эти похотливые звуки.", SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_CHECK);
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_CHECK = new DialogueNode("", "", true, true) {
		@Override
		public String getAuthor() {
			return "PoyntFury";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_CHECK", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Присоединиться (дом)",
						UtilText.parse(slave, characterForSex, "Заявить о своем присутствии и взять под контроль трах [npc.name] и [npc2.name]."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(Main.game.getPlayer()), Util.newArrayListOfValues(slave, characterForSex), null),
						SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_START_SEX_DOM", slave, characterForSex));
				
			} else if(index == 2) {
				if(!slave.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Присоединиться (саб)", UtilText.parse(slave, "Так как [npc.name] к тебе не тянет, ты не можешь заставить [npc.herHim] доминировать над тобой..."), null);
				}
				return new ResponseSex("Присоединиться (саб)",
						UtilText.parse(slave, characterForSex, "Заявить о себе и подчиниться [npc.name], позволяя [npc.herHim] доминировать как над тобой, так и над [npc2.name]."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(slave), Util.newArrayListOfValues(Main.game.getPlayer(), characterForSex), null),
						SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_START_SEX_SUB", slave, characterForSex));
					
			} else if(index == 3) {
				return new ResponseSex("Смотреть",
						UtilText.parse(slave, characterForSex, "Скрыться из виду и наблюдать, как [npc.name] трахает [npc2.name]..."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(slave), Util.newArrayListOfValues(characterForSex), Util.newArrayListOfValues(Main.game.getPlayer())),
						SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_START_SEX_WATCHING", slave, characterForSex));
				
			} else if(index == 4) {
				return new Response("Уйти", "Оставить своих рабынь развлекаться вместе и продолжить свой путь...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = OccupancyUtil.applySlaveSexWithOtherSlave(Main.game.getDayNumber(), Main.game.getHourOfDay(), slave, characterForSex);
						if(event!=null) {
							Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_CHECK_LEAVE", slave, characterForSex));
						returnSlavesToHomeLilayasCorridor();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Теперь, когда [npc.sheHas] наставила [npc.her] на путь наслаждения, [npc.name] [npc.steps] обратно...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Продолжить", "Продолжить путь...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = new SlaveryEventLogEntry(Main.game.getHourOfDay(),
									slave,
									Util.newArrayListOfValues(characterForSex.getId()),
									SlaveEvent.SLAVE_SEX,
									null,
									Util.newArrayListOfValues(UtilText.parse(slave,  characterForSex, "Вы застали [npc.name] за сексом с [npc2.name] в одном из коридоров дома Lilaya.")),
									true);
						Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						returnSlavesToHomeLilayasCorridor();
					}
				};
			}
			return null;
			
		}
	};
	
}
