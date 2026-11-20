package com.lilithsthrone.world.places;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaDiningHallDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaDressingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaMilkingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaOfficeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSlaveLoungeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSpa;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomArthur;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;


/**
 * @since 0.1.85
 * @version 0.3.9
 * @author Innoxia
 */
public class PlaceUpgrade {

	//**** MISC. UPGRADES ****//
	
	public static final AbstractPlaceUpgrade SLAVERY_ADMINISTRATION_CELLS = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"Мрачные камеры",
			"-",
			"",
			"Финч совсем не заботится о содержании камер под Администрацией рабства, и в результате любые рабы, содержащиеся здесь, быстро теряют всю свою привязанность и покорность.",
			200,
			0,
			0,
			1000,
			-0.5f,
			-0.5f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_EMPTY_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_GREY,
			"Пустая комната",
			"Роуз вернет эту комнату в ее первоначальное состояние, из-за чего она станет непригодной для содержания кого-либо из ваших рабов.",
			"Эта комната пуста, и если вы захотите поселить здесь своих рабов, потребуется провести перепланировку.",
			"Эта комната не занята, и хотя Роуз, кажется, отлично справляется с уборкой и натиранием пыли, кажется обидным, что она не используется в полную силу...",
			2000,
			0,
			0,
			0,
			0,
			0,
			null) {
		
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_MILKING_ROOM)) {
				Main.game.getOccupancyUtil().removeMilkingRoom(Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation()));
			}
			
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_EMPTY_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			boolean nonCompanionCharactersPresent = false;
			if(cell.getCharactersPresentIds()!=null) {
				for(String id : cell.getCharactersPresentIds()) {
					if(!id.equals(Main.game.getPlayer().getId()) && !Main.game.getPlayer().getCompanionsId().contains(id)) {
						nonCompanionCharactersPresent = true;
					}
				}
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() || nonCompanionCharactersPresent) {
				return new Value<>(false, "Чтобы купить это улучшение, комната должна быть свободна и не использоваться в данный момент.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_ARTHUR_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.RACE_HUMAN,
			"Комната Arthur",
			"Помогите Роуз перенести в эту комнату арканные инструменты, чтобы сделать ее подходящей для проживания Arthur. <b>Это постоянное изменение, которое невозможно отменить!</b>",
			"Теперь эта комната принадлежит Arthur, который использует ее как личную лабораторию, совмещенную со спальней.",
			"Эта комната не занята, и хотя Роуз, кажется, отлично справляется с уборкой и натиранием пыли, кажется обидным, что она не используется в полную силу...",
			0,
			0,
			0,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return RoomArthur.ROOM_ARTHUR_INSTALLATION;
		}
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			place.setPlaceType(PlaceType.LILAYA_HOME_ARTHUR_ROOM);
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_ARTHUR_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			c.getPlace().setName("Комната Arthur");
			if(Main.game.isStarted()) {
				Main.game.getNpc(Arthur.class).setLocation(c.getType(), c.getLocation(), true);
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS)
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_ARTHUR_ROOM).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_ARTHUR_ROOM).isEmpty()) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};


	//**** PLAYER'S ROOM UPGRADES ****//

	public static final AbstractPlaceUpgrade LILAYA_PLAYER_ROOM_BED = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_GOLD,
			"Кровать императорского размера",
			"Замените вашу нынешнюю королевскую кровать на огромную кровать «императорского размера». [style.italicsGood(Это улучшит бонус «хорошо отдохнувший», получаемый во время отдыха в вашей комнате.)]",
			"Ваша старая королевская кровать была заменена на огромную кровать «императорского размера». [style.italicsGood(Бонус «хорошо отдохнувший», получаемый во время отдыха в вашей комнате, был улучшен!)]",
			"Ваша старая королевская кровать была заменена на огромную кровать «императорского размера», которая занимает доминирующее положение у одной из стен вашей комнаты. Ее удобный матрас, пушистые подушки и теплое одеяло гарантируют, что после сна вы всегда будете чувствовать себя превосходно отдохнувшим.",
			10000,
			-5000,
			0,
			0,
			0.2f,
			-0.1f,
			null) {
		public Value<Boolean, String> getAvailability(Cell cell) {
			return new Value<>(true, "");
		}
	};

	//**** DOLL CLOSET ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DOLL_CLOSET = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"Шкаф для кукол",
			"Роуз вынесет из этой комнаты всю мебель, что позволит использовать ее как хранилище для всех ваших секс-кукол.",
			"Эта комната была переоборудована в подходящее место для хранения ваших секс-кукол.",
			"Вы полностью освободили эту комнату, превратив ее в хранилище для всех ваших секс-кукол.",
			250,
			0,
			0,
			12,
			0,
			0,
			null) {
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomDoll", PresetColour.RACE_DOLL, PresetColour.BASE_WHITE, PresetColour.BASE_WHITE);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_DOLL_STORAGE)) {
				return new Value<>(false, "Чтобы установить шкаф для кукол, вам сначала нужно получить разрешение Lilaya на это улучшение.");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_DOLL_CLOSET) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	//**** GUEST ROOM ****//
	
	public static final AbstractPlaceUpgrade LILAYA_GUEST_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"Гостевая комната",
			"Роуз подготовит эту комнату для одного из ваших гостей, сделав её подходящей для проживания одного человека. Пусть это и не самый экономный вариант, но обитатель этой комнаты, без сомнения, оценит наличие собственного личного пространства.",
			"Эта комната была переоборудована в подходящее место для проживания одного из ваших гостей.",
			"Вы заплатили за то, чтобы превратить эту комнату в простую гостевую спальню. У одной из стен стоит односпальная кровать, застеленная простым белым одеялом. Рядом находится скромная прикроватная тумбочка с лампой на аркане. Помимо этого, единственная другая мебель здесь — это деревянный Гардероб и комод.",
			2000,
			0,
			100,
			1,
			0,
			0,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomGuest", PresetColour.BASE_GREEN_LIGHT);
		}
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
				return new Value<>(false, "Чтобы обустроить гостевую спальню, вам сначала нужно найти кого-то, кого вы пригласите жить с вами, а затем получить разрешение Lilaya на это улучшение.");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_GUEST_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};

	
	//**** SLAVE UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.GENERIC_ARCANE,
			"Комната рабыни",
			"Роуз подготовит эту комнату так же, как и для любого другого гостя, сделав её подходящей для проживания всего одной вашей рабыни. Хотя это не самый выгодный выбор, если вы планируете владеть большим количеством рабов, обитательница этой комнаты, без сомнения, оценит наличие собственного личного пространства.",
			"Эта комната была переоборудована в подходящее место для проживания одной из ваших рабынь.",
			"Вы заплатили за переоборудование этой комнаты в простые рабские покои. У одной из стен стоит односпальная кровать, застеленная простым белым одеялом. Рядом находится простая прикроватная тумбочка с лампой на аркане. Помимо этого, единственная другая мебель здесь — это деревянный гардероб и комод.",
			2000,
			0,
			100,
			1,
			0,
			0,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlave", PresetColour.BASE_CRIMSON);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "Вы заплатили за переоборудование этой комнаты в простые рабские покои. У одной из стен стоит уютная двуспальная кровать, застеленная теплым пушистым одеялом. Рядом находится простая прикроватная тумбочка с лампой на аркане. Помимо этого, единственная другая мебель здесь — это деревянный гардероб и комод.";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "Вы заплатили за переоборудование этой комнаты в простые рабские покои. У одной из стен стоит неудобная односпальная кровать, накрытая тонким одеялом. Рядом находится простая прикроватная тумбочка с лампой на аркане. Помимо этого, единственная другая мебель здесь — это деревянный гардероб и комод.";
		
			}else {
				return "Вы заплатили за переоборудование этой комнаты в простые рабские покои. У одной из стен стоит односпальная кровать, застеленная простым белым одеялом. Рядом находится простая прикроватная тумбочка с лампой на аркане. Помимо этого, единственная другая мебель здесь — это деревянный гардероб и комод.";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_SLAVE_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOUBLE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"Двуспальная рабская комната",
			"Роуз подготовит эту комнату так же, как и для любого другого гостя, сделав её подходящей для размещения двух ваших рабов. Хотя это обходится дешевле, чем выделять каждому рабу отдельную комнату, обитатели, без сомнения, будут немного раздосадованы тем, что им приходится делить личное пространство с другим рабом, а также получат возможность сговариваться друг с другом против вас.",
			"Эта комната была переоборудована в подходящее место для размещения двух ваших рабов.",
			"Вы заплатили за переоборудование этой комнаты, чтобы она подходила для размещения двух ваших рабов. У противоположных стен стоят две односпальные кровати, застеленные простыми белыми одеялами. Рядом с каждой из них стоит простая тумбочка, укомплектованная лампой на аркане. Помимо этого, единственная другая мебель здесь — это один деревянный гардероб и одинокий комод.",
			3500,
			0,
			100,
			2,
			-0.05f,
			-0.05f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveDouble", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "Вы заплатили за переоборудование этой комнаты, чтобы она подходила для размещения двух ваших рабов. У противоположных стен стоят две двуспальные кровати, застеленные теплыми пушистыми одеялами. По обе стороны от них стоят простые прикроватные тумбочки, каждая со своей лампой на аркане. Помимо этого, единственная другая мебель здесь — это пара деревянных гардеробов и два комода.";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "Вы заплатили за переделку этой комнаты, чтобы она подходила для размещения двух ваших рабынь. У противоположных стен стоят две неудобные односпальные кровати, застеленные тонкими одеялами. Рядом с каждой стоит простая тумбочка, укомплектованная лампой на аркане. Помимо этого, единственная другая мебель здесь — это один деревянный гардероб и одинокий комод.";
		
			}else {
				return "Вы заплатили за переоборудование этой комнаты, чтобы она подходила для размещения двух ваших рабов. У противоположных стен стоят две односпальные кровати, застеленные простыми белыми одеялами. Рядом с каждой из них стоит простая тумбочка, укомплектованная лампой на аркане. Помимо этого, единственная другая мебель здесь — это один деревянный гардероб и одинокий комод.";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty() && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM);
				
			} else {
				for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
					if(upgrade != LILAYA_SLAVE_ROOM_DOUBLE) {
						place.removePlaceUpgrade(c, upgrade);
					}
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_QUADRUPLE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"Четверная комната для рабынь",
			"Роза подготовит эту комнату так же, как и для любого другого гостя, сделав её подходящей для размещения четырех ваших рабынь. Хотя это гораздо выгоднее, чем выделять каждой рабыне отдельную комнату, обитательницы, без сомнения, будут немного раздосадованы тем, что им приходится делить личное пространство с другой рабыней, а также получат массу возможностей для заговоров против вас.",
			"Эта комната была переделана в подходящее место для размещения четырех ваших рабынь.",
			"Вы заплатили за переделку этой комнаты, чтобы она подходила для размещения четырех ваших рабынь. Четыре односпальные кровати, застеленные простыми белыми одеялами, стоят вдоль стен. Рядом с каждой — простая тумбочка, укомплектованная лампой на аркане. Помимо этого, единственная другая мебель здесь — это пара деревянных гардеробов и два комода.",
			6000,
			0,
			100,
			4,
			-0.1f,
			-0.2f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveQuadruple", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return "Вы заплатили за переделку этой комнаты, чтобы она подходила для размещения четырех ваших рабынь. Вдоль стен стоят четыре двуспальные кровати, укрытые теплыми пушистыми одеялами. По обе стороны от каждой кровати стоят простые прикроватные тумбочки с лампами, работающими на энергии Арканы. Помимо них, единственная другая мебель здесь — это четыре деревянных Гардероба и четыре комода.";
				
			} else if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return "Вы заплатили за переделку этой комнаты, чтобы она подходила для размещения четырех ваших рабынь. Вдоль стен стоят четыре неудобные односпальные кровати, накрытые тонкими одеялами. Рядом с каждой стоит простая прикроватная тумбочка с лампой на энергии Арканы. Помимо них, единственная другая мебель здесь — это пара деревянных Гардеробов и два комода.";
		
			}else {
				return "Вы заплатили за переделку этой комнаты, чтобы она подходила для размещения четырех ваших рабынь. Вдоль стен стоят четыре односпальные кровати, застеленные простыми белыми одеялами. Рядом с каждой стоит простая прикроватная тумбочка с лампой на энергии Арканы. Помимо них, единственная другая мебель здесь — это один деревянный Гардероб и одинокий комод.";
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()
					 && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)
					 && !cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM);
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				place.removePlaceUpgrade(c, LILAYA_SLAVE_ROOM_DOUBLE);
				
			} else {
				for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
					if(upgrade != LILAYA_SLAVE_ROOM_QUADRUPLE) {
						place.removePlaceUpgrade(c, upgrade);
					}
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOWNGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"Маленькая стальная кровать",
			"Замените кровать в этой комнате на стальную раму с неудобным матрасом, жесткой подушкой и тонким одеялом. Эти изменения не оставят обитательницам сомнений в том, что они — рабыни.",
			"В этой комнате установили маленькую односпальную кровать с неудобным матрасом, жесткой подушкой и тонким одеялом.",
			"Маленькая односпальная кровать с неудобным матрасом, жесткой подушкой и тонким одеялом стоит у одной из стен. То, что обитателю комнаты приходится спать в таких неудобствах, определенно закрепит в его сознании мысль о том, что он раб, но в то же время он наверняка станет еще сильнее тебя недолюбливать...",
			250,
			100,
			-10,
			0,
			-0.1f,
			0.2f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return "Все четыре односпальные кровати в этой комнате заменили на простые модели в стальных рамах, оснащенные неудобными матрасами, жесткими подушками и тонкими одеялами. Такие неудобные спальные места определенно помогут обитателям комнаты смириться с тем, что они рабы, но в то же время они наверняка станут еще сильнее тебя недолюбливать...";
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return "Пара односпальных кроватей в этой комнате заменили на простые модели в стальных рамах, оснащенные неудобными матрасами, жесткими подушками и тонкими одеялами. Такие неудобные спальные места определенно помогут обитателям комнаты смириться с тем, что они рабы, но в то же время они наверняка станут еще сильнее тебя недолюбливать...";
				
			} else {
				return "Маленькая односпальная кровать в этой комнате была заменена на простую кровать со стальным каркасом, укомплектованную неудобным матрасом, жесткой подушкой и тонким одеялом. Предоставление обитателю этой комнаты столь неудобного места для сна определенно поможет ему смириться с тем, что он раб, но в то же время это неизбежно заставит его любить вас еще меньше...";
			}
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_UPGRADE_BED)) {
				return new Value<>(false, "Чтобы установить «Маленькую стальную кровать», необходимо сначала убрать «Двуспальную кровать».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_UPGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"Двуспальная кровать",
			"Установить в этой комнате двуспальную кровать с более удобным матрасом, пушистыми подушками и теплым одеялом. Обитатель этой комнаты наверняка оценит такие улучшения.",
			"В этой комнате установлена двуспальная кровать с удобным матрасом, пушистыми подушками и теплым одеялом.",
			"Вдоль одной из стен комнаты стоит двуспальная кровать с удобным матрасом, пушистыми подушками и теплым одеялом. Предоставление обитателю этой комнаты столь восхитительного места для сна определенно заставит его любить вас больше, хотя такая роскошь может заставить его забыть о своем месте...",
			500,
			200,
			25,
			0,
			0.2f,
			-0.1f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return "Четыре односпальные кровати в этой комнате были заменены на двуспальные, укомплектованные удобными матрасами, пушистыми подушками и теплыми одеялами. Предоставление обитателям комнаты таких комфортных спальных мест определенно заставит их любить вас больше, хотя подобная роскошь может заставить их забыть свое место...";
				
			} else if(place.getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOUBLE)) {
				return "Пара односпальных кроватей в этой комнате была заменена на двуспальные, укомплектованные удобными матрасами, пушистыми подушками и теплыми одеялами. Предоставление обитателям комнаты таких комфортных спальных мест определенно заставит их любить вас больше, хотя подобная роскошь может заставить их забыть свое место...";
				
			} else {
				return "Вдоль одной из стен комнаты стоит двуспальная кровать с удобным матрасом, пушистыми подушками и теплым одеялом. Предоставление обитателю этой комнаты столь восхитительного места для сна определенно заставит его любить вас больше, хотя такая роскошь может заставить его забыть о своем месте...";
			}
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOWNGRADE_BED)) {
				return new Value<>(false, "Прежде чем можно будет установить «Двуспальную кровать», необходимо убрать улучшение «Маленькая стальная кровать».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Тренажер покорности",
			"Lilaya спросила вас, не согласились бы вы установить в этой комнате одно из ее экспериментальных устройств — так называемый «тренажер покорности». Это дополнение представляет собой огромный светящийся кристалл, который помещается в центре комнаты. Всякий раз, когда обитатель комнаты допускает непослушную мысль, кристалл выпускает шокирующий разряд арканой энергии, тренируя покорность рабыни самым навязчивым образом, какой только можно вообразить.",
			"Вы установили в этой комнате одно из экспериментальных устройств Lilaya — так называемый «тренажер покорности». Это дополнение представляет собой огромный светящийся кристалл, помещенный в центре комнаты. Всякий раз, когда обитатель комнаты допускает непослушную мысль, кристалл выпускает шокирующий разряд арканой энергии, тренируя покорность рабыни самым бесцеремонным образом, какой только можно вообразить.",
			"В центре этой комнаты установлено одно из экспериментальных устройств Lilaya — так называемый «тренажер покорности». Представляя собой огромный светящийся кристалл, тренажер будет выпускать шокирующий разряд арканой энергии в любую находящуюся рядом рабыню, которая осмелится подумать о непослушании. Хотя этот метод крайне эффективен для тренировки покорности, любая рабыня, подвергшаяся такому бесцеремонному обучению, вскоре обязательно возненавидит вас...",
			10000,
			500,
			250,
			0,
			-0.2f,
			0.4f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_DOG_BOWLS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"Миски для собак",
			"Подавать еду обитателям этой комнаты в мисках для собак, расставленных на полу. Вынужденное питание в столь унизительной манере наверняка заставит ваших рабынь недолюбливать вас, но это также подчеркнет тот факт, что они — не более чем ваша собственность.",
			"На полу в этой комнате расставлены металлические миски для собак, и именно из них обитатели комнаты вынуждены принимать пищу. То, что их заставляют есть столь унизительным способом, заставляет твоих рабынь недолюбливать тебя, но в то же время это лишь подчеркивает тот факт, что они — не более чем твоя собственность.",
			"В одном из углов комнаты расставлена серия металлических мисок для собак, и именно из них обитатели комнаты должны есть и пить. То, что их заставляют вставать на четвереньки и жрать как псов, заставляет твоих рабынь недолюбливать тебя, но в то же время это вбивает им в головы мысль, что они — не более чем твоя собственность...",
			100,
			0,
			10,
			0,
			-0.2f,
			0.25f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_ROOM_SERVICE)) {
				return new Value<>(false, "Прежде чем можно будет использовать «Миски для собак», необходимо убрать улучшение «Обслуживание номеров».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_ROOM_SERVICE = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Обслуживание номеров",
			"Ты можешь предложить обитателям этой комнаты безлимитное обслуживание. Не совсем так большинство хозяев обращаются со своими рабынями, и хотя это наверняка заставит их любить тебя больше, это потребует немалых затрат на содержание, а также окажет негативное влияние на их покорность...",
			"Ты предложил обитателям этой комнаты безлимитное обслуживание. Это определенно заставляет их любить тебя больше, но обходится довольно дорого, а также отрицательно сказывается на покорности твоих рабынь...",
			"Небольшая тележка с несколькими пустыми серебряными тарелками и стопками стаканов на ней — явное свидетельство того, что живущие здесь рабы вовсю пользуются предложенным вами безлимитным обслуживанием номеров. Это определенно заставляет их любить вас сильнее, но наличие такой роскоши также негативно сказывается на их покорности, не говоря уже о том ущербе, который это наносит вашим финансам...",
			500,
			0,
			250,
			0,
			0.4f,
			-0.2f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_SLAVE_ROOM_DOG_BOWLS)) {
				return new Value<>(false, "Прежде чем можно будет использовать улучшение «Обслуживание номеров», необходимо убрать «Собачьи миски».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Arcane инструменты",
			"Позволить Lilaya установить в этой комнате arcane сенсоры, чтобы она могла собирать полезные данные об ауре ваших рабов. Ваши рабы сочтут это вторжением в то немногое личное пространство, которое у них есть, и в результате их привязанность к вам пострадает.",
			"В этой комнате установлена серия arcane сенсоров, позволяющая Lilaya собирать полезные данные об ауре ваших рабов. Ваши рабы считают это вторжением в то немногое личное пространство, которое у них есть, и в результате их привязанность к вам пострадает.",
			"В обмен на снижение расходов на содержание этой комнаты, Lilaya установила в помещении несколько arcane инструментов, которые позволяют ей собирать данные о любом рабе, проживающем здесь. Некоторые из них издают едва слышное гудение, которое в сочетании со слабым фиолетовым свечением делает их весьма навязчивыми, что негативно скажется на привязанности обитателей к вам.",
			500,
			100,
			-25,
			0,
			-0.1f,
			0f,
			null) {
	};

	//**** DUNGEON UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_MAGENTA,
			"Камера в подземелье",
			"-",
			"Эта камера едва достаточно велика, чтобы вместить четверых ваших несчастных рабов.",
			"-",
			0,
			0,
			0,
			4,
			-0.15f,
			0.2f,
			null) {
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveQuadruple", PresetColour.BASE_MAGENTA);
		}
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("Стены, пол и потолок здесь сложены из крупных серых камней, из-за чего в камере царит мрачная, гнетущая атмосфера. На противоположных стенах закреплены металлические подсвечники, в каждом из которых горит аркана-факел. Их вечно пылающие огни время от времени трещат и мерцают, отбрасывая пляшущие тени на стены. В тяжелой деревянной двери прорезано небольшое окно с решеткой, позволяющее наблюдать за несчастными рабами из безопасности коридора.");
			
			if(!place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DUNGEON_CELL_UPGRADE_BED) && !place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DUNGEON_CELL_DOWNGRADE_BED)) {
				sb.append("<br/><br/>В этой камере стоят четыре маленькие кровати в стальных рамах; в каждой — тонкий матрас, пара сдувшихся подушек и рваное одеяло. Старые деревянные ящики, стоящие рядом с кроватями, служат примитивной заменой прикроватным тумбам. Помимо этих кроватей и ящиков, в камере нет никакой другой мебели.");
			}
			
			return sb.toString();
		}
	};
	

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DOWNGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"Соломенная подстилка",
			"Уберите из этой камеры стальные кровати и замените их кучами соломы. Предоставление рабам столь неудобных мест для сна определенно укрепит их осознание того, что они — всего лишь ваша собственность, но в то же время они неизбежно станут ненавидеть вас еще сильнее...",
			"Кровати в этой комнате убрали и заменили кучами соломы.",
			"У одной из стен комнаты стоит маленькая односпальная кровать с неудобным матрасом, жесткой подушкой и тонким одеялом. Предоставление рабам столь неудобных мест для сна определенно подчеркивает тот факт, что они всего лишь ваша собственность, но в то же время они начинают испытывать к вам всё больше неприязни...",
			100,
			25,
			0,
			0,
			-0.1f,
			0.2f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			return "Четыре стальные кровати в этой комнате были убраны и заменены кучами соломы. Старые деревянные ящики, служившие примитивными тумбочками, также заменили простыми деревянными каркасами, в которых упаковывали солому. Предоставление рабам столь неудобных мест для сна определенно подчеркнет тот факт, что они всего лишь ваша собственность, но в то же время они неизбежно станут относиться к вам с еще большей неприязнью...";
				
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_UPGRADE_BED)) {
				return new Value<>(false, "Прежде чем установить «Соломенные постели», необходимо убрать «Улучшенные постели».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_UPGRADE_BED = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"Улучшенные постели",
			"Уберите тонкие матрасы, сдувшиеся подушки и рваные одеяла, заменив их более комфортными вариантами. Обеспечение рабам более высокого уровня комфорта определенно заставит их больше любить вас, хотя подобная относительная роскошь может заставить их забыть свое место...",
			"Матрасы, подушки и одеяла на четырех кроватях в этой камере были заменены на более комфортные варианты.",
			"Матрасы, подушки и одеяла на четырех кроватях в этой камере были заменены на более комфортные варианты. Обеспечение рабыням этой камеры более высокого уровня комфорта определенно заставляет их любить вас сильнее, хотя подобная относительная роскошь заставляет их забывать свое место...",
			250,
			50,
			5,
			0,
			0.05f,
			-0.05f,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			return "Матрасы, подушки и одеяла на четырех кроватях в этой камере были заменены на более комфортные варианты. Обеспечение рабыням этой камеры более высокого уровня комфорта определенно заставит их любить вас сильнее, хотя подобная относительная роскошь может заставить их забыть свое место...";
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DOWNGRADE_BED)) {
				return new Value<>(false, "Прежде чем можно будет установить улучшение «Улучшенная постель», необходимо убрать «Соломенную постель».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DOG_BOWLS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_BAD,
			"Миски для собак",
			"Подавать еду рабыням этой камеры следует в собачьих мисках, расставленных на полу. Вынужденное питание в столь унизительной манере наверняка заставит ваших рабынь недолюбливать вас, но это также послужит напоминанием о том, что они — не более чем ваша собственность.",
			"На пол были расставлены металлические собачьи миски, из которых рабыни этой камеры вынуждены есть.",
			"На полу расставлены четыре пары металлических мисок для собак — именно из них рабы должны есть и пить. То, что их заставляют вставать на четвереньки и жрать как псов, заставляет их недолюбливать тебя, но в то же время это наглядно вбивает им в головы мысль, что они — всего лишь твоя собственность...",
			100,
			0,
			10,
			0,
			-0.2f,
			0.25f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DECENT_FOOD)) {
				return new Value<>(false, "Чтобы использовать улучшение «Миски для собак», необходимо сначала убрать «Приличную еду».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_DECENT_FOOD = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_GOOD,
			"Приличная еда",
			"Замените холодную и безвкусную еду, которую подают рабам в этой камере, на достойную горячую пищу. Более качественная еда определенно заставит рабов симпатизировать вам больше, хотя подобная относительная роскошь может заставить их забыть свое место...",
			"Вы заменили холодную и безвкусную еду, которую подают рабам в этой камере, на достойную горячую пищу.",
			"Вы заменили холодную и безвкусную еду, которую подают рабам в этой камере, на достойную горячую пищу. Более качественная еда определенно заставляет рабов симпатизировать вам больше, хотя подобная относительная роскошь заставляет их забывать свое место...",
			250,
			0,
			100,
			0,
			0.1f,
			-0.1f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_DOG_BOWLS)) {
				return new Value<>(false, "Чтобы использовать улучшение «Приличная еда», необходимо сначала убрать «Миски для собак».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};

	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_ROPES = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_TERRIBLE,
			"Веревочные путы",
			"Прикрепите прочные веревки к стенам этой камеры и используйте их, чтобы привязывать рабов на месте. Это помешает им взаимодействовать друг с другом, а если раб находится в подчиненном слоте, он будет начинать секс-сцены с эффектом обездвиживания «Связана веревками».",
			"К стенам этой камеры прикреплены прочные веревки, которые используются для того, чтобы удерживать находящихся здесь рабов на месте.",
			"К стенам этой камеры прикреплены прочные веревки, которые используются для того, чтобы удерживать находящихся здесь рабов на месте. Это мешает вашим рабам взаимодействовать друг с другом и делает их более послушными, но при этом они начинают относиться к вам с большим неприятием...",
			250,
			100,
			10,
			0,
			-0.2f,
			0.15f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_CHAINS)) {
				return new Value<>(false, "Прежде чем можно будет использовать «Веревочные путы», необходимо снять улучшение «Цепные путы».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public ImmobilisationType getImmobilisationType() {
			return ImmobilisationType.ROPE;
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_DUNGEON_CELL_CHAINS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_TERRIBLE,
			"Цепные путы",
			"Прикрепите к стенам этой камеры металлические цепи и используйте их, чтобы удерживать ваших рабов на месте. Это помешает им взаимодействовать друг с другом, а если раб находится в подчиненном слоте, то секс-сцены будут начинаться с эффектом обездвиживания «Связанные цепями».",
			"К стенам этой камеры прикреплены металлические цепи, которые используются для того, чтобы удерживать находящихся здесь рабов на месте.",
			"К стенам этой камеры прикреплены металлические цепи, которые используются для того, чтобы удерживать находящихся здесь рабов на месте. Это мешает вашим рабам взаимодействовать друг с другом и делает их более послушными, но при этом они начинают относиться к вам с большим неприятием...",
			500,
			250,
			25,
			0,
			-0.25f,
			0.3f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_DUNGEON_CELL_ROPES)) {
				return new Value<>(false, "Прежде чем можно будет использовать «Цепные путы», необходимо снять улучшение «Веревочные путы».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public ImmobilisationType getImmobilisationType() {
			return ImmobilisationType.CHAINS;
		}
	};
	
	
	//**** MILKING UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_ORANGE,
			"Доильный зал",
			"Установите в этой комнате доильные аппараты, что позволит назначить сюда [style.colourGood(восемь)] ваших рабов; каждую из них будут доить — и молоко, и сперму.<br/><i>Молоко: "+Units.fluid(MilkingRoom.BASE_MILKING_AMOUNT)+" в час<br/>Сперма: "+Units.fluid(MilkingRoom.BASE_CUM_MILKING_AMOUNT)+" в час<br/>Девичья сперма: "+Units.fluid(MilkingRoom.BASE_GIRLCUM_MILKING_AMOUNT)+" в час</i>",
			"Эта комната была переоборудована в подходящее место для [style.colourGood(дойки)] молока и спермы ваших рабынь.",
			"Эта комната была переоборудована в специальный доильный зал, в котором можно доить различные жидкости у восьми ваших рабынь. Четыре машины установлены вдоль левой стены, а остальные четыре — на противоположной стороне комнаты.",
			10000,
			0,
			500,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaMilkingRoomDialogue.MILKING_ROOM;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomMilking", PresetColour.BASE_YELLOW_LIGHT);
		}
		@Override
		public String getRoomDescription(Cell c) {
			MilkingRoom room = Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation());
			
			return room.getRoomDescription();
		}
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_MILKING_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			if(Main.game.getOccupancyUtil().getMilkingRoom(c.getType(), c.getLocation())==null) {
				Main.game.getOccupancyUtil().addMilkingRoom(new MilkingRoom(c.getType(), c.getLocation()));
			}
		}
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_ARTISAN_MILKERS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Ремесленные доильные аппараты",
			"Вы могли бы заменить стандартные доильные аппараты в этой комнате на очень дорогие ремесленные модели. Хотя они гораздо комфортнее для использующих их рабынь, эти аппараты кажутся скорее декоративными, чем практичными, и дают чуть меньший выход молока, чем обычные машины.<br/><i>Молоко: "+Units.fluid(MilkingRoom.ARTISAN_MILKING_AMOUNT)+" в час<br/>Сперма: "+Units.fluid(MilkingRoom.ARTISAN_CUM_MILKING_AMOUNT)+" в час<br/>Девичья сперма: "+Units.fluid(MilkingRoom.ARTISAN_GIRLCUM_MILKING_AMOUNT)+" в час</i>",
			"Вы установили в этой комнате ремесленные доильные аппараты. Рабыни, которым посчастливится оказаться прикованными к этим машинам, наверняка будут вам за это благодарны.<br/><i>Молоко: "+Units.fluid(MilkingRoom.ARTISAN_MILKING_AMOUNT)+" в час<br/>Сперма: "+Units.fluid(MilkingRoom.ARTISAN_CUM_MILKING_AMOUNT)+" в час<br/>Девичья сперма: "+Units.fluid(MilkingRoom.ARTISAN_GIRLCUM_MILKING_AMOUNT)+" в час</i>",
			"Ремесленные доильные аппараты, работающие на энергии Арканы, наполняют комнату мягким, почти мелодичным гулом. Хотя они куда комфортнее обычных машин, кажется, что они созданы скорее для красоты, чем для дела: ваши рабыни наверняка будут в восторге, но объемы молока будут заметно ниже обычного.",
			2500,
			500,
			500,
			0,
			1f,
			0.5f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS)) {
				return new Value<>(false, "Прежде чем установить улучшение «Ремесленные доильные аппараты», необходимо снять «Промышленные доильные аппараты».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Промышленные доильные аппараты",
			"Вы могли бы заменить стандартные доильные аппараты в этой комнате на промышленные модели. Хотя рабыням будет куда менее комфортно, эти машины максимизируют и выход молока, и прибыль.<br/><i>Молоко: "+Units.fluid(MilkingRoom.INDUSTRIAL_MILKING_AMOUNT)+" в час<br/>Сперма: "+Units.fluid(MilkingRoom.INDUSTRIAL_CUM_MILKING_AMOUNT)+" в час<br/>Девичья сперма: "+Units.fluid(MilkingRoom.INDUSTRIAL_GIRLCUM_MILKING_AMOUNT)+" в час</i>",
			"Вы установили в этой комнате промышленные доильные аппараты. Рабыни, которым выпадет несчастье оказаться пристегнутыми к этим машинам, наверняка возненавидят вас за это.<br/><i>Молоко: "+Units.fluid(MilkingRoom.INDUSTRIAL_MILKING_AMOUNT)+" в час<br/>Сперма: "+Units.fluid(MilkingRoom.INDUSTRIAL_CUM_MILKING_AMOUNT)+" в час<br/>Девичья сперма: "+Units.fluid(MilkingRoom.INDUSTRIAL_GIRLCUM_MILKING_AMOUNT)+" в час</i>",
			"Промышленные доильные аппараты, работающие на энергии Арканы, наполняют воздух ровным фоновым гулом. Хотя они гарантированно максимизируют выход молока и прибыль, эти устройства не назовешь самыми удобными для фиксации тела, так что любая рабыня, назначенная на дойку здесь, наверняка будет вас ненавидеть...",
			1500,
			500,
			1000,
			0,
			-1f,
			0.5f,
			null) {
		
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_MILKING_ROOM_ARTISAN_MILKERS)) {
				return new Value<>(false, "Прежде чем установить «Промышленные доильные аппараты», необходимо снять «Ремесленные доильные аппараты».");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_MILK_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.MILK,
			"Лакто-чаши",
			"Компания, производящая доильные аппараты, также предлагает набор дополнительных улучшений. Одно из них — «Lact-o-Cups», которое обещает удвоить максимальную часовую эффективность доения.",
			"Вы установили дополнительную опцию «Lact-o-Cups» на доильные аппараты в этой комнате, удвоив их максимальную часовую эффективность доения.",
			"Стандартные присоски на каждом аппарате были заменены на тюнингованные «Lact-o-Cups», которые удваивают максимальное количество молока, извлекаемого за час.",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_CUM_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.CUM,
			"Суккубы",
			"Компания, производящая доильные аппараты, также предлагает набор дополнительных улучшений. Одно из них — «Succ-u-Buses», которое обещает удвоить максимальную часовую эффективность сцеживания спермы.",
			"Вы установили дополнительную опцию «Succ-u-Buses» на доильные аппараты в этой комнате, удвоив их максимальную часовую эффективность сцеживания спермы.",
			"Стандартные трубки для сцеживания на каждом аппарате были заменены на тюнингованные «Succ-u-Buses», которые удваивают максимальное количество спермы, извлекаемого за час.",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};

	public static final AbstractPlaceUpgrade LILAYA_MILKING_ROOM_GIRLCUM_EFFICIENCY = new AbstractPlaceUpgrade(false,
			PresetColour.GIRLCUM,
			"Вибронасосы",
			"Компания, производящая доильные аппараты, также предлагает набор дополнительных улучшений. Одно из них — «Vibro-Pumps», которое обещает удвоить максимальную часовую эффективность сцеживания девичьей спермы.",
			"Вы установили дополнительную опцию «Vibro-Pumps» на доильные аппараты в этой комнате, удвоив их максимальную часовую эффективность сцеживания девичьей спермы.",
			"Стандартные вагинальные помпа на каждой машине были заменены на тюнингованные «Вибро-помпы», которые вдвое увеличивают максимальное количество девичьей смазки, извлекаемой за час.",
			500,
			100,
			200,
			0,
			0,
			0,
			null) {
	};

	
	//**** OFFICE UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_TEAL,
			"Кабинет",
			"Из-за жесткого регулирования экзотических материалов, которые Lilaya регулярно заказывает для своей лаборатории, ей приходится ежемесячно заполнять огромное количество бумаг. Если заставить Роуз заменить мебель в этой комнате на столы, стулья и картотечные шкафы, её можно превратить в офис, где [style.colourGood(четырем)] твоим рабыням можно будет платить за выполнение этой работы. [style.italicsGood(Также, находясь в офисе, вы получите доступ к «Журналу занятости»!)]",
			"Эта комната была переоборудована в офис с достаточным количеством столов и места, чтобы с комфортом разместить [style.colourGood(четыре)] работницы. [style.italicsGood(Также, находясь в офисе, вы получили доступ к «Журналу занятости»!)]",
			"Чтобы помочь Lilaya с горой бумаг, связанных с закупкой экзотических материалов, вы превратили эту комнату в офис на четыре персоны. Помимо заполнения бланков для строго регламентированных закупок Lilaya, на назначенных сюда работниц возложена задача вести общий «Журнал занятости», к которому вы можете обратиться здесь в любое время.",
			8000,
			500,
			250,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaOfficeDialogue.ROOM_OFFICE;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomOffice", PresetColour.BASE_LILAC);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_OFFICE).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_OFFICE).isEmpty()) {
				return new Value<>(false, "Бумажной работы недостаточно, чтобы оправдать наличие более чем одного офиса.");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_OFFICE) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_EXECUTIVE_UPGRADE = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_GOLD,
			"Кабинет руководителя",
			"Скажи Роуз обставить этот кабинет самой роскошной и экстравагантной мебелью, которую она только сможет раздобыть. Хотя такая обстановка наверняка внушит уважение любому работнику, назначенному в этот кабинет, лучшая мебель в Dominion стоит немалых денег...",
			"Ты обставил этот кабинет самой элитной мебелью, которая способна внушить глубокое уважение к тебе любому, кто будет здесь работать.",
			"Ты обставил этот кабинет самой роскошной и вызывающе богатой мебелью, которую только можно купить за деньги. На каждом из четырех рабочих мест стоит мастерски вырезанный стол из красного дерева, а на стенах за ними закреплены полки, уставленные ремесленными книгами учета в кожаных переплетах.",
			500_000,
			-200_000,
			50,
			0,
			0.25f,
			1f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_COFFEE_MACHINE = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_BROWN_DARK,
			"Кофемашина",
			"Роуз сообщила тебе, что знает об отличной кофемашине на базе Арканы, которую можно приобрести. Способная выдавать широкий ассортимент как горячих, так и холодных кофеиновых напитков, такая машина наверняка придется по душе работникам, назначенным в этот кабинет.",
			"Ты купил для этого кабинета кофемашину на базе Арканы, которую очень ценят рабы, работающие здесь.",
			"В углу комнаты стоит кофемашина на базе Арканы, способная выдавать широкий ассортимент как горячих, так и холодных кофеиновых напитков.",
			5000,
			100,
			250,
			0,
			0.1f,
			0.05f,
			null) {
	};
	
	public static final AbstractPlaceUpgrade LILAYA_OFFICE_PARTITIONING_WALLS = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_TAN,
			"Перегородки",
			"В этом кабинете можно установить перегородки, что даст каждому из четырех потенциальных работников личное пространство для выполнения своих задач.",
			"Вы установили в этом кабинете перегородки, которые обеспечивают каждому работнику чуть больше уединения и спокойствия для выполнения возложенных на них дел.",
			"Все четыре рабочих места в этом кабинете разделены перегородками, что дает работникам возможность спокойно и уединенно заниматься своими задачами.",
			2500,
			500,
			0,
			0,
			0.05f,
			0,
			null) {
	};

	
	//**** DRESSING ROOM UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DRESSING_ROOM = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_GOLD,
			"Гардеробная",
			"Хотя в каждой спальне достаточно места для одного или двух гардеробов, было бы здорово иметь целую комнату, предназначенную исключительно для хранения одежды.",
			"Эта комната была переоборудована в гардеробную, и в ней достаточно места, чтобы вместить сотни предметов одежды.",
			"Вы превратили эту комнату в полноценную гардеробную. Десятки гардеробов позволяют хранить сотни предметов одежды.",
			5000,
			250,
			50,
			0,
			0,
			0,
			null) {
		@Override
		public String getRoomDescription(Cell c) {
			GenericPlace place = c.getPlace();
			
			StringBuilder sb = new StringBuilder();
			sb.append(super.getRoomDescription(c));
			
			if(!place.getPlaceUpgrades().contains(LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE)) {
				sb.append(" Гардероб Lyssieth, вырезанный из слоновой кости и украшенный золотом, занимает почетное место в этой комнате. Его уникальное зачарование даровало бы вам способность создавать предметы буквально из воображения, но на данный момент оно не работает, а починить его силами Lilaya будет стоить баснословных денег.");
			}
			
			return sb.toString();
		}
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated)) {
				return LilayaDressingRoomDialogue.INSTALLATION;
			}
			return super.getInstallationDialogue(c);
		}
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaDressingRoomDialogue.ROOM_DRESSING_ROOM;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomDressingRoom", PresetColour.BASE_GOLD);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_DRESSING_ROOM).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_DRESSING_ROOM).isEmpty()) {
				return new Value<>(false, "У вас может быть только одна гардеробная.");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_DRESSING_ROOM) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated)) {
				place.addPlaceUpgrade(c, LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE);
			}
		}
	};

	public static final AbstractPlaceUpgrade LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE = new AbstractPlaceUpgrade(false,
			PresetColour.GENERIC_ARCANE,
			"Гардероб Lyssieth",
			"Попросите Лилайю заново зачаровать уникальный гардероб Лиссиет, что даст вам невероятную силу — возможность создавать почти любое оружие или предмет одежды, какой только сможете вообразить.<br/>[style.italicsGood(Это откроет вам доступ к функции «Наряды» как для вас самих, так и для ваших рабынь.)]",
			"Вырезанный из слоновой кости и украшенный золотом, необычайный гардероб занимает почетное место в вашей гардеробной. Этот уникальный предмет мебели, заказанный старейшей лилин, Лиссиет, обладает невероятной силой — способностью создавать почти любое оружие или предмет одежды, какой только сможете вообразить.<br/>[style.italicsGood(Гардероб Лиссиет дает вам доступ к функции «Наряды» как для вас самих, так и для ваших рабынь.)]",
			"Вырезанный из слоновой кости и украшенный золотом, необычайный гардероб занимает почетное место в вашей гардеробной. Этот уникальный предмет мебели, заказанный старейшей лилин, Лиссиет, обладает невероятной силой — способностью создавать почти любое оружие или предмет одежды, какой только сможете вообразить.",
			250_000,
			0,
			0,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return LilayaDressingRoomDialogue.WARDROBE_ACTIVATION;
		}
		@Override
		public Value<Boolean, String> getRemovalAvailability(Cell cell) {
			return new Value<>(false, "Вы не можете убрать гардероб Лиссиет.");
		}
	};
	

	//**** SPA UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_SPA = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_AQUA,
			"Спа",
			"Если полностью очистить эту комнату и перестроить её, то из неё можно будет сделать частный спа-салон. Без сомнения, это станет отличным дополнением к особняку, но перенаправление геотермальных источников для создания крытого бассейна обойдется чрезвычайно дорого...<br/>[style.italicsbad(Можно установить только один спа-салон, и его нельзя будет убрать, так что убедитесь, что вы действительно хотите его именно в этой клетке, прежде чем приступать к установке!)]",
			"Эта комната была полностью отремонтирована и превращена в роскошный частный спа-салон. Посреди мраморного пола расположилась серия больших бассейнов, каждый из которых наполнен теплой водой из геотермальных источников.",
			"Эта комната была полностью отремонтирована и превращена в роскошный частный спа-салон, дополненный отдельными душевыми и раздевалками. Посреди мраморного пола расположилась серия больших бассейнов, каждый из которых наполнен теплой водой из геотермальных источников. По всей комнате расставлено множество удобных шезлонгов, предлагающих место для отдыха и восстановления сил, если вам не хочется окунаться в один из бассейнов.",
			1_500_000,
			250_000,
			500,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			LilayaSpa.setCellInstallation(c);
			return LilayaSpa.SPA_INSTALLATION;
		}
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaSpa.SPA_RECEPTION;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSpaReception", PresetColour.BASE_BLUE_STEEL);
		}
		@Override
		public boolean isSlaverUpgrade() {
			return false;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_SPA).isEmpty()
					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_SPA).isEmpty()) {
				return new Value<>(false, "У вас может быть только один спа-салон!");
			}
			int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
			if(cell.getType()!=WorldType.LILAYAS_HOUSE_GROUND_FLOOR
					|| cell.getLocation().getY()<2
					|| (Math.abs(cell.getLocation().getY()-size)>2 && Math.min(cell.getLocation().getX(), Math.abs(size-cell.getLocation().getX()))>2)) {
				return new Value<>(false, "Спа-салон можно построить только на северной, восточной или западной стороне первого этажа!");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "Сначала Lilaya должна разобраться со своей беременностью!");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "Сначала вам нужно поговорить с Lilaya о вашей демонической форме!");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "Вы можете установить спа-салон только тогда, когда Lilaya бодрствует!");
			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public void applyInstallationEffects(Cell cell) {
			GenericPlace place = cell.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_SPA) {
					place.removePlaceUpgrade(cell, upgrade);
				}
			}
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_SAUNA = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_ROSE,
			"Сауна (расширение)",
			"Здесь всё ещё достаточно места для дальнейшего расширения спа...<br/>Если захотите, вы могли бы предложить Lilaya, что сауна станет ценным дополнением к спа.",
			"",
			"",
			150_000,
			25_000,
			100,
			0,
			0.25f,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return LilayaSpa.SPA_SAUNA_INSTALLATION;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "Сначала нужно завершить основное расширение спа, прежде чем приступать к дальнейшему!");
			}
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION)!=null) {
				return new Value<>(false, "Нужно дождаться завершения расширения плавательного бассейна, прежде чем начинать это!");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "Сначала Lilaya должна разобраться со своей беременностью!");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "Сначала вам нужно поговорить с Lilaya о вашей демонической форме!");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "Установить расширение сауны можно только тогда, когда Lilaya не спит!");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public Value<Boolean, String> getRemovalAvailability(Cell cell) {
			return new Value<>(false, "Это улучшение нельзя убрать!");
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_POOL = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_BLUE_LIGHT,
			"Плавательный бассейн (расширение)",
			"Здесь всё ещё достаточно места для дальнейшего расширения спа...<br/>Если захотите, вы могли бы предложить Lilaya, что крытый плавательный бассейн станет ценным дополнением к спа.",
			"",
			"",
			300_000,
			50_000,
			100,
			0,
			0.25f,
			0,
			null) {
		@Override
		public DialogueNode getInstallationDialogue(Cell c) {
			return LilayaSpa.SPA_POOL_INSTALLATION;
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "Сначала нужно завершить основное расширение спа, прежде чем приступать к дальнейшему!");
			}
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION)!=null) {
				return new Value<>(false, "Нужно дождаться завершения расширения сауны, прежде чем начинать это!");
			}
			if(Main.game.getNpc(Lilaya.class).getBaseFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& (Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0) || Main.game.getNpc(Lilaya.class).isPregnant())) {
				return new Value<>(false, "Сначала Lilaya должна разобраться со своей беременностью!");
			}
			if(Lab.isLilayaAngryAtPlayerDemonTF() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaReactedToPlayerAsDemon)) {
				return new Value<>(false, "Сначала вам нужно поговорить с Lilaya о вашей демонической форме!");
			}
			if(!Main.game.isExtendedWorkTime()) {
				return new Value<>(false, "Установить расширение сауны можно только тогда, когда Lilaya не спит!");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		@Override
		public Value<Boolean, String> getRemovalAvailability(Cell cell) {
			return new Value<>(false, "Это улучшение нельзя убрать!");
		}
	};
	
	public static final AbstractPlaceUpgrade LILAYA_SPA_BAR = new AbstractPlaceUpgrade(false,
			PresetColour.BASE_ORANGE,
			"Бар",
			"Постройте бар в зоне бассейна спа-центра, который будет постоянно пополняться крепкими и безалкогольными напитками. Однако расходы на охлаждение и пополнение запасов означают, что содержать его будет не дешево...",
			"Вы построили бар в зоне бассейна спа-центра, который будет постоянно пополняться крепкими и безалкогольными напитками. Однако расходы на охлаждение и пополнение запасов означают, что содержать его будет не дешево...",
			"",
			15_000,
			5_000,
			500,
			0,
			0.1f,
			-0.2f,
			null) {
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				return new Value<>(false, "Прежде чем вы сможете улучшить это помещение, необходимо завершить основное расширение спа!");
			}
			return super.getExtraConditionalAvailability(cell);
		}
	};

	//**** DINING HALL UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_DINING_HALL = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_ORANGE_LIGHT,
			"Столовая",
			"Lilaya и Rose привыкли обедать в лаборатории, поэтому в особняке нет отдельной столовой. Достаточно будет поставить величественный стол и около дюжины стульев, чтобы исправить это...",
			"Эта комната была переоборудована в столовую, укомплектованную длинным деревянным столом и дюжиной стульев.",
			"Эта комната была переоборудована в столовую, укомплектованную длинным деревянным столом и дюжиной стульев. Хотя Lilaya и Rose, скорее всего, продолжат обедать в лаборатории, ничто не мешает вам пользоваться этой комнатой.",
			6000,
			250,
			50,
			0,
			0,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaDiningHallDialogue.ROOM_DINING_HALL;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomDining", PresetColour.BASE_ORANGE_LIGHT);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
//			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_DINING_HALL).isEmpty()
//					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_DINING_HALL).isEmpty()) {
//				return new Value<>(false, "There's no need for more than one dining hall.");
//			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_DINING_HALL) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};

	//**** SLAVE LOUNGE UPGRADES ****//
	
	public static final AbstractPlaceUpgrade LILAYA_SLAVE_LOUNGE = new AbstractPlaceUpgrade(true,
			PresetColour.BASE_GREEN_LIME,
			"Рай для рабов",
			"Помимо своих личных комнат, ваши рабы иногда будут выбирать отдых в специальной комнате отдыха. Превращение этой комнаты в рай для рабов позволит им приходить сюда в свободное время.",
			"Эта комната была переоборудована в комнату отдыха для ваших рабов. Когда они не спят и не работают, и при условии, что у них есть '"+SlavePermissionSetting.GENERAL_HOUSE_FREEDOM.getName()+"' разрешение, они могут решить прийти сюда, чтобы отдохнуть часок или около того.",
			"Эта комната была переоборудована в комнату отдыха для ваших рабов. Когда они не спят и не работают, и при условии, что у них есть '"+SlavePermissionSetting.GENERAL_HOUSE_FREEDOM.getName()+"' разрешение, они могут решить прийти сюда, чтобы отдохнуть часок или около того.",
			5000,
			250,
			50,
			0,
			0.5f,
			0,
			null) {
		@Override
		public DialogueNode getRoomDialogue(Cell c) {
			return LilayaSlaveLoungeDialogue.ROOM_SLAVE_LOUNGE;
		}
		@Override
		public String getSVGOverride() {
			return AbstractPlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveLounge", PresetColour.BASE_GREEN_LIME);
		}
		@Override
		protected Value<Boolean, String> getExtraConditionalAvailability(Cell cell) {
			if(cell.getPlace().getPlaceUpgrades().contains(LILAYA_ARTHUR_ROOM)) {
				return new Value<>(false, "");
			}
//			if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(LILAYA_DINING_HALL).isEmpty()
//					|| !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(LILAYA_DINING_HALL).isEmpty()) {
//				return new Value<>(false, "There's no need for more than one slave lounge.");
//			}
			if(!Main.game.getCharactersTreatingCellAsHome(cell).isEmpty()) {
				return new Value<>(false, "Чтобы приобрести это улучшение, комната должна быть свободна.");
			}
			return super.getExtraConditionalAvailability(cell);
		}
		
		@Override
		public void applyInstallationEffects(Cell c) {
			GenericPlace place = c.getPlace();
			for(AbstractPlaceUpgrade upgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
				if(upgrade != LILAYA_SLAVE_LOUNGE) {
					place.removePlaceUpgrade(c, upgrade);
				}
			}
		}
	};
	
	private static ArrayList<AbstractPlaceUpgrade> coreRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> guestRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> dungeonCellUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesSingle;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesDouble;
	private static ArrayList<AbstractPlaceUpgrade> slaveQuartersUpgradesQuadruple;
	private static ArrayList<AbstractPlaceUpgrade> milkingRoomUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> officeUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> spaUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> diningHallUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> slaveLoungeUpgrades;
	private static ArrayList<AbstractPlaceUpgrade> dressingRoomUpgrades;
	
	
	public static ArrayList<AbstractPlaceUpgrade> getCoreRoomUpgrades() {
		return coreRoomUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getGuestRoomUpgrades() {
		return guestRoomUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getDungeonCellUpgrades() {
		return dungeonCellUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesSingle() {
		return slaveQuartersUpgradesSingle;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesDouble() {
		return slaveQuartersUpgradesDouble;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSlaveQuartersUpgradesQuadruple() {
		return slaveQuartersUpgradesQuadruple;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getMilkingUpgrades() {
		return milkingRoomUpgrades;
	}
	
	public static ArrayList<AbstractPlaceUpgrade> getOfficeUpgrades() {
		return officeUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSpaUpgrades() {
		return spaUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getDiningHallUpgrades() {
		return diningHallUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getSlaveLoungeUpgrades() {
		return slaveLoungeUpgrades;
	}

	public static ArrayList<AbstractPlaceUpgrade> getDressingRoomUpgrades() {
		return dressingRoomUpgrades;
	}
	
	
	static {
		coreRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_DOLL_CLOSET,
				PlaceUpgrade.LILAYA_GUEST_ROOM,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				
				PlaceUpgrade.LILAYA_SLAVE_LOUNGE,
				
				PlaceUpgrade.LILAYA_SPA,
				
				PlaceUpgrade.LILAYA_OFFICE,
				PlaceUpgrade.LILAYA_DRESSING_ROOM,
				
				PlaceUpgrade.LILAYA_MILKING_ROOM,
				PlaceUpgrade.LILAYA_DINING_HALL,
				
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);

		guestRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_EMPTY_ROOM);

		dungeonCellUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DECENT_FOOD,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_DUNGEON_CELL_UPGRADE_BED,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_DOWNGRADE_BED,

				PlaceUpgrade.LILAYA_DUNGEON_CELL_ROPES,
				PlaceUpgrade.LILAYA_DUNGEON_CELL_CHAINS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER);
				
		slaveQuartersUpgradesSingle = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,

				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		slaveQuartersUpgradesDouble = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,

				PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE,
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		slaveQuartersUpgradesQuadruple = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ROOM_SERVICE,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOG_BOWLS,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_UPGRADE_BED,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_DOWNGRADE_BED,
				
				PlaceUpgrade.LILAYA_SLAVE_ROOM_ARCANE_INSTRUMENTS,
				PlaceUpgrade.LILAYA_SLAVE_ROOM_OBEDIENCE_TRAINER,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM,
				PlaceUpgrade.LILAYA_ARTHUR_ROOM);
		
		milkingRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_MILKING_ROOM_ARTISAN_MILKERS,
				PlaceUpgrade.LILAYA_MILKING_ROOM_INDUSTRIAL_MILKERS,

				PlaceUpgrade.LILAYA_MILKING_ROOM_MILK_EFFICIENCY,
				PlaceUpgrade.LILAYA_MILKING_ROOM_CUM_EFFICIENCY,
				PlaceUpgrade.LILAYA_MILKING_ROOM_GIRLCUM_EFFICIENCY,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		officeUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_OFFICE_EXECUTIVE_UPGRADE,
				PlaceUpgrade.LILAYA_OFFICE_COFFEE_MACHINE,
				PlaceUpgrade.LILAYA_OFFICE_PARTITIONING_WALLS,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		spaUpgrades = Util.newArrayListOfValues(
				//TODO
//				PlaceUpgrade.LILAYA_SPA_SAUNA,
//				PlaceUpgrade.LILAYA_SPA_POOL,
				PlaceUpgrade.LILAYA_SPA_BAR);
		
		diningHallUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		slaveLoungeUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
		
		dressingRoomUpgrades = Util.newArrayListOfValues(
				PlaceUpgrade.LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE,
				
				PlaceUpgrade.LILAYA_EMPTY_ROOM);
	}
	

	private static List<AbstractPlaceUpgrade> allPlaceUpgrades = new ArrayList<>();
	private static Map<AbstractPlaceUpgrade, String> placeUpgradeToIdMap = new HashMap<>();
	private static Map<String, AbstractPlaceUpgrade> idToPlaceUpgradeMap = new HashMap<>();

	public static List<AbstractPlaceUpgrade> getAllPlaceUpgrades() {
		return allPlaceUpgrades;
	}
	
	public static AbstractPlaceUpgrade getPlaceUpgradeFromId(String id) {
		id = Util.getClosestStringMatch(id, idToPlaceUpgradeMap.keySet());
		return idToPlaceUpgradeMap.get(id);
	}

	public static String getIdFromPlaceUpgrade(AbstractPlaceUpgrade placeType) {
		return placeUpgradeToIdMap.get(placeType);
	}
	
	static {
		Field[] fields = PlaceUpgrade.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPlaceUpgrade.class.isAssignableFrom(f.getType())) {
				AbstractPlaceUpgrade placeType;
				try {
					placeType = ((AbstractPlaceUpgrade) f.get(null));

					placeUpgradeToIdMap.put(placeType, f.getName());
					idToPlaceUpgradeMap.put(f.getName(), placeType);
					allPlaceUpgrades.add(placeType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
