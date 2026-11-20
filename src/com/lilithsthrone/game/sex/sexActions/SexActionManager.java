package com.lilithsthrone.game.sex.sexActions;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.sex.sexActions.baseActions.ClitAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerBreastsCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerFinger;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.FootMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisArmpit;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAss;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreastsCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisPenisCloaca;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFoot;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisSpinneret;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisThighs;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisUrethraPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisUrethraVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TentacleAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TentacleMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TentacleVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueArmpit;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueBreastsCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMound;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueNippleCrotch;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericPositioning;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.LovingActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PartnerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PositioningMenu;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.SadisticActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerCrotchNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfFingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfNoPen;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfPenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfPenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfPenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfPenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTailVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTongueNipple;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelf.SelfTongueVagina;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * Handles the loading and id generation of SexActions from both internal and external files.
 * 
 * @since 0.4.1
 * @version 0.4.1
 * @author Innoxia
 */
public class SexActionManager {
	
	private static List<SexAction> allSexActions = new ArrayList<>();
	private static Map<SexAction, String> sexActionToIdMap = new HashMap<>();
	private static Map<String, SexAction> idToSexActionMap = new HashMap<>();
	
	public static List<SexAction> getAllSexActions() {
		return allSexActions;
	}
	
	public static SexAction getSexActionFromId(String id) {
		id = id.trim(); // Just make sure that any parsed ids have been trimmed
		id = Util.getClosestStringMatch(id, idToSexActionMap.keySet());
		return idToSexActionMap.get(id);
	}
	
	public static String getIdFromSexAction(SexAction sexAction) {
		return sexActionToIdMap.get(sexAction);
	}
	
	static {
		// Modded sexActions:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/sex/actions");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					SexActionExternal sexAction = new SexActionExternal(innerEntry.getValue(), innerEntry.getKey(), false);
					String id = innerEntry.getKey();
					id = id.replaceAll("sex_actions_", "");
					allSexActions.add(sexAction);
					sexActionToIdMap.put(sexAction, id);
					idToSexActionMap.put(id, sexAction);
//					System.out.println("modded sexAction: "+innerEntry.getKey());
				} catch(Exception ex) {
					System.err.println("Loading modded sexAction type failed at 'SexAction'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res SexActions:
		
		// Ids are generated in the format 'author_folders_fileName'
		// Example: innoxia_meraxis_masturbation_orgasm_panties_cum
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/sex");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("sexAction")) {
					try {
						SexActionExternal sexAction = new SexActionExternal(innerEntry.getValue(), innerEntry.getKey(), false);
						String id = innerEntry.getKey();
						id = id.replaceAll("actions_", "");
						allSexActions.add(sexAction);
						sexActionToIdMap.put(sexAction, id);
						idToSexActionMap.put(id, sexAction);
//						System.out.println("res sexAction: "+node.getId());
							
					} catch(Exception ex) {
						System.err.println("Loading sexAction type failed at 'SexAction'. File path: "+innerEntry.getValue().getAbsolutePath());
						System.err.println("Actual exception: ");
						ex.printStackTrace(System.err);
					}
				}
			}
		}

		// Add in hard-coded sex actions:
		
		Map<String, Field[]> sexActionClassIdToFields = Util.newHashMapOfValues(new Value<>("Меню поз", PositioningMenu.class.getFields()),
				new Value<>("Общие позы", GenericPositioning.class.getFields()),
				
				new Value<>("Общие действия", GenericActions.class.getFields()),
				new Value<>("Общие оргазмы", GenericOrgasms.class.getFields()),
				new Value<>("Разговор игрока", PlayerTalk.class.getFields()),
				new Value<>("Разговор партнера", PartnerTalk.class.getFields()),
				new Value<>("Общий разговор", GenericTalk.class.getFields()),

				// Sadistic actions:
				new Value<>("Садистские действия", SadisticActions.class.getFields()),

				// Gentle actions:
				new Value<>("Любящие действия", LovingActions.class.getFields()),
				
				// Finger actions:
				new Value<>("Пальцы в анус", FingerAnus.class.getFields()),
				new Value<>("Ласкать грудь пальцами", FingerBreasts.class.getFields()),
				new Value<>("Пальцами по груди и лобку", FingerBreastsCrotch.class.getFields()),
				new Value<>("Пальцы во рту", FingerMouth.class.getFields()),
				new Value<>("Ласкать соски пальцами", FingerNipple.class.getFields()),
				new Value<>("Пальцами по соскам и лобку", FingerNippleCrotch.class.getFields()),
				new Value<>("Пальцы во влагалище", FingerVagina.class.getFields()),
				new Value<>("Ласкать клитор пальцами", FingerClit.class.getFields()),
				new Value<>("Ласкать член пальцами", FingerPenis.class.getFields()),
				new Value<>("Пальцы в пальцы", FingerFinger.class.getFields()),

				// Oral actions:
				new Value<>("Язык во рту", TongueMouth.class.getFields()),
				new Value<>("Язык в анусе", TongueAnus.class.getFields()),
				new Value<>("Язык во влагалище", TongueVagina.class.getFields()),
				new Value<>("Клитор во рту", ClitMouth.class.getFields()),
				new Value<>("Язык по лобку", TongueMound.class.getFields()),
				new Value<>("Язык по груди", TongueBreasts.class.getFields()),
				new Value<>("Язык от груди к промежности", TongueBreastsCrotch.class.getFields()),
				new Value<>("Язык по соску", TongueNipple.class.getFields()),
				new Value<>("Язык от соска к промежности", TongueNippleCrotch.class.getFields()),
				new Value<>("Стопа во рту", FootMouth.class.getFields()),
				new Value<>("Пенис во рту", PenisMouth.class.getFields()),
				new Value<>("Язык в подмышках", TongueArmpit.class.getFields()),

				// Tail actions:
				new Value<>("Хвост в анусе", TailAnus.class.getFields()),
				new Value<>("Хвост во влагалище", TailVagina.class.getFields()),
				new Value<>("Хвост во рту", TailMouth.class.getFields()),

				// Tentacle actions:
				new Value<>("Тентакль в анусе", TentacleAnus.class.getFields()),
				new Value<>("Тентакль во влагалище", TentacleVagina.class.getFields()),
				new Value<>("Тентакль во рту", TentacleMouth.class.getFields()),
				
				// Penis actions:
				new Value<>("Пенис в задницу", PenisAss.class.getFields()),
				new Value<>("Пенис в анус", PenisAnus.class.getFields()),
				new Value<>("Пенис-Сосок", PenisNipple.class.getFields()),
				new Value<>("Пенис-Сосок-Промежность", PenisNippleCrotch.class.getFields()),
				new Value<>("Пенис-Вагина", PenisVagina.class.getFields()),
				new Value<>("Пенис-Грудь", PenisBreasts.class.getFields()),
				new Value<>("Пенис-Грудь-Промежность", PenisBreastsCrotch.class.getFields()),
				new Value<>("Пенис-Бедра", PenisThighs.class.getFields()),
				new Value<>("Пенис-Стопа", PenisFoot.class.getFields()),
				new Value<>("Пенис-Ступни", PenisFeet.class.getFields()),
				new Value<>("Пенис-Уретра-Вагина", PenisUrethraVagina.class.getFields()),
				new Value<>("Пенис-Уретра-Пенис", PenisUrethraPenis.class.getFields()),
				new Value<>("Пенис-сосок", PenisSpinneret.class.getFields()),
				new Value<>("Пенис-подмышка", PenisArmpit.class.getFields()),
				new Value<>("Пенис-пенис", PenisPenis.class.getFields()),
				new Value<>("Пенис-клоака", PenisPenisCloaca.class.getFields()),
				
				// Vagina/clit actions:
				new Value<>("Клитор-клитор", ClitClit.class.getFields()),
				new Value<>("Клитор-вагина", ClitVagina.class.getFields()),
				new Value<>("Клитор-анус", ClitAnus.class.getFields()),
				new Value<>("Клитор-сосок", ClitNipple.class.getFields()),
				new Value<>("Клитор-сосок-промежность", ClitNippleCrotch.class.getFields()),
		
				// Self actions
				new Value<>("Самоудовлетворение (без пениса)", SelfNoPen.class.getFields()),
				new Value<>("Ласкать анус пальцами", SelfFingerAnus.class.getFields()),
				new Value<>("Ласкать грудь пальцами", SelfFingerBreasts.class.getFields()),
				new Value<>("Ласкать соски и промежность пальцами", SelfFingerCrotchNipple.class.getFields()),
				new Value<>("Ласкать рот пальцами", SelfFingerMouth.class.getFields()),
				new Value<>("Ласкать соски пальцами", SelfFingerNipple.class.getFields()),
				new Value<>("Ласкать член пальцами", SelfFingerPenis.class.getFields()),
				new Value<>("Ласкать влагалище пальцами", SelfFingerVagina.class.getFields()),
				
				new Value<>("Тереть анус членом", SelfPenisAnus.class.getFields()),
				new Value<>("Тереть рот членом", SelfPenisMouth.class.getFields()),
				new Value<>("Тереть соски членом", SelfPenisNipple.class.getFields()),
				new Value<>("Свой член во влагалище", SelfPenisVagina.class.getFields()),

				new Value<>("Свой хвост в анус", SelfTailAnus.class.getFields()),
				new Value<>("Свой хвост во рту", SelfTailMouth.class.getFields()),
				new Value<>("Свой хвост в сосок", SelfTailNipple.class.getFields()),
				new Value<>("Свой хвост во влагалище", SelfTailVagina.class.getFields()),

				new Value<>("Свой язык в анус", SelfTongueAnus.class.getFields()),
				new Value<>("Свой язык во рту", SelfTongueMouth.class.getFields()),
				new Value<>("Свой язык в сосок", SelfTongueNipple.class.getFields()),
				new Value<>("Свой язык во влагалище", SelfTongueVagina.class.getFields()));
				
		for(Entry<String, Field[]> entry : sexActionClassIdToFields.entrySet()) {
			for(Field f : entry.getValue()) {
				if (SexAction.class.isAssignableFrom(f.getType())) {
					SexAction sexAction;
					try {
						sexAction = ((SexAction) f.get(null));
						
						String id = entry.getKey()+"_"+f.getName();
						
						sexActionToIdMap.put(sexAction, id);
						idToSexActionMap.put(id, sexAction);
						
						allSexActions.add(sexAction);
						
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
