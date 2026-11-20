package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.3.9
 * @version 0.3.9
 * @author Innoxia
 */
public class LilayaMilkingRoomDialogue {
	
	private static GameCharacter getMilkingTarget() {
		return MilkingRoom.getTargetedCharacter();
	}
	
	public static final DialogueNode MILKING_ROOM = new DialogueNode("Комната", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());
			StringBuilder sb = new StringBuilder();

			sb.append("<p>"
						+ "<b style='color:"+PlaceUpgrade.LILAYA_MILKING_ROOM.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_MILKING_ROOM.getName()+"</b><br/>Эта просторная комната была переоборудована в доильный зал, где восемь ваших рабынь могут отдавать свои различные жидкости. Вдоль левой стены установлены четыре аппарата, а остальные четыре расположены на противоположной стороне комнаты.</p>");
			
			sb.append(room.getRoomDescription());
			sb.append("<br/>");
			
			for(AbstractPlaceUpgrade up : Main.game.getPlayerCell().getPlace().getPlaceUpgrades()) {
				if(!up.isCoreRoomUpgrade()) {
					sb.append("<p>"
								+ "<b style='color:"+up.getColour().toWebHexString()+";'>"+up.getName()+"</b><br/>"
								+ up.getRoomDescription(Main.game.getPlayerCell())
							+ "</p>");
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
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(Main.game.getPlayerCell().getType(), Main.game.getPlayerCell().getLocation());
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Управлять комнатой", "Перейти к экрану управления этой конкретной комнатой.", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("Управлять комнатой", "Чтобы получить доступ к этому меню, вам нужна лицензия работорговца или разрешение от Lilaya, чтобы поселить здесь своих друзей или кукол!",  null);
				}
				
			}  else if (index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("Управлять людьми", "Перейти к экрану управления вашими рабынями и дружелюбными жильцами.", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("Управлять людьми", "Чтобы получить доступ к этому меню, вам нужна лицензия работорговца или разрешение от Lilaya, чтобы поселить здесь своих друзей или кукол!",  null);
				}
				
			} else if(index>=3 && index<6) {
				return null;
				
			} else if(index==6) {
				if(getMilkingTarget().getBreastRawStoredMilkValue()==0) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "В груди [npc.namePos] нет молока, поэтому [npc.she] сейчас невозможно подоить!"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] не может добраться до сосков [npc.her], поэтому [npc.she] сейчас невозможно подоить!"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(), "Для [npc.name] нет свободных доильных аппаратов!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							UtilText.parse(getMilkingTarget(),
									"Так как [npc.sheIs] не твоя рабыня, [npc.name] позволит тебе это сделать, только если [npc.she] <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> тебя."),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative())) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]")),
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()
												?"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у обоих "
													+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" и "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" фетишей."
												:"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у "+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" фетиш.")
											:"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" фетиш.")),
							null);
					
				} else {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.Name]")),
							"Использовать запасное оборудование в этой комнате, чтобы подоить "+(getMilkingTarget().isPlayer()?"саму(ую)":UtilText.parse(getMilkingTarget(), "[npc.name]"))+".",
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualMilkPerHour(getMilkingTarget());
							if(milked < getMilkingTarget().getBreastRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(getMilkingTarget())) {
								milked = (int) Math.min(getMilkingTarget().getBreastRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(getMilkingTarget()));
							}
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getMilk(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_TARGET", getMilkingTarget()));
							}
							
							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.MILK.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " [npc.milk] добавлено в хранилище этой комнаты!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualMilkPerHour(getMilkingTarget());
							getMilkingTarget().incrementBreastStoredMilk(-milked);
							return true;
						}
					};
				}
				
			} else if(index==7) {
				if(!getMilkingTarget().hasPenisIgnoreDildo()) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" сперма",
							UtilText.parse(getMilkingTarget(), "У [npc.Name] [npc.do] нет пениса, так что [npc.she] не может выделять сперму..."),
							null);
					
				} else if(getMilkingTarget().getPenisRawStoredCumValue()==0) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" сперма",
							UtilText.parse(getMilkingTarget(), "В [npc.namePos] нет спермы, так что [npc.her] нельзя подоить..."),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" сперма",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] не может добраться до [npc.her] члена, так что [npc.she] пока нельзя подоить..."),
							null);
					
				} else if(!getMilkingTarget().isAbleToOrgasm()) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" сперма",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] не может достичь оргазма, поэтому из него нельзя выдоить [npc.her] спермы!"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" сперма",
							UtilText.parse(getMilkingTarget(), "Для [npc.name] нет свободных доильных аппаратов!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion() && !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" сперма",
							UtilText.parse(getMilkingTarget(),
									"Так как [npc.sheIs] не твоя рабыня, [npc.name] позволит тебе это сделать, только если [npc.she] <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> тебя."),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative())) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" сперма",
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_CUM_STUD).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative()
												?"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у обоих "
													+Fetish.FETISH_CUM_STUD.getName(getMilkingTarget())+" и "+Fetish.FETISH_PENIS_GIVING.getName(getMilkingTarget())+" фетишей."
												:"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у "+Fetish.FETISH_CUM_STUD.getName(getMilkingTarget())+" фетиш.")
											:"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у "+Fetish.FETISH_PENIS_GIVING.getName(getMilkingTarget())+" фетиш.")),
							null);
					
				} else {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" сперма",
							UtilText.parse(getMilkingTarget(), "Использовать запасное оборудование для дойки в этой комнате, чтобы подоить член [npc.namePos]."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualCumPerHour(getMilkingTarget());
							room.incrementFluidStored(new FluidStored(getMilkingTarget(), getMilkingTarget().getCum(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_COCK_TARGET", getMilkingTarget()));
							}

							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.CUM.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " [npc.cum] добавлено в хранилище этой комнаты!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualCumPerHour(getMilkingTarget());
							getMilkingTarget().incrementPenisStoredCum(-milked);
							getMilkingTarget().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed());
							return true;
						}
					};
				}
				
			} else if(index==8) {
				if(!getMilkingTarget().hasVagina()) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" девичья сперма",
							UtilText.parse(getMilkingTarget(), "[npc.Name] [npc.do]у [npc.she] нет влагалища, так что она не может выделять девичью смазку..."),
							null);
					
				} else if(getMilkingTarget().getVaginaWetness().getValue()==0) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" девичья сперма",
							UtilText.parse(getMilkingTarget(), "Киска [npc.NamePos] совершенно сухая и не может произвести ни единой капли девичьей смазки..."),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" девичья сперма",
							UtilText.parse(getMilkingTarget(), "У [npc.NameIsFull] нет доступа к [npc.her] киске, поэтому сейчас из неё нельзя выдоить [npc.her] девичьей смазки..."),
							null);
					
				} else if(!getMilkingTarget().isAbleToOrgasm()) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" девичья сперма",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] не может достичь оргазма, поэтому из неё нельзя выдоить [npc.her] девичьей смазки!"),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" девичья сперма",
							UtilText.parse(getMilkingTarget(), "Для [npc.name] нет свободных доильных аппаратов!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" девичья сперма",
							UtilText.parse(getMilkingTarget(),
									"Так как [npc.sheIs] не твоя рабыня, [npc.name] позволит тебе это сделать, только если [npc.she] <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> тебя."),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" девичья сперма",
							UtilText.parse(getMilkingTarget(), "Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у "+Fetish.FETISH_VAGINAL_RECEIVING.getName(getMilkingTarget())+" фетиш."),
							null);
					
				}else {
					return new Response(
							"Доить "+(getMilkingTarget().isPlayer()?"себя":UtilText.parse(getMilkingTarget(), "[npc.NamePos]"))+" девичья сперма",
							UtilText.parse(getMilkingTarget(), "Использовать запасное оборудование для дойки в этой комнате, чтобы подоить киску [npc.namePos]."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualGirlcumPerHour(getMilkingTarget());
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getGirlcum(), milked), milked);
							
							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_PUSSY_TARGET", getMilkingTarget()));
							}

							Main.game.getTextEndStringBuilder().append(
									"<p style='text-align:center; color:"+PresetColour.GIRLCUM.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " [npc.girlcum] добавлено в хранилище этой комнаты!")
									+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							getMilkingTarget().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed());
							return true;
						}
					};
				}
				
				
			} else if(index==9) {
				if(!getMilkingTarget().hasBreastsCrotch()) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Доить собственные вымени"
									:UtilText.parse(getMilkingTarget(), "Доить вымя [npc.NamePos]")),
							UtilText.parse(getMilkingTarget(), "У [npc.Name] [npc.do] нет вымени, чтобы его доить!"),
							null);
					
				} else if(getMilkingTarget().getBreastCrotchRawStoredMilkValue()==0) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Доить себя [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Доить [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "В [npc.namePos] [npc.crotchBoobs] нет запасов молока, так что [npc.she] сейчас нельзя подоить!"),
							null);
					
				} else if(!getMilkingTarget().isAbleToAccessCoverableArea(CoverableArea.NIPPLES_CROTCH, true)) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Доить себя [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Доить [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] не может добраться до [npc.her] [npc.crotchNipples], так что сейчас подоить не получится..."),
							null);
					
				} else if(charactersPresent.size()==8) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Доить себя [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Доить [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "Для [npc.name] нет свободных доильных аппаратов!"),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& getMilkingTarget().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue()) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Доить себя [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Доить [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(),
									"Так как [npc.sheIs] не твоя рабыня, [npc.name] позволит тебе это сделать, только если [npc.she] <span style='color:"+AffectionLevel.POSITIVE_FOUR_LOVE.getColour().toWebHexString()+";'>"+AffectionLevel.POSITIVE_FOUR_LOVE.getDescriptor()+"</span> тебя."),
							null);
					
				} else if(!getMilkingTarget().isPlayer()
						&& getMilkingTarget().isAbleToRefuseSexAsCompanion()
						&& !getMilkingTarget().isSlave()
						&& (getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative() || getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative())) {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Доить себя [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Доить [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(),
									(getMilkingTarget().getFetishDesire(Fetish.FETISH_LACTATION_SELF).isNegative()
											?(getMilkingTarget().getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()
												?"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у обоих "
													+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" и "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" фетишей."
												:"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у "+Fetish.FETISH_LACTATION_SELF.getName(getMilkingTarget())+" фетиш.")
											:"Поскольку [npc.sheIs] не твоя рабыня, [npc.name] не позволит тебе этого сделать, так как [npc.sheHasFull] это вызовет нежелание у "+Fetish.FETISH_BREASTS_SELF.getName(getMilkingTarget())+" фетиш.")),
							null);
					
				} else {
					return new Response(
							(getMilkingTarget().isPlayer()
									?"Доить себя [pc.crotchBoobs]"
									:UtilText.parse(getMilkingTarget(), "Доить [npc.NamePos] [npc.crotchBoobs]")),
							UtilText.parse(getMilkingTarget(), "Использовать запасное доильное оборудование в этой комнате, чтобы подоить [npc.namePos] [npc.crotchBoobs]."),
							MILKED) {
						@Override
						public void effects() {
							int milked = MilkingRoom.getActualCrotchMilkPerHour(getMilkingTarget());
							if(milked < getMilkingTarget().getBreastCrotchRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(getMilkingTarget())) {
								milked = (int) Math.min(getMilkingTarget().getBreastCrotchRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(getMilkingTarget()));
							}
							room.incrementFluidStored(new FluidStored(getMilkingTarget().getId(), getMilkingTarget().getMilkCrotch(), milked), milked);

							if(getMilkingTarget().isPlayer()) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_PLAYER"));
								
							} else if(getMilkingTarget().getPartyLeader()==null) {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_SLAVE_TARGET", getMilkingTarget()));
								
							} else {
								Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("misc/milking", "MILKING_UDDERS_TARGET", getMilkingTarget()));
							}
							
							Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center; color:"+PresetColour.MILK.toWebHexString()+";'>"
										+ Units.fluid(milked) + UtilText.parse(getMilkingTarget(), " [npc.crotchMilk] добавлено в хранилище этой комнаты!")
								+ "</p>");
						}
						@Override
						public boolean postEndTurnEffects() {
							int milked = MilkingRoom.getActualCrotchMilkPerHour(getMilkingTarget());
							getMilkingTarget().incrementBreastCrotchStoredMilk(-milked);
							return true;
						}
					};
				}
				
			} else if(index==10) {
				if(Main.game.getCharactersPresent().isEmpty()) {
					return new ResponseEffectsOnly(
							"Цель: <span style='color:"+getMilkingTarget().getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(getMilkingTarget(), "[npc.Name]")+"</span>",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] выбран(а) в качестве цели — человека, которого будут доить и в которого будут закачивать жидкости [npc.herHim]. Если бы комната не была пуста, вы могли бы использовать эту кнопку для переключения между доступными целями.")) {
						
					};
					
				} else {
					return new Response(
							"Цель: <span style='color:"+getMilkingTarget().getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(getMilkingTarget(), "[npc.Name]")+"</span>",
							UtilText.parse(getMilkingTarget(), "[npc.NameIsFull] выбран(а) в качестве цели — человека, которого будут доить и в которого будут закачивать жидкости [npc.herHim]. Нажмите эту кнопку, чтобы переключиться между доступными целями."),
							null) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getCurrentDialogueNode();
						}
						@Override
						public void effects() {
							List<GameCharacter> targetCharactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
							targetCharactersPresent.add(Main.game.getPlayer());
							for(int i=0; i<targetCharactersPresent.size();i++) {
								if(targetCharactersPresent.get(i).equals(getMilkingTarget())) {
									if(i==targetCharactersPresent.size()-1) {
										MilkingRoom.setTargetedCharacter(targetCharactersPresent.get(0));
									} else {
										MilkingRoom.setTargetedCharacter(targetCharactersPresent.get(i+1));
									}
									break;
								}
							}
							Main.game.updateResponses();
						}
					};
				}
				
			} else if(index-11<charactersPresent.size()) {
				GameCharacter slave = charactersPresent.get(index-11);
				return LilayaHomeGeneric.interactWithNPC(slave);
			}
				
			return null;
		}
	};
	
	public static final DialogueNode MILKED = new DialogueNode("Комната", ".", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Отстегнитесь от доильного аппарата и идите дальше.", MILKED) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	/**
	 * Used in OccupantController when a character consumes fluids.
	 */
	public static final DialogueNode INGEST = new DialogueNode("Комната", ".", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public boolean isRegenerationDisabled() {
			return true;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить", "Вы гадаете, что делать дальше...", INGEST) {
					@Override
					public DialogueNode getNextDialogue() {
						return Main.game.getDefaultDialogue(false);
					}
				};
			}
			return null;
		}
	};
}
