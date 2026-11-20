package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.LegConfigurationAffinity;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.BodyPartClothingBlock;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.1
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractLegType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;
	
	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private Map<LegConfiguration, FootStructure> defaultFootStructure;
	private AbstractFootType footType;
	
	private String determiner;
	
	private String name;
	private String namePlural;
	
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;
	
	private List<String> footDescriptorsMasculine;
	private List<String> footDescriptorsFeminine;
	
	private List<String> toeDescriptorsMasculine;
	private List<String> toeDescriptorsFeminine;
	
	private String legTransformationDescription;
	private String legBodyDescription;
	
	private List<LegConfiguration> allowedLegConfigurations;
	
	private boolean spinneret;

	private AbstractTentacleType tentacleType;
	private int tentacleCount;
	
	/**
	 * @param coveringType What covers this leg type (i.e skin/fur/feather type).
	 * @param race What race has this leg type.
	 * @param defaultFootStructure The default foot structure for this leg type.
	 * @param footType The type of foot attached to this leg type.
	 * @param determiner Will usually be "a pair of".
	 * @param name The singular name of the leg. This will usually just be "leg".
	 * @param namePlural The plural name of the leg. This will usually just be "legs".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this leg type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this leg type.
	 * @param footDescriptorsMasculine The <b>additional</b> descriptors that are applied to a masculine form of the associated foot type. (Base descriptors are in the AbstractFootType class.)
	 * @param footDescriptorsFeminine The <b>additional</b> descriptors that are applied to a feminine form of the associated foot type. (Base descriptors are in the AbstractFootType class.)
	 * @param toeDescriptorsMasculine The <b>additional</b> descriptors that are applied to a masculine form of the associated toe type. (Base descriptors are in the AbstractFootType class.)
	 * @param toeDescriptorsFeminine The <b>additional</b> descriptors that are applied to a feminine form of the associated toe type. (Base descriptors are in the AbstractFootType class.)
	 * @param legTransformationDescription A paragraph describing a character's legs transforming into this leg type. Parsing assumes that the character already has this leg type and associated skin covering.
	 * @param legBodyDescription A sentence or two to describe this leg type, as seen in the character view screen. It should follow the same format as all of the other entries in the LegType class.
	 * @param allowedLegConfigurations A list of LegConfigurations that are allowed for this LegType.
	 * @param spinneret true if this leg type has a spinneret.
	 */
	public AbstractLegType(AbstractBodyCoveringType coveringType,
			AbstractRace race,
			FootStructure defaultFootStructure,
			AbstractFootType footType,
			String determiner,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			List<String> footDescriptorsMasculine,
			List<String> footDescriptorsFeminine,
			List<String> toeDescriptorsMasculine,
			List<String> toeDescriptorsFeminine,
			String legTransformationDescription,
			String legBodyDescription,
			List<LegConfiguration> allowedLegConfigurations,
			boolean spinneret) {
		
		this.coveringType = coveringType;
		this.race = race;

		this.transformationName = null; // Use default race transformation name
		
		this.defaultFootStructure = Util.newHashMapOfValues(new Value<>(LegConfiguration.BIPEDAL, defaultFootStructure));
		this.footType = footType;
		
		this.allowedLegConfigurations = allowedLegConfigurations;
		
		this.determiner = determiner;
		
		this.name = name;
		this.namePlural = namePlural;
		
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.footDescriptorsMasculine = footDescriptorsMasculine;
		this.footDescriptorsFeminine = footDescriptorsFeminine;
		
		this.toeDescriptorsMasculine = toeDescriptorsMasculine;
		this.toeDescriptorsFeminine = toeDescriptorsFeminine;
		
		this.legTransformationDescription = legTransformationDescription;
		this.legBodyDescription = legBodyDescription;
		
		this.spinneret = spinneret;
		
		this.tentacleType = TentacleType.NONE;
		this.tentacleCount = 0;
	}
	
	public AbstractLegType(File XMLFile, String author, boolean mod) {
		if (XMLFile.exists()) {
			try {
				Document doc = Main.getDocBuilder().parse(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile);

				this.mod = mod;
				this.fromExternalFile = true;
				
				this.race = Race.getRaceFromId(coreElement.getMandatoryFirstOf("race").getTextContent());
				this.coveringType = BodyCoveringType.getBodyCoveringTypeFromId(coreElement.getMandatoryFirstOf("coveringType").getTextContent());

				this.transformationName = coreElement.getMandatoryFirstOf("transformationName").getTextContent();
				
				this.defaultFootStructure = new HashMap<>();
				FootStructure defaultStructure = FootStructure.valueOf(coreElement.getMandatoryFirstOf("defaultFootStructure").getTextContent());
				for(LegConfiguration config : LegConfiguration.values()) {
					this.defaultFootStructure.put(config, defaultStructure);
				}
				for(Element e : coreElement.getAllOf("additionalFootStructure")) {
					this.defaultFootStructure.put(LegConfiguration.valueOf(e.getAttribute("legConfiguration")), FootStructure.valueOf(e.getTextContent()));
				}
				
				this.footType = FootType.getFootTypeFromId(coreElement.getMandatoryFirstOf("footType").getTextContent());
				this.spinneret = Boolean.valueOf(coreElement.getMandatoryFirstOf("spinneret").getTextContent());
				
				this.tentacleType = TentacleType.getTentacleTypeFromId(coreElement.getMandatoryFirstOf("tentacleType").getTextContent());
				this.tentacleCount = Integer.valueOf(coreElement.getMandatoryFirstOf("tentacleCount").getTextContent());
				
				this.allowedLegConfigurations = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("allowedLegConfigurations").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("allowedLegConfigurations").getAllOf("configuration")) {
						allowedLegConfigurations.add(LegConfiguration.valueOf(e.getTextContent()));
					}
				}
				if(allowedLegConfigurations.isEmpty()) {
					allowedLegConfigurations.add(LegConfiguration.BIPEDAL);
				}
				
				this.name = coreElement.getMandatoryFirstOf("name").getTextContent();
				this.namePlural = coreElement.getMandatoryFirstOf("namePlural").getTextContent();

				this.descriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsMasculine").getAllOf("descriptor")) {
						descriptorsMasculine.add(e.getTextContent());
					}
				}
				this.descriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("descriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("descriptorsFeminine").getAllOf("descriptor")) {
						descriptorsFeminine.add(e.getTextContent());
					}
				}

				this.footDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("footDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("footDescriptorsMasculine").getAllOf("descriptor")) {
						footDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.footDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("footDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("footDescriptorsFeminine").getAllOf("descriptor")) {
						footDescriptorsFeminine.add(e.getTextContent());
					}
				}

				this.toeDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("toeDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("toeDescriptorsMasculine").getAllOf("descriptor")) {
						toeDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.toeDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("toeDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("toeDescriptorsFeminine").getAllOf("descriptor")) {
						toeDescriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.legTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.legBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractLegType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}


	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc==null) {
			return determiner;
		}
		if(gc.getLegCount()==1) {
			return "";
		} else if(gc.getLegCount()==2) {
			return "пара";
		}
		return Util.intToString(gc.getLegCount());
	}

	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		if(gc==null) {
			return true;
		}
		return gc.getLegCount()>1;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(gc!=null) {
			switch(gc.getLegConfiguration()) {
				case ARACHNID:
				case AVIAN:
				case BIPEDAL:
				case QUADRUPEDAL:
				case WINGED_BIPED:
					return "нога";
				case CEPHALOPOD:
					return "щупальце";
				case TAIL:
				case TAIL_LONG:
					return "хвост";
			}
		}
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(gc!=null) {
			switch(gc.getLegConfiguration()) {
				case ARACHNID:
				case AVIAN:
				case BIPEDAL:
				case QUADRUPEDAL:
				case WINGED_BIPED:
					return "ноги";
				case CEPHALOPOD:
					return "щупальца";
				case TAIL:
				case TAIL_LONG:
					return "хвосты";
			}
		}
		return namePlural;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if(gc!=null && !gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsMasculine);
		} else {
			return Util.randomItemFrom(descriptorsFeminine);
		}
	}

	@Override
	public AbstractBodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	@Override
	public AbstractRace getRace() {
		return race;
	}

	@Override
	public TFModifier getTFModifier() {
		return getTFTypeModifier(LegType.getLegTypes(race));
	}

	public AbstractFootType getFootType() {
		return footType;
	}

	public FootStructure getDefaultFootStructure(LegConfiguration legConfiguration) {
		if(!defaultFootStructure.containsKey(legConfiguration)) {
			return defaultFootStructure.get(LegConfiguration.BIPEDAL);
		}
		return defaultFootStructure.get(legConfiguration);
	}
	
	
	public String getFootNameSingular(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				return FootType.TENTACLE.getFootName();
			case TAIL:
				return "fin";
			case TAIL_LONG:
				return "хвост";
		}
		return this.getFootType().getFootName();
	}
	
	public String getFootNamePlural(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				return FootType.TENTACLE.getFootNamePlural();
			case TAIL:
				return "плавники";
			case TAIL_LONG:
				return "хвост";
		}
		return this.getFootType().getFootNamePlural();
	}

	public String getFootDescriptor(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				if (gc.isFeminine()) {
					return Util.randomItemFrom(Util.mergeLists(FootType.TENTACLE.getFootDescriptorsFeminine(), footDescriptorsFeminine));
				} else {
					return Util.randomItemFrom(Util.mergeLists(FootType.TENTACLE.getFootDescriptorsMasculine(), footDescriptorsMasculine));
				}
			case TAIL:
			case TAIL_LONG:
				if (gc.isFeminine()) {
					return Util.randomItemFrom(Util.newArrayListOfValues("masculine","strong"));
				} else {
					return Util.randomItemFrom(Util.newArrayListOfValues("feminine", "strong", "slender"));
				}
		}
		if (gc.isFeminine()) {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getFootDescriptorsFeminine(), footDescriptorsFeminine));
		} else {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getFootDescriptorsMasculine(), footDescriptorsMasculine));
		}
	}
	

	public String getToeNameSingular(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				return FootType.TENTACLE.getToeSingularName();
			case TAIL:
				return "кончик";
			case TAIL_LONG:
				return "кончик";
		}
		return this.getFootType().getToeSingularName();
	}
	
	public String getToeNamePlural(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				return FootType.TENTACLE.getToePluralName();
			case TAIL:
				return "кончик";
			case TAIL_LONG:
				return "кончик";
		}
		return this.getFootType().getToePluralName();
	}

	public String getToeDescriptor(GameCharacter gc) {
		switch(gc.getLegConfiguration()) {
			case ARACHNID:
			case AVIAN:
			case BIPEDAL:
			case QUADRUPEDAL:
			case WINGED_BIPED:
				break;
			case CEPHALOPOD:
				if (gc.isFeminine()) {
					return Util.randomItemFrom(Util.mergeLists(FootType.TENTACLE.getToeDescriptorsFeminine(), footDescriptorsFeminine));
				} else {
					return Util.randomItemFrom(Util.mergeLists(FootType.TENTACLE.getToeDescriptorsFeminine(), footDescriptorsMasculine));
				}
			case TAIL:
			case TAIL_LONG:
				if (gc.isFeminine()) {
					return Util.randomItemFrom(Util.newArrayListOfValues("masculine","strong"));
				} else {
					return Util.randomItemFrom(Util.newArrayListOfValues("feminine", "strong", "slender"));
				}
		}
		if (gc.isFeminine()) {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getToeDescriptorsFeminine(), toeDescriptorsFeminine));
		} else {
			return Util.randomItemFrom(Util.mergeLists(this.getFootType().getToeDescriptorsMasculine(), toeDescriptorsMasculine));
		}
	}
	
	
//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, legBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, legTransformationDescription);
	}


	/**
	 * Applies the related leg configuration transformation for this leg type, and returns a description of the changes.<br/><br/>
	 * 
	 * <b>When overriding, consider:</b><br/>
	 * Ass.class (type)<br/>
	 * BreastCrotch.class (type)<br/>
	 * Tail.class (type)<br/>
	 * Tentacle.class (type)<br/>
	 * Penis.class (type, size, cloaca)<br/>
	 * Vagina.class (type, capacity, cloaca)<br/>
	 * 
	 * @param legConfiguration The leg configuration to be applied.
	 * @param character The character which is being transformed.
	 * @param applyEffects Whether the transformative effects should be applied. Pass in false to get the transformation description without applying any of the actual effects.
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 * 
	 * @return A description of the transformation.
	 */
	public String applyLegConfigurationTransformation(GameCharacter character, LegConfiguration legConfiguration, boolean applyEffects, boolean applyFullEffects) {
		StringBuilder feralStringBuilder = new StringBuilder();

		if(character.isFeral()) {
			return "<p style='text-align:center;'>"
						+ UtilText.parse(character, "[style.italicsDisabled(Ничего не происходит, ведь для такого дикого [npc.sheIsFull], [npc.race] [npc.name] не может трансформировать конфигурацию [npc.her] ног!)]")
					+ "</p>";
		}
		
		if(character.getLegConfiguration()==legConfiguration && character.getLegType().equals(this)) {
			return "<p>"
						+ UtilText.parse(character, "[style.italicsDisabled(Ничего не происходит, так как [npc.name] уже [npc.has] нижнюю часть тела [npc.a_legRace] в конфигурации '"+legConfiguration.getName()+"' конфигурация...)]")
					+ "</p>";
		}
		
		if(!character.getLegType().isLegConfigurationAvailable(legConfiguration)) {
			return "<p>"
					+ UtilText.parse(character, "[style.italicsDisabled(Ничего не происходит, так как текущая нижняя часть тела [npc.namePos] не может быть трансформирована в конфигурацию '"+legConfiguration.getName()+"' конфигурация...)]")
				+ "</p>";
		}
		
		feralStringBuilder.append(handleLegConfigurationChanges(character.getBody(), legConfiguration, applyEffects, applyFullEffects));

		feralStringBuilder.append("<p style='text-align:center;'>");
			if(character.hasWings()) {
				if(legConfiguration.isWingsOnLegConfiguration()) {
					feralStringBuilder.append("[style.italicsFeral([npc.Her] [npc.wingSize], [npc.wings+] теперь расположены по бокам [npc.her] "+legConfiguration.getName()+" тела!)]");
				} else {
					feralStringBuilder.append("[style.italicsFeral([npc.Her] [npc.wingSize], [npc.wings+] теперь расположены на спине верхней части тела [npc.her].)]");
				}
			} else {
				if(legConfiguration.isWingsOnLegConfiguration()) {
					feralStringBuilder.append("[style.italicsFeral(Если у [npc.she] [npc.verb(grow)] есть крылья, они будут располагаться по бокам [npc.her] "+legConfiguration.getName()+" тела!)]");
				} else {
					feralStringBuilder.append("[style.italicsFeral(Если у [npc.she] [npc.verb(grow)] есть крылья, они будут располагаться на спине верхней части тела [npc.her].)]");
				}
			}
			if(!legConfiguration.isAbleToGrowTail()) {
				feralStringBuilder.append("<br/>");
				feralStringBuilder.append("[style.italicsFeral(Такая конфигурация ног помешает [npc.name] отрастить хвост!)]");
			}
		feralStringBuilder.append("</p>");
		
		if(applyEffects) {
			character.getBody().getLeg().setLegConfigurationForced(this, legConfiguration);
		}
		
		character.calculateStatusEffects(0);
		
		feralStringBuilder.append("<p>"
									+ character.postTransformationCalculation()
								+ "</p>");
		
		return UtilText.parse(character, feralStringBuilder.toString());
	}
	
	/**
	 * For use in modifying bodies without an attached character. Outside of the Subspecies class, you should probably always be calling the version of this method that takes in a GameCharacter.
	 * <br/>
	 * <b>Note:</b> If the body's LegConfiguration is already set to legConfiguration, then nothing will happen!
	 * 
	 * @param body The body to be modified.
	 * @param legConfiguration The LegConfiguration to be applied.
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 */
	public void applyLegConfigurationTransformation(Body body, LegConfiguration legConfiguration, boolean applyFullEffects) {
		if(body.getLegConfiguration()==legConfiguration) {
			return;
		}
		handleLegConfigurationChanges(body, legConfiguration, true, applyFullEffects);
		body.getLeg().setLegConfigurationForced(this, legConfiguration);
	}

	/**
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 */
	private String handleLegConfigurationChanges(Body body, LegConfiguration legConfiguration, boolean applyEffects, boolean applyFullEffects) {
		
		String feralRaceName = this.getRace().getFeralName(new LegConfigurationAffinity(legConfiguration, body.getSubspecies().getAffinity()), false);
		String feralRaceNameDeterminer = UtilText.generateSingularDeterminer(feralRaceName);
		StringBuilder feralStringBuilder = new StringBuilder();
		String feralRaceNameWithDeterminer = feralRaceNameDeterminer+" "+feralRaceName;
		String raceColorString = this.getRace().getColour().toWebHexString();
		boolean feral = true;
		
		switch(legConfiguration) {
			case BIPEDAL:
				feral = false;
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, body.getLeg().getLegConfiguration(), legConfiguration.isLargeGenitals(), applyFullEffects); // revert feral parts based on current configuration
					// Changing back to bipedal reverts crotch-boobs based on preferences:
					AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
					if(body.getRaceStage()!=RaceStage.GREATER || Main.getProperties().getUddersLevel()<2 || !body.getGender().isFeminine()) {
						body.setBreastCrotch(
								new BreastCrotch(
									BreastType.NONE,
									Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
									startingBodyType.getBreastCrotchSize(),
									startingBodyType.getBreastCrotchLactationRate(),
									startingBodyType.getBreastCrotchCount(),
									startingBodyType.getBreastCrotchNippleSize(),
									startingBodyType.getBreastCrotchNippleShape(),
									startingBodyType.getBreastCrotchAreolaeSize(),
									startingBodyType.getBreastCrotchAreolaeShape(),
									startingBodyType.getNippleCountPerBreastCrotch(),
									startingBodyType.getBreastCrotchCapacity(),
									startingBodyType.getBreastCrotchDepth(),
									startingBodyType.getBreastCrotchElasticity(),
									startingBodyType.getBreastCrotchPlasticity(), 
									true));
					}
					body.setGenitalArrangement(startingBodyType.getGenitalArrangement());
				}
				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms back into a regular, bipedal configuration, with [npc.her] genitals shifting back to their normal position between [npc.her] [npc.legs].<br/>"
							+ "[npc.Name] now [npc.has] [style.boldTfGeneric(bipedal)] <b style='color:"+raceColorString+";'>"+this.getTransformName()+" ног</b>, покрытых [npc.legFullDescription].</p>");
				break;
			case ARACHNID:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					if(!legConfiguration.getAvailableGenitalConfigurations().contains(body.getGenitalArrangement())) {
						body.setGenitalArrangement(legConfiguration.getAvailableGenitalConfigurations().get(0));
					}
				}
				feralStringBuilder.append(
						"<p>Без предупреждения, [npc.name] внезапно [npc.verb(lose)] всю силу в [npc.her] [npc.legs], и [npc.she] [npc.verb(collapse)] на землю с испуганным криком. Прежде чем [npc.sheIs] успеет среагировать на это тревожное событие, нижняя часть тела [npc.her] начинает стремительно трансформироваться...</p><p>Посмотрев вниз, [npc.name] [npc.verb(watch)] в неверии, как [npc.her] [npc.legs] разделяются и превращаются в восемь длинных сегментированных ног. Однако изменения на этом не заканчиваются, так как нижняя часть тела [npc.her] продолжает стремительно превращаться в тело огромного восьминогого "+feralRaceName+". Возбужденный [npc.moan] вырывается изо рта [npc.her], в то время как гениталии [npc.her] смещаются на брюшную сторону массивного паукообразного тела [npc.her], а анус [npc.her] "+(body.getLeg().getType().hasSpinneret()?"и паутинная железа являются":"есть")+" расположен рядом с кончиком брюшка [npc.her]. [style.italicsSex(Так как гениталии [npc.her] видны только снизу, [npc.she] [npc.do] не стесняется отсутствия одежды на [npc.her] паукообразном теле.)]<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric(паукообразное тело)] <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, покрытое [npc.legFullDescription].</p>");
				break;
			case AVIAN:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					if(!legConfiguration.getAvailableGenitalConfigurations().contains(body.getGenitalArrangement())) {
						body.setGenitalArrangement(legConfiguration.getAvailableGenitalConfigurations().get(0));
					}
				}
				feralStringBuilder.append(
						"<p>Без предупреждения, [npc.name] внезапно [npc.verb(lose)] всю силу в [npc.her] [npc.legs], и [npc.she] [npc.verb(collapse)] на землю с испуганным криком. Прежде чем [npc.sheIs] успеет среагировать на это тревожное событие, нижняя часть тела [npc.her] начинает стремительно трансформироваться...</p><p>Посмотрев вниз, [npc.name] [npc.verb(watch)] в неверии, как вся нижняя часть тела [npc.her] стремительно превращается в тело огромной "+feralRaceName+". A horny [npc.moan] bursts out of [npc.her] mouth as [npc.her] genitals and asshole shift to be located within a cloaca that's found on the rear-facing underside of [npc.her] massive avian body. [style.italicsSex(As [npc.her] genitals are only visible from below, [npc.she] [npc.do]n't feel embarrassed to have no clothing covering [npc.her] avian body.)]<br/>[npc.Name] now [npc.has] the [style.boldTfGeneric(avian body)] of <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, покрытое [npc.legFullDescription].</p>");
				break;
			case CEPHALOPOD:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>Без предупреждения, [npc.name] внезапно [npc.verb(lose)] всю силу в [npc.her] [npc.legs], и [npc.she] [npc.verb(collapse)] на землю с испуганным криком. Прежде чем [npc.sheIs] успеет среагировать на это тревожное событие, нижняя часть тела [npc.her] начинает стремительно трансформироваться...</p><p>Посмотрев вниз, [npc.name] [npc.verb(watch)] в неверии, как [npc.her] [npc.legs] разделяются и превращаются в восемь длинных, сильных щупалец. Однако изменения на этом не заканчиваются, так как нижняя часть тела [npc.her] продолжает стремительно превращаться в тело огромного восьминогого "+feralRaceName+". Из [npc.her] рта вырывается похотливый [npc.moan], а [npc.her] половые органы и анус смещаются, чтобы расположиться внутри клоаки, находящейся в центре нижней части новых щупалец [npc.her]. [style.italicsSex(Так как клоака [npc.her] видна только снизу, [npc.she] [npc.do] не стыдно быть без одежды, покрывающей [npc.her] щупальцеобразное тело.)]<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric(щупальцеобразное тело)] <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, покрытое [npc.legFullDescription].</p>");
				break;
			case TAIL:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>нижняя часть тела [npc.NamePos] трансформируется в "+feralRaceNameWithDeterminer+"хвост, при этом гениталии и анус [npc.her] смещаются, чтобы расположиться внутри направленной вперед клоаки, находящейся на том же месте, где были бы обычные межбедренные гениталии [npc.her].<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric(хвост)] <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, покрытое [npc.legFullDescription].</p>");
				break;
			case TAIL_LONG:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(GenitalArrangement.CLOACA);
				}
				feralStringBuilder.append(
						"<p>нижняя часть тела [npc.NamePos] трансформируется в тело огромного "+feralRaceName+"хвост <b style='color:, при этом [npc.her] гениталии и анус смещаются, образуя направленную вперед клоаку, расположенную там же, где обычно находятся [npc.her] межбедренные половые органы.<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric(огромный хвост)] <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, покрытое [npc.legFullDescription].</p>");
				break;
			case WINGED_BIPED:
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
					body.setGenitalArrangement(startingBodyType.getGenitalArrangement());
				}

				feralStringBuilder.append(
						"<p>"
							+ "[npc.NamePos] lower body transforms back into a bipedal configuration, with [npc.her] genitals shifting back to their normal position between [npc.her] [npc.legs]."
							+ " Letting out a surprised cry, [npc.name] [npc.verb(bend)] down and [npc.verb(stoop)] over as [npc.her] spine rapidly reshapes itself."
							+ " The transformation is over within a matter of moments, leaving [npc.name] to naturally use [npc.her] [npc.arms] in place of forelegs so as to support [npc.her] newly-shaped body.<br/>"
							+ "[npc.Name] now [npc.has] [style.boldTfGeneric(bipedal)] <b style='color:"+raceColorString+";'>"+this.getTransformName()+" ноги</b>, покрытые [npc.legFullDescription], и [style.boldTfGeneric([npc.verb(use)] [npc.her] [npc.arms], словно передние лапы)].</p>");
				break;
			case QUADRUPEDAL:
				feralStringBuilder.append(
						"<p>Экстремально тревожное, покалывающее чувство начинает разливаться вниз по [npc.namePos] [npc.legs+],");
				
				if(applyEffects) {
					applyExtraLegConfigurationTransformations(body, legConfiguration, legConfiguration.isLargeGenitals(), applyFullEffects);
					body.setGenitalArrangement(body.getLegType().getRace().getRacialBody().getGenitalArrangement());
				}
				
				feralStringBuilder.append(
						" и [npc.she] [npc.verb(let)] испускает тревожный крик, когда [npc.she] [npc.verb(lose)] [npc.her] равновесие и [npc.verb(tumble)] на пол. Прямо перед тем как [npc.her] [npc.eyes], [npc.her] нижняя часть тела смещается и трансформируется: [npc.her] конечности втягиваются в промежуточную массу [npc.bodyMaterial], после чего почти мгновенно выталкиваются наружу, принимая конфигурацию четвероногого существа.</p><p>Пока это происходит, [npc.her] [npc.ass+] стремительно расширяется и выпирает, быстро формируя тело, к которому крепятся четыре новые ноги [npc.her]. Хотя это можно назвать не иначе как экстремальным процессом, трансформация проходит удивительно безболезненно, а судорожные вздохи и [npc.moans], вырывающиеся из рта [npc.namePos], вызваны лишь шоком и напряжением.</p><p>Спустя всего несколько мгновений, нижняя часть тела [npc.namePos] полностью превращается в "+feralRaceNameWithDeterminer
								+", при этом [npc.her] гениталии и анус сместились так, чтобы располагаться там же, где и у дикого существа "+feralRaceName+".<br/>[npc.Name] теперь [npc.has] [style.boldTfGeneric(нижнюю часть тела)] <b style='color:"+raceColorString+";'>"+feralRaceNameWithDeterminer+"</b>, которая покрыта [npc.legFullDescription(true)].</p>");
				break;
		}

		// Increase or decrease height based on configuration:
		if(applyEffects) {
			if(!body.getLegConfiguration().isTall() && legConfiguration.isTall()) {
				int newHeight = (int) (body.getHeightValue()*1.33f);
				if(body.isShortStature()) {
					newHeight = Math.min(Height.getShortStatureCutOff()-1, newHeight);
				}
				body.setHeight(newHeight);
				String colouredHeightValue = "<span style='color:"+body.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
				feralStringBuilder.append("<p>Из-за размера новой нижней части тела [npc.namePos], [npc.herHim] стала выше, так что теперь, когда [npc.she] [npc.verb(measure)] во весь рост, "+colouredHeightValue+".</p>");
				
			} else if(body.getLegConfiguration().isTall() && !legConfiguration.isTall()) {
				int newHeight = (int) (body.getHeightValue()/1.33f);
				if(!body.isShortStature()) {
					newHeight = Math.max(Height.getShortStatureCutOff(), newHeight);
				}
				if(!body.isFairySized()) {
					newHeight = Math.max(Height.getFairySizeCutOff(), newHeight);
				}
				newHeight = Math.max(newHeight, Height.NEGATIVE_TWO_MINIMUM.getMinimumValue()); // Do not reduce into tiny size
				body.setHeight(newHeight);
				String colouredHeightValue = "<span style='color:"+body.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
				feralStringBuilder.append("<p>Из-за уменьшенного размера новой нижней части тела [npc.namePos], [npc.herHim] стала ниже, так что теперь, когда [npc.she] [npc.verb(measure)] во весь рост, "+colouredHeightValue+".</p>");
			}
		}
		
		
		if(legConfiguration.isTailLostOnInitialTF()) {
			if(body.getTail().getType()!=TailType.NONE) {
				body.getTail().setType(null, TailType.NONE);
				// Tail description is handled below
//				feralStringBuilder.append(
//						"<p style='text-align:center;'>"
//								+ "[style.italicsFeral(As part of the transformation, [npc.name] [nc.has] lost [npc.her] tail!)]"
//						+ "</p>");
			}
		}
		
		if(Main.getProperties().getUddersLevel()==0 && !body.isFeral()) {
			AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
			body.setBreastCrotch(
					new BreastCrotch(
						BreastType.NONE,
						Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
						startingBodyType.getBreastCrotchSize(),
						startingBodyType.getBreastCrotchLactationRate(),
						startingBodyType.getBreastCrotchCount(),
						startingBodyType.getBreastCrotchNippleSize(),
						startingBodyType.getBreastCrotchNippleShape(),
						startingBodyType.getBreastCrotchAreolaeSize(),
						startingBodyType.getBreastCrotchAreolaeShape(),
						startingBodyType.getNippleCountPerBreastCrotch(),
						startingBodyType.getBreastCrotchCapacity(),
						startingBodyType.getBreastCrotchDepth(),
						startingBodyType.getBreastCrotchElasticity(),
						startingBodyType.getBreastCrotchPlasticity(), 
						true));
		}
		
		feralStringBuilder.append("<p><i>[style.boldTfGeneric(Каждая часть)] нижней части тела [npc.her] превратилась в часть "+(feral?"дикого существа "+feralRaceName:"обычного "+this.getRace().getName(false))+", а значит, что [npc.she] теперь [npc.has]");
		
		List<String> partsList = new ArrayList<>();
		// Tail:
		if(body.getTail().getType()==TailType.NONE) {
			partsList.add(" [style.boldTfGeneric(без хвоста)]");
		} else {
			partsList.add(" [style.boldTfGeneric("
					+(body.getTail().getTailCount()==1
						?(body.getTail().getType()==TailType.HARPY?"пучок ":UtilText.generateSingularDeterminer(body.getTail().getName(null))+" ")
						:Util.intToString(body.getTail().getTailCount())+" "+(body.getTail().getType()==TailType.HARPY?" пучки ":""))
					+body.getTail().getName(null)+")]");
		}
		// Ass:
		partsList.add(feralRaceNameWithDeterminer+"'s ass");
		// Crotch boobs:
		if((Main.getProperties().getUddersLevel()==1 && legConfiguration!=LegConfiguration.BIPEDAL)
				|| (Main.getProperties().getUddersLevel()==2 && body.getRaceStage()==RaceStage.GREATER)
				|| body.isFeral()) {
			if(body.getBreastCrotch().getType()!=BreastType.NONE && !legConfiguration.isBipedalPositionedCrotchBoobs()) {
				partsList.add("animal-like "+body.getBreastCrotch().getName(null));
			}
		}
		feralStringBuilder.append(Util.stringsToStringList(partsList, false)+".");
		
		Penis penis = body.getPenis();
		if(penis.getType()!=PenisType.NONE) {
			if(feral) {
				feralStringBuilder.append(" [npc.Her] член тоже превратился в нечто, напоминающее орган дикого зверя "+feralRaceName+", и он не только выделяет мускусную, звериную сперму, но и является впечатляюще "+Units.size(penis.getRawLengthValue())+" длинным.");
			} else {
				feralStringBuilder.append(" член [npc.Her] тоже превратился в нечто, напоминающее орган обычного "+this.getRace().getName(false)+", и он "+Units.size(penis.getRawLengthValue())+" длинный.");
			}
		}
		
		Vagina vagina = body.getVagina();
		if(vagina.getType()!=VaginaType.NONE) {
			if(feral) {
				feralStringBuilder.append(" теперь уже мускусистая киска [npc.Her] стала точной копией киски "+feralRaceNameWithDeterminer
						+", и она превратилась в "+vagina.getOrificeVagina().getCapacity().getDescriptor()+" , чтобы соответствовать созвучному дикому члену.");
			} else {
				feralStringBuilder.append(" [npc.Her] теперь-"+vagina.getOrificeVagina().getCapacity().getDescriptor()+" киска так же трансформировалась в обычную.");
			}
		}
		feralStringBuilder.append("</i></p>");
		
		if(feral) {
			feralStringBuilder.append("<p><i>Чувствуя, что это совершенно естественно, [npc.she] [style.colourGood(больше не [npc.verb(get)] смущается)] из-за того, что [npc.her] [style.italicsFeral(животные гениталии"
					+(legConfiguration.isBipedalPositionedCrotchBoobs()?(body.getBreastCrotch().getShape()==BreastShape.UDDERS?" или вымя":" или грудь в паху"):"")
					+ ")] выставлены напоказ!</i></p>");
		}

		if(body.getLeg().getType().hasSpinneret()) {
			feralStringBuilder.append("<p>[npc.Her] живот имеет [style.italicsFeral(паучье брюшко)], которое, помимо плетения паутины, [style.italicsSex(можно проникать и использовать как сексуальное отверстие)]!</p>");
		} else if(body.getTail().getType().hasSpinneret()) {
			feralStringBuilder.append("<p>[npc.Her] хвост имеет [style.italicsFeral(паучье брюшко)], которое, помимо плетения паутины, [style.italicsSex(можно проникать и использовать как сексуальное отверстие)]!</p>");
		}
		
		int landSpeed = body.getLeg().getType().getLandSpeedModifier() + legConfiguration.getLandSpeedModifier();
		if(landSpeed>0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>[npc.Her] новая нижняя часть тела приспособлена к передвижению по суше не так хорошо, как у обычного двуногого, и в результате, [style.colourTerrible([npc.she] [npc.verb(move)] медленнее обычного)] [style.colourEarth(на суше)]!<br/>[style.colourTerrible(+"+landSpeed+"%)] времени в пути на суше!</i></p>");
		} else if(landSpeed<0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>[npc.Her] новая нижняя часть тела способна развивать скорость выше той, что достижима для обычного двуногого, и в результате, [style.colourExcellent([npc.she] [npc.verb(move)] быстрее обычного)] [style.colourEarth(на суше)]!<br/>[style.colourExcellent("+landSpeed+"%)] времени в пути на суше!</i></p>");
		}

		int waterSpeed = body.getLeg().getType().getWaterSpeedModifier() + legConfiguration.getWaterSpeedModifier();
		if(waterSpeed>0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>[npc.SheIsFull] теперь гораздо менее способна к передвижению в воде, чем обычный двуногий, и в результате, [style.colourTerrible([npc.she] [npc.verb(move)] медленнее обычного)] [style.colourWater(в воде)]!<br/>[style.colourTerrible(+"+waterSpeed+"%)] времени в пути в воде!</i></p>");
		} else if(waterSpeed<0) {
			feralStringBuilder.append("<p style='text-align:center;'><i>[npc.SheIsFull] теперь обладает телом, прекрасно подходящим для передвижения в воде, и в результате, [style.colourExcellent([npc.she] [npc.verb(move)] быстрее обычного)] [style.colourWater(в воде)]!<br/>[style.colourExcellent("+waterSpeed+"%)] времени в пути в воде!</i></p>");
		}
		
		if(legConfiguration==LegConfiguration.TAIL) {
			feralStringBuilder.append("<p style='text-align:center;'><i>Пока [style.colourTan(на суше)], нижняя часть тела [npc.namePos] будет [style.colourTfGeneric(автоматически принимать двуногую форму)]! В этой форме [npc.she] будет страдать от некоторых негативных побочных эффектов.<br/>Напротив, пока [style.colourBlueLight(в воде)], нижняя часть тела [npc.namePos] будет [style.colourTfGeneric(автоматически возвращаться в свою истинную хвостатую форму)]! В этой форме [npc.she] получит ряд положительных бонусов.</i></p>");
		}
		
		return feralStringBuilder.toString();
	}
	
	// Setting parts is applied directly through body to circumvent transformation blocks
	/**
	 * @param applyFullEffects Pass in true if you want the additional transformations to include attribute changes (such as penis resizing, vagina capacity resetting, etc.).
	 **/
	private void applyExtraLegConfigurationTransformations(Body body, LegConfiguration legConfiguration, boolean largeGenitals, boolean applyFullEffects) {
		AbstractRacialBody startingBodyType = RacialBody.valueOfRace(this.getRace());
		
		boolean demon = body.getRace()==Race.DEMON;
		
		if(legConfiguration.getFeralParts().contains(Ass.class)) { // Ass (includes Anus):
			if(!applyFullEffects) {
				body.getAss().setType(null, (demon
						?AssType.DEMON_COMMON
						:startingBodyType.getAssType()));
			} else {
				boolean virgin = body.getAss().getAnus().getOrificeAnus().isVirgin();
				body.setAss(
						new Ass(
							(demon
								?AssType.DEMON_COMMON
								:startingBodyType.getAssType()),
							(body.isFeminine() ? startingBodyType.getFemaleAssSize() : startingBodyType.getMaleAssSize()),
							(body.isFeminine() ? startingBodyType.getFemaleHipSize() : startingBodyType.getMaleHipSize()),
							startingBodyType.getAnusWetness(),
							(float) (startingBodyType.getAnusCapacity()*(largeGenitals?2.5:1)),
							startingBodyType.getAnusDepth()+(largeGenitals?2:0),
							startingBodyType.getAnusElasticity(),
							startingBodyType.getAnusPlasticity(),
							true));
				body.getAss().getAnus().getOrificeAnus().setVirgin(virgin);
			}
		}
		if(legConfiguration.getFeralParts().contains(BreastCrotch.class)) { // Crotch-boobs:
			AbstractBreastType crotchBoobType = BreastType.NONE;
			if(body.isFeminine()) {
				if(demon) {
					crotchBoobType = BreastType.DEMON_COMMON;
				} else {
					crotchBoobType = startingBodyType.getBreastCrotchType();
				}
			}
			
			if(!applyFullEffects) {
				body.getBreastCrotch().setType(null, crotchBoobType);
				
			} else {
				body.setBreastCrotch(
						new BreastCrotch(
							crotchBoobType,
							Util.randomItemFrom(startingBodyType.getBreastCrotchShapes()),
							startingBodyType.getBreastCrotchSize(),
							startingBodyType.getBreastCrotchLactationRate(),
							startingBodyType.getBreastCrotchCount(),
							startingBodyType.getBreastCrotchNippleSize(),
							startingBodyType.getBreastCrotchNippleShape(),
							startingBodyType.getBreastCrotchAreolaeSize(),
							startingBodyType.getBreastCrotchAreolaeShape(),
							startingBodyType.getNippleCountPerBreastCrotch(),
							startingBodyType.getBreastCrotchCapacity(),
							startingBodyType.getBreastCrotchDepth(),
							startingBodyType.getBreastCrotchElasticity(),
							startingBodyType.getBreastCrotchPlasticity(), 
							true));
			}
		}
		if(legConfiguration.getFeralParts().contains(Tail.class)) { // Tail:
			if(body.getLeg().getType().getRace()==Race.DEMON) {
				body.setTail(new Tail(TailType.DEMON_HORSE));
			} else {
				if(body.getTail().getType().getRace()!=startingBodyType.getTailType().get(0).getRace()) {
					body.setTail(new Tail(startingBodyType.getTailType().get(0)));
				}
			}
		}
		if(legConfiguration.getFeralParts().contains(Tentacle.class)) { // Tentacle:
			body.setTentacle(new Tentacle(startingBodyType.getTentacleType()));
		}
		if(legConfiguration.getFeralParts().contains(Penis.class)) { // Penis (includes Testicle):
			if(!applyFullEffects) {
				if(body.getPenis().getType()!=PenisType.NONE && body.getPenis().getType()!=PenisType.DILDO) {
					body.getPenis().setType(null,
								(demon
									?PenisType.DEMON_COMMON
									:startingBodyType.getPenisType()));
				}
				
			} else {
				boolean virgin = body.getPenis().isVirgin();
				body.setPenis(body.getPenis().getType()!=PenisType.NONE && body.getPenis().getType()!=PenisType.DILDO
						? new Penis(
							(demon
								?PenisType.DEMON_COMMON
								:startingBodyType.getPenisType()),
							(int) (startingBodyType.getPenisSize()*(largeGenitals?2.5:1)),
							true,
							startingBodyType.getPenisGirth()+(largeGenitals?1:0),
							startingBodyType.getTesticleSize()+(largeGenitals?1:0),
							startingBodyType.getCumProduction()*(largeGenitals?10:1),
							startingBodyType.getTesticleQuantity())
						: new Penis(PenisType.NONE, 0, false, 0, 0, 0, 2));
//				body.getPenis().getTesticle().getCum().addFluidModifier(null, FluidModifier.MUSKY);
				body.getPenis().setVirgin(virgin);
			}
		}
		if(legConfiguration.getFeralParts().contains(Vagina.class)) { // Vagina (includes Clitoris):
			if(!applyFullEffects) {
				if(body.getVagina().getType()!=VaginaType.NONE && body.getVagina().getType()!=VaginaType.ONAHOLE) {
					body.getVagina().setType(null,
								(demon
									?VaginaType.DEMON_COMMON
									:startingBodyType.getVaginaType()));
				}
				
			} else {
				boolean virgin = body.getVagina().getType()!=VaginaType.NONE?body.getVagina().getOrificeVagina().isVirgin():true;
				boolean hymen = body.getVagina().getType()!=VaginaType.NONE?body.getVagina().getOrificeVagina().hasHymen():true;
				body.setVagina(
						body.getVagina().getType()!=VaginaType.NONE && body.getVagina().getType()!=VaginaType.ONAHOLE
							? new Vagina(
									(demon
										?VaginaType.DEMON_COMMON
										:startingBodyType.getVaginaType()),
									LabiaSize.getRandomLabiaSize().getValue(),
									startingBodyType.getClitSize(),
									startingBodyType.getClitGirth(),
									startingBodyType.getVaginaWetness(),
									(float) (startingBodyType.getVaginaCapacity()*(largeGenitals?2.5:1)),
									startingBodyType.getVaginaDepth()+(largeGenitals?2:0),
									startingBodyType.getVaginaElasticity(),
									startingBodyType.getVaginaPlasticity(),
									true)
							: new Vagina(VaginaType.NONE, 0, 0, 0, 0, 0, 2, 3, 3, true));
//				body.getVagina().getGirlcum().addFluidModifier(null, FluidModifier.MUSKY);
				body.getVagina().getOrificeVagina().setVirgin(virgin);
				body.getVagina().getOrificeVagina().setHymen(null, hymen);
			}
		}
 	}
	
	public List<LegConfiguration> getAllowedLegConfigurations() {
		return allowedLegConfigurations;
	}

	/**
	 * @param legConfiguration The configuration to check transformation availability of.
	 * @return True if this configuration is allowed for this LegType.
	 */
	public boolean isLegConfigurationAvailable(LegConfiguration legConfiguration) {
		return allowedLegConfigurations.contains(legConfiguration);
	}
	
	public boolean hasSpinneret() {
		return spinneret;
	}

	/**
	 * @return Usually TentacleType.NONE. If it returns an actual TentacleType, then that means this LegType has tentacles in place of legs.
	 */
	public AbstractTentacleType getTentacleType() {
		return tentacleType;
	}
	
	public boolean isLegsReplacedByTentacles() {
		return getTentacleType()!=TentacleType.NONE;
	}
	
	public int getTentacleCount() {
		return tentacleCount;
	}
	
	/**
	 * @return By default, LegTypes return a modification of 0, but if a LegType requires a modifier, then this can be overridden and its effects will be handled alongside LegConfiguration's getLandSpeedModifier().
	 */
	public int getLandSpeedModifier() {
		return 0;
	}

	/**
	 * @return By default, LegTypes return a modification of 0, but if a LegType requires a modifier, then this can be overridden and its effects will be handled alongside LegConfiguration's getWaterSpeedModifier().
	 */
	public int getWaterSpeedModifier() {
		return 0;
	}
	
	@Override
	public BodyPartClothingBlock getBodyPartClothingBlock() {
		if(this.getFootType()==FootType.HOOFS) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.FOOT),
					this.getRace(),
					"Из-за формы копыт [npc.namePos], в этот слот можно надеть только одежду, совместимую с копытами.",
					Util.newArrayListOfValues(ItemTag.FITS_HOOFS, ItemTag.FITS_HOOFS_EXCLUSIVE));
		}
		if(this.getFootType()==FootType.TALONS) {
			return new BodyPartClothingBlock(
					Util.newArrayListOfValues(
							InventorySlot.FOOT),
					this.getRace(),
					"Из-за формы когтей [npc.namePos], в этот слот можно надеть только одежду, совместимую с когтями.",
					Util.newArrayListOfValues(ItemTag.FITS_TALONS, ItemTag.FITS_TALONS_EXCLUSIVE));
		}
		return null;
	}
}
