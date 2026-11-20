package com.lilithsthrone.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @since 0.1.0
 * @version 0.4.11
 * @author Innoxia
 */
public class Main extends Application {

	public static Game game;
	public static Sex sex;
	public static Combat combat;

	public static TransformerFactory transformerFactory = TransformerFactory.newInstance();
	private static DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder docBuilder;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	
	public static final String AUTHOR = "Инноксия";
	public static final String NAME_OF_GAME = "Трон Лилит"; // Sick of eclipse auto-correcting "Main.game.get..." to "Main.GAME_NAME.get..." so changed to NAME_OF_GAME in v0.4.11.4
	public static final String VERSION_NUMBER = "0.4.11.9"; // Remember to do the stuff below!
	/*
	 * BEFORE BUILDING:
	 * update pom.xml!
	 * use smaller images folder
	 * 
	 * launch4j include JVM options:
	 * -Dbuild.type=exe64 or -Dbuild.type=exe32 as appropriate
	 */
	public static final String VERSION_DESCRIPTION = "Альфа";

	public static boolean quickSaved = false;
	
	private static boolean displayingTurnTimer = false;
	
	/**
	 * To turn it on, just add -Ddebug=true to java's VM options. (You should be able to do this in Eclipse through Run::Run Configurations...::Arguments tab::VM Arguments).
	 * Help page: https://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.pde.doc.user%2Fguide%2Ftools%2Flaunchers%2Farguments.htm
	 *  Or, from the command line java -Ddebug=true -jar LilithsThrone.jar
	 */
	public final static boolean DEBUG = Boolean.valueOf(System.getProperty("debug", "false"));

	public static Image WINDOW_IMAGE;
	
	private static Properties properties;
	
	private static String patchNotes = "";
	
	public static String disclaimer = "<h6 style='text-align: center; color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>Чтобы играть в эту игру, вы должны прочитать и согласиться со следующим!</h6><p>Эта игра представляет собой <b>вымышленную</b> текстовую эротическую RPG. Весь контент, содержащийся в этой игре, является частью вымышленной вселенной, не связанной с реальными местами, людьми или событиями.<br/><br/> Все персонажи, появляющиеся в этой истории, являются вымышленными существами, которые дали свое согласие на появление и участие в этой истории. Если вы начнете беспокоиться за персонажей, пожалуйста, будьте уверены, что все они — согласные взрослые люди, произносящие реплики по сценарию. Ни один из персонажей, представленных в этой игре, не пострадал и не подвергается каким-либо вредным воздействиям во время создания или прохождения игры. Каждому персонажу в игре не менее 18 лет (или же он магическим образом достиг законного возраста, необходимого для появления в эротической литературе в любой стране, где вы играете). Ни один персонаж в этой игре не состоит в кровном родстве с другими; повторим еще раз, они просто произносят реплики по сценарию.<br/><br/>Соглашаясь с этим дисклеймером и играя в эту игру, вы соглашаетесь на столкновение с откровенным сексуальным и взрослым контентом, представленным как часть вымышленной вселенной игры. Такой контент включает в себя, помимо прочего: графические описания секса, межвидовой секс (с фантастическими существами), сексуальные трансформации, фантазии о изнасиловании/подразумеваемое отсутствие согласия, умеренное физическое насилие, сексуальное насилие и употребление наркотиков.<br/>Экстремальный фетиш-контент, такой как гуро/экстремальное насилие, скатология и несовершеннолетний/сомнительный возраст, <i>не</i> является частью этой игры.<br/><br/>Соглашаясь с этим дисклеймером и играя в эту игру, вы также подтверждаете, что вам <b>как минимум 18 лет</b>, или вы достигли как минимум возраста, позволяющего покупать и смотреть порнографические материалы в вашей стране, если этот возраст превышает 18 лет.<br/><br/>В заключение создатели этой игры хотят подчеркнуть, что представленный контент является полностью вымышленным и не отражает их личных взглядов или мнений. Эта игра была создана в духе художественной интерактивной литературы, и крайне важно, чтобы вы проводили четкую грань между реальностью и вымышленными событиями, изображенными в этой игре.</p>";
	
	public static List<CreditsSlot> credits = new ArrayList<>();

	// World generation:
	public static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {

		CheckForDataDirectory();
		CheckForResFolder();
		
		credits.add(new CreditsSlot("Аноним", "", 99, 99, 99, 99));
		
		
		credits.add(new CreditsSlot("Кайл С. П.", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Paradoxiso", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("luka_fateburn", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Коричная Суккуб", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Лука Фейтберн", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Фларкс", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Моро", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Нео", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Abaddon_TMZ", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Забытый_Один", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Вельвет", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("GentleTark", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("QW", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Мастер Исами", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Валейя", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Бабблээээй", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Крысиный Король", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("H3adShotB33otch", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Берсеркская Сталь", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Дейв Зиглер", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Каас", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Темный Мирос", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("DethEagle666", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Мистический Экзарх", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Люцифер", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("A(ух)CE", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("BL4Z3ST0RM", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("~Чай~", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Похоть пугала", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Doomtrack", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Окурикара", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Инферникен", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Эйс Моррис", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Зиродил", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Vorst", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("МегаДжанк", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Фокс-Сама", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Риатен", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Джон Страж", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("LemonMuffin", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Аура", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("shotgunlo", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Полиполе", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Homero L", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("UtmostPlatypus", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Рейвен Клавдий", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Дракар Браги", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Полковник", "", 0, 0, 0, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Мелоне", "", 0, 0, 0, 0, Subspecies.DEMON));
		
		
		credits.add(new CreditsSlot("Адана Конкер", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Акира", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Алеска", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Лекси <3", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Alvinsimon", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("dragonouv2019", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Aklev", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("AndroidSam", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("48 дней", "", 0, 0, 2, 17));
		credits.add(new CreditsSlot("Спагетти-код", "", 0, 0, 2, 3));
		credits.add(new CreditsSlot("Anonymous_Platypus", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Аптидрагон", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Archan9el S117", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("SchALLieS", "", 0, 0, 4, 12));
		credits.add(new CreditsSlot("Аргмоэ", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("HoneyNutQueerios", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Архан", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Эш", "", 0, 1, 0, 10));
		credits.add(new CreditsSlot("Джек Клауди", "", 0, 1, 10, 0));
		credits.add(new CreditsSlot("b00marrows", "", 0, 1, 5, 0));
		credits.add(new CreditsSlot("Деймиос", "", 0, 0, 3, 7));
		credits.add(new CreditsSlot("Баз Голденкло*", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Mhaak", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Фидель Пиночет", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Тирия", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Runehood66", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Krissy2017", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("Блэккайн", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Блэкхарт", "", 0, 0, 1, 3));
		credits.add(new CreditsSlot("Блэктач", "", 0, 0, 2, 17));
		credits.add(new CreditsSlot("BlakLite", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Blue999", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("BlueWolf", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Брэндон Стах", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("BreakerB", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("BRobort", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("BloodsailXXII", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Берт", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Atroykus", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Калрак", "", 0, 0, 0, 18, Subspecies.DEMON));
		credits.add(new CreditsSlot("Рак-Маг", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Капитан_Сигмус", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Каспер &quot;Cdaser&quot; Д.", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Небесный Кошмар", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Сайт»", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Шлюшка Лекси", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Chattyneko", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Vmpireassassin (Хлоя)", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("cinless", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("crashtestdummy", "", 0, 0, 9, 5));
		credits.add(new CreditsSlot("Малиновый", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("CrowCorvus", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Криошторм", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Проклятая Рена", "", 0, 0, 1, 12));
		credits.add(new CreditsSlot("Циничный-Си", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Dace617", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Саладофстоунс", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Дэн", "", 0, 1, 0, 15));
		credits.add(new CreditsSlot("Хикару Светоносный", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Грифф", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Даниэль Д. Манан", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("ДарКаз", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Дартсойер", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Иллариус", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("МертвыеГлазаВидят", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("DeadMasterZero", "", 0, 0, 8, 6));
		credits.add(new CreditsSlot("CruellerTwo24 ", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Demonicgamer666", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Джон Скарлет", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Десгакс", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Дестон", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Нордо", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Ди", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("DLI", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("suka", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("rinoskin", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Алатар", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Элмсдор", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("EnigmaticYoshi", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("Бесконечный", "", 0, 0, 6, 2));
		credits.add(new CreditsSlot("Зелёная фасоль", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("Эрин Киан", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Авандемин", "", 0, 0, 1, 11));
		credits.add(new CreditsSlot("Эвит", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Ф. Роуэн", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Искатель", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("щенячий шлюх феликс", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Fenrakk101", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Фиона", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("ForeverFree2MeTaMax", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("FossorTumulus", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Уильям Э", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Freekingamer", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("fun_bot", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Ники Паркс", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("Гаркилал", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Georgio154", "", 0, 0, 1, 6));
		credits.add(new CreditsSlot("glocknar", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Золотой член", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Гракснар", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Гробовое негодование", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("GravyRainbow", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("WodashGSJ", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Aceofspades", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Ассиялос", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Ёжик", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Хелириэль", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Jatch", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("JaminGold", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Джейсон Патерсон", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("первый_навык", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Bocaj91", "", 0, 0, 0, 13));
		credits.add(new CreditsSlot("Крейжил", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Джоибер", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Eushully", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("Джошуа Уолтер", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Garth614", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Хустисия Энтони", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("HerrKommissar11", "", 0, 0, 1, 4));
		credits.add(new CreditsSlot("Каэреа", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("Калеб Мудрый", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Карлимеро", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Tappi", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("KazukiNero", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Kelly999", "", 0, 1, 12, 0));
		credits.add(new CreditsSlot("kenshin5491", "", 0, 0, 18, 0));
		credits.add(new CreditsSlot("Кестрел", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("BlueVulcan", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Царь Царей", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Кироберос", "", 0, 0, 0, 17));
		credits.add(new CreditsSlot("Керног", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Рыцарь-лорд Ксандер", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Крозоз", "", 0, 0, 2, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Крулин", "", 0, 0, 1, 0));
		credits.add(new CreditsSlot("Киралон", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Крис Терпин", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Ли Томпсон", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("Леоб", "", 0, 0, 10, 4));
		credits.add(new CreditsSlot("Паллид", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("ilderon", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("МаленькаяКошечка", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("ЛедиЛисиц", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Мистер Л", "", 0, 0, 4, 1));
		credits.add(new CreditsSlot("loveless", "", 0, 0, 0, 19, Subspecies.DEMON));
		credits.add(new CreditsSlot("Ваддекс", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("Кицунэ Лин", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Бабник", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Бельдамон", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("matchsticks", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("masterpuppet", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Кошмар", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("АльфаОдинБраво", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Макс Никто", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Мега", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Миа Монтане", "", 0, 0, 0, 3, Subspecies.DEMON));
		credits.add(new CreditsSlot("Мора", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Мухаку", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Нексимус", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Милерра", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Кобу", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Натемаре", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("IreCobra", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("NeonRaven94", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Неоновый Сваглорд Чен", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Нексусмен", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Сестра-Фистер420", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Ник Лаблю", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Кверник", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Нико", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Ннхх", "", 0, 1, 3, 2));
		credits.add(new CreditsSlot("НорвежскийМонстр", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Сео Лейфтхрасир", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Odd8Ball", "", 0, 0, 0, 18));
		credits.add(new CreditsSlot("Партийный комиссар", "", 0, 0, 4, 13));
		credits.add(new CreditsSlot("Патрик Грёндлунд", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Просто отпад", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Ропси", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("P.", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("BLKCandy", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Пьер Мура", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Покис", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("PoyntFury", "", 0, 0, 1, 4, Subspecies.DEMON));
		credits.add(new CreditsSlot("QQQ", "", 0, 0, 0, 19, Subspecies.DEMON));
		credits.add(new CreditsSlot("awrfyu_", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Ракеш", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("R.W", "", 0, 3, 11, 0, Subspecies.DEMON));
		credits.add(new CreditsSlot("Раруке", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Принц Бездны", "", 0, 0, 13, 0));
		credits.add(new CreditsSlot("Тупая сучка Хозяина", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Арпи", "", 0, 0, 0, 1));
		credits.add(new CreditsSlot("Рейла Ода", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Роарик", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Темный_Лорд", "", 0, 0, 2, 6));
		credits.add(new CreditsSlot("redwulfen", "", 0, 0, 0, 18));
		credits.add(new CreditsSlot("Роджер Рейн", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("RogueRandom", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Horagen81", "", 0, 0, 0, 13, Subspecies.DEMON));
		credits.add(new CreditsSlot("RyubosJ", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Саладин Легендарный", "", 0, 0, 0, 15));
		credits.add(new CreditsSlot("Sand9k", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Шанде", "", 0, 0, 0, 11));
		credits.add(new CreditsSlot("Скит", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Синий кобольд", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("sebasjac", "", 0, 0, 0, 2));
		credits.add(new CreditsSlot("S", "", 0, 0, 1, 18));
		credits.add(new CreditsSlot("Шас'О Дал'ит Кауйон Кайс Таку", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("Ворон Непобедимый", "", 0, 0, 18, 0));
		credits.add(new CreditsSlot("Шелтем", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Шилоу", "", 0, 0, 0, 1, Subspecies.DEMON));
		credits.add(new CreditsSlot("shrikes", "", 0, 0, 8, 2));
		credits.add(new CreditsSlot("Сиг", "", 0, 0, 4, 0));
		credits.add(new CreditsSlot("Силентарк", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Сэр Бинс", "", 0, 0, 10, 0));
		credits.add(new CreditsSlot("Сортировщик", "", 0, 0, 0, 9));
		credits.add(new CreditsSlot("Зрелищный", "", 0, 0, 11, 0));
		credits.add(new CreditsSlot("Спенсер", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Призрачные люди", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Звездохлад", "", 0, 0, 0, 8));
		credits.add(new CreditsSlot("Стеф", "", 0, 0, 9, 0));
		credits.add(new CreditsSlot("Strigon888", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("Суварестин", "", 0, 0, 2, 0));
		credits.add(new CreditsSlot("Быстрый выстрел", "", 0, 0, 14, 0));
		credits.add(new CreditsSlot("TalonMort", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Таналл", "", 0, 1, 17, 0));
		credits.add(new CreditsSlot("Таннер Д.", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Терренс", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Testostetyrone", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Бросенарий", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Джордан Эйткен", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("Т. Гару", "", 0, 0, 0, 12));
		credits.add(new CreditsSlot("xerton", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Timmybond24", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("TKaempfer", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Pro Skater от Тома Клэнси", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("FreakyHydra", "", 0, 0, 0, 4));
		credits.add(new CreditsSlot("Kahvi_Toope", "", 0, 0, 0, 6));
		credits.add(new CreditsSlot("Торинир", "", 0, 0, 18, 0));
		credits.add(new CreditsSlot("Torsten015", "", 0, 0, 0, 18));
		credits.add(new CreditsSlot("ТринВалл", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("triangleman", "", 0, 0, 16, 0));
		credits.add(new CreditsSlot("Антриад", "", 0, 0, 1, 13));
		credits.add(new CreditsSlot("Джесс", "", 0, 0, 3, 0));
		credits.add(new CreditsSlot("Исидорос", "", 0, 0, 7, 0));
		credits.add(new CreditsSlot("СоларЭйдолон", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Ваэлин", "", 0, 0, 4, 14));
		credits.add(new CreditsSlot("vasadariu", "", 0, 0, 15, 0));
		credits.add(new CreditsSlot("waaaghkus", "", 0, 0, 19, 0));
		credits.add(new CreditsSlot("Веноми", "", 0, 0, 0, 5));
		credits.add(new CreditsSlot("iloveyouMiaoNiNi", "", 0, 0, 0, 14));
		credits.add(new CreditsSlot("Вегсхааль", "", 0, 0, 0, 3));
		credits.add(new CreditsSlot("Без разницы", "", 0, 0, 17, 0));
		credits.add(new CreditsSlot("Уилл Ландрум", "", 0, 0, 0, 7));
		credits.add(new CreditsSlot("Уильям Браун", "", 0, 0, 5, 2));
		credits.add(new CreditsSlot("Мэри́с", "", 0, 0, 0, 10));
		credits.add(new CreditsSlot("CMPirate9867", "", 0, 0, 8, 0));
		credits.add(new CreditsSlot("Волфрейв", "", 0, 0, 6, 0));
		credits.add(new CreditsSlot("Вольфрегис", "", 0, 0, 0, 19));
		credits.add(new CreditsSlot("Yuki_Sukafu", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Нельсон Адамс", "", 0, 0, 12, 0));
		credits.add(new CreditsSlot("Закарин", "", 0, 0, 0, 14, Subspecies.DEMON));
		credits.add(new CreditsSlot("Зая", "", 0, 0, 5, 0));
		credits.add(new CreditsSlot("Zero_One", "", 0, 0, 4, 0));
		
		
		
		credits.sort(Comparator.comparing((CreditsSlot a) -> a.getName().toLowerCase()));
		
		
		Main.primaryStage = primaryStage;
		
		Main.primaryStage.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
				if(t) {
					TooltipUpdateThread.cancelThreads = true;
				}
			}
		});

		WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		refreshTitle();

		loadFonts();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));

		Pane pane = loader.load();

		mainScene = new Scene(pane);

		if (properties.hasValue(PropertyValue.lightTheme)) {
			mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
		} else {
			mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
		}

		mainController = loader.getController();
		Main.primaryStage.setScene(mainScene);
		Main.primaryStage.show();
		Main.game = new Game();
		Main.sex = new Sex();
		Main.combat = new Combat();
		
		loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));
		try {
			if (Main.mainScene == null) {
				pane = loader.load();
				Main.mainController = loader.getController();

				Main.mainScene = new Scene(pane);
				if (Main.getProperties().hasValue(PropertyValue.lightTheme))
					Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
				else
					Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
			}

			Main.primaryStage.setScene(Main.mainScene);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.setContent(new Response("", "", OptionsDialogue.MENU));
		
	}
	
	public static boolean CheckNotUnpacked() {
		File dir = new File("");
		String currentDir = dir.getAbsolutePath();
		String tempDir = System.getProperty("java.io.tmpdir");
//		System.err.println(currentDir);
//		System.err.println(tempDir);
		return currentDir.contains(tempDir);
	}
	
	protected static void CheckForDataDirectory() {
		File dir = new File("data/");
		if(!dir.exists()) {
			Alert a = new Alert(AlertType.ERROR,
					"Не удалось найти папку 'data' ("+dir.getAbsolutePath()+"). Сохранение и ведение логов ошибок отключены.\nУбедитесь, что вы распаковали игру из zip-архива и что у файлов есть права на запись.\n(Пожалуйста, прочитайте раздел 'MISSING FOLDERS' в файле README.txt.)\nПродолжить?",
					ButtonType.YES, ButtonType.NO);
			System.err.println("Unable to find the 'data' folder ("+dir.getAbsolutePath()+").");
			a.showAndWait().ifPresent(response -> {
			     if (response == ButtonType.NO) {
			         System.exit(1);
			     }
			 });
		}
	}
	
	protected static void CheckForResFolder() {
		File dir = new File("res/");
		if(!dir.exists()) {
			Alert a = new Alert(AlertType.WARNING,
					"Не удалось найти папку 'res' ("+dir.getAbsolutePath()+"). Это ОБЯЗАТЕЛЬНО приведет к ошибкам и появлению фрагментов отсутствующего текста.\nУбедитесь, что вы распаковали игру из zip-архива и что у файлов есть права на запись.\nЕсли вы используете командную строку или batch/script файл для запуска игры, попробуйте запустить её непосредственно из папки с игрой, чтобы избежать этой ошибки.\n(Пожалуйста, прочитайте раздел 'MISSING FOLDERS' в файле README.txt.)\nПродолжить?",
					ButtonType.YES, ButtonType.NO);
			System.err.println("Unable to find the 'res' folder ("+dir.getAbsolutePath()+").");
			a.showAndWait().ifPresent(response -> {
				if(response == ButtonType.NO) {
					System.exit(1);
				}
			});
		}
	}

	/**
	 * Attempts to load fallback fonts to make sure that they are available later. The size doesn't actually matter as
	 * the WebEngine will reload other sizes as required. The files referenced must persist until application shutdown.
	 *
	 * Do not call Font.getFamilies() prior to this as additional fonts must be loaded before the list is cached.
	 */
	protected void loadFonts() {
		// Load fallback for Calibri
		if (Font.loadFont(toUri("res/fonts/Carlito/Carlito-Regular.ttf"), 11) != null) {
			// Load variants
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-Bold.ttf"), 11);
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-BoldItalic.ttf"), 11);
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-Italic.ttf"), 11);
		} else {
			System.err.println("Carlito font could not be loaded.");
		}

		// Load fallback for Verdana
		if (Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans.ttf"), 12) != null) {
			// Load variants
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-Bold.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-BoldOblique.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-ExtraLight.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-Oblique.ttf"), 12);
		} else {
			System.err.println("DejaVu Sans font could not be loaded.");
		}
	}

	/**
	 * Creates a URI string with spaces. The given path can be absolute or relative to the current working directory.
	 * @param path The path to convert
	 * @return A string containing a URI
	 */
	public static String toUri(String path) {
		return Paths.get(path).toUri().toString().replaceAll("%20", " ");
	}

	public static DocumentBuilder getDocBuilder() {
		if (docBuilder == null) {
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return docBuilder;
	}

	public static String getPatchNotes() {
		if (!patchNotes.isEmpty()) {
			return patchNotes;
		}

		String prefaceText = "";
		StringBuilder patchNotesText = new StringBuilder();

		File patchNotesFolder = new File("res/patchNotes");
		if (!patchNotesFolder.exists() || !patchNotesFolder.isDirectory()) {
			System.err.println("Folder 'res/patchNotes' could not be loaded!");
			return "";
		}

		File[] fileList = patchNotesFolder.listFiles((dir, name) -> name.endsWith(".html"));
		Arrays.sort(fileList, Collections.reverseOrder());

		for (File file : fileList) {
			Matcher matcher = Pattern.compile("^\\d{8}-(preface|patchnotes)-", Pattern.CASE_INSENSITIVE).matcher(file.getName());
			if (!matcher.find()) {
				continue;
			}

			try (FileInputStream fis = new FileInputStream(file)) {
				String contentHTML = Util.inputStreamToString(fis);
				switch (matcher.group(1).toLowerCase()) {
					case "preface":
						if (prefaceText.isEmpty()) {
							prefaceText = contentHTML;
						}
						break;

					case "patchnotes":
						patchNotesText.append("<br />\n<div class=\"list\">\n").append(contentHTML).append("</div>\n");
						break;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		patchNotes = prefaceText + patchNotesText;

		return patchNotes;
	}

	public static void main(String[] args) {
		
		// Create folders:
		File dir = new File("data/");
		dir.mkdir();
		dir = new File("data/saves");
		dir.mkdir();
		dir = new File("data/characters");
		dir.mkdir();
		
		
		// Open error log
		if(!DEBUG) {
			System.out.println("Printing to error.log");
			try {
				PrintStream stream = new PrintStream("data/error.log");
				System.setErr(stream);
				System.err.println("Game Version: "+VERSION_NUMBER+" ("+System.getProperty("build.type", "jar")+")");
				System.err.println("Java: "+System.getProperty("java.version")+" ("+System.getProperty("java.vendor")+")");
				System.err.println("OS: "+System.getProperty("os.name")+" ("+System.getProperty("os.arch")+")");
				if (new File("res/mods").exists()) {
					System.err.print("Mod folders present: ");
					int i=0;
					for(File f : new File("res/mods").listFiles()) {
						if(f.isDirectory()) {
							if(i>0) {
								System.err.print(", ");
							}
							System.err.print(f.getName());
						}
						i++;
					}
					System.err.println();
				}
				
				
//				System.err.println("OS: "+System.getProperty("os.name"));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		// Load properties:
		if (new File("data/properties.xml").exists()) {
			try {
				properties = new Properties();
				properties.loadPropertiesFromXML();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			properties = new Properties();
			properties.savePropertiesAsXML();
		}

		launch(args);
	}
	
	/**
	 * Starts a completely new game. Runs a new World Generation.
	 */
	public static void startNewGame(DialogueNode startingDialogueNode) {
		
		Main.game = new Game();
		
		// Generate world:
		if (!(gen == null)) {
			if (gen.isRunning()) {
				gen.cancel();
			}
		}
		
		gen = new Generation();

		gen.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));
				Pane pane;
				try {
					if (Main.mainScene == null) {
						pane = loader.load();
						Main.mainController = loader.getController();

						Main.mainScene = new Scene(pane);
						if (Main.getProperties().hasValue(PropertyValue.lightTheme))
							Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
						else
							Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
					}

					Main.primaryStage.setScene(Main.mainScene);

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				Main.game.setPlayer(new PlayerCharacter(false, new NameTriplet("Игрок"), 1, null, Gender.M_P_MALE, Subspecies.HUMAN, RaceStage.HUMAN, WorldType.MUSEUM, PlaceType.MUSEUM_ENTRANCE));

				Main.game.initNewGame(startingDialogueNode);

				Main.game.endTurn(0);
				
				OptionsDialogue.startingNewGame = false;
				//Main.mainController.processNewDialogue();
			}
		});
		new Thread(gen).start();
	}
	
	public static boolean isVersionOlderThan(String versionToCheck, String versionToCheckAgainst) {
		String[] v1 = versionToCheck.split("\\.");
		String[] v2 = versionToCheckAgainst.split("\\.");
		
		try {
			int maxLength = (v1.length > v2.length) ? v1.length : v2.length;
			for (int i = 0; i < maxLength; i++) {
				int v1i;
				int v2i;
				
				if(v1[1].charAt(0)=='1') { // Versions prior to 0.2.x used an old system of the format: 0.1.10.1 being a lower version than 0.1.9.1:
					v1i = (i < v1.length) ? Integer.valueOf((v1[i]+"00").substring(0, 3)) : 0;
					v2i = (i < v2.length) ? Integer.valueOf((v2[i]+"00").substring(0, 3)) : 0;
					
				} else { // Versions of 0.2.x and higher use a new system of the format: 0.2.10.1 being a higher version than 0.2.9.1:
					v1i = (i < v1.length) ? Integer.valueOf(v1[i]) : 0;
					v2i = (i < v2.length) ? Integer.valueOf(v2[i]) : 0;
				}
				
				if (v1i < v2i) {
					return true;
				} else if (v1i > v2i) {
					return false;
				} 
			}
			
		} catch(Exception ex) {
			return true;
		}
		
		return false;
	}
	
	public static void refreshTitle() {
		Main.primaryStage.setTitle(getTitle());
	}
	
	public static String getTitle() {
		displayingTurnTimer = Main.game!=null && Main.game.isDebugMode() && Main.game.isStarted();
		
		return NAME_OF_GAME
				+ " " + VERSION_NUMBER
				+ " " + VERSION_DESCRIPTION
				+ (DEBUG?" (Режим отладки)":"")
				+ (displayingTurnTimer
					?" "+Math.round((Main.game.endTurnTimeTaken/1000000000d)*1000)/1000f+"s"
					:"");
	}
	
	public static boolean isDisplayingTurnTimer() {
		return displayingTurnTimer;
	}
	
	public static int getFontSize() {
		return properties.fontSize;
	}

	public static void setFontSize(int size) {
		properties.fontSize = size;
		properties.savePropertiesAsXML();
	}
	
	public static boolean isQuickSaveAvailable() {
		return Main.game.isStarted()
				&& !Main.game.isInCombat()
				&& !Main.game.isInSex()
				&& Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL
				&& Main.game.isInNeutralDialogue();
	}
	
	public static String getQuickSaveUnavailabilityDescription() {
		if (!Main.game.isInNewWorld()) {
			return "Вы не можете сохранить игру во время создания персонажа или пролога!";
			
		} else if (Main.game.isInCombat()) {
			return "Вы не можете сохранить игру во время боя!";
			
		} else if (Main.game.isInSex()) {
			return "Вы не можете сохранить игру во время секса!";
			
		} else if (Main.game.getCurrentDialogueNode().getDialogueNodeType()!=DialogueNodeType.NORMAL) {
			return "Вы можете сохранить игру только в нейтральной сцене!";
			
		} else if (!Main.game.isStarted() || !Main.game.isInNeutralDialogue()) {
			return "Вы можете сохранить игру только в нейтральной сцене!";
		}
		
		return "";
	}
	
	public static String getQuickSaveName() {
		String name;
		if(!Main.game.isStarted()) {
			name = "Быстрое сохранение: вступление";
		} else {
			name = "Быстрое сохранение_"+Main.game.getPlayer().getName(false);
		}
		return Main.checkFileName(name);
	}
	
	public static void quickSaveGame() {
		if(isQuickSaveAvailable()){
			Main.getProperties().lastQuickSaveName = getQuickSaveName();
			saveGame(getQuickSaveName(), true, false);
			quickSaved = true;
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, getQuickSaveUnavailabilityDescription());
		}
	}

	public static void quickLoadGame() {
		String name = "";
		if(quickSaved) {
			name = Main.checkFileName(Main.properties.lastQuickSaveName);
		} else {
			name = Main.checkFileName(getQuickSaveName());
		}

		if(name.isEmpty()) {
			return;
		}
		loadGame(name);
	}

	public static boolean isSaveGameAvailable() {
		return Main.game.isStarted()
				&& ((!Main.game.getSavedDialogueNode().isTravelDisabled() && MapTravelType.WALK_SAFE.isAvailable(Main.game.getPlayerCell(), Main.game.getPlayer()))
						|| Main.game.getSavedDialogueNode().equals(Main.game.getDefaultDialogue(false)));
	}
	
	public static void saveGame(String name, boolean allowOverwrite, boolean isAutoSave) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		Game.exportGame(name, allowOverwrite, isAutoSave);

		try {
			properties.lastSaveLocation = name;//"data/saves/"+name+".lts";
			properties.nameColour = Femininity.valueOf(game.getPlayer().getFemininityValue()).getColour().toWebHexString();
			properties.name = game.getPlayer().getName(false);
			properties.level = game.getPlayer().getLevel();
			properties.money = game.getPlayer().getMoney();
			properties.arcaneEssences = game.getPlayer().getEssenceCount();
			properties.raceColour = game.getPlayer().getSubspecies().getColour(Main.game.getPlayer()).toWebHexString();
			if (game.getPlayer().isFeminine()) {
				properties.race = game.getPlayer().getSubspecies().getSingularFemaleName(game.getPlayer().getBody());
			} else {
				properties.race = game.getPlayer().getSubspecies().getSingularMaleName(game.getPlayer().getBody());
			}
			properties.quest = game.getPlayer().getQuest(QuestLine.MAIN).getName();

			properties.savePropertiesAsXML();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String checkFileName(String name) {
		name = name.replace(" ", "_").replaceAll("[^\\w]+", "");
		if (name.length()==0) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Слишком короткое имя!");
			return "";
		}
		if (name.length() > 64) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Слишком длинное имя!");
			return "";
		}
		return name;
	}

	public static boolean isLoadGameAvailable(String name) {
		File file = new File("data/saves/"+name+".xml");

		return file.exists();
	}
	
	public static void loadGame(String name) {
		if (isLoadGameAvailable(name)) {
			Game.importGame(name);
			MainController.updateUIButtons();
		}
	}

	public static void loadGame(File f) {
		Game.importGame(f);
		MainController.updateUIButtons();
	}
	
	public static void deleteGame(String name) {
		File file = new File("data/saves/"+name+".xml");

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

	public static void deleteExportedGame(String name) {
		File file = new File("data/saves/"+name+".xml");

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

	public static void deleteExportedCharacter(String name) {
		File file = new File("data/characters/"+name+".xml");

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
	
	/**
	 * @param sortAlphabetically Pass in true if you want the files sorted alphabetically, false if you want them sorted by date modified.
	 * @return A list of xml files from the 'data/saves' folder.
	 */
	public static List<File> getSavedGames(boolean sortAlphabetically) {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/saves");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}
		
		if(sortAlphabetically) {
			filesList.sort(Comparator.comparing(File::getName));
		} else {
			filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		}
		
		return filesList;
	}
	
	public static List<File> getCharactersForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/characters");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		
		return filesList;
	}
	
	public static List<File> getSlavesForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/characters");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}
		
		filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		
		return filesList;
	}
	
	public static List<File> getGamesForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/saves");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		
		return filesList;
	}
	
	public static void importCharacter(File file) {
		if (file != null) {
			try {
				Main.game.setPlayer(Main.game.getCharacterUtils().startLoadingCharacterFromXML());
				Main.game.setPlayer(Main.game.getCharacterUtils().loadCharacterFromXML(file, Main.game.getPlayer(),
						CharacterImportSetting.NEW_GAME_IMPORT,
						CharacterImportSetting.NO_PREGNANCY,
						CharacterImportSetting.NO_COMPANIONS,
						CharacterImportSetting.NO_ELEMENTAL,
						CharacterImportSetting.CLEAR_SLAVERY,
						CharacterImportSetting.CLEAR_KEY_ITEMS,
						CharacterImportSetting.CLEAR_COMBAT_HISTORY,
						CharacterImportSetting.CLEAR_SEX_HISTORY,
						CharacterImportSetting.REMOVE_RACE_CONCEALED,
						CharacterImportSetting.CLEAR_FAMILY_ID));
				
				Main.game.getPlayer().getSlavesOwned().clear();
				Main.game.getPlayer().endPregnancy(false);
				
				Main.game.setRenderAttributesSection(true);
				Main.game.clearTextStartStringBuilder();
				Main.game.clearTextEndStringBuilder();
				Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
				Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
				Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
				Main.game.getPlayer().calculateStatusEffects(0);

				Main.game.initNewGame(CharacterCreation.START_GAME_WITH_IMPORT);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void saveProperties() {
		properties.savePropertiesAsXML();
	}
}
