package com.lilithsthrone.game.character.body.abstractTypes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.7
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractTailType implements BodyPartTypeInterface {

	private boolean mod;
	private boolean fromExternalFile;

	private AbstractBodyCoveringType coveringType;
	private AbstractRace race;

	private String transformationName;
	
	private int defaultGirth;
	private float defaultLengthAsPercentageOfHeight;

	private String determiner;
	private String determinerPlural;
	
	private String name;
	private String namePlural;
	private List<String> descriptorsMasculine;
	private List<String> descriptorsFeminine;

	private String tipName;
	private String tipNamePlural;
	private List<String> tipDescriptorsMasculine;
	private List<String> tipDescriptorsFeminine;
	
	private String tailTransformationDescription;
	private String tailBodyDescription;

	private List<BodyPartTag> tags;
	
	private boolean spinneret;
	
	/**
	 * @param coveringType What covers this tail type (i.e skin/fur/feather type).
	 * @param race What race has this tail type.
	 * @param defaultGirth The girth which this TailType spawns with.
	 * @param defaultLengthAsPercentageOfHeight The percentage, as a float from 0->1, of this tail's length as a proportion of the owner's body height.
	 * @param transformationName The name that should be displayed when offering this tail type as a transformation. Should be something like "demonic spaded" or "demonic hair-tipped".
	 * @param determiner The singular determiner which should be used for this tail type. Should normally be left blank unless the tail is of a special type (such as harpy 'tail feathers' needing 'a plume of' as the determiner).
	 * @param determinerPlural The plural determiner which should be used for this tail type, appended after a number. Should normally be left blank unless the tail is of a special type (such as harpy 'tail feathers' needing 'plumes of' as the determiner).
	 * @param name The singular name of the tail. This will usually just be "tail".
	 * @param namePlural The plural name of the tail. This will usually just be "tails".
	 * @param descriptorsMasculine The descriptors that can be used to describe a masculine form of this tail type.
	 * @param descriptorsFeminine The descriptors that can be used to describe a feminine form of this tail type.
	 * @param tipName The singular name of the tip of this tail. This will usually just be "tip".
	 * @param tipNamePlural The plural name of tip of this tail. This will usually just be "tips".
	 * @param tipDescriptorsMasculine The descriptors that can be used to describe a masculine form of this tail type's tip. Will usually be blank.
	 * @param tipDescriptorsFeminine The descriptors that can be used to describe a feminine form of this tail type's tip. Will usually be blank.
	 * @param tailTransformationDescription A paragraph describing a character's tails transforming into this tail type. Parsing assumes that the character already has this tail type and associated skin covering.
	 * @param tailBodyDescription A sentence or two to describe this tail type, as seen in the character view screen. It should follow the same format as all of the other entries in the TailType class.
	 * @param tags The tags which define this tail's properties.
	 * @param spinneret true if this tail type has a spinneret.
	 */
	public AbstractTailType(
			AbstractBodyCoveringType coveringType,
			AbstractRace race,
			PenetrationGirth defaultGirth,
			float defaultLengthAsPercentageOfHeight,
			String transformationName,
			String determiner,
			String determinerPlural,
			String name,
			String namePlural,
			List<String> descriptorsMasculine,
			List<String> descriptorsFeminine,
			String tipName,
			String tipNamePlural,
			List<String> tipDescriptorsMasculine,
			List<String> tipDescriptorsFeminine,
			String tailTransformationDescription,
			String tailBodyDescription,
			List<BodyPartTag> tags,
			boolean spinneret) {
		
		this.coveringType = coveringType;
		this.race = race;

		this.defaultGirth = defaultGirth.getValue();
		this.defaultLengthAsPercentageOfHeight = defaultLengthAsPercentageOfHeight;
		
		this.transformationName = transformationName;
		
		this.determiner = determiner;
		this.determinerPlural = determinerPlural;
		
		this.name = name;
		this.namePlural = namePlural;
		this.descriptorsMasculine = descriptorsMasculine;
		this.descriptorsFeminine = descriptorsFeminine;
		
		this.tipName = tipName;
		this.tipNamePlural = tipNamePlural;
		this.tipDescriptorsMasculine = tipDescriptorsMasculine;
		this.tipDescriptorsFeminine = tipDescriptorsFeminine;
		
		this.tailTransformationDescription = tailTransformationDescription;
		this.tailBodyDescription = tailBodyDescription;
		
		this.tags = tags;
		
		this.spinneret = spinneret;
	}
	
	public AbstractTailType(File XMLFile, String author, boolean mod) {
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
				
				this.spinneret = Boolean.valueOf(coreElement.getMandatoryFirstOf("spinneret").getTextContent());

				this.defaultGirth = Integer.valueOf(coreElement.getMandatoryFirstOf("defaultGirth").getTextContent());
				this.defaultLengthAsPercentageOfHeight = Float.valueOf(coreElement.getMandatoryFirstOf("defaultLengthAsPercentageOfHeight").getTextContent());

				this.tags = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tags").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tags").getAllOf("tag")) {
						tags.add(BodyPartTag.getBodyPartTagFromId(e.getTextContent()));
					}
				}
				if(tags.isEmpty()) {
					tags.add(BodyPartTag.TAIL_TYPE_GENERIC);
					tags.add(BodyPartTag.TAIL_TAPERING_NONE);
				}
				
				this.determiner = coreElement.getMandatoryFirstOf("determiner").getTextContent();
				this.determinerPlural = coreElement.getMandatoryFirstOf("determinerPlural").getTextContent();
				
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
				
				this.tipName = coreElement.getMandatoryFirstOf("tipName").getTextContent();
				this.tipNamePlural = coreElement.getMandatoryFirstOf("tipNamePlural").getTextContent();
				this.tipDescriptorsMasculine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tipDescriptorsMasculine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tipDescriptorsMasculine").getAllOf("descriptor")) {
						tipDescriptorsMasculine.add(e.getTextContent());
					}
				}
				this.tipDescriptorsFeminine = new ArrayList<>();
				if(coreElement.getOptionalFirstOf("tipDescriptorsFeminine").isPresent()) {
					for(Element e : coreElement.getMandatoryFirstOf("tipDescriptorsFeminine").getAllOf("descriptor")) {
						tipDescriptorsFeminine.add(e.getTextContent());
					}
				}
				
				this.tailTransformationDescription = coreElement.getMandatoryFirstOf("transformationDescription").getTextContent();
				this.tailBodyDescription = coreElement.getMandatoryFirstOf("bodyDescription").getTextContent();
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractTailType was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
	}
	
	public boolean isMod() {
		return mod;
	}

	public boolean isFromExternalFile() {
		return fromExternalFile;
	}

	public int getDefaultGirth() {
		return defaultGirth;
	}
	
	public float getDefaultLengthAsPercentageOfHeight() {
		return defaultLengthAsPercentageOfHeight;
	}
	
	@Override
	public List<BodyPartTag> getTags() {
		return tags;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc==null) {
			return "";
		}
		if(gc.getTailCount()==1) {
			return Util.intToString(gc.getTailCount())+" "+determiner;
		}
		return Util.intToString(gc.getTailCount())+" "+determinerPlural;
	}
	
	@Override
	public String getTransformationNameOverride() {
		return transformationName;
	}
	
	@Override
	public boolean isDefaultPlural(GameCharacter gc) {
		return false;
	}
	
	@Override
	public String getName(GameCharacter gc){
		if(isDefaultPlural(gc) || (gc!=null && gc.getTailCount()!=1)) {
			return getNamePlural(gc);
		} else {
			return getNameSingular(gc);
		}
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return name;
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return namePlural;
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(descriptorsFeminine);
		} else {
			return Util.randomItemFrom(descriptorsMasculine);
		}
	}
	
	public String getTailTipNameSingular(GameCharacter gc) {
		return tipName;
	}
	
	public String getTailTipNamePlural(GameCharacter gc) {
		return tipNamePlural;
	}
	
	public String getTailTipDescriptor(GameCharacter gc) {
		if (gc.isFeminine()) {
			return Util.randomItemFrom(tipDescriptorsFeminine);
		} else {
			return Util.randomItemFrom(tipDescriptorsMasculine);
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

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, tailBodyDescription);
	}
	
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, tailTransformationDescription);
	}
	
	/**
	 * @return A description of this tail's girth, based on the TYPE tag and the owner's girth.
	 */
	public String getGirthDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN) || this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tails] — это"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tail] — это"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>очень тонкий<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>изящный<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>довольно узкий<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " <colourStart>средней толщины<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>довольно толстый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>очень толстый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>невероятно толстый и мощный<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>чрезвычайно толстый и мощный<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tails] — это"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] [npc.tail] — это"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>очень тонкий и почти лишенный пушистости<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>стройный и недостаточно пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>довольно узкий и слегка непушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " <colourStart>средней толщины и пушистости<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>довольно большой и очень пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>очень большой и пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>невероятно толстый и пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>чрезвычайно толстый и пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] кисточки на хвостах — это"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] кисточка на хвосте — это"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>очень маленький и заметно непушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>довольно маленький и непушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>слегка маловат и непушист<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " <colourStart>среднего размера и пушистости<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>довольно большой и очень пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>очень большой и чрезвычайно пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>невероятно толстый и пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>чрезвычайно толстый и пушистый<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her] лошадиные хвосты — это"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her] лошадиный хвост — это"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>сильно не хватает объема<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>не хватает объема<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>слегка не хватает объема<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " <colourStart>среднего объема<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>довольно объемным<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>очень объемным<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>невероятно объемным<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>чрезвычайно объемным<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her]перья представляют собой"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her]перья представляют собой"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>очень маленьким и лишенным объема<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>маленьким и несколько лишенным объема<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>немного узким и лишенным объема<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " <colourStart>среднего размера и объема<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>довольно крупный и объемный<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>очень крупный и объемный<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>невероятно объемным<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>чрезвычайно объемным<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(owner.getTailCount()>1) {
				sb.append(UtilText.parse(owner, " [npc.Her]хвосты"));
			} else {
				sb.append(UtilText.parse(owner, " [npc.Her]хвост"));
			}
			switch(owner.getTailGirth()) {
				case ZERO_THIN:
					sb.append(UtilText.parse(owner, " <colourStart>очень маленький<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case ONE_SLENDER:
					sb.append(UtilText.parse(owner, " <colourStart>несколько мелкий<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case TWO_NARROW:
					sb.append(UtilText.parse(owner, " <colourStart>слегка узкий<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case THREE_AVERAGE:
					sb.append(UtilText.parse(owner, " <colourStart>среднего размера<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case FOUR_GIRTHY:
					sb.append(UtilText.parse(owner, " <colourStart>довольно крупный<colourEnd> по отношению к остальному телу [npc.her]."));
					break;
				case FIVE_THICK:
					sb.append(UtilText.parse(owner, " <colourStart>очень большой<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case SIX_CHUBBY:
					sb.append(UtilText.parse(owner, " <colourStart>невероятно большой<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
				case SEVEN_FAT:
					sb.append(UtilText.parse(owner, " <colourStart>чрезвычайно большой<colourEnd> по сравнению с остальным телом [npc.her]."));
					break;
			}
		}
		
		String returnString = sb.toString();
		String colourStartTag = "<span style='color:"+owner.getTailGirth().getColour().toWebHexString()+";'>";
		String colourEndTag = "</span>";
		returnString = returnString.replaceAll("<colourStart>", colourStartTag);
		returnString = returnString.replaceAll("<colourEnd>", colourEndTag);
		
		return returnString;
	}

	public String getGirthDescriptor(GameCharacter owner) {
		return getGirthDescriptor(owner.getTailGirth());
	}

	public String getGirthDescriptor(Body body) {
		return getGirthDescriptor(body.getTail().getGirth());
	}
	
	public String getGirthDescriptor(PenetrationGirth girth) {
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)) {
			switch(girth) {
				case ZERO_THIN:
					return "тонкий";
				case ONE_SLENDER:
					return "тонкие";
				case TWO_NARROW:
					return "узкий";
				case THREE_AVERAGE:
					return "средний";
				case FOUR_GIRTHY:
					return "толстый";
				case FIVE_THICK:
					return "сверхтолстый";
				case SIX_CHUBBY:
					return "экстремально толстый";
				case SEVEN_FAT:
					return "невероятно толстый";
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			switch(girth) {
				case ZERO_THIN:
					return "тонкий";
				case ONE_SLENDER:
					return "тонкие";
				case TWO_NARROW:
					return "узкий";
				case THREE_AVERAGE:
					return "пушистый";
				case FOUR_GIRTHY:
					return "очень пушистый";
				case FIVE_THICK:
					return "сверхпушистый";
				case SIX_CHUBBY:
					return "невероятно пушистый";
				case SEVEN_FAT:
					return "невообразимо пушистый";
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			switch(girth) {
				case ZERO_THIN:
					return "крошечный";
				case ONE_SLENDER:
					return "маленький";
				case TWO_NARROW:
					return "узкий";
				case THREE_AVERAGE:
					return "пушистый";
				case FOUR_GIRTHY:
					return "очень пушистый";
				case FIVE_THICK:
					return "сверхпушистый";
				case SIX_CHUBBY:
					return "невероятно пушистый";
				case SEVEN_FAT:
					return "невообразимо пушистый";
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			switch(girth) {
				case ZERO_THIN:
					return "тонкий";
				case ONE_SLENDER:
					return "маленький";
				case TWO_NARROW:
					return "узкий";
				case THREE_AVERAGE:
					return "средний";
				case FOUR_GIRTHY:
					return "пышный";
				case FIVE_THICK:
					return "очень пышный";
				case SIX_CHUBBY:
					return "чрезвычайно пышный";
				case SEVEN_FAT:
					return "невероятно пышный";
			}
		}

		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			switch(girth) {
				case ZERO_THIN:
					return "тонкий";
				case ONE_SLENDER:
					return "маленький";
				case TWO_NARROW:
					return "узкий";
				case THREE_AVERAGE:
					return "средний";
				case FOUR_GIRTHY:
					return "пышный";
				case FIVE_THICK:
					return "очень пышный";
				case SIX_CHUBBY:
					return "чрезвычайно пышный";
				case SEVEN_FAT:
					return "невероятно пышный";
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			switch(girth) {
				case ZERO_THIN:
					return "крошечный";
				case ONE_SLENDER:
					return "маленький";
				case TWO_NARROW:
					return "узкий";
				case THREE_AVERAGE:
					return "средний";
				case FOUR_GIRTHY:
					return "крупные";
				case FIVE_THICK:
					return "огромный";
				case SIX_CHUBBY:
					return "массивный";
				case SEVEN_FAT:
					return "колоссальный";
			}
		}
		
		return girth.getName();
	}
	
	public String getGirthTransformationDescription(GameCharacter owner, boolean positive) {
		String tailText = "[npc.a_tailGirth] [npc.tail]";
		if(owner.getTailCount()>1) {
			tailText = "[npc.tailGirth] [npc.tails]";
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(становится толще)]."
									:" [npc.tail] внезапно [style.boldGrow(набухает)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(уменьшается)]."
										:" [npc.tail] внезапно [style.boldShrink(сжимается)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(распушается и увеличивается в размерах)]."
									:" [npc.tail] внезапно [style.boldGrow(распушается и увеличивается в размерах)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(уменьшается)]."
										:" [npc.tail] внезапно [style.boldShrink(сжимается)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(набухает и увеличивается в объеме)]."
									:" [npc.tail] внезапно [style.boldGrow(набухает и увеличивается в объеме)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(сжимается и теряет в объеме)]."
										:" [npc.tail] внезапно [style.boldShrink(сжимается и теряет в объеме)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(набухает и увеличивается в объеме)]."
									:" [npc.tail] внезапно [style.boldGrow(набухает и увеличивается в объеме)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(сжимается и теряет в объеме)]."
										:" [npc.tail] внезапно [style.boldShrink(сжимается и теряет в объеме)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(увеличивается)]."
									:" [npc.tail] внезапно [style.boldGrow(становится больше)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(уменьшается)]."
										:" [npc.tail] внезапно [style.boldShrink(сжимается)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric("+tailText+")]!"
						+ "</p>");
			}
		}
		return "";
	}
	
	public String getLengthTransformationDescription(GameCharacter owner, boolean positive) {
		String heightPercentageDescription = " (длина составляет "+((int)(owner.getTailLengthAsPercentageOfHeight()*100))+"% oф при росте [npc.namePos])";
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_SKIN)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_SCALES)
				|| this.getTags().contains(BodyPartTag.TAIL_TYPE_FUR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(удлиняется)]."
									:" [npc.tail] внезапно [style.boldGrow(становится длиннее)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(укорачивается)]."
										:" [npc.tail] внезапно [style.boldShrink(становится короче)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_TUFT)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(распушается и становится длиннее)]."
									:" [npc.tail] внезапно [style.boldGrow(распушается и удлиняется)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(укорачивается)]."
										:" [npc.tail] внезапно [style.boldShrink(становится короче)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_HAIR)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(удлиняется)]."
									:" [npc.tail] внезапно [style.boldGrow(становится длиннее)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(укорачивается)]."
										:" [npc.tail] внезапно [style.boldShrink(становится короче)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_FEATHER)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(удлиняется)]."
									:" [npc.tail] внезапно [style.boldGrow(становится длиннее)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(укорачивается)]."
										:" [npc.tail] внезапно [style.boldShrink(становится короче)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		if(this.getTags().contains(BodyPartTag.TAIL_TYPE_GENERIC)) {
			if(positive) {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
							+(owner.getTailCount()>1
									?" [npc.tails] внезапно [style.boldGrow(удлиняется)]."
									:" [npc.tail] внезапно [style.boldGrow(становится длиннее)].")
							+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
					
			} else {
				return UtilText.parse(owner,
						"<p>[npc.Name] [npc.verb(let)] из [npc.a_moan+] как [npc.she] [npc.verb(feel)] глубокое пульсирующее ощущение, нарастающее у основания [npc.her] позвоночника. Без всякого предупреждения о том, что последует дальше, [npc.her]"
								+(owner.getTailCount()>1
										?" [npc.tails] внезапно [style.boldShrink(укорачивается)]."
										:" [npc.tail] внезапно [style.boldShrink(становится короче)].")
								+ "<br/>[npc.She] теперь [npc.has] [style.boldTfGeneric([npc.a_tailLength] [npc.tail])]"+heightPercentageDescription+"!"
						+ "</p>");
			}
		}
		return "";
	}
	
	public boolean isPrehensile() {
		return tags.contains(BodyPartTag.TAIL_PREHENSILE);
	}
	
	public boolean isSuitableForSleepHugging() {
		return tags.contains(BodyPartTag.TAIL_SLEEP_HUGGING);
	}
	
	public boolean isSuitableForAttack() {
		return tags.contains(BodyPartTag.TAIL_ATTACK);
	}

	public boolean isOvipositor() {
		return tags.contains(BodyPartTag.TAIL_OVIPOSITOR);
	}

	@Override
	public TFModifier getTFModifier() {
		return this == TailType.NONE ? TFModifier.REMOVAL : getTFTypeModifier(TailType.getTailTypes(race));
	}
	
	public boolean hasSpinneret() {
		return spinneret;
	}
}
