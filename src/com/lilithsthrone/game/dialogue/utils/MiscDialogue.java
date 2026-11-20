package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Fiammetta;
import com.lilithsthrone.game.character.npc.dominion.Saellatrix;
import com.lilithsthrone.game.character.npc.fields.Angelixx;
import com.lilithsthrone.game.character.npc.misc.BasicDoll;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractFilledCondom;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.62
 * @version 0.3.7.7
 * @author Innoxia
 */
public class MiscDialogue {
	
	public static final DialogueNode STATUS_EFFECTS = new DialogueNode("Важные обновления эффектов статуса", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			for(Entry<Long, Map<AbstractStatusEffect, String>> entry : Main.game.getPlayer().getStatusEffectDescriptions().entrySet()){
				if(!entry.getValue().isEmpty()) {
					sb.append("<div class='container-full-width'>");
						sb.append("<h6 style='text-align:center; margin:16px auto 0 auto; padding:0;'>"+Units.dateTime(Main.game.getStartingDate().plusSeconds(entry.getKey()))+":</h6>");
						for(Entry<AbstractStatusEffect, String> innerEntry : entry.getValue().entrySet()) {
							sb.append("<hr/>");
							sb.append("<h6 style='text-align:center; margin:0; padding:0;'>");
								sb.append(Util.capitaliseSentence(innerEntry.getKey()==null?"Разные эффекты":innerEntry.getKey().getName(Main.game.getPlayer())));
							sb.append("</h6>");
							sb.append("<p style='margin-top:0;'>");
								sb.append(UtilText.parse(Main.game.getPlayer(), innerEntry.getValue()));
							sb.append("</p>");
						}
					sb.append("</div>");
				}
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("Продолжить", "Продолжить делать то, чем вы занимались."){
					@Override
					public void effects() {
						Main.game.getPlayer().getStatusEffectDescriptions().clear();
						Main.game.restoreSavedContent(false);
					}
				};
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.STATUS_EFFECT_MESSAGE;
		}
	};

	private static boolean withHairStyle;
	private static String makeupOpeningDescription;

	public static DialogueNode getMakeupDialogueForEqualityCheck() {
		return BODY_CHANGING_MAKEUP;
	}
	
	public static DialogueNode getMakeupDialogue(boolean withHairStyle, String makeupOpeningDescription) {
		MiscDialogue.withHairStyle = withHairStyle;
		MiscDialogue.makeupOpeningDescription = makeupOpeningDescription;
		return BODY_CHANGING_MAKEUP;
	}
	
	private static final DialogueNode BODY_CHANGING_MAKEUP = new DialogueNode("Макияж", "", true) {
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='container-full-width' style='text-align:center;'>"
						+ makeupOpeningDescription
					+ "</div>"
					
					+ (withHairStyle
						?CharacterModificationUtils.getSelfDivHairStyles("Прическа", UtilText.parse(BodyChanging.getTarget(), "Изменить прическу [npc.namePos]."))
						:""));
			if(!BodyChanging.getTarget().isAbleToWearMakeup()) {
				sb.append("<div class='container-full-width' style='text-align:center;'>"
						+ UtilText.parse(BodyChanging.getTarget(),
								"<i>Так как тело [npc.namePos] состоит из "+BodyChanging.getTarget().getBodyMaterial().getName()+", [npc.sheIsFull] [style.colourBad(невозможно использовать любую косметику)]!</i>")
					+ "</div>");
				
			} else {
				sb.append(CharacterModificationUtils.getKatesDivCoveringsNew(
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
								false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Лак для ногтей на ногах", "Лак для ногтей на ногах используется для окрашивания и защиты ногтей на твоих [pc.feet].", true, true));
			}
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isInSex()) {
					return new Response(
							"Завершено",
							"Закончить использование набора косметики...",
							Main.sex.SEX_DIALOGUE){
						@Override
						public void effects(){
							Main.mainController.openInventory();
							Main.sex.setUsingItemText(UtilText.parse(BodyChanging.getTarget(),
								BodyChanging.getTarget().isPlayer()
									?"Вы используете арканный набор косметики на себе..."
									:"Вы используете арканный набор косметики на [npc.name]..."));
							Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
							Main.sex.setSexStarted(true);
						}
					};
					
				} else {
					return new ResponseEffectsOnly("Завершено", "Вернуться в экран инвентаря.") {
						@Override
						public void effects() {
							if(BodyChanging.getTarget().isPlayer()) {
								Main.mainController.openInventory();
							} else {
								Main.mainController.openInventory((NPC) BodyChanging.getTarget(), InventoryInteraction.FULL_MANAGEMENT);
							}
						}
					};
				}
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(Main.game.isInSex()) {
				return DialogueNodeType.NORMAL;
			}
			return DialogueNodeType.PHONE;
		}
		@Override
		public boolean isInventoryForcedDisabledInSex() {
			return true;
		}
	};
	
	// Condom use:
	
	// init for hook to condom and user/target:
	private static GameCharacter condomOwner;
	private static GameCharacter condomUser;
	private static GameCharacter condomTarget;
	private static AbstractFilledCondom usedCondom;
	private static String condomUseDescription;
	
	public static DialogueNode getUsedCondomSelectionDialogue(GameCharacter condomOwner, GameCharacter condomUser, GameCharacter condomTarget, AbstractFilledCondom usedCondom, String condomUseDescription) {
		boolean debug = false;
		
		MiscDialogue.condomOwner = condomOwner;
		MiscDialogue.condomUser = condomUser;
		MiscDialogue.condomTarget = condomTarget;
		MiscDialogue.usedCondom = usedCondom;
		MiscDialogue.condomUseDescription = condomUseDescription;
		
		if(debug) {
			System.out.println("UsedCondomInit:");
			System.out.println(UtilText.parse(condomOwner, "condomOwner: [npc.name]"));
			System.out.println(UtilText.parse(condomUser, "condomUser: [npc.name]"));
			System.out.println(UtilText.parse(condomTarget, "condomTarget: [npc.name]"));
		}
		
		return USED_CONDOM_SELECTION;
	}
	
	private static final DialogueNode USED_CONDOM_SELECTION = new DialogueNode("Использованный презерватив", "", true) {
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			FluidStored fs = usedCondom.getCum();
			
			sb.append("<p>");
				sb.append(condomUseDescription);
				sb.append(" Внутри презерватива:");
			sb.append("</p>");
			
			sb.append("<p style='text-align:center;'>");
			sb.append("<b>[units.fluid("+fs.getMillilitres()+")]</b> из");
			sb.append("<br/>");
				try {
					sb.append(UtilText.parse(fs.getFluidCharacter(), "<span style='color:"+fs.getFluidCharacter().getFemininity().getColour().toWebHexString()+";'>[npc.NamePos]</span>"));
					sb.append(" ");
					sb.append(" [style.colourCum("+fs.getFluid().getName(condomOwner)+")]");
				} catch(Exception ex) {
					String raceName = fs.getBody().getRace().getName(false);
					sb.append("<span style='color:"+fs.getBody().getRace().getColour().toWebHexString()+";'>");
					sb.append(UtilText.generateSingularDeterminer(raceName)+" "+ Util.capitaliseSentence(raceName)+"'s");
					sb.append("</span> ");
					sb.append(" [style.colourCum("+fs.getFluid().getName(null)+")]");
				}
				sb.append("<br/>");
				sb.append("У него вкус <span style='color:"+fs.getFluid().getFlavour().getColour().toWebHexString()+";'>"+fs.getFluid().getFlavour().getName()+"</span>");
				if(!fs.getFluid().getFluidModifiers().isEmpty()) {
					sb.append("<br/>");
					StringBuilder modifiersSB = new StringBuilder();
					modifiersSB.append("Оно ");
					List<String> modList = new ArrayList<>();
					for(FluidModifier mod : fs.getFluid().getFluidModifiers()) {
						modList.add("<span style='color:"+mod.getColour().toWebHexString()+";'>"+mod.getName()+"</span>");
					}
					modifiersSB.append(Util.stringsToStringList(modList, false));
					sb.append(modifiersSB.toString());
					sb.append(".");
				}
			sb.append("</p>");

			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append("Раз уж ты так обожаешь сперму, ты чувствуешь, как тебя безумно возбуждает сама мысль о том, чтобы использовать содержимое этого презерватива...");
				} else {
					sb.append("Сперма холодная и неаппетитная, что заставляет тебя усомниться в решении вскрыть презерватив...");
				}
			sb.append("</p>");
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				if(Main.game.isInSex()) {
					return new Response(
							"Назад",
							"Решить не использовать содержимое презерватива...",
							Main.sex.SEX_DIALOGUE){
						@Override
						public void effects(){
							condomOwner.addItem(usedCondom);
							Main.mainController.openInventory();
						}
					};
					
				} else {
					return new ResponseEffectsOnly("Назад", "Решить не использовать содержимое презерватива...") {
						@Override
						public void effects() {
							condomOwner.addItem(usedCondom);
							if(condomUser.isPlayer()) {
								if(InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.TRADING) {
									NPC trader = InventoryDialogue.getInventoryNPC();
									Main.game.restoreSavedContent(false);
									Main.mainController.openInventory(trader, InventoryInteraction.TRADING);
								} else {
									Main.mainController.openInventory();
								}
							} else {
								Main.mainController.openInventory((NPC) condomUser, InventoryInteraction.FULL_MANAGEMENT);
							}
						}
					};
				}
			}
			
			List<Response> responses = new ArrayList<>();
			
			if(!condomTarget.isSexAreaExposed(SexAreaOrifice.MOUTH)) {
				responses.add(new Response("Проглотить",
						UtilText.parse(condomTarget, "Рот [npc.NamePos] забит..."),
						null));
			} else {
				responses.add(getUsedCondomResponse("Проглотить",
							"Проглотить содержимое презерватива...",
							"Заставить [npc.name] проглотить содержимое презерватива",
							(SexAreaOrifice.MOUTH)));
			}
			
			if(!condomTarget.hasVagina()) {
				responses.add(new Response("Киска",
						UtilText.parse(condomTarget, "У [npc.Name] нет [npc.do]..."),
						null));
			} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.VAGINA)) {
				responses.add(new Response("Киска",
						UtilText.parse(condomTarget, "Киска [npc.NamePos] забита..."),
						null));
			} else {
				responses.add(getUsedCondomResponse("Киска",
							"Запихнуть содержимое презерватива в свою киску...",
							"Запихай содержимое презерватива в киску [npc.namePos]...",
							(SexAreaOrifice.VAGINA)));
			}

			if(Main.game.isAnalContentEnabled()) {
				if(!condomTarget.isSexAreaExposed(SexAreaOrifice.ANUS)) {
					responses.add(new Response("Анус",
							UtilText.parse(condomTarget, "Анус [npc.NamePos] забит..."),
							null));
				} else {
					responses.add(getUsedCondomResponse("Анус",
								"Запихай содержимое презерватива в свой анус...",
								"Запихай содержимое презерватива в анус [npc.namePos]...",
								(SexAreaOrifice.ANUS)));
				}
			}
			
			if(Main.game.isNipplePenEnabled()) {
				if(!condomTarget.isBreastFuckableNipplePenetration()) {
					responses.add(new Response("Соски",
							UtilText.parse(condomTarget, "У [npc.Name] [npc.do] нет подходящих для секса сосков, в которые можно было бы запихать содержимое презерватива..."),
							null));
				} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.NIPPLE)) {
					responses.add(new Response("Соски",
							UtilText.parse(condomTarget, "Соски [npc.NamePos] забиты..."),
							null));
				} else {
					responses.add(getUsedCondomResponse("Соски",
							"Запихай содержимое презерватива в свои соски...",
							"Запихай содержимое презерватива в соски [npc.namePos]...",
							(SexAreaOrifice.NIPPLE)));
				}
				
				if(Main.game.isUdderContentEnabled()) {
					if(!condomTarget.isBreastCrotchFuckableNipplePenetration()) {
						responses.add(new Response(condomTarget.getBreastCrotchShape()==BreastShape.UDDERS?"Вымя":"Лобковые соски",
								UtilText.parse(condomTarget, "У [npc.Name] [npc.do] нет подходящих для секса [npc.nipplesCrotch], в которые можно было бы запихать содержимое презерватива..."),
								null));
					} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.NIPPLE_CROTCH)) {
						responses.add(new Response(condomTarget.getBreastCrotchShape()==BreastShape.UDDERS?"Вымя":"Лобковые соски",
								UtilText.parse(condomTarget, "[npc.NamePos] [npc.nipplesCrotch] забиты..."),
								null));
					} else {
						responses.add(getUsedCondomResponse(condomTarget.getBreastCrotchShape()==BreastShape.UDDERS?"Вымя":"Лобковые соски",
								"Запихай содержимое презерватива в свою [npc.nipplesCrotch+]...",
								"Запихай содержимое презерватива в [npc.namePos] [npc.nipplesCrotch+]...",
								(SexAreaOrifice.NIPPLE_CROTCH)));
					}
				}
			}
			
			if(Main.game.isUrethraEnabled()) {
				if(!condomTarget.hasPenisIgnoreDildo() || !condomTarget.isUrethraFuckable()) {
					responses.add(new Response("Мочеиспускательный канал",
							condomTarget.hasPenisIgnoreDildo()
								?UtilText.parse(condomTarget, "У [npc.Name] [npc.do] нет подходящего для секса канала, куда можно было бы запихать содержимое презерватива...")
								:UtilText.parse(condomTarget, "У [npc.Name] [npc.do] нет пениса..."),
							null));
				} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.URETHRA_PENIS)) {
					responses.add(new Response("Мочеиспускательный канал",
							UtilText.parse(condomTarget, "Уретра [npc.NamePos] закупорена..."),
							null));
				} else {
					responses.add(getUsedCondomResponse("Мочеиспускательный канал",
							"Запихай содержимое презерватива в свою [npc.urethraPenis]...",
							"Запихай содержимое презерватива в [npc.namePos] [npc.urethraPenis+]...",
							(SexAreaOrifice.URETHRA_PENIS)));
				}
				
				if(!condomTarget.hasVagina() || !condomTarget.isVaginaUrethraFuckable()) {
					responses.add(new Response("Вагинальная уретра",
							condomTarget.hasVagina()
								?UtilText.parse(condomTarget, "У [npc.Name] [npc.do] нет подходящего для секса канала, куда можно было бы запихать содержимое презерватива...")
								:UtilText.parse(condomTarget, "У [npc.Name] нет [npc.do]..."),
							null));
				} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.URETHRA_VAGINA)) {
					responses.add(new Response("Вагинальная уретра",
							UtilText.parse(condomTarget, "Уретра [npc.NamePos] закупорена..."),
							null));
				} else {
					responses.add(getUsedCondomResponse("Вагинальная уретра",
							"Запихай содержимое презерватива в свою [npc.urethraVagina]...",
							"Запихай содержимое презерватива в [npc.namePos] [npc.urethraVagina+]...",
							(SexAreaOrifice.URETHRA_VAGINA)));
				}
			}

			if(!condomTarget.hasSpinneret()) {
				responses.add(new Response("Паутинный аппарат",
						UtilText.parse(condomTarget, "У [npc.Name] [npc.do] нет паутинного аппарата, в который можно было бы запихать содержимое презерватива..."),
						null));
			} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.SPINNERET)) {
				responses.add(new Response("Паутинный аппарат",
						UtilText.parse(condomTarget, "Паутинный аппарат [npc.NamePos] забит..."),
						null));
			} else {
				responses.add(getUsedCondomResponse("Паутинный аппарат",
							"Запихай содержимое презерватива в свой паутинный аппарат...",
							"Запихай содержимое презерватива в паутинный аппарат [npc.namePos]...",
							(SexAreaOrifice.SPINNERET)));
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index-1==i) {
					return responses.get(i);
				}
			}
			
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(Main.game.isInSex()) {
				return DialogueNodeType.NORMAL;
			}
			return DialogueNodeType.PHONE;
		}
		@Override
		public boolean isInventoryForcedDisabledInSex() {
			return true;
		}
	};
	
	private static Response getUsedCondomResponse(String title, String descriptionSelf, String description, SexAreaOrifice orifice) {
		if(Main.game.isInSex()) {
			return new Response(
					title,
					UtilText.parse(condomTarget, condomUser==condomTarget?descriptionSelf:description),
					Main.sex.SEX_DIALOGUE){
				@Override
				public void effects(){
					String condomEffectString = getAndApplyCondomUseDescription(orifice);
					condomTarget.calculateStatusEffects(0);
					Main.mainController.openInventory();
					Main.sex.setUsingItemText(
							condomEffectString
							+ Main.sex.calculateWetAreas(false));
					Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
					Main.sex.setSexStarted(true);
					
				}
			};
			
		} else {
			return new ResponseEffectsOnly(title, 
					UtilText.parse(condomTarget, condomUser==condomTarget?descriptionSelf:description)) {
				@Override
				public void effects() {
					String condomEffectString = getAndApplyCondomUseDescription(orifice);
					condomTarget.calculateStatusEffects(0);
					Main.game.getTextEndStringBuilder().append(condomEffectString);
					if(condomTarget.isPlayer()) {
						if(InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.TRADING) {
							NPC trader = InventoryDialogue.getInventoryNPC();
							Main.game.restoreSavedContent(false);
							Main.game.getTextEndStringBuilder().append(condomEffectString);
							Main.mainController.openInventory(trader, InventoryInteraction.TRADING);
						} else {
							Main.mainController.openInventory();
						}
					} else {
						Main.mainController.openInventory((NPC) condomTarget, InventoryInteraction.FULL_MANAGEMENT);
					}
				}
			};
		}
	}
	
	private static String getAndApplyCondomUseDescription(SexAreaOrifice orifice) {
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
		String targetNameSelfShe = condomTarget==condomUser?"[npc.she]":"[npc2.name]";
		String userNamePosSelfHer = condomTarget==condomUser?"[npc.her]":"[npc.namePos]";
		String cumName = "cum";
		try {
			cumName = usedCondom.getCum().getFluid().getName(usedCondom.getCum().getFluidCharacter());
		} catch(Exception ex) {
		}
		switch(orifice) {
			case ARMPITS:
			case ASS:
			case BREAST:
			case BREAST_CROTCH:
			case THIGHS:
				return "";// These orifices are never used
			// Internal orifices:
			case ANUS:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] не может не издать восторженный [npc.moan], когда "+targetNameSelfShe+" жадно [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.asshole+]. Отчаянно запихивая "+userNamePosSelfHer+" [npc.ass], наполненный содержимым презерватива, "+targetNameSelfShe+" только [npc2.verb(discard)] презерватив, когда [npc2.sheIs], что он полностью пуст."));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] [npc.verb(shudder)], как "+targetNameSelfShe+" [npc2.verb(push)] слизистую жидкость в [npc.her] [npc.asshole+], стараясь [npc.her] изо всех сил не думать о холоде "+cumName+" который теперь в [npc.her] [npc.ass], когда "+targetNameSelfShe+" [npc2.verb(throw)] теперь уже пустой презерватив на пол..."));
				}
				break;
			case MOUTH:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] не может не издать восторженный [npc.moan], когда [npc.she] жадно [npc.verb(gulp)] склизкую жидкость. Быстро высунув [npc.her] [npc.tongue], [npc.she] отчаянно [npc.verb(lick)] каждую последнюю каплю спермы; выбрасывая презерватив только тогда, когда [npc.sheIs] уверен, что он полностью пуст."));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] [npc.verb(scrunch)] [npc.her] [npc.eyes] заглатывал [npc.she] [npc.verb(gulp)] скользкую жидкость, стараясь [npc.her] изо всех сил не думать о том, что [npc.sheHas] только что сделал, когда "+targetNameSelfShe+" [npc2.verb(throw)] теперь уже пустой презерватив на пол..."));
				}
				break;
			case NIPPLE:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] не может не издать восторженный [npc.moan], когда "+targetNameSelfShe+" жадно [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.nipple+]. Отчаянно запихивая "+userNamePosSelfHer+" [npc.breasts], наполненное содержимым презерватива, "+targetNameSelfShe+" только [npc2.verb(discard)] презерватив, когда [npc2.sheIs], что он полностью пуст."));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] [npc.verb(shudder)], как "+targetNameSelfShe+" [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.nipple+], стараясь изо [npc.her] сил не думать о холоде, "+cumName+" который теперь в [npc.her] [npc.breasts], пока "+targetNameSelfShe+" [npc2.verb(throw)] теперь уже пустой презерватив на пол..."));
				}
				break;
			case NIPPLE_CROTCH:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] не может не издать восторженный [npc.moan], когда "+targetNameSelfShe+" жадно [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.nippleCrotch+]. Отчаянно запихивая "+userNamePosSelfHer+" [npc.crotchBoobs], наполненное содержимым презерватива, "+targetNameSelfShe+" только [npc2.verb(discard)] презерватив, когда [npc2.sheIs], что он полностью пуст."));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] [npc.verb(shudder)], как "+targetNameSelfShe+" [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.nippleCrotch+], стараясь изо [npc.her] сил не думать о холоде, "+cumName+" который теперь в [npc.her] [npc.crotchBoobs], пока "+targetNameSelfShe+" [npc2.verb(throw)] теперь уже пустой презерватив на пол..."));
				}
				break;
			case SPINNERET:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] не может не издать восторженный [npc.moan], когда "+targetNameSelfShe+" жадно [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.spinneret+]. Отчаянно запихивая "+userNamePosSelfHer+" похожее на паутину отверстие, полное содержимого презерватива, "+targetNameSelfShe+" только [npc2.verb(discard)] презерватив, когда [npc2.sheIs], что он полностью пуст."));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] [npc.verb(shudder)], как "+targetNameSelfShe+" [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.spinneret+], стараясь [npc.her] изо всех сил не думать о холоде "+cumName+" который теперь в паутиноподобном отверстии [npc.her], когда "+targetNameSelfShe+" [npc2.verb(throw)] теперь уже пустой презерватив на пол..."));
				}
				break;
			case URETHRA_PENIS:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] не может не издать восторженный [npc.moan], когда "+targetNameSelfShe+" с жадностью [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.urethraPenis+]. Отчаянно запихивая "+userNamePosSelfHer+" [npc.cock], наполненное содержимым презерватива, "+targetNameSelfShe+" только [npc2.verb(discard)] презерватив, когда [npc2.sheIs], что он полностью пуст."));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] [npc.verb(shudder)], как "+targetNameSelfShe+" [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.urethraPenis+], стараясь [npc.her] изо всех сил не думать о холоде "+cumName+" который теперь в [npc.her] [npc.cock], когда "+targetNameSelfShe+" [npc2.verb(throw)] теперь уже пустой презерватив на пол..."));
				}
				break;
			case URETHRA_VAGINA:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] не может не издать восторженный [npc.moan], когда "+targetNameSelfShe+" с жадностью [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.urethraVagina+]. Отчаянно запихивая "+userNamePosSelfHer+" [npc.pussy], наполненное содержимым презерватива, "+targetNameSelfShe+" только [npc2.verb(discard)] презерватив, когда [npc2.sheIs], что он полностью пуст."));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] [npc.verb(shudder)], как "+targetNameSelfShe+" [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.urethraVagina+], стараясь [npc.her] изо всех сил не думать о холоде "+cumName+" который теперь в [npc.her] [npc.pussy], когда "+targetNameSelfShe+" [npc2.verb(throw)] теперь уже пустой презерватив на пол..."));
				}
				break;
			case VAGINA:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] не может не издать восторженный [npc.moan], когда "+targetNameSelfShe+" жадно [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.pussy+]. Отчаянно запихивая "+userNamePosSelfHer+" [npc.pussy], наполненное содержимым презерватива, "+targetNameSelfShe+" только [npc2.verb(discard)] презерватив, когда [npc2.sheIs], что он полностью пуст."));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name] [npc.verb(shudder)], как "+targetNameSelfShe+" [npc2.verb(push)] склизкую жидкость в [npc.her] [npc.pussy+], стараясь [npc.her] изо всех сил не думать о холоде "+cumName+" который теперь в [npc.her] [npc.pussy], когда "+targetNameSelfShe+" [npc2.verb(throw)] теперь уже пустой презерватив на пол..."));
				}
				break;
		}
		sb.append("</p>");
		
		sb.append(condomTarget.ingestFluid(usedCondom.getCum(), orifice));
		
		return sb.toString();
	}
	
	
	// Dolls:
	
	public static int dollOption = 0;
	private static int[] dollCost = {200_000, 300_000, 600_000};
	public static int genitalsOption = 0;
	private static int[] genitalCost = {0, 20_000, 30_000};
	public static int ageOption = 0;
	private static int[] ageCost = {0, 25_000, 25_000, 25_000, 25_000, 25_000};
	public static int outfitOption = 0;
	private static String[] outfitId = {null, "innoxia_rainbow", "innoxia_kitty", "innoxia_maid"};
	private static int[] outfitCost = {0, 2_500, 5_000, 15_000};
	
	public static boolean barcodeRemoval = false;
	private static int barcodeCost = 5_000;
	public static boolean toySet = false;
	private static int toyCost = 15_000;
	public static boolean hair = false;
	private static int hairCost = 25_000;
	public static boolean deck = false;
	public static int deckCost = 1_000_000;
	
	public static boolean fucked = false;
	private static int fuckedCost = 1_000;
	
	private static AbstractSubspecies dollSubspecies = Subspecies.HUMAN;
	
	private static GameCharacter slaveToDollify = null;
	private static GameCharacter newDoll = null;
	
	public static int getDollBrochureCost() {
		int cost  = 0;
		
		cost += getDollCost(dollOption);
		cost += getGenitalCost(genitalsOption);
		cost += getAgeCost(ageOption);
		cost += getOutfitCost(outfitOption);
		
		cost += barcodeRemoval?getBarcodeCost():0;
		cost += toySet?getToyCost():0;
		cost += hair?getHairCost():0;
		cost += deck?getDeckCost():0;

		cost += fucked?getFuckedCost():0;
		
		if(Main.game.getDialogueFlags().hasFlag("innoxia_sex_shop_discount")) {
			cost *= 0.75f;
		} else if(Main.game.getDialogueFlags().hasFlag("innoxia_sex_shop_penalty")) {
			cost *= 2f;
		}
		
		return cost;
	}
	
	private static float getCostModifier() {
		if(slaveToDollify!=null) {
			return 0.05f;
		}
		return 1f;
	}
	
	private static int getDollCost(int option) {
		return (int) (dollCost[option] * getCostModifier());
	}
	
	private static int getGenitalCost(int option) {
		return (int) (genitalCost[option] * getCostModifier());
	}
	
	private static int getAgeCost(int option) {
		return (int) (ageCost[option] * getCostModifier());
	}
	
	private static int getOutfitCost(int option) {
		return outfitCost[option];
	}
	
	private static int getBarcodeCost() {
		return barcodeCost;
	}
	
	private static int getToyCost() {
		return toyCost;
	}
	
	private static int getHairCost() {
		return (int) (hairCost * getCostModifier());
	}
	
	private static int getDeckCost() {
		return deckCost;
	}
	
	private static int getFuckedCost() {
		return fuckedCost;
	}
	
	
	public static final DialogueNode DOLL_BROCHURE = new DialogueNode("Брошюра с куклами", "", true) {
		@Override
		public void applyPreParsingEffects() {
			dollOption = 0;
			genitalsOption = 0;
			ageOption = 0;
			outfitOption = 0;
			barcodeRemoval = false;
			toySet = false;
			hair = false;
			deck = false;
			fucked = false;
			dollSubspecies = Subspecies.HUMAN;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			if(slaveToDollify!=null) {
				sb.append(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_SLAVE", slaveToDollify));
			} else {
				sb.append(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE"));
			}
			
			sb.append(startWrapper("Модель"));
				sb.append(applyWrapperDiscounted("DD", "Наша стандартная модель; версия «DD» оснащена грудью размера DD и женственной фигурой.", PresetColour.GENERIC_GOOD, "DOLL_CORE_0", "Выбрать", dollOption==0, getDollCost(0), false));
				sb.append(applyWrapperDiscounted("HH", "Наша премиальная модель; версия «HH» оснащена грудью размера HH и более зрелой женской фигурой.", PresetColour.GENERIC_GOOD, "DOLL_CORE_1", "Выбрать", dollOption==1, getDollCost(1), false));
				sb.append(applyWrapperDiscounted("Антропо-спецвыпуск", "Наши специальные антропоморфные куклы представлены во всех мыслимых расах. Вы сможете выбрать предпочитаемую расу после заполнения этой формы.",
							PresetColour.GENERIC_EXCELLENT, "DOLL_CORE_2", "Выбрать", dollOption==2, getDollCost(2), false));
			sb.append(endWrapper());

			sb.append(startWrapper("Гениталии"));
				sb.append(applyWrapperDiscounted("Киска", "Вариант по умолчанию; искусственная киска.", PresetColour.FEMININE, "DOLL_GENITALS_0", "Выбрать", genitalsOption==0, getGenitalCost(0), true));
				sb.append(applyWrapperDiscounted("Хер", "Замените киску вашей куклы на член; члены наших кукол демонстрируют реалистичное поведение в расслабленном и эрегированном состоянии.",
						PresetColour.MASCULINE, "DOLL_GENITALS_1", "Выбрать", genitalsOption==1, getGenitalCost(1), true));
				sb.append(applyWrapperDiscounted("Оба", "Ведь если вы хотите получить лучшее из двух миров — подарите своей кукле и киску, и член.", PresetColour.ANDROGYNOUS, "DOLL_GENITALS_2", "Выбрать", genitalsOption==2, getGenitalCost(2), true));
			sb.append(endWrapper());

			if(Main.getProperties().hasValue(PropertyValue.ageContent)) {
				sb.append(startWrapper("Внешний возраст"));
					sb.append(applyWrapperDiscounted("18", "Наша стандартная модель выглядит как 18-летняя девушка.", PresetColour.AGE_TEENS, "DOLL_AGE_0", "Выбрать", ageOption==0, getAgeCost(0), true));
					sb.append(applyWrapperDiscounted("20's", "Если хотите, мы можем немного состарить вашу куклу.", PresetColour.AGE_TWENTIES, "DOLL_AGE_1", "Выбрать", ageOption==1, getAgeCost(1), true));
					sb.append(applyWrapperDiscounted("30's", "Или же, если предпочитаете, мы можем удвоить её возраст относительно стандартного.", PresetColour.AGE_THIRTIES, "DOLL_AGE_2", "Выбрать", ageOption==2, getAgeCost(2), true));
					sb.append(applyWrapperDiscounted("40's", "Хотите, чтобы ваша кукла выглядела как милфа? Мы можем и это.", PresetColour.AGE_FORTIES, "DOLL_AGE_3", "Выбрать", ageOption==3, getAgeCost(3), true));
					sb.append(applyWrapperDiscounted("50's", "Если вам нравятся тела постарше, мы можем продолжать...", PresetColour.AGE_FIFTIES, "DOLL_AGE_4", "Выбрать", ageOption==4, getAgeCost(4), true));
					sb.append(applyWrapperDiscounted("60's", "И сделать их *очень* зрелыми...", PresetColour.AGE_SIXTIES, "DOLL_AGE_5", "Выбрать", ageOption==5, getAgeCost(5), true));
				sb.append(endWrapper());
			}
			
			sb.append(startWrapper("Наряды"));
				sb.append(applyWrapper("Обнаженная", "Одежда в комплект к кукле не входит.", PresetColour.BASE_BLUE_STEEL, "DOLL_CLOTHING_0", "Выбрать", outfitOption==0, getOutfitCost(0), true));
				sb.append(applyWrapper("Радуга", "Ваша кукла прибудет в радужных аксессуарах.", PresetColour.BASE_INDIGO, "DOLL_CLOTHING_1", "Выбрать", outfitOption==1, getOutfitCost(1), true));
				sb.append(applyWrapper("Кошачье белье", "Ваша кукла прибудет в полном комплекте кошачьего белья.", PresetColour.BASE_PINK_LIGHT, "DOLL_CLOTHING_2", "Выбрать", outfitOption==2, getOutfitCost(2), true));
				sb.append(applyWrapper("Горничная", "Ваша кукла прибудет в полном костюме горничной.", PresetColour.BASE_PINK, "DOLL_CLOTHING_3", "Выбрать", outfitOption==3, getOutfitCost(3), true));
			sb.append(endWrapper());
			
			sb.append(startWrapper("Дополнительно"));
				sb.append(applyWrapper("Стереть штрихкод", "Перед доставкой мы удалим татуировку в виде штрихкода со лба вашей куклы.", PresetColour.GENERIC_MINOR_GOOD, "DOLL_BARCODE", "Убрать", barcodeRemoval, getBarcodeCost(), true));
				sb.append(applyWrapper("Набор игрушек", "Ваша кукла будет укомплектована набором игрушек на ваш выбор.", PresetColour.BASE_PINK, "DOLL_TOYS", "Добавить", toySet, getToyCost(), true));
				sb.append(applyWrapperDiscounted("Волосы", "Мы сделаем вашей кукле парик из синтетических волос.", PresetColour.BASE_BROWN_LIGHT, "DOLL_HAIR", "Добавить", hair, getHairCost(), true));
				sb.append(applyWrapperDisabled("D.E.C.K.",
						(Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE)
							?"[style.italicsDisabled(У вас уже есть D.E.C.K., и другой вам не нужен...)]"
							:"Приобрести D.E.C.K. вместе с куклой, что позволит вам полностью настраивать её в любое время."),
						PresetColour.GENERIC_EXCELLENT, "DOLL_DECK", "Купить", deck, getDeckCost(), true, Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE)));
			sb.append(endWrapper());

			sb.append(startWrapper("До доставки"));
				sb.append(applyWrapper("Разношенная", "Не нравится мысль о том, что твоя кукла — девственница? Саэллатрикс с радостью разбередит все её дырочки.", PresetColour.BASE_PINK_DEEP, "DOLL_FUCKED", "Трахнутый", fucked, getFuckedCost(), true));
			sb.append(endWrapper());
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()>=getDollBrochureCost()) {
					return new Response("Продолжить ("+UtilText.formatAsMoney(getDollBrochureCost(), "охват")+")",
							"Заполните форму и отдайте её Саэллатрикс.",
							dollOption==2
								?DOLL_BROCHURE_RACE_SELECTION
								:DOLL_BROCHURE_FINISHED) {
						@Override
						public void effects() {
							if(dollOption!=2) {
								Main.game.appendToTextStartStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_FINISHED_STANDARD"));
							}
						}
					};
					
				} else {
					return new Response("Продолжить ("+UtilText.formatAsMoneyUncoloured(getDollBrochureCost(), "охват")+")", "Тебе это не по карману...", null);
				}
				
			} else if(index==2) {
				return new Response("Назад", "В итоге отказаться от покупки куклы.", DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_counter"));
			}
			return null;
		}
	};

	public static final DialogueNode DOLL_BROCHURE_INTERNAL = new DialogueNode("Брошюра с куклами", "", true) {
		@Override
		public String getHeaderContent() {
			return DOLL_BROCHURE.getHeaderContent();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOLL_BROCHURE.getResponse(responseTab, index);
		}
	};
	
	private static List<AbstractSubspecies> getProhibitedSubspecies() {
		return Util.newArrayListOfValues(
				Subspecies.ANGEL, // No angels
				Subspecies.FOX_ASCENDANT, // They don't TF youko in case it pisses off the youko lilin
				Subspecies.FOX_ASCENDANT_ARCTIC,
				Subspecies.FOX_ASCENDANT_FENNEC,
				Subspecies.DOLL // Silly!
			);
	}
	
	private static List<AbstractSubspecies> dollCompatibleSubspecies = new ArrayList<>();
	private static List<AbstractSubspecies> getDollCompatibleSubspecies() {
		// check every subspecies for compatibility with dolls...
		if(dollCompatibleSubspecies.isEmpty()) {
			dollCompatibleSubspecies.addAll(Subspecies.getAllSubspecies());
			dollCompatibleSubspecies.removeIf(s->s.getRace()==Race.ELEMENTAL
					 || s.getRace()==Race.getRaceFromId("dsg_dragon") // They aren't able to capture dragons to TF
					 || s.getRace()==Race.DEMON // They don't TF demons in case it pisses off a lilin
					 || s.getRace()==Race.SLIME // This would make no sense
					 || s.getRace().isAbleToSelfTransform() // Catch to make sure special future races aren't added
					 || getProhibitedSubspecies().contains(s));
			NPC doll = new BasicDoll();
			for(AbstractSubspecies s : new ArrayList<>(dollCompatibleSubspecies)) {
				doll.setBody(doll.getGender(), s, RaceStage.GREATER, true);
				doll.setBodyMaterial(BodyMaterial.SILICONE);
				if(doll.getFleshSubspecies()!=s) {
					dollCompatibleSubspecies.remove(s);
				}
			}
		}
		return dollCompatibleSubspecies;
	}
	
	public static final DialogueNode DOLL_BROCHURE_RACE_SELECTION = new DialogueNode("Брошюра с куклами", "", true) {
		@Override
		public String getContent() {
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_RACE_SELECTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<AbstractSubspecies> availableSubspecies = getDollCompatibleSubspecies();//new ArrayList<>();
//			availableSubspecies.addAll(Subspecies.getAllSubspecies());
//			availableSubspecies.removeIf(s->s.getRace()==Race.ELEMENTAL
//					 || s.getRace()==Race.getRaceFromId("dsg_dragon") // They aren't able to capture dragons to TF
//					 || s.getRace()==Race.DEMON // They don't TF demons in case it pisses off a lilin
//					 || s.getRace()==Race.SLIME // This would make no sense
//					 || s.getRace().isAbleToSelfTransform() // Catch to make sure special future races aren't added
//					 || getProhibitedSubspecies().contains(s));
			
			if (index!=0 && index<availableSubspecies.size()+1) {
				AbstractSubspecies subspecies = availableSubspecies.get(index - 1);
				String name = subspecies.getSingularFemaleName(null);
				
				return new Response(
						Util.capitaliseSentence(name),
						"Выбрать "+UtilText.generateSingularDeterminer(name)+" "+name+" подвид для твоей куклы.",
						DOLL_BROCHURE_FINISHED){
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_FINISHED_SPECIAL"));
						dollSubspecies = subspecies;
					}
				};
				
			} else if (index == 0) {
				return new Response("Назад", "Вернуться на предыдущую страницу.", DOLL_BROCHURE_INTERNAL);
			}
			return null;
		}
	};
	
	public static final DialogueNode DOLL_BROCHURE_FINISHED = new DialogueNode("Брошюра с куклами", "", true) {
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(dollOption), true);
			UtilText.addSpecialParsingString(dollSubspecies==null?"":dollSubspecies.getSingularFemaleName(null), false);
			UtilText.addSpecialParsingString(String.valueOf(genitalsOption), false);
			UtilText.addSpecialParsingString(String.valueOf(ageOption), false);
			UtilText.addSpecialParsingString(String.valueOf(outfitOption), false);
			UtilText.addSpecialParsingString(String.valueOf(toySet), false);
			UtilText.addSpecialParsingString(String.valueOf(barcodeRemoval), false);
			UtilText.addSpecialParsingString(String.valueOf(hair), false);
			UtilText.addSpecialParsingString(String.valueOf(deck), false);
			UtilText.addSpecialParsingString(String.valueOf(fucked), false);
			UtilText.addSpecialParsingString(Util.intToString(getDollBrochureCost()), false);
			if(slaveToDollify==null) {
				return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_FINISHED");
			} else {
				return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_FINISHED_SLAVE");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Заплатить ("+UtilText.formatAsMoney(getDollBrochureCost(), "охват")+")",
						"Заплатить "+UtilText.formatAsMoney(getDollBrochureCost(), "охват")+" чтобы купить куклу с выбранными вами настройками.",
						slaveToDollify==null
							?DOLL_BROCHURE_END
							:DOLL_BROCHURE_END_SLAVE) {
					@Override
					public void effects() {
						Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementMoney(-getDollBrochureCost()));
						Main.game.getNpc(Saellatrix.class).setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop_factory"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_factory_doll_machine"));
					}
				};
				
			} else if (index == 2) {
				return new Response("Назад", "Вернуться к брошюре.", DOLL_BROCHURE_INTERNAL);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DOLL_BROCHURE_END = new DialogueNode("Брошюра с куклами", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(deck) {
				Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.DOLL_CONSOLE)));
			}
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(deck), true);
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать",
						"Ждать возвращения Саэллатрикс с вашей новой куклой.",
						DOLL_BROCHURE_END_FINAL) {
					@Override
					public void effects() {
						initDoll();
						Main.game.getNpc(Saellatrix.class).setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_dolls"));
						Main.game.getPlayer().setLocation(Main.game.getNpc(Saellatrix.class));
						newDoll.setLocation(Main.game.getNpc(Saellatrix.class), true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DOLL_BROCHURE_END_FINAL = new DialogueNode("Брошюра с куклами", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.appendToTextEndStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_NEW_DOLL_END", newDoll));
		}
		@Override
		public int getSecondsPassed() {
			return 60 * 15;
		}
		@Override
		public String getContent() {
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_END_FINAL", newDoll);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						"Вы получили новую куклу!",
						DOLL_BROCHURE_NEW_DOLL_END);
			}
			return null;
		}
	};
	
	
	public static final DialogueNode DOLL_BROCHURE_END_SLAVE = new DialogueNode("Брошюра с куклами", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(deck) {
				Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.DOLL_CONSOLE)));
			}
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(deck), true);
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_END_SLAVE", slaveToDollify);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Ждать",
						"Ждать возвращения Саэллатрикс с вашей новой куклой.",
						DOLL_BROCHURE_END_SLAVE_FINAL) {
					@Override
					public void effects() {
						initDoll();
						Main.game.getNpc(Saellatrix.class).setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_dolls"));
						Main.game.getPlayer().setLocation(Main.game.getNpc(Saellatrix.class));
						newDoll.setLocation(Main.game.getNpc(Saellatrix.class), true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOLL_BROCHURE_END_SLAVE_FINAL = new DialogueNode("Брошюра с куклами", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.appendToTextEndStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_NEW_DOLL_END", newDoll));
		}
		@Override
		public int getSecondsPassed() {
			return 60 * 30;
		}
		@Override
		public String getContent() {
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_END_SLAVE_FINAL", newDoll);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Продолжить",
						"Вы получили новую куклу!",
						DOLL_BROCHURE_NEW_DOLL_END);
			}
			return null;
		}
	};

	public static final DialogueNode DOLL_BROCHURE_NEW_DOLL_END = new DialogueNode("Брошюра с куклами", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.appendToTextEndStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_NEW_DOLL_END", newDoll));
			newDoll = null;
			Main.game.getNpc(Saellatrix.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 60 * 1;
		}
		@Override
		public String getContent() {
			return Main.game.getDefaultDialogue().getContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_dolls").getResponse(responseTab, index);
		}
	};

	private static void initDoll() {
		NPC doll;
		if(slaveToDollify!=null) {
			Main.game.getPlayer().removeSlave(slaveToDollify);
			slaveToDollify.unequipAllClothing(slaveToDollify, true, true);
			doll = (NPC) slaveToDollify;
		} else {
			doll = new BasicDoll();
		}
		
		Gender gender = Gender.F_V_B_FEMALE;
		if(genitalsOption==1) {
			gender = Gender.F_P_B_SHEMALE;
		} else if(genitalsOption==2) {
			gender = Gender.F_P_V_B_FUTANARI;
		}

		if(dollOption!=2) {
			dollSubspecies = Subspecies.HUMAN;
		}
		
		doll.setBody(gender, dollSubspecies, RaceStage.GREATER, true);
		doll.setBodyMaterial(BodyMaterial.SILICONE); // Birthday is set in here
		if(slaveToDollify==null) {
			doll.setBirthday(doll.getBirthday().minusDays(Util.random.nextInt(61))); // Creation date is 0-60 days before purchase
		}
		if(ageOption==1) {
			doll.setAgeAppearanceAbsolute(25);
		} else if(ageOption==2) {
			doll.setAgeAppearanceAbsolute(35);
		} else if(ageOption==3) {
			doll.setAgeAppearanceAbsolute(45);
		} else if(ageOption==4) {
			doll.setAgeAppearanceAbsolute(55);
		} else if(ageOption==5) {
			doll.setAgeAppearanceAbsolute(65);
		}
		doll.setPlayerKnowsName(true);

		int dollCount = ((Saellatrix)Main.game.getNpc(Saellatrix.class)).getDollsSold();
		String dollNumber = "#"+String.format("%05d", dollCount);
		
		if(slaveToDollify==null) {
			doll.setName("Кукла");
			((Saellatrix)Main.game.getNpc(Saellatrix.class)).incrementDollsSold(1);
			doll.setSurname(dollNumber);
		}
		
		doll.addTattoo(InventorySlot.EYES,
				new Tattoo("innoxia_property_barcode",
						PresetColour.CLOTHING_WHITE,
						false,
						new TattooWriting(
								dollNumber,
								PresetColour.CLOTHING_WHITE,
								false),
						null));

		if(slaveToDollify!=null) {
			doll.setDescription("[npc.Name] когда-то была одной из ваших рабынь, но вы навсегда превратили [npc.herHim] в послушную секс-куклу в «Роскоши Ловьенн».");
		} else {
			doll.setDescription("Эта кукла была создана в «Роскоши Ловьенн»,");
		}
		
		doll.setPetName(Main.game.getPlayer(), "master");
		
		doll.setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_dolls"), true);


		if(slaveToDollify!=null) {
			// Reset all affections:
			for(NPC npc : Main.game.getAllNPCs()) {
				if(npc.getAffectionMap().containsKey(slaveToDollify.getId())) {
					npc.setAffection(slaveToDollify, 0);
				}
			}
		}
		
		if(dollOption==1) {
			doll.setBreastSize(CupSize.HH);
			doll.setHipSize(HipSize.FIVE_VERY_WIDE);
			doll.setAssSize(AssSize.FIVE_HUGE);
		}
		
		doll.unequipAllClothingIntoVoid(true, true);
		doll.setMoney(0);
		
		if(outfitOption!=0) {
			String id = outfitId[outfitOption];
			AbstractSetBonus sb = SetBonus.getSetBonusFromId(id);

			if(ClothingType.getAllClothingInSet(sb)!=null) {
				for (AbstractClothingType ct : ClothingType.getAllClothingInSet(sb)) {
					AbstractClothing clothing = Main.game.getItemGen().generateClothing(ct, false);
					for(int i=0; i<ct.getColourReplacements().size(); i++) {
						ColourReplacement cr = ct.getColourReplacement(i);
						clothing.setColour(i, cr.getFirstOfDefaultColours());
					}
					doll.equipClothingFromNowhere(clothing, true, doll);
				}
			}
			if(outfitId[outfitOption]=="innoxia_rainbow") { // Rainbow dolls come with heels
				doll.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_RED, PresetColour.CLOTHING_BLUE, PresetColour.CLOTHING_YELLOW, false), true, doll);
			}
		}
		
		if(barcodeRemoval) {
			doll.clearTattoos();
		}
		
		if(toySet) {
			if(doll.hasVagina()) {
				doll.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_dildos_realistic_dildo", PresetColour.CLOTHING_PINK_LIGHT, false), true, doll);
			}
			if(doll.hasPenis()) {
				AbstractClothing cage = Main.game.getItemGen().generateClothing("innoxia_bdsm_ornate_chastity_cage", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_BRASS, false);
				cage.setSealed(false);
				doll.equipClothingFromNowhere(cage, true, doll);
			}
			doll.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_heart", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, null, false), true, doll);
			AbstractClothing gag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK_LIGHT, false);
			gag.setSealed(false);
			doll.equipClothingFromNowhere(gag, true, doll);
			AbstractClothing blindfold = Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", PresetColour.CLOTHING_PINK_LIGHT, false);
			blindfold.setSealed(false);
			doll.equipClothingFromNowhere(blindfold, true, doll);
		}
		
		if(hair) {
			doll.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			doll.setHairStyle(HairStyle.STRAIGHT);
		}

		if(fucked) {
			doll.completeVirginityLoss();
			Saellatrix saellatrix = ((Saellatrix)Main.game.getNpc(Saellatrix.class));
//			if(doll.hasPenis()) {
//				doll.setVirginityLoss(new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), saellatrix, "before being delivered to you");
//			}
			if(doll.hasVagina()) {
				doll.setVirginityLoss(new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), saellatrix, "прежде чем её доставили вам");
			}
			doll.setVirginityLoss(new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), saellatrix, "прежде чем её доставили вам");
			doll.setVirginityLoss(new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), saellatrix, "прежде чем её доставили вам");
			doll.setVirginityLoss(new SexType(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS), saellatrix, "прежде чем её доставили вам");
		}
		
		try {
			if(slaveToDollify==null) {
				Main.game.addNPC(doll, false);
			}
			Main.game.getPlayer().addSlave(doll);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		newDoll = doll;
		slaveToDollify = null; // Reset to null so standard DOLL_BROCHURE works
	}
	
	private static String startWrapper(String title) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='cosmetics-inner-container' style='margin:1% 1%; width:98%; padding:1%; box-sizing:border-box; position:relative;'>");
		sb.append("<b>"+title+"</b>");
		
		return sb.toString();
	}
	
	private static String endWrapper() {
		return "</div>";
	}
	
	private static String applyWrapper(String title, String description, Colour buttonColour, String buttonId, String buttonText, boolean buttonActive, int cost, boolean isCostAdditional) {
		return applyWrapper(title, description, buttonColour, buttonId, buttonText, buttonActive, cost, isCostAdditional, false, false);
	}

	private static String applyWrapperDiscounted(String title, String description, Colour buttonColour, String buttonId, String buttonText, boolean buttonActive, int cost, boolean isCostAdditional) {
		return applyWrapper(title, description, buttonColour, buttonId, buttonText, buttonActive, cost, isCostAdditional, false, slaveToDollify!=null);
	}
	
	private static String applyWrapperDisabled(String title, String description, Colour buttonColour, String buttonId, String buttonText, boolean buttonActive, int cost, boolean isCostAdditional, boolean isDisabled) {
		return applyWrapper(title, description, buttonColour, buttonId, buttonText, buttonActive, cost, isCostAdditional, isDisabled, false);
	}
	
	private static String applyWrapper(String title, String description, Colour buttonColour, String buttonId, String buttonText, boolean buttonActive, int cost, boolean isCostAdditional, boolean isDisabled, boolean isDiscounted) {
		StringBuilder sb = new StringBuilder();

		String border = "граница: 1px сплошная "+(buttonActive?buttonColour:PresetColour.BASE_GREY_DARK).toWebHexString()+"55;";
		String background = "";//buttonActive?"background:#555;":"";
		String buttonStyle= "style='min-width:0; width:calc(100% - 8px); padding:4px; margin:0;'";
		
		buttonText = buttonActive?"&#10003;":"-";
		
		sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:2px 0 2px 0; text-align:center; "+border+" "+background+"'>");
			sb.append("<div class='container-full-width' style='width:20%; padding:0; margin:0;'>");
				sb.append(isDisabled?"[style.boldDisabled(":"[style.bold(");
					sb.append(title);
				sb.append(")]");
			sb.append("</div>");
			
			sb.append("<div class='container-full-width' style='width:55%; padding:0; margin:0;'>");
				sb.append("<i>");
					sb.append(description);
				sb.append("</i>");
			sb.append("</div>");

			sb.append("<div class='container-full-width' style='width:5%; padding:0; margin:0;'>");
				if(isDisabled) {
					sb.append(
							"<div class='cosmetics-button disabled' "+buttonStyle+">"
								+ buttonText
							+ "</div>");
					
				} else if(buttonActive) {
					sb.append(
							"<div id='"+buttonId+"' class='cosmetics-button active' "+buttonStyle+"><span style='color:"+buttonColour.toWebHexString()+";'>"+buttonText+"</span>"
							+ "</div>");
					
				} else {
					sb.append(
							"<div id='"+buttonId+"' class='cosmetics-button' "+buttonStyle+"><span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+buttonText+"</span>"
							+ "</div>");
				}
			sb.append("</div>");
			
			sb.append("<div class='container-full-width' style='width:20%; padding:0; margin:0;'>");
				if(isDiscounted) {
					sb.append("<span style='text-decoration: line-through;'>[style.colourDisabled("+(isCostAdditional?"+":"")+UtilText.formatAsMoneyUncoloured((int) (cost * (1/getCostModifier())), "охват")+")]</span><br/>");
				}
				if(buttonActive) {
					sb.append((isCostAdditional?"+":"")+UtilText.formatAsMoney(cost, "охват"));
				} else {
					sb.append("[style.colourDisabled("+(isCostAdditional?"+":"")+UtilText.formatAsMoneyUncoloured(cost, "охват")+")]");
				}
			sb.append("</div>");
		
		sb.append("</div>");
		return sb.toString();
	}
	
	private static GameCharacter getDollTarget() {
		if(Main.game.getNpc(Angelixx.class).isDoll()) {
			return Main.game.getNpc(Angelixx.class);
		} else {
			return Main.game.getNpc(Fiammetta.class);
		}
	}

	public static final DialogueNode SAELLATRIX_DOLL_CORE = new DialogueNode("Основа", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getDollTarget());
			SuccubisSecrets.initCoveringsMap(getDollTarget());
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ UtilText.parse(getDollTarget(), "<i>Подключив кабель D.E.C.K. к порту на затылке [npc.namePos], вы сможете изменить цвет тела [npc.her]...</i>")
				+ "</div>");
				
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();

				// Remove vagina, anus, fluids:
				if(!Collections.disjoint(bct.getAllPatterns().keySet(), Util.newArrayListOfValues(CoveringPattern.ORIFICE_VAGINA, CoveringPattern.ORIFICE_ANUS, CoveringPattern.FLUID))) {
					continue;
				}
				
				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(getDollTarget(), bct, entry.getValue().getValue());

				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(getDollTarget(), titleDescription.getValue()),
						true,
						true));
			}
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(false, getDollTarget().getEyeType().getRace(), getDollTarget().getCovering(getDollTarget().getEyeCovering()).getType(),
					"Цвет радужки",
					UtilText.parse(getDollTarget(), "Цвет и узор радужки глаз [npc.namePos]."),
					true, true));

			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getDollTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(),
					"Цвет зрачков",
					UtilText.parse(getDollTarget(), "Цвет и узор зрачков [npc.namePos]."),
					true, true));

			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getDollTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(),
					"Цвет склер",
					UtilText.parse(getDollTarget(), "Цвет и узор склер [npc.namePos]."),
					true, true));
			
			return UtilText.parse(getDollTarget(), UtilText.nodeContentSB.toString());
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Angelixx.class).isDoll()) {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_angelixx_doll").getResponse(responseTab, index);
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_fia_doll").getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode SAELLATRIX_DOLL_PUSSY = new DialogueNode("Киска", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getDollTarget());
			SuccubisSecrets.initCoveringsMap(getDollTarget());
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
				UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformVaginaSquirterDiv()
						+CharacterModificationUtils.getSelfTransformLabiaSizeDiv()
					+"</div>"
					+"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformVaginaModifiersDiv()
						+CharacterModificationUtils.getSelfTransformVaginaWetnessDiv()
					+"</div>"
					+"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformVaginaCapacityDiv()
						+CharacterModificationUtils.getSelfTransformVaginaDepthDiv()
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
					+"</div>");
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(false,
					getDollTarget().getVaginaRace(),
					getDollTarget().getCovering(BodyCoveringType.VAGINA).getType(),
					"Цвет влагалища",
					UtilText.parse(getDollTarget(), "Изменить цвет [npc.namePos] вагины."),
					true, true));

			return UtilText.parse(getDollTarget(), UtilText.nodeContentSB.toString());
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Angelixx.class).isDoll()) {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_angelixx_doll").getResponse(responseTab, index);
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_fia_doll").getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode SAELLATRIX_DOLL_ASS = new DialogueNode("Задница", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getDollTarget());
			SuccubisSecrets.initCoveringsMap(getDollTarget());
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAnusModifiersDiv()
						+ CharacterModificationUtils.getSelfTransformAnusWetnessDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAnusCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformAnusDepthDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							getDollTarget().getAssRace(),
							getDollTarget().getCovering(BodyCoveringType.ANUS).getType(),
							"Цвет ануса", 
							UtilText.parse(getDollTarget(), "Изменить цвет ануса [npc.namePos]."),
							true, true));
				
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Angelixx.class).isDoll()) {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_angelixx_doll").getResponse(responseTab, index);
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_fia_doll").getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode SLAVE_DOLLIFICATION = new DialogueNode("Окукливание рабыни", "", true) {
		@Override
		public void applyPreParsingEffects() {
			slaveToDollify = null;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "SLAVE_DOLLIFICATION"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> slaveResponses = new ArrayList<>();
			for(GameCharacter slave : Main.game.getPlayer().getSlavesOwnedAsCharacters()) {
				if(slave.isDoll() || slave.isUnique()) {
					continue;
				}
				slaveResponses.add(new Response(
						"<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(slave, "[npc.Name]")+"</span>",
						UtilText.parse(slave,
							"Скажите Саэллатрикс, что вы хотите превратить [npc.name], вашу [npc.raceFull(true)] рабыню, в куклу.<br/>[style.italicsTerrible(Это необратимая, перманентная трансформация!)]"),
						DOLL_BROCHURE) {
					@Override
					public void effects() {
						slaveToDollify = slave;
					}
				});
			}
			
			if(index==0) {
				return new Response("Назад", "В итоге решить не превращать своих рабынь в кукол.", DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_counter")) {
					@Override
					public void effects() {
						slaveToDollify = null;
					}
				};
			}
			for(int i=0; i<slaveResponses.size(); i++) {
				if(index==i+1) {
					return slaveResponses.get(i);
				}
			}
			
			return null;
		}
	};
}
