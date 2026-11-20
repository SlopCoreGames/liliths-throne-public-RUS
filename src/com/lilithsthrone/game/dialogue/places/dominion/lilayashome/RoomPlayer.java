package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMBath;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMShower;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RoomPlayer {
	
	private static int sleepTimeInMinutes = 240;

    private static GameCharacter makeupTarget;
    
    private static List<GameCharacter> slavesWashing;
    
    public static GameCharacter getMakeupTarget() {
        if(makeupTarget==null) {
            return Main.game.getPlayer();
        }
        return makeupTarget;
    }
    
    /**
     * @param sleepTimeInMinutes Calls an endTurn(sleepTimeInMinutes*60) so that NPCs have their status effects updated before the next scene is parsed.
     */
	public static void applySleep(int sleepTimeInMinutes) {
		List<GameCharacter> charactersPresent = new ArrayList<>(LilayaHomeGeneric.getSlavesAndOccupantsPresent());
		charactersPresent.addAll(Main.game.getPlayer().getCompanions());
		charactersPresent.add(Main.game.getPlayer());

		for(GameCharacter character : charactersPresent) {
			character.applySleep(sleepTimeInMinutes);
		}
		
		slavesPresentWhenGoingToSleep = slavesInRoom(Main.game.getHourOfDay());
//		Main.game.getTextStartStringBuilder().append("X: "+Main.game.getHourOfDay());

		Main.game.getPlayer().setActive(false);
		Main.game.endTurn(sleepTimeInMinutes*60);
		Main.game.getPlayer().setActive(true);
		Main.game.endTurnTimeTakenAddition = Main.game.endTurnTimeTaken;

		slavesPresentWhenWaking = slavesInRoom(Main.game.getHourOfDay());
		slavesToWakePlayer = slavesInRoom(Main.game.getHourOfDay()).stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_WAKE_UP)).collect(Collectors.toList());
	}
	
	private static Response getResponseRoom(int responseTab, int index) {
		if(responseTab==1) {
			return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			
		} else if(responseTab==0) {
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("Отдохнуть (1 час)",
						"Отдохнуть час. А также восстановить ваши "+Attribute.HEALTH_MAXIMUM.getName()+" и "+Attribute.MANA_MAXIMUM.getName()+", вы также получите статус «Выспавшийся».",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60;
						applySleep(sleepTimeInMinutes);
					}
				};

			} else if (index == 2) {
				return new Response("Отдохнуть (4 часа)",
						"Отдохнуть четыре часа. А также восстановить ваши "+Attribute.HEALTH_MAXIMUM.getName()+" и "+Attribute.MANA_MAXIMUM.getName()+", вы также получите статус «Выспавшийся».",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60 * 4;
						applySleep(sleepTimeInMinutes);
					}
				};

            } else if (index == 3) {
                return new Response("Отдохнуть (8 часов)",
                        "Отдохнуть восемь часов. А также восстановить ваши "+Attribute.HEALTH_MAXIMUM.getName()+" и "+Attribute.MANA_MAXIMUM.getName()+", вы также получите статус «Выспавшийся».",
                        AUNT_HOME_PLAYERS_ROOM_SLEEP){
                    @Override
                    public void effects() {
                        sleepTimeInMinutes = 60 * 8;
                        applySleep(sleepTimeInMinutes);
                    }
                };

            } else if (index == 4) {
				return new Response("Отдохнуть (12 часов)",
						"Отдохнуть двенадцать часов. А также восстановить ваши "+Attribute.HEALTH_MAXIMUM.getName()+" и "+Attribute.MANA_MAXIMUM.getName()+", вы также получите статус «Выспавшийся».",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60 * 12;
						applySleep(sleepTimeInMinutes);
					}
				};
	
			} else if (index == 5) {
				int timeUntilChange = Main.game.getMinutesUntilNextMorningOrEvening() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always sleeping to sunset/sunrise
				LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
				return new Response("Отдыхать до " + (Main.game.isDayTime() ? "Заката" : "Рассвета"),
						"Отдохнуть на " + (timeUntilChange >= 60 ?timeUntilChange / 60 + " часов " : " ")
							+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + " минут" : "")
							+ (Main.game.isDayTime()
									? " до пяти минут после заката ("+Units.time(sunriseSunset[1].plusMinutes(5))+")."
									: " до пяти минут после рассвета ("+Units.time(sunriseSunset[0].plusMinutes(5))+").")
							+ " А также восполнение вашего "+Attribute.HEALTH_MAXIMUM.getName()+" и "+Attribute.MANA_MAXIMUM.getName()+", вы также получите статус «Выспавшийся».",
							AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = timeUntilChange;
						applySleep(sleepTimeInMinutes);
					}
				};
				
			} else if (index == 6) {
				return new Response("Управлять комнатой", "Перейти к экрану управления этой конкретной комнатой.", OccupantManagementDialogue.ROOM_UPGRADES) {
					@Override
					public void effects() {
						OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
					}
				};
				
			}  else if (index == 7) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Управлять людьми", "Перейти к экрану управления вашими рабынями и дружелюбными жильцами.", ROOM) {
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null);
						}
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Управлять людьми", "Чтобы получить доступ к этому меню, вам нужна лицензия работорговца или разрешение от Lilaya, чтобы поселить здесь своих друзей или кукол!",  null);
				}
				
			} else if (index == 8) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
					return new Response("Календарь", "Еще раз взглянуть на зачарованный календарь, прикрепленный к стене.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				} else {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>Календарь</span>", "На стене висит календарь. Присмотреться к нему повнимательнее.", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				}
				
			} else if (index == 9) {
				return new Response("Установить будильник", "Установи будильник на телефоне, чтобы проснуться в определенное время.", RoomPlayer.ROOM_SET_ALARM) {
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
					}
				};

			} else if (index == 10) {
				long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
				if(alarmTime >= 0) {
					String alarmTimeStr = Main.game.getDisplayTime(LocalTime.ofSecondOfDay(alarmTime*60));
					int timeUntilAlarm = Main.game.getMinutesUntilTimeInMinutes((int)alarmTime);
					
					return new Response("Отдыхать до будильника (" + alarmTimeStr + ")",
							"Отдохнуть на "
									+ (timeUntilAlarm==0
										?"24 часа"
										:((timeUntilAlarm >= 60 ? timeUntilAlarm / 60 + " ч, " : "")
												+ (timeUntilAlarm % 60 != 0 ? timeUntilAlarm % 60 + " мин, " : "")))
									+ " пока не прозвенит будильник. А также восстановит ваши " + Attribute.HEALTH_MAXIMUM.getName() + " и " + Attribute.MANA_MAXIMUM.getName() + ", вы также получите статус «Выспавшийся».",
							AUNT_HOME_PLAYERS_ROOM_SLEEP) {
						@Override
						public void effects() {
							sleepTimeInMinutes = timeUntilAlarm==0?24*60:timeUntilAlarm;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};
				} else {
					return new Response("Отдыхать до будильника (не установлен)", "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>Будильник не установлен!</span>", null);
				}
			}
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			
			int indexPresentStart = 11;
			if(index-indexPresentStart<charactersPresent.size() && index-indexPresentStart>=0) {
				NPC character = charactersPresent.get(index-indexPresentStart);
				return LilayaHomeGeneric.interactWithNPC(character);
			}
			
		} else if(responseTab==2) {
			if (index == 1) {
				return new Response("Быстрый душ",
						"Воспользоваться ванной комнатой, чтобы быстро принять душ.<br/>[style.italicsGood(Очистит <b>максимум "+Units.fluid(500)+"</b> жидкостей из всех отверстий.)]<br/>[style.italicsGood(Это очистит <b>только</b> твою текущую одежду.)]",
//								+ "<br/>[style.italicsMinorBad(This does <b>not</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER){
					@Override
					public void effects() {
						List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 120+30);
						}

						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>Вы оставляете одежду снаружи ванной комнаты, чтобы она очистилась, пока вы моетесь...</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(false, false, null, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 10*60;
					}
				};
				
			} else if (index == 2) {
				return new Response("Тщательный душ",
						"Воспользоваться ванной комнатой, чтобы принять душ и тщательно привести себя в порядок.<br/>[style.italicsExcellent(Это вымоет <b>все</b> жидкости из всех ваших отверстий.)]<br/>[style.italicsGood(Это очистит <b>только</b> вашу текущую одежду.)]",
//								+ "<br/>[style.italicsMinorBad(This does <b>not</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER){
					@Override
					public void effects() {
						List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
						}
						
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>Вы оставляете одежду снаружи ванной комнаты, чтобы она очистилась, пока вы моетесь...</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SHOWER, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
				};
				
			} else if(index==3) {
				return new Response("Время купания",
						"Воспользоваться ванной комнатой, чтобы принять ванну и тщательно привести себя в порядок.<br/>[style.italicsExcellent(Это вымоет <b>все</b> жидкости из всех ваших отверстий.)]<br/>[style.italicsExcellent(Это очистит <b>всю</b> одежду в вашем Инвентаре.)]",
//								+ "<br/>[style.italicsMinorGood(This <b>does</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_BATH){
					@Override
					public void effects() {
						List<GameCharacter> charactersPresent = new ArrayList<>(LilayaHomeGeneric.getSlavesAndOccupantsPresent());
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
						}
						
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>Вы оставляете одежду снаружи ванной комнаты, чтобы она очистилась, пока вы моетесь...</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
				};
				
			} else if(index==11) {
				return new ResponseEffectsOnly(
						UtilText.parse(getMakeupTarget(), "Цель: <b style='color:"+getMakeupTarget().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
						"Сменить персонажа, которому будет нанесен макияж.") {
					@Override
					public void effects() {
						List<GameCharacter> companions = Util.newArrayListOfValues(Main.game.getPlayer());
						companions.addAll(Main.game.getCharactersPresent());
//						companions.removeIf((c) -> !c.isPlayer() && (!c.isSlave() || !c.getOwner().isPlayer()));
						if(!companions.isEmpty()) {
							for(int i=0; i<companions.size();i++) {
								if(companions.get(i).equals(getMakeupTarget())) {
									if(i==companions.size()-1) {
										makeupTarget = companions.get(0);
										break;
										
									} else {
										makeupTarget = companions.get(i+1);
										break;
									}
								}
							}
						}
						Main.game.updateResponses();
					}
				};
				
			} else if(index==12) {
				return new Response("Прическа и макияж",
						UtilText.parse(getMakeupTarget(), "В одном из шкафчиков вашей ванной комнаты спрятан впечатляющий набор косметики и инструментов для укладки волос. Если захотите, вы могли бы потратить время на улучшение [npc.namePos] внешности..."),
						AUNT_HOME_PLAYERS_ROOM_MAKEUP){
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
				};
				
			}
		}
		return null;
	}
	
	private static String getShowerSlavesDescription(List<GameCharacter> slavesWashing) {
		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing,
						"Ваша рабыня, которой было приказано помогать вам во время мытья, "+Util.stringsToStringList(names, false)+", так же оставляет [npc.her] одежду у двери, прежде чем последовать за тобой в ванную."));
					sb.append(UtilText.parse(slavesWashing,
							" К счастью, твой роскошный душ достаточно просторный, чтобы [npc.name] могла вполне комфортно разместиться рядом с тобой."));
				
			} else {
				sb.append("Получив указание помочь тебе вымыться, твои рабыни, "+Util.stringsToStringList(names, false)+", так же оставляют свою одежду у двери, прежде чем последовать за тобой в ванную.");
					sb.append(UtilText.parse(slavesWashing,
							" К счастью, твой роскошный душ достаточно просторный, чтобы твои рабыни могли вполне комфортно разместиться рядом с тобой."));
			}
		sb.append("</p>");
		
		// Slave reactions while helping wash:
		
		List<GameCharacter> washingNice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		boolean firstWashing = true;
		for(GameCharacter npc : washingNice) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
			if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
				if(firstWashing) {
					if(Main.game.getPlayer().hasHair()) {
						start.add("Включая краны, [npc.name] послушно принимается помогать тебе мыть твои [pc.hair(true)] и тело. Повысив [npc.her] голос, чтобы он был слышен сквозь шум льющейся воды, [npc.she] говорит,");
					} else {
						start.add("Включая краны, [npc.name] послушно принимается помогать тебе мыть твое тело. Повысив [npc.her] голос, чтобы он был слышен сквозь шум льющейся воды, [npc.she] говорит,");
					}
				} else {
					start.add("Взяв кусок мыла, [npc.name] помогает [npc.her] своей подруге "+(washingNice.size()>2?"slaves":"slave")+" очистить твое тело. Пока [npc.she] принимается за [npc.her] задачу, [npc.she] говорит,");
					start.add("Сделав шаг вперед с куском мыла в [npc.hand], [npc.name] принимается очищать твое тело, приговаривая, пока [npc.she] это делает,");
					start.add("[npc.her] подходит к тебе сбоку, "+(washingNice.size()>2?"slaves":"slave")+"[npc.name] начинает омывать твое тело, приговаривая:");
				}
				speech.add("[npc.speech(Надеюсь, тебе понравится, [pc.name].)]");
				speech.add("[npc.speech(Пожалуйста, дай мне знать, если хочешь, чтобы я делала что-то иначе, [pc.name].)]");
				speech.add("[npc.speech(Я постараюсь очень тщательно тебя отмыть, [pc.name].)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("Включив воду, [npc.name] подходит вплотную к тебе со спины, намыливает твою спину и начинает тебя чистить. Внезапно [npc.she] делает шаг вперед и, прижимая [npc.her] [npc.breasts+] к твоей спине, [npc.she] соблазнительно [npc.moans],");
					} else {
						start.add("Включив воду, [npc.name] подходит вплотную к тебе со спины, намыливает твою спину и начинает тебя чистить. Внезапно [npc.she] делает шаг вперед и, прижимая [npc.herself] к твоей спине, [npc.she] соблазнительно [npc.moans],");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("Сделав шаг вперед, [npc.name] прижимает [npc.her] [npc.breasts+] к твоей спине, а затем соблазнительно [npc.moaning],");
						start.add("Намыливая твою спину куском мыла, [npc.name] внезапно делает шаг вперед, прижимая [npc.her] [npc.breasts+] к тебе, пока [npc.she] соблазнительно [npc.moans],");
						start.add("Решив начать с твоей спины, [npc.name] обходит тебя сзади, наклоняется вперед и прижимает [npc.her] [npc.breasts+] к тебе, [npc.moaning],");
					} else {
						start.add("Сделав шаг вперед, [npc.name] прижимается [npc.herself] к твоей спине, а затем соблазнительно [npc.moaning],");
						start.add("Намыливая твою спину, [npc.name] внезапно делает шаг вперед, прижимая [npc.herself] к тебе, пока [npc.she] соблазнительно [npc.moans],");
						start.add("Решив начать с чистки твоей спины, [npc.name] обходит тебя сзади, после чего наклоняется вперед и прижимается [npc.herself] к тебе, [npc.moaning],");
					}
				}
				speech.add("[npc.speech(Ты ведь не против, если я подойду так близко?)]");
				speech.add("[npc.speech(Тебе ведь нравится это ощущение, когда я так близко, правда?)]");
				speech.add("[npc.speech(Вот так, расслабься и позволь мне позаботиться о тебе...)]");
				speech.add("[npc.speech(Возможно, когда ты отмоешься, ты захочешь заняться со мной чем-нибудь грязным...)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("Включив воду, [npc.name] подходит вплотную к тебе со спины, а затем тут же прижимает [npc.her] [npc.breasts+] к твоей спине. Очевидно видя в этом возможность повеселиться с тобой, [npc.she] обхватывает тебя руками, начиная ласкать твое тело и соблазнительно [npc.moaning] в твою [pc.ear], пока [npc.she] делает это,");
					} else {
						start.add("Включив воду, [npc.name] подходит вплотную к тебе со спины, а затем тут же прижимает свое тело [npc.her] к твоей спине. Очевидно видя в этом возможность повеселиться с тобой, [npc.she] обхватывает тебя руками, начиная ласкать твое тело и соблазнительно [npc.moaning] в твою [pc.ear], пока [npc.she] делает это,");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("Сделав шаг вперед, [npc.name] прижимает [npc.her] [npc.breasts+] к твоей спине, а затем соблазнительно [npc.moaning],");
						start.add("Намыливая твою спину куском мыла, [npc.name] внезапно делает шаг вперед, прижимая [npc.her] [npc.breasts+] к тебе, пока [npc.she] соблазнительно [npc.moans],");
						start.add("Решив начать с твоей спины, [npc.name] обходит тебя сзади, наклоняется вперед и прижимает [npc.her] [npc.breasts+] к тебе, [npc.moaning],");
					} else {
						start.add("Сделав шаг вперед, [npc.name] прижимается [npc.herself] к твоей спине, а затем соблазнительно [npc.moaning],");
						start.add("Намыливая твою спину, [npc.name] внезапно делает шаг вперед, прижимая [npc.herself] к тебе, пока [npc.she] соблазнительно [npc.moans],");
						start.add("Решив начать с чистки твоей спины, [npc.name] обходит тебя сзади, после чего наклоняется вперед и прижимается [npc.herself] к тебе, [npc.moaning],");
					}
				}
				speech.add("[npc.speech(О да, тебе ведь нравится, когда я так тебя ласкаю, правда?)]");
				speech.add("[npc.speech(От такого тесного контакта меня так и штырит... Хочешь трахнуться после этого?)]");
				speech.add("[npc.speech(Меня так возбуждает... Ты ведь не против хорошенько трахнуться после этого, а?)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)
					|| npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
				if(npc.isShy()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Включив краны, [npc.name] нервно шагает вперед, а затем принимается помогать тебе мыть [pc.hair(true)] и тело. Стараясь [npc.her] изо всех сил, чтобы тебя было слышно сквозь шум льющейся воды, [npc.she] немного повышает [npc.her] голос и говорит:");
						} else {
							start.add("Включив краны, [npc.name] нервно шагает вперед, а затем принимается помогать тебе мыть тело. Стараясь [npc.her] изо всех сил, чтобы тебя было слышно сквозь шум льющейся воды, [npc.she] немного повышает [npc.her] голос и говорит:");
						}
					} else {
						start.add("Сжимая в руке кусок мыла, [npc.name] нервно подается вперед, чтобы помочь [npc.her] соратнику "+(washingNice.size()>2?"slaves":"slave")+" отмыться. Немного повысив [npc.her] голос, [npc.she] говорит:");
						start.add("Сделав шаг вперед с куском мыла в [npc.hand], [npc.name] нервно принимается отмывать твое тело. Перекрикивая шум льющейся воды, [npc.she] говорит:");
						start.add("[npc.her] подходит к тебе сбоку, "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] краснеет, когда [npc.she] начинает отмывать твое тело, приговаривая:");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(Мне так нравится быть так близко к тебе, [pc.name]!)]");
						speech.add("[npc.speech(Я так счастлива, что могу вот так тебе помогать...)]");
						speech.add("[npc.speech(Меня так радует возможность помогать тебе вот так...)]");
					} else {
						speech.add("[npc.speech(Я ведь всё правильно делаю, [pc.name]?)]");
						speech.add("[npc.speech(Просто скажи, если я буду делать что-то не так...)]");
						speech.add("[npc.speech(Надеюсь, ты именно так и хотел, чтобы я тебя отмыла...)]");
					}
					
				} else if(npc.isKind()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Включив краны, [npc.name] радостно шагает вперед, а затем нежно принимается помогать тебе мыть [pc.hair(true)] и тело. Стараясь [npc.her] изо всех сил, чтобы тебя было слышно сквозь шум льющейся воды, [npc.she] повышает [npc.her] голос и говорит:");
						} else {
							start.add("Включив краны, [npc.name] радостно шагает вперед, а затем нежно принимается помогать тебе мыть тело. Стараясь [npc.her] изо всех сил, чтобы тебя было слышно сквозь шум льющейся воды, [npc.she] повышает [npc.her] голос и говорит:");
						}
					} else {
						start.add("Сжимая в руке кусок мыла, [npc.name] счастливо шагает вперед, чтобы помочь своему товарищу [npc.her] "+(washingNice.size()>2?"slaves":"slave")+" отмыться. Повысив [npc.her] голос, [npc.she] говорит:");
						start.add("Сделав шаг вперед с куском мыла в [npc.hand], [npc.name] с радостью принимается за очищение твоего тела. Повысив [npc.her] голос над шумом льющейся воды, [npc.she] говорит:");
						start.add("[npc.her] подходит к тебе сбоку, "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] улыбается, пока [npc.she] начинает отмывать твое тело, говоря,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(Мне так нравится быть так близко к тебе, [pc.name]. Пожалуйста, скажи мне, если захочешь, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Я так счастлива, что могу вот так помогать тебе; мне так повезло, что ты мой хозяин.)]");
						speech.add("[npc.speech(Позволь мне позаботиться о тебе, [pc.name]; я хочу показать тебе, как много ты для меня значишь.)]");
					} else {
						speech.add("[npc.speech(Дай мне знать, если нужно будет что-то изменить, хорошо, [pc.name]?)]");
						speech.add("[npc.speech(Пожалуйста, скажи мне, если захочешь, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Позволь мне позаботиться о тебе, [pc.name].)]");
					}
					
				} else {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Включив краны, [npc.name] шагает вперед, а затем принимается помогать тебе мыть [pc.hair(true)] и тело. Стараясь [npc.her] изо всех сил, чтобы тебя было слышно сквозь шум льющейся воды, [npc.she] повышает [npc.her] голос и говорит:");
						} else {
							start.add("Включив краны, [npc.name] шагает вперед, а затем принимается помогать тебе мыть тело. Стараясь [npc.her] изо всех сил, чтобы тебя было слышно сквозь шум льющейся воды, [npc.she] повышает [npc.her] голос и говорит:");
						}
					} else {
						start.add("Сжимая в руке кусок мыла, [npc.name] делает шаг вперед, чтобы помочь [npc.her] своему "+(washingNice.size()>2?"slaves":"slave")+" отмыться. Повысив [npc.her] голос, [npc.she] говорит:");
						start.add("Сделав шаг вперед с куском мыла в [npc.hand], [npc.name] принимается отмывать твое тело. Возвышая [npc.her] голос над шумом текущей воды, [npc.she] говорит:");
						start.add("[npc.her] подходит к тебе сбоку, "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] улыбается, пока [npc.she] начинает отмывать твое тело, говоря,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(Мне так нравится быть так близко к тебе, [pc.name]. Пожалуйста, скажи мне, если захочешь, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Я так счастлива, что могу вот так помогать тебе; мне так повезло, что ты мой хозяин.)]");
						speech.add("[npc.speech(Позволь мне позаботиться о тебе, [pc.name]; я хочу показать тебе, как много ты для меня значишь.)]");
					} else {
						speech.add("[npc.speech(Дай знать, если хочешь, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Просто скажи, если нужно, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Позволь мне позаботиться о тебе, [pc.name].)]");
					}
				}
			}
			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append(" ");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		List<GameCharacter> washingRude = new ArrayList<>(slavesWashing);
		washingRude.removeAll(washingNice);
		for(GameCharacter npc : washingRude) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
				if(npc.isShy()) {
					if(firstWashing) {
						start.add("Неохотно открыв краны, [npc.name] делает шаг вперед, а затем вяло принимается помогать тебе мыться. Пытаясь заглушить шум льющейся воды своими комментариями, [npc.she] бормочет, [npc.her],");
					} else {
						start.add("Неохотно сделав шаг вперед, [npc.name] устремляет [npc.her] взгляд в пол, пока [npc.she] вяло помогает тебе мыться. Тяжело вздохнув, [npc.she] бормочет:");
						start.add("Глядя в пол, [npc.name] делает шаг вперед и неохотно начинает помогать тебе мыться. Раздраженно вздохнув, [npc.she] бормочет себе под [npc.her] нос:");
						start.add("Очевидно не в восторге, [npc.name], тем не менее, делает шаг вперед и вяло начинает помогать тебе мыться. Бормоча под [npc.her] нос, [npc.she] вздыхает:");
					}
					speech.add("[npc.speech(Ненавижу это...)]");
					speech.add("[npc.speech(Как же я вообще докатилась до того, что приходится делать такое...)]");
					speech.add("[npc.speech(Отстой какой-то...)]");
					
				} else if(npc.isSelfish()) {
					if(firstWashing) {
						start.add("Неохотно открыв краны, [npc.name] делает шаг вперед, а затем вяло принимается помогать тебе мыться. С явным оттенком враждебности в [npc.her] голосе, [npc.she] ворчит,");
					} else {
						start.add("Неохотно сделав шаг вперед, [npc.name] вяло принимается помогать тебе мыться. Тяжело вздохнув, [npc.she] жалуется,");
						start.add("Закатив [npc.her] [npc.eyes], [npc.name] делает шаг вперед и неохотно начинает помогать тебе мыться, при этом [npc.she] постоянно ворчит,");
						start.add("Раздраженно вздохнув, [npc.name] делает шаг вперед и вяло принимается помогать тебе мыться. Даже не пытаясь скрыть [npc.her] раздражение, [npc.she] гневно говорит,");
					}
					speech.add("[npc.speech(Я прямо ненавижу это делать, понимаешь? Просто возьми в следующий раз другого раба, чтобы он тебе помог!)]");
					speech.add("[npc.speech(Ну почему ты постоянно заставляешь меня это делать?! Разве не ясно, что я ненавижу помогать тебе вот так?!)]");
					speech.add("[npc.speech(Это просто гребаный отстой. Как насчет того, чтобы в следующий раз тебя помыл какой-нибудь другой раб?)]");
					
				} else {
					if(firstWashing) {
						start.add("Неохотно открыв краны, [npc.name] делает шаг вперед, а затем вяло принимается помогать тебе мыться. Явственно выражая недовольство, [npc.she] вздыхает,");
					} else {
						start.add("Неохотно сделав шаг вперед, [npc.name] вяло принимается помогать тебе мыться. Тяжело вздохнув, [npc.she] вздыхает,");
						start.add("Закатив [npc.her] [npc.eyes], [npc.name] делает шаг вперед и неохотно начинает помогать тебе мыться, при этом [npc.she] постоянно ворчит,");
						start.add("С раздраженным вздохом [npc.name] делает шаг вперед и без особого энтузиазма принимается помогать тебе мыться. Даже не пытаясь скрыть [npc.her] раздражение, [npc.she] вздыхает,");
					}
					speech.add("[npc.speech(Неужели тебе больше некого попросить об этом?)]");
					speech.add("[npc.speech(Как бы я хотела, чтобы ты не заставлял меня это делать...)]");
					speech.add("[npc.speech(Отстой какой-то...)]");
					speech.add("[npc.speech(Ты же знаешь, как сильно я ненавижу это делать?)]");
				}

			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append(" ");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		return sb.toString();
	}
	
	private static String getBathSlavesDescription(List<GameCharacter> slavesWashing) {
		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing,
						"Ваша рабыня, которой было приказано помогать вам во время мытья, "+Util.stringsToStringList(names, false)+", так же оставляет [npc.her] одежду у двери, прежде чем последовать за тобой в ванную."));
					sb.append(UtilText.parse(slavesWashing,
							" К счастью, твоя роскошная ванная настолько просторна, что комната не кажется переполненной, даже когда здесь находится [npc.name]."));
				
			} else {
				sb.append("Получив указание помочь тебе вымыться, твои рабыни, "+Util.stringsToStringList(names, false)+", так же оставляют свою одежду у двери, прежде чем последовать за тобой в ванную.");
					sb.append(UtilText.parse(slavesWashing,
							" К счастью, твоя роскошная ванная настолько просторна, что комната не кажется переполненной, даже когда здесь находятся твои рабыни."));
			}
		sb.append("</p>");
		
		// Slave reactions while helping wash:
		
		List<GameCharacter> washingNice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		boolean firstWashing = true;
		for(GameCharacter npc : washingNice) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
			if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
				if(firstWashing) {
					if(Main.game.getPlayer().hasHair()) {
						start.add("Открыв краны и набрав тебе ванну, [npc.name] послушно начинает помогать тебе мыть [pc.hair(true)] и тело. Повысив [npc.her] голос, чтобы его было слышно сквозь шум льющейся воды, [npc.she] говорит,");
					} else {
						start.add("Открыв краны и набрав тебе ванну, [npc.name] послушно начинает помогать тебе мыть тело. Повысив [npc.her] голос, чтобы его было слышно сквозь шум льющейся воды, [npc.she] говорит,");
					}
				} else {
					start.add("Взяв кусок мыла, [npc.name] помогает [npc.her] своей подруге "+(washingNice.size()>2?"slaves":"slave")+" очистить твое тело. Пока [npc.she] принимается за [npc.her] задачу, [npc.she] говорит,");
					start.add("Сделав шаг вперед с куском мыла в [npc.hand], [npc.name] принимается очищать твое тело, приговаривая, пока [npc.she] это делает,");
					start.add("[npc.her] подходит к тебе сбоку, "+(washingNice.size()>2?"slaves":"slave")+"[npc.name] начинает омывать твое тело, приговаривая:");
				}
				speech.add("[npc.speech(Надеюсь, тебе понравится, [pc.name].)]");
				speech.add("[npc.speech(Пожалуйста, дай мне знать, если хочешь, чтобы я делала что-то иначе, [pc.name].)]");
				speech.add("[npc.speech(Я постараюсь очень тщательно тебя отмыть, [pc.name].)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("Открыв краны и набрав тебе ванну, [npc.name] подходит к тебе почти вплотную, после чего намыливает твою спину и начинает тебя очищать. Внезапно [npc.she] делает шаг вперед и, прижимая [npc.her] [npc.breasts+] к твоей спине, [npc.she] соблазнительно [npc.moans],");
					} else {
						start.add("Включив воду и набрав тебе ванну, [npc.name] подходит вплотную сзади, после чего начинает намыливать твою спину и отмывать тебя. Внезапно [npc.she] делает шаг вперед и, прижимая [npc.herself] к твоей спине, соблазнительно [npc.moans] [npc.she],");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("Сделав шаг вперед, [npc.name] прижимает [npc.her] [npc.breasts+] к твоей спине, а затем соблазнительно [npc.moaning],");
						start.add("Намыливая твою спину куском мыла, [npc.name] внезапно делает шаг вперед, прижимая [npc.her] [npc.breasts+] к тебе, пока [npc.she] соблазнительно [npc.moans],");
						start.add("Решив начать с твоей спины, [npc.name] обходит тебя сзади, наклоняется вперед и прижимает [npc.her] [npc.breasts+] к тебе, [npc.moaning],");
					} else {
						start.add("Сделав шаг вперед, [npc.name] прижимается [npc.herself] к твоей спине, а затем соблазнительно [npc.moaning],");
						start.add("Намыливая твою спину, [npc.name] внезапно делает шаг вперед, прижимая [npc.herself] к тебе, пока [npc.she] соблазнительно [npc.moans],");
						start.add("Решив начать с чистки твоей спины, [npc.name] обходит тебя сзади, после чего наклоняется вперед и прижимается [npc.herself] к тебе, [npc.moaning],");
					}
				}
				speech.add("[npc.speech(Ты ведь не против, если я подойду так близко?)]");
				speech.add("[npc.speech(Тебе ведь нравится это ощущение, когда я так близко, правда?)]");
				speech.add("[npc.speech(Вот так, расслабься и позволь мне позаботиться о тебе...)]");
				speech.add("[npc.speech(Возможно, когда ты отмоешься, ты захочешь заняться со мной чем-нибудь грязным...)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("Включив воду и набрав тебе ванну, [npc.name] подходит вплотную сзади, после чего сразу прижимает [npc.her] [npc.breasts+] к твоей спине. Очевидно видя в этом возможность немного развлечься с тобой, [npc.she] обхватывает тебя руками и начинает ласкать твое тело, соблазнительно [npc.moaning] в твою [pc.ear], пока [npc.she] делает это,");
					} else {
						start.add("Включив воду и набрав тебе ванну, [npc.name] подходит вплотную сзади, после чего сразу прижимается [npc.her] всем телом к твоей спине. Очевидно видя в этом возможность немного развлечься с тобой, [npc.she] обхватывает тебя руками и начинает ласкать твое тело, соблазнительно [npc.moaning] в твою [pc.ear], пока [npc.she] делает это,");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("Сделав шаг вперед, [npc.name] прижимает [npc.her] [npc.breasts+] к твоей спине, а затем соблазнительно [npc.moaning],");
						start.add("Намыливая твою спину куском мыла, [npc.name] внезапно делает шаг вперед, прижимая [npc.her] [npc.breasts+] к тебе, пока [npc.she] соблазнительно [npc.moans],");
						start.add("Решив начать с твоей спины, [npc.name] обходит тебя сзади, наклоняется вперед и прижимает [npc.her] [npc.breasts+] к тебе, [npc.moaning],");
					} else {
						start.add("Сделав шаг вперед, [npc.name] прижимается [npc.herself] к твоей спине, а затем соблазнительно [npc.moaning],");
						start.add("Намыливая твою спину, [npc.name] внезапно делает шаг вперед, прижимая [npc.herself] к тебе, пока [npc.she] соблазнительно [npc.moans],");
						start.add("Решив начать с чистки твоей спины, [npc.name] обходит тебя сзади, после чего наклоняется вперед и прижимается [npc.herself] к тебе, [npc.moaning],");
					}
				}
				speech.add("[npc.speech(О да, тебе ведь нравится, когда я так тебя ласкаю, правда?)]");
				speech.add("[npc.speech(От такого тесного контакта меня так и штырит... Хочешь трахнуться после этого?)]");
				speech.add("[npc.speech(Меня так возбуждает... Ты ведь не против хорошенько трахнуться после этого, а?)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)
					|| npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
				if(npc.isShy()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Включив воду и набирая тебе ванну, [npc.name] нервно шагает вперед, а затем принимается помогать тебе мыть [pc.hair(true)] и тело. Стараясь изо всех [npc.her] сил перекричать шум льющейся воды, [npc.she] немного повышает [npc.her] голос и говорит,");
						} else {
							start.add("Включив краны и набрав тебе ванну, [npc.name] нервно делает шаг вперед, а затем принимается помогать тебе мыться. Стараясь перекричать шум льющейся воды, [npc.she] немного повышает [npc.her] голос и говорит: [npc.her] старается изо всех сил, чтобы её было слышно, и говорит,");
						}
					} else {
						start.add("Сжимая в руке кусок мыла, [npc.name] нервно подается вперед, чтобы помочь [npc.her] соратнику "+(washingNice.size()>2?"slaves":"slave")+" отмыться. Немного повысив [npc.her] голос, [npc.she] говорит:");
						start.add("Сделав шаг вперед с куском мыла в [npc.hand], [npc.name] нервно принимается отмывать твое тело. Перекрикивая шум льющейся воды, [npc.she] говорит:");
						start.add("[npc.her] подходит к тебе сбоку, "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] краснеет, когда [npc.she] начинает отмывать твое тело, приговаривая:");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(Мне так нравится быть так близко к тебе, [pc.name]!)]");
						speech.add("[npc.speech(Я так счастлива, что могу вот так тебе помогать...)]");
						speech.add("[npc.speech(Меня так радует возможность помогать тебе вот так...)]");
					} else {
						speech.add("[npc.speech(Я ведь всё правильно делаю, [pc.name]?)]");
						speech.add("[npc.speech(Просто скажи, если я буду делать что-то не так...)]");
						speech.add("[npc.speech(Надеюсь, ты именно так и хотел, чтобы я тебя отмыла...)]");
					}
					
				} else if(npc.isKind()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Включив воду и набирая тебе ванну, [npc.name] радостно шагает вперед, а затем нежно принимается помогать тебе мыть [pc.hair(true)] и тело. Стараясь изо всех [npc.her] сил перекричать шум льющейся воды, [npc.she] повышает [npc.her] голос и говорит,");
						} else {
							start.add("Включив воду и набирая тебе ванну, [npc.name] радостно шагает вперед, а затем нежно принимается помогать тебе мыть тело. Стараясь изо всех [npc.her] сил перекричать шум льющейся воды, [npc.she] повышает [npc.her] голос и говорит,");
						}
					} else {
						start.add("Сжимая в руке кусок мыла, [npc.name] счастливо шагает вперед, чтобы помочь своему товарищу [npc.her] "+(washingNice.size()>2?"slaves":"slave")+" отмыться. Повысив [npc.her] голос, [npc.she] говорит:");
						start.add("Сделав шаг вперед с куском мыла в [npc.hand], [npc.name] с радостью принимается за очищение твоего тела. Повысив [npc.her] голос над шумом льющейся воды, [npc.she] говорит:");
						start.add("[npc.her] подходит к тебе сбоку, "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] улыбается, пока [npc.she] начинает отмывать твое тело, говоря,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(Мне так нравится быть так близко к тебе, [pc.name]. Пожалуйста, скажи мне, если захочешь, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Я так счастлива, что могу вот так помогать тебе; мне так повезло, что ты мой хозяин.)]");
						speech.add("[npc.speech(Позволь мне позаботиться о тебе, [pc.name]; я хочу показать тебе, как много ты для меня значишь.)]");
					} else {
						speech.add("[npc.speech(Дай мне знать, если нужно будет что-то изменить, хорошо, [pc.name]?)]");
						speech.add("[npc.speech(Пожалуйста, скажи мне, если захочешь, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Позволь мне позаботиться о тебе, [pc.name].)]");
					}
					
				} else {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("Включив воду и набирая тебе ванну, [npc.name] шагает вперед, а затем принимается помогать тебе мыть [pc.hair(true)] и тело. Стараясь изо всех [npc.her] сил перекричать шум льющейся воды, [npc.she] повышает [npc.her] голос и говорит,");
						} else {
							start.add("Включив воду и набирая тебе ванну, [npc.name] шагает вперед, а затем принимается помогать тебе мыть тело. Стараясь изо всех [npc.her] сил перекричать шум льющейся воды, [npc.she] повышает [npc.her] голос и говорит,");
						}
					} else {
						start.add("Сжимая в руке кусок мыла, [npc.name] делает шаг вперед, чтобы помочь [npc.her] своему "+(washingNice.size()>2?"slaves":"slave")+" отмыться. Повысив [npc.her] голос, [npc.she] говорит:");
						start.add("Сделав шаг вперед с куском мыла в [npc.hand], [npc.name] принимается отмывать твое тело. Возвышая [npc.her] голос над шумом текущей воды, [npc.she] говорит:");
						start.add("[npc.her] подходит к тебе сбоку, "+(washingNice.size()>2?"slaves":"slave")+", [npc.name] улыбается, пока [npc.she] начинает отмывать твое тело, говоря,");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(Мне так нравится быть так близко к тебе, [pc.name]. Пожалуйста, скажи мне, если захочешь, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Я так счастлива, что могу вот так помогать тебе; мне так повезло, что ты мой хозяин.)]");
						speech.add("[npc.speech(Позволь мне позаботиться о тебе, [pc.name]; я хочу показать тебе, как много ты для меня значишь.)]");
					} else {
						speech.add("[npc.speech(Дай знать, если хочешь, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Просто скажи, если нужно, чтобы я делала что-то иначе.)]");
						speech.add("[npc.speech(Позволь мне позаботиться о тебе, [pc.name].)]");
					}
				}
			}
			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append(" ");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		List<GameCharacter> washingRude = new ArrayList<>(slavesWashing);
		washingRude.removeAll(washingNice);
		for(GameCharacter npc : washingRude) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
				if(npc.isShy()) {
					if(firstWashing) {
						start.add("Неохотно открыв краны, [npc.name] делает шаг вперед, а затем вяло принимается помогать тебе мыться. Пытаясь заглушить шум льющейся воды своими комментариями, [npc.she] бормочет, [npc.her],");
					} else {
						start.add("Неохотно сделав шаг вперед, [npc.name] устремляет [npc.her] взгляд в пол, пока [npc.she] вяло помогает тебе мыться. Тяжело вздохнув, [npc.she] бормочет:");
						start.add("Глядя в пол, [npc.name] делает шаг вперед и неохотно начинает помогать тебе мыться. Раздраженно вздохнув, [npc.she] бормочет себе под [npc.her] нос:");
						start.add("Очевидно не в восторге, [npc.name], тем не менее, делает шаг вперед и вяло начинает помогать тебе мыться. Бормоча под [npc.her] нос, [npc.she] вздыхает:");
					}
					speech.add("[npc.speech(Ненавижу это...)]");
					speech.add("[npc.speech(Как же я вообще докатилась до того, что приходится делать такое...)]");
					speech.add("[npc.speech(Отстой какой-то...)]");
					
				} else if(npc.isSelfish()) {
					if(firstWashing) {
						start.add("Неохотно открыв краны, [npc.name] делает шаг вперед, а затем вяло принимается помогать тебе мыться. С явным оттенком враждебности в [npc.her] голосе, [npc.she] ворчит,");
					} else {
						start.add("Неохотно сделав шаг вперед, [npc.name] вяло принимается помогать тебе мыться. Тяжело вздохнув, [npc.she] жалуется,");
						start.add("Закатив [npc.her] [npc.eyes], [npc.name] делает шаг вперед и неохотно начинает помогать тебе мыться, при этом [npc.she] постоянно ворчит,");
						start.add("Раздраженно вздохнув, [npc.name] делает шаг вперед и вяло принимается помогать тебе мыться. Даже не пытаясь скрыть [npc.her] раздражение, [npc.she] гневно говорит,");
					}
					speech.add("[npc.speech(Я прямо ненавижу это делать, понимаешь? Просто возьми в следующий раз другого раба, чтобы он тебе помог!)]");
					speech.add("[npc.speech(Ну почему ты постоянно заставляешь меня это делать?! Разве не ясно, что я ненавижу помогать тебе вот так?!)]");
					speech.add("[npc.speech(Это просто гребаный отстой. Как насчет того, чтобы в следующий раз тебя помыл какой-нибудь другой раб?)]");
					
				} else {
					if(firstWashing) {
						start.add("Неохотно открыв краны, [npc.name] делает шаг вперед, а затем вяло принимается помогать тебе мыться. Явственно выражая недовольство, [npc.she] вздыхает,");
					} else {
						start.add("Неохотно сделав шаг вперед, [npc.name] вяло принимается помогать тебе мыться. Тяжело вздохнув, [npc.she] вздыхает,");
						start.add("Закатив [npc.her] [npc.eyes], [npc.name] делает шаг вперед и неохотно начинает помогать тебе мыться, при этом [npc.she] постоянно ворчит,");
						start.add("С раздраженным вздохом [npc.name] делает шаг вперед и без особого энтузиазма принимается помогать тебе мыться. Даже не пытаясь скрыть [npc.her] раздражение, [npc.she] вздыхает,");
					}
					speech.add("[npc.speech(Неужели тебе больше некого попросить об этом?)]");
					speech.add("[npc.speech(Как бы я хотела, чтобы ты не заставлял меня это делать...)]");
					speech.add("[npc.speech(Отстой какой-то...)]");
					speech.add("[npc.speech(Ты же знаешь, как сильно я ненавижу это делать?)]");
				}

			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append(" ");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		return sb.toString();
	}
	
	/** Calendar's associated animal-morphs are based on the twelve animals of the Chinese zodiac, with the Monkey being replaced with a demon, the Rooster with a harpy, and the Snake with a lamia.
	 *  The ordering of the demon and harpy have also been switched, so that October has demons.<br/>
	 *  There is also a 15% chance of giving a different, random animal-morph for each month.<br/>
	 * Animals are:<br/>
	 * Rat, Cow, Tiger, Rabbit, Dragon, Lamia (Snake), Horse, Sheep/Goat, Harpy (Rooster), Demon (Monkey), Dog, Pig
	 */
	private static String getCalendarImageDescription(Month month) {
		StringBuilder sb = new StringBuilder();

		sb.append("<p>Листая календарь, пока ты не доходишь до страницы "+month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)+", ты видишь, что на картинке в этом месяце теперь изображена ");
		
		if(Util.random.nextInt()<15) {
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				sb.append(UtilText.returnStringAtRandom(
						"красивый тритон, который увлеченно играет мускулами, восседая на омытом волнами камне.",
						"мускулистый олень-мальчик, который ухмыляется, демонстрируя тебе свой огромный член."));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"прекрасная русалка, которая счастливо выставляет напоказ свою обнаженную грудь, восседая на омытом волнами камне.",
						"пышная олениха, которая склонилась над деревянным столом, подставляя тебе свою мокрую киску."));
			}
			
		} else {
			switch(month) {
				case JANUARY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("подтянутый "+Subspecies.RAT_MORPH.getSingularMaleName(null)+", который озорно ухмыляется тебе, поглаживая свой толстый эрегированный член.");
					} else {
						sb.append("возбужденная "+Subspecies.RAT_MORPH.getSingularFemaleName(null)+", которая наклонилась над столом, чтобы продемонстрировать тебе свою сочащуюся киску.");
					}
					break;
				case FEBRUARY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("с обнаженной грудью "+Subspecies.COW_MORPH.getSingularMaleName(null)+". Его огромные мышцы перекатываются, пока он несет срубленное дерево на одном плече, а между его ног невозможно не заметить массивный бугор, вдавливающийся в ткань шорт.");
					} else {
						sb.append("черно-белая "+Subspecies.COW_MORPH.getSingularFemaleName(null)+", сидящая на маленькой табуретке для дойки. С счастливой улыбкой на лице она увлеченно щиплет и тянет свои раздувшиеся соски, из-за чего струйки молока стекают в металлическое ведро.");
					}
					break;
				case MARCH:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("с яростным видом "+Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger").getSingularMaleName(null)+". Приняв доминантную позу, он скалится тебе в улыбке, явно возбужденный тем, что его огромный кошачий член выставлен напоказ.");
					} else {
						sb.append("с яростным видом "+Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger").getSingularFemaleName(null)+". Приняв доминантную позу, она скалится тебе в улыбке, явно возбужденная тем, что ее пышная грудь и тугая киска выставлены напоказ.");
					}
					break;
				case APRIL:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("красивый "+Subspecies.RABBIT_MORPH.getSingularMaleName(null)+", который держит свой массивный член одной рукой, многозначительно подмигивая тебе.");
					} else {
						sb.append("три раскрасневшихся "+Subspecies.RABBIT_MORPH.getPluralFemaleName(null)+", которые стоят на четвереньках бок о бок, подставляя тебе свои киски.");
					}
					break;
				case MAY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("могучий дракон, восседающий на золотом троне на вершине огромной горы сокровищ. Его огромный чешуйчатый член выставлен напоказ, и с ухмылкой на лице он смотрит на тебя с ожиданием, словно ждет, когда ты заберешься повыше и попробуешь его на вкус.");
					} else {
						sb.append("могучая дракониха, восседающая на золотом троне на вершине огромной горы сокровищ. Ее влажная чешуйчатая киска выставлена напоказ, и с ухмылкой на лице она смотрит на тебя с ожиданием, словно ждет, когда ты заберешься повыше и попробуешь ее на вкус.");
					}
					break;
				case JUNE:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("экзотически выглядящий самец ламии. Он явно возбужден и жаждет секса: его двойной член высунулся из клоаки, а головки уже блестят на солнце от выделяющейся слизистой предэякулята.");
					} else {
						sb.append("экзотически выглядящая самка ламии. Она явно возбуждена и жаждет секса: она тянется вниз, чтобы раздвинуть клоаку и продемонстрировать тебе свою сочащуюся влагой киску.");
					}
					break;
				case JULY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("впечатляюще одаренный "+Subspecies.HORSE_MORPH.getSingularMaleName(null)+", который напрягает мышцы, демонстрируя тебе свой полностью эрегированный расширенный член.");
					} else {
						sb.append("подтянутая "+Subspecies.HORSE_MORPH.getSingularFemaleName(null)+", которая прислонилась к забору, помахивая хвостом в сторону, чтобы продемонстрировать тебе свою звериную киску.");
					}
					break;
				case AUGUST:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("мальчик-овечка и мальчик-козочка, стоящие плечом к плечу и демонстрирующие свои эрегированные члены, игриво подмигивая тебе.");
					} else {
						sb.append("пушистая овечья и козья девчонки, которые лежат, широко разведя ноги и демонстрируя тебе свои узкие, влажные киски.");
					}
					break;
				case SEPTEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("необычайно мужественная гарпия. Хотя размер его члена не вызывает особого восторга, он чертовски красив, и ты чувствуешь, как твое сердце начинает биться чаще, когда он подмигивает тебе.");
					} else {
						sb.append("прекрасная гарпия. Хотя она охотно подставляет тебе свою влажную киску, на её лице читается снисходительное превосходство, и у тебя складывается впечатление, что за право заняться с ней сексом она выдвинет какое-нибудь возмутительное требование.");
					}
					break;
				case OCTOBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("подтянутый, красавец "+Subspecies.DEMON.getSingularMaleName(null)+", который многозначительно подмигивает тебе, проводя пальцами по своему огромному, эрегированному члену.");
					} else {
						sb.append("подтянутая, красавица "+Subspecies.DEMON.getSingularFemaleName(null)+", на которой нет ничего, кроме ведьминой шляпы; она многозначительно подмигивает тебе, проводя пальцами по своей влажной киске и огромной груди.");
					}
					break;
				case NOVEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("энергичный "+Subspecies.DOG_MORPH.getSingularMaleName(null)+", который улыбается тебе, поглаживая свой эрегированный, узловатый собачий член.");
					} else {
						sb.append("возбужденная "+Subspecies.DOG_MORPH.getSingularFemaleName(null)+", которая стоит на четвереньках, прогнув бедра, чтобы подставить тебе свою мокрую киску.");
					}
					break;
				case DECEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("мускулистый кабанчик, который предвкушающе скалится на тебя, поглаживая свой огромный член и пару массивных, полных спермы яиц.");
					} else {
						sb.append("миловидная, покрасневшая свинка, которая прислонилась к стене и тянется вниз, чтобы раздвинуть тебе свою припухлую розовую киску.");
					}
					break;
			}
		}
		
		sb.append(" Посмотрев на картину несколько мгновений, ты заставляешь себя отвести взгляд и читаешь написанное ниже:</p>");
		
		return sb.toString();
	}

	public static final DialogueNode ROOM = new DialogueNode("Ваша комната", "", false) {
		@Override
		public void applyPreParsingEffects() {
			makeupTarget = Main.game.getPlayer();
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			GenericPlace place = Main.game.getPlayerCell().getPlace();
			
			sb.append("<p>Твоя спальня расположена рядом с главной лестницей, соединяющей вестибюль с коридором первого этажа, и является одной из самых больших комнат во всем особняке. Напротив главного входа в комнату расположены четыре больших подъемных окна, из которых открывается отличный вид на внутренний сад, а слева еще одна дверь ведет в твою личную ванную комнату.</p>");
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_PLAYER_ROOM_BED)) {
				sb.append("<p>Твоя комната хорошо обставлена: здесь есть два комода и высокий гардероб, обеспечивающие тебя всем необходимым местом для хранения вещей. Помимо этой мебели, у тебя также есть диван, письменный стол с подходящим к нему стулом и высокий отдельно стоящий зеркало в полный рост.</p>");
			} else {
				sb.append("<p>У правой стены стоит огромная двуспальная кровать, а два комода и во весь рост стоящий гардероб обеспечивают вас всем необходимым местом для хранения вещей. Помимо этой мебели, у вас также есть диван, письменный стол с подходящим к нему стулом и ростовое зеркало на подставке.</p>");
			}
			
			sb.append(
					"<p>Как и всё остальное, что в вашем мире обычно работает на электричестве, освещение, радиаторы и сантехника, судя по всему, питаются арканой.</p>");
			
			sb.append(LilayaHomeGeneric.getRoomModificationsDescription(false));

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			if(!charactersPresent.isEmpty()) {
				List<String> names = new ArrayList<>();
				boolean soloSlave = charactersPresent.size()==1;
				for(NPC npc : charactersPresent) {
					names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
				}
				sb.append("<p>Закреплена за вашей комнатой "+(soloSlave?"— ваша рабыня":"— ваши рабы")+"; "+Util.stringsToStringList(names, false)+".");
				
				List<NPC> greetings = charactersPresent.stream().filter(npc -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_GREETING)).collect(Collectors.toList());
				names = new ArrayList<>();
				for(NPC npc : greetings) {
					names.add(npc.getName());
				}
				soloSlave = greetings.size()==1;
				if(!greetings.isEmpty()) {
					sb.append(" Получив указание поприветствовать вас по прибытии, "
								+(soloSlave
										?UtilText.parse(charactersPresent.get(0), "[npc.she] делает шаг вперед и приветствует ваше возвращение.")
										:Util.stringsToStringList(names, false)+" делают шаг вперед и приветствуют ваше возвращение.")
							+ "</p>");

					List<NPC> greetingsNice = greetings.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
					for(NPC npc : greetingsNice) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						List<String> endSpeechGreetings = new ArrayList<>();
						
						if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							speechGreetings.add("[npc.speech(С возвращением, [pc.name],)]");
							speechGreetings.add("[npc.speech(Добрый [style.morning], [pc.name],)]");
							speechGreetings.add("[npc.speech(С возвращением, [pc.name],)]");
							
							if(npc.isFeminine()) {
								endGreetings.add("[npc.name] говорит это, почтительно кланяясь вам.");
								endGreetings.add("[npc.name] приветствует вас, почтительно кланяясь вслед за [npc.she].");
								endGreetings.add("[npc.name] обращается к вам, сопровождая слова реверансом.");
							} else {
								endGreetings.add("[npc.name] говорит это, почтительно склоняясь перед вами.");
								endGreetings.add("[npc.name] приветствует вас, почтительно склоняясь вслед за [npc.she].");
								endGreetings.add("[npc.name] обращается к вам, сопровождая слова поклоном.");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							speechGreetings.add("[npc.speech(Так скоро вернулся, [pc.name]?)]");
							speechGreetings.add("[npc.speech(Уже вернулся, [pc.name]?)]");
							speechGreetings.add("[npc.speech(Неужели ты вернулся повидаться со мной так быстро, [pc.name]?)]");
							
							if(npc.isFeminine()) {
								endGreetings.add("[npc.name] кокетливо спрашивает, прежде чем прикусить [npc.her] губу и подмигнуть тебе.");
								endGreetings.add("[npc.name] кокетливо спрашивает, слегка повернувшись в сторону и одарив тебя соблазнительной улыбкой.");
								endGreetings.add("[npc.name] кокетливо спрашивает, подмигивая тебе и посылая воздушный поцелуй.");
							} else {
								endGreetings.add("[npc.name] игриво спрашивает, одарив тебя очаровательной улыбкой.");
								endGreetings.add("[npc.name] игриво спрашивает, выпятив грудь и одарив тебя победоносной улыбкой.");
								endGreetings.add("[npc.name] игриво спрашивает, очаровательно тебе улыбаясь.");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							speechGreetings.add("[npc.speech(Ты ведь вернулся, чтобы трахнуться, [pc.name]?)]");
							speechGreetings.add("[npc.speech(О, [pc.name], ты ведь здесь ради хорошего траха, верно?)]");
							speechGreetings.add("[npc.speech(Эй, [pc.name], ты ведь хочешь трахнуться, да?)]");

							endGreetings.add("[npc.name] прямолинейно спрашивает,");
							endGreetings.add("[npc.name] бестактно спрашивает,");
							endGreetings.add("[npc.name] неловко спрашивает,");
							
							if(npc.isFeminine()) {
								endSpeechGreetings.add("[npc.speech(Ты же знаешь, меня всегда тянет к тебе...)]");
								endSpeechGreetings.add("[npc.speech(Не могу перестать фантазировать о тебе...)]");
								endSpeechGreetings.add("[npc.speech(У меня всё горит внутри, стоит только подумать о тебе...)]");
							} else {
								endSpeechGreetings.add("[npc.speech(Я так сильно хочу тебя прямо сейчас.)]");
								endSpeechGreetings.add("[npc.speech(Не могу перестать фантазировать о тебе.)]");
								endSpeechGreetings.add("[npc.speech(Ты же знаешь, я всегда готова, когда и ты захочешь.)]");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							speechGreetings.add("[npc.speech(Привет, [pc.name],)]");
							speechGreetings.add("[npc.speech(Добрый [style.morning], [pc.name],)]");
							speechGreetings.add("[npc.speech(С возвращением, [pc.name],)]");
							
							if(npc.isShy()) {
								endGreetings.add("[npc.name] говорит, глядя в пол и застенчиво переминаясь с [npc.her] на [npc.feet].");
								endGreetings.add("[npc.name] тихо произносит это, а затем краснеет и опускает взгляд в пол.");
								endGreetings.add("[npc.name] тихо приветствует тебя, застенчиво перебирая [npc.feet] [npc.her].");
								
							} else if(npc.isKind()) {
								endGreetings.add("[npc.name] говорит, ласково улыбаясь тебе,");
								endGreetings.add("[npc.name] говорит добрым голосом, после чего мягко улыбается тебе и продолжает,");
								endGreetings.add("[npc.name] радостно приветствует тебя нежным тоном,");
								
								endSpeechGreetings.add("[npc.speech(Могу я чем-нибудь тебе помочь?)]");
								endSpeechGreetings.add("[npc.speech(Как ты поживаешь в этот [style.morning]?)]");
								endSpeechGreetings.add("[npc.speech(Пожалуйста, дай знать, если я смогу что-то для тебя сделать!)]");
								
							} else {
								endGreetings.add("[npc.name] окликает тебя, а затем счастливо улыбается.");
								endGreetings.add("[npc.name] радостно приветствует тебя, улыбается и ждет твоих распоряжений.");
								endGreetings.add("[npc.name] говорит, улыбаясь вам.");
								endGreetings.add("[npc.name] радостно произносит, прежде чем улыбнуться вам.");
								endGreetings.add("[npc.name] обращается к вам, а затем улыбается и ждет, что вы предпримете дальше.");
								endGreetings.add("[npc.name] приветствует вас с улыбкой.");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							speechGreetings.add("[npc.speech(Привет, [pc.name],)]");
							speechGreetings.add("[npc.speech(Добрый [style.morning], [pc.name],)]");
							speechGreetings.add("[npc.speech(С возвращением, [pc.name],)]");
							
							if(npc.isShy()) {
								endGreetings.add("[npc.name] говорит, глядя в пол и застенчиво перебирая [npc.her] [npc.feet], а затем продолжает,");
								endGreetings.add("[npc.name] говорит тихим голосом, прежде чем покраснеть и застенчиво продолжить,");
								endGreetings.add("[npc.name] тихо приветствует вас, застенчиво перебирая [npc.her] [npc.feet], а затем краснеет и продолжает,");
								
								endSpeechGreetings.add("[npc.speech(Я так счастлива, что ты вернулся; я действительно скучала по тебе...)]");
								endSpeechGreetings.add("[npc.speech(Видеть тебя снова — это такое счастье...)]");
								endSpeechGreetings.add("[npc.speech(Я очень скучала по тебе, пока тебя не было...)]");
								
							} else if(npc.isKind()) {
								endGreetings.add("[npc.name] на мгновение счастливо улыбается вам и говорит,");
								endGreetings.add("[npc.name] говорит добрым голосом, после чего нежно улыбается вам и продолжает,");
								endGreetings.add("[npc.name] приветствует вас добрым тоном, одарив любящей улыбкой, и продолжает,");

								endSpeechGreetings.add("[npc.speech(Так здорово видеть тебя снова; казалось, ты ушел навсегда!)]");
								endSpeechGreetings.add("[npc.speech(Я так счастлива видеть тебя снова; надеюсь, в следующий раз ты не задержишься так надолго!)]");
								endSpeechGreetings.add("[npc.speech(Ты даже не представляешь, как я скучала; слава богу, ты вернулся!)]");
								
							} else {
								endGreetings.add("[npc.name] весело говорит, после чего продолжает,");
								endGreetings.add("[npc.name] счастливо говорит, улыбаясь вам, и продолжает,");
								endGreetings.add("[npc.name] приветствует вас взволнованным тоном, одарив радостной улыбкой, и продолжает,");

								endSpeechGreetings.add("[npc.speech(Я так счастлива видеть тебя снова!)]");
								endSpeechGreetings.add("[npc.speech(Я так счастлива снова тебя видеть!)]");
								endSpeechGreetings.add("[npc.speech(Надеюсь, в следующий раз ты не исчезнешь на так долго! Ты же знаешь, я скучаю по тебе?)]");
							}
						}
						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append(" ");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						if(!endSpeechGreetings.isEmpty()) {
							sb.append(" ");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endSpeechGreetings)));
						}
						sb.append("</p>");
					}
					
					List<NPC> greetingsRude = new ArrayList<>(greetings);
					greetingsRude.removeAll(greetingsNice);
					for(NPC npc : greetingsRude) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						
							if(npc.isShy()) {
								speechGreetings.add("[npc.speech(А, это всего лишь ты,)]");
								speechGreetings.add("[npc.speech(Черт, а я надеялась, что это кто-то другой,)]");
								speechGreetings.add("[npc.speech(О нет, только не ты,)]");
								speechGreetings.add("[npc.speech(Ну почему [pc.she] должна была вернуться,)]");
								
								endGreetings.add("[npc.name] бормочет сквозь [npc.her] зубы, прежде чем посмотреть в пол и неловко переступить с [npc.her] на [npc.feet].");
								endGreetings.add("[npc.name] бормочет, устремляя [npc.her] взгляд в пол и отказываясь смотреть тебе в глаза.");
								endGreetings.add("[npc.name] раздраженно бормочет, переминаясь с [npc.her] на [npc.feet] и тихо выругавшись себе под нос.");
								
							} else if(npc.isSelfish()) {
								speechGreetings.add("[npc.speech(Фу, чего тебе опять, [pc.name]?)]");
								speechGreetings.add("[npc.speech(И чего именно тебе нужно от <i>этого</i> раза, [pc.name]?)]");
								speechGreetings.add("[npc.speech(Ну и зачем ты вернулся?)]");
								speechGreetings.add("[npc.speech(Зачем тебе обязательно было возвращаться?)]");
								
								endGreetings.add("[npc.name] гневно спрашивает, даже не пытаясь скрыть [npc.her] враждебность к тебе.");
								endGreetings.add("[npc.name] вопрошает, прежде чем прищурить [npc.her] [npc.eyes] и яростно уставиться на тебя.");
								endGreetings.add("[npc.name] огрызается, прежде чем бросить на тебя полный обиды взгляд своими [npc.her] [npc.eyes].");
								
							} else {
								speechGreetings.add("[npc.speech(Просто делай то, ради чего пришел, [pc.name],)]");
								speechGreetings.add("[npc.speech(Ну же, [pc.name], делай то, ради чего пришел,)]");
								speechGreetings.add("[npc.speech(Давай просто покончим с этим,)]");
								speechGreetings.add("[npc.speech(Ну же, говори, чего тебе нужно на этот раз,)]");
								
								endGreetings.add("[npc.name] вздыхает, а затем раздраженно закатывает [npc.her] [npc.eyes].");
								endGreetings.add("[npc.name] раздраженно вздыхает, скрестив [npc.her] [npc.arms], и ждет твоего следующего шага.");
								endGreetings.add("[npc.name] вздыхает, явно не в восторге от того, что ее отправили в твою комнату.");
								endGreetings.add("Говорит [npc.name], после чего устало вздыхает и постукивает [npc.her] [npc.foot] по полу.");
								endGreetings.add("Говорит [npc.name], после чего вздыхает и закатывает [npc.her] [npc.eyes] в явном знак недовольства.");
								endGreetings.add("Говорит [npc.name], скрестив [npc.her] [npc.arms] и раздраженно вздыхая.");
							}

						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append(" ");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						sb.append("</p>");
					}
				}
			}
			
			return sb.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(responseTab, index);
		}
	};

	private static List<GameCharacter> slavesInRoom(int hour) {
		List<GameCharacter> charactersPresent = new ArrayList<>();
		
		for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			try {
				GameCharacter slave = Main.game.getNPCById(slaveId);
				if(slave.getSlaveJob(hour)==SlaveJob.BEDROOM) {
					charactersPresent.add(slave);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return charactersPresent;
	}
	
	private static List<GameCharacter> slavesWantingToSexPlayer(int hour) {
		List<GameCharacter> charactersPresent = new ArrayList<>();
		
		for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			try {
				GameCharacter slave = Main.game.getNPCById(slaveId);
				if(slave.getSlaveJob(hour)==SlaveJob.BEDROOM
						&& slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
						&& slave.isAttractedTo(Main.game.getPlayer())
						&& (slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE) || !((NPC)slave).hasFlag(NPCFlagValue.slaveBedroomHadSleepSex))) {
					charactersPresent.add(slave);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return charactersPresent;
	}
	
	public static final DialogueNode ROOM_SET_ALARM = new DialogueNode("Установить будильник", "", true) {
		@Override
		public void applyPreParsingEffects() {
			super.applyPreParsingEffects();
			if(Main.game.getDialogueFlags().getSavedLong("player_phone_alarm") < 0) {
				// If unset, default to 8:00 AM
				Main.game.getDialogueFlags().setSavedLong("player_phone_alarm", 8*60);
			}
		}
		@Override
		public String getContent() {
			long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
			String alarmTimeStr = Units.time(LocalTime.ofSecondOfDay(alarmTime*60));
			return "<div><p style='text-align:center;'>Достав телефон, ты открываешь приложение будильника и готовишься установить время его срабатывания...</p></div><div class='cosmetics-inner-container' style='margin:1% 10%; width:78%; padding:1%; box-sizing:border-box; position:relative;'><p style='margin:0; padding:0;'><b>Установить будильник</b></p><div class='container-full-width' style='width:35%; text-align:center; float:left; position:relative; padding:0; margin:0;'><div id='PLAYER_ALARM_DECREASE_LARGE' class='normal-button' style='width:48%; margin:1%; padding:0;'>[style.boldBad(-1 час)]</div><div id='PLAYER_ALARM_DECREASE' class='normal-button' style='width:48%; margin:1%; padding:0;'>[style.boldBadMinor(-5 минут)]</div></div><div class='container-full-width' style='width:28%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+ alarmTimeStr
						+ "</div><div class='container-full-width' style='width:35%; text-align:center; float:left; position:relative; padding:0; margin:0;'><div id='PLAYER_ALARM_INCREASE' class='normal-button' style='width:48%; margin:1%; padding:0;'>[style.boldGoodMinor(+5 минут)]</div><div id='PLAYER_ALARM_INCREASE_LARGE' class='normal-button' style='width:48%; margin:1%; padding:0;'>[style.boldGood(+1 час)]</div></div></div>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Установить будильник", "Ваш будильник будет установлен на указанное время.", Main.game.getSavedDialogueNode());
				
			} else if(index == 2) {
				return new Response("Удалить будильник", "Удалить ваш будильник, оставив его выключенным.", Main.game.getSavedDialogueNode()) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().removeSavedLong("player_phone_alarm");
					}
				};
				
			}
			
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
//	private static int getHourPlusSleep() {
//		return (Main.game.getHourOfDay() + (sleepTimeInMinutes/60))%24;
//	}
	private static List<GameCharacter> slavesPresentWhenGoingToSleep;
	private static List<GameCharacter> slavesPresentWhenWaking;
	private static List<GameCharacter> slavesToWakePlayer;
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_SLEEP = new DialogueNode("Ваша комната", "", false) {

		@Override
		public boolean isTravelDisabled() {
			return !slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty();
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			// Going to sleep:
			
			if(!slavesPresentWhenGoingToSleep.isEmpty()) {
				boolean soloSlave = slavesPresentWhenGoingToSleep.size()==1;
				
				List<String> names = new ArrayList<>();
				slavesPresentWhenGoingToSleep.stream().forEach((npc) -> names.add(npc.getName()));

				sb.append("<p>Чувствуя усталость и потребность в отдыхе, вы направляетесь к кровати и рушитесь на матрас, после чего откидываете одеяло и ныряете под него. Осознав, что хотите поспать, "+Util.stringsToStringList(names, false)+(soloSlave?" быстро задергивает шторы":" быстро задергивают шторы")+", а затем делает шаг обратно к вам.</p>");

				// Sleeping arrangements:
				List<GameCharacter> floorSlaves = slavesPresentWhenGoingToSleep.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_FLOOR)).collect(Collectors.toList());
				List<GameCharacter> onBedSlaves = slavesPresentWhenGoingToSleep.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_ON_BED)).collect(Collectors.toList());
				if(!floorSlaves.isEmpty() || !onBedSlaves.isEmpty()) {
					sb.append("<p>");
					boolean soloFloor = floorSlaves.size()==1;
					List<String> floorNames = new ArrayList<>();
					floorSlaves.stream().forEach((npc) -> floorNames.add(npc.getName()));
					if(!floorSlaves.isEmpty()) {
						sb.append(soloFloor
								?UtilText.parse(floorSlaves.get(0), "Зная, что [npc.sheIs] нельзя спать на вашей кровати, [npc.name] ложится на пол и готовится попытаться вздремнуть.")
								:"Зная, что им нельзя спать на вашей кровати, "+Util.stringsToStringList(floorNames, false)+" лечь на пол и попытаться поспать.");
					}
					if(!onBedSlaves.isEmpty()) {
						boolean soloOnBed = onBedSlaves.size()==1;
						List<String> onBedNames = new ArrayList<>();
						onBedSlaves.stream().forEach((npc) -> onBedNames.add(npc.getName()));
						if(!floorSlaves.isEmpty()) {
							sb.append(soloOnBed
									?UtilText.parse(onBedSlaves.get(0),  "Проходя мимо "+Util.stringsToStringList(floorNames, false)+", [npc.name] забирается к тебе в постель, после чего издает расслабленный вздох и сворачивается калачиком на твоем одеяле.")
									:"Проходя мимо "+(soloFloor?UtilText.parse(floorSlaves.get(0), "[npc.name]"):"твоих рабынь на полу,")
										+", "+Util.stringsToStringList(onBedNames, false)+" они забираются к тебе в постель, каждая из которых издает расслабленный вздох и сворачивается калачиком на твоем одеяле.");
						} else {
							sb.append(soloOnBed
									?UtilText.parse(onBedSlaves.get(0), "Зная, что [npc.sheIs] разрешено спать на покрывале, [npc.name] забирается к тебе в постель, после чего издает расслабленный вздох и сворачивается калачиком, готовая ко сну.")
									:"Зная, что им разрешено спать на покрывале, "+Util.stringsToStringList(onBedNames, false)+" они забираются к тебе в постель, каждая из которых издает расслабленный вздох и сворачивается калачиком, готовые ко сну.");
						}
					}
					sb.append("</p>");
				}

				List<GameCharacter> inBedSlaves = slavesPresentWhenGoingToSleep.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_IN_BED)).collect(Collectors.toList());
				if(!inBedSlaves.isEmpty()) {
					sb.append("<p>");
					boolean soloInBed = inBedSlaves.size()==1;
					List<String> inBedNames = new ArrayList<>();
					inBedSlaves.stream().forEach((npc) -> inBedNames.add(npc.getName()));
					sb.append(soloInBed
							?UtilText.parse(inBedSlaves.get(0), "Получив твое явное разрешение, [npc.name] откидывает край одеяла и быстро ныряет под него.")
							:"Получив ваше явное разрешение, "+Util.stringsToStringList(inBedNames, false)
								+" откинуть два верхних угла одеяла, а затем "+(inBedSlaves.size()==2?"оба":"каждый")+" из них быстро ныряют под покрывало.");
					sb.append("</p>");
					
					List<GameCharacter> niceSlaves = inBedSlaves.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
					for(GameCharacter npc : niceSlaves) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						List<String> endSpeechGreetings = new ArrayList<>();
						
						if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Позволь мне согреть тебя, [pc.name],)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Позволь мне подарить тебе еще больше комфорта, [pc.name],)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Позволь мне согреть тебя, [pc.name],)]"));
							
							endGreetings.add(UtilText.parse(npc, "предлагает [npc.name], прежде чем прижаться к вам."));
							endGreetings.add(UtilText.parse(npc, "предлагает [npc.name], забираясь под простыни, чтобы прижаться к вам."));
							endGreetings.add(UtilText.parse(npc, "[npc.name] говорит это, прежде чем прижаться к тебе, вжимая [npc.her] тело в твое."));
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Может, когда ты проснешься, мы займемся в этой постели чем-нибудь еще?)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Когда ты немного отдохнешь, может, мы вдвоем потратим немного энергии вместе?)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Хочешь немного развлечься со мной после того, как проснешься, [pc.name]?)]"));
							
							endGreetings.add(UtilText.parse(npc, "[npc.name] дразнит тебя, прижимаясь к тебе [npc.herself]."));
							endGreetings.add(UtilText.parse(npc, "[npc.name] соблазнительно предлагает, прежде чем залезть под одеяло и прижаться к тебе [npc.herself]."));
							endGreetings.add(UtilText.parse(npc, "[npc.name] дразнит тебя, прежде чем скользнуть под одеяло и прижаться к тебе [npc.her] телом."));
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							if(npc.hasBreasts()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Если хочешь, можешь использовать мою грудь вместо подушки,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Потискай мою грудь хорошенько; это поможет тебе лучше уснуть,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Ну же, поиграй с моей грудью немного, прежде чем уснешь,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] предлагает, прижимая [npc.her] [npc.breastSize] груди к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] игриво предлагает, прежде чем заскользить под одеяло и прижаться к тебе [npc.her] [npc.breastSize] грудями."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] предлагает, прежде чем подползти ближе и прижаться к тебе [npc.her] [npc.breastSize] грудями."));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Мы сможем хорошенько трахнуться после того, как ты поспишь, верно?)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Когда ты немного отдохнешь, ты ведь будешь готов к хорошему траху, а?)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Когда встанешь, мы сможем хорошенько трахнуться, ладно?)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] бесцеремонно спрашивает, запуская руку под одеяло, чтобы погладить твой [pc.leg]."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] игриво предлагает, прежде чем заскользить под одеяло и прижаться к тебе [npc.herself]."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] предлагает, прежде чем подползти ближе и прижаться к тебе [npc.herself]."));
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(npc.isShy()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Эм, [pc.name], только скажи, если я занимаю слишком много места,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Просто скажи, если я тебе мешаю,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Если я подбираюсь слишком близко, просто дай знать,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] застенчиво произносит это, прикрывая [npc.her] краснеющую [npc.face] краем простыни."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит, нервно вцепившись в простыни, пока [npc.she] подползает к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] бормочет, яростно краснея, пока [npc.she] подбирается к тебе под одеялом."));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Позволь мне подобраться чуть ближе,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Мне просто нужно устроиться поудобнее,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Вот так, так-то лучше,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит, подползая к тебе и прижимаясь своей [pc.breasts] к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит, прежде чем залезть под одеяло и прижаться своей [npc.herself] к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит, улыбаясь, пока [npc.she] прижимает [npc.herself] к тебе."));
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Мне так нравится засыпать с тобой, [pc.name],)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Это лучшее чувство — засыпать с тобой вот так,)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(Засыпать с тобой — лучшая часть моего дня,)]"));
							
							if(npc.isShy()) {
								endGreetings.add(UtilText.parse(npc, "[npc.name] застенчиво произносит, прикрываясь краем простыни, чтобы скрыть [npc.her] румянец [npc.face], пока [npc.she] подползает ближе, прижимаясь [npc.herself] к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит, нервно вцепившись в простыни, пока [npc.she] подползает к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] нервно бормочет, заливаясь густым румянцем, пока [npc.she] подбирается к тебе под одеялом."));
								
							} else {
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит счастливо улыбаясь, пока [npc.she] прижимается к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит, прежде чем скользнуть под одеяло и с любовью прижать [npc.herself] к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] нежно произносит, после чего подбирается ближе и прижимает [npc.herself] к тебе."));
							}
						}
						if(npc.getTailType().isSuitableForSleepHugging()) {
							endSpeechGreetings.add(UtilText.parse(npc, "С удовлетворенным вздохом [npc.race] водит [npc.her] [npc.tail+] под одеялом, прежде чем накрыть им твое тело, ласково обнимая тебя хвостом."));
							endSpeechGreetings.add(UtilText.parse(npc, "Перемещая [npc.her] [npc.tail+] под одеялом, [npc.race] обвивает им твою [pc.leg], а затем прижимается к тебе еще плотнее."));
							endSpeechGreetings.add(
									UtilText.parse(npc, "Демонстрируя впечатляющий контроль над [npc.her] [npc.tail+], [npc.race] стремительно подтягивает его вверх и обвивает твою нижнюю часть тела, после чего прижимается к тебе еще теснее."));
						}
						
						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append(" ");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						if(!endSpeechGreetings.isEmpty()) {
							sb.append(" ");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endSpeechGreetings)));
						}
						sb.append("</p>");
					}
					
					List<GameCharacter> rudeSlaves = new ArrayList<>(inBedSlaves);
					rudeSlaves.removeAll(niceSlaves);
					for(GameCharacter npc : rudeSlaves) {
						sb.append("<p>");
							List<String> speechGreetings = new ArrayList<>();
							List<String> endGreetings = new ArrayList<>();
							if(npc.isShy()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Пожалуйста, соблюдай свою сторону кровати,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Я бы предпочла, чтобы ты не подбирался слишком близко,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Мне бы очень хотелось, чтобы ты меня не трогал,)]"));
								
								endGreetings.add(UtilText.parse(npc, "нервно просит [npc.name], после чего переворачивается на бок, повернувшись к тебе [npc.her]."));
								endGreetings.add(UtilText.parse(npc, "бормочет [npc.name], прежде чем отползти от тебя."));
								endGreetings.add(UtilText.parse(npc, "робко просит [npc.name], после чего зарывается под одеяло и поворачивается к тебе [npc.her]."));
								
							} else if(npc.isSelfish()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Не заваливайся в мое личное пространство во сне,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Придерживайся своей половины кровати,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(На твоей половине кровати полно места; в мою не лезь,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] предостерегает, раздраженно фыркнув, пока [npc.she] отодвигается от тебя."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] резко заявляет, после чего отодвигается от тебя с разочарованным вздохом."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит, прежде чем снова развернуть [npc.her] к тебе."));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Я просто буду держаться своей половины кровати,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Я просто останусь здесь, на своей стороне,)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(Я оставлю тебе много места, не волнуйся,)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name] говорит, устало вздыхая, пока [npc.she] отодвигается от тебя."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] вздыхает, немного отодвигаясь, а затем перекатывается и снова разворачивает [npc.her] к тебе."));
								endGreetings.add(UtilText.parse(npc, "[npc.name] вздыхает, прежде чем снова развернуть [npc.her] к тебе и отодвинуться к краю кровати."));
							}
							sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
							sb.append(" ");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						sb.append("</p>");
					}
				}
				
			} else {
				sb.append("<p>Завесив шторы, вы заводите будильник на телефоне, ложитесь в постель и закрываете глаза. Здесь, в доме Lilaya, вы чувствуете себя в полной безопасности и комфорте, и вскоре погружаетесь в сон, размышляя обо всем, что произошло с вами за последнее время...</p>");
				
			}
			
			
			// Sleeping:
			sb.append("<p>"
					+ "[style.italics(...)]"
				+ "</p>");
			
			
			// Waking up:
			if(!slavesPresentWhenWaking.isEmpty()) {
				int hour = Main.game.getHourOfDay();
				String morningString = "evening";
				if(hour<4) {
					morningString = "evening";
				} else if(hour<12) {
					morningString = "morning";
				}else if(hour<17) {
					morningString = "afternoon";
				}

				List<GameCharacter> hornySlaves = slavesWantingToSexPlayer(Main.game.getHourOfDay());
				
//				boolean soloSlave = slavesToWakePlayer.size()==1;
				
				if(!slavesToWakePlayer.isEmpty()) {
					List<String> names = new ArrayList<>();
					slavesToWakePlayer.stream().forEach((npc) -> names.add(npc.getName()));
					
					GameCharacter slaveWaking = Util.randomItemFrom(slavesToWakePlayer);
//					sb.append("<p>"
//							+ (soloSlave
//								?UtilText.parse(slaveWaking,
//									"With you and [npc.name] now in your respective positions, you ask [npc.herHim] to wake you at the time that you'd like to be getting up."
//										+ " After [npc.sheHas] reassured you that [npc.she] won't let you sleep in too late, you let out a contented sigh, close your eyes, and start to drift off to sleep...")
//								:UtilText.parse(slaveWaking,
//									"With your slaves now being settled into their respective positions, you ask [npc.name] to wake you at the time that you'd like to be getting up."
//										+ " After [npc.sheHas] reassured you that [npc.she] won't let you sleep in too late, you let out a contented sigh, close your eyes, and start to drift off to sleep..."))
//						+ "</p>"
//						+ "<p>"
//							+ "[style.italics(...)]"
//						+ "</p>");
					
					if(hornySlaves.isEmpty()) {
						sb.append("<p>");
						if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							sb.append(UtilText.parse(slaveWaking,
									"[npc.speech([pc.Name]? Пора просыпаться,)] — слышите вы чей-то голос. Медленно открыв [pc.eyes], вы видите, что [npc.name] улыбается вам сверху вниз. Заметив, что вы проснулись, [npc.she] отходит, чтобы раздвинуть шторы, и, заглядывая через [npc.her] плечо, пока [npc.she] это делает, [npc.speech(Добро "+morningString+", [pc.name]!)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							sb.append(UtilText.parse(slaveWaking, 
									"[npc.speech(Давай, [pc.name], пора вставать,)] — слышите вы соблазнительный шепот прямо в [pc.ear]. Медленно открыв [pc.eyes], вы видите, что [npc.name] улыбается вам, прикусив [npc.her] губу. Заметив, что вы проснулись, [npc.she] проводит рукой по вашим [pc.chest], после чего отходит, чтобы раздвинуть шторы. Заглядывая через [npc.her] плечо, пока [npc.she] это делает, [npc.she] дразнит вас: [npc.speech(Добро "+morningString+", [pc.name]... Ну так есть ли что-нибудь <i>особенное</i>, чем ты хотела бы заняться сегодня?)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							sb.append(UtilText.parse(slaveWaking,
									"[npc.speech(Если встанешь прямо сейчас, еще успеешь быстро трахнуться, верно?)] — слышишь ты чей-то голос, и когда ты медленно открываешь свои [pc.eyes], то видишь, как [npc.name] жадно смотрит на тебя сверху вниз. Заметив, что ты проснулась, [npc.she] наклоняется, чтобы крепко поцеловать тебя в [pc.lips], а затем отстраняется, чтобы раздвинуть шторы. Опираясь на плечо [npc.her], пока [npc.she] это делает, [npc.she] дразнит тебя: [npc.speech(Добро "+morningString+", [pc.name]! Значит, хочешь трахнуть меня прямо сейчас, да?)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(slaveWaking.isShy()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Эм, извини, [pc.name]? Пора просыпаться,)] — слышишь ты чей-то нервный голос, и когда ты медленно открываешь свои [pc.eyes], то видишь, как [npc.name] обеспокоенно смотрит на тебя. Заметив, что ты проснулась, [npc.she] с облегчением вздыхает и спешит открыть шторы, выкрикивая на ходу, пока [npc.she] это делает: [npc.speech(Добро "+morningString+", [pc.name]!)]"));
							} else {
								sb.append(UtilText.parse(slaveWaking, 
										"[npc.speech([pc.Name]? Давай, [pc.name], пора просыпаться,)] — слышишь ты чей-то голос, и когда ты медленно открываешь свои [pc.eyes], то видишь, как [npc.name] улыбается тебе сверху вниз. Заметив, что ты проснулась, [npc.she] отстраняется, чтобы открыть шторы, и, опираясь на плечо [npc.her], пока [npc.she] это делает, поддразнивает тебя: [npc.speech(Добро "+morningString+", [pc.name]!)]"));
							}
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							if(slaveWaking.isShy()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Ну же... Давай, соня... Пора просыпаться,)] ты слышишь робкий голос, зовущий тебя, и когда ты медленно открываешь свои [pc.eyes], ты видишь, что [npc.name] смотрит на тебя сверху вниз. Заметив, что ты проснулся, [npc.she] с легким вздохом облегчения спешит открыть шторы, тихонько выкликая, пока [npc.she] делает это, [npc.speech(Добро "+morningString+", [pc.name]... Надеюсь, сегодняшний день будет у тебя чудесным...)]"));
							} else if(slaveWaking.isKind()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Ну же, соня! Ты ведь не хочешь вечно проваляться в постели?)] ты слышишь зовущий тебя голос, и когда ты медленно открываешь свои [pc.eyes], ты видишь, что [npc.name] улыбается тебе, глядя сверху вниз. Заметив, что ты проснулся, [npc.she] нежно и любя гладит тебя по лицу, после чего спешит открыть шторы, окликая тебя через [npc.her] плечо, пока [npc.she] делает это, [npc.speech(Добро "+morningString+", [pc.name]! Надеюсь, сегодняшний день будет у тебя чудесным!)]"));
							} else {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]? Ну же, соня! Ты ведь не хочешь вечно проваляться в постели?)] ты слышишь зовущий тебя голос, и когда ты медленно открываешь свои [pc.eyes], ты видишь, что [npc.name] улыбается тебе, глядя сверху вниз. Заметив, что ты проснулся, [npc.she] встает и спешит открыть шторы, окликая тебя через [npc.her] плечо, пока [npc.she] делает это, [npc.speech(Добро "+morningString+", [pc.name]! Надеюсь, сегодняшний день будет у тебя чудесным!)]"));
							}
						}
						sb.append("</p>");

						sb.append(UtilText.parse(slaveWaking,
								"<p>Как бы ни было уютно, вы решаете, что лучше поступить так, как говорит [npc.name], и встать с постели. После минуты лежания под одеялом вы так и делаете. С довольным зевком и потягиванием [pc.arms], вы позволяете "+ Util.stringsToStringList(names, false)+" ей помочь вам собрать вещи, и, сделав последнее глубокое потягивание, вы готовы отправиться в путь вновь...</p>"));
					}
					
				} else {
//					sb.append("<p>"
//								+ (soloSlave
//									?UtilText.parse(slavesPresentWhenGoingToSleep.get(0),
//											"With you and [npc.name] now in your respective positions, you set your phone's alarm and place it on the bedside cabinet beside you."
//											+ " Letting out a contented sigh, you close your eyes and start to drift off to sleep...")
//									:"With your slaves now being settled into their respective positions, you set your phone's alarm and place it on the bedside cabinet beside you."
//										+ " Letting out a contented sigh, you close your eyes and start to drift off to sleep...")
//							+ "</p>"
//							+ "<p>"
//								+ "[style.italics(...)]"
//							+ "</p>");

					if(hornySlaves.isEmpty()) {
						List<String> names = new ArrayList<>();
						slavesPresentWhenWaking.stream().forEach((npc) -> names.add(npc.getName()));
						sb.append(
								"<p><i>Бип-бип... бип-бип... би-</i></p><p>Перевернувшись на бок, вы нащупываете телефон и выключаете будильник, после чего со вздохом снова плюхаетесь на кровать. Несмотря на невероятный уют, вы решаете, что пора вставать, и после минуты лежания под одеялом наконец поднимаетесь. С довольным зевком и потягиванием [pc.arms], вы позволяете "+ Util.stringsToStringList(names, false)+" ей помочь вам собрать вещи, и, сделав последнее глубокое потягивание, вы готовы отправиться в путь вновь...</p>");
					}
				}

				if(!hornySlaves.isEmpty()) {
					Collections.shuffle(hornySlaves);
					boolean soloHornySex = hornySlaves.size()==1;
					List<String> hornyNames = new ArrayList<>();
					hornySlaves.stream().forEach((npc) -> hornyNames.add(npc.getName()));
					if(Main.game.getPlayer().hasTrait(Perk.HEAVY_SLEEPER, true)) {
						if(!hornySlaves.get(0).isMute()) {
							sb.append(UtilText.parse(hornySlaves,
									"<p>[npc.speech(~Ммм!~ Вот так... Не просыпайся...)]</p><p>Поскольку вы спите очень крепко, эти слова, произнесенные [npc.name], даже близко не могут вас разбудить."
										+ (soloHornySex
												?" Скользя по вашей постели, [npc.name] с голодным блеском в глазах смотрит на вас сверху вниз."
												:" Скользя по вашей постели, "+Util.stringsToStringList(hornyNames, false)+" с голодным блеском в глазах смотрит на вас сверху вниз.")
										+ " С возбужденной [npc.moan], [npc.race] начинает нежно ласкать вас, жадно посапывая, пока [npc.she] делает это, [npc.speech(Я трахну тебя, [pc.name], а ты даже не узнаешь...)]</p>"));
						} else {
							sb.append(UtilText.parse(hornySlaves,
									"<p>Тихо подкравшись к твоей постели, [npc.name] смотрит на тебя, пока ты спишь, и издает возбужденный стон. Ты спишь так крепко, что этот звук даже близко не может тебя разбудить."
										+ (soloHornySex
												?" Скользя по вашей постели, [npc.name] с голодным блеском в глазах смотрит на вас сверху вниз."
												:" Скользя по вашей постели, "+Util.stringsToStringList(hornyNames, false)+" с голодным блеском в глазах смотрит на вас сверху вниз.")
										+ " Облизывая [npc.lips] [npc.her], [npc.race] начинает нежно ощупывать тебя, жадно дыша, пока [npc.she] готовится трахать тебя во сне...</p>"));
						}
						
					} else {
						sb.append(UtilText.parse(hornySlaves,
								"<p>"
									+ (soloHornySex
											?"[npc.speech(~Ммм!~ Вот так... теперь ты в моей власти...)]"
											:"[npc2.speech(~Ммм!~ Вот так... теперь ты в моей власти...)] — голос [npc2.name] проникает в твой спящий разум.")
								+ "</p><p>На мгновение ты не совсем понимаешь, снится тебе это или нет, но вдруг чувствуешь, как сверху на тебя переваливается странная тяжесть, что мгновенно заставляет тебя проснуться."
									+ (soloHornySex
											?" Открыв глаза, ты видишь [npc.name], сидящую на твоей груди и с голодным предвкушением смотрящую на тебя сверху вниз."
											:" Открыв глаза, ты видишь, "+Util.stringsToStringList(hornyNames, false)+" наклонившихся над тобой, и на лицах каждой из них играет голодная ухмылка.")
									+ " Видя похотливую [npc.moan], [npc.name] с нетерпением восклицает: [npc.speech(Хорош, "+morningString+", [pc.name]! Надеюсь, ты готова к траху!)]</p>"));
					}
					return sb.toString();
				}
				
			} else {
				sb.append("<p><i>Бип-бип... бип-бип... би-</i></p><p>Перекатившись на бок, вы нащупываете телефон и выключаете будильник, после чего со вздохом снова плюхаетесь на кровать. Несмотря на невероятный уют, вы понимаете, что пора вставать, и, полежав под одеялом еще минуту, наконец решаетесь на это. Раздвинув шторы, вы начинаете собирать вещи и, сделав последнюю растяжку, готовитесь снова отправиться в путь...</p>");
			}
			
			if(!slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty()) {
				sb.append("<p style='text-align:center;'>[style.italicsGood(Вы чувствуете себя совершенно бодрым!)]</p>");
			}
			
			return sb.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty()) {
				return null;
			}
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> hornySlaves = slavesWantingToSexPlayer(Main.game.getHourOfDay());
			if(!hornySlaves.isEmpty()) {
				boolean soloSex = hornySlaves.size()==1;
				List<String> names = new ArrayList<>();
				hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
				List<GameCharacter> spectators = new ArrayList<>(slavesInRoom(Main.game.getHourOfDay()));
				spectators.removeAll(hornySlaves);
				
				UtilText.addSpecialParsingString(String.valueOf(soloSex), true);
				UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
				
				if(index==1) {
					if(Main.game.getPlayer().hasTrait(Perk.HEAVY_SLEEPER, true)) {
						return new ResponseSex("Секс во сне",
								(soloSex
									?UtilText.parse(hornySlaves.get(0), "[npc.Name] трахает вас во сне...")
									:Util.stringsToStringList(names, false)+" трахает вас во сне..."),
								false,
								false,
								new SMGeneric(
										hornySlaves,
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null,
										ResponseTag.PREFER_MISSIONARY){
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.DOM_GENTLE;
										}
										return super.getStartingSexPaceModifier(character);
									}
									@Override
									public SexPace getForcedSexPace(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.DOM_GENTLE;
										}
										return super.getForcedSexPace(character);
									}
									@Override
									public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
										Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
										map.put(ImmobilisationType.SLEEP, new HashMap<>());
										map.get(ImmobilisationType.SLEEP).put(hornySlaves.get(0), Util.newHashSetOfValues(Main.game.getPlayer()));
										return map;
									}
								},
								POST_WAKE_UP_SEX,
								UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "BED_SEX_START_SLEEP", hornySlaves)) {
							@Override
							public void effects() {
								Main.game.getPlayer().addStatusEffect(StatusEffect.SLEEPING_HEAVY, -1);
								for(GameCharacter slave : hornySlaves) {
									((NPC)slave).addFlag(NPCFlagValue.slaveBedroomHadSleepSex);
								}
							}
						};
						
					} else {
						return new ResponseSex("Секс",
								(soloSex
									?UtilText.parse(hornySlaves.get(0), "[npc.Name] навязывает вам [npc.herself]...")
									:Util.stringsToStringList(names, false)+" насилуют вас..."),
								!hornySlaves.stream().anyMatch(s->s.isWillingToRape(Main.game.getPlayer()) && s.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)),
								!hornySlaves.stream().anyMatch(s->s.isWillingToRape(Main.game.getPlayer()) && s.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)),
								new SMGeneric(
										hornySlaves,
										Util.newArrayListOfValues(Main.game.getPlayer()),
										spectators,
										null,
										ResponseTag.PREFER_MISSIONARY),
								POST_WAKE_UP_SEX,
								UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "BED_SEX_START", hornySlaves));
					}
					
				} else if(index==2) {
					if(Main.game.getPlayer().hasTrait(Perk.HEAVY_SLEEPER, true)) {
						return new Response("Отказаться",
								"Пока вы крепко спите, вы не можете сказать «нет»!",
								null);
						
					} else if(hornySlaves.stream().anyMatch(s->s.isWillingToRape(Main.game.getPlayer()) && s.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST))) {
						GameCharacter rapist = hornySlaves.stream().filter(s->s.isWillingToRape(Main.game.getPlayer()) && s.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)).findFirst().get();
						return new Response("Отказаться",
								UtilText.parse(rapist, "Раз уж ты дал(а) [npc.herHim] разрешение на изнасилование, [npc.nameIsFull] не примет отказ!"),
								null);
						
					} else {
						return new Response("Отказаться",
								(soloSex
										?UtilText.parse(hornySlaves.get(0), "У тебя сейчас совсем нет настроения, так что решительно прикажи [npc.name] остановиться.")
										:"Вы сейчас совсем не в настроении, поэтому твердо говорите "+ Util.stringsToStringList(names, false)+" чтобы они остановились."),
								REFUSE_SLAVE_SEX);
						
					}
				}
				return null;
			}
			return getResponseRoom(responseTab, index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode POST_WAKE_UP_SEX = new DialogueNode("Завершено", "", false) {
		@Override
		public void applyPreParsingEffects() {
			List<GameCharacter> hornySlaves = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			boolean soloSex = hornySlaves.size()==1;
			List<String> names = new ArrayList<>();
			hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
			
			UtilText.addSpecialParsingString(String.valueOf(soloSex), true);
			UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
			
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "POST_WAKE_UP_SEX", hornySlaves));
			
			Main.game.getPlayer().wakeUp();
		}
		@Override
		public String getDescription() {
			if(Main.game.getPlayer().isAsleep()) {
				return "Вы продолжаете спать...";
			}
			return "Вы снова рушитесь на кровать...";
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(responseTab, index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode REFUSE_SLAVE_SEX = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			List<GameCharacter> hornySlaves = new ArrayList<>(slavesWantingToSexPlayer(Main.game.getHourOfDay()));
			boolean soloSex = hornySlaves.size()==1;
			List<String> names = new ArrayList<>();
			hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
			
			UtilText.addSpecialParsingString(String.valueOf(soloSex), true);
			UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
			
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "REFUSE_SLAVE_SEX", hornySlaves));
			
			Main.game.getPlayer().wakeUp();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(responseTab, index);
		}
		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER = new DialogueNode("Ваша комната", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<GameCharacter> slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"Желая освежиться, но не тратя на это слишком много времени, вы заходите в свою просторную ванную комнату при спальне и решаете быстро принять душ. Раздевшись, вы оставляете одежду у двери и подходите к своей роскошной душевой кабине из мрамора и стекла.");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getShowerSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>Открыв кран, вы расслабленно вздыхаете, чувствуя, как теплые струи воды стекают по вашему обнаженному телу. Не желая задерживаться в душе слишком долго, вы сосредотачиваетесь на быстром мытье...</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] showerSlots = new SexSlot[] {
					SexSlotStanding.STANDING_SUBMISSIVE,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND,
					SexSlotStanding.STANDING_SUBMISSIVE_TWO,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO};
			
			if(index==1) {
				return new Response("Закончить", "Закончить душ и вернуться в комнату.", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"Не желая проводить в душе слишком много времени, вы вскоре просите [npc.name] выключить воду и помочь вам вытереться. После этого вы вдвоем одеваетесь и возвращаетесь в комнату..."));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"Не желая проводить в душе слишком много времени, вы вскоре приказываете своим рабыням выключить воду и помочь вам вытереться. После этого вы все одеваетесь и возвращаетесь в комнату...");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"Спустя некоторое время вы чувствуете, что провели в душе достаточно, поэтому, выключив воду и вытеревшись пушистым полотенцем, вы одеваетесь и возвращаетесь в комнату...");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} else if(index==2) { // If you change this, be aware that it is called in AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER
				if(slavesWashing.isEmpty()) {
					return new Response("Секс", "В вашей спальне нет назначенных рабынь с правом ухода за телом, так что вам не с кем заняться сексом...", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size() && i<4; i++) {
					slaveSlots.put(slavesWashing.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Секс",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "Заняться доминантным сексом с [npc.name] в душе.")
							:"Заняться доминантным сексом со своими рабами в душе.",
						true, false,
						new SMShower(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								slaveSlots),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_SHOWER_SEX_AS_DOM", slavesWashing));
				
			} else if(index==3) {  // If you change this, be aware that it is called in AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER
				if(slavesWashing.isEmpty()) {
					return new Response("Субмиссивный секс", "В вашей спальне нет назначенных рабынь с правом ухода за телом, так что вам не с кем заняться подчиненным сексом...", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("Субмиссивный секс",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "Так как [npc.name] не испытывает к вам влечения, [npc.she] не желает выступать в роли доминанта в сексе...")
								:"Заняться доминантным сексом со своими рабами в душе.",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Субмиссивный секс",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "Позволить [npc.name] доминантно трахать вас в душе.")
										:"Позволь своим рабыням доминантно трахать тебя в душе.",
						true, true,
						new SMShower(SexPosition.STANDING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT))),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_SHOWER_SEX_AS_SUB", attractedSlaves));
			}
			
			return null;
		
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER = new DialogueNode("Ваша комната", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<GameCharacter> slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"Желая уделить время тщательному очищению, вы заходите в свою просторную ванную комнату при спальне и решаете принять долгий душ. Раздевшись, вы оставляете одежду у двери и подходите к своей роскошной душевой кабине из мрамора и стекла.");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getShowerSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>Открыв кран, вы удовлетворенно вздыхаете, чувствуя, как теплые струи воды стекают по вашему обнаженному телу. Решив, что не будет лишним немного расслабиться, вы не спешите, тщательно отмывая каждую клеточку кожи.</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Закончить", "Закончить душ и вернуться в комнату.", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"Убедившись, что каждый дюйм вашего тела сияет чистотой, вы просите [npc.name] выключить воду и помочь вам вытереться. После этого вы вдвоем одеваетесь и возвращаетесь в комнату..."));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"Убедившись, что каждый дюйм вашего тела сияет чистотой, вы приказываете своим рабыням выключить воду и помочь вам вытереться. После этого вы все одеваетесь и возвращаетесь в комнату...");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"Спустя некоторое время вы чувствуете, что провели в душе достаточно, поэтому, выключив воду и вытеревшись пушистым полотенцем, вы одеваетесь и возвращаетесь в комнату...");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} if(index==2) {
				return AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER.getResponse(responseTab, index);
				
			} else if(index==3) {
				return AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SHOWER_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getDescription() {
			return "Насладившись, твои рабыни напоминают тебе, что у тебя есть и другие дела, которыми нужно заняться...";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "AFTER_SHOWER_SEX", slavesWashing);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда вы вдоволь накупались, пора возвращаться в комнату.", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_BATH = new DialogueNode("Ваша комната", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"Желая расслабиться и привести себя в порядок, вы заходите в свою просторную ванную комнату при спальне и решаете принять долгую ванну. Раздевшись, вы оставляете одежду у двери и подходите к своей роскошной мраморной ванне.");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getBathSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>Открыв краны и набрав ванну, вы погружаетесь в горячую воду и издаете удовлетворенный вздох. Решив, что не будет лишним уделить время отдыху, вы не спешите, тщательно отмывая свое тело...</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] bathSlots = new SexSlot[] {
					SexSlotLyingDown.MISSIONARY,
					SexSlotLyingDown.BESIDE,
					SexSlotLyingDown.BESIDE_TWO,
					SexSlotLyingDown.BESIDE_THREE};
			
			if(index==1) {
				return new Response("Закончить", "Закончить купание и вернуться в свою комнату.", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"Убедившись, что каждый дюйм вашего тела сияет чистотой, вы вынимаете пробку, [pc.step] выходите из ванны и просите [npc.name] помочь вам вытереться. После этого вы вдвоем одеваетесь и возвращаетесь в свою комнату..."));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"Убедившись, что каждый дюйм вашего тела сияет чистотой, вы вынимаете пробку, [pc.step] выходите из ванны и велите своим рабыням помочь вам вытереться. После этого вы все одеваетесь и возвращаетесь в свою комнату...");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"Насладившись приятным расслабляющим купанием, вы чувствуете, что провели в ванне достаточно времени. Вынув пробку и вытеревшись, вы быстро одеваетесь и возвращаетесь в свою комнату...");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} else if(index==2) {
				if(slavesWashing.isEmpty()) {
					return new Response("Секс", "В вашей спальне нет назначенных рабынь с правом ухода за телом, так что вам не с кем заняться сексом...", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size() && i<4; i++) {
					slaveSlots.put(slavesWashing.get(i), i==0?SexSlotLyingDown.LYING_DOWN:bathSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("Секс",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "Заняться доминантным сексом с [npc.name] в ванне.")
							:"Заняться доминантным сексом со своими рабынями в ванне.",
						true, false,
						new SMBath(SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
								slaveSlots),
						null,
						null,
						AFTER_BATH_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_BATH_SEX_AS_DOM", slavesWashing));
				
			} else if(index==3) {
				if(slavesWashing.isEmpty()) {
					return new Response("Субмиссивный секс", "В вашей спальне нет назначенных рабынь с правом ухода за телом, так что вам не с кем заняться подчиненным сексом...", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("Субмиссивный секс",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "Так как [npc.name] не испытывает к вам влечения, [npc.she] не желает выступать в роли доминанта в сексе...")
								:"Заняться доминантным сексом со своими рабынями в ванне.",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), bathSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(attractedSlaves.size()), true);
				return new ResponseSex("Субмиссивный секс",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "Позволить [npc.name] доминантно трахать вас в ванне.")
										:"Позволить своим рабыням доминантно трахать вас в ванне.",
						true, true,
						new SMBath(SexPosition.LYING_DOWN,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
						null,
						null,
						AFTER_BATH_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_BATH_SEX_AS_SUB", attractedSlaves));
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_BATH_SEX = new DialogueNode("Завершено", "", true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
		}
		@Override
		public String getDescription() {
			return "Насладившись, твои рабыни напоминают тебе, что у тебя есть и другие дела, которыми нужно заняться...";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "AFTER_BATH_SEX", slavesWashing);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Теперь, когда вы вдоволь накупались, пора возвращаться в комнату.", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_MAKEUP = new DialogueNode("Прическа и макияж", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getMakeupTarget());
		}
		@Override
		public String getHeaderContent() {
			return MiscDialogue.getMakeupDialogue(true,
					BodyChanging.getTarget().isPlayer()
						?"Вы садитесь перед зеркалом, готовясь заняться своим внешним видом..."
						:UtilText.parse(BodyChanging.getTarget(), "Вы заставляете [npc.name] сесть перед зеркалом и готовитесь заняться улучшением [npc.her] внешности...")).getHeaderContent();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Закончить", "Закончить макияж перед зеркалом и вернуться в спальню.", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR = new DialogueNode("Календарь", "", true) {
		@Override
		public void applyPreParsingEffects() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>Вы подходите к одной стороне комнаты, где на стене приколот календарь. Он явно зачарован, ведь когда вы перелистываете страницы, обнаруживаете, что картинка на каждый месяц меняется в зависимости от ваших текущих мыслей.");
						
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				sb.append(" Когда вы думаете о каждом месяце, на странице появляется тематически одетый мужчина, инкуб или какой-нибудь зверочеловек.");
			} else {
				sb.append(" Когда вы думаете о каждом месяце, на странице появляется тематически одетая женщина, суккуб или какая-нибудь зверодевушка.");
			}
			
			if(Main.game.getPlayer().getCorruptionLevel()==CorruptionLevel.ZERO_PURE) {
				sb.append(" Чем больше вы листаете календарь туда-сюда, тем более раздетой становится фигура на каждой картинке, пока вы внезапно не осознаете, что делаете, и не отступаете в шоке.");
			} else {
				sb.append(" Чем больше вы листаете календарь туда-сюда, тем более раздетой становится фигура на каждой картинке, и вы чувствуете, как вас начинает слегка возбуждать...");
			}
			sb.append("</p>");
			
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				sb.append("<p>Внезапно вспомнив, что именно вы хотели посмотреть, вы пролистываете календарь, чтобы найти текущую дату,");
			} else {
				sb.append("<p>Вы так отвлеклись на сменяющиеся картинки, что на мгновение забыли, что именно хотели проверить. Потрясши головой, вы пролистываете календарь назад, чтобы узнать текущую дату,");
			}
			
			sb.append(" и видите, что сегодня <b style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>"
						+ Units.date(Main.game.getDateNow(), Units.DateType.LONG)
					+"</b>. Сделав быстрый расчет "+(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)<IntelligenceLevel.ONE_AVERAGE.getMaximumValue()?"(с небольшой помощью калькулятора в телефоне)":"")
					+ ", вы понимаете, что прошло уже <b style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>"+Main.game.getDayNumber()+" день"+(Main.game.getDayNumber()>1?"s":"")+"</b> с тех пор, как вы появились в этом мире.</p>");
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				sb.append("<p>[pc.thought(Погоди... "+Main.game.getDateNow().format(DateTimeFormatter.ofPattern("yyyy", Locale.ENGLISH))+"?! Мне нужно обсудить это с Lilaya...)]</p>");
			}
			
			sb.append("<p>Вы замечаете, что на каждой странице календаря есть несколько абзацев с описанием событий, происходящих в этом месяце.</p>");
			
			Main.game.getTextStartStringBuilder().append(sb.toString());
			
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.knowsDate);
		}
		@Override
		public String getContent() {
			return "";
		}

//		@Override
//		public String getResponseTabTitle(int index) {
//			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
//		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
//			if(responseTab==1) {
//				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
//			}
			if (index == 0) {
				return new Response("Назад", "Отойди от календаря.", ROOM);
			} else if(index==1) {
				return new Response("Январь", "Прочитать информацию на странице января. [style.italicsMinorBad(В январе пока нет особых событий.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JANUARY);
			} else if(index==2) {
				return new Response("Февраль", "Прочитать информацию на странице февраля. [style.italicsMinorBad(В феврале пока нет особых событий.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_FEBRUARY);
			} else if(index==3) {
				return new Response("Март", "Прочитать информацию на странице марта. [style.italicsMinorBad(В марте пока нет особых событий.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_MARCH);
			} else if(index==4) {
				return new Response("Апрель", "Прочитать информацию на странице апреля. [style.italicsMinorBad(В апреле пока нет особых событий.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_APRIL);
			} else if(index==5) {
				return new Response("Май", "Прочитать информацию на странице мая.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY);
			} else if(index==6) {
				return new Response("Июнь", "Прочитать информацию на странице июня.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE);
			} else if(index==7) {
				return new Response("Июль", "Прочитать информацию на странице июля. [style.italicsMinorBad(В июле сейчас нет особых событий.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JULY);
			} else if(index==8) {
				return new Response("Огаст", "Прочитать информацию на странице августа. [style.italicsMinorBad(В августе сейчас нет особых событий.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_AUGUST);
			} else if(index==9) {
				return new Response("Сентябрь", "Прочитать информацию на странице сентября. [style.italicsMinorBad(В сентябре сейчас нет особых событий.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_SEPTEMBER);
			} else if(index==10) {
				return new Response("Октябрь", "Прочитать информацию на странице октября.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER);
			} else if(index==11) {
				return new Response("Ноябрь", "Прочитать информацию на странице ноября. [style.italicsMinorBad(В ноябре сейчас нет особых событий.)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_NOVEMBER);
			} else if(index==12) {
				return new Response("Декабрь", "Прочитать информацию на странице декабря.", AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JANUARY = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JANUARY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>[style.italicsMinorBad(В январе сейчас нет особых событий.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Январь", "Вы уже читаете страницу календаря, относящуюся к январю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_FEBRUARY = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.FEBRUARY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>[style.italicsMinorBad(В феврале сейчас нет особых событий.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Февраль", "Вы уже читаете страницу календаря, относящуюся к февралю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_MARCH = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.MARCH));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>[style.italicsMinorBad(В марте сейчас нет особых событий.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("Март", "Вы уже читаете страницу календаря, относящуюся к марту.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_APRIL = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.APRIL));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>[style.italicsMinorBad(В апреле сейчас нет особых событий.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==4) {
				return new Response("Апрель", "Вы уже читаете страницу календаря, посвященную апрелю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.MAY));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>Май</span></h4><h6 style='text-align:center;'><span style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>Неделя Матери</span><br/>8–14 мая</h6><p><i>Третья неделя мая — время чествования матерей, материнства и самой природы связи между матерью и ребенком. В это время всем жителям Dominion щедро и бесплатно раздаются средства, повышающие фертильность; волонтеры разносят их по главным бульварам. Так Lilith проявляет свою любовь к матерям и заботится о том, чтобы их стало еще больше!</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5) {
				return new Response("Май", "Вы уже читаете страницу календаря, посвященную маю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JUNE));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>Июнь</span></h4><h6 style='text-align:center;'><span style='color:"+PresetColour.BASE_BLUE.toWebHexString()+";'>Неделя Отца</span><br/>15–21 июня</h6><p><i>Третья неделя июня — время чествования отцов, отцовства и самой природы связи между отцом и ребенком. В это время всем жителям Dominion щедро и бесплатно раздаются средства, повышающие фертильность; волонтеры разносят их по главным бульварам. Так Lilith проявляет свою любовь к отцам и заботится о том, чтобы их стало еще больше!</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("Июнь", "Вы уже читаете страницу календаря, посвященную июню.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JULY = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JULY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>[style.italicsMinorBad(В июле специальных событий пока нет.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==7) {
				return new Response("Июль", "Вы уже читаете страницу календаря, посвященную июлю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_AUGUST = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.AUGUST));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>[style.italicsMinorBad(В августе пока нет особых событий.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==8) {
				return new Response("Огаст", "Вы уже читаете страницу календаря, посвященную августу.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_SEPTEMBER = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.SEPTEMBER));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>[style.italicsMinorBad(В сентябре пока нет особых событий.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==9) {
				return new Response("Сентябрь", "Вы уже читаете страницу календаря, посвященную сентябрю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.OCTOBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>Октябрь</span></h4><h6 style='text-align:center;'><span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Месяц Lilith</span><br/>Весь месяц</h6><p><i>Lilith сама выбрала октябрь месяцем, в который все жители Dominion должны продемонстрировать свою преданность своей славной королеве! Знамена и ленты, обычно выполненные в традиционных цветах Lilith — оранжевом, фиолетовом и черном, — с гордостью развеваются на каждом здании, чтобы показать нашей королеве, насколько верны ей её подданные! И хотя праздновать правление Lilith должны все граждане, самые истовые последовательницы облачаются в традиционные демонические костюмы, чтобы доказать свою лояльность.</p><p>Официально признанный «Культ Lilith» — самая фанатичная группа сторонниц нашей королевы, и их очень легко заметить в октябре, так как они отказываются носить что-либо, кроме традиционных ведьминских нарядов, подобных тем, что носила сама Lilith столетия назад. Если в остальное время года они довольствуются совершением актов преданности в частном порядке, то в октябре эти культистки могут стать весьма ревностными и порой даже заходят так далеко, что подходят к случайным прохожим, требуя от них проявления верности!</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==10) {
				return new Response("Октябрь", "Вы уже читаете страницу календаря, посвященную октябрю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_NOVEMBER = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.NOVEMBER));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>[style.italicsMinorBad(В ноябре пока нет особых событий.)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				return new Response("Ноябрь", "Вы уже читаете страницу календаря, посвященную ноябрю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER = new DialogueNode("Календарь", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.DECEMBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>Декабрь</span></h4><h6 style='text-align:center;'><span style='color:"+PresetColour.BASE_GOLD.toWebHexString()+";'>Йоль</span><br/>Весь месяц</h6><i><p>Празднование Йоля проходит на протяжении всего декабря, а иногда затягивается даже на январь и февраль! Обмен подарками, пиршества и вечеринки — вот как празднуют Йоль. Поскольку это торжество совпадает с прибытием олене-морфов в Dominion, сложилась традиция дарить на Йоль вещи, купленные именно у этих олене-морфов.</p><p>Персонажем, ассоциирующимся с этим сезоном, является лилин «Йольнир» (что означает «йольский» или «фигура Йоля»). Об этом лилине известно немного, за исключением очевидного факта, что его имя нарушает традицию, согласно которой имена всех лилинов начинаются на «Л», и того, что он является предводителем «Дикой Охоты».</p><p>«Дикая Охота», представляющая собой бродячую орду призванных аркана-элементалей, была изгнана из Dominion много лет назад и теперь встречается только во время Йоля в полях Фолой и близлежащих лесах.</p></i>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==12) {
				return new Response("Декабрь", "Вы уже читаете страницу календаря, посвященную декабрю.", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};
	

	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME = new DialogueNode("Ваша комната", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME", NightlifeDistrict.getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Секс (доминант)", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Заняться доминантным сексом с [npc.name]."),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
						null,
						null), BACK_HOME_AFTER_CLUBBER_SEX, UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEX_AS_DOM", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==2) {
				return new ResponseSex("Секс (сабмиссив)", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Заниматься подчиненным сексом с [npc.name]."),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), BACK_HOME_AFTER_CLUBBER_SEX, UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEX_AS_SUB", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==4) {
				return new Response("Попрощаться",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что вы передумали, отправив [npc.herHim] домой с обещанием увидеть [npc.herHim] в клубе в другой раз.</br>[style.italicsGood(Сохранить этого персонажа, которого затем можно будет снова встретить в клубе.)]"),
						AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_CHANGE_MIND", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==5) {
				return new Response("Отправить домой",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что вы передумали, и резко отправить [npc.herHim] домой.</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_CHANGE_MIND_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BACK_HOME_AFTER_CLUBBER_SEX = new DialogueNode("Ваша комната", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_CLUBBER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_CLUBBER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Увидеться снова",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Сказать [npc.name], что надеетесь снова увидеть [npc.herHim].</br>[style.italicsGood(Сохраняет этого персонажа, которого затем можно будет снова встретить в клубе.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==2) {
				return new Response("Надеяться, что нет (мягко)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Ответить уклончиво, втайне надеясь, что вы больше не увидите [npc.name].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==3) {
				return new Response("Надеяться, что нет (грубо)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "Грубо сказать [npc.name], что вас интересовал только трах [npc.herHim].</br>[style.italicsBad(Удаляет этого персонажа из игры.)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode BACK_HOME_AFTER_SEX = new DialogueNode("Ваша комната", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME = new DialogueNode("Ваша комната", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};
}
