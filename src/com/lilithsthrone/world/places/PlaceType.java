package com.lilithsthrone.world.places;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.npc.dominion.Daddy;
import com.lilithsthrone.game.character.npc.dominion.Elle;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Wes;
import com.lilithsthrone.game.character.npc.submission.DarkSiren;
import com.lilithsthrone.game.character.npc.submission.Elizabeth;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesSpawnRarity;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.DaddyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DemonHome;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPark;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.EnforcerWarehouse;
import com.lilithsthrone.game.dialogue.places.dominion.HomeImprovements;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallDemographics;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHallProperty;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.BraxOffice;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.feliciaApartment.FeliciaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestHelena;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestsDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaApartment;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaSpa;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomArthur;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanApartment;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ArcaneArts;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ClothingEmporium;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.DreamLover;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.PixsPlayground;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.BountyHunterLodge;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaveryAdministration;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.DominionExpress;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.KaysWarehouse;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.Warehouses;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeFirstFloorRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.places.fields.FieldsDialogue;
import com.lilithsthrone.game.dialogue.places.submission.BatCaverns;
import com.lilithsthrone.game.dialogue.places.submission.LyssiethPalaceDialogue;
import com.lilithsthrone.game.dialogue.places.submission.SlimeQueensLair;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.GamblingDenDialogue;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.PregnancyRoulette;
import com.lilithsthrone.game.dialogue.places.submission.gamblingDen.RoxysShop;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensCaptiveDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.VengarCaptiveDialogue;
import com.lilithsthrone.game.dialogue.places.submission.rebelBase.RebelBase;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Bearing;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.TeleportPermissions;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.population.Population;
import com.lilithsthrone.world.population.PopulationDensity;
import com.lilithsthrone.world.population.PopulationType;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class PlaceType {
	
	// Generic holding map:
	
	public static final AbstractPlaceType GENERIC_IMPASSABLE = new AbstractPlaceType(
			WorldRegion.MISC, "Непроходимая клетка", "", null, PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");
	
	public static final AbstractPlaceType GENERIC_EMPTY_TILE = new AbstractPlaceType(
			WorldRegion.MISC, "Пустая клетка", "", "dominion/slaverAlleyIcon", PresetColour.BASE_CRIMSON, null, Darkness.ALWAYS_LIGHT, null, "");

	public static final AbstractPlaceType GENERIC_HOLDING_CELL = new AbstractPlaceType(
			WorldRegion.MISC, "Камера временного содержания", "", "dominion/slaverAlleyIcon", PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");

	public static final AbstractPlaceType GENERIC_CLUB_HOLDING_CELL = new AbstractPlaceType(
			WorldRegion.MISC, "Камера временного содержания (клуб)", "", "dominion/slaverAlleyIcon", PresetColour.BASE_GREY, null, Darkness.ALWAYS_LIGHT, null, "");
	
	public static final AbstractPlaceType GENERIC_MUSEUM = new AbstractPlaceType(
			WorldRegion.OLD_WORLD, "Музей", "", "dominion/slaverAlleyIcon", PresetColour.BASE_TAN, null, Darkness.ALWAYS_LIGHT, null, "в Музее Лили"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	
	
	// Museum:
	
	public static final AbstractPlaceType MUSEUM_ENTRANCE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Вход",
			"Главный вход в музей, где работает ваша тетя Лили.",
			"prologue/exit",
			PresetColour.BASE_RED,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_CROWDS = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Толпы",
			"Эта часть главного вестибюля музея сейчас заполнена огромной толпой посетителей, которые, как и вы, пришли на торжественное открытие выставки.",
			"prologue/crowd",
			PresetColour.BASE_YELLOW,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_OFFICE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Кабинет",
			"Просторный кабинет руководителя. Он кажется смутно знакомым, и где-то на задворках сознания промелькнула мысль, что это может быть офис вашей тетушки Лили...",
			"prologue/office",
			PresetColour.BASE_BLUE_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_STAGE = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Сцена",
			"Посреди вестибюля возведена большая сцена, с которой ваша тетушка Лили должна произнести свою речь.",
			"prologue/stage",
			PresetColour.BASE_ORANGE,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_ROOM = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Выставочный зал",
			"Одна из множества комнат музея, предназначенная для экспозиции древних реликвий и драгоценных артефактов.",
			"prologue/room",
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_STAIRS = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Лестница",
			"Музейная лестница соединяет первый и второй этажи.",
			"prologue/stairsUp",
			PresetColour.BASE_GREEN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_LOBBY = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Вестибюль",
			 "Главный, двухъярусный вестибюль музея. С перил верхнего этажа свисают баннеры, прославляющие новую выставку.",
			null,
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_CORRIDOR = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Коридор",
			 "Коридоры верхнего этажа музея пугающе напоминают лабиринт.",
			null,
			PresetColour.BASE_TAN,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType MUSEUM_MIRROR = new AbstractPlaceType(
			WorldRegion.OLD_WORLD,
			"Зеркальная комната",
			"Главная особенность этой комнаты — огромное зеркало во всю стену.",
			"prologue/mirror",
			PresetColour.BASE_PINK,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в Музее Лили"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	
	
	// Dominion:
	
	public static final AbstractPlaceType DOMINION_PLAZA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Плаза Lilith",
			"В самом центре Dominion раскинулась обширная площадь, где штатные глашатаи зачитывают новости из земель Lilith.",
			"dominion/statue",
			PresetColour.BASE_PINK_DEEP,
			DominionPlaces.DOMINION_PLAZA,
			Darkness.ALWAYS_LIGHT,
			null, "на центральной площади Dominion") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.isDayTime()) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getDominionStormImmuneSpecies(true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
				} else {
					pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
					pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
				}
			} else {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					pop.add(new Population(false, PopulationType.PERSON, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
				} else {
					pop.add(new Population(false, PopulationType.PERSON, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				}
			}
			
			return pop;
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_STREET = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Улицы Dominion",
			"Широкие улицы Dominion застроены ухоженными особняками и полностью предназначены для пешеходов.",
			null,
			PresetColour.BASE_GREY,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
			Encounter.DOMINION_STREET,
			"на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.isDayTime()) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					if(Main.game.getNonCompanionCharactersPresent().isEmpty() || !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
						pop.add(new Population(false, PopulationType.PERSON, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true)));
					}
					
				} else {
					pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
					pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
				}
			} else {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					if(Main.game.getNonCompanionCharactersPresent().isEmpty() || !Main.game.getCurrentDialogueNode().isTravelDisabled()) {
						pop.add(new Population(false, PopulationType.PERSON, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true)));
					}
				} else {
					pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				}
			}
			
			return pop;
		}
	};
	
	public static final AbstractPlaceType DOMINION_BOULEVARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Бульвар Dominion",
			"Главные бульвары, ведущие к центру Dominion, чрезвычайно широки и людны; их регулярно патрулируют Enforcer.",
			null,
			PresetColour.BASE_PINK_LIGHT,
			DominionPlaces.BOULEVARD,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_BOULEVARD, "на улицах Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_LILITHS_TOWER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Башня Lilith",
			"Колоссальная башня из темного камня, в которой обитает Lilith, видна за многие мили и служит постоянным напоминанием горожанам о том, кто здесь правит.",
			"dominion/lilithsTowerIcon",
			PresetColour.BASE_PURPLE,
			LilithsTower.OUTSIDE,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Dominion") {

		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_ENFORCER_HQ = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Штаб Enforcer",
			"Штаб Enforcer — одно из самых современных зданий в Dominion; именно отсюда управляют всеми силами правопорядка Dominion.",
			"dominion/enforcerHQIcon",
			PresetColour.BASE_BLUE,
			EnforcerHQDialogue.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_GATE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Врата Демоньего Дома",
			"У территории, известной как «Демоний Дом», всего пара входов, и оба они усиленно охраняются многочисленными Enforcer.",
			"dominion/gate",
			PresetColour.BASE_PINK_LIGHT,
			DemonHome.DEMON_HOME_GATE,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Демоньего Дома") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Демоний Дом",
			"Территория вокруг башни Lilith известна как «Демоний Дом», но, несмотря на название, здесь живут представители самых разных рас.",
			null,
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Демоньего Дома") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_ARTHUR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Башни Солти",
			"Массивное каменное здание, богато украшенное в викторианском стиле; оно больше напоминает пятизвездочный отель, чем жилой комплекс.",
			"dominion/demonHomeSawltyTowersIcon",
			PresetColour.RACE_HUMAN,
			DemonHome.DEMON_HOME_STREET_ARTHUR,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Демоньего Дома за пределами башен Солти") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("Демоний Дом ([arthur.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+" Квартира Arthur находится именно в этом районе.";
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_ZARANIX = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Демоний Дом (Zaranix)",
			"Территория вокруг башни Lilith известна как «Демоний Дом», но, несмотря на название, здесь живут представители самых разных рас.",
			"dominion/demonHomeZaranixIcon",
			PresetColour.BASE_PINK,
			DemonHome.DEMON_HOME_STREET_ZARANIX,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Демоньего Дома") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("Демоний Дом ([zaranix.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+UtilText.parse(" Квартира [zaranix.NamePos] находится именно в этом районе.");
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_DADDY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Демоний Дом (Daddy)",
			"Территория вокруг башни Lilith известна как «Демоний Дом», но, несмотря на название, здесь живут представители самых разных рас.",
			"dominion/demonHomeDaddyIcon",
			PresetColour.BASE_INDIGO,
			DemonHome.DEMON_HOME_STREET_DADDY,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Демоньего Дома") {
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				return UtilText.parse("Демоний Дом ([daddy.Name])");
			}
			return name;
		}
		@Override
		public String getTooltipDescription() {
			return tooltipDescription+UtilText.parse(" Квартира [daddy.NamePos] находится именно в этом районе.");
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Daddy.class))) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.DINER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_DEMON_HOME_SEX_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Роскошь Ловьенн",
			"Ориентированный на более состоятельную клиентуру Демоньего Дома, секс-шоп «Роскошь Ловьенны» — лучшее место для покупки секс-игрушек и автономных секс-кукол.",
			"dominion/sexShopIcon",
			PresetColour.BASE_PINK_LIGHT,
			DemonHome.DEMON_HOME_SEX_SHOP,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Демоньего Дома") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getDialogueFlags().hasFlag("innoxia_doll_factory_exterior_population_hidden")) {
				return super.getPopulation();
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.SIDE_DOLL_FACTORY)==Quest.DOLL_FACTORY_7A) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					return Util.newArrayListOfValues(
							new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getDominionStormImmuneSpecies(true)),
							new Population(true, PopulationType.ENFORCER, PopulationDensity.DOZENS, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
				} else {
					return Util.newArrayListOfValues(
							new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
							new Population(true, PopulationType.ENFORCER, PopulationDensity.DOZENS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				}
			}
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_SHOPPING_ARCADE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Торговая аркада",
			"Хотя по всему Dominion разбросаны бесчисленные лавки, эта аркада славится как лучшее место для покупок.",
			"dominion/shoppingArcadeIcon",
			PresetColour.BASE_GOLD,
			ShoppingArcadeDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_NYAN_APARTMENT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Квартира Nyan",
			"Поскольку она показала тебе, где живет, ты знаешь, что дом Nyan находится в этом районе Dominion.",
			"dominion/homeNyanIcon",
			PresetColour.BASE_PINK_LIGHT,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
			Encounter.DOMINION_STREET,
			"на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_CALLIE_BAKERY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Сливочная пекарня",
			"Этой пекарней владеет кобылка по имени Callie.",
			"dominion/callieBakeryIcon",
			PresetColour.BASE_BROWN,
			DominionPlaces.STREET,
			Darkness.ALWAYS_LIGHT,
			Encounter.DOMINION_STREET,
			"на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_STREET_HARPY_NESTS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Улицы Dominion",
			"Запутанная сеть дорожек и мостов в Гнездах гарпий переплетается между крышами в этом районе, отбрасывая свои тени на улицы внизу.",
			null,
			PresetColour.BASE_GREY,
			DominionPlaces.STREET_SHADED,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);
	
	public static final AbstractPlaceType DOMINION_HARPY_NESTS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход в Гнезда гарпий",
			"Огромное здание с множеством лифтов и винтовых лестниц соединяет Гнезда гарпий с улицами внизу.",
			"dominion/harpyNestIcon",
			PresetColour.BASE_MAGENTA,
			HarpyNestsDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);

	public static final AbstractPlaceType DOMINION_HELENA_HOTEL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Отель «Золотое перо»",
			"Один из самых престижных и известных отелей во всем Dominion, «Золотое перо» принадлежит матриарху гарпий, Helena.",
			"dominion/demonHomeHelenaIcon",
			PresetColour.BASE_GOLD,
			DominionPlaces.HELENAS_HOTEL,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Демоньего Дома") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);
	
	public static final AbstractPlaceType DOMINION_NIGHTLIFE_DISTRICT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Район ночной жизни",
			"Хотя клубы, бары и прочие подобные заведения встречаются по всему Dominion, именно здесь сосредоточена лучшая ночная жизнь, которую может предложить Dominion.",
			"dominion/nightlifeIcon",
			PresetColour.BASE_PINK_LIGHT,
			NightlifeDistrict.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_CITY_HALL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ратуша",
			"Являясь центром регионального управления, ратуша занимается административными делами не только Dominion, но и всего владения Lilith.",
			"dominion/townHallIcon",
			PresetColour.BASE_INDIGO,
			CityHall.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_BANK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Банк Dominion",
			"Единственный банк королевства, «Банк Dominion», имеет свое главное отделение здесь, в Dominion.",
			"dominion/bankIcon",
			PresetColour.BASE_GOLD,
			DialogueManager.getDialogueFromId("innoxia_places_dominion_bank_generic_exterior"),
			Darkness.ALWAYS_LIGHT,
			null,
			"на улицах Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_AUNTS_HOME = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Дом Lilaya",
			"Дом Lilaya — это скорее особняк, чем городской дом, и благодаря своим внушительным размерам он выделяется как особенно впечатляющее здание в этом районе.",
			"dominion/homeIcon",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaHomeGeneric.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	public static final AbstractPlaceType DOMINION_SLAVER_ALLEY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Переулок работорговцев",
			"Хотя рабство в Dominion полностью легально, этот главный центр всех сделок с рабами расположен в очень сомнительной части города, окруженной опасными закоулками.",
			"dominion/slaverAlleyIcon",
			PresetColour.BASE_CRIMSON,
			SlaverAlleyDialogue.OUTSIDE,
			Darkness.ALWAYS_LIGHT,
			null, "в переулках рядом с Переулком работорговцев") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return new ArrayList<>();
			}
			return SLAVER_ALLEY_ENTRANCE.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_RED_LIGHT_DISTRICT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Квартал красных фонарей",
			"Как и секс в Dominion, который здесь достать проще простого, наличие навыков в постели у партнера не гарантировано. Именно здесь можно нанять профессиональных любовников, не обремененных обязательствами.",
			"dominion/brothel",
			PresetColour.BASE_MAGENTA,
			RedLightDistrict.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK);

	public static final AbstractPlaceType DOMINION_PARK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Парк",
			"В Dominion разбросано несколько больших парков, и все они полностью открыты для публики.",
			"dominion/park",
			PresetColour.BASE_GREEN,
			DominionPark.PARK,
			Darkness.DAYLIGHT,
			Encounter.DOMINION_PARK,
			"в одном из парков Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Natalya.class))) {
					pop.add(new Population(true, PopulationType.CENTAUR_CARTS, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
				}
			}
			
			return pop;
		}
	};

	public static final AbstractPlaceType DOMINION_HOME_IMPROVEMENT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Строительный гипермаркет Аргуса",
			"Обеспечивая товарами как любителей мастерить на дому, так и профессиональные строительные фирмы, «Строительный гипермаркет Аргуса» представляет собой пару огромных складов, расположенных посреди обширного лесопильного двора.",
			"dominion/construction",
			PresetColour.BASE_ORANGE,
			HomeImprovements.OUTSIDE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};

	public static final AbstractPlaceType DOMINION_WAREHOUSES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Складской район",
			"Хотя по всему Dominion разбросано бесчисленное множество промышленных зданий, именно в этом специализированном складском районе наблюдается их самая высокая концентрация.",
			"dominion/warehouse",
			PresetColour.BASE_BROWN,
			Warehouses.WAREHOUSE_DISTRICT,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_STREET, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_STREET.getPopulation();
		}
	};
	
	
	
	// Alleyways:
	
	public static final AbstractPlaceType DOMINION_BACK_ALLEYS_SAFE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Переулки (патрулируемые)",
			"Хотя подавляющее большинство переулков Dominion крайне опасны, этот конкретный район усиленно патрулируется Enforcer, которые следят за тем, чтобы грабители не представляли угрозы для обычных горожан.",
			"dominion/alleysIcon",
			PresetColour.BASE_GREY,
			DominionPlaces.BACK_ALLEYS_SAFE,
			Darkness.DAYLIGHT, Encounter.DOMINION_STREET, "в одном из переулков Dominion"
			) {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
					new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	};
	
	public static final AbstractPlaceType DOMINION_BACK_ALLEYS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Переулки",
			"Хотя они и позволяют срезать путь между главными улицами, по лабиринтам городских переулков редко кто ходит, так как все знают, что там обитают самые опасные элементы общества.",
			"dominion/alleysIcon",
			PresetColour.BASE_BLACK,
			DominionPlaces.BACK_ALLEYS,
			Darkness.DAYLIGHT, Encounter.DOMINION_ALLEY, "в одном из переулков Dominion"
			).initDangerous()
			.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType DOMINION_DARK_ALLEYS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Темные переулки",
			"Самые темные глубины извилистых, похожих на лабиринт переулков Dominion обходят стороной даже грабители, для которых подобные места обычно служат домом...",
			"dominion/alleysDarkIcon",
			PresetColour.BASE_PURPLE,
			DominionPlaces.DARK_ALLEYS,
			Darkness.DAYLIGHT, Encounter.DOMINION_DARK_ALLEY, "в одном из темных переулков Dominion"
			).initDangerous()
			.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType DOMINION_ALLEYS_CANAL_CROSSING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Переправа через канал",
			"Эти переулки, пересекающие городской канал, считаются очень опасными и имеют репутацию излюбленных прибежищ отчаянных разбойников.",
			"dominion/bridge",
			PresetColour.BASE_BLUE_LIGHT,
			DominionPlaces.BACK_ALLEYS_CANAL,
			Darkness.DAYLIGHT, Encounter.DOMINION_ALLEY, "в одном из переулков Dominion"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	       
	// Canals:
	
	public static final AbstractPlaceType DOMINION_CANAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Канал",
			"Каналы Dominion достаточно безопасны для тех, кто путешествует на баржах или лодках, но для тех, кто идет по часто пустынным и свободным от Enforcer буксирным тропам, всё выглядит совсем иначе...",
			"dominion/canalIcon",
			PresetColour.BASE_BLUE_LIGHT,
			DominionPlaces.CANAL,
			Darkness.DAYLIGHT, Encounter.DOMINION_CANAL, "у одного из каналов Dominion"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType DOMINION_CANAL_END = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Канал",
			"Буксирные тропы, идущие вдоль канала Dominion, резко обрываются на окраине города.",
			"dominion/canalEndIcon",
			PresetColour.BASE_BLUE,
			DominionPlaces.CANAL_END,
			Darkness.DAYLIGHT, Encounter.DOMINION_CANAL, "у одного из каналов Dominion"
			).initDangerous()
			.initAquatic(Aquatic.MIXED);
	
	// Exits & entrances:
	
	public static final AbstractPlaceType DOMINION_EXIT_TO_SUBMISSION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход в Submission",
			"На каждом перекрестке между каналом Dominion и главными улицами расположены охраняемые Enforcer входы в подземный город Submission.",
			"dominion/submissionExit",
			PresetColour.BASE_TEAL,
			DominionPlaces.CITY_EXIT_SEWERS,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	};

	public static final AbstractPlaceType DOMINION_EXIT_TO_BAT_CAVERNS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Шахта к Пещерам Летучих Мышей",
			"Эта глубокая, извилистая шахта окружена высоким сетчатым забором, а стоящие рядом знаки указывают на то, что она ведет вниз, к Пещерам Летучих Мышей под Submission.",
			"dominion/batCaverns",
			PresetColour.BASE_BLUE,
			DominionPlaces.CITY_EXIT_BAT_CAVERNS,
			Darkness.ALWAYS_LIGHT,
			null,
			"на улицах Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.RANDOM;
		}
	};
	
	public static final AbstractPlaceType DOMINION_EXIT_EAST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Выход из Dominion",
			"Широкие бульвары Dominion превращаются в обычные улицы, когда они уходят в удивительно маленькие пригороды города.",
			"dominion/exitEast",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.NORTH;
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_NORTH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Выход из Dominion",
			"Широкие бульвары Dominion превращаются в обычные улицы, когда они уходят в удивительно маленькие пригороды города.",
			"dominion/exitNorth",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_WEST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Выход из Dominion",
			"Широкие бульвары Dominion превращаются в обычные улицы, когда они уходят в удивительно маленькие пригороды города.",
			"dominion/exitWest",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	public static final AbstractPlaceType DOMINION_EXIT_SOUTH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Выход из Dominion",
			"Широкие бульвары Dominion превращаются в обычные улицы, когда они уходят в удивительно маленькие пригороды города.",
			"dominion/exitSouth",
			PresetColour.BASE_RED,
			DominionPlaces.CITY_EXIT,
			Darkness.ALWAYS_LIGHT,
			null, "на улицах Dominion") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_PLAZA.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_PINK);
	
	
	
	// Enforcer HQ:
	
	public static final AbstractPlaceType ENFORCER_HQ_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"По обе стороны этого довольно обычного на вид коридора тянется множество дверей, на каждой из которых указано название и специализация отдельного подразделения Enforcer.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerHQDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_CELLS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Этот конкретный коридор не обладает какими-либо отличительными чертами.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerHQDialogue.CORRIDOR_PLAIN,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Охраняемая лестница",
			"Лестницу, ведущую на следующий этаж, охраняет бдительный Enforcer.",
			"dominion/enforcerHQ/stairs",
			PresetColour.BASE_GREEN,
			EnforcerHQDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_WAITING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Зона ожидания",
			"Эту зону ожидания составляют несколько низких диванов, несколько комнатных растений и атмосфера утомительной скуки.",
			"dominion/enforcerHQ/waitingRoom",
			PresetColour.BASE_BROWN,
			EnforcerHQDialogue.WAITING_AREA,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_RECEPTION_DESK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Стойка регистрации",
			"За стойкой регистрации в штаб-квартире Enforcer дежурит кошкодевочка-бимбо по имени Кэнди.",
			"dominion/enforcerHQ/receptionDesk",
			PresetColour.BASE_BLUE_LIGHT,
			EnforcerHQDialogue.RECEPTION_DESK,
			Darkness.ALWAYS_LIGHT,
			null, "в кабинете Кэнди")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_GUARDED_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Охраняемая дверь",
			"Дверь, соединяющая общую приемную с остальной частью штаб-квартиры Enforcer, охраняется на редкость мускулистым конем.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.GUARDED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer") {
		@Override
		public Colour getColour() {
			if((Main.game.getDialogueFlags().values.contains(DialogueFlagValue.accessToEnforcerHQ) && !Main.game.isBraxMainQuestComplete())
					|| Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
				return PresetColour.BASE_GREEN_LIGHT;
			}
			return PresetColour.BASE_CRIMSON;
		} 
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.ENFORCER, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_REQUISITIONS_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Запертая дверь",
			"Эта внутренняя дверь наглухо заперта, преграждая путь всем, у кого нет нужного ключа.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.REQUISITIONS_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer") {
		@Override
		public Colour getColour() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_WES)) {
				return PresetColour.BASE_GREEN_LIGHT;
			}
			return PresetColour.BASE_CRIMSON;
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_LOCKED_DOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Запертая дверь",
			"Эта внутренняя дверь наглухо заперта, преграждая путь всем, у кого нет нужного ключа.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_CRIMSON,
			EnforcerHQDialogue.LOCKED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer").initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_LOCKED_DOOR_EDGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Запертая дверь",
			"Эта внутренняя дверь наглухо заперта, преграждая путь всем, у кого нет нужного ключа.",
			"dominion/enforcerHQ/guardedDoor",
			PresetColour.BASE_RED_DARK,
			EnforcerHQDialogue.LOCKED_DOOR,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_BRAXS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет Brax",
			"Инспекторам разрешено иметь собственные кабинеты, которые они могут обставлять по своему вкусу.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_BLUE_DARK,
			BraxOffice.INTERIOR_BRAX,
			Darkness.ALWAYS_LIGHT,
			null, "в его кабинете") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
			jacket.setSticker("collar", "tab_ip");
			jacket.setSticker("name", "name_brax");
			jacket.setSticker("ribbon", "ribbon_brax");
			inventory.addClothing(jacket);
			
			inventory.addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false));
			
			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			inventory.addClothing(hat);
		}
		@Override
		public boolean isItemsDisappear() {
			return false;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.braxEncountered)) {
				return BraxOffice.INTERIOR_BRAX_REPEAT;
			} else {
				return BraxOffice.INTERIOR_BRAX;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Запертый кабинет",
			"Дверь в этот кабинет заперта, и вы невольно задаетесь вопросом, что же может скрываться внутри.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_GREY,
			EnforcerHQDialogue.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_CELLS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет камер",
			"В этом маленьком кабинете происходит регистрация всех заключенных при их прибытии в камеры или выходе из них.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_PURPLE,
			EnforcerHQDialogue.CELLS_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "в штаб-квартире Enforcer")
			.initWeatherImmune();
	
	public static final AbstractPlaceType ENFORCER_HQ_CELL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Камера",
			"Камеры в штаб-квартире Enforcer — это место, где заключенных временно держат до тех пор, пока их не оформят должным образом.",
			"dominion/enforcerHQ/cell",
			PresetColour.BASE_BROWN_DARK,
			EnforcerHQDialogue.CELL,
			Darkness.ALWAYS_LIGHT,
			null, "в камерах штаб-квартиры Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход",
			"Вход в штаб-квартиру Enforcer представляет собой пару звуконепроницаемых стеклянных дверей.",
			"dominion/enforcerHQ/exit",
			PresetColour.BASE_RED,
			EnforcerHQDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_ENFORCER_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход Enforcer",
			"Один из многих непубличных входов в штаб-квартиру Enforcer; звуконепроницаемые стеклянные двери открываются только тогда, когда специальный пропуск проводят над находящимся рядом аркана-сканером.",
			"dominion/enforcerHQ/exit",
			PresetColour.BASE_BLUE,
			EnforcerHQDialogue.ENTRANCE_ENFORCER,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_REQUISITIONS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Стол выдачи снаряжения",
			"В этой зоне выдается специализированное или сменное снаряжение Enforcer.",
			"dominion/enforcerHQ/requisitions",
			PresetColour.BASE_TAN,
			EnforcerHQDialogue.REQUISITIONS,
			Darkness.ALWAYS_LIGHT,
			null, "") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(ENFORCER_HQ_REQUISITIONS)).contains(Main.game.getNpc(Wes.class))
					&& !Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(ENFORCER_HQ_REQUISITIONS)).contains(Main.game.getNpc(Elle.class))) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.ENFORCER, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.DOG_MORPH_GERMAN_SHEPHERD, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_HQ_OFFICE_QUARTERMASTER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет квартирмейстера",
			"Квартирмейстер штаб-квартиры, отвечающий за управление снаряжением Enforcer, имеет кабинет, удобно расположенный прямо напротив стола выдачи снаряжения.",
			"dominion/enforcerHQ/office",
			PresetColour.BASE_ORANGE,
			EnforcerHQDialogue.OFFICE_QUARTERMASTER,
			Darkness.ALWAYS_LIGHT,
			null, "")
			.initWeatherImmune();
	
        //Felicia's Apartment       
        
        public static final AbstractPlaceType FELICIA_APARTMENT_ENTRYWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Прихожая",
			"В прихожей квартиры [felicia.NamePos] сбоку есть гардероб.",
			"dominion/feliciaApartment/entranceHall",
			PresetColour.BASE_RED,
			FeliciaApartment.ENTRYWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"в вестибюле квартиры [felicia.NamePos]"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спальня",
			"PLACEHOLDER_FELICIA_APARTMENT_BEDROOM",
			"dominion/feliciaApartment/feliciaBedroom",
			PresetColour.BASE_YELLOW_PALE,
			FeliciaApartment.FELICIA_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в спальне [felicia.NamePos]"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ванная",
			"PLACEHOLDER_FELICIA_APARTMENT_BATHROOM",
			"dominion/feliciaApartment/toilet",
			PresetColour.BASE_BLUE_LIGHT,
			FeliciaApartment.BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в ванной в квартире Фелиции"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кухня",
			"Пустая кухня плавно переходит и в коридор, и в обеденную зону.",
			"dominion/feliciaApartment/kitchen",
			PresetColour.BASE_ORANGE,
			FeliciaApartment.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null,
			"на кухне в квартире [felicia.NamePos]"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_DINING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Обеденная зона",
			"Обеденная зона в квартире [felicia.NamePos] кажется тесной, хотя там рассчитано всего на одно место.",
			"dominion/feliciaApartment/diningArea",
			PresetColour.BASE_BLUE_STEEL,
			FeliciaApartment.DINING_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"в обеденной зоне в квартире [felicia.NamePos]"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_LIVING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Гостиная",
			"Гостиная [felicia.Name] обставлена скудно, но из окон открывается вид на улицы Dominion.",
			"dominion/feliciaApartment/livingArea",
			PresetColour.BASE_INDIGO,
			FeliciaApartment.LIVING_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"в гостиной в квартире [felicia.NamePos]"
        ).initWeatherImmune();
        
        public static final AbstractPlaceType FELICIA_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Коридор совершенно пуст: ни украшений, ни мебели.",
			null,
			PresetColour.BASE_BLACK,
			FeliciaApartment.HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"в коридоре квартиры [felicia.NamePos]"
        ).initWeatherImmune();
        
	// Enforcer warehouse:
	
	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход",
			"Единственный вход на склад охраняет небольшая будка, где дежурят Enforcer.",
			"dominion/enforcerWarehouse/exit",
			PresetColour.BASE_RED,
			EnforcerWarehouse.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Сквозь склад змеятся многочисленные коридоры, по обе стороны которых высятся штабеля деревянных ящиков.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CLAIRE_WARNING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Сквозь склад змеятся многочисленные коридоры, по обе стороны которых высятся штабеля деревянных ящиков.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.CLAIRE_WARNING,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Загон",
			"Забытый уголок склада, со всех сторон окруженный возвышающимися горами деревянных ящиков.",
			null,
			PresetColour.BASE_BLACK,
			EnforcerWarehouse.ENCLOSURE,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_PADS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Телепортационная платформа",
			"В этой области находится телепортационная платформа, на которой прибыли вы и Клэр.",
			"dominion/enforcerWarehouse/teleportPads",
			PresetColour.BASE_MAGENTA,
			EnforcerWarehouse.ENCLOSURE_TELEPORT_PADS,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENCLOSURE_TELEPORT_SHELVING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Стеллажи",
			"В этом углу нагромождены несколько отдельно стоящих стеллажей.",
			"dominion/enforcerWarehouse/shelving",
			PresetColour.BASE_PURPLE_LIGHT,
			EnforcerWarehouse.ENCLOSURE_SHELVING,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_ENFORCER_GUARD_POST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Пост охраны Enforcer",
			"По всему складу разбросано несколько постов охраны Enforcer, которые представляют собой не более чем стол и стул.",
			"dominion/enforcerWarehouse/enforcerGuardPost",
			PresetColour.BASE_BLUE_STEEL,
			EnforcerWarehouse.ENFORCER_GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initDangerous()
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ящики",
			"Один или два ящика в этой части склада еще не были запечатаны.",
			"dominion/enforcerWarehouse/crates",
			PresetColour.BASE_ORANGE,
			EnforcerWarehouse.CRATES,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ящики (осмотрены)",
			"Один или два ящика в этой части склада еще не были запечатаны, что позволило вам обыскать их.",
			"dominion/enforcerWarehouse/cratesSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.CRATES,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_ARK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ящики",
			"Один или два ящика в этой части склада еще не были запечатаны.",
			"dominion/enforcerWarehouse/crates",
			PresetColour.BASE_ORANGE,
			EnforcerWarehouse.CRATES_ARK,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_ARK_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ящики (осмотрены)",
			"Один или два ящика в этой части склада еще не были запечатаны, что позволило вам обыскать их.",
			"dominion/enforcerWarehouse/cratesSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.CRATES_ARK,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_LUST_WEAPON = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ящик «Совершенно секретно»",
			"Один из ящиков в этой области помечен как «Совершенно секретно».",
			"dominion/enforcerWarehouse/cratesLustWeapon",
			PresetColour.BASE_PINK_DEEP,
			EnforcerWarehouse.CRATES_LUST_WEAPON,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Стеллажи",
			"Вместо привычных ящиков, разбросанных по всему складу, в этой части стоят стеллажи, забитые запрещенными книгами и прочей контрабандной писаниной.",
			"dominion/enforcerWarehouse/shelvingSpellBook",
			PresetColour.BASE_MAGENTA,
			EnforcerWarehouse.SHELVES_SPELL_BOOK,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();

	public static final AbstractPlaceType ENFORCER_WAREHOUSE_CRATES_SPELL_BOOK_SEARCHED = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Стеллажи (осмотрены)",
			"Вместо привычных ящиков, разбросанных по всему складу, в этой части стоят стеллажи, забитые запрещенными книгами и прочей контрабандной писаниной. Вы уже осмотрели их и нашли книгу заклинаний.",
			"dominion/enforcerWarehouse/shelvingSearched",
			PresetColour.BASE_GREY,
			EnforcerWarehouse.SHELVES_SPELL_BOOK,
			Darkness.ALWAYS_LIGHT,
			null, "на складе Enforcer")
			.initWeatherImmune();
	
	
	// City hall:
	
	public static final AbstractPlaceType CITY_HALL_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Мраморные коридоры в мэрии Dominion позволяют местным бюрократам легко переходить из одного кабинета в другой.",
			null,
			PresetColour.BASE_BLACK,
			CityHall.CITY_HALL_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.OFFICE_WORKER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход",
			"Вход в мэрию представляет собой пару вращающихся стеклянных дверей; одна из них помечена как «Выход», а другая — «Вход: проход ограничен».",
			"dominion/cityHall/exit",
			PresetColour.BASE_RED,
			CityHall.CITY_HALL_FOYER,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getDominionStormImmuneSpecies(true)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_INFORMATION_DESK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Информационная стойка",
			"В центре огромного вестибюля стоит круглый стол, за которым дежурят несколько администраторов.",
			"dominion/cityHall/front_desk",
			PresetColour.BASE_BLUE_LIGHT,
			CityHall.CITY_HALL_INFORMATION_DESK,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_WAITING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Зал ожидания",
			"На дальней стене просторного зала ожидания висят большие аналоговые часы; мерное тиканье секундной стрелки служит всем присутствующим постоянным напоминанием о парализующей неэффективности бюрократии.",
			"dominion/cityHall/waiting_area",
			PresetColour.BASE_PURPLE_LIGHT,
			CityHall.CITY_HALL_WAITING_AREA,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {

		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет",
			"Дверь в этот конкретный кабинет помечена надписью «Частный», давая понять каждому, что внутри не происходит абсолютно ничего важного.",
			"dominion/cityHall/office",
			PresetColour.BASE_ORANGE,
			CityHall.CITY_HALL_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Лестницы на верхние этажи мэрии помечены как частные и отгорожены красными канатами.",
			"dominion/cityHall/stairs",
			PresetColour.BASE_GREY,
			CityHall.CITY_HALL_STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {
	}.initWeatherImmune();
	
	public static final AbstractPlaceType CITY_HALL_BUREAU_OF_DEMOGRAPHICS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Бюро демографии",
			"«Бюро демографии» представляет собой небольшой кабинет, примыкающий к огромному хранилищу, напоминающему библиотеку.",
			"dominion/cityHall/officeDemographics",
			PresetColour.BASE_TEAL,
			CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_ARCHIVES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Бюро демографии (Архив)",
			"«Бюро демографии» представляет собой небольшой кабинет, примыкающий к огромному хранилищу, напоминающему библиотеку.",
			"dominion/cityHall/officeDemographicsArchives",
			PresetColour.BASE_BLUE, // Player cannot enter this tile.
			CityHallDemographics.CITY_HALL_DEMOGRAPHICS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {
	}.initWeatherImmune();

	public static final AbstractPlaceType CITY_HALL_BUREAU_OF_PROPERTY_RIGHTS_AND_COMMERCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"«Бюро собственности и торговли» — один из крупнейших и наиболее щедро финансируемых департаментов в мэрии, состоящий из множества взаимосвязанных кабинетов и залов для совещаний.",
			"dominion/cityHall/officeProperty",
			PresetColour.BASE_GOLD,
			CityHallProperty.CITY_HALL_PROPERTY_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в мэрии Dominion") {
	}.initWeatherImmune();

	
	// Home Improvements:
	
	public static final AbstractPlaceType HOME_IMPROVEMENTS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Проходы",
			"Широкие проходы с бетонным полом тянутся через весь склад, предоставляя покупателям достаточно места, чтобы беспрепятственно разъезжаться на своих тележках.",
			null,
			PresetColour.BASE_BLACK,
			HomeImprovements.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в «Магазине товаров для дома Аргуса»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход",
			"Вход в мэрию представляет собой пару вращающихся стеклянных дверей; одна из них помечена как «Выход», а другая — «Вход: проход ограничен».",
			"dominion/homeImprovements/exit",
			PresetColour.BASE_RED,
			HomeImprovements.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в «Магазине товаров для дома Аргуса»") {
		@Override
		public List<Population> getPopulation() {
			return HOME_IMPROVEMENTS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_SHELVING_PREMIUM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Стеллажи (Премиум)",
			"Стеллажи, расположенные напротив главного входа, забиты краской «премиум-класса», инструментами и прочими разнообразными товарами для дома.",
			"dominion/homeImprovements/shelving",
			PresetColour.BASE_GOLD,
			HomeImprovements.SHELVING_PREMIUM,
			Darkness.ALWAYS_LIGHT,
			null, "в «Магазине товаров для дома Аргуса»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_SHELVING_STANDARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Стеллажи (Стандарт)",
			"Стеллажи, проходящие вдоль основных проходов, забиты краской «стандартного класса», инструментами и прочими разнообразными товарами для дома.",
			"dominion/homeImprovements/shelving",
			PresetColour.BASE_BROWN,
			HomeImprovements.SHELVING_STANDARD,
			Darkness.ALWAYS_LIGHT,
			null, "в «Магазине товаров для дома Аргуса»") {
		@Override
		public List<Population> getPopulation() {
			return HOME_IMPROVEMENTS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_BUILDING_SUPPLIES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Строительные материалы",
			"В глубине склада тянутся многочисленные стеллажи, забитые древесиной, черепицей, трубами и всевозможными другими строительными материалами.",
			"dominion/homeImprovements/crates",
			PresetColour.BASE_ORANGE,
			HomeImprovements.BUILDING_SUPPLIES,
			Darkness.ALWAYS_LIGHT,
			null, "в «Магазине товаров для дома Аргуса»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет менеджера",
			"Кабинет Аргуса, владельца и управляющего этим предприятием, расположен неподалеку от входа на склад.",
			"dominion/homeImprovements/office",
			PresetColour.BASE_MAGENTA,
			HomeImprovements.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "в кабинете Аргуса")
	.initWeatherImmune();

	public static final AbstractPlaceType HOME_IMPROVEMENTS_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Туалеты",
			"В дальнем углу склада есть туалеты, которыми могут пользоваться покупатели.",
			"dominion/homeImprovements/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			HomeImprovements.TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "в туалетах магазина «Аргус и сыновья»")
	.initWeatherImmune();

	
	// Dominion Express:
	
	public static final AbstractPlaceType DOMINION_EXPRESS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Этот коридор тянется вдоль всего склада, фактически отделяя открытую складскую зону от многочисленных офисов предприятия.",
			null,
			PresetColour.BASE_BLACK,
			DominionExpress.CORRIDOR,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_EXPRESS, "на складе «Dominion Express'") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_EXIT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход",
			"С одной стороны от входа на склад стоит одинокая стойка регистрации; за ней сидят секретарши, следящие за тем, чтобы у каждого посетителя была веская причина находиться здесь.",
			"dominion/dominionExpress/exit",
			PresetColour.BASE_RED,
			DominionExpress.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "на складе «Dominion Express'") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.RECEPTIONIST, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_STORAGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Склад",
			"Половина площади склада отведена под временное хранение товаров.",
			"dominion/dominionExpress/crates",
			PresetColour.BASE_ORANGE,
			DominionExpress.STORAGE,
			Darkness.ALWAYS_LIGHT, Encounter.DOMINION_EXPRESS, "на складе «Dominion Express'") {
		@Override
		public List<Population> getPopulation() {
			return DOMINION_EXPRESS_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет",
			"Повседневное управление «Dominion Express» осуществляется в этих офисах.",
			"dominion/dominionExpress/office",
			PresetColour.BASE_BLUE_LIGHT,
			DominionExpress.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "на складе «Dominion Express'") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.OFFICE_WORKER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_FILLY_STATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"[style.Mule] Станция наград",
			"В небольшой нише с одной стороны коридора склада стоит любопытный аркана-автомат, на котором отчетливо видна надпись «[style.Mule] Станция наград».",
			"dominion/dominionExpress/fillyStation",
			PresetColour.BASE_PINK_LIGHT,
			DominionExpress.FILLY_STATION,
			Darkness.ALWAYS_LIGHT,
			null, "на складе «Dominion Express'")
		.initWeatherImmune();
	
	public static final AbstractPlaceType DOMINION_EXPRESS_OFFICE_STABLE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет Хозяйки конюшен",
			"Кабинет, отвечающий за уход и управление рабами-кентаврами, расположен в самом конце коридора склада.",
			"dominion/dominionExpress/officeStable",
			PresetColour.BASE_TAN,
			DominionExpress.OFFICE_STABLE,
			Darkness.ALWAYS_LIGHT,
			null, "в кабинете Хозяйки конюшен на складе «Dominion Express»")
	.initWeatherImmune();

	public static final AbstractPlaceType DOMINION_EXPRESS_STABLES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Конюшни",
			"Этот большой одноэтажный пристрой содержит множество стойл, каждое из которых достаточно велико, чтобы там с комфортом разместился раб-кентавр.",
			"dominion/dominionExpress/stables",
			PresetColour.BASE_BROWN,
			DominionExpress.STABLES,
			Darkness.ALWAYS_LIGHT,
			null, "в конюшнях на складе «Dominion Express»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.SLAVE, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	
	
	// Harpy Nests:
	
	public static final AbstractPlaceType HARPY_NESTS_WALKWAYS = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Переход",
			"Гнезда гарпий соединены между собой узкими деревянными переходами, построенными поверх жилых зданий Dominion.",
			null,
			PresetColour.BASE_BLACK,
			HarpyNestsDialogue.WALKWAY,
			Darkness.ALWAYS_LIGHT, Encounter.HARPY_NEST_WALKWAYS, "в Гнездах гарпий") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return super.getPopulation();
			} else {
				return Util.newArrayListOfValues(new Population(true, PopulationType.HARPY, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.HARPY_NEST, this, false, false)));
			}
		}
	}.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType HARPY_NESTS_WALKWAYS_BRIDGE = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Мост-переход",
			"То тут, то там над улицами раскинулись мосты, соединяющие одну сеть переходов с другой.",
			"dominion/harpyNests/bridge",
			PresetColour.BASE_GREY,
			HarpyNestsDialogue.WALKWAY_BRIDGE,
			Darkness.ALWAYS_LIGHT, Encounter.HARPY_NEST_WALKWAYS, "в Гнездах гарпий") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_HARPY_PACIFICATION) || Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
		}
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	}.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType HARPY_NESTS_ENTRANCE_ENFORCER_POST = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Пост Enforcer",
			"Чтобы поддерживать мир между бесчисленными сварливыми гарпиями, необходим хорошо укомплектованный аванпост Enforcer.",
			"dominion/harpyNests/exit",
			PresetColour.BASE_RED,
			HarpyNestsDialogue.ENTRANCE_ENFORCER_POST,
			Darkness.ALWAYS_LIGHT,
			null, "в Гнездах гарпий") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HARPY_NESTS_HELENAS_NEST = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Гнездо Helena",
			"Потрясающе красивая матка гарпий, Helena, правит самым большим из всех гнезд гарпий.",
			"dominion/harpyNests/nestHelena",
			PresetColour.BASE_GOLD,
			HarpyNestHelena.HELENAS_NEST_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в гнезде Helena"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_RED = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Гнездо гарпий",
			"Гнездо Diana состоит в основном из разгневанных рыжих гарпий; цвет их перьев — попытка подражать внешности их садистской предводительницы.",
			"dominion/harpyNests/nestRed",
			PresetColour.BASE_CRIMSON,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в гнезде Diana"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_dominant_exterior");
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_PINK = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Гнездо гарпий",
			"В гнезде Lexi непропорционально много самцов гарпий, каждый из которых ошивается поблизости в надежде трахнуть свою обожающую секс матку.",
			"dominion/harpyNests/nestPink",
			PresetColour.BASE_PINK_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в гнезде Lexi"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_nympho_exterior");
		}
	};
	
	public static final AbstractPlaceType HARPY_NESTS_HARPY_NEST_YELLOW = new AbstractPlaceType(
			WorldRegion.HARPY_NESTS,
			"Гнездо гарпий",
			"В гнезде Бриттани обитает немало белокурых, пышногрудых и глуповатых гарпий.",
			"dominion/harpyNests/nestYellow",
			PresetColour.BASE_YELLOW_LIGHT,
			null,
			Darkness.ALWAYS_LIGHT,
			null, "в гнезде Бриттани"){
		@Override
		public List<Population> getPopulation() {
			return HARPY_NESTS_WALKWAYS.getPopulation();
		}
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_harpy_nests_bimbo_exterior");
		}
	};
	
			
	// Lilaya's home (ground floor):
	
	public static final AbstractPlaceType LILAYA_HOME_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"По центру каждого коридора в доме Лилайи тянется безупречно чистый красный ковер, а стены украшены изысканными картинами и искусно вырезанными мраморными бюстами.",
			null,
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			Encounter.LILAYAS_HOME_CORRIDOR,
			"в доме Лилайи"
		).initItemsPersistInTile()
		.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Из внешних комнат первого этажа, включая эту, открывается вид на улицы Dominion или на частные переулки, огибающие дом.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_WINDOW,
			Darkness.ALWAYS_LIGHT,
			null,
			"в доме Лилайи") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_WINDOW;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
				return PlaceUpgrade.getGuestRoomUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesSingle();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesDouble();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_QUADRUPLE)) {
				return PlaceUpgrade.getSlaveQuartersUpgradesQuadruple();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
				return PlaceUpgrade.getMilkingUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_OFFICE)) {
				return PlaceUpgrade.getOfficeUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SPA)) {
				return PlaceUpgrade.getSpaUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_DINING_HALL)) {
				return PlaceUpgrade.getDiningHallUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_LOUNGE)) {
				return PlaceUpgrade.getSlaveLoungeUpgrades();
				
			} else if(upgrades.contains(PlaceUpgrade.LILAYA_DRESSING_ROOM)) {
				return PlaceUpgrade.getDressingRoomUpgrades();
			}
			
			return PlaceUpgrade.getCoreRoomUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " G-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Садовая комната",
			"Все внутренние комнаты первого этажа соединены с частным садом панорамными дверями.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " GG-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Внешние комнаты на первом этаже, включая эту, имеют окна, выходящие либо на улицы Dominion, либо на частные переулки, проходящие вокруг дома.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_WINDOW,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_WINDOW;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " F-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Садовая комната",
			"Внутренние комнаты на первом этаже, включая эту, имеют окна, выходящие в частный сад.",
			"dominion/lilayasHome/room",
			PresetColour.BASE_GREY,
			LilayaHomeGeneric.ROOM_GARDEN,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.ROOM_GARDEN;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR.getAvailablePlaceUpgrades(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " FG-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_DUNGEON_CELL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Темница",
			"Камеры в подземелье Lilaya спроектированы так, чтобы быть тесными и неудобными.",
			"dominion/lilayasHome/roomSlave",
			PresetColour.BASE_GREY,//BASE_MAGENTA
			LilayaHomeGeneric.DUNGEON_CELL,
			Darkness.ALWAYS_LIGHT,
			null,
			"в подземелье Lilaya") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(cell!=null) {
				for(AbstractPlaceUpgrade pu : cell.getPlace().getPlaceUpgrades()) {
					if(pu.getRoomDialogue(cell)!=null) {
						return pu.getRoomDialogue(cell);
					}
				}
			}
			return LilayaHomeGeneric.DUNGEON_CELL;
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_DUNGEON_CELL);
		}
		@Override
		public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
			return PlaceUpgrade.getDungeonCellUpgrades();
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " D-"+String.format("%02d", count);
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ARTHUR_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната Arthur",
			"Переделанная из кладовой комната, где живет Arthur, бывший любовник и коллега Lilaya.",
			"dominion/lilayasHome/roomArthur",
			PresetColour.BASE_BLUE_STEEL,
			RoomArthur.ROOM_ARTHUR,
			Darkness.ALWAYS_LIGHT,
			null, "в комнате Arthur"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_BIRTHING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Вы не совсем понимаете, зачем Lilaya выделила отдельную комнату для доставки, но предполагаете, что когда-то она использовалась для исследований в области Аркана.",
			"dominion/lilayasHome/roomBirthing",
			PresetColour.BASE_PINK,
			LilayaHomeGeneric.BIRTHING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кухня",
			"В задней части дома расположена огромная, хорошо оборудованная кухня.",
			"dominion/lilayasHome/kitchen",
			PresetColour.BASE_TAN,
			LilayaHomeGeneric.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "на кухне у Lilaya"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_LIBRARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Библиотека",
			"Огромная библиотека с бесконечными рядами книжных полок расположена в одном из углов первого этажа.",
			"dominion/lilayasHome/library",
			PresetColour.BASE_TEAL,
			Library.LIBRARY,
			Darkness.ALWAYS_LIGHT,
			null, "в библиотеке Lilaya") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			inventory.addItem(Main.game.getItemGen().generateItem(ItemType.getLoreBook(Subspecies.HALF_DEMON)));
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_UP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Эта лестница соединяет первый и второй этажи дома Lilaya; на середине пути есть небольшая промежуточная площадка.",
			"dominion/lilayasHome/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			LilayaHomeGeneric.STAIRCASE_UP,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_UP_SECONDARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Эта лестница меньше той, что находится у главного входа в особняк, но служит той же цели — соединяет первый и второй этажи дома Lilaya.",
			"dominion/lilayasHome/stairsUpSecondary",
			PresetColour.BASE_GREEN_LIME,
			LilayaHomeGeneric.STAIRCASE_UP_SECONDARY,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ENTRANCE_HALL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Прихожая",
			"Стены этого величественного вестибюля украшены изысканными картинами и мраморными бюстами, а под высоким потолком висит огромная хрустальная люстра.",
			"dominion/lilayasHome/entranceHall",
			PresetColour.BASE_RED,
			LilayaHomeGeneric.ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_LAB = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лаборатория Lilaya",
			"Комната в углу первого этажа была переоборудована в настоящую лабораторию, где Lilaya проводит почти всё своё время.",
			"dominion/lilayasHome/lab",
			PresetColour.BASE_GREEN_LIME,
			Lab.LAB,
			Darkness.ALWAYS_LIGHT,
			null, "в лаборатории Lilaya") {
//		@Override
//		public void applyInventoryInit(CharacterInventory inventory) {
//			inventory.addClothing(Main.game.getItemGen().generateClothing("innoxia_scientist_safety_goggles", false));
//		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_GARDEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Сад",
			"Этот частный сад со всех четырех сторон окружен стенами дома Lilaya.",
			null,
			PresetColour.BASE_GREEN,
			LilayaHomeGeneric.GARDEN,
			Darkness.DAYLIGHT,
			null, "в саду Lilaya"
			).initItemsPersistInTile()
			.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_GREEN);
	
	public static final AbstractPlaceType LILAYA_HOME_FOUNTAIN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Фонтан",
			"В самом центре сада весело журчит огромный, богато украшенный фонтан, словно живя своей собственной жизнью.",
			"dominion/lilayasHome/fountain",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaHomeGeneric.FOUNTAIN,
			Darkness.DAYLIGHT,
			null, "в саду Lilaya"
			).initItemsPersistInTile()
			.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_GREEN)
			.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType LILAYA_HOME_UNDER_CONSTRUCTION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Строительная площадка",
			"Эта часть особняка Lilaya сейчас расширяется, поэтому она напоминает строительную площадку...",
			"dominion/lilayasHome/construction",
			PresetColour.BASE_BROWN,
			LilayaSpa.SPA_CONSTRUCTION,
			Darkness.ALWAYS_LIGHT,
			null, "в спа-комплексе Lilaya"
			) {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isWorkTime()) {
				pop.add(new Population(true, PopulationType.CONSTRUCTION_WORKER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спа-бассейны",
			"Центральным элементом частного спа-комплекса является серия бассейнов, наполненных теплой водой из геотермальных источников.",
			"dominion/lilayasHome/roomSpa",
			PresetColour.BASE_TEAL,
			LilayaSpa.SPA_CORE,
			Darkness.ALWAYS_LIGHT,
			null, "в спа-комплексе Lilaya"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA_POOL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Бассейн",
			"В этой зоне обустроен большой крытый бассейн.",
			"dominion/lilayasHome/roomSpaPool",
			PresetColour.BASE_BLUE_LIGHT,
			LilayaSpa.SPA_POOL,
			Darkness.ALWAYS_LIGHT,
			null, "в спа-комплексе Lilaya"
			).initItemsPersistInTile();
	
	public static final AbstractPlaceType LILAYA_HOME_SPA_SAUNA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Сауна",
			"В этой зоне расположены большая сауна и паровая баня.",
			"dominion/lilayasHome/roomSpaSauna",
			PresetColour.BASE_BROWN,
			LilayaSpa.SPA_SAUNA,
			Darkness.ALWAYS_LIGHT,
			null, "в спа-комплексе Lilaya"
			).initItemsPersistInTile();
	
	
	
	// Lilaya's home (first floor):

	public static final AbstractPlaceType LILAYA_HOME_ROOM_LILAYA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната Lilaya",
			"Комната Lilaya расположена в углу первого этажа и заметно примыкает к комнате Rose...",
			"dominion/lilayasHome/roomLilaya",
			PresetColour.BASE_CRIMSON,
			LilayasRoom.ROOM_LILAYA,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_ROSE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната Rose",
			"Комната Rose расположена в углу первого этажа и заметно примыкает к комнате Lilaya...",
			"dominion/lilayasHome/roomRose",
			PresetColour.BASE_PINK,
			LilayaHomeGeneric.ROOM_ROSE,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи"
			).initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_ROOM_PLAYER = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ваша комната",
			"Комната, щедро подаренная вам Lilaya; это единственное место в Dominion, где вы можете по-настоящему беззаботно отдохнуть.",
			"dominion/lilayasHome/roomPlayer",
			PresetColour.BASE_AQUA,
			RoomPlayer.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в твоей комнате"
			) {
				@Override
				public ArrayList<AbstractPlaceUpgrade> getAvailablePlaceUpgrades(Set<AbstractPlaceUpgrade> upgrades) {
					return Util.newArrayListOfValues(
							PlaceUpgrade.LILAYA_PLAYER_ROOM_BED);
				}
				@Override
				public boolean isAbleToBeUpgraded() {
					return true;
				}
			}.initItemsPersistInTile()
			.initWeatherImmune();
	
	public static final AbstractPlaceType LILAYA_HOME_STAIR_DOWN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Эта лестница соединяет первый и цокольный этажи дома Lilaya; на середине пути есть небольшая дополнительная площадка.",
			"dominion/lilayasHome/stairsDown",
			PresetColour.BASE_RED,
			LilayaHomeGeneric.STAIRCASE_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи"
			).initItemsPersistInTile()
			.initWeatherImmune();

	public static final AbstractPlaceType LILAYA_HOME_STAIR_DOWN_SECONDARY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Эта лестница меньше той, что находится у главного входа в особняк, но служит той же цели — соединяет первый и второй этажи дома Lilaya.",
			"dominion/lilayasHome/stairsDownSecondary",
			PresetColour.BASE_RED_LIGHT,
			LilayaHomeGeneric.STAIRCASE_DOWN_SECONDARY,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Лилайи"
			).initItemsPersistInTile()
			.initWeatherImmune();
	

	
	
	// Zaranix's home (ground floor):
	
	public static final AbstractPlaceType ZARANIX_GF_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Коридоры дома Зараникса украшены многочисленными изысканными картинами, мягкими креслами и искусно сделанными шкафами.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникса"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Эта лестница соединяет цокольный и первый этажи дома Зараникса.",
			"dominion/zaranixHome/stairsDown",
			PresetColour.BASE_GREEN_LIGHT,
			ZaranixHomeGroundFloor.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникса"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeGroundFloor.STAIRS;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход",
			"Огромная хрустальная люстра заливает входной зал ярким светом, питаемым энергией Арканы, а на окружающих стенах висят изысканные картины в золоченых рамах.",
			"dominion/zaranixHome/entranceHall",
			PresetColour.BASE_RED,
			ZaranixHomeGroundFloor.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникса"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ENTRANCE;
				
			} else {
				return ZaranixHomeGroundFloor.ENTRANCE;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Гостиная",
			"Вокруг низкого стола в центре комнаты со вкусом расставлены диваны и кресла, а стены, оклеенные цветочными обоями, уставлены книжными шкафами и буфетами.",
			"dominion/zaranixHome/lounge",
			PresetColour.BASE_ORANGE,
			ZaranixHomeGroundFloor.LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникса"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.LOUNGE;
				
			} else {
				return ZaranixHomeGroundFloor.LOUNGE;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Дверь в эту комнату заперта, и изнутри не доносится ни звука.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в одной из комнат дома Зараникс"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_MAID = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Суккуб с кожей цвета слоновой кости в светло-розовой форме горничной усердно вытирает здесь пыль.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.CORRIDOR_MAID,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникс"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.CORRIDOR;
			} else {
				return ZaranixHomeGroundFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(isDangerous()) {
				return tooltipDescription;
			} else {
				return ZARANIX_GF_CORRIDOR.getTooltipDescription();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Довольно неинтересная комната, соединяющая сад с остальной частью дома Зараникс.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeGroundFloor.GARDEN_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в одной из комнат дома Зараникс"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ROOM;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Сад",
			"Сад, в котором выращивают всевозможные странные и экзотические растения.",
			"dominion/zaranixHome/garden",
			PresetColour.BASE_GREEN,
			ZaranixHomeGroundFloor.GARDEN,
			Darkness.DAYLIGHT,
			null, "в саду Зараникс"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN;
			}
		}
	};
	
	public static final AbstractPlaceType ZARANIX_GF_GARDEN_ENTRY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Сад",
			"Это место находится рядом с забором, отделяющим сад Зараникс от улиц Dominion.",
			"dominion/zaranixHome/entranceHall",
			PresetColour.BASE_GREEN,
			ZaranixHomeGroundFloor.GARDEN_ENTRY,
			Darkness.DAYLIGHT,
			null, "в саду Зараникс"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeGroundFloorRepeat.GARDEN_ENTRY;
				
			} else {
				return ZaranixHomeGroundFloor.GARDEN_ENTRY;
			}
		}
	};
	
	
	
	// Zaranix's home (first floor):
	
	public static final AbstractPlaceType ZARANIX_FF_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Коридоры дома Зараникса украшены многочисленными изысканными картинами, мягкими креслами и искусно сделанными шкафами.",
			null,
			PresetColour.BASE_GREY,
			ZaranixHomeFirstFloor.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникса"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Эта лестница соединяет первый и цокольный этажи дома Зараникса.",
			"dominion/zaranixHome/stairsDown",
			PresetColour.BASE_RED,
			ZaranixHomeFirstFloor.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникса"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.STAIRS;
				
			} else {
				return ZaranixHomeFirstFloor.STAIRS;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната Зараникса",
			"Кабинет Зараникса, переоборудованный в небольшую лабораторию.",
			"dominion/zaranixHome/roomZaranix",
			PresetColour.BASE_GREEN_LIME,
			ZaranixHomeFirstFloor.ZARANIX_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникса"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ZARANIX_ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ZARANIX_ROOM;
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Дверь в эту комнату заперта, и изнутри не доносится ни звука.",
			"dominion/zaranixHome/room",
			PresetColour.BASE_GREY,
			ZaranixHomeFirstFloor.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в одной из комнат дома Зараникс"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.ROOM;
				
			} else {
				return ZaranixHomeFirstFloor.ROOM;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType ZARANIX_FF_MAID = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Суккуб с кожей цвета слоновой кости в светло-розовой форме горничной усердно вытирает здесь пыль.",
			null,
			PresetColour.BASE_RED,
			ZaranixHomeFirstFloor.CORRIDOR_MAID,
			Darkness.ALWAYS_LIGHT,
			null, "в доме Зараникс"){
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
				return ZaranixHomeFirstFloorRepeat.CORRIDOR;
				
			} else {
				return ZaranixHomeFirstFloor.CORRIDOR_MAID;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(isDangerous()) {
				return tooltipDescription;
			} else {
				return ZARANIX_FF_CORRIDOR.getTooltipDescription();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		}
	}.initWeatherImmune();
	
	
	
	
	// Angel's Kiss:

	public static final AbstractPlaceType ANGELS_KISS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Коридоры «Поцелуя Ангела» устланы роскошными бордовыми коврами, а стены отделаны темным деревом наполовину и светло-голубыми цветочными обоями — на другую половину.",
			null,
			PresetColour.BASE_GREY,
			RedLightDistrict.ANGELS_KISS_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поцелуе Ангела»"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Прихожая",
			"С высокого потолка свисает золотая люстра, заливая длинную стойку из красного дерева в вестибюле мягким белым светом.",
			"dominion/angelsKiss/entrance",
			PresetColour.BASE_RED,
			RedLightDistrict.ANGELS_KISS_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поцелуе Ангела»"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_STAIRCASE_UP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Эта лестница соединяет цокольный и первый этажи «Поцелуя Ангела».",
			"dominion/angelsKiss/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			RedLightDistrict.ANGELS_KISS_STAIRS_UP,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поцелуе Ангела»"
		).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_STAIRCASE_DOWN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Эта лестница соединяет первый и цокольный этажи «Поцелуя Ангела».",
			"dominion/angelsKiss/stairsDown",
			PresetColour.BASE_RED,
			RedLightDistrict.ANGELS_KISS_STAIRS_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поцелуе Ангела»"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спальня",
			"В этих спальнях ведутся дела заведения, и для этой цели в каждой из них стоит чистая кровать королевского размера.",
			"dominion/angelsKiss/bedroom",
			PresetColour.BASE_PINK,
			RedLightDistrict.ANGELS_KISS_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поцелуе Ангела»"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM_BUNNY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спальня Банни",
			"В этой спальне живет покорная проститутка Банни, сестра-близнец Лоппи.",
			"dominion/angelsKiss/bedroomBunny",
			PresetColour.BASE_PINK_LIGHT,
			RedLightDistrict.ANGELS_KISS_BEDROOM_BUNNY,
			Darkness.ALWAYS_LIGHT,
			null, "в спальне Банни"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_BEDROOM_LOPPY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спальня Лоппи",
			"В этой спальне живет доминантная проститутка Лоппи, сестра-близнец Банни.",
			"dominion/angelsKiss/bedroomLoppy",
			PresetColour.BASE_PURPLE,
			RedLightDistrict.ANGELS_KISS_BEDROOM_LOPPY,
			Darkness.ALWAYS_LIGHT,
			null, "в спальне Лоппи"
			).initWeatherImmune();
	
	public static final AbstractPlaceType ANGELS_KISS_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет Энджел",
			"Комната, в которой Энджел занимается всей бумажной работой, необходимой для работы «административного центра, санкционированного Enforcer».",
			"dominion/angelsKiss/office",
			PresetColour.BASE_BLUE_LIGHT,
			RedLightDistrict.ANGELS_KISS_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "в кабинете Энджел"
			).initWeatherImmune();
	
	
	
	
	// Shopping arcade:
	
	public static final AbstractPlaceType SHOPPING_ARCADE_PATH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Аркада",
			"По обе стороны главных торговых улиц, проходящих через аркаду, выстроились самые разные лавки.",
			null,
			PresetColour.BASE_BLACK,
			ShoppingArcadeDialogue.ARCADE,
			Darkness.ALWAYS_LIGHT,
			null, "в Торговой аркаде") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			pop.add(new Population(true, PopulationType.ENFORCER, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
			return pop;
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_GENERIC_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лавка",
			"Одна из множества безликих лавок в аркаде; в этом конкретном магазине нет ничего, что стоило бы вашего времени или денег.",
			"dominion/shoppingArcade/genericShop",
			PresetColour.BASE_BLACK,
			ShoppingArcadeDialogue.GENERIC_SHOP,
			Darkness.ALWAYS_LIGHT,
			null, "в Торговой аркаде") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_RALPHS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Закуски от Ralph",
			"Магазин, специализирующийся на продаже еды, напитков и прочих всяческих мелочей. Дружелюбный владелец «Закусок от Ralph» — мускулистый кареглазый старший жеребец-мальчик.",
			"dominion/shoppingArcade/ralphShop",
			PresetColour.BASE_TEAL,
			RalphsSnacks.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в его лавке"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_NYANS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Магазин одежды Nyan",
			"Двухэтажный магазин «Магазин одежды Nyan» — самое крупное торговое заведение во всей аркаде.",
			"dominion/shoppingArcade/nyanShop",
			PresetColour.BASE_ROSE,
			ClothingEmporium.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"в её лавке") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isHourBetween(9, 20) && !Main.game.getCurrentDialogueNode().isTravelDisabled()) { // Travel disabled indicates that the player is in the storeroom with Nyan
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.DOZENS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_VICKYS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Искусства Arcane",
			"Специализирующаяся на продаже арканного оружия и сопутствующих товаров, лавка «Arcane Arts» управляется весьма свирепой на вид волчьей девушкой по имени Vicky.",
			"dominion/shoppingArcade/vickyShop",
			PresetColour.BASE_MAGENTA,
			ArcaneArts.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в её лавке"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_KATES_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Секреты Суккуба",
			"С заколоченными окнами и облупившейся краской, этот салон красоты на первый взгляд кажется заброшенным, но при ближайшем рассмотрении обнаруживается висящая на двери табличка «открыто».",
			"dominion/shoppingArcade/kateShop",
			PresetColour.BASE_PINK,
			SuccubisSecrets.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в её салоне красоты"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ASHLEYS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Любимец снов",
			"Единственный магазин в Dominion, специализирующийся на подарках для других.",
			"dominion/shoppingArcade/ashleyShop",
			PresetColour.BASE_LILAC_LIGHT,
			DreamLover.EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в их лавке"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ANTIQUES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Антикварная лавка",
			"Антикварная лавка, торгующая пестрой смесью скрипучей старой мебели, устаревших аркана-инструментов и всевозможного прочего антиквариата.",
			"dominion/shoppingArcade/antiques",
			PresetColour.BASE_BROWN,
			ShoppingArcadeDialogue.ANTIQUES,
			Darkness.ALWAYS_LIGHT,
			null, "в антикварной лавке"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_RESTAURANT = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Дубрава",
			"Ресторан «Дубрава» обслуживает наиболее состоятельных посетителей Торгового пассажа; их самый простой обед из трех блюд стоит баснословных почти тысячу огней.",
			"dominion/shoppingArcade/restaurant",
			PresetColour.BASE_GREEN_DARK,
			ShoppingArcadeDialogue.RESTAURANT,
			Darkness.ALWAYS_LIGHT,
			null, "в «Дубраве\"") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getHourOfDay()>=18) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.DINER, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SHOPPING_ARCADE_PIXS_GYM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Игровая площадка Пикса",
			"Огромный многоэтажный спортзал «Площадка Пикс» принадлежит и управляется необычайно энергичной девочкой-бордер-колли.",
			"dominion/shoppingArcade/gym",
			PresetColour.BASE_GOLD,
			PixsPlayground.GYM_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в спортзале «Игровая площадка Пикса»"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Выход",
			"Эти большие стеклянные двери ведут обратно на улицы Dominion.",
			"dominion/shoppingArcade/exit",
			PresetColour.BASE_RED,
			ShoppingArcadeDialogue.ENTRY,
			Darkness.ALWAYS_LIGHT,
			null, "в Торговой аркаде"
			).initWeatherImmune();

	public static final AbstractPlaceType SHOPPING_ARCADE_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Туалеты",
			"Удобно расположенный рядом со входом в Торговый пассаж, здесь есть общественные туалеты.",
			"dominion/shoppingArcade/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			ShoppingArcadeDialogue.TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "в общественных туалетах Торгового пассажа") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isExtendedWorkTime()) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.SHOPPER, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				return new ArrayList<>();
			}
		}
	}.initWeatherImmune();
	
	
	
	// Supplier Depot:
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Хорошо освещенный коридор, ведущий к складским помещениям и зонам ткачества, тянется вдоль всего здания.",
			null,
			PresetColour.BASE_BLACK,
			KaysWarehouse.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Текстиле Кей»") {
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "Когда с доберманами было покончено, коридор в задней части здания отремонтировали, и теперь он чистый и ярко освещенный.";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Зона ресепшена",
			"На ресепшене склада дежурит выглядящая подавленной собачья девчонка, которая, кажется, совсем не заинтересована в выполнении своих обязанностей.",
			"dominion/textilesWarehouse/exit",
			PresetColour.BASE_RED,
			KaysWarehouse.RECEPTION,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Текстиле Кей»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return "После того как с доберманами разобрались, подавленная собачья девчонка на ресепшене кажется куда более счастливой и готовой помочь посетителям.";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_STORAGE_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Склад",
			"Этот склад забит многочисленными ящиками, каждый из которых доверху наполнен зачатой одеждой.",
			"dominion/textilesWarehouse/storage",
			PresetColour.BASE_RED,
			KaysWarehouse.STORAGE_AREA,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Текстиле Кей»"
			) {
		@Override
		public Colour getColour() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)
					|| !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
				return PresetColour.BASE_RED;
			} else {
				return PresetColour.BASE_GREEN;
			}
		}
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)
					|| !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
				return SVGString;
			} else {
				return getSVGOverride("dominion/textilesWarehouse/storage", PresetColour.BASE_GREEN);
			}
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_ENCHANTING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ткацкие станки",
			"В этой зоне десятки текстильщиков управляют огромным количеством устройств, которые выглядят как арканаческие ткацкие станки.",
			"dominion/textilesWarehouse/enchanting",
			PresetColour.GENERIC_ARCANE,
			KaysWarehouse.WEAVING_MACHINES,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Текстиле Кей»"
			) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.TEXTILE_WORKER, PopulationDensity.NUMEROUS,
							Util.newHashMapOfValues(
									new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.CAT_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.COW_MORPH, SubspeciesSpawnRarity.TEN),
									new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_OVERSEER_STATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Пост надзирателя",
			"Ряд ступеней ведет на мезонин, на котором расположен пост надзирателя, возвышающийся над всем складским помещением.",
			"dominion/textilesWarehouse/overseer_station",
			PresetColour.BASE_GREY_DARK,
			KaysWarehouse.OVERSEER_STATION,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Текстиле Кей»"
			) {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getWorlds().get(WorldType.TEXTILES_WAREHOUSE).getCell(PlaceType.TEXTILE_WAREHOUSE_OFFICE).isTravelledTo()) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.TEXTILE_WORKER, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.TEN))));
			} else {
				return super.getPopulation();
			}
		}
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS);
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getWorlds().get(WorldType.TEXTILES_WAREHOUSE).getCell(PlaceType.TEXTILE_WAREHOUSE_OFFICE).isTravelledTo()) {
				return "После того как с Доберманнами было покончено, пост надзирателя у кабинета Кей теперь должным образом укомплектован одним из текстильщиков.";
			} else {
				return tooltipDescription;
			}
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType TEXTILE_WAREHOUSE_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет Кей",
			"В кабинет Кей можно попасть через пост надзирателя; он довольно мал, но обставлен очень элегантно.",
			"dominion/textilesWarehouse/office",
			PresetColour.BASE_BLUE_LIGHT,
			KaysWarehouse.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Текстиле Кей»").initWeatherImmune();
	
	
	
	// Slaver Alley:
	
	public static final AbstractPlaceType SLAVER_ALLEY_PATH = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Переулок",
			"Переулки, проходящие через Рабский переулок, совсем не похожи на те, что встречаются в остальном Dominion: они оживленные, чистые и, что самое важное, очень безопасные.",
			null,
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.ALLEYWAY,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			pop.add(new Population(false, PopulationType.PRIVATE_SECURITY_GUARD, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_FEMALES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Женское прикосновение",
			"Магазин, специализирующийся на продаже хорошо обученных и послушных рабынь.",
			"dominion/slaverAlley/marketStallFemale",
			PresetColour.BASE_PINK_LIGHT,
			SlaverAlleyDialogue.MARKET_STALL_FEMALE,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_MALES = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Железо и сталь",
			"От чернорабочих до радующих глаз моделей — этот магазин специализируется на продаже самых разных видов рабов-мужчин.",
			"dominion/slaverAlley/marketStallMale",
			PresetColour.BASE_BLUE_STEEL,
			SlaverAlleyDialogue.MARKET_STALL_MALE,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_ANAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Задний вход",
			"Специализируясь на продаже рабынь, обученных любить анальный секс, этот магазин является одним из трех, что окружают похотливую статую Марлель.",
			"dominion/slaverAlley/marketStallAnal",
			PresetColour.BASE_ORANGE,
			SlaverAlleyDialogue.MARKET_STALL_ANAL,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_VAGINAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Белые лилии",
			"Один из трех магазинов, окружающих статую падшего ангела Марлель, «Белые лилии» специализируется на продаже девственниц.",
			"dominion/slaverAlley/marketStallVaginal",
			PresetColour.BASE_PINK,
			SlaverAlleyDialogue.MARKET_STALL_VAGINAL,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STALL_ORAL = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Viva Voce",
			"«Viva Voce» специализируется на продаже рабов, обученных любить оральный секс, и является одним из трех специализированных магазинов, расположенных вокруг статуи Марлель.",
			"dominion/slaverAlley/marketStallOral",
			PresetColour.BASE_BLUE_LIGHT,
			SlaverAlleyDialogue.MARKET_STALL_ORAL,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_STATUE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Статуя Падшего Ангела",
			"Воздвигнутая на высоком постаменте, эта статуя падшего ангела Марлел изображает её в крайне развращённом и невероятно похотливом облике.",
			"dominion/slaverAlley/marketStallStatue",
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.MARKET_STALL_STATUE,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_MARKET_STALL_EXCLUSIVE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Прокат рабов",
			"Довольно много лавок в Переулке Рабов не продают рабов напрямую, а вместо этого сдают их в аренду предприятиям, которым нужно временно восполнить нехватку рабочей силы.",
			"dominion/slaverAlley/marketStallExclusive",
			PresetColour.BASE_GREY,
			SlaverAlleyDialogue.MARKET_STALL_EXCLUSIVE,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_MARKET_STALL_BULK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Биржа Зайбацу",
			"Безусловно, крупнейшая лавка во всём Переулке Рабов, «Биржа Зайбацу» контролируется могущественным конгломератом работорговцев и отказывается вести дела с теми, кто не является членом их организации.",
			"dominion/slaverAlley/marketStallBulk",
			PresetColour.BASE_BLUE,
			SlaverAlleyDialogue.MARKET_STALL_BULK,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return SLAVER_ALLEY_PATH.getPopulation();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кафе",
			"По всему Переулку Рабов разбросано множество кафе, где покупатели могут отдохнуть и восстановить свою энергию.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_2 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кафе",
			"По всему Переулку Рабов разбросано множество кафе, где покупатели могут отдохнуть и восстановить свою энергию.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_3 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кафе",
			"По всему Переулку Рабов разбросано множество кафе, где покупатели могут отдохнуть и восстановить свою энергию.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	
	public static final AbstractPlaceType SLAVER_ALLEY_CAFE_4 = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кафе",
			"По всему Переулку Рабов разбросано множество кафе, где покупатели могут отдохнуть и восстановить свою энергию.",
			"dominion/slaverAlley/marketStallCafe",
			PresetColour.BASE_BROWN,
			SlaverAlleyDialogue.MARKET_STALL_CAFE,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
			}
			return pop;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_AUCTIONING_BLOCK = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Аукционный помост",
			"Посреди обширной площади возвышается огромная деревянная платформа, на которой проводятся публичные аукционы.",
			"dominion/slaverAlley/auctionBlock",
			PresetColour.BASE_GOLD,
			SlaverAlleyDialogue.AUCTION_BLOCK,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_PUBLIC_STOCKS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Общественные колодки",
			"Ряд колодок для публичных наказаний расположен прямо перед входом в Переулок Рабов, служа напоминанием о том, что ждет непокорных рабов.",
			"dominion/slaverAlley/stocks",
			PresetColour.BASE_TAN,
			SlaverAlleyDialogue.PUBLIC_STOCKS,
			Darkness.ALWAYS_LIGHT,
			null, "в колодках у Переулка Рабов") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_BOUNTY_HUNTERS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"«Ржавый ошейник»",
			"Таверна, где можно нанять охотников за головами, чтобы выследить сбежавших рабов.",
			"dominion/slaverAlley/bountyHunters",
			PresetColour.BASE_COPPER,
			SlaverAlleyDialogue.BOUNTY_HUNTERS,
			Darkness.ALWAYS_LIGHT,
			null,
			"в Рабском переулке"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_SLAVERY_ADMINISTRATION = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Администрация рабства",
			"Главный административный центр по всем вопросам, связанным с владением рабами.",
			"dominion/slaverAlley/slaveryAdministration",
			PresetColour.BASE_PURPLE,
			SlaveryAdministration.SLAVERY_ADMINISTRATION_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке"){
		@Override
		public ArrayList<AbstractPlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS);
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_SCARLETTS_SHOP = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лавка Scarlett",
			"",
			"dominion/slaverAlley/scarlettsStall",
			PresetColour.BASE_CRIMSON,
			ScarlettsShop.SCARLETTS_SHOP_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке"){
		@Override
		public Colour getColour() {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return PresetColour.BASE_BLACK;
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)) {
				return PresetColour.BASE_GOLD;
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)) {
				return PresetColour.BASE_GREY;
			}
			return PresetColour.BASE_CRIMSON;
		}
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			return getSVGOverride("dominion/slaverAlley/scarlettsStall", getColour());
		}
		@Override
		public String getName() {
			if(Main.game.isStarted()) {
				if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
					return "Заброшенная лавка";
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)) {
					return "Бутик Helena";
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)) {
					return "Безымянная лавка";
				}
			}
			return "Лавка Scarlett";
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) { // Scarlett owns the shop:
				return ScarlettsShop.SCARLETTS_SHOP_EXTERIOR;
				
			} else { // Helena owns the shop:
				return ScarlettsShop.HELENAS_SHOP_EXTERIOR;
			}
		}
		@Override
		public String getTooltipDescription() {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return "Эту работорговую лавку Helena забросила после того, как вы отказались продать ей Scarlett...";
				
			} else if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
				return "Лавка рабов, которой заправляет гарпия по имени Scarlett. В отличие от всех остальных лавок в Переулке Рабов, в её лавке совершенно нет рабов на продажу...";
				
			} else {
				return "Матриарх Scarlett, Helena, взяла управление этой лавкой на себя.";
			}
		}
		@Override
		public List<Population> getPopulation() {
			if(Main.game.isStarted()) {
				if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_6_ADVERTISING)) {
					if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
						return Util.newArrayListOfValues(
								new Population(true, PopulationType.HARPY, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.HARPY_NEST, this, false, false)),
								new Population(true, PopulationType.SHOPPER, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
								new Population(true, PopulationType.FAN, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					} else {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					}
					
				} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) && !Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
					if(Main.game.getNpc(Helena.class).getLocationPlace().getPlaceType()==PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP) {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					} else {
						return Util.newArrayListOfValues(new Population(true, PopulationType.FAN, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
					}
				}
			}
			return new ArrayList<>();
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType SLAVER_ALLEY_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ворота",
			"Единственный вход и выход из Переулка Рабов охраняет пара конных мальчиков, которые бдительно следят за любыми сбежавшими рабами.",
			"dominion/slaverAlley/exit",
			PresetColour.BASE_RED,
			SlaverAlleyDialogue.GATEWAY,
			Darkness.ALWAYS_LIGHT,
			null, "в Рабском переулке") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(
					new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)),
					new Population(true, PopulationType.PRIVATE_SECURITY_GUARD, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType SLAVER_ALLEY_DESERTED_ALLEYWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Пустынный переулок",
			"Узкий переулок змеится за несколькими лавками, прежде чем внезапно упереться в тупик.",
			"dominion/slaverAlley/desertedAlleyway",
			PresetColour.BASE_BLACK,
			SlaverAlleyDialogue.DESERTED_ALLEYWAY,
			Darkness.DAYLIGHT,
			null, "в Рабском переулке").initWeatherImmune(Weather.MAGIC_STORM);
	
	
	// Bounty hunter lodge:

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход",
			"Главный вход в таверну представляет собой пару выветренных деревянных дверей.",
			"dominion/slaverAlley/bountyHunterLodge/exit",
			PresetColour.BASE_RED,
			BountyHunterLodge.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»")
		.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Первый этаж",
			"Большую часть площади внутри таверны занимают дюжина больших деревянных столов, за которыми сидят охотники за головами и всякого рода сомнительные личности.",
			null,
			PresetColour.BASE_BLACK,
			BountyHunterLodge.FLOOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
		@Override
		public boolean isLoiteringEnabledOverride() {
			return true;
		}
		@Override
		public boolean isLoiteringEnabled() {
			return true;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_BOUNTY_BOARD = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Доска объявлений",
			"Рядом с главным входом стоит огромная деревянная доска, на которой вывешены активные заказы на головы.",
			"dominion/slaverAlley/bountyHunterLodge/bountyBoard",
			PresetColour.CLOTHING_BLUE_GREY,
			BountyHunterLodge.BOUNTY_BOARD,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_BAR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Бар",
			"Длинная барная стойка всегда оживлена, и здесь многочисленных посетителей таверны снабжают всевозможным алкоголем.",
			"dominion/slaverAlley/bountyHunterLodge/bar",
			PresetColour.BASE_ORANGE,
			BountyHunterLodge.BAR,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.NUMEROUS, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
		@Override
		public boolean isLoiteringEnabledOverride() {
			return true;
		}
		@Override
		public boolean isLoiteringEnabled() {
			return true;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_SEATING = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ниши для сидения",
			"По краям основного зала таверны расположено несколько уютных ниш для сидения.",
			"dominion/slaverAlley/bountyHunterLodge/seatingArea",
			PresetColour.BASE_BROWN,
			BountyHunterLodge.SEATING,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»"){
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.FEW, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Узкая лестница ведет на второй этаж таверны.",
			"dominion/slaverAlley/bountyHunterLodge/stairsUp",
			PresetColour.BASE_GREEN_LIGHT,
			BountyHunterLodge.STAIRS,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»")
		.initWeatherImmune(Weather.MAGIC_STORM);
	
	// First floor:
	
	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Узкий коридор соединяет все комнаты на втором этаже таверны.",
			null,
			PresetColour.BASE_BLACK,
			BountyHunterLodge.UPSTAIRS_CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»") {
		@Override
		public boolean isLoiteringEnabledOverride() {
			return true;
		}
		@Override
		public boolean isLoiteringEnabled() {
			return true;
		}
	}.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_STAIRS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Лестница",
			"Узкая лестница ведет на первый этаж таверны.",
			"dominion/slaverAlley/bountyHunterLodge/stairsDown",
			PresetColour.BASE_RED_LIGHT,
			BountyHunterLodge.UPSTAIRS_STAIRS,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Как и все остальные комнаты здесь, эта уже сдана в аренду...",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_DOBERMANNS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Как и все остальные комнаты здесь, эта уже сдана в аренду...",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»")
		.initWeatherImmune(Weather.MAGIC_STORM);

	public static final AbstractPlaceType BOUNTY_HUNTER_LODGE_UPSTAIRS_ROOM_SHADOW_SILENCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Комната",
			"Как и все остальные комнаты здесь, эта уже сдана в аренду...",
			"dominion/slaverAlley/bountyHunterLodge/room",
			PresetColour.BASE_TEAL,
			BountyHunterLodge.UPSTAIRS_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в «Ржавом ошейнике»")
		.initWeatherImmune(Weather.MAGIC_STORM);

	
	// Watering hole:
	
	public static final AbstractPlaceType WATERING_HOLE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Вход",
			"У входа в клуб «Поилочка» дежурят двое охранников — зебромальчиков.",
			"dominion/nightLife/exit",
			PresetColour.BASE_RED,
			NightlifeDistrict.WATERING_HOLE_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поилочке»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
		@Override
		public boolean isFurniturePresent() {
			return true;
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_MAIN_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Поилочка",
			"В клубе полно народу: все заняты выпивкой, болтовней и страстными поцелуями.",
			null,
			PresetColour.BASE_BLUE_LIGHT,
			NightlifeDistrict.WATERING_HOLE_MAIN,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поилочке»") {
		@Override
		public List<Population> getPopulation() {
			return WATERING_HOLE_ENTRANCE.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_SEATING_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Зона отдыха",
			"У края клуба обустроены зоны, где гуляки могут относительно спокойно присесть и поболтать друг с другом.",
			"dominion/nightLife/seatingArea",
			PresetColour.BASE_BROWN,
			NightlifeDistrict.WATERING_HOLE_SEATING,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поилочке»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_VIP_AREA = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"VIP-зона",
			"VIP-зона клуба, охраняемая парой мускулистых львов-вышибал, состоит из множества полукруглых кабинок, в каждой из которых стоит стол из полированного черного мрамора и длинный изогнутый кожаный диван.",
			"dominion/nightLife/vipArea",
			PresetColour.BASE_PURPLE,
			NightlifeDistrict.WATERING_HOLE_VIP,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поилочке»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.VIP, PopulationDensity.SEVERAL,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_lion"), SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.HORSE_MORPH_ZEBRA, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_BAR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Бар",
			"Бар в клубе работает без остановки: многочисленные барменши — зебры и львицы — разносят заказы один за другим.",
			"dominion/nightLife/bar",
			PresetColour.BASE_ORANGE,
			NightlifeDistrict.WATERING_HOLE_BAR,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поилочке»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_DANCE_FLOOR = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Танцпол",
			"Центральный танцпол — самое оживленное и шумное место во всем клубе.",
			"dominion/nightLife/danceFloor",
			PresetColour.BASE_PINK_DEEP,
			NightlifeDistrict.WATERING_HOLE_DANCE_FLOOR,
			Darkness.ALWAYS_LIGHT,
			null, "в «Поилочке»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CROWD, PopulationDensity.DENSE, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType WATERING_HOLE_TOILETS = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Туалеты",
			"Туалеты в клубе довольно просторные, с множеством кабинок и раковин.",
			"dominion/nightLife/toilets",
			PresetColour.BASE_BLUE_LIGHT,
			NightlifeDistrict.WATERING_HOLE_TOILETS,
			Darkness.ALWAYS_LIGHT,
			null, "в туалетах «Поилки»") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.NIGHTLIFE_CLUB, this, false)));
		}
	}.initWeatherImmune();
	
	
	// Daddy's apartment:
	
	public static final AbstractPlaceType DADDY_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Прихожая",
			"Прихожая квартиры.",
			"dominion/daddy/entranceHall",
			PresetColour.BASE_GREEN,
			DaddyDialogue.PLACE_ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "в прихожей папиной квартиры"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Гостиная",
			"Гостиная квартиры.",
			"dominion/daddy/lounge",
			PresetColour.BASE_ORANGE,
			DaddyDialogue.PLACE_LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "в гостиной папиной квартиры"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кухня",
			"Кухня квартиры.",
			"dominion/daddy/kitchen",
			PresetColour.BASE_TAN,
			DaddyDialogue.PLACE_KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "на кухне папиной квартиры"
		).initWeatherImmune();

	public static final AbstractPlaceType DADDY_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спальня",
			"Спальня квартиры.",
			"dominion/daddy/bedroom",
			PresetColour.BASE_CRIMSON,
			DaddyDialogue.PLACE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в спальне папы"
		).initWeatherImmune();
	
	
	// Helena's apartment:
	
	public static final AbstractPlaceType HELENA_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Широкие коридоры с ковровым покрытием соединяют комнаты в квартире Helena.",
			null,
			PresetColour.BASE_BLACK,
			HelenaApartment.PLACE_HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null, "в коридоре квартиры Helena"
		) {
			@Override
			public List<Population> getPopulation() {
				return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.OCCASIONAL, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
			}
		}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BALCONY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Балкон",
			"Этот огромный балкон с деревянным настилом возвышается над улицами Dominion.",
			null,
			PresetColour.BASE_BLUE_LIGHT,
			HelenaApartment.PLACE_BALCONY,
			Darkness.ALWAYS_LIGHT,
			null, "на балконе в квартире Helena"
		).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_BLUE)
		.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Прихожая",
			"Прихожая в пентхаусе Helena впечатляет своими размерами и со вкусом оформлена в современном стиле.",
			"dominion/helenaApartment/entranceHall",
			PresetColour.BASE_GREEN,
			HelenaApartment.PLACE_ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "в прихожей квартиры Helena"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_HELENA_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спальня Helena",
			"Спальня Helena впечатляет своими размерами и роскошной мебелью.",
			"dominion/helenaApartment/bedroomHelena",
			PresetColour.BASE_GOLD,
			HelenaApartment.PLACE_HELENA_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в спальне Helena"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_SCARLETT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спальня Scarlett",
			"Спальня Scarlett впечатляет своими размерами и роскошной мебелью.",
			"dominion/helenaApartment/bedroomScarlett",
			PresetColour.BASE_CRIMSON,
			HelenaApartment.PLACE_SCARLETT_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в спальне Scarlett"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Гостевая спальня",
			"Пара свободных спален всегда готова принять любых гостей, которые могут остаться здесь на ночь.",
			"dominion/helenaApartment/bedroom",
			PresetColour.BASE_YELLOW,
			HelenaApartment.PLACE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в одной из гостевых спален в квартире Helena"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Личная ванная",
			"В каждой спальне в квартире Helena есть своя собственная ванная комната.",
			"dominion/helenaApartment/bathroom",
			PresetColour.BASE_BLUE_LIGHT,
			HelenaApartment.PLACE_BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в одной из ванных комнат в квартире Helena"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_OFFICE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кабинет",
			"Расположенный в центре квартиры Helena, её личный кабинет — это место, куда она уходит, чтобы подумать и побыть наедине с собой.",
			"dominion/helenaApartment/office",
			PresetColour.BASE_BROWN,
			HelenaApartment.PLACE_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "в кабинете в квартире Helena"
		).initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кухня",
			"На кухне в квартире Helena всегда работает превосходный профессиональный шеф-повар.",
			"dominion/helenaApartment/kitchen",
			PresetColour.BASE_ORANGE,
			HelenaApartment.PLACE_KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null, "на кухне в квартире Helena"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.CHEF, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_DINING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Столовая",
			"Центральное место в этой просторной столовой занимает огромный стол, окруженный дюжиной стульев.",
			"dominion/helenaApartment/diningRoom",
			PresetColour.BASE_BLUE_STEEL,
			HelenaApartment.PLACE_DINING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в столовой в квартире Helena"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(false, PopulationType.MAID, PopulationDensity.ONE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Гостиная",
			"В огромной гостиной открытой планировки в квартире Helena расположено множество удобных диванов.",
			"dominion/helenaApartment/lounge",
			PresetColour.BASE_INDIGO,
			HelenaApartment.PLACE_LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null, "в гостиной в квартире Helena"
		) {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.HARPY, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType HELENA_APARTMENT_HOT_TUB = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Джакузи",
			"В одном из углов балкона стоит большое джакузи, работающее на энергии Арканы.",
			"dominion/helenaApartment/hotTub",
			PresetColour.BASE_RED_LIGHT,
			HelenaApartment.PLACE_HOT_TUB,
			Darkness.ALWAYS_LIGHT,
			null, "в джакузи на балконе в квартире Helena"
		).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_BLUE)
		.initWeatherImmune();
	
	

	// Helena's apartment:
	
	public static final AbstractPlaceType NYAN_APARTMENT_HALLWAY = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Коридор",
			"Широкие коридоры с ковровым покрытием соединяют комнаты в квартире Nyan.",
			null,
			PresetColour.BASE_BLACK,
			NyanApartment.HALLWAY,
			Darkness.ALWAYS_LIGHT,
			null,
			"в коридоре квартиры Nyan"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_ENTRANCE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Прихожая",
			"Хотя скромность и украшает это место, прихожая в квартире Nyan достаточно просторна, чтобы в ней можно было разместить встроенную гардеробную.",
			"dominion/nyanApartment/entranceHall",
			PresetColour.BASE_RED,
			NyanApartment.ENTRANCE_HALL,
			Darkness.ALWAYS_LIGHT,
			null,
			"в прихожей квартиры Nyan"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_NYAN_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Спальня Nyan",
			"Оформленная в мягких пастельных тонах и хранящая впечатляющую коллекцию любовных романов и мягких игрушек, спальня Nyan — это единственное место, где она чувствует себя в полном спокойствии.",
			"dominion/nyanApartment/bedroomNyan",
			PresetColour.BASE_PINK_LIGHT,
			NyanApartment.NYAN_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в спальне Nyan"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_SPARE_BEDROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Гостевая спальня",
			"Прямо напротив спальни Nyan находится полностью обставленная гостевая спальня.",
			"dominion/nyanApartment/bedroom",
			PresetColour.BASE_LILAC,
			NyanApartment.SPARE_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в гостевой спальне квартиры Nyan"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_ENSUITE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Личная ванная",
			"В спальне Nyan есть собственная ванная комната, половину которой занимает огромная ванна.",
			"dominion/nyanApartment/bathroom",
			PresetColour.BASE_AQUA,
			NyanApartment.ENSUITE,
			Darkness.ALWAYS_LIGHT,
			null,
			"в ванной комнате квартиры Nyan"
		).initWeatherImmune();

	public static final AbstractPlaceType NYAN_APARTMENT_BATHROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Ванная",
			"Эта ванная довольно тесная, в ней есть только унитаз и раковина.",
			"dominion/nyanApartment/toilet",
			PresetColour.BASE_BLUE_LIGHT,
			NyanApartment.BATHROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в ванной в квартире Nyan"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_KITCHEN = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Кухня",
			"Кухня Nyan объединена с обеденной зоной и имеет вполне приличный размер. Из окон открывается хороший вид на улицу внизу.",
			"dominion/nyanApartment/kitchen",
			PresetColour.BASE_ORANGE,
			NyanApartment.KITCHEN,
			Darkness.ALWAYS_LIGHT,
			null,
			"на кухне в квартире Nyan"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_DINING_ROOM = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Столовая",
			"Обеденная зона Nyan объединена с кухней; здесь есть несколько шкафов, а также привычные стол и стулья. Из окон открывается хороший вид на улицу внизу.",
			"dominion/nyanApartment/diningRoom",
			PresetColour.BASE_BLUE_STEEL,
			NyanApartment.DINING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в столовой в квартире Nyan"
		).initWeatherImmune();
	
	public static final AbstractPlaceType NYAN_APARTMENT_LOUNGE = new AbstractPlaceType(
			WorldRegion.DOMINION,
			"Гостиная",
			"В просторной гостиной Nyan стоит несколько мягких диванов, каждый из которых усыпан яркими подушками.",
			"dominion/nyanApartment/lounge",
			PresetColour.BASE_INDIGO,
			NyanApartment.LOUNGE,
			Darkness.ALWAYS_LIGHT,
			null,
			"в гостиной в квартире Nyan"
		).initWeatherImmune();
	
	
	
	// Submission:

	public static final AbstractPlaceType SUBMISSION_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Контрольно-пропускной пункт Enforcer",
			"Чтобы популяция бесов не разбрелась по всему Dominion, каждый из многочисленных входов в Submission представляет собой хорошо охраняемый контрольно-пропускной пункт Enforcer.",
			"submission/submissionExit",
			PresetColour.BASE_BROWN,
			SubmissionGenericPlaces.SEWER_ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.ENFORCER, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.ALLIGATOR_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.CAT_MORPH, SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN),
							new Value<>(Subspecies.FOX_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.HORSE_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.RABBIT_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.FIVE),
							new Value<>(Subspecies.WOLF_MORPH, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_WALKWAYS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Пешеходные дорожки",
			"Вдоль подземных водных путей, пронизывающих почти всё Submission, вдоль серых каменных стен возведены ухоженные деревянные настилы.",
			null,
			PresetColour.BASE_BLACK,
			SubmissionGenericPlaces.WALKWAYS,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission") {
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			if(Main.game.isExtendedWorkTime()) {
				pop.add(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false)));
			}
			pop.addAll(SUBMISSION_ENTRANCE.getPopulation());
			return pop;
		}
	}.initWeatherImmune()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType SUBMISSION_TUNNELS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Туннели",
			"Затененные ниши и широкие отверстия труб делают эти темные, клаустрофобные туннели идеальным местом для засады...",
			"submission/tunnelsIcon",
			PresetColour.BASE_BLACK,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "в Submission"
			).initDangerous()
			.initWeatherImmune()
			.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType SUBMISSION_BAT_CAVERNS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Пещеры летучих мышей",
			"Здесь дорожки Submission заканчиваются, и глубокий темный провал с чередой крутых каменных ступеней ведет вниз, в пещеры летучих мышей.",
			"submission/batCaverns",
			PresetColour.BASE_BLUE,
			SubmissionGenericPlaces.BAT_CAVERNS,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission"
			).initWeatherImmune()
			.initAquatic(Aquatic.MIXED)
			.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType SUBMISSION_RAT_WARREN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Крысиные Норы",
			"В этой области можно найти вход в крысиные норы, представляющий собой каменную арку, запечатанную парой тяжелых дубовых дверей.",
			"submission/ratWarren",
			PresetColour.BASE_BROWN_DARK,
			SubmissionGenericPlaces.RAT_WARREN,
			Darkness.ALWAYS_DARK,
			null, "в Submission"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_GAMBLING_DEN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Игорный притон",
			"'Игорный притон' — бесспорно, самое популярное место во всем Submission и основная цель для посетителей из Dominion.",
			"submission/gamblingDen",
			PresetColour.BASE_GOLD,
			SubmissionGenericPlaces.GAMBLING_DEN,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission"
			) {
		@Override
		public List<Population> getPopulation() {
			return SUBMISSION_WALKWAYS.getPopulation();
		}
	}.initWeatherImmune()
	.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Дворец Lyssieth",
			"Дворец старшей лилин, Lyssieth, расположен в самом дальнем уголке Submission.",
			"submission/lilinPalace",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.LILIN_PALACE,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission"
			).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
			.initWeatherImmune()
			.initTeleportPermissions(TeleportPermissions.NONE);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE_GATE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Врата дворца Lyssieth",
			"Дворцовые ворота надежно охраняются группой полудемонов.",
			"submission/gate",
			PresetColour.BASE_PURPLE_LIGHT,
			SubmissionGenericPlaces.LILIN_PALACE_GATE,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
	.initWeatherImmune()
	.initTeleportPermissions(TeleportPermissions.NONE);
	
	public static final AbstractPlaceType SUBMISSION_LILIN_PALACE_CAVERN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Пещера",
			"Дворец Lyssieth расположен на противоположной стороне огромной пещеры, пол которой плавно уходит вниз к главным воротам.",
			null,
			PresetColour.BASE_GREY,
			SubmissionGenericPlaces.LILIN_PALACE_CAVERN,
			Darkness.ALWAYS_DARK,
			null, "в Submission"
			).initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
			.initWeatherImmune();
	
	
	
	
	// Alpha succubus imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_ALPHA = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Крепость бесов",
			"Грубая обнесенная стенами крепость, возведенная на возвышающемся скалистом холме, расположена посреди огромной подземной пещеры.",
			"submission/impFortress1",
			PresetColour.BASE_CRIMSON,
			SubmissionGenericPlaces.IMP_FORTRESS_ALPHA,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getNpc(FortressAlphaLeader.class).getWorldLocation()!=WorldType.IMP_FORTRESS_ALPHA) {
				return getSVGOverride("submission/impFortress1", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress1", PresetColour.BASE_CRIMSON);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_ALPHA = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Туннели бесов",
			"Эти туннели особенно опасны, так как в них обитают враждебные группы бродячих бесов.",
			"submission/impTunnels1Icon",
			PresetColour.BASE_RED,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "в Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated)) {
				return getSVGOverride("submission/impTunnels1Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels1Icon", PresetColour.BASE_RED);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_ALPHA_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Ворота",
			"Главные ворота крепости ведут обратно в Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в крепости Альфа-беса"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_ALPHA_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Внутренний двор",
			"Между воротами и деревянной цитаделью крепости раскинулся лишь пустынный, запущенный внутренний двор.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "в крепости Альфа-беса"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_ALPHA_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Цитадель",
			"Грубо построенная цитадель служит резиденцией правителя этой конкретной крепости.",
			"submission/impFortress/keep",
			PresetColour.BASE_CRIMSON,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "в крепости Альфа-беса"
			).initDangerous()
			.initWeatherImmune();

	
	
	// Imp citadel:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_DEMON = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Цитадель бесов",
			"Огромные каменные стены высотой в потолок образуют внешние укрепления могучей цитадели бесов.",
			"submission/impFortress2",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.IMP_FORTRESS_DEMON,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return getSVGOverride("submission/impFortress2", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress2", PresetColour.BASE_PURPLE_DARK);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_DEMON = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Туннели бесов",
			"Эти туннели особенно опасны, так как в них обитают враждебные группы бродячих бесов.",
			"submission/impTunnels2Icon",
			PresetColour.BASE_PURPLE,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "в Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
				return getSVGOverride("submission/impTunnels2Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels2Icon", PresetColour.BASE_PURPLE);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();
	
	public static final AbstractPlaceType FORTRESS_DEMON_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Ворота",
			"Массивные каменные ворота служат единственным проходом между цитаделью и пещерой за ней.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpCitadelDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в цитадели Темной Сирены") {
		@Override
		public List<Population> getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.NUMEROUS,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public Darkness getDarkness() {
			if(ImpCitadelDialogue.isDefeated()) {
				return Darkness.ALWAYS_DARK;
			}
			return Darkness.ALWAYS_LIGHT;
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Внутренний двор",
			"Главная цитадель отделена от внешних укреплений просторным мощеным двором.",
			null,
			PresetColour.BASE_BLACK,
			ImpCitadelDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "в цитадели Темной Сирены") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_WELL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Колодец",
			"Безграничный источник пресной воды, подобный этому колодцу, бесценен для защитников любой крепости.",
			"submission/impFortress/well",
			PresetColour.BASE_BLUE_LIGHT,
			ImpCitadelDialogue.WELL,
			Darkness.ALWAYS_LIGHT,
			null, "в цитадели Темной Сирены") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_ENTRANCE.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Цитадель",
			"Главная цитадель была высечена прямо в монолитной скале одной из стен пещеры.",
			"submission/impFortress/keep",
			PresetColour.BASE_PURPLE,
			ImpCitadelDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "в цитадели Темной Сирены") {
		@Override
		public boolean isDangerous() {
			return Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_CELLS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Камеры",
			"Вырубленные в стенах пещеры, эти камеры служат местом для содержания узников.",
			"submission/impFortress/cells",
			PresetColour.BASE_TEAL,
			ImpCitadelDialogue.CELLS,
			Darkness.ALWAYS_LIGHT,
			null, "в цитадели Темной Сирены") {
		@Override
		public List<Population> getPopulation() {
			if(ImpCitadelDialogue.isImpsDefeated() || ImpCitadelDialogue.isDefeated()) {
				return new ArrayList<>();
			}
			return Util.newArrayListOfValues(new Population(true, PopulationType.GUARD, PopulationDensity.FEW,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.IMP_ALPHA, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.IMP, SubspeciesSpawnRarity.TEN))));
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_LAB = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Лаборатория",
			"С одной стороны внутреннего двора возведено массивное каменное строение, предназначенное исключительно для использования в качестве специализированной лаборатории.",
			"submission/impFortress/laboratory",
			PresetColour.BASE_GREEN_LIME,
			ImpCitadelDialogue.LABORATORY,
			Darkness.ALWAYS_LIGHT,
			null, "в цитадели Темной Сирены") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_DEMON_TREASURY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Сокровищница",
			"Каждому правителю цитадели нужно место, где он сможет в безопасности хранить свои драгоценности.",
			"submission/impFortress/treasury",
			PresetColour.BASE_GOLD,
			ImpCitadelDialogue.TREASURY,
			Darkness.ALWAYS_LIGHT,
			null, "в цитадели Темной Сирены") {
		@Override
		public List<Population> getPopulation() {
			return FORTRESS_DEMON_CELLS.getPopulation();
		}
		@Override
		public Darkness getDarkness() {
			return FORTRESS_DEMON_ENTRANCE.getDarkness();
		}
	}.initWeatherImmune();
	
	
	
	// Female seducer imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_FEMALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Крепость бесов",
			"Грубая обнесенная стенами крепость, возведенная на возвышающемся скалистом холме, расположена посреди огромной подземной пещеры.",
			"submission/impFortress3",
			PresetColour.BASE_PINK,
			SubmissionGenericPlaces.IMP_FORTRESS_FEMALES,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getNpc(FortressFemalesLeader.class).getWorldLocation()!=WorldType.IMP_FORTRESS_FEMALES) {
				return getSVGOverride("submission/impFortress3", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress3", PresetColour.BASE_PINK);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_FEMALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Туннели бесов",
			"Эти туннели особенно опасны, так как в них обитают враждебные группы бродячих бесов.",
			"submission/impTunnels3Icon",
			PresetColour.BASE_PINK_LIGHT,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "в Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated)) {
				return getSVGOverride("submission/impTunnels3Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels3Icon", PresetColour.BASE_PINK_LIGHT);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_FEMALES_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Ворота",
			"Главные ворота крепости ведут обратно в Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в Крепости Самок Бесов"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_FEMALES_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Внутренний двор",
			"Между воротами и деревянной цитаделью крепости раскинулся лишь пустынный, запущенный внутренний двор.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "в Крепости Самок Бесов"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_FEMALES_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Цитадель",
			"Грубо построенная цитадель служит резиденцией правителя этой конкретной крепости.",
			"submission/impFortress/keep",
			PresetColour.BASE_PINK,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "в Крепости Самок Бесов"
			).initDangerous()
			.initWeatherImmune();

	
	
	// Incubus imp fortress:
	
	public static final AbstractPlaceType SUBMISSION_IMP_FORTRESS_MALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Крепость бесов",
			"Окруженная стенами крепость, возведенная на возвышающемся скалистом холме, расположена посреди огромной подземной пещеры.",
			"submission/impFortress4",
			PresetColour.BASE_BLUE,
			SubmissionGenericPlaces.IMP_FORTRESS_MALES,
			Darkness.ALWAYS_LIGHT,
			null, "в Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getNpc(FortressMalesLeader.class).getWorldLocation()!=WorldType.IMP_FORTRESS_MALES) {
				return getSVGOverride("submission/impFortress4", PresetColour.BASE_GREEN_LIGHT);
			}
			return getSVGOverride("submission/impFortress4", PresetColour.BASE_BLUE);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SUBMISSION_IMP_TUNNELS_MALES = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Туннели бесов",
			"Эти туннели особенно опасны, так как в них обитают враждебные группы бродячих бесов.",
			"submission/impTunnels4Icon",
			PresetColour.BASE_BLUE_LIGHT,
			SubmissionGenericPlaces.TUNNEL,
			Darkness.ALWAYS_DARK, Encounter.SUBMISSION_TUNNELS, "в Submission") {
		@Override
		public String getSVGString(Set<AbstractPlaceUpgrade> upgrades) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated)) {
				return getSVGOverride("submission/impTunnels4Icon", PresetColour.BASE_GREY);
			}
			return getSVGOverride("submission/impTunnels4Icon", PresetColour.BASE_BLUE_LIGHT);
		}
	}.initDangerous()
	.initWeatherImmune()
	.initSexNotBlockedFromCharacterPresent();

	public static final AbstractPlaceType FORTRESS_MALES_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Ворота",
			"Главные ворота крепости ведут обратно в Submission.",
			"submission/impFortress/entrance",
			PresetColour.BASE_RED,
			ImpFortressDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в Крепости Мужских Бесов"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_MALES_COURTYARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Внутренний двор",
			"Между главными воротами и деревянным донжоном крепости раскинулся пустынный внутренний двор, уставленный многочисленными мишенями для стрельбы из лука и соломенными чучелами.",
			null,
			PresetColour.BASE_BLACK,
			ImpFortressDialogue.COURTYARD,
			Darkness.ALWAYS_LIGHT,
			null, "в Крепости Мужских Бесов"
			).initWeatherImmune();
	
	public static final AbstractPlaceType FORTRESS_MALES_KEEP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Цитадель",
			"Донжон этой бесовской крепости представляет собой одноэтажное здание, выполненное в ярко выраженном японском стиле.",
			"submission/impFortress/keep",
			PresetColour.BASE_BLUE,
			ImpFortressDialogue.KEEP,
			Darkness.ALWAYS_LIGHT,
			null, "в Крепости Мужских Бесов"
			).initDangerous()
			.initWeatherImmune();
	
	
	
	
	// Lyssieth's palace:
	
	public static final AbstractPlaceType LYSSIETH_PALACE_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Коридор",
			"Обширные коридоры дворца Lyssieth столь же вызывающе роскошны, сколь и подобает резиденции прямой дочери самой Lilith.",
			null,
			PresetColour.BASE_GREY,
			LyssiethPalaceDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth") {
		@Override
		public List<Population> getPopulation() {
			return Util.newArrayListOfValues(new Population(true, PopulationType.MAID, PopulationDensity.COUPLE,
					Util.newHashMapOfValues(
							new Value<>(Subspecies.HUMAN, SubspeciesSpawnRarity.THREE),
							new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_WINDOWS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Окна",
			"Коридоры, отходящие влево и вправо от главного вестибюля, уставлены рядом узких окон, из которых открывается вид на пещеру.",
			null,
			PresetColour.BASE_GREY_DARK,
			LyssiethPalaceDialogue.WINDOWS,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initMapBackgroundColour(PresetColour.MAP_BACKGROUND_DARK)
	.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Вход",
			"Вестибюль дворца Lyssieth ослепляет своей роскошью, разительно контрастируя с мрачным и невзрачным экстерьером.",
			"submission/lyssiethsPalace/entrance",
			PresetColour.BASE_RED,
			LyssiethPalaceDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_ROOM = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Комната",
			"В этих гостиных можно найти мягкие диваны, столы с изысканной резьбой и даже редкие пианино.",
			"submission/lyssiethsPalace/lounge",
			PresetColour.BASE_PINK,
			LyssiethPalaceDialogue.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth"
			) {
		@Override
		public DialogueNode getBaseDialogue(Cell cell) {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Elizabeth.class))) {
				return DialogueManager.getDialogueFromId("acexp_submission_palace_elizabeth");
			}
			return LyssiethPalaceDialogue.ROOM;
		}
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_HALL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Холл",
			"В каждом крыле дворца расположен длинный, богато обставленный обеденный зал, который Lyssieth использует для приема своих самых важных гостей.",
			"submission/lyssiethsPalace/hall",
			PresetColour.BASE_ORANGE_LIGHT,
			LyssiethPalaceDialogue.HALL,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth"
			) {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Кабинет Lyssieth",
			"Lyssieth почти всегда можно застать в своем роскошном кабинете, где она увлеченно отыгрывает роль важной деловой особы.",
			"submission/lyssiethsPalace/office",
			PresetColour.BASE_GOLD,
			LyssiethPalaceDialogue.LYSSIETH_OFFICE_ENTER,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth"
			).initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_SIREN_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Кабинет Meraxis",
			"Комната, через которую нужно пройти, чтобы увидеть Lyssieth, превращена в некое подобие кабинета и приемной, где работает не кто иная, как её дочь, Meraxis.",
			"submission/lyssiethsPalace/officeSiren",
			PresetColour.BASE_CRIMSON,
			LyssiethPalaceDialogue.SIREN_OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth") {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getNpc(DarkSiren.class).getHomeWorldLocation()!=WorldType.LYSSIETH_PALACE) {
				return Util.newArrayListOfValues(new Population(false, PopulationType.RECEPTIONIST, PopulationDensity.ONE,
						Util.newHashMapOfValues(
								new Value<>(Subspecies.HALF_DEMON, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_STAIRS_1 = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Лестница",
			"Эти лестницы ведут на комнаты первого этажа, где расположены личные спальни Lyssieth и её персонала.",
			"submission/lyssiethsPalace/staircase",
			PresetColour.BASE_GREEN,
			LyssiethPalaceDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType LYSSIETH_PALACE_STAIRS_2 = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Лестница",
			"Эти лестницы ведут на комнаты первого этажа, где расположены личные спальни Lyssieth и её персонала.",
			"submission/lyssiethsPalace/staircase",
			PresetColour.BASE_GREEN,
			LyssiethPalaceDialogue.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "во Дворце Lyssieth") {
		@Override
		public List<Population> getPopulation() {
			return LYSSIETH_PALACE_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	
	
	// Bat caverns:

	public static final AbstractPlaceType BAT_CAVERN_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Винтовая лестница",
			"Эта длинная, извилистая лестница высечена в цельной скале и ведет обратно к основным проходам Submission.",
			"submission/batCaverns/cavernStaircase",
			PresetColour.BASE_GREEN,
			BatCaverns.STAIRCASE,
			Darkness.ALWAYS_LIGHT,
			null, "в Пещерах Летучих Мышей"
			).initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_DARK = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Темная пещера",
			"Гнетущую, чернильную черноту пещер летучих мышей сдерживает мягко светящийся мох, покрывающий весь пол.",
			null,
			PresetColour.BASE_GREY,
			BatCaverns.CAVERN_DARK,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"в Пещерах Летучих Мышей"
			).initDangerous()
			.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_LIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Биолюминесцентная пещера",
			"Ковер из мха в этой части пещеры процветает среди биолюминесцентных грибов всех форм, размеров и цветов, образуя извилистые тропы, петляющие по округе.",
			"submission/batCaverns/cavernBioluminescent",
			PresetColour.BASE_AQUA,
			BatCaverns.CAVERN_LIGHT,
			Darkness.ALWAYS_LIGHT, Encounter.BAT_CAVERN, "в Пещерах Летучих Мышей"
			) {
		@Override
		public List<Population> getPopulation() {
			if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Elle.class))) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(
						new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN),
						new Value<>(Subspecies.ALLIGATOR_MORPH, SubspeciesSpawnRarity.TEN),
						new Value<>(Subspecies.DOG_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return super.getPopulation();
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Подземная река",
			"Медленно текущая подземная река прокладывает себе путь сквозь пещеры летучих мышей; ее прохладные темные глубины оказываются непроницаемыми для того малого количества света, что излучают биолюминесцентные лишайники.",
			"submission/batCaverns/cavernRiver",
			PresetColour.BASE_BLUE,
			BatCaverns.RIVER,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "в Пещерах Летучих Мышей"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER_CROSSING = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Грибной мост",
			"Пара биолюминесцентных грибов размером с дерево переплелись в горизонтальный узор, образовав живой мост над темными глубинами реки.",
			"submission/batCaverns/cavernBridge",
			PresetColour.BASE_TEAL,
			BatCaverns.RIVER_BRIDGE,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "в Пещерах Летучих Мышей"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_RIVER_END = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Конец подземной реки",
			"Там, где заканчивается прибрежная тропа, река обрывается в кромешную бездну. Чтобы уберечь путников от падения в бездонную пропасть, была установлена металлическая решетка с мелкой сеткой.",
			"submission/batCaverns/cavernRiverEnd",
			PresetColour.BASE_BLUE_DARK,
			BatCaverns.RIVER_END,
			Darkness.ALWAYS_DARK, Encounter.BAT_CAVERN, "в Пещерах Летучих Мышей"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);
	
	public static final AbstractPlaceType BAT_CAVERN_SLIME_QUEEN_LAIR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Озеро Slime",
			"Гигантское подземное озеро освещается лесами биолюминесцентных грибов, растущих вдоль его берегов. Посреди неподвижных черных вод лежит маленький остров, покрытый грибами.",
			"submission/batCaverns/cavernLake",
			PresetColour.BASE_PINK_LIGHT,
			BatCaverns.SLIME_LAKE,
			Darkness.ALWAYS_LIGHT,
			Encounter.BAT_CAVERN,
			"возле озера Slime"
			).initDangerous()
			.initWeatherImmune()
			.initAquatic(Aquatic.MIXED);

	public static final AbstractPlaceType BAT_CAVERN_SHAFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Шахта к Dominion",
			"Большая извилистая шахта, вырубленная в потолке, обеспечивает прямую связь между Летучими пещерами и Dominion.",
			"submission/batCaverns/cavernShaft",
			PresetColour.BASE_GREEN,
			BatCaverns.SHAFT,
			Darkness.DAYLIGHT,
			null,
			"в Пещерах Летучих Мышей"
			).initWeatherImmune();
	
	// HLF Quest places:
	
	public static final AbstractPlaceType BAT_CAVERNS_REBEL_BASE_ENTRANCE_EXTERIOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Скрытый вход в пещеру",
			"Вход в таинственную искусственную пещеру, прежде скрытый за плотно прилегающей каменной дверью.",
			"submission/rebelBase/entrance",
			PresetColour.BASE_RED,
			BatCaverns.REBEL_BASE_ENTRANCE_EXTERIOR,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"возле входа в таинственную искусственную пещеру"
			).initDangerous()
			.initWeatherImmune();
        
       public static final AbstractPlaceType BAT_CAVERNS_REBEL_BASE_ENTRANCE_HANDLE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Странная рукоять",
			"Из скалы торчит странная рукоять.",
			"submission/rebelBase/entrance",
			PresetColour.BASE_GREY,
			BatCaverns.REBEL_BASE_ENTRANCE_HANDLE,
			Darkness.ALWAYS_DARK,
			Encounter.BAT_CAVERN,
			"в Пещерах Летучих Мышей"
			).initDangerous()
			.initWeatherImmune();
	
	// Slime queen's island tower:

	public static final AbstractPlaceType SLIME_QUEENS_LAIR_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Коридор",
			"По центру коридора тянется толстый бордово-золотой ковер, а каменные стены по обе стороны увешаны тяжелыми ткаными гобеленами.",
			null,
			PresetColour.BASE_GREY,
			SlimeQueensLair.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Прихожая",
			"Тяжелая дубовая дверь с железными решетками служит единственным выходом и входом в эту башню.",
			"submission/slimeQueensLair/entranceHall",
			PresetColour.BASE_RED,
			SlimeQueensLair.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STAIRS_UP = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Винтовая лестница",
			"Узкая винтовая лестница ведет на первый этаж башни.",
			"submission/slimeQueensLair/staircase",
			PresetColour.BASE_GREEN,
			SlimeQueensLair.STAIRCASE_UP,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STAIRS_DOWN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Винтовая лестница",
			"Узкая винтовая лестница ведет на первый этаж башни.",
			"submission/slimeQueensLair/staircase",
			PresetColour.BASE_RED,
			SlimeQueensLair.STAIRCASE_DOWN,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ROOM = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Спальня",
			"Место отдыха одного или нескольких стражников башни; в этой спальне стоит аккуратно заправленная кровать с балдахином, а также привычная спальная мебель.",
			"submission/slimeQueensLair/room",
			PresetColour.BASE_BLUE_LIGHT,
			SlimeQueensLair.ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime"
			).initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_STORAGE_VATS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Дистиллерия",
			"Огромный дистилляционный аппарат, расположенный в этой комнате, является источником не только всех трансформирующих жидкостей из слайма в Submission, но и популярного напитка «Slime Quencher».",
			"submission/slimeQueensLair/storageVats",
			PresetColour.BASE_ORANGE,
			SlimeQueensLair.STORAGE_VATS,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime") {
		@Override
		public void applyInventoryInit(CharacterInventory inventory) {
			for(int i=0; i<15; i++) {
				inventory.addItem(Main.game.getItemGen().generateItem("innoxia_race_slime_slime_quencher"));
			}
			for(int i=0; i<5; i++) {
				inventory.addItem(Main.game.getItemGen().generateItem("innoxia_race_slime_biojuice_canister"));
			}
		}
	}.initItemsPersistInTile()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ENTRANCE_GUARDS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Пост стражи",
			"В этой зоне расположена деревянная баррикада высотой по грудь, возведенная от стены до стены.",
			"submission/slimeQueensLair/guards",
			PresetColour.BASE_RED,
			SlimeQueensLair.GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime"
			) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeGuardsDefeated) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_ROYAL_GUARD = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Пост королевской стражи",
			"Этот участок коридора охраняет могучий инкуб-слайм пурпурного оттенка.",
			"submission/slimeQueensLair/royalGuards",
			PresetColour.BASE_PURPLE,
			SlimeQueensLair.ROYAL_GUARD_POST,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime"
			) {
		@Override
		public boolean isDangerous() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slimeRoyalGuardDefeated) && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.SIDE_SLIME_QUEEN, Quest.SLIME_QUEEN_FOUR);
		}
	}.initDangerous()
	.initWeatherImmune();
	
	public static final AbstractPlaceType SLIME_QUEENS_LAIR_SLIME_QUEEN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Спальня",
			"В спальне королевы слаймов стоит колоссальная кровать с балдахином, а также огромная ванна, наполненная внушительным количеством полупрозрачной розовой жидкости.",
			"submission/slimeQueensLair/bedChamber",
			PresetColour.BASE_PINK,
			SlimeQueensLair.BED_CHAMBER,
			Darkness.ALWAYS_LIGHT,
			null, "в башне Королевы Slime"
			).initWeatherImmune();
	
	
	
	
	// Gambling Den:
	
	public static final AbstractPlaceType GAMBLING_DEN_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Игорный притон",
			"Стены просторного атриума Игорного притона уставлены бесчисленными игровыми автоматами, а в центре расставлено множество столов и стульев.",
			null,
			PresetColour.BASE_BLACK,
			GamblingDenDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Игорном притоне") {
		@Override
		public List<Population> getPopulation() {
			Map<AbstractSubspecies, SubspeciesSpawnRarity> popComponent = new HashMap<>(Subspecies.getWorldSpecies(WorldType.SUBMISSION, this, false));
			Subspecies.getWorldSpecies(WorldType.DOMINION, this, false).forEach((key, value) -> popComponent.merge(key, value, (v1, v2) -> v1));
			popComponent.remove(Subspecies.IMP);
			popComponent.remove(Subspecies.IMP_ALPHA);
			return Util.newArrayListOfValues(new Population(true, PopulationType.CROWD, PopulationDensity.SPARSE, popComponent));
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GAMBLING_DEN_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Вход",
			"Деревянные двери главного входа в Игорный притон всегда приоткрыты, обеспечивая легкий доступ многочисленным посетителям заведения.",
			"submission/gamblingDen/entrance",
			PresetColour.BASE_GREEN,
			GamblingDenDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в Игорном притоне") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType GAMBLING_DEN_OFFICE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Кабинет Акселя",
			"Кабинет Акселя расположен рядом с главным входом и запирается, когда он не используется.",
			"submission/gamblingDen/office",
			PresetColour.BASE_ORANGE,
			GamblingDenDialogue.OFFICE,
			Darkness.ALWAYS_LIGHT,
			null, "в Игорном притоне") {
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_TRADER = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Коробочка Рокси",
			"'Коробочка Рокси' — это довольно дорогой ломбард, предлагающий товары, которые можно найти по гораздо более низким ценам в Dominion.",
			"submission/gamblingDen/trader",
			PresetColour.BASE_TEAL,
			RoxysShop.TRADER_EXTERIOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Игорном притоне"
			).initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_GAMBLING = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Столы для костей",
			"Игра в кости — одно из главных развлечений Игорного притона, и многочисленные столы, выделенные для этой игры, почти всегда полностью заняты.",
			"submission/gamblingDen/gambling",
			PresetColour.BASE_GOLD,
			GamblingDenDialogue.GAMBLING,
			Darkness.ALWAYS_LIGHT,
			null, "в Игорном притоне") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_PREGNANCY_ROULETTE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Беременная рулетка",
			"В «беременную рулетку» играет девочка-лошадь Эпона, стоя за длинной деревянной стойкой, встроенной в стену.",
			"submission/gamblingDen/referee",
			PresetColour.BASE_PINK,
			PregnancyRoulette.PREGNANCY_ROULETTE,
			Darkness.ALWAYS_LIGHT,
			null, "в Игорном притоне") {
		@Override
		public List<Population> getPopulation() {
			return GAMBLING_DEN_CORRIDOR.getPopulation();
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_PREGNANCY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Загоны для разведения",
			"Именно здесь добровольные игроки-мужчины продолжают свою игру в «беременную рулетку».",
			"submission/gamblingDen/normalPregnancy",
			PresetColour.BASE_BLUE_LIGHT,
			GamblingDenDialogue.PREGNANCY_ROULETTE_MALE_STALLS,
			Darkness.ALWAYS_LIGHT,
			null, "в Игорном притоне"
			).initWeatherImmune();
	
	public static final AbstractPlaceType GAMBLING_DEN_FUTA_PREGNANCY = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Загоны для фута-разведения",
			"Именно здесь добровольные игроки-футанарри продолжают свою игру в «беременную рулетку».",
			"submission/gamblingDen/futaPregnancy",
			PresetColour.BASE_PINK_LIGHT,
			GamblingDenDialogue.PREGNANCY_ROULETTE_FUTA_STALLS,
			Darkness.ALWAYS_LIGHT,
			null, "в Игорном притоне"
			).initWeatherImmune();
	
	
	
	
	// Rat warrens:

	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Извилистые проходы",
			"Извилистые проходы Крысиных Варренов сильно различаются как по ширине, так и по качеству постройки.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CHECKPOINT_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Извилистые проходы",
			"Извилистые проходы Крысиных Варренов сильно различаются как по ширине, так и по качеству постройки.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Извилистые проходы",
			"Извилистые проходы Крысиных Варренов сильно различаются как по ширине, так и по качеству постройки.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CORRIDOR_RIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Извилистые проходы",
			"Извилистые проходы Крысиных Варренов сильно различаются как по ширине, так и по качеству постройки.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				return VengarCaptiveDialogue.CORRIDOR;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_CHECKPOINT_RIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Извилистые проходы",
			"Извилистые проходы Крысиных Варренов сильно различаются как по ширине, так и по качеству постройки.",
			null,
			PresetColour.BASE_BLACK,
			RatWarrensDialogue.CORRIDOR,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight));
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_ENTRANCE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Вход",
			"Вход в Крысиные Лабиринты всегда охраняют как минимум двое членов банды.",
			"submission/ratWarrens/entrance",
			PresetColour.BASE_GREEN,
			RatWarrensDialogue.ENTRANCE,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_DORMITORY_LEFT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Спальня",
			"Вдоль стен этой сырой и мрачной комнаты стоят двухъярусные кровати, а в центре разбросаны несколько столов и стульев.",
			"submission/ratWarrens/dormitory",
			PresetColour.BASE_BROWN,
			RatWarrensDialogue.DORMITORY,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();
	
	public static final AbstractPlaceType RAT_WARRENS_DORMITORY_RIGHT = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Спальня",
			"Вдоль стен этой сырой и мрачной комнаты стоят двухъярусные кровати, а в центре разбросаны несколько столов и стульев.",
			"submission/ratWarrens/dormitory",
			PresetColour.BASE_BROWN,
			RatWarrensDialogue.DORMITORY,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.FEW, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_DICE_DEN = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Логово Костей",
			"Место, куда члены банды приходят выпить и попыграть в кости.",
			"submission/ratWarrens/diceDen",
			PresetColour.BASE_COPPER,
			RatWarrensDialogue.DICE_DEN,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.isExtendedWorkTime()) {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.SEVERAL, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				} else {
					return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.COUPLE, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
				}
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_MILKING_ROOM = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Доильный зал",
			"Это конечный пункт назначения для несчастных людей, похищенных бандой Венгара.",
			"submission/ratWarrens/stocks",
			PresetColour.BASE_MAGENTA,
			RatWarrensDialogue.MILKING_ROOM,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = RatWarrensCaptiveDialogue.CAPTIVE_NIGHT;
			} else {
				dialogue = RatWarrensDialogue.MILKING_ROOM;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_MILKING_STORAGE = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Запас молока",
			"В этой зоне хранится огромное количество металлических бидонов для молока; на каждом из них отмечен тип какой-либо телесной жидкости и её вкус.",
			"submission/ratWarrens/milkingRoom",
			PresetColour.BASE_YELLOW_LIGHT,
			RatWarrensDialogue.MILKING_STORAGE,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_VENGARS_HALL = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Зал Венгара",
			"Огромный каменный зал, заполненный множеством длинных деревянных скамей, с возвышающимся троном в дальнем конце.",
			"submission/ratWarrens/vengarsHall",
			PresetColour.BASE_PURPLE,
			RatWarrensDialogue.VENGARS_HALL,
			Darkness.ALWAYS_LIGHT,
			null, "в Крысиных Лабиринтах") {
		@Override
		public AbstractEncounter getEncounterType() {
			if(Main.game.getPlayer().isCaptive()) {
				return Encounter.VENGAR_CAPTIVE_HALL;
			}
			return null;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = VengarCaptiveDialogue.VENGARS_HALL;
			} else {
				dialogue = RatWarrensDialogue.VENGARS_HALL;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public List<Population> getPopulation() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				return Util.newArrayListOfValues(new Population(true, PopulationType.GANG_MEMBER, PopulationDensity.NUMEROUS, Util.newHashMapOfValues(new Value<>(Subspecies.RAT_MORPH, SubspeciesSpawnRarity.TEN))));
			}
			return new ArrayList<>();
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
	}.initWeatherImmune();

	public static final AbstractPlaceType RAT_WARRENS_PRIVATE_BEDCHAMBERS = new AbstractPlaceType(
			WorldRegion.SUBMISSION,
			"Личные спальни",
			"Личные спальни, используемые Венгаром и его телохранителями, расположены рядом с главным залом.",
			"submission/ratWarrens/bedroom",
			PresetColour.BASE_PURPLE_LIGHT,
			RatWarrensDialogue.VENGARS_BEDROOM,
			Darkness.ALWAYS_LIGHT,
			null,
			"в Крысиных Лабиринтах") {
		@Override
		public AbstractEncounter getEncounterType() {
			if(Main.game.getPlayer().isCaptive()) {
				return Encounter.VENGAR_CAPTIVE_BEDROOM;
			}
			return null;
		}
		@Override
		public DialogueNode getDialogue(Cell c, boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getPlayer().isCaptive()) {
				dialogue = VengarCaptiveDialogue.VENGARS_BEDROOM;
			} else {
				dialogue = RatWarrensDialogue.VENGARS_BEDROOM;
			}
			return super.getDialogue(c, withRandomEncounter, forceEncounter);
		}
		@Override
		public boolean isDangerous() {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile);
		}
	}.initWeatherImmune();

	// HLF Quest places:
	
    public static final AbstractPlaceType REBEL_BASE_ENTRANCE = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Вход",
			"Единственный путь в пещеру и из неё искусно скрыт за плотно прилегающей каменной дверью.",
			"submission/rebelBase/entrance",
			PresetColour.BASE_RED,
			RebelBase.REBEL_BASE_ENTRANCE,
			Darkness.ALWAYS_DARK,
			null,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_CORRIDOR = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Коридор",
			"Искусственная пещера, укрепленная сомнительными деревянными опорами.",
			null,
			PresetColour.BASE_BLACK,
			RebelBase.REBEL_BASE_CORRIDOR,
			Darkness.ALWAYS_DARK,
			null,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_SLEEPING_AREA = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Заброшенная спальня",
			"Давно заброшенная комната, полная старых заброшенных кроватей.",
			"submission/rebelBase/cache1",
			PresetColour.BASE_BLUE,
			RebelBase.REBEL_BASE_SLEEPING_AREA,
			Darkness.ALWAYS_DARK,
			Encounter.REBEL_BASE,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_SLEEPING_AREA_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Заброшенная спальня",
			"Давно заброшенная комната, полная старых заброшенных кроватей.",
			"submission/rebelBase/cache1",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_SLEEPING_AREA_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_COMMON_AREA = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Заброшенная общая зона",
			"Скудно обставленные руины общей зоны.",
			"submission/rebelBase/cache2",
			PresetColour.BASE_ORANGE,
			RebelBase.REBEL_BASE_COMMON_AREA,
			Darkness.ALWAYS_DARK,
			null,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_COMMON_AREA_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Заброшенная общая зона",
			"Скудно обставленные руины общей зоны.",
			"submission/rebelBase/cache2",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_COMMON_AREA_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();

    public static final AbstractPlaceType REBEL_BASE_ARMORY = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Частично обрушенная комната",
			"Комната, частично заваленная обломками.",
			"submission/rebelBase/cache3",
			PresetColour.BASE_GREEN,
			RebelBase.REBEL_BASE_ARMORY,
			Darkness.ALWAYS_DARK,
			null,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();
    
    public static final AbstractPlaceType REBEL_BASE_ARMORY_SEARCHED = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Частично обрушенная комната",
			"Комната, частично заваленная обломками.",
			"submission/rebelBase/cache3",
			PresetColour.BASE_GREY,
			RebelBase.REBEL_BASE_ARMORY_SEARCHED,
			Darkness.ALWAYS_DARK,
			null,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();
    
     public static final AbstractPlaceType REBEL_BASE_CAVED_IN_ROOM = new AbstractPlaceType(
 			WorldRegion.SUBMISSION,
			"Сильно обрушенная комната",
			"Комната, где нет ничего, кроме обломков.",
			"submission/rebelBase/cavein",
			PresetColour.BASE_GREY_DARK,
			RebelBase.REBEL_BASE_CAVED_IN_ROOM,
			Darkness.ALWAYS_DARK,
			null,
			"в таинственной искусственной пещере")
	            .initWeatherImmune();
	
	
	
	// World map tiles:

	public static final AbstractGlobalPlaceType WORLD_MAP_THICK_JUNGLE = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE,
			"густые джунгли",
			null,
			"Чем дальше в джунгли заходит путник, тем гуще становится растительность, позволяя особенно диким и опасным хищникам скрываться в зарослях...",
			new Colour(Util.newColour(0x6b8f7e)), null, null, "в джунглях") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_JUNGLE = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE,
			"джунгли",
			null,
			"В редких тропических зарослях обитает множество различных звероморфов, и далеко не все они дружелюбны.",
			new Colour(Util.newColour(0x8fbfa8)), null, null, "в джунглях") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_JUNGLE_CITY = new AbstractGlobalPlaceType(
			WorldRegion.JUNGLE_CITY,
			"Ица'ак",
			null,
			"Ица'ак — раскинувшийся город в майянском стиле, последний оплот цивилизации перед бескрайними дикими джунглями севера.",
			new Colour(Util.newColour(0xb377b0)), null, null, "снаружи Ица'ака") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_FOOTHILLS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"предгорья",
			null,
			"Постепенный подъем ведет к пологим холмам у подножия Лунных гор.",
			PresetColour.BASE_BLACK, null, null, "в предгорьях Лунных гор") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_MOUNTAINS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"горы",
			null,
			"Горная цепь на самом западе известна как «Лунные горы», и она является домом для множества альпийских звероморфов.",
			PresetColour.BASE_GREY_DARK, null, null, "в Лунных горах") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SNOWY_MOUNTAINS = new AbstractGlobalPlaceType(
			WorldRegion.MOUNTAINS,
			"горные пики",
			null,
			"Высшие пики Лунных гор покрыты вечными снегами, и здесь обитают несколько диких и агрессивных рас...",
			PresetColour.BASE_GREY_LIGHT, null, null, "в Лунных горах") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_SNOWY_VALLEY = new AbstractGlobalPlaceType(
			WorldRegion.SNOW,
			"снежнобуранная долина",
			null,
			"В этой защищенной долине регулярно выпадают обильные снегопады, и здесь обитает множество арктических рас.",
			new Colour(Util.newColour(0xeeeeee)), null, null, "в снежнобуранной долине") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_GLACIAL_LAKE = new AbstractGlobalPlaceType(
			WorldRegion.SNOW,
			"озеро селки",
			null,
			"В западной части снежнобуранной долины можно найти огромное, частично замерзшее озеро.",
			new Colour(Util.newColour(0xbbf0f1)), null, null, "у озера Селки") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_DOMINION = new AbstractGlobalPlaceType(
			WorldRegion.DOMINION,
			"Пригороды Dominion",
			"Столица королевства Lilith, Dominion — это оплот власти королевы суккубов.",
			"global/dominion",
			PresetColour.BASE_PURPLE,
			new Colour(Util.newColour(0x826B85)),
			FieldsDialogue.DOMINION_EXTERIOR,
			null, "на окраинах Dominion") {
		@Override
		protected DialogueNode getBaseDialogue(Cell cell) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.leftDominionFirstTime)) {
				return FieldsDialogue.DOMINION_EXTERIOR;
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_fields_leaving_dominion_start");
			}
		}
		@Override
		public AbstractWorldType getGlobalLinkedWorldType() {
			return WorldType.DOMINION;
		}
		@Override
		public List<Population> getPopulation() {
			List<Population> pop = new ArrayList<>();
			
			if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.COUPLE, Subspecies.getDominionStormImmuneSpecies(true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getDominionStormImmuneSpecies(true, Subspecies.HUMAN)));
			} else {
				pop.add(new Population(true, PopulationType.PERSON, PopulationDensity.SEVERAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true)));
				pop.add(new Population(false, PopulationType.ENFORCER, PopulationDensity.OCCASIONAL, Subspecies.getWorldSpecies(WorldType.DOMINION, this, true, Subspecies.HUMAN)));
				pop.add(new Population(false, PopulationType.CENTAUR_CARTS, PopulationDensity.OCCASIONAL, Util.newHashMapOfValues(new Value<>(Subspecies.CENTAUR, SubspeciesSpawnRarity.TEN))));
			}
			
			return pop;
		}
	}.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_GRASSLANDS = new AbstractGlobalPlaceType(
			WorldRegion.FIELDS,
			"дикие луга",//"global/grassland",
			null,
			"Дикие луга служат домом для множества различных рас, подавляющее большинство из которых столь же дики и необузданны, как и земли, которые они населяют.",
			new Colour(Util.newColour(0x688255)),
			FieldsDialogue.GRASSLAND_WILDERNESS,
			null, "в диких лугах полей Фолой") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_FIELDS = new AbstractGlobalPlaceType(
			WorldRegion.FIELDS,
			"Поля Фолой",
			null,
			"Сельскохозяйственные угодья, окружающие Dominion, известны как «Поля Фолой», и в основном населены морфами сельскохозяйственных животных.",
			new Colour(Util.newColour(0xB9E3A1)),
			FieldsDialogue.FOLOI_FIELDS,
			null, "на полях Фолой") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_FOREST = new AbstractGlobalPlaceType(
			WorldRegion.WOODLAND,
			"лес",
			"Густые леса, окружающие поля Фолой, особенно опасны, так как в них обитают дикие хищные морфы волков, лисиц и медведей.",
			"global/forest",
			new Colour(Util.newColour(0x51A468)),
			new Colour(Util.newColour(0x5E685E)),
			FieldsDialogue.FOLOI_FOREST,
			null, "в лесистых частях полей Фолой") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_FIELDS_CITY = new AbstractGlobalPlaceType(
			WorldRegion.FIELD_CITY,
			"Элис",
			"Элис — крупнейшее и самое процветающее поселение на полях Фолой, служащее торговым узлом как для ёко, так и для рас, обитающих в горах.",
			"global/elis",
			new Colour(Util.newColour(0xd544ae)),
			new Colour(Util.newColour(0x859871)),
			FieldsDialogue.ELIS,
			null, "за пределами Элис") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};
	
	public static final AbstractGlobalPlaceType WORLD_MAP_RIVER = new AbstractGlobalPlaceType(
			WorldRegion.RIVER,
			"река Хубур",
			"Река Хубур течет с запада через Dominion и впадает в бескрайнее море. Те её участки, что граничат с полями Фолой, считаются безопасными.",
			"global/river",
			new Colour(Util.newColour(0x61BDFF)),
			new Colour(Util.newColour(0x98B4CD)),
			FieldsDialogue.RIVER_HUBUR,
			null, "у реки Хубур") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initAquatic(Aquatic.MIXED)
	.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_WILD_RIVER = new AbstractGlobalPlaceType(
			WorldRegion.RIVER,
			"река Хубур (дикая)",
			null,
			"Вдали от Dominion река Хубур — опасное место для купания, так как в ней обитает множество диких пресноводных рас.",
			new Colour(Util.newColour(0xc1f1ee)),
			DialogueManager.getDialogueFromId("innoxia_places_shinrin_highlands_global_river"),
			null,
			"у реки Хубур") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.MIXED);

	public static final AbstractGlobalPlaceType WORLD_MAP_YOUKO_FOREST = new AbstractGlobalPlaceType(
			WorldRegion.YOUKO_FOREST,
			"Нагорье Шинрин",
			null,
			"Нагорье Шинрин представляет собой цепь низких, покрытых лесами холмов, высота которых неуклонно растет по мере продвижения на запад. Здесь обитают неуловимые ёко, наложившие на эти земли заклинания, чтобы предотвратить любую телепортацию.",
			PresetColour.MAP_BACKGROUND_SHINRIN_HIGHLANDS,
			DialogueManager.getDialogueFromId("innoxia_places_shinrin_highlands_global_shinrin_highlands"),
			null,
			"в нагорье Шинрин") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
				@Override
				public TeleportPermissions getTeleportPermissions() {
					return TeleportPermissions.NONE;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_SEA = new AbstractGlobalPlaceType(
			WorldRegion.SEA,
			"бескрайнее море",
			null,
			"Водные расы, населяющие мир Lilith, не любят уходить далеко от берега, поэтому море кажется им бесконечным.",
			PresetColour.BASE_BLUE_DARK, null, null, "в бескрайнем море") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous()
	.initAquatic(Aquatic.WATER_SURFACE);
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SEA_CITY = new AbstractGlobalPlaceType(
			WorldRegion.SEA_CITY,
			"Лионесс",
			null,
			"Подводный город Лионесс расположен у восточного побережья, и, что неудивительно, его особенно трудно посетить неводным расам.",
			new Colour(Util.newColour(0x8264b0)), null, null, "за пределами Лионесса") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initAquatic(Aquatic.WATER_UNDER);

	public static final AbstractGlobalPlaceType WORLD_MAP_ARID_GRASSLAND = new AbstractGlobalPlaceType(
			WorldRegion.SAVANNAH,
			"засушливая степь",
			null,
			"К югу дикие степи начинают высыхать, становясь излюбленным домом для таких морфов, как львы, леопарды и зебры.",
			PresetColour.BASE_YELLOW_LIGHT, null, null, "в засушливых степях") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_ARID_SAVANNAH = new AbstractGlobalPlaceType(
			WorldRegion.SAVANNAH,
			"саванна",
			null,
			"По всей этой местности разбросаны редколесья с разреженным пологом деревьев, где обитают те же расы, что и в засушливых степях.",
			PresetColour.BASE_TAN, null, null, "в саванне") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();

	public static final AbstractGlobalPlaceType WORLD_MAP_DESERT = new AbstractGlobalPlaceType(
			WorldRegion.DESERT,
			"пустыня",
			null,
			"К югу от засушливых степей вся растительность исчезает, образуя жаркую бесплодную пустошь.",
			new Colour(Util.newColour(0xffe7a7)), null, null, "в пустыне") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_SAND_DUNES = new AbstractGlobalPlaceType(
			WorldRegion.DESERT,
			"песчаные дюны",
			null,
			"На южной окраине пустыни раскинулся огромный массив песчаных дюн, ставший домом для множества опасных рас.",
			new Colour(Util.newColour(0xffdb7a)), null, null, "в песчаных дюнах") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_DESERT_CITY = new AbstractGlobalPlaceType(
			WorldRegion.DESERT_CITY,
			"Тонкий",
			null,
			"Город, напоминающий Древний Египет; Тинис — самое южное поселение в королевстве Lilith, широко известное своим престижным университетом Арканы.",
			new Colour(Util.newColour(0xd5445e)), null, null, "снаружи Тиниса") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	};

	public static final AbstractGlobalPlaceType WORLD_MAP_VOLCANO = new AbstractGlobalPlaceType(
			WorldRegion.VOLCANO,
			"вулкан Дыхание дракона",
			null,
			"Огромный вулкан, из которого непрерывно извергается раскаленная лава. Несмотря на название, драконы здесь встречаются так же редко, как и во всем остальном королевстве Lilith.",
			PresetColour.BASE_ORANGE, null, null, "у вулкана Дыхание дракона") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	public static final AbstractGlobalPlaceType WORLD_MAP_LAVA_FLOWS = new AbstractGlobalPlaceType(
			WorldRegion.VOLCANO,
			"лавовые потоки",
			null,
			"Лава, изливающаяся из вулкана, медленно стекает в южном направлении.",
			PresetColour.BASE_BLACK, null, null, "у лавовых потоков") {
				@Override
				public AbstractWorldType getGlobalLinkedWorldType() {
					return null;
				}
	}.initDangerous();
	
	
	
	private static List<AbstractPlaceType> allPlaceTypes = new ArrayList<>();
	private static Map<AbstractPlaceType, String> placeToIdMap = new HashMap<>();
	private static Map<String, AbstractPlaceType> idToPlaceMap = new HashMap<>();

	public static List<AbstractPlaceType> getAllPlaceTypes() {
		return allPlaceTypes;
	}
	
	public static AbstractPlaceType getPlaceTypeFromId(String id) {
		id = id.replaceAll("ALEXA", "HELENA");
		id = id.replaceAll("SUPPLIER_DEPOT", "TEXTILE_WAREHOUSE");
		
		if(id.equals("ZARANIX_FF_BEDROOM")) {
			id = "ZARANIX_FF_OFFICE";
			
		} else if(id.equals("LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR";
			
		} else if(id.equals("LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_SLAVE")
				|| id.equals("LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR_MILKING")) {
			id = "LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR";
			
		} else if(id.equals("DOMINION_EXIT_TO_JUNGLE")) {
			id = "DOMINION_EXIT_EAST";
		} else if(id.equals("DOMINION_EXIT_TO_DESERT")) {
			id = "DOMINION_EXIT_SOUTH";
		} else if(id.equals("DOMINION_EXIT_TO_FIELDS")) {
			id = "DOMINION_EXIT_NORTH";
		} else if(id.equals("DOMINION_EXIT_TO_SEA")) {
			id = "DOMINION_EXIT_WEST";
			
		} else if(id.equals("SHOPPING_ARCADE_SUPPLIER_DEPOT")) {
			id = "SHOPPING_ARCADE_RESTAURANT";
			
		} else if(id.equals("innoxia_fields_elis_town_tavern_seedy")) {
			id = "innoxia_fields_elis_town_tavern_alley";
		}
		
		id = Util.getClosestStringMatch(id, idToPlaceMap.keySet());
		return idToPlaceMap.get(id);
	}

	public static String getIdFromPlaceType(AbstractPlaceType placeType) {
		return placeToIdMap.get(placeType);
	}
	
	static {
		// Modded place types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/maps", "placeTypes", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey().replace("_placeTypes", "");
					AbstractPlaceType placeType = new AbstractPlaceType(innerEntry.getValue(), entry.getKey(), id, true) {};
					allPlaceTypes.add(placeType);
					placeToIdMap.put(placeType, id);
					idToPlaceMap.put(id, placeType);
//					System.out.println("modded PT: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded place type failed at 'PlaceType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res place types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/maps", "placeTypes", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String id = innerEntry.getKey().replace("_placeTypes", "");
					AbstractPlaceType placeType = new AbstractPlaceType(innerEntry.getValue(), entry.getKey(), id, false) {};
					allPlaceTypes.add(placeType);
					placeToIdMap.put(placeType, id);
					idToPlaceMap.put(id, placeType);
//					System.out.println("res PT: "+innerEntry.getKey()+" | "+id);
				} catch(Exception ex) {
					System.err.println("Loading place type failed at 'PlaceType'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}

		// Hard-coded place types (all those up above):
		
		Field[] fields = PlaceType.class.getFields();
		
		for(Field f : fields) {
			if(AbstractPlaceType.class.isAssignableFrom(f.getType())) {
				AbstractPlaceType placeType;
				try {
					placeType = ((AbstractPlaceType) f.get(null));

					placeToIdMap.put(placeType, f.getName());
					idToPlaceMap.put(f.getName(), placeType);
					allPlaceTypes.add(placeType);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
