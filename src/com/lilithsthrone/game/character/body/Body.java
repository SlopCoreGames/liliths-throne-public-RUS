package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTongueType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringSkinToneColorHelper;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyPartTypeInterface;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodyShape;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.FluidTypeBase;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.StartingSkinTone;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FeralAttributes;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Builder;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4.0
 * @author Innoxia
 */
public class Body implements XMLSaving {
	
	/** This determines the maximum amount of fluid (in mL) that can be stored in the SexAreaOrifice.VAGINA and SexAreaOrifice.URETHRA_VAGINA while pregnant. */
	public static final int MAXIMUM_CREAMPIE_WHILE_PREGNANT = 250;
	
	// Required:
	private Arm arm;
	private Ass ass;
	private Breast breast;
	private Face face;
	private Eye eye;
	private Ear ear;
	private Hair hair;
	private Leg leg;
	private Torso torso;
	private BodyMaterial bodyMaterial;
	protected AbstractSubspecies fleshSubspecies;

	// Optional:
	private Antenna antenna;
	private BreastCrotch breastCrotch;
	private Horn horn;
	private Penis penis;
	private Tail tail;
	private Tentacle tentacle;
	private Vagina vagina;
	private Wing wing;

	private OrificeSpinneret spinneret;
	
	private GenitalArrangement genitalArrangement;
	
	private boolean feral;
	
	private Map<AbstractRace, Integer> raceWeightMap = new ConcurrentHashMap<>();
	private AbstractSubspecies subspecies;
	/** This keeps track of what this body's subspecies was at the moment of last being saved. it's only used in BodyChanging.java as part of save/load transformation presets. */
	private AbstractSubspecies loadedSubspecies;
	private RaceStage raceStage;
	private boolean piercedStomach = false;
	private AbstractSubspecies subspeciesOverride = null;
	private AbstractSubspecies halfDemonSubspecies = null;
	private int height;
	private int femininity;
	private int bodySize;
	private int muscle;
	private BodyHair pubicHair;

	private Set<AbstractBodyCoveringType> heavyMakeup;
	private Map<AbstractBodyCoveringType, Covering> coverings;
	private Set<AbstractBodyCoveringType> coveringsDiscovered;

	private List<BodyPartInterface> allBodyParts;
	private List<BodyPartInterface> allBodyPartsExtended;
	
	private boolean takesAfterMother = true;
	
	
	public static class BodyBuilder implements Builder<Body> {
		// Required parameters:
		private final Arm arm;
		private final Ass ass;
		private final Breast breast;
		private final Face face;
		private final Eye eye;
		private final Ear ear;
		private final Hair hair;
		private final Leg leg;
		private final Torso torso;
		private final BodyMaterial bodyMaterial;
		private GenitalArrangement genitalArrangement;
		private final int height;
		private final int femininity, bodySize, muscle;
		
		// Optional parameters - initialised to null values:
		private Antenna antenna = new Antenna(AntennaType.NONE, 0);
		private BreastCrotch breastCrotch = new BreastCrotch(BreastType.NONE, BreastShape.ROUND, 0, 0, 1, 1, NippleShape.NORMAL, 1, AreolaeShape.NORMAL, 1, 0, 2, 0, 0, true);
		private Horn horn = new Horn(HornType.NONE, 0);
		private Penis penis = new Penis(PenisType.NONE, 0, false, 0, 0, 0, 0);
		private Tail tail = new Tail(TailType.NONE);
		private Tentacle tentacle = new Tentacle(TentacleType.NONE);
		private Vagina vagina = new Vagina(VaginaType.NONE, 0, 0, 0, 0, 0, 2, 3, 3, true);
		private Wing wing = new Wing(WingType.NONE, 0);
		private OrificeSpinneret spinneret = new OrificeSpinneret();

		public BodyBuilder(Arm arm, Ass ass, Breast breast, Face face, Eye eye, Ear ear, Hair hair, Leg leg, Torso torso, BodyMaterial bodyMaterial, GenitalArrangement genitalArrangement, int height, int femininity, int bodySize, int muscle) {
			this.arm = arm;
			this.ass = ass;
			this.breast = breast;
			this.face = face;
			this.eye = eye;
			this.ear = ear;
			this.hair = hair;
			this.leg = leg;
			this.torso = torso;
			this.bodyMaterial = bodyMaterial;
			this.genitalArrangement = genitalArrangement;
			this.height = height;
			this.femininity = femininity;
			this.bodySize = bodySize;
			this.muscle = muscle;
		}
		
		public BodyBuilder antenna(Antenna antenna) {
			this.antenna = antenna;
			return this;
		}
		
		public BodyBuilder breastCrotch(BreastCrotch breastCrotch) {
			this.breastCrotch = breastCrotch;
			return this;
		}

		public BodyBuilder horn(Horn horn) {
			this.horn = horn;
			return this;
		}

		public BodyBuilder penis(Penis penis) {
			this.penis = penis;
			return this;
		}
		
		public BodyBuilder tail(Tail tail) {
			this.tail = tail;
			return this;
		}

		public BodyBuilder tentacle(Tentacle tentacle) {
			this.tentacle = tentacle;
			return this;
		}

		public BodyBuilder vagina(Vagina vagina) {
			this.vagina = vagina;
			return this;
		}

		public BodyBuilder wing(Wing wing) {
			this.wing = wing;
			return this;
		}

		public BodyBuilder spinneret(OrificeSpinneret spinneret) {
			this.spinneret = spinneret;
			return this;
		}

		@Override
		public Body build() {
			return new Body(this);
		}
	}

	private Body(BodyBuilder builder) {
		antenna = builder.antenna;
		arm = builder.arm;
		ass = builder.ass;
		breast = builder.breast;
		breastCrotch = builder.breastCrotch;
		face = builder.face;
		eye = builder.eye;
		ear = builder.ear;
		hair = builder.hair;
		leg = builder.leg;
		torso = builder.torso;
		horn = builder.horn;
		penis = builder.penis;
		tail = builder.tail;
		tentacle = builder.tentacle;
		vagina = builder.vagina;
		wing = builder.wing;
		
		spinneret = builder.spinneret;
		
		bodyMaterial = builder.bodyMaterial;
		genitalArrangement = builder.genitalArrangement;
		
		feral = false;
		
		height = builder.height;
		femininity = builder.femininity;
		bodySize = builder.bodySize;
		muscle = builder.muscle;
		
		this.pubicHair = BodyHair.ZERO_NONE;
		
		handleAllBodyPartsList();
		
		coverings = new HashMap<>();
		heavyMakeup = new HashSet<>();

		applyStartingCoveringValues();
		
		coveringsDiscovered = new HashSet<>();
		for(BodyPartInterface bp : allBodyPartsExtended) {
			if(bp.getBodyCoveringType(this)!=null) {
				coveringsDiscovered.add(bp.getBodyCoveringType(this));
			}
		}
		
		addDiscoveredBodyCoveringsFromMaterial(bodyMaterial);
		
		calculateRace(null);
		
		coveringsDiscovered.add(getBodyHairCoveringType(this.getRace()));
	}
	
	public Body(Body bodyToCopy) {
		// Core:
		this.genitalArrangement = bodyToCopy.getGenitalArrangement();
		this.feral = bodyToCopy.isFeral();
		this.subspecies = bodyToCopy.getSubspecies();
		this.piercedStomach = bodyToCopy.isPiercedStomach();
		if(bodyToCopy.getSubspeciesOverride()!=null) {
			this.subspeciesOverride = bodyToCopy.getSubspeciesOverride();
		}
		this.height = bodyToCopy.getHeightValue();
		this.femininity = bodyToCopy.getFemininity();
		this.bodySize = bodyToCopy.getBodySize();
		this.muscle = bodyToCopy.getMuscle();
		this.pubicHair = bodyToCopy.getPubicHair();
		this.bodyMaterial = bodyToCopy.getBodyMaterial();
		this.takesAfterMother = bodyToCopy.isTakesAfterMother();
		
		// Coverings:
		
		this.coverings = new HashMap<>();
		for(Entry<AbstractBodyCoveringType, Covering> entry : bodyToCopy.coverings.entrySet()) {
			coverings.put(entry.getKey(), new Covering(entry.getValue()));
		}
		this.coveringsDiscovered = new HashSet<>(bodyToCopy.coveringsDiscovered);
		
		this.heavyMakeup = new HashSet<>(bodyToCopy.heavyMakeup);
		
		// Body parts:
		
		this.antenna = new Antenna(bodyToCopy.antenna);
		this.arm = new Arm(bodyToCopy.arm);
		this.ass = new Ass(bodyToCopy.ass);
		this.breast = new Breast(bodyToCopy.breast);
		this.breastCrotch = new BreastCrotch(bodyToCopy.breastCrotch);
		this.ear = new Ear(bodyToCopy.ear);
		this.eye = new Eye(bodyToCopy.eye);
		this.face = new Face(bodyToCopy.face);
		this.hair = new Hair(bodyToCopy.hair);
		this.horn = new Horn(bodyToCopy.horn);
		this.leg = new Leg(bodyToCopy.leg);
		this.penis = new Penis(bodyToCopy.penis);
		this.spinneret = new OrificeSpinneret(bodyToCopy.spinneret);
		this.torso = new Torso(bodyToCopy.torso);
		this.tail = new Tail(bodyToCopy.tail);
		this.tentacle = new Tentacle(bodyToCopy.tentacle);
		this.vagina = new Vagina(bodyToCopy.vagina);
		this.wing = new Wing(bodyToCopy.wing);

		handleAllBodyPartsList();
		calculateRace(null);
	}
	
	public void handleAllBodyPartsList() {
		allBodyParts = new ArrayList<>();
		allBodyParts.add(antenna);
		allBodyParts.add(arm);
		allBodyParts.add(ass);
		allBodyParts.add(breast);
		allBodyParts.add(breastCrotch);
		allBodyParts.add(face);
		allBodyParts.add(eye);
		allBodyParts.add(ear);
		allBodyParts.add(hair);
		allBodyParts.add(leg);
		allBodyParts.add(torso);
		allBodyParts.add(horn);
		allBodyParts.add(penis);
		allBodyParts.add(tail);
		allBodyParts.add(tentacle);
		allBodyParts.add(vagina);
		allBodyParts.add(wing);

		allBodyPartsExtended = new ArrayList<>();
		allBodyPartsExtended.addAll(allBodyParts);
		allBodyPartsExtended.add(ass.getAnus());
		allBodyPartsExtended.add(breast.getNipples());
		allBodyPartsExtended.add(breastCrotch.getNipples());
		allBodyPartsExtended.add(face.getMouth());
		allBodyPartsExtended.add(face.getTongue());
	}
	
	public void addDiscoveredBodyCoveringsFromMaterial(BodyMaterial bodyMaterial) {
		if(bodyMaterial!=BodyMaterial.FLESH) {
			for(BodyCoveringCategory cat : BodyCoveringCategory.values()) {
				if(cat.isInfluencedByMaterialType()) {
					coveringsDiscovered.add(BodyCoveringType.getMaterialBodyCoveringType(bodyMaterial, cat));
				}
			}
			
		} else {
			coveringsDiscovered.add(BodyCoveringType.EYE_SCLERA);
			coveringsDiscovered.add(BodyCoveringType.NIPPLES);
			coveringsDiscovered.add(BodyCoveringType.NIPPLES_CROTCH);
			coveringsDiscovered.add(BodyCoveringType.TONGUE);
			coveringsDiscovered.add(BodyCoveringType.MOUTH);
			coveringsDiscovered.add(BodyCoveringType.ANUS);
			coveringsDiscovered.add(BodyCoveringType.VAGINA);
			coveringsDiscovered.add(BodyCoveringType.PENIS);
			coveringsDiscovered.add(BodyCoveringType.SPINNERET);
		}
	}
	
	public static AbstractBodyCoveringType getBodyHairCoveringType(AbstractRace race) {
		return race.getRacialBody().getBodyHairType();
	}
	
	private void applyStartingCoveringValues() {
		
		// Everything is based on human skin value:
		StartingSkinTone tone = StartingSkinTone.values()[Util.random.nextInt(StartingSkinTone.values().length)];
		
		for(AbstractBodyCoveringType s : BodyCoveringType.getAllBodyCoveringTypes()) {
			// Specials:
			// orifice exterior/interior
			// makeup
			if(s == BodyCoveringType.MAKEUP_BLUSHER
					|| s == BodyCoveringType.MAKEUP_EYE_LINER
					|| s == BodyCoveringType.MAKEUP_EYE_SHADOW
					|| s == BodyCoveringType.MAKEUP_LIPSTICK
					|| s == BodyCoveringType.MAKEUP_NAIL_POLISH_FEET
					|| s == BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS) {
				coverings.put(s, new Covering(s,
						CoveringPattern.NONE,
						PresetColour.COVERING_NONE, false,
						PresetColour.COVERING_NONE, false));
				continue;
			}
			
			List<Colour> colourApplicationList = BodyCoveringSkinToneColorHelper.getAcceptableColoursForPrimary(tone, s);
			Colour primary = colourApplicationList.get(Util.random.nextInt(colourApplicationList.size()));
			
			Colour secondary = primary;
			
			if(!s.getNaturalColoursSecondary().isEmpty()) {
				colourApplicationList = new ArrayList<>(BodyCoveringSkinToneColorHelper.getAcceptableColoursForSecondary(tone, s));
				colourApplicationList.remove(primary);
				if(!colourApplicationList.isEmpty()) {
					secondary = colourApplicationList.get(Util.random.nextInt(colourApplicationList.size()));
				}
			}
			
//			List<CoveringPattern> availablePatterns = new ArrayList<>(s.getNaturalPatterns().keySet());
//			if(availablePatterns.size()>1) {
//				availablePatterns.remove(CoveringPattern.FRECKLED); // Do not start with freckles
//			}
			
			CoveringPattern pattern = Util.getRandomObjectFromWeightedMap(s.getNaturalPatterns());//availablePatterns.get(Util.random.nextInt(availablePatterns.size()));
			if(pattern==CoveringPattern.EYE_IRISES) {
				pattern = CoveringPattern.EYE_IRISES_HETEROCHROMATIC;
			}
			if(pattern == CoveringPattern.EYE_IRISES_HETEROCHROMATIC) {
				if(Math.random()>0.02f) { // As it's already selected heterochromatic eyes (0.5 chance), this 0.02 chance corresponds to an overall heterochromatic chance of 0.01, or 1%
					pattern = CoveringPattern.EYE_IRISES;
				} else {
					if(primary==secondary) {
						List<Colour> secondaryIrisColours = new ArrayList<>(colourApplicationList);
						secondaryIrisColours.remove(primary);
						if(secondaryIrisColours.isEmpty()) {
							pattern = CoveringPattern.EYE_IRISES;
							secondary = primary;
						} else {
							secondary = Util.randomItemFrom(secondaryIrisColours);
						}
					}
				}
			}
			
			coverings.put(s, new Covering(s,
					pattern,
					primary, false,
					secondary, false));
		}

		updateCoverings(true, true, true, true);
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		// Core:
		Element bodyCore = doc.createElement("bodyCore");
		parentElement.appendChild(bodyCore);
		XMLUtil.addAttribute(doc, bodyCore, "version", Main.VERSION_NUMBER);
		XMLUtil.addAttribute(doc, bodyCore, "piercedStomach", String.valueOf(this.isPiercedStomach()));
		XMLUtil.addAttribute(doc, bodyCore, "height", String.valueOf(this.getHeightValue()));
		XMLUtil.addAttribute(doc, bodyCore, "femininity", String.valueOf(this.getFemininity()));
		XMLUtil.addAttribute(doc, bodyCore, "bodySize", String.valueOf(this.getBodySize()));
		XMLUtil.addAttribute(doc, bodyCore, "muscle", String.valueOf(this.getMuscle()));
		XMLUtil.addAttribute(doc, bodyCore, "pubicHair", String.valueOf(this.getPubicHair()));
		XMLUtil.addAttribute(doc, bodyCore, "bodyMaterial", String.valueOf(this.getBodyMaterial()));
		XMLUtil.addAttribute(doc, bodyCore, "genitalArrangement", String.valueOf(this.getGenitalArrangement()));
		XMLUtil.addAttribute(doc, bodyCore, "feral", String.valueOf(this.isFeral()));
		if(this.getSubspeciesOverride()!=null) {
			XMLUtil.addAttribute(doc, bodyCore, "subspeciesOverride", Subspecies.getIdFromSubspecies(this.getSubspeciesOverride()));
		}
		XMLUtil.addAttribute(doc, bodyCore, "subspecies", Subspecies.getIdFromSubspecies(this.getSubspecies()));
		XMLUtil.addAttribute(doc, bodyCore, "takesAfterMother", String.valueOf(this.isTakesAfterMother()));
		
		for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
			Covering covering = this.coverings.get(bct);
			if(this.isBodyCoveringTypesDiscovered(bct)
					|| ((bct == BodyCoveringType.MAKEUP_BLUSHER
							|| bct == BodyCoveringType.MAKEUP_EYE_LINER
							|| bct == BodyCoveringType.MAKEUP_EYE_SHADOW
							|| bct == BodyCoveringType.MAKEUP_LIPSTICK
							|| bct == BodyCoveringType.MAKEUP_NAIL_POLISH_FEET
							|| bct == BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS)
							&& covering.getPrimaryColour()!=PresetColour.COVERING_NONE)
					|| bct == BodyCoveringType.EYE_PUPILS
					|| bct.getCategory()==BodyCoveringCategory.FLUID
					|| bct == getBodyHairCoveringType(this.getRace())) {
				Element element = doc.createElement("bodyCovering");
				bodyCore.appendChild(element);
				
				XMLUtil.addAttribute(doc, element, "type", BodyCoveringType.getIdFromBodyCoveringType(bct));
				XMLUtil.addAttribute(doc, element, "pattern", covering.getPattern().toString());
				XMLUtil.addAttribute(doc, element, "modifier", covering.getModifier().toString());
				XMLUtil.addAttribute(doc, element, "c1", covering.getPrimaryColour().getId());
				if(covering.isPrimaryGlowing()) {
					XMLUtil.addAttribute(doc, element, "g1", String.valueOf(covering.isPrimaryGlowing()));
				}
				if(covering.getPrimaryColour()!=covering.getSecondaryColour()) {
					XMLUtil.addAttribute(doc, element, "c2", covering.getSecondaryColour().getId());
				}
				if(covering.isSecondaryGlowing()) {
					XMLUtil.addAttribute(doc, element, "g2", String.valueOf(covering.isSecondaryGlowing()));
				}
			}
		}
		
		if(!heavyMakeup.isEmpty()) {
			Element element = doc.createElement("heavyMakeup");
			bodyCore.appendChild(element);
			for(AbstractBodyCoveringType bct : heavyMakeup) {
				Element bctElement = doc.createElement("type");
				element.appendChild(bctElement);
				bctElement.setTextContent(BodyCoveringType.getIdFromBodyCoveringType(bct));
			}
		}
		

		// Antennae:
		Element bodyAntennae = doc.createElement("antennae");
		parentElement.appendChild(bodyAntennae);
			XMLUtil.addAttribute(doc, bodyAntennae, "type", AntennaType.getIdFromAntennaType(this.antenna.getType()));
			XMLUtil.addAttribute(doc, bodyAntennae, "rows", String.valueOf(this.antenna.getAntennaRows()));
			XMLUtil.addAttribute(doc, bodyAntennae, "length", String.valueOf(this.antenna.length));
			XMLUtil.addAttribute(doc, bodyAntennae, "antennaePerRow", String.valueOf(this.antenna.antennaePerRow));
		
		// Arm:
		Element bodyArm = doc.createElement("arm");
		parentElement.appendChild(bodyArm);
			XMLUtil.addAttribute(doc, bodyArm, "type", ArmType.getIdFromArmType(this.arm.getType()));
			XMLUtil.addAttribute(doc, bodyArm, "rows", String.valueOf(this.arm.getArmRows()));
			XMLUtil.addAttribute(doc, bodyArm, "underarmHair", this.arm.getUnderarmHair().toString());
		
		// Ass:
		Element bodyAss = doc.createElement("ass");
		parentElement.appendChild(bodyAss);
			XMLUtil.addAttribute(doc, bodyAss, "type", AssType.getIdFromAssType(this.ass.getType()));
			XMLUtil.addAttribute(doc, bodyAss, "assSize", String.valueOf(this.ass.getAssSize().getValue()));
			XMLUtil.addAttribute(doc, bodyAss, "hipSize", String.valueOf(this.ass.getHipSize().getValue()));

		Element bodyAnus = doc.createElement("anus");
		parentElement.appendChild(bodyAnus);
			XMLUtil.addAttribute(doc, bodyAnus, "wetness", String.valueOf(this.ass.anus.orificeAnus.wetness));
			XMLUtil.addAttribute(doc, bodyAnus, "depth", String.valueOf(this.ass.anus.orificeAnus.depth));
			XMLUtil.addAttribute(doc, bodyAnus, "elasticity", String.valueOf(this.ass.anus.orificeAnus.elasticity));
			XMLUtil.addAttribute(doc, bodyAnus, "plasticity", String.valueOf(this.ass.anus.orificeAnus.plasticity));
			XMLUtil.addAttribute(doc, bodyAnus, "capacity", String.valueOf(this.ass.anus.orificeAnus.capacity));
			XMLUtil.addAttribute(doc, bodyAnus, "stretchedCapacity", String.valueOf(this.ass.anus.orificeAnus.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyAnus, "virgin", String.valueOf(this.ass.anus.orificeAnus.virgin));
			XMLUtil.addAttribute(doc, bodyAnus, "bleached", String.valueOf(this.ass.anus.bleached));
			XMLUtil.addAttribute(doc, bodyAnus, "assHair", this.ass.anus.assHair.toString());
			for(OrificeModifier om : this.ass.anus.orificeAnus.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyAnus.appendChild(mod);
			}
		
		// Breasts:
		Element bodyBreast = doc.createElement("breasts");
		parentElement.appendChild(bodyBreast);
			XMLUtil.addAttribute(doc, bodyBreast, "type", BreastType.getIdFromBreastType(this.breast.getType()));
			XMLUtil.addAttribute(doc, bodyBreast, "shape", this.breast.shape.toString());
			XMLUtil.addAttribute(doc, bodyBreast, "size", String.valueOf(this.breast.size));
			XMLUtil.addAttribute(doc, bodyBreast, "rows", String.valueOf(this.breast.rows));
			XMLUtil.addAttribute(doc, bodyBreast, "milkStorage", String.valueOf(this.breast.milkStorage));
			XMLUtil.addAttribute(doc, bodyBreast, "storedMilk", String.valueOf(this.breast.milkStored));
			XMLUtil.addAttribute(doc, bodyBreast, "milkRegeneration", String.valueOf(this.breast.milkRegeneration));
			XMLUtil.addAttribute(doc, bodyBreast, "nippleCountPerBreast", String.valueOf(this.breast.nippleCountPerBreast));

		Element bodyNipple = doc.createElement("nipples");
		parentElement.appendChild(bodyNipple);
			XMLUtil.addAttribute(doc, bodyNipple, "depth", String.valueOf(this.breast.nipples.orificeNipples.depth));
			XMLUtil.addAttribute(doc, bodyNipple, "elasticity", String.valueOf(this.breast.nipples.orificeNipples.elasticity));
			XMLUtil.addAttribute(doc, bodyNipple, "plasticity", String.valueOf(this.breast.nipples.orificeNipples.plasticity));
			XMLUtil.addAttribute(doc, bodyNipple, "capacity", String.valueOf(this.breast.nipples.orificeNipples.capacity));
			XMLUtil.addAttribute(doc, bodyNipple, "stretchedCapacity", String.valueOf(this.breast.nipples.orificeNipples.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyNipple, "virgin", String.valueOf(this.breast.nipples.orificeNipples.virgin));
			XMLUtil.addAttribute(doc, bodyNipple, "pierced", String.valueOf(this.breast.nipples.pierced));
			XMLUtil.addAttribute(doc, bodyNipple, "nippleSize", String.valueOf(this.breast.nipples.nippleSize));
			XMLUtil.addAttribute(doc, bodyNipple, "nippleShape", this.breast.nipples.nippleShape.toString());
			XMLUtil.addAttribute(doc, bodyNipple, "areolaeSize", String.valueOf(this.breast.nipples.areolaeSize));
			XMLUtil.addAttribute(doc, bodyNipple, "areolaeShape", this.breast.nipples.areolaeShape.toString());
			for(OrificeModifier om : this.breast.nipples.orificeNipples.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyNipple.appendChild(mod);
			}
			
		this.breast.milk.saveAsXML("milk", parentElement, doc);
		
		// Crotch Breasts:
		Element bodyCrotchBreast = doc.createElement("breastsCrotch");
		parentElement.appendChild(bodyCrotchBreast);
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "type", BreastType.getIdFromBreastType(this.breastCrotch.getType()));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "shape", this.breastCrotch.shape.toString());
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "size", String.valueOf(this.breastCrotch.size));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "rows", String.valueOf(this.breastCrotch.rows));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "milkStorage", String.valueOf(this.breastCrotch.milkStorage));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "storedMilk", String.valueOf(this.breastCrotch.milkStored));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "milkRegeneration", String.valueOf(this.breastCrotch.milkRegeneration));
			XMLUtil.addAttribute(doc, bodyCrotchBreast, "nippleCountPerBreast", String.valueOf(this.breastCrotch.nippleCountPerBreast));

		Element bodyCrotchNipple = doc.createElement("nipplesCrotch");
		parentElement.appendChild(bodyCrotchNipple);
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "depth", String.valueOf(this.breastCrotch.nipples.orificeNipples.depth));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "elasticity", String.valueOf(this.breastCrotch.nipples.orificeNipples.elasticity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "plasticity", String.valueOf(this.breastCrotch.nipples.orificeNipples.plasticity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "capacity", String.valueOf(this.breastCrotch.nipples.orificeNipples.capacity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "stretchedCapacity", String.valueOf(this.breastCrotch.nipples.orificeNipples.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "virgin", String.valueOf(this.breastCrotch.nipples.orificeNipples.virgin));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "pierced", String.valueOf(this.breastCrotch.nipples.pierced));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "nippleSize", String.valueOf(this.breastCrotch.nipples.nippleSize));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "nippleShape", this.breastCrotch.nipples.nippleShape.toString());
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "areolaeSize", String.valueOf(this.breastCrotch.nipples.areolaeSize));
			XMLUtil.addAttribute(doc, bodyCrotchNipple, "areolaeShape", this.breastCrotch.nipples.areolaeShape.toString());
			for(OrificeModifier om : this.breastCrotch.nipples.orificeNipples.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyCrotchNipple.appendChild(mod);
			}
			
		this.breastCrotch.milk.saveAsXML("milkCrotch", parentElement, doc);
		
		
		// Ear:
		Element bodyEar = doc.createElement("ear");
		parentElement.appendChild(bodyEar);
			XMLUtil.addAttribute(doc, bodyEar, "type", EarType.getIdFromEarType(this.ear.type));
			XMLUtil.addAttribute(doc, bodyEar, "pierced", String.valueOf(this.ear.pierced));

		// Eye:
		Element bodyEye = doc.createElement("eye");
		parentElement.appendChild(bodyEye);
			XMLUtil.addAttribute(doc, bodyEye, "type", EyeType.getIdFromEyeType(this.eye.type));
			XMLUtil.addAttribute(doc, bodyEye, "eyePairs", String.valueOf(this.eye.eyePairs));
			XMLUtil.addAttribute(doc, bodyEye, "irisShape", this.eye.irisShape.toString());
			XMLUtil.addAttribute(doc, bodyEye, "pupilShape", this.eye.pupilShape.toString());
		
		// Face:
		Element bodyFace = doc.createElement("face");
		parentElement.appendChild(bodyFace);
			XMLUtil.addAttribute(doc, bodyFace, "type", FaceType.getIdFromFaceType(this.face.type));
			XMLUtil.addAttribute(doc, bodyFace, "piercedNose", String.valueOf(this.face.piercedNose));
			XMLUtil.addAttribute(doc, bodyFace, "facialHair", this.face.facialHair.toString());

		Element bodyMouth = doc.createElement("mouth");
		parentElement.appendChild(bodyMouth);
			XMLUtil.addAttribute(doc, bodyMouth, "depth", String.valueOf(this.face.mouth.orificeMouth.depth));
			XMLUtil.addAttribute(doc, bodyMouth, "elasticity", String.valueOf(this.face.mouth.orificeMouth.elasticity));
			XMLUtil.addAttribute(doc, bodyMouth, "plasticity", String.valueOf(this.face.mouth.orificeMouth.plasticity));
			XMLUtil.addAttribute(doc, bodyMouth, "capacity", String.valueOf(this.face.mouth.orificeMouth.capacity));
			XMLUtil.addAttribute(doc, bodyMouth, "wetness", String.valueOf(this.face.mouth.orificeMouth.wetness));
			XMLUtil.addAttribute(doc, bodyMouth, "stretchedCapacity", String.valueOf(this.face.mouth.orificeMouth.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyMouth, "virgin", String.valueOf(this.face.mouth.orificeMouth.virgin));
			XMLUtil.addAttribute(doc, bodyMouth, "piercedLip", String.valueOf(this.face.mouth.piercedLip));
			XMLUtil.addAttribute(doc, bodyMouth, "lipSize", String.valueOf(this.face.mouth.lipSize));
			for(OrificeModifier om : this.face.mouth.orificeMouth.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyMouth.appendChild(mod);
			}
			
		Element bodyTongue = doc.createElement("tongue");
		parentElement.appendChild(bodyTongue);
			XMLUtil.addAttribute(doc, bodyTongue, "piercedTongue", String.valueOf(this.face.tongue.pierced));
			XMLUtil.addAttribute(doc, bodyTongue, "tongueLength", String.valueOf(this.face.tongue.tongueLength));
			for(TongueModifier tm : this.face.tongue.tongueModifiers) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(tm.toString());
				bodyTongue.appendChild(mod);
			}
			
		
		// Hair:
		Element bodyHair = doc.createElement("hair");
		parentElement.appendChild(bodyHair);
			XMLUtil.addAttribute(doc, bodyHair, "type", HairType.getIdFromHairType(this.hair.type));
			XMLUtil.addAttribute(doc, bodyHair, "length", String.valueOf(this.hair.length));
			XMLUtil.addAttribute(doc, bodyHair, "hairStyle", this.hair.style.toString());
			XMLUtil.addAttribute(doc, bodyHair, "neckFluff", String.valueOf(this.hair.neckFluff));
		
		// Horn:
		Element bodyHorn = doc.createElement("horn");
		parentElement.appendChild(bodyHorn);
			XMLUtil.addAttribute(doc, bodyHorn, "type", HornType.getIdFromHornType(this.horn.type));
			XMLUtil.addAttribute(doc, bodyHorn, "length", String.valueOf(this.horn.length));
			XMLUtil.addAttribute(doc, bodyHorn, "rows", String.valueOf(this.horn.rows));
			XMLUtil.addAttribute(doc, bodyHorn, "hornsPerRow", String.valueOf(this.horn.hornsPerRow));
		
		// Leg:
		Element bodyLeg = doc.createElement("leg");
		parentElement.appendChild(bodyLeg);
			XMLUtil.addAttribute(doc, bodyLeg, "type", LegType.getIdFromLegType(this.leg.type));
			XMLUtil.addAttribute(doc, bodyLeg, "footStructure", this.leg.footStructure.toString());
			XMLUtil.addAttribute(doc, bodyLeg, "configuration", this.leg.legConfiguration.toString());
			XMLUtil.addAttribute(doc, bodyLeg, "tailLength", String.valueOf(this.leg.lengthAsPercentageOfHeight));
		
		// Penis:
		Element bodyPenis = doc.createElement("penis");
		parentElement.appendChild(bodyPenis);
			XMLUtil.addAttribute(doc, bodyPenis, "type", PenisType.getIdFromPenisType(this.penis.type));
			if(this.penis.previousType!=null) {
				XMLUtil.addAttribute(doc, bodyPenis, "previousType", PenisType.getIdFromPenisType(this.penis.previousType));
			}
			XMLUtil.addAttribute(doc, bodyPenis, "size", String.valueOf(this.penis.length));
			XMLUtil.addAttribute(doc, bodyPenis, "girth", String.valueOf(this.penis.girth));
			XMLUtil.addAttribute(doc, bodyPenis, "pierced", String.valueOf(this.penis.pierced));
			XMLUtil.addAttribute(doc, bodyPenis, "virgin", String.valueOf(this.penis.virgin));
			for(PenetrationModifier pm : this.penis.getPenisModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(pm.toString());
				bodyPenis.appendChild(mod);
			}
			XMLUtil.addAttribute(doc, bodyPenis, "depth", String.valueOf(this.penis.orificeUrethra.depth));
			XMLUtil.addAttribute(doc, bodyPenis, "elasticity", String.valueOf(this.penis.orificeUrethra.elasticity));
			XMLUtil.addAttribute(doc, bodyPenis, "plasticity", String.valueOf(this.penis.orificeUrethra.plasticity));
			XMLUtil.addAttribute(doc, bodyPenis, "capacity", String.valueOf(this.penis.orificeUrethra.capacity));
			XMLUtil.addAttribute(doc, bodyPenis, "stretchedCapacity", String.valueOf(this.penis.orificeUrethra.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyPenis, "urethraVirgin", String.valueOf(this.penis.orificeUrethra.virgin));
			for(OrificeModifier om : this.penis.orificeUrethra.getOrificeModifiers()) {
				Element mod = doc.createElement("modUrethra");
				mod.setTextContent(om.toString());
				bodyPenis.appendChild(mod);
			}
			
		Element bodyTesticle = doc.createElement("testicles");
		parentElement.appendChild(bodyTesticle);
			XMLUtil.addAttribute(doc, bodyTesticle, "testicleSize", String.valueOf(this.penis.testicle.testicleSize));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumStorage", String.valueOf(this.penis.testicle.cumStorage));
			XMLUtil.addAttribute(doc, bodyTesticle, "storedCum", String.valueOf(this.penis.testicle.cumStored));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumRegeneration", String.valueOf(this.penis.testicle.cumRegeneration));
			XMLUtil.addAttribute(doc, bodyTesticle, "cumExpulsion", String.valueOf(this.penis.testicle.cumExpulsion));
			XMLUtil.addAttribute(doc, bodyTesticle, "numberOfTesticles", String.valueOf(this.penis.testicle.testicleCount));
			XMLUtil.addAttribute(doc, bodyTesticle, "internal", String.valueOf(this.penis.testicle.internal));
		
		this.penis.testicle.cum.saveAsXML("cum", parentElement, doc);

		// Spinneret:
		Element bodySpinneret = doc.createElement("spinneret");
		parentElement.appendChild(bodySpinneret);
			XMLUtil.addAttribute(doc, bodySpinneret, "wetness", String.valueOf(this.spinneret.wetness));
			XMLUtil.addAttribute(doc, bodySpinneret, "depth", String.valueOf(this.spinneret.depth));
			XMLUtil.addAttribute(doc, bodySpinneret, "elasticity", String.valueOf(this.spinneret.elasticity));
			XMLUtil.addAttribute(doc, bodySpinneret, "plasticity", String.valueOf(this.spinneret.plasticity));
			XMLUtil.addAttribute(doc, bodySpinneret, "capacity", String.valueOf(this.spinneret.capacity));
			XMLUtil.addAttribute(doc, bodySpinneret, "stretchedCapacity", String.valueOf(this.spinneret.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodySpinneret, "virgin", String.valueOf(this.spinneret.virgin));
			for(OrificeModifier om : this.spinneret.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodySpinneret.appendChild(mod);
			}
			
		// Torso:
		Element bodyTorso = doc.createElement("torso");
		parentElement.appendChild(bodyTorso);
			XMLUtil.addAttribute(doc, bodyTorso, "type", TorsoType.getIdFromTorsoType(this.torso.type));
		
		// Tail:
		Element bodyTail = doc.createElement("tail");
		parentElement.appendChild(bodyTail);
			XMLUtil.addAttribute(doc, bodyTail, "type", TailType.getIdFromTailType(this.tail.type));
			XMLUtil.addAttribute(doc, bodyTail, "count", String.valueOf(this.tail.tailCount));
			XMLUtil.addAttribute(doc, bodyTail, "girth", String.valueOf(this.tail.girth));
			XMLUtil.addAttribute(doc, bodyTail, "length", String.valueOf(this.tail.lengthAsPercentageOfHeight));
		
		// Tail:
		Element bodyTentacle = doc.createElement("tentacle");
		parentElement.appendChild(bodyTentacle);
			XMLUtil.addAttribute(doc, bodyTentacle, "type", TentacleType.getIdFromTentacleType(this.tentacle.type));
			XMLUtil.addAttribute(doc, bodyTentacle, "count", String.valueOf(this.tentacle.tentacleCount));
			XMLUtil.addAttribute(doc, bodyTentacle, "girth", String.valueOf(this.tentacle.girth));
			XMLUtil.addAttribute(doc, bodyTentacle, "length", String.valueOf(this.tentacle.lengthAsPercentageOfHeight));
			
		// Vagina
		Element bodyVagina = doc.createElement("vagina");
		parentElement.appendChild(bodyVagina);
			XMLUtil.addAttribute(doc, bodyVagina, "type", VaginaType.getIdFromVaginaType(this.vagina.type));
			XMLUtil.addAttribute(doc, bodyVagina, "labiaSize", String.valueOf(this.vagina.labiaSize));
			XMLUtil.addAttribute(doc, bodyVagina, "clitSize", String.valueOf(this.vagina.clitoris.clitSize));
			XMLUtil.addAttribute(doc, bodyVagina, "clitGirth", String.valueOf(this.vagina.clitoris.girth));
			for(PenetrationModifier pm : this.vagina.clitoris.getClitorisModifiers()) {
				Element mod = doc.createElement("modClit");
				mod.setTextContent(pm.toString());
				bodyVagina.appendChild(mod);
			}
			XMLUtil.addAttribute(doc, bodyVagina, "pierced", String.valueOf(this.vagina.pierced));
			XMLUtil.addAttribute(doc, bodyVagina, "eggLayer", String.valueOf(this.vagina.eggLayer));
			
			XMLUtil.addAttribute(doc, bodyVagina, "wetness", String.valueOf(this.vagina.orificeVagina.wetness));
			XMLUtil.addAttribute(doc, bodyVagina, "depth", String.valueOf(this.vagina.orificeVagina.depth));
			XMLUtil.addAttribute(doc, bodyVagina, "elasticity", String.valueOf(this.vagina.orificeVagina.elasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "plasticity", String.valueOf(this.vagina.orificeVagina.plasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "capacity", String.valueOf(this.vagina.orificeVagina.capacity));
			XMLUtil.addAttribute(doc, bodyVagina, "stretchedCapacity", String.valueOf(this.vagina.orificeVagina.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyVagina, "virgin", String.valueOf(this.vagina.orificeVagina.virgin));
			XMLUtil.addAttribute(doc, bodyVagina, "hymen", String.valueOf(this.vagina.orificeVagina.hymen));
			XMLUtil.addAttribute(doc, bodyVagina, "squirter", String.valueOf(this.vagina.orificeVagina.squirter));
			for(OrificeModifier om : this.vagina.orificeVagina.getOrificeModifiers()) {
				Element mod = doc.createElement("mod");
				mod.setTextContent(om.toString());
				bodyVagina.appendChild(mod);
			}

			XMLUtil.addAttribute(doc, bodyVagina, "urethraDepth", String.valueOf(this.vagina.orificeUrethra.depth));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraElasticity", String.valueOf(this.vagina.orificeUrethra.elasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraPlasticity", String.valueOf(this.vagina.orificeUrethra.plasticity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraCapacity", String.valueOf(this.vagina.orificeUrethra.capacity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraStretchedCapacity", String.valueOf(this.vagina.orificeUrethra.stretchedCapacity));
			XMLUtil.addAttribute(doc, bodyVagina, "urethraVirgin", String.valueOf(this.vagina.orificeUrethra.virgin));
			for(OrificeModifier om : this.vagina.orificeUrethra.getOrificeModifiers()) {
				Element mod = doc.createElement("modUrethra");
				mod.setTextContent(om.toString());
				bodyVagina.appendChild(mod);
			}
			
		this.vagina.girlcum.saveAsXML("girlcum", parentElement, doc);

		// Wing:
		Element bodyWing = doc.createElement("wing");
		parentElement.appendChild(bodyWing);
		XMLUtil.addAttribute(doc, bodyWing, "type", WingType.getIdFromWingType(this.wing.type));
		XMLUtil.addAttribute(doc, bodyWing, "size", String.valueOf(this.wing.size));

//		System.out.println("Difference1: "+(System.nanoTime()-timeStart)/1000000000f);
		
		return parentElement;
	}

	
	private void setBodyCoveringForXMLImport(AbstractBodyCoveringType bct, CoveringPattern pattern, CoveringModifier modifier, Colour primary, boolean primaryGlow, Colour secondary, boolean secondaryGlow) {
		this.getCoverings().put(bct, new Covering(bct, pattern, modifier, primary, primaryGlow, secondary, secondaryGlow));
	}
	private void setBodyCoveringForXMLImport(AbstractBodyCoveringType bct, CoveringPattern pattern, Colour primary, boolean primaryGlow, Colour secondary, boolean secondaryGlow) {
		this.getCoverings().put(bct, new Covering(bct, pattern, primary, primaryGlow, secondary, secondaryGlow));
	}
	
	public static Body loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		
		// **************** Core **************** //
		
		Element element = (Element) parentElement.getElementsByTagName("bodyCore").item(0);
		
		String version = element.getAttribute("version");
		
		int importedFemininity = (Integer.valueOf(element.getAttribute("femininity")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set femininity: "+Integer.valueOf(element.getAttribute("femininity")));
		
		int importedHeight =(Integer.valueOf(element.getAttribute("height")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set height: "+Integer.valueOf(element.getAttribute("height")));
		
		int importedBodySize = (Integer.valueOf(element.getAttribute("bodySize")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set body size: "+Integer.valueOf(element.getAttribute("bodySize")));
	
		int importedMuscle = (Integer.valueOf(element.getAttribute("muscle")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set muscle: "+Integer.valueOf(element.getAttribute("muscle")));
		
		GenitalArrangement importedGenitalArrangement = GenitalArrangement.NORMAL;
		if(element.getAttribute("genitalArrangement") != null && !element.getAttribute("genitalArrangement").isEmpty()) {
			importedGenitalArrangement = GenitalArrangement.valueOf(element.getAttribute("genitalArrangement"));
		}
		
		BodyMaterial importedBodyMaterial = BodyMaterial.FLESH;
		if(element.getAttribute("bodyMaterial") != null && !element.getAttribute("bodyMaterial").isEmpty()) {
			importedBodyMaterial = BodyMaterial.valueOf(element.getAttribute("bodyMaterial"));
		}
		
		boolean feralBody = false;
		if(element.getAttribute("feral") != null && !element.getAttribute("feral").isEmpty()) {
			feralBody = Boolean.valueOf(element.getAttribute("feral"));
		}
		
		AbstractSubspecies importedSubspeciesOverride = null;
		try {
			if(element.getAttribute("subspeciesOverride") != null && !element.getAttribute("subspeciesOverride").isEmpty()) {
				importedSubspeciesOverride = Subspecies.getSubspeciesFromId(element.getAttribute("subspeciesOverride"));
			}
		} catch(Exception ex) {	
		}

		AbstractSubspecies importedLoadedSubspecies = null;
		try {
			if(element.getAttribute("subspecies") != null && !element.getAttribute("subspecies").isEmpty()) {
				importedLoadedSubspecies = Subspecies.getSubspeciesFromId(element.getAttribute("subspecies"));
			}
		} catch(Exception ex) {	
		}
		
		
		
		// **************** Antenna **************** //
		
		Element antennae = (Element)parentElement.getElementsByTagName("antennae").item(0);
		
		Antenna importedAntenna = new Antenna(AntennaType.getAntennaTypeFromId(antennae.getAttribute("type")), 0);
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Antennae:"+ "<br/>type: "+importedAntenna.getType());
		importedAntenna.setAntennaRows(null, Integer.valueOf(antennae.getAttribute("rows")));
		if(!antennae.getAttribute("length").isEmpty()) {
			importedAntenna.length = Integer.valueOf(antennae.getAttribute("length"));
		}
		if(!antennae.getAttribute("antennaePerRow").isEmpty()) {
			importedAntenna.antennaePerRow = Integer.valueOf(antennae.getAttribute("antennaePerRow"));
		}
		
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>rows: "+importedAntenna.getAntennaRows());
		
		
		// **************** Arm **************** //
		
		Element arm = (Element)parentElement.getElementsByTagName("arm").item(0);
		
		Arm importedArm = new Arm(ArmType.getArmTypeFromId(arm.getAttribute("type")), Integer.valueOf(arm.getAttribute("rows")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Arm:"+ "<br/>type: "+importedArm.getType());

		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>rows: "+importedArm.getArmRows());

		try {
			importedArm.underarmHair = BodyHair.valueOf(arm.getAttribute("underarmHair"));
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/>underarm hair: "+importedArm.getUnderarmHair());
		} catch(IllegalArgumentException e) {
			importedArm.underarmHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "Волосы под мышками <br/>: СТАРОЕ ЗНАЧЕНИЕ - Установлено NONE");
		}
		
		// **************** Ass **************** //
		
		Element ass = (Element)parentElement.getElementsByTagName("ass").item(0);
		Element anus = (Element)parentElement.getElementsByTagName("anus").item(0);
		
		int depth = OrificeDepth.TWO_AVERAGE.getValue();
		String depthAttribute = anus.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Ass importedAss = new Ass(AssType.getAssTypeFromId(ass.getAttribute("type")),
				Integer.valueOf(ass.getAttribute("assSize")),
				Integer.valueOf(ass.getAttribute("hipSize")),
				Integer.valueOf(anus.getAttribute("wetness")),
				handleCapacityLoading(Float.valueOf(anus.getAttribute("capacity"))),
				depth,
				Integer.valueOf(anus.getAttribute("elasticity")),
				Integer.valueOf(anus.getAttribute("plasticity")),
				Boolean.valueOf(anus.getAttribute("virgin")));

		importedAss.anus.orificeAnus.stretchedCapacity = handleCapacityLoading(Float.valueOf(anus.getAttribute("stretchedCapacity")));
		importedAss.anus.bleached = (Boolean.valueOf(anus.getAttribute("bleached")));
		try {
			importedAss.anus.assHair = (BodyHair.valueOf(anus.getAttribute("assHair")));
		} catch(IllegalArgumentException e) {
			importedAss.anus.assHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "Волосы на заднице <br/>: СТАРОЕ ЗНАЧЕНИЕ - Установлено NONE");
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Ass:"
				+ "<br/>type: "+importedAss.getType()
				+ "<br/>assSize: "+importedAss.getAssSize()
				+ "<br/>hipSize: "+importedAss.getHipSize());
		
		if (anus != null) {
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Anus:"
					+ "<br/>wetness: "+importedAss.anus.orificeAnus.wetness
					+ "<br/>depth: "+importedAss.anus.orificeAnus.depth
					+ "<br/>elasticity: "+importedAss.anus.orificeAnus.elasticity
					+ "<br/>elasticity: "+importedAss.anus.orificeAnus.plasticity
					+ "<br/>capacity: "+importedAss.anus.orificeAnus.capacity
					+ "<br/>stretchedCapacity: "+importedAss.anus.orificeAnus.stretchedCapacity
					+ "<br/>virgin: "+importedAss.anus.orificeAnus.virgin
					+ "<br/>bleached: "+importedAss.anus.bleached
					+ "<br/>assHair: "+importedAss.anus.assHair
					+"<br/>Modifiers:");
			
			Collection<OrificeModifier> anusModifiers = importedAss.anus.orificeAnus.orificeModifiers;
			anusModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element anusModifiersElement = (Element)anus.getElementsByTagName("anusModifiers").item(0);
				if(anusModifiersElement!=null) {
					if(anusModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedAss.anus.orificeAnus.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, anusModifiersElement, anusModifiers);
				}
				
			} else {
				NodeList mods = anus.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedAss.anus.orificeAnus.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
		}
		

		// **************** Breasts **************** //
		
		Element breasts = (Element)parentElement.getElementsByTagName("breasts").item(0);
		Element nipples = (Element)parentElement.getElementsByTagName("nipples").item(0);
		
		BreastShape breastShape = BreastShape.ROUND;
		try {
			breastShape = BreastShape.valueOf(breasts.getAttribute("shape"));
		} catch(Exception e) {
		}
		
		int milkStorage = 0;
		try {
			if(!breasts.getAttribute("lactation").isEmpty()) {
				milkStorage = Integer.valueOf(breasts.getAttribute("lactation"));
			} else {
				milkStorage = Integer.valueOf(breasts.getAttribute("milkStorage"));
			}
		} catch(Exception ex) {
		}

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = nipples.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Breast importedBreast = new Breast(BreastType.getBreastTypeFromId(breasts.getAttribute("type")),
				breastShape,
				Integer.valueOf(breasts.getAttribute("size")),
				milkStorage,
				Integer.valueOf(breasts.getAttribute("rows")),
				Integer.valueOf(nipples.getAttribute("nippleSize")),
				NippleShape.valueOf(nipples.getAttribute("nippleShape")),
				Integer.valueOf(nipples.getAttribute("areolaeSize")),
				AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")),
				Integer.valueOf(breasts.getAttribute("nippleCountPerBreast")),
				handleCapacityLoading(Float.valueOf(nipples.getAttribute("capacity"))),
				depth,
				Integer.valueOf(nipples.getAttribute("elasticity")),
				Integer.valueOf(nipples.getAttribute("plasticity")),
				Boolean.valueOf(nipples.getAttribute("virgin")));

		try {
			importedBreast.milkStored = Float.valueOf(breasts.getAttribute("storedMilk"));
			importedBreast.milkRegeneration = Integer.valueOf(breasts.getAttribute("milkRegeneration"));
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.9")) { // Change from percentage-based to set value:
				importedBreast.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
			}
		} catch(Exception ex) {
		}
		
		importedBreast.nipples.crotchNipples = false;
		importedBreast.nipples.orificeNipples.stretchedCapacity = handleCapacityLoading(Float.valueOf(nipples.getAttribute("stretchedCapacity")));
		importedBreast.nipples.pierced = (Boolean.valueOf(nipples.getAttribute("pierced")));
//		importedBreast.nipples.areolaeShape = (AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Breasts:"
				+ "<br/>type: "+importedBreast.getType()
				+ "<br/>size: "+importedBreast.getSize()
				+ "<br/>rows: "+importedBreast.getRows()
				+ "<br/>lactation: "+importedBreast.getRawMilkStorageValue()
				+ "<br/>nippleCountPer: "+importedBreast.getNippleCountPerBreast()
				
				+ "<br/><br/>Nipples:"
				+ "<br/>depth: "+importedBreast.nipples.orificeNipples.getDepth(null)
				+ "<br/>elasticity: "+importedBreast.nipples.orificeNipples.getElasticity()
				+ "<br/>plasticity: "+importedBreast.nipples.orificeNipples.getPlasticity()
				+ "<br/>capacity: "+importedBreast.nipples.orificeNipples.getRawCapacityValue()
				+ "<br/>stretchedCapacity: "+importedBreast.nipples.orificeNipples.getStretchedCapacity()
				+ "<br/>virgin: "+importedBreast.nipples.orificeNipples.isVirgin()
				+ "<br/>pierced: "+importedBreast.nipples.isPierced()
				+ "<br/>nippleSize: "+importedBreast.nipples.getNippleSize()
				+ "<br/>nippleShape: "+importedBreast.nipples.getNippleShape()
				+ "<br/>areolaeSize: "+importedBreast.nipples.getAreolaeSize()
				+ "<br/>areolaeShape: "+importedBreast.nipples.getAreolaeShape()
				+"<br/>Modifiers:");

		Collection<OrificeModifier> nippleOrificeModifiers = importedBreast.nipples.orificeNipples.orificeModifiers;
		nippleOrificeModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element nippleModifiersElement = (Element)nipples.getElementsByTagName("nippleModifiers").item(0);
			if(nippleModifiersElement!=null) {
				if(nippleModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedBreast.nipples.orificeNipples.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, nippleModifiersElement, nippleOrificeModifiers);
			}
	
		} else {
			NodeList mods = nipples.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedBreast.nipples.orificeNipples.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Milk:");
		
		importedBreast.milk = FluidMilk.loadFromXML("milk", parentElement, doc, importedBreast.getType().getFluidType(), false);
		if(Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.1")) {
			importedBreast.milk.type = importedBreast.getType().getFluidType();
		}
		
		// **************** Ear **************** //
		
		Element ear = (Element)parentElement.getElementsByTagName("ear").item(0);

		Ear importedEar = new Ear(EarType.getEarTypeFromId(ear.getAttribute("type")));
		
		importedEar.pierced = (Boolean.valueOf(ear.getAttribute("pierced")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Ear:"
				+ "<br/>type: "+importedEar.getType()
				+ "<br/>pierced: "+importedEar.isPierced());

		
		
		// **************** Eye **************** //
		
		Element eye = (Element)parentElement.getElementsByTagName("eye").item(0);
		
		String eyeTypeFromSave = eye.getAttribute("type");
		
		Map<String, String> eyeTypeConverterMap = new HashMap<>();
		eyeTypeConverterMap.put("EYE_HUMAN", "HUMAN");
		eyeTypeConverterMap.put("EYE_ANGEL", "ANGEL");
		eyeTypeConverterMap.put("EYE_DEMON_COMMON", "DEMON_COMMON");
		eyeTypeConverterMap.put("EYE_DOG_MORPH", "DOG_MORPH");
		eyeTypeConverterMap.put("EYE_LYCAN", "LYCAN");
		eyeTypeConverterMap.put("EYE_FELINE", "CAT_MORPH");
		eyeTypeConverterMap.put("EYE_SQUIRREL", "SQUIRREL_MORPH");
		eyeTypeConverterMap.put("EYE_HORSE_MORPH", "HORSE_MORPH");
		eyeTypeConverterMap.put("EYE_HARPY", "HARPY");
		eyeTypeConverterMap.put("EYE_SLIME", "SLIME");
		if(eyeTypeConverterMap.containsKey(eyeTypeFromSave)) {
			eyeTypeFromSave = eyeTypeConverterMap.get(eyeTypeFromSave);
		}
		
		Eye importedEye = new Eye(EyeType.getEyeTypeFromId(eyeTypeFromSave));
		
		if(Main.isVersionOlderThan(version, "0.3.9") && importedSubspeciesOverride==Subspecies.HALF_DEMON) { // Fix to taurs spawning with no demon parts at all in v0.3.8.9
			importedEye = new Eye(EyeType.DEMON_COMMON);
		}
		
		importedEye.eyePairs = (Integer.valueOf(eye.getAttribute("eyePairs")));
		importedEye.irisShape = (EyeShape.valueOf(eye.getAttribute("irisShape")));
		importedEye.pupilShape = (EyeShape.valueOf(eye.getAttribute("pupilShape")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Eye:"
				+ "<br/>type: "+importedEye.getType()
				+ "<br/>pairs: "+importedEye.getEyePairs()
				+ "<br/>iris shape: "+importedEye.getIrisShape()
				+ "<br/>pupil shape: "+importedEye.getPupilShape());
		
		
		// **************** Face **************** //
	
		Element face = (Element)parentElement.getElementsByTagName("face").item(0);
		Element mouth = (Element)parentElement.getElementsByTagName("mouth").item(0);
		
		boolean oldPantherReplacement = face.getAttribute("type").equals("CAT_MORPH_PANTHER");
		
		Face importedFace = new Face(FaceType.getFaceTypeFromId(face.getAttribute("type")), Integer.valueOf(mouth.getAttribute("lipSize")));
		
		importedFace.piercedNose = (Boolean.valueOf(face.getAttribute("piercedNose")));
		try {
			importedFace.facialHair = (BodyHair.valueOf(face.getAttribute("facialHair")));
		} catch(IllegalArgumentException e) {
			importedFace.facialHair = BodyHair.ZERO_NONE;
			Main.game.getCharacterUtils().appendToImportLog(log, "Растительность на лице <br/>: СТАРОЕ ЗНАЧЕНИЕ - Установлено NONE");
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Face: "
				+ "<br/>type: "+importedFace.getType()
				+ "<br/>piercedNose: "+importedFace.isPiercedNose()
				+ "<br/>facial hair: "+importedFace.getFacialHair()
				
				+ "<br/><br/>Mouth: ");

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = mouth.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		importedFace.mouth.orificeMouth.depth = depth;
		importedFace.mouth.orificeMouth.elasticity = (Integer.valueOf(mouth.getAttribute("elasticity")));
		importedFace.mouth.orificeMouth.plasticity = (Integer.valueOf(mouth.getAttribute("plasticity")));
		importedFace.mouth.orificeMouth.capacity = handleCapacityLoading(Float.valueOf(mouth.getAttribute("capacity")));
		try {
			importedFace.mouth.orificeMouth.wetness = (Integer.valueOf(mouth.getAttribute("wetness")));
		} catch(Exception ex) {
		}
		importedFace.mouth.orificeMouth.stretchedCapacity = handleCapacityLoading(Float.valueOf(mouth.getAttribute("stretchedCapacity")));
		importedFace.mouth.orificeMouth.virgin = (Boolean.valueOf(mouth.getAttribute("virgin")));
		importedFace.mouth.piercedLip = (Boolean.valueOf(mouth.getAttribute("piercedLip")));
		
		Main.game.getCharacterUtils().appendToImportLog(log, 
				"<br/>depth: "+importedFace.mouth.orificeMouth.getDepth(null)
				+ "<br/>elasticity: "+importedFace.mouth.orificeMouth.getElasticity()
				+ "<br/>plasticity: "+importedFace.mouth.orificeMouth.getPlasticity()
				+ "<br/>capacity: "+importedFace.mouth.orificeMouth.getCapacity()
				+ "<br/>stretchedCapacity: "+importedFace.mouth.orificeMouth.getStretchedCapacity()
				+ "<br/>virgin: "+importedFace.mouth.orificeMouth.isVirgin()
				+ "<br/>piercedLip: "+importedFace.mouth.isPiercedLip()
				+ "<br/>lipSize: "+importedFace.mouth.getLipSize()
				+ "<br/>Modifiers: ");

		Collection<OrificeModifier> mouthOrificeModifiers = importedFace.mouth.orificeMouth.orificeModifiers;
		mouthOrificeModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element mouthModifiersElement = (Element)mouth.getElementsByTagName("mouthModifiers").item(0);
			if(mouthModifiersElement!=null) {
				if(mouthModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedFace.mouth.orificeMouth.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, mouthModifiersElement, mouthOrificeModifiers);
			}
			
		} else {
			NodeList mods = mouth.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedFace.mouth.orificeMouth.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}

		Element tongue = (Element)parentElement.getElementsByTagName("tongue").item(0);
			importedFace.tongue.pierced = (Boolean.valueOf(tongue.getAttribute("piercedTongue")));
			importedFace.tongue.tongueLength = (Integer.valueOf(tongue.getAttribute("tongueLength")));
			
			Main.game.getCharacterUtils().appendToImportLog(log, 
					"<br/><br/>Tongue: "
					+ "<br/>piercedTongue: "+importedFace.tongue.isPierced()
					+ "<br/>tongueLength: "+importedFace.tongue.getTongueLength()
					+ "<br/>Modifiers: ");

			Collection<TongueModifier> tongueModifiers = importedFace.tongue.tongueModifiers;
			tongueModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element tongueModifiersElement = (Element)tongue.getElementsByTagName("tongueModifiers").item(0);
				if(tongueModifiersElement!=null) {
					handleLoadingOfModifiers(TongueModifier.values(), log, tongueModifiersElement, tongueModifiers);
				}
				if(version.isEmpty()) { // Version tracking was added in v0.3.7, so if there is no version, it is before then. Add new default modifiers added in v0.3.7:
					for(TongueModifier mod :importedFace.tongue.getType().getDefaultRacialTongueModifiers()) {
						if(mod==TongueModifier.FLAT || mod==TongueModifier.WIDE || mod==TongueModifier.STRONG) {
							importedFace.tongue.tongueModifiers.add(mod);
						}
					}
				}
				
			} else {
				NodeList mods = tongue.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedFace.tongue.tongueModifiers.add(TongueModifier.valueOf(e.getTextContent()));
				}
			}
			
			
		// **************** Hair **************** //
		
		Element hair = (Element)parentElement.getElementsByTagName("hair").item(0);
		String hairTypeFromSave = hair.getAttribute("type");
		
		Map<String, String> hairTypeConverterMap = new HashMap<>();
		hairTypeConverterMap.put("HAIR_HUMAN", "HUMAN");
		hairTypeConverterMap.put("HAIR_ANGEL", "ANGEL");
		hairTypeConverterMap.put("HAIR_DEMON", "DEMON_COMMON");
		hairTypeConverterMap.put("HAIR_CANINE_FUR", "DOG_MORPH");
		hairTypeConverterMap.put("HAIR_LYCAN_FUR", "LYCAN");
		hairTypeConverterMap.put("HAIR_FELINE_FUR", "CAT_MORPH");
		hairTypeConverterMap.put("HAIR_HORSE_HAIR", "HORSE_MORPH");
		hairTypeConverterMap.put("HAIR_SQUIRREL_FUR", "SQUIRREL_MORPH");
		hairTypeConverterMap.put("SLIME", "SLIME");
		hairTypeConverterMap.put("HAIR_HARPY", "HARPY");
		if(hairTypeConverterMap.containsKey(hairTypeFromSave)) {
			hairTypeFromSave = hairTypeConverterMap.get(hairTypeFromSave);
		}
		
		Hair importedHair = new Hair(HairType.getHairTypeFromId(hairTypeFromSave),
				Integer.valueOf(hair.getAttribute("length")),
				HairStyle.valueOf(hair.getAttribute("hairStyle")),
				null);
		
		importedHair.neckFluff = Boolean.valueOf(hair.getAttribute("neckFluff"));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Hair: "
				+ "<br/>type: "+importedHair.getType()
				+ "<br/>length: "+importedHair.getLength()
				+ "<br/>hairStyle: "+importedHair.getStyle());

		
		// **************** Horn **************** //
		Element horn = (Element)parentElement.getElementsByTagName("horn").item(0);
		
		Horn importedHorn = new Horn(HornType.NONE, 0);
		int rows = (Integer.valueOf(horn.getAttribute("rows")));
		
		String hornType = horn.getAttribute("type");
		if(hornType.equals("DEMON")) {
			hornType = "";
		}
		if(hornType.equals("BOVINE")) {
			hornType = "";
		}
		int length = 0;
		if(!hornType.equals("NONE")) {
			length = HornLength.TWO_LONG.getMedianValue();
		}
		if(!horn.getAttribute("length").isEmpty()) {
			try {
				length = Integer.valueOf(horn.getAttribute("length"));
			} catch(IllegalArgumentException e) {
			}
		}
		int hornsPerRow = 2;
		if(!horn.getAttribute("hornsPerRow").isEmpty()) {
			try {
				hornsPerRow = Integer.valueOf(horn.getAttribute("hornsPerRow"));
			} catch(IllegalArgumentException e) {
			}
		}
		try {
			importedHorn = new Horn(HornType.getHornTypeFromId(hornType), length);
			importedHorn.rows = rows;
			importedHorn.hornsPerRow = hornsPerRow;
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Horn: "
					+ "<br/>type: "+importedHorn.getType()
					+ "<br/>length: "+length
					+ "<br/>rows: "+importedHorn.getHornRows()
					+ "<br/>horns per row: "+importedHorn.getHornsPerRow());
			
		} catch(IllegalArgumentException e) {
			if(horn.getAttribute("type").startsWith("DEMON")) {
				importedHorn = new Horn(HornType.SWEPT_BACK, length);
				importedHorn.rows = rows;
				
			} else if(horn.getAttribute("type").startsWith("BOVINE")) {
				importedHorn = new Horn(HornType.CURVED, length);
				importedHorn.rows = rows;
			}
			
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Horn: "
					+ "<br/>type NOT FOUND, defaulted to: "+importedHorn.getType()
					+ "<br/>rows: "+importedHorn.getHornRows());
		}
		
		
			
		// **************** Leg **************** //
		
		Element leg = (Element)parentElement.getElementsByTagName("leg").item(0);

		AbstractLegType legType = LegType.getLegTypeFromId(leg.getAttribute("type"));
		
		LegConfiguration configuration = LegConfiguration.BIPEDAL;
		try {
			configuration = LegConfiguration.getValueFromString(leg.getAttribute("configuration"));
		} catch(Exception ex) {}
		
		FootStructure footStructure = legType.getDefaultFootStructure(configuration);
		try {
			footStructure = FootStructure.valueOf(leg.getAttribute("footStructure"));
		} catch(Exception ex) {}
		
		
		Leg importedLeg = new Leg(legType, configuration);
		importedLeg.setFootStructure(null, footStructure);
		try {
			float tailLength = Float.valueOf(leg.getAttribute("tailLength"));
			importedLeg.setLengthAsPercentageOfHeight(null, tailLength);
		} catch(Exception ex) {}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Leg: "
				+ "<br/>type: "+importedLeg.getType());

		
		// **************** Penis **************** //
		
		Element penis = (Element)parentElement.getElementsByTagName("penis").item(0);
		Element testicles = (Element)parentElement.getElementsByTagName("testicles").item(0);
		
		int girth = 2;
		if(penis.getAttribute("girth") != null && !penis.getAttribute("girth").isEmpty()) {
			girth = Integer.valueOf(penis.getAttribute("girth"));
			if(Main.isVersionOlderThan(version, "0.3.7.6")) {// An extra level was added in 0.3.7.6, so +1 if the version is older than that
				girth+=1;
			}
		}
		
		int cumStorage = 0;
		try {
			if(testicles.hasAttribute("cumProduction")) {
				cumStorage = Integer.valueOf(testicles.getAttribute("cumProduction"));
			} else {
				cumStorage = Integer.valueOf(testicles.getAttribute("cumStorage"));
			}
		} catch(Exception ex) {
		}
		
		Penis importedPenis = new Penis(PenisType.getPenisTypeFromId(penis.getAttribute("type")),
				Integer.valueOf(penis.getAttribute("size")),
				false,
				girth,
				Integer.valueOf(testicles.getAttribute("testicleSize")),
				cumStorage,
				Integer.valueOf(testicles.getAttribute("numberOfTesticles")));
		
		importedPenis.pierced = (Boolean.valueOf(penis.getAttribute("pierced")));
		
		if(!penis.getAttribute("virgin").isEmpty()) {
			importedPenis.virgin = (Boolean.valueOf(penis.getAttribute("virgin")));
		}

		if(!penis.getAttribute("previousType").isEmpty()) {
			importedPenis.previousType = PenisType.getPenisTypeFromId(penis.getAttribute("previousType"));
		}
		
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Penis: "
				+ "<br/>type: "+importedPenis.getType()
				+ "<br/>size: "+importedPenis.getRawLengthValue()
				+ "<br/>pierced: "+importedPenis.isPierced()
				+ "<br/>Penis Modifiers: ");

		Collection<PenetrationModifier> penisModifiers = importedPenis.penisModifiers;
		penisModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element penisModifiersElement = (Element)penis.getElementsByTagName("penisModifiers").item(0);
			if (penisModifiersElement != null) {
				handleLoadingOfModifiers(PenetrationModifier.values(), log, penisModifiersElement, penisModifiers);
			}
			
		} else {
			NodeList mods = penis.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedPenis.addPenisModifier(null, PenetrationModifier.valueOf(e.getTextContent()));
			}
		}
		
		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = penis.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		importedPenis.orificeUrethra.depth = depth;
		importedPenis.orificeUrethra.elasticity = (Integer.valueOf(penis.getAttribute("elasticity")));
		importedPenis.orificeUrethra.plasticity = (Integer.valueOf(penis.getAttribute("plasticity")));
		importedPenis.orificeUrethra.capacity = handleCapacityLoading(Float.valueOf(penis.getAttribute("capacity")));
		importedPenis.orificeUrethra.stretchedCapacity = handleCapacityLoading(Float.valueOf(penis.getAttribute("stretchedCapacity")));
		if(!penis.getAttribute("urethraVirgin").isEmpty()) {
			importedPenis.orificeUrethra.virgin = (Boolean.valueOf(penis.getAttribute("urethraVirgin")));
		} else {
			importedPenis.orificeUrethra.virgin = true;
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, 
				"<br/>depth: "+importedPenis.orificeUrethra.getDepth(null)
				+ "<br/>elasticity: "+importedPenis.orificeUrethra.getElasticity()
				+ "<br/>plasticity: "+importedPenis.orificeUrethra.getPlasticity()
				+ "<br/>capacity: "+importedPenis.orificeUrethra.getCapacity()
				+ "<br/>stretchedCapacity: "+importedPenis.orificeUrethra.getStretchedCapacity()
				+ "<br/>virgin: "+importedPenis.orificeUrethra.isVirgin()
				+ "<br/>Urethra Modifiers:");

		Collection<OrificeModifier> urethraOrificeModifiers = importedPenis.orificeUrethra.orificeModifiers;
		urethraOrificeModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element urethraModifiersElement = (Element)penis.getElementsByTagName("urethraModifiers").item(0);
			if (urethraModifiersElement != null) {
				if(urethraModifiersElement.hasAttribute("EXTRA_DEEP")) {
					importedPenis.orificeUrethra.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, urethraModifiersElement, urethraOrificeModifiers);
			}
			
		} else {
			NodeList mods = penis.getElementsByTagName("modUrethra");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedPenis.orificeUrethra.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		importedPenis.testicle.internal = (Boolean.valueOf(testicles.getAttribute("internal")));
		
		try {
			importedPenis.testicle.cumStored = Float.valueOf(testicles.getAttribute("storedCum"));
			importedPenis.testicle.cumRegeneration = Integer.valueOf(testicles.getAttribute("cumRegeneration"));
			importedPenis.testicle.setCumExpulsion(null, Integer.valueOf(testicles.getAttribute("cumExpulsion")));
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.9")) { // Change from percentage-based to set value:
				importedPenis.testicle.cumRegeneration = FluidRegeneration.CUM_REGEN_DEFAULT;
			}
		} catch(Exception ex) {
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Testicles: "
				+ "<br/>cumProduction: "+importedPenis.testicle.getCumStorage()
				+ "<br/>numberOfTesticles: "+importedPenis.testicle.getTesticleCount()
				+ "<br/>testicleSize: "+importedPenis.testicle.getTesticleSize()
				+ "<br/>internal: "+importedPenis.testicle.internal);
		
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Cum:");
		
		importedPenis.testicle.cum = FluidCum.loadFromXML("cum", parentElement, doc, importedPenis.getType().getTesticleType().getFluidType());

		
		// **************** Skin **************** //
		
		Element torso;
		if(parentElement.getElementsByTagName("skin").getLength()>0) {
			torso = (Element)parentElement.getElementsByTagName("skin").item(0);
		} else {
			torso = (Element)parentElement.getElementsByTagName("torso").item(0);
		}
		String torsoTypeFromSave = torso.getAttribute("type");
		
		Torso importedTorso = new Torso(TorsoType.getTorsoTypeFromId(torsoTypeFromSave));
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Torso: "
				+ "<br/>type: "+importedTorso.getType());


		// **************** Spinneret **************** //
		
		Element spinneret = (Element)parentElement.getElementsByTagName("spinneret").item(0);

		OrificeSpinneret importedSpinneret = new OrificeSpinneret();
		
		if(spinneret!=null) {
			importedSpinneret.wetness = Integer.valueOf(spinneret.getAttribute("wetness"));
			importedSpinneret.capacity = handleCapacityLoading(Float.valueOf(spinneret.getAttribute("capacity")));
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = spinneret.getAttribute("depth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			importedSpinneret.depth = depth;
			importedSpinneret.elasticity = Integer.valueOf(spinneret.getAttribute("elasticity"));
			importedSpinneret.plasticity = Integer.valueOf(spinneret.getAttribute("plasticity"));
			importedSpinneret.virgin = Boolean.valueOf(spinneret.getAttribute("virgin"));
			
			importedSpinneret.stretchedCapacity = handleCapacityLoading(Float.valueOf(spinneret.getAttribute("stretchedCapacity")));

			Collection<OrificeModifier> spinneretOrificeModifiers = importedSpinneret.orificeModifiers;
			spinneretOrificeModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element spinneretModifiers = (Element)spinneret.getElementsByTagName("spinneretModifiers").item(0);
				if(spinneretModifiers!=null) {
					handleLoadingOfModifiers(OrificeModifier.values(), log, spinneretModifiers, spinneretOrificeModifiers);
				}
				
			} else {
				NodeList mods = spinneret.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedSpinneret.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
		}
		
		// **************** Tail **************** //
		
		Element tail = (Element)parentElement.getElementsByTagName("tail").item(0);
		AbstractTailType tailType = TailType.getTailTypeFromId(tail.getAttribute("type"));
		
		Tail importedTail = new Tail(tailType);
		
		importedTail.tailCount = (Integer.valueOf(tail.getAttribute("count")));
		
		if(tail.getAttribute("girth") != null && !tail.getAttribute("girth").isEmpty()) {
			int tailGirth = Integer.valueOf(tail.getAttribute("girth"));
			if(Main.isVersionOlderThan(version, "0.3.7.6")) {// An extra level was added in 0.3.7.6, so +1 if the version is older than that
				tailGirth+=1;
			}
			importedTail.girth = tailGirth;
		}
		
		if(Main.isVersionOlderThan(version, "0.3.10")) {
			importedTail.lengthAsPercentageOfHeight = tailType.getDefaultLengthAsPercentageOfHeight();
			
		} else {
			if(tail.getAttribute("length") != null && !tail.getAttribute("length").isEmpty()) {
				float tailLength = Float.valueOf(tail.getAttribute("length"));
				importedTail.lengthAsPercentageOfHeight = tailLength;
			}
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Tail: "
				+ "<br/>type: "+importedTail.getType()
				+ "<br/>count: "+importedTail.getTailCount());

		
		// **************** Tentacle **************** //

		Tentacle importedTentacle = new Tentacle(TentacleType.NONE);	
		
		Element tentacle = (Element)parentElement.getElementsByTagName("tentacle").item(0);
		if(tentacle!=null) {
			AbstractTentacleType tentacleType = TentacleType.getTentacleTypeFromId(tentacle.getAttribute("type"));
	
			importedTentacle = new Tentacle(tentacleType);
			
			importedTentacle.tentacleCount = (Integer.valueOf(tentacle.getAttribute("count")));
			
			if(tentacle.getAttribute("girth") != null && !tentacle.getAttribute("girth").isEmpty()) {
				int tentacleGirth = Integer.valueOf(tentacle.getAttribute("girth"));
				importedTentacle.girth = tentacleGirth;
			}
			
			if(tentacle.getAttribute("length")!=null && !tentacle.getAttribute("length").isEmpty()) {
				float tentacleLength = Float.valueOf(tentacle.getAttribute("length"));
				importedTentacle.lengthAsPercentageOfHeight = tentacleLength;
			} else {
				importedTentacle.lengthAsPercentageOfHeight = tentacleType.getDefaultLengthAsPercentageOfHeight();
			}
			
			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Tentacle: "
					+ "<br/>type: "+importedTentacle.getType()
					+ "<br/>count: "+importedTentacle.getTentacleCount());
		}
		
		
		// **************** Vagina **************** //
		
		Element vagina = (Element)parentElement.getElementsByTagName("vagina").item(0);

		depth = OrificeDepth.TWO_AVERAGE.getValue();
		depthAttribute = vagina.getAttribute("depth");
		if(!depthAttribute.isEmpty()) {
			depth = Integer.valueOf(depthAttribute);
		}
		
		Vagina importedVagina = new Vagina(VaginaType.getVaginaTypeFromId(vagina.getAttribute("type")),
				(vagina.getAttribute("labiaSize").isEmpty()?1:Integer.valueOf(vagina.getAttribute("labiaSize"))),
				Integer.valueOf(vagina.getAttribute("clitSize")),
				vagina.hasAttribute("clitGirth")?Integer.valueOf(vagina.getAttribute("clitGirth")):PenetrationGirth.THREE_AVERAGE.getValue(),
				Integer.valueOf(vagina.getAttribute("wetness")),
				handleCapacityLoading(Float.valueOf(vagina.getAttribute("capacity"))),
				depth,
				Integer.valueOf(vagina.getAttribute("elasticity")),
				Integer.valueOf(vagina.getAttribute("plasticity")),
				Boolean.valueOf(vagina.getAttribute("virgin")));

		Collection<PenetrationModifier> clitModifiers = importedVagina.clitoris.clitModifiers;
		clitModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			try {
				Element clitModifiersElement = (Element)vagina.getElementsByTagName("clitModifiers").item(0);
				if (clitModifiersElement != null) {
					handleLoadingOfModifiers(PenetrationModifier.values(), log, clitModifiersElement, clitModifiers);
				}
				
			} catch(Exception ex) {
			}
			
		} else {
			NodeList mods = vagina.getElementsByTagName("modClit");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedVagina.getClitoris().addClitorisModifier(null, PenetrationModifier.valueOf(e.getTextContent()));
			}
		}
		
		importedVagina.pierced = (Boolean.valueOf(vagina.getAttribute("pierced")));
		
		if(vagina.hasAttribute("eggLayer")) {
			importedVagina.eggLayer = (Boolean.valueOf(vagina.getAttribute("eggLayer")));
		} else if(vagina.getAttribute("type").equals("DEMON_EGGS") || vagina.getAttribute("type").equals("NoStepOnSnek_snake_vagina_e")){ // Removed egg-laying vagina variants in 0.4
			importedVagina.eggLayer = true;
		}
		
		importedVagina.orificeVagina.stretchedCapacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("stretchedCapacity")));
		try {
			importedVagina.orificeVagina.squirter = (Boolean.valueOf(vagina.getAttribute("squirter")));
		} catch(Exception ex) {
		}
		if(Main.isVersionOlderThan(version, "0.3.6.9")) {
			importedVagina.orificeVagina.hymen = importedVagina.getOrificeVagina().isVirgin();
			
		} else {
			importedVagina.orificeVagina.hymen = Boolean.valueOf(vagina.getAttribute("hymen"));
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Vagina: "
				+ "<br/>type: "+importedVagina.getType()
				+ "<br/>clitSize: "+importedVagina.clitoris.getClitorisSize()
				+ "<br/>pierced: "+importedVagina.isPierced()

				+ "<br/>wetness: "+importedVagina.orificeVagina.wetness
				+ "<br/>depth: "+importedVagina.orificeVagina.getDepth(null)
				+ "<br/>elasticity: "+importedVagina.orificeVagina.getElasticity()
				+ "<br/>plasticity: "+importedVagina.orificeVagina.getPlasticity()
				+ "<br/>capacity: "+importedVagina.orificeVagina.getCapacity()
				+ "<br/>stretchedCapacity: "+importedVagina.orificeVagina.getStretchedCapacity()
				+ "<br/>virgin: "+importedVagina.orificeVagina.isVirgin());

		Collection<OrificeModifier> vaginaOrificeModifiers = importedVagina.orificeVagina.orificeModifiers;
		vaginaOrificeModifiers.clear();
		if(Main.isVersionOlderThan(version, "0.4.9.7")) {
			Element vaginaModifiers = (Element)vagina.getElementsByTagName("vaginaModifiers").item(0);
			if(vaginaModifiers!=null) {
				if(vaginaModifiers.hasAttribute("EXTRA_DEEP")) {
					importedVagina.orificeVagina.setDepth(null, depth+2);
				}
				handleLoadingOfModifiers(OrificeModifier.values(), log, vaginaModifiers, vaginaOrificeModifiers);
			}
			
		} else {
			NodeList mods = vagina.getElementsByTagName("mod");
			for(int i = 0; i < mods.getLength(); i++) {
				Element e = ((Element)mods.item(i));
				importedVagina.orificeVagina.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
			}
		}
		
		try {
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = vagina.getAttribute("urethraDepth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			importedVagina.orificeUrethra.depth = depth;
			importedVagina.orificeUrethra.elasticity = (Integer.valueOf(vagina.getAttribute("urethraElasticity")));
			importedVagina.orificeUrethra.plasticity = (Integer.valueOf(vagina.getAttribute("urethraPlasticity")));
			importedVagina.orificeUrethra.capacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("urethraCapacity")));
			importedVagina.orificeUrethra.stretchedCapacity = handleCapacityLoading(Float.valueOf(vagina.getAttribute("urethraStretchedCapacity")));
			if(!vagina.getAttribute("urethraVirgin").isEmpty()) {
				importedVagina.orificeUrethra.virgin = (Boolean.valueOf(vagina.getAttribute("urethraVirgin")));
			} else {
				importedVagina.orificeUrethra.virgin = true;
			}

			Collection<OrificeModifier> vaginaUrethraOrificeModifiers = importedVagina.orificeUrethra.orificeModifiers;
			vaginaUrethraOrificeModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element urethraModifiersElement = (Element)vagina.getElementsByTagName("urethraModifiers").item(0);
				if (urethraModifiersElement != null) {
					if(urethraModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedVagina.orificeUrethra.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, urethraModifiersElement, vaginaUrethraOrificeModifiers);
				}
				
			} else {
				NodeList mods = vagina.getElementsByTagName("modUrethra");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedVagina.orificeUrethra.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}
			
		} catch(Exception ex) {
		}
		
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Girlcum:");
		
		importedVagina.girlcum = FluidGirlCum.loadFromXML("girlcum", parentElement, doc, importedVagina.getType().getFluidType());
		
		// **************** Wing **************** //
		
		Element wing = (Element)parentElement.getElementsByTagName("wing").item(0);
		int wingSize = 0;
		if(!wing.getAttribute("size").isEmpty()) {
			wingSize = Integer.valueOf(wing.getAttribute("size"));
		}
		Wing importedWing = new Wing(WingType.getWingTypeFromId(wing.getAttribute("type")), wingSize);
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Wing: "
				+ "<br/>type: "+importedWing.getType()+"<br/>"
				+ "<br/>size: "+importedWing.getSizeValue()+"<br/>");


		// ************** Version Overrides **************//

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.5.1")) {
			importedVagina.girlcum.type = importedVagina.getType().getFluidType();
			importedPenis.testicle.cum.type = importedPenis.getType().getTesticleType().getFluidType();
			importedBreast.milk.type = importedBreast.getType().getFluidType();
		}


		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.8")) {
			// Convert all sizes from inch to cm
			importedHair.length *= 2.54;
			importedHorn.length *= 2.54;
			importedFace.tongue.tongueLength *= 2.54;
			importedPenis.length *= 2.54;
			importedVagina.clitoris.clitSize *= 2.54;

			// Convert all capacities from inch to cm
			importedFace.mouth.orificeMouth.capacity *= 2.54;
			importedFace.mouth.orificeMouth.stretchedCapacity *= 2.54;
			importedPenis.orificeUrethra.capacity *= 2.54;
			importedPenis.orificeUrethra.stretchedCapacity *= 2.54;
			importedVagina.orificeVagina.capacity *= 2.54;
			importedVagina.orificeVagina.stretchedCapacity *= 2.54;
			importedVagina.orificeUrethra.capacity *= 2.54;
			importedVagina.orificeUrethra.stretchedCapacity *= 2.54;
			importedAss.anus.orificeAnus.capacity *= 2.54;
			importedAss.anus.orificeAnus.stretchedCapacity *= 2.54;
			importedBreast.nipples.orificeNipples.capacity *= 2.54;
			importedBreast.nipples.orificeNipples.stretchedCapacity *= 2.54;
		}
		
		if(oldPantherReplacement) {
			if(importedArm.getType().getRace()==Race.CAT_MORPH) {
				importedArm.setType(null, ArmType.getArmTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedAss.getType().getRace()==Race.CAT_MORPH) {
				importedAss.setType(null, AssType.getAssTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedBreast.getType().getRace()==Race.CAT_MORPH) {
				importedBreast.setType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedFace.getType().getRace()==Race.CAT_MORPH) {
				importedFace.setType(null, FaceType.getFaceTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedEye.getType().getRace()==Race.CAT_MORPH) {
				importedEye.setType(null, EyeType.getEyeTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedEar.getType().getRace()==Race.CAT_MORPH) {
				importedEar.setType(null, EarType.getEarTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedHair.getType().getRace()==Race.CAT_MORPH) {
				importedHair.setType(null, HairType.getHairTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedLeg.getType().getRace()==Race.CAT_MORPH) {
				importedLeg.setType(null, LegType.getLegTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedTorso.getType().getRace()==Race.CAT_MORPH) {
				importedTorso.setType(null, TorsoType.getTorsoTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			
			if(importedVagina.getType().getRace()==Race.CAT_MORPH) {
				importedVagina.setType(null, VaginaType.getVaginaTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedPenis.getType().getRace()==Race.CAT_MORPH) {
				importedPenis.setType(null, PenisType.getPenisTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
			if(importedTail.getType().getRace()==Race.CAT_MORPH) {
				importedTail.setType(null, TailType.getTailTypes(Race.getRaceFromId("innoxia_panther")).get(0));
			}
		}
		
		Body body = new Body.BodyBuilder(
				importedArm,
				importedAss,
				importedBreast,
				importedFace,
				importedEye,
				importedEar,
				importedHair,
				importedLeg,
				importedTorso,
				importedBodyMaterial,
				importedGenitalArrangement,
				importedHeight,
				importedFemininity,
				importedBodySize,
				importedMuscle)
						.vagina(importedVagina)
						.penis(importedPenis)
						.horn(importedHorn)
						.antenna(importedAntenna)
						.tail(importedTail)
						.wing(importedWing)
						.spinneret(importedSpinneret)
						.tentacle(importedTentacle)
						.build();


		// **************** Crotch Breasts **************** //

		breasts = (Element)parentElement.getElementsByTagName("breastsCrotch").item(0);
		nipples = (Element)parentElement.getElementsByTagName("nipplesCrotch").item(0);
		BreastCrotch importedCrotchBreast = null;

		if(breasts!=null) {
			breastShape = BreastShape.ROUND;
			try {
				breastShape = BreastShape.valueOf(breasts.getAttribute("shape"));
			} catch(Exception e) {
			}

			milkStorage = 0;
			try {
				if(!breasts.getAttribute("lactation").isEmpty()) {
					milkStorage = Integer.valueOf(breasts.getAttribute("lactation"));
				} else {
					milkStorage = Integer.valueOf(breasts.getAttribute("milkStorage"));
				}
			} catch(Exception ex) {
			}

			AbstractBreastType crotchBoobType = BreastType.getBreastTypeFromId(breasts.getAttribute("type"));
			if(oldPantherReplacement) {
				if(crotchBoobType.getRace()==Race.CAT_MORPH) {
					crotchBoobType = BreastType.getBreastTypes(Race.getRaceFromId("innoxia_panther")).get(0);
				}
			}
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6") && importedLeg.getLegConfiguration().isBipedalPositionedCrotchBoobs()) { // Reset crotch-boob type as I accidentally applied crotch-boobs to demons
				if(body.isFeminine()) {
					crotchBoobType = RacialBody.valueOfRace(body.getRace()).getBreastCrotchType();
				} else {
					crotchBoobType = BreastType.NONE;
				}
			}
			
			depth = OrificeDepth.TWO_AVERAGE.getValue();
			depthAttribute = nipples.getAttribute("depth");
			if(!depthAttribute.isEmpty()) {
				depth = Integer.valueOf(depthAttribute);
			}
			
			importedCrotchBreast = new BreastCrotch(crotchBoobType,
					breastShape,
					Integer.valueOf(breasts.getAttribute("size")),
					milkStorage,
					Integer.valueOf(breasts.getAttribute("rows")),
					Integer.valueOf(nipples.getAttribute("nippleSize")),
					NippleShape.valueOf(nipples.getAttribute("nippleShape")),
					Integer.valueOf(nipples.getAttribute("areolaeSize")),
					AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")),
					Integer.valueOf(breasts.getAttribute("nippleCountPerBreast")),
					Float.valueOf(nipples.getAttribute("capacity")),
					depth,
					Integer.valueOf(nipples.getAttribute("elasticity")),
					Integer.valueOf(nipples.getAttribute("plasticity")),
					Boolean.valueOf(nipples.getAttribute("virgin")));

			try {
				importedCrotchBreast.milkStored = Float.valueOf(breasts.getAttribute("storedMilk"));
				importedCrotchBreast.milkRegeneration = Integer.valueOf(breasts.getAttribute("milkRegeneration"));
				if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.2")) { // Change from percentage-based to set value:
					importedCrotchBreast.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
				}
			} catch(Exception ex) {
			}

			importedCrotchBreast.nipples.crotchNipples = true;
			importedCrotchBreast.nipples.orificeNipples.stretchedCapacity = (Float.valueOf(nipples.getAttribute("stretchedCapacity")));
			importedCrotchBreast.nipples.pierced = (Boolean.valueOf(nipples.getAttribute("pierced")));
//			importedCrotchBreast.nipples.areolaeShape = (AreolaeShape.valueOf(nipples.getAttribute("areolaeShape")));

			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Crotch Breasts:"
					+ "<br/>type: "+importedCrotchBreast.getType()
					+ "<br/>size: "+importedCrotchBreast.getSize()
					+ "<br/>rows: "+importedCrotchBreast.getRows()
					+ "<br/>lactation: "+importedCrotchBreast.getRawMilkStorageValue()
					+ "<br/>nippleCountPer: "+importedCrotchBreast.getNippleCountPerBreast()

					+ "<br/><br/>Nipples:"
					+ "<br/>depth: "+importedCrotchBreast.nipples.orificeNipples.getDepth(null)
					+ "<br/>elasticity: "+importedCrotchBreast.nipples.orificeNipples.getElasticity()
					+ "<br/>plasticity: "+importedCrotchBreast.nipples.orificeNipples.getPlasticity()
					+ "<br/>capacity: "+importedCrotchBreast.nipples.orificeNipples.getRawCapacityValue()
					+ "<br/>stretchedCapacity: "+importedCrotchBreast.nipples.orificeNipples.getStretchedCapacity()
					+ "<br/>virgin: "+importedCrotchBreast.nipples.orificeNipples.isVirgin()
					+ "<br/>pierced: "+importedCrotchBreast.nipples.isPierced()
					+ "<br/>nippleSize: "+importedCrotchBreast.nipples.getNippleSize()
					+ "<br/>nippleShape: "+importedCrotchBreast.nipples.getNippleShape()
					+ "<br/>areolaeSize: "+importedCrotchBreast.nipples.getAreolaeSize()
					+ "<br/>areolaeShape: "+importedCrotchBreast.nipples.getAreolaeShape()
					+"<br/>Modifiers:");


			Collection<OrificeModifier> crotchNippleOrificeModifiers = importedCrotchBreast.nipples.orificeNipples.orificeModifiers;
			crotchNippleOrificeModifiers.clear();
			if(Main.isVersionOlderThan(version, "0.4.9.7")) {
				Element nippleModifiersElement = (Element)nipples.getElementsByTagName("nippleModifiers").item(0);
				if (nippleModifiersElement != null) {
					if(nippleModifiersElement.hasAttribute("EXTRA_DEEP")) {
						importedCrotchBreast.nipples.orificeNipples.setDepth(null, depth+2);
					}
					handleLoadingOfModifiers(OrificeModifier.values(), log, nippleModifiersElement, crotchNippleOrificeModifiers);
				}
				
			} else {
				NodeList mods = nipples.getElementsByTagName("mod");
				for(int i = 0; i < mods.getLength(); i++) {
					Element e = ((Element)mods.item(i));
					importedCrotchBreast.nipples.orificeNipples.addOrificeModifier(null, OrificeModifier.valueOf(e.getTextContent()));
				}
			}

			Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Milk:");

			if(parentElement.getElementsByTagName("milkCrotch").item(0)==null) {
				importedCrotchBreast.milk = FluidMilk.loadFromXML("milk", parentElement, doc, importedCrotchBreast.getType().getFluidType(), true);
			} else {
				importedCrotchBreast.milk = FluidMilk.loadFromXML("milkCrotch", parentElement, doc, importedCrotchBreast.getType().getFluidType(), true);
			}
			if(Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.1")) {
				importedCrotchBreast.milk.type = importedCrotchBreast.getType().getFluidType();
			}
		}


		if(importedCrotchBreast!=null) {
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.8")) {
				importedCrotchBreast.nipples.orificeNipples.capacity *= 2.54;
				importedCrotchBreast.nipples.orificeNipples.stretchedCapacity *= 2.54;
			}

			body.setBreastCrotch(importedCrotchBreast);
		}
		
		body.setSubspeciesOverride(importedSubspeciesOverride);
		body.loadedSubspecies = importedLoadedSubspecies;
		
		body.setPiercedStomach(Boolean.valueOf(element.getAttribute("piercedStomach")));
		Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set piercedStomach: "+Boolean.valueOf(element.getAttribute("piercedStomach")));
		
		try {
			if(element.getAttribute("takesAfterMother") != null && !element.getAttribute("takesAfterMother").isEmpty()) {
				body.setTakesAfterMother(Boolean.valueOf(element.getAttribute("takesAfterMother")));
			}
		} catch(Exception ex) {	
		}
		
		if(element.getAttribute("pubicHair")!=null && !element.getAttribute("pubicHair").isEmpty()) {
			try {
				body.setPubicHair(BodyHair.valueOf(element.getAttribute("pubicHair")));
				Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set pubicHair: "+body.getPubicHair());
			} catch(IllegalArgumentException e) {
				body.pubicHair = BodyHair.ZERO_NONE;
				Main.game.getCharacterUtils().appendToImportLog(log, "Лобковые волосы <br/>: СТАРОЕ ЗНАЧЕНИЕ - Установлено NONE");
			}
		}
		
		NodeList bodyCoverings = element.getElementsByTagName("bodyCovering");
		for(int i = 0; i < bodyCoverings.getLength(); i++){
			Element e = ((Element)bodyCoverings.item(i));

			String type = e.getAttribute("type");
			if(type.equals("HORN_COW") || type.equals("HORN_DEMON")) {
				type = "HORN";
			}
			try {
				String colourPrimary = e.getAttribute("colourPrimary");
				if(colourPrimary.isEmpty()) {
					colourPrimary = e.getAttribute("c1");
				}
				String colourSecondary = e.getAttribute("colourSecondary");
				if(colourSecondary.isEmpty()) {
					colourSecondary = e.getAttribute("c2");
				}
				if(colourSecondary.isEmpty()) {
					colourSecondary = colourPrimary; // If secondary colour is missing, then it's the same as the primary
				}
				
				if(type.startsWith("HAIR_")) {
					if(colourPrimary.equals("COVERING_TAN")) {
						colourPrimary = "COVERING_DIRTY_BLONDE";
					}
					if(colourSecondary.equals("COVERING_TAN")) {
						colourSecondary = "COVERING_DIRTY_BLONDE";
					}
				}

				if(type.startsWith("EYE_")) {
					if(colourPrimary.equals("COVERING_BLUE")) {
						colourPrimary = "COVERING_BLUE_LIGHT";
					}
					if(colourSecondary.equals("COVERING_BLUE")) {
						colourSecondary = "COVERING_BLUE_LIGHT";
					}
				}

				AbstractBodyCoveringType coveringType = BodyCoveringType.getBodyCoveringTypeFromId(type);
				CoveringPattern loadedPattern = CoveringPattern.valueOf(e.getAttribute("pattern"));
				if(!coveringType.getAllPatterns().containsKey(loadedPattern)) {
					loadedPattern = Util.getRandomObjectFromWeightedMap(coveringType.getNaturalPatterns());
				}
				if(e.getAttribute("modifier").isEmpty()) {
					body.setBodyCoveringForXMLImport(coveringType,
							loadedPattern,
							PresetColour.getColourFromId(colourPrimary),
							!e.getAttribute("g1").isEmpty()?Boolean.valueOf(e.getAttribute("g1")):false,
							PresetColour.getColourFromId(colourSecondary),
							!e.getAttribute("g2").isEmpty()?Boolean.valueOf(e.getAttribute("g2")):false);
					
				} else { //TODO
					CoveringModifier modifier = CoveringModifier.valueOf(e.getAttribute("modifier"));
					
					body.setBodyCoveringForXMLImport(coveringType,
							loadedPattern,
							coveringType.getNaturalModifiers().contains(modifier) || coveringType.getExtraModifiers().contains(modifier) ? modifier : coveringType.getNaturalModifiers().get(0),
							PresetColour.getColourFromId(colourPrimary),
							!e.getAttribute("g1").isEmpty()?Boolean.valueOf(e.getAttribute("g1")):false,
							PresetColour.getColourFromId(colourSecondary),
							!e.getAttribute("g2").isEmpty()?Boolean.valueOf(e.getAttribute("g2")):false);
				}
				
				if(oldPantherReplacement && coveringType==BodyCoveringType.FELINE_FUR) {
					Covering felineCovering = body.getCovering(coveringType, false);
					body.setBodyCoveringForXMLImport(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"),
							felineCovering.getPattern(),
							felineCovering.getModifier(),
							felineCovering.getPrimaryColour(),
							felineCovering.isPrimaryGlowing(),
							felineCovering.getSecondaryColour(),
							felineCovering.isSecondaryGlowing());
					body.addBodyCoveringTypesDiscovered(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"));
				}
				
//				if(!e.getAttribute("discovered").isEmpty() && Boolean.valueOf(e.getAttribute("discovered"))) {
					body.addBodyCoveringTypesDiscovered(BodyCoveringType.getBodyCoveringTypeFromId(type));
//				}
				
				Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set bodyCovering: "+colourPrimary+", "+colourSecondary);
			} catch(Exception ex) {
			}
		}
		
		
		try {
			Element heavyMakeupElement = (Element)element.getElementsByTagName("heavyMakeup").item(0);
			
			NodeList bodyTypes = heavyMakeupElement.getElementsByTagName("type");
			for(int i = 0; i < bodyTypes.getLength(); i++){
				Element e = ((Element)bodyTypes.item(i));
				body.addHeavyMakeup(BodyCoveringType.getBodyCoveringTypeFromId(e.getTextContent()));
			}
		} catch(Exception ex) {	
		}
		
		body.feral = feralBody;
		
		body.addDiscoveredBodyCoveringsFromMaterial(importedBodyMaterial);
		
		body.calculateRace(null);
		
		// Converting harpy bald eagles to raptor bald eagles:
		if(Main.isVersionOlderThan(version, "0.4.2.5")) {
			if(body.getRace()==Race.HARPY) {
				AbstractBodyCoveringType feathers = body.getBodyMaterial()==BodyMaterial.SLIME?BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.MAIN_FEATHER):BodyCoveringType.FEATHERS;
				AbstractBodyCoveringType headFeathers = body.getBodyMaterial()==BodyMaterial.SLIME?BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.HAIR):BodyCoveringType.HAIR_HARPY;
				
				if(body.getCoverings().get(feathers).getPrimaryColour()==PresetColour.COVERING_BROWN_DARK && body.getCoverings().get(headFeathers).getPrimaryColour()==PresetColour.COVERING_WHITE) {

					if(body.getArmType().getRace()==Race.HARPY) {
						body.setArmType(null, ArmType.getArmTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getAssType().getRace()==Race.HARPY) {
						body.setAssType(null, AssType.getAssTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getBreastType().getRace()==Race.HARPY) {
						body.setBreastType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getBreastCrotchType().getRace()==Race.HARPY) {
						body.setBreastCrotchType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getFaceType().getRace()==Race.HARPY) {
						body.setFaceType(null, FaceType.getFaceTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getEyeType().getRace()==Race.HARPY) {
						body.setEyeType(null, EyeType.getEyeTypeFromId("innoxia_raptor_eye"));
					}
					if(body.getEarType().getRace()==Race.HARPY) {
						body.setEarType(null, EarType.getEarTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getHairType().getRace()==Race.HARPY) {
						body.setHairType(null, HairType.getHairTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getLegType().getRace()==Race.HARPY) {
						body.setLegType(null, LegType.getLegTypeFromId("innoxia_raptor_leg_large"));
					}
					if(body.getTorsoType().getRace()==Race.HARPY) {
						body.setTorsoType(null, TorsoType.getTorsoTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					
					if(body.getVaginaType().getRace()==Race.HARPY) {
						body.setVaginaType(null, VaginaType.getVaginaTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getPenisType().getRace()==Race.HARPY) {
						body.setPenisType(null, PenisType.getPenisTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					if(body.getTailType().getRace()==Race.HARPY) {
						body.setTailType(null, TailType.getTailTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
					}
					
					// coverings:
					coveringCopyConversion(body, BodyCoveringType.FEATHERS, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_feathers"));
					coveringCopyConversion(body, BodyCoveringType.HARPY_SKIN, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_skin"));
					coveringCopyConversion(body, BodyCoveringType.BODY_HAIR_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_body_hair"));
					coveringCopyConversion(body, BodyCoveringType.EYE_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_eye"));
					coveringCopyConversion(body, BodyCoveringType.HAIR_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_hair"));
				}
			}
		}
		
		if(Main.isVersionOlderThan(version, "0.3.0.5")) {
			body.updateNippleCrotchColouring();
		}
		
		return body;
	}
	
	private static void coveringCopyConversion(Body body, AbstractBodyCoveringType coveringTypeToCopy, AbstractBodyCoveringType newCoveringType) {
		Covering covering = body.getCovering(coveringTypeToCopy, false);
		body.setBodyCoveringForXMLImport(newCoveringType,
				covering.getPattern(),
				covering.getModifier(),
				covering.getPrimaryColour(),
				covering.isPrimaryGlowing(),
				covering.getSecondaryColour(),
				covering.isSecondaryGlowing());
		body.addBodyCoveringTypesDiscovered(newCoveringType);
	}

	static <T extends Enum<T>> void handleLoadingOfModifiers(T[] enumValues, StringBuilder log, Element modifiersElement, Collection<T> modifiers) {
		for(T enumValue : enumValues) {
			String attributeValue = modifiersElement.getAttribute(enumValue.toString());
			if(!attributeValue.isEmpty()) {
				if(Boolean.valueOf(attributeValue)) {
					if(!modifiers.contains(enumValue)) {
						modifiers.add(enumValue);
					}
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":истина");
					}
				} else if (!attributeValue.isEmpty()) {
					modifiers.remove(enumValue);
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":ложно");
					}
				} else {
					if(log!=null) {
						Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":отсутствует, по умолчанию установлено "+modifiers.contains(enumValue));
					}
				}
			}
		}
	}
	
	private static float handleCapacityLoading(float input) {
		float loadedCapacity = input;
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6.7")) {
			loadedCapacity = Penis.getGenericDiameter((int)loadedCapacity, PenetrationGirth.THREE_AVERAGE);
		}
		return loadedCapacity;
	}
	
	public List<BodyPartInterface> getAllBodyParts() {
		return allBodyParts;
	}
	
	public List<BodyPartInterface> getAllBodyPartsWithAllOrifices() {
		return allBodyPartsExtended;
	}
	
	private String getHeader(String header) {
		return "<p style='padding-top:0; margin-top:0;'>[style.colourDisabled("+header+":)]<br/>";
	}
	
	/**
	 * @param owner
	 * @return
	 */
	public String getDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		boolean observant = Main.game.getPlayer().hasTrait(Perk.OBSERVANT, true);
		// Describe race:
		sb.append(getHeader("Обзор"));
		String colouredHeightValue = "<span style='color:"+this.getHeight().getColour().toWebHexString()+";'>[npc.heightValue]</span>";
		
		String heightDescription = " Стоя в полный рост, [npc.she] [npc.verb(measure)] "+colouredHeightValue;
		if(owner.isFeral() && !owner.getFeralAttributes().isSizeHeight()) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				heightDescription = " Параметры тела [npc.Her] "+colouredHeightValue+", что в сочетании с [npc.her] [npc.tailLength]-длинным хвостом, дает [npc.herHim] общую длину <span style='color:"+Height.getHeightFromInt(owner.getHeightValue()+owner.getLegTailLength(false)).getColour().toWebHexString()+";'>"
							+ Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG)
						+"</span>";
				
//				heightDescription = " From head to tail,  [npc.she] [npc.verb(measure)] "
//						+Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG);
			} else {
				heightDescription = " От головы до хвоста, [npc.she] [npc.verb(measure)] "+colouredHeightValue;
			}
		}
		
		if (owner.isPlayer()) {
			sb.append("Вы — [pc.name], "
							+(owner.getRace()==Race.HUMAN
								?"<span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>[pc.a_femininity]</span> [pc.gender(true)] [style.colourHuman(человек)]. "
								:"[pc.a_fullRace(true)] [pc.gender(true)]. ")
						+ owner.getAppearsAsGenderDescription(true)
						+heightDescription+".");
		} else {
			if(owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer()) && owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
				sb.append("[npc.Name] — это "
							+(owner.getRace()==Race.HUMAN
								?"<span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>[npc.a_femininity]</span> [npc.gender(true)] [style.colourHuman(человек)]. "
								:"[npc.a_fullRace(true)] [npc.gender(true)]. ")
						+ owner.getAppearsAsGenderDescription(true)
						+ heightDescription);
			} else {
				if(observant) {
					sb.append("Благодаря вашей наблюдательности, вы замечаете, что [npc.name] имеет длину <span style='color:"+getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span> [npc.raceStage] [npc.race]. "
							+ owner.getAppearsAsGenderDescription(true)
							+ heightDescription);
				} else {
					sb.append("[npc.Name] — это [npc.a_fullRace(true)]. "
								+ owner.getAppearsAsGenderDescription(true)
								+ heightDescription);
				}
			}
			if(owner.isSizeDifferenceTallerThan(Main.game.getPlayer())) {
				String descriptor = owner.isFeral() && !owner.getFeralAttributes().isSizeHeight()?"longer":"taller";
				sb.append(", превращая [npc.herHim] в <span style='color:"+PresetColour.BODY_SIZE_FOUR.toWebHexString()+";'> значительно "+descriptor+"</span>, чем ты.");
			} else if(owner.isSizeDifferenceShorterThan(Main.game.getPlayer())) {
				sb.append(", превращая [npc.herHim] в <span style='color:"+PresetColour.BODY_SIZE_ZERO.toWebHexString()+";'> значительно ниже</span>, чем ты.");
			} else {
				sb.append(".");
			}
		}
		
		switch(owner.getLegConfiguration()) {
			case ARACHNID:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral(Все тело [npc.Her] превратилось в тело дикого [npc.legRace]. [npc.Her] гениталии расположены на нижней стороне тела дикого [npc.assRace] [npc.her], а [npc.her] анус находится ближе к концу дикого живота [npc.her].)]");
				} else {
					sb.append(" [style.colourFeral(Вся нижняя часть тела [npc.Her], от талии и ниже, превратилась в тело огромного [npc.legRace]. [npc.Her] ноги и гениталии носят чисто дикий характер и чрезвычайно похожи на гениталии [npc.a_assRace]. [npc.Her] гениталии расположены на нижней стороне тела дикого [npc.assRace] [npc.her], а [npc.her] анус находится ближе к концу дикого живота [npc.her].)]");
				}
				break;
			case AVIAN:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral(Все тело [npc.Her] превратилось в тело дикого [npc.legRace]. [npc.Her] гениталии расположены ближе к задней части нижней стороны тела дикого [npc.assRace] [npc.her] и находятся внутри клоаки.)]");
				} else {
					sb.append(" [style.colourFeral(вся нижняя часть тела [npc.Her], от талии и ниже, превратилась в тело огромного [npc.legRace]. [npc.Her] ноги и гениталии носят совершенно дикий характер и чрезвычайно похожи на гениталии [npc.a_assRace]. [npc.Her] гениталии расположены ближе к задней части нижней стороны дикого тела [npc.assRace] [npc.her] и находятся внутри клоаки.)]");
				}
				break;
			case CEPHALOPOD:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral(все тело [npc.Her] превратилось в тело дикого [npc.legRace]. [npc.Her] гениталии и анус расположены внутри клоаки на нижней стороне дикого тела [npc.her].)]");
				} else {
					sb.append(" [style.colourFeral(вся нижняя часть тела [npc.Her], от талии и ниже, превратилась в тело огромного [npc.legRace]. [npc.Her] ноги и гениталии почти полностью приобрели дикий вид и чрезвычайно похожи на гениталии [npc.a_assRace]. [npc.Her] гениталии и анус расположены внутри клоаки на нижней стороне дикого тела [npc.her].)]");
				}
				break;
			case BIPEDAL:
			case WINGED_BIPED:
				break;
			case TAIL:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral(все тело [npc.Her] превратилось в тело дикого [npc.legRace]. [npc.Her] задница и гениталии сместились и теперь находятся внутри клоаки на нижней стороне дикого тела [npc.her].)]");
				} else {
					sb.append(" [style.colourFeral(вся нижняя часть тела [npc.Her], от талии и ниже, превратилась в хвост [npc.a_legRace]. [npc.Her] задница и гениталии, сохраняя антропоморфные черты, сместились и теперь находятся внутри направленной вперед клоаки.)]");
	
					if(owner.hasStatusEffect(StatusEffect.AQUATIC_NEGATIVE)) {
						sb.append(" Поскольку поблизости нет водоемов, хвост [npc.her] автоматически [style.colourTan(превратился в пару ног)], что позволяет [npc.herHim] с легкостью передвигаться по суше.");
					}
				}
				break;
			case TAIL_LONG:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral(все тело [npc.Her] превратилось в тело дикого [npc.legRace]. [npc.Her] задница и гениталии сместились и теперь находятся внутри клоаки на нижней стороне дикого тела [npc.her].)]");
				} else {
					sb.append(" [style.colourFeral(вся нижняя часть тела [npc.Her], от талии и ниже, превратилась в длинный хвост [npc.a_legRace], длина которого составляет "+Units.size(owner.getLegTailLength(false))+" в длину. Задница и гениталии [npc.Her] носят полностью дикий характер и, подобно [npc.a_assRace], сместились и теперь расположены внутри фронтальной клоаки.)]");
				}
				break;
			case QUADRUPEDAL:
				if(owner.isFeral()) {
					sb.append(" [style.colourFeral(все тело [npc.Her] превратилось в тело дикого [npc.legRace]. Гениталии и анус [npc.Her] расположены на задней части дикого тела [npc.her].)]");
				} else {
					sb.append(" [style.colourFeral(вся нижняя часть тела [npc.Her], от талии и ниже, превратилась в тело [npc.a_legRace]. Ноги, хвост, задница и гениталии [npc.Her] носят полностью дикий характер и идентичны таковым у [npc.a_assRace]. Гениталии и анус [npc.Her] расположены на задней части дикого тела [npc.her], в том же месте, что и у обычного [npc.a_assRace].)]");
				}
				break;
		}
		
		if(Main.getProperties().hasValue(PropertyValue.ageContent)) {
			sb.append(" [npc.She] [npc.verb(appear)] быть "
					+(owner.getAppearsAsAge()==AgeCategory.SIXTIES_PLUS
						?""
						:"в [npc.her] ")+
					"<span style='color:"+owner.getAppearsAsAge().getColour().toWebHexString()+";'>"+owner.getAppearsAsAge().getName()+"</span>.");
		}
		sb.append("</p>");
		
		switch(this.getBodyMaterial()) {
			case FLESH:
				break;
			case SLIME:
				sb.append("<p>[npc.NamePos] всё тело, за исключением маленькой светящейся сферы на месте, где должно быть сердце [npc.her], состоит из [npc.skinFullDescription(true)]! [npc.She] [npc.do] не нужно прокалывать части [npc.her] тела, чтобы носить украшения, так как [npc.she] может свободно менять форму [npc.her] тела по своему желанию!</p>");
				break;
			case SILICONE:
			case AIR:
			case ARCANE:
			case STONE:
			case RUBBER:
			case ICE:
			case WATER:
			case FIRE:
				sb.append("<p>"
							+ "[npc.NamePos] entire body, save for a small obsidian sphere in the place where [npc.her] heart should be, is made out of"
								+ " <b style='color:"+this.getBodyMaterial().getColour().toWebHexString()+";'>"+this.getBodyMaterial().getName()+"</b>!"
						+ "</p>");
				break;
		}
		
		// Describe face (ears, eyes & horns):
		// Femininity:
		sb.append(getHeader("Лицо"));
		sb.append(face.getType().getBodyDescription(owner));
		if(owner.getBlusher().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append(" [npc.SheIsFull] носит "+owner.getBlusher().getColourDescriptor(owner, true, false)+" румяна.");
		}
		
		// Hair:
		if (hair.getRawLengthValue() == 0) {
			if(face.isBaldnessNatural() || owner.isFeral()) {
				sb.append(" Голова [npc.Her] — это [npc.materialDescriptor] [npc.faceFullDescription(true)].");
			} else {
				sb.append(" У [npc.SheHasFull] нет волос на [npc.her] голове, что обнажает [npc.faceSkin], покрывающий [npc.her] скальп.");
			}
			
		} else {
			sb.append(" "+hair.getType().getBodyDescription(owner).trim());
			
			if(hair.getType().getTags().contains(BodyPartTag.HAIR_NATURAL_MANE) && owner.getFaceType().getRace()==hair.getType().getRace()) {
				sb.append(", образующую гриву, спускающуюся по затылку [npc.her], и которая "); // If hair and face races match, the mane is fully formed
			} else {
				sb.append(", которая ");
			}
			
			switch (hair.getStyle()) {
				case NONE:
					sb.append((hair.getType().isDefaultPlural(owner)?"являются":"есть")+" неухоженными.");
					break;
				case BRAIDED:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" были заплетены в длинную косу.");
					break;
				case CURLY:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" были завиты и распущены.");
					break;
				case LOOSE:
					sb.append((hair.getType().isDefaultPlural(owner)?"являются":"есть")+" распущены и не уложены.");
					break;
				case PONYTAIL:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" были собраны в хвост.");
					break;
				case STRAIGHT:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" выпрямлены и распущены.");
					break;
				case TWIN_TAILS:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в два хвоста.");
					break;
				case WAVY:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены волнами и распущены.");
					break;
				case MOHAWK:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в ирокез.");
					break;
				case AFRO:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в афро.");
					break;
				case SIDECUT:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в сайдкат.");
					break;
				case BOB_CUT:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в каре.");
					break;
				case PIXIE:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в пикси.");
					break;
				case SLICKED_BACK:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" зачесаны назад.");
					break;
				case MESSY:
					sb.append((hair.getType().isDefaultPlural(owner)?"являются":"есть")+" не уложены и очень растрепаны.");
					break;
				case HIME_CUT:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" выпрямлены и уложены в стрижку химэ.");
					break;
				case CHONMAGE:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" выпрямлены, смазаны маслом и уложены в пучок чонмаге.");
					break;
				case TOPKNOT:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в пучок.");
					break;
				case DREADLOCKS:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в дреды.");
					break;
				case BIRD_CAGE:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в замысловатую конструкцию в виде птичьей клетки"+UtilText.returnStringAtRandom(".",", птицы в комплект не входят."));
					break;
				case TWIN_BRAIDS:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" заплетены в две длинные косы.");
					break;
				case DRILLS:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в локоны одзё.");
					break;
				case LOW_PONYTAIL:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в низкий хвост.");
					break;
				case CROWN_BRAID:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" заплетены в "+UtilText.returnStringAtRandom("плетеная корона.","корона из кос."));
					break;
				case BUN:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" уложены в пучок.");
					break;
				case CHIGNON:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" собраны в шиньон.");
					break;
				case SIDE_BRAIDS:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" заплетена в косы, спадающие по обе стороны лица [npc.her].");
					break;
				case SIDE_PARTED:
					sb.append((hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" зачесаны от пробора на боку головы [npc.her].");
					break;
			}
		}
		if (hair.isNeckFluff()) {
			sb.append(" Обильная копна [npc.hair(true)] "+(hair.getType().isDefaultPlural(owner)?"имеют":"имеет")+" отрастающая вокруг шеи и верхней части груди [npc.her].");
		}
		
		// Horns:
		
		if(owner.hasHorns()) {
			sb.append(" "+owner.getHornType().getBodyDescription(owner));
		}
		
		// Antenna:

		if(owner.hasAntennae()) {
			sb.append(" "+owner.getAntennaType().getBodyDescription(owner));
		}
		
		// Nose:
		
		if(face.isPiercedNose()) {
			sb.append(" [npc.Her] [npc.nose] проколоты.");
		}
		
		// Eyes:

		if(owner.isAreaKnownByCharacter(CoverableArea.EYES, Main.game.getPlayer())) {
			sb.append(getEyeDescription(owner));
		} else {
			sb.append(" [style.colourDisabled(Вы еще не видели глаз [npc.her], поэтому не знаете, как они выглядят.)]");
		}
		
		// Ear:
		if(owner.isFeral()) {
			sb.append(" [npc.She] [npc.has] пару #IF(npc.isPiercedEar()) проколотых,#ENDIF [npc.earRace] ушей, которые [npc.materialCompositionDescriptor] [npc.earFullDescription(true)].");
			
		} else {
			sb.append(" "+ear.getType().getBodyDescription(owner));
			
			if(Main.game.isFacialHairEnabled()) {
				if(owner.getFacialHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getFacialHair()) {
						case ZERO_NONE:
							if(!owner.isFeminine()) {
								sb.append(" [npc.She] [npc.do] даже намека на грубую щетину, "+owner.getFacialHairType().getName(owner)+".");
							}
							break;
						case ONE_STUBBLE:
							sb.append(" [npc.SheHasFull] клочок щетины на "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face].");
							break;
						case TWO_MANICURED:
							sb.append(" [npc.SheHasFull] небольшой участок грубой кожи на "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face], напоминающий бороду.");
							break;
						case THREE_TRIMMED:
							sb.append(" [npc.SheHasFull] участок грубой кожи, "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face], напоминающий бороду.");
							break;
						case FOUR_NATURAL:
							sb.append(" [npc.SheHasFull] естественный участок грубой кожи, "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face], напоминающий бороду.");
							break;
						case FIVE_UNKEMPT:
							sb.append(" [npc.SheHasFull] неопрятный, жесткий участок "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face], напоминающий бороду.");
							break;
						case SIX_BUSHY:
							sb.append(" [npc.SheHasFull] густой, жесткий участок "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face], напоминающий бороду.");
							break;
						case SEVEN_WILD:
							sb.append(" [npc.SheHasFull] дикий, жесткий участок "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face], напоминающий бороду.");
							break;
					}
				} else {
					switch(owner.getFacialHair()) {
						case ZERO_NONE:
							if(!owner.isFeminine()) {
								sb.append(" [npc.She] [npc.do] даже намека на растительность на лице "+owner.getFacialHairType().getName(owner)+".");
							}
							break;
						case ONE_STUBBLE:
							sb.append(" [npc.SheHasFull] клочок щетины на "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face].");
							break;
						case TWO_MANICURED:
							sb.append(" [npc.SheHasFull] небольшое количество "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face].");
							break;
						case THREE_TRIMMED:
							sb.append(" [npc.SheHasFull] аккуратно подстриженную бороду "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face].");
							break;
						case FOUR_NATURAL:
							sb.append(" [npc.SheHasFull] естественную бороду "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face].");
							break;
						case FIVE_UNKEMPT:
							sb.append(" [npc.SheHasFull] неопрятную, густую бороду "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face].");
							break;
						case SIX_BUSHY:
							sb.append(" [npc.SheHasFull] густую, пышную бороду "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face].");
							break;
						case SEVEN_WILD:
							sb.append(" [npc.SheHasFull] дикую, густую бороду "+owner.getFacialHairType().getFullDescription(owner, true)+" растущий на [npc.her] [npc.face].");
							break;
					}
				}
			}
		}
		sb.append("</p>");
		
		
		// Mouth & lips:
		sb.append(getHeader("Рот"));
		sb.append(face.getMouth().getType().getBodyDescription(owner));
		
		// Throat modifiers:
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasFaceOrificeModifier(om)) {
				switch(om) {
					case PUFFY:
						sb.append(" [npc.Her] [npc.lips] раздулись и стали куда пухлее, чем это считается нормальным.");
						break;
					case MUSCLE_CONTROL:
						sb.append(" [npc.SheHasFull] ряд внутренних мышц, выстилающих внутреннюю часть [npc.her], что позволяет [npc.herHim] умело сжимать и обхватывать любой проникающий объект.");
						break;
					case RIBBED:
						sb.append(" Внутренняя часть [npc.her] горла покрыта чувствительными мясистыми ребрами, которые дарят [npc.herHim] дополнительное удовольствие при стимуляции.");
						break;
					case TENTACLED:
						sb.append(" [npc.Her] горло заполнено крошечными щупальцами, которые извиваются и шевелятся сами по себе.");
						break;
				}
			}
		}
		
		
		// Tongue & blowjob:
		if(owner.isFeral()) {
			sb.append(" Во рту [npc.Her] находится [npc.a_tongueLength], [npc.tongueColour(true)] [npc.tongueRace] [npc.tongue]#IF(npc.isPiercedTongue()), который проколот#ENDIF.");
		} else {
			sb.append(" "+face.getTongue().getType().getBodyDescription(owner));
		}
		
		for(TongueModifier tm : TongueModifier.values()) {
			if(owner.hasTongueModifier(tm)) {
				switch(tm) {
					case RIBBED:
						sb.append(" Вся его длина покрыта твердыми мясистыми ребрами, которые непременно принесут дополнительное удовольствие любому проникающему отверстию.");
						break;
					case TENTACLED:
						sb.append(" Его поверхность покрыта множеством маленьких щупалец, которые извиваются и шевелятся сами по себе.");
						break;
					case BIFURCATED:
						sb.append(" Ближе к кончику он раздвоен, из-за чего [npc.her] язык раздвоен, словно у змеи.");
						break;
					case FLAT:
						if(owner.hasTongueModifier(TongueModifier.WIDE)) {
							sb.append(" Он гораздо более плоский и широкий, чем это считается нормальным.");
						} else {
							sb.append(" Оно гораздо более плоское, чем принято считать нормой.");
						}
						break;
					case STRONG:
						sb.append(" Оно намного сильнее обычного языка.");
						break;
					case WIDE:
						if(!owner.hasTongueModifier(TongueModifier.FLAT)) {
							sb.append(" Оно гораздо шире, чем принято считать нормой.");
						}
						break;
					case TAPERED:
						sb.append(" Оно сужается от основания к кончику.");
						break;
				}
			}
		}
		
		
		if (owner.isPlayer() || owner.isAreaKnownByCharacter(CoverableArea.MOUTH, Main.game.getPlayer())) {
			if(face.getMouth().getOrificeMouth().isVirgin()) { //TODO
				sb.append(" [npc.SheHas] [style.colourExcellent(никогда не получала минет)], поэтому [npc.is] не уверена, сколько [npc.she] сможет пролезть в [npc.her] горло.</span>");
				
			} else {
				boolean virginityLossFound = false;
				for(SexAreaPenetration pt : SexAreaPenetration.values()) {
					if(pt.isTakesVirginity()) {
						if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pt))!=null) {
							sb.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pt)) + "</span>");
							virginityLossFound = true;
							break;
						}
					}
				}
				if(!virginityLossFound) {
					sb.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] лишилась [npc.her] оральной девственности.</span>");
				}
				
				sb.append(" Оно "+Capacity.getCapacityFromValue(face.getMouth().getOrificeMouth().getStretchedCapacity()).getDescriptor(true)+", и в него с комфортом входят предметы диаметром до [style.colourSex(\", "+ Units.size(Capacity.getMaximumComfortableDiameter(face.getMouth().getOrificeMouth().getElasticity(), face.getMouth().getOrificeMouth().getRawCapacityValue(), true)) + ")] в диаметре.");
				
				if(Main.game.isPenetrationLimitationsEnabled()) {
					switch(owner.getFaceDepth()) {
						default:
							sb.append(" Горло [npc.Her] — это <span style='color:"+owner.getFaceDepth().getColour().toWebHexString()+";'>[npc.throatDepth]</span>,");
							break;
						case TWO_AVERAGE:
							sb.append(" горло [npc.Her] имеет <span style='color:"+owner.getFaceDepth().getColour().toWebHexString()+";'>среднюю глубину</span>,");
							break;
					}
					if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
						if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
							sb.append(" и как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"[npc.she] можно [style.colourMinorGood(комфортно)] растягивать для размещения объектов максимальной длины [style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthUncomfortable())+")].");
						} else {
							sb.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthComfortable())+")] проникающего объекта, и [style.colourMinorBad(с дискомфортом)] принимать [style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthUncomfortable())+")].");
						}
						
					} else {
						if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
							sb.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", в сочетании с тем фактом, что тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorGood(комфортно)] растягивать, чтобы вмещать объекты [style.colourSex(любой длины)].");
						} else {
							sb.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getFaceMaximumPenetrationDepthComfortable())+")] проникающего объекта, а так как тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorBad(с дискомфортом)] растягивать для размещения объектов [style.colourSex(любой длины)].");
						}
					}
				}
				
				// Throat wetness:
				switch (face.getMouth().getOrificeMouth().getWetness(owner)) {
					case ZERO_DRY:
						sb.append(" Рот и горло [npc.Her] [style.colourWetness(очень сухие)], и [npc.she] с трудом может выдавить хоть каплю слюны.");
						break;
					case ONE_SLIGHTLY_MOIST:
						sb.append(" Рот и горло [npc.Her] [style.colourWetness(суше, чем у большинства)], и [npc.she] выделяет лишь небольшое количество слюны.");
						break;
					case TWO_MOIST:
						sb.append(" Рот и горло [npc.Her] [style.colourWetness(немного суше, чем у большинства)], и [npc.she] выделяет меньше слюны, чем можно было бы считать нормой.");
						break;
					case THREE_WET:
						sb.append(" Рот и горло [npc.Her] [style.colourWetness(обычной влажности)], и [npc.she] выделяет среднее количество слюны.");
						break;
					case FOUR_SLIMY:
						sb.append(" Рот и горло [npc.Her] [style.colourWetness(влажнее, чем у большинства)], и [npc.she] выделяет больше слюны, чем обычный человек.");
						break;
					case FIVE_SLOPPY:
						sb.append(" Рот и горло [npc.Her] [style.colourWetness(значительно влажнее, чем у большинства)], и [npc.she] выделяет гораздо больше слюны, чем обычный человек.");
						break;
					case SIX_SOPPING_WET:
						sb.append(" Рот и горло [npc.Her] [style.colourWetness(всегда влажные и склизкие)], а [npc.she] выделяет столько слюны, что [npc.she] приходится [npc.herself] глотать каждые несколько секунд.");
						break;
					case SEVEN_DROOLING:
						sb.append(" Рот и горло [npc.Her] [style.colourWetness(переполнены слюной)], и несмотря на непрерывные попытки [npc.her] проглотить эту скользкую массу, из уголков рта [npc.her] постоянно течет струйка слюны.");
						break;
				}

				// Elasticity & plasticity:
				sb.append(" Горло [npc.Her]");
				switch (face.getMouth().getOrificeMouth().getElasticity()) {
					case ZERO_UNYIELDING:
						sb.append(" [style.colourElasticity(чрезвычайно неподатливое)],");
						break;
					case ONE_RIGID:
						sb.append(" [style.colourElasticity(требует огромных усилий, чтобы растянуться)],");
						break;
					case TWO_FIRM:
						sb.append(" [style.colourElasticity(растягивается довольно неохотно)],");
						break;
					case THREE_FLEXIBLE:
						sb.append(" [style.colourElasticity(неохотно растягивается)], когда используется как сексуальное отверстие,");
						break;
					case FOUR_LIMBER:
						sb.append(" является [style.colourElasticity(довольно устойчивым к растяжению)],");
						break;
					case FIVE_STRETCHY:
						sb.append(" [style.colourElasticity(растягивается довольно легко)],");
						break;
					case SIX_SUPPLE:
						sb.append(" [style.colourElasticity(растягивается очень легко)],");
						break;
					case SEVEN_ELASTIC:
						sb.append(" является [style.colourElasticity(чрезвычайно эластичным)],");
						break;
				}
				sb.append(" и после использования оно "+face.getMouth().getOrificeMouth().getPlasticity().getDescription()+".");
			}
			
		} else {
			sb.append(" [style.colourDisabled(Вы знаете [npc.herHim] недостаточно хорошо, чтобы понимать, насколько умело [npc.she] занимается оральным сексом.)]");
		}
		
		sb.append("</p>");

		
		// Describe body:
		sb.append(getHeader("Торс"));
		if(owner.isFeral()) {
			sb.append("[style.colourFeral(торс [npc.Her] выглядит совершенно диким и ничем не отличается от торса обычного [npc.race].)] ");
		}
		sb.append(owner.getTorsoType().getBodyDescription(owner));
		sb.append(" [npc.SheHasFull] <span style='color:"+ BodySize.valueOf(getBodySize()).getColour().toWebHexString() + ";'>" + BodySize.valueOf(getBodySize()).getName(true) + "</span>, "
						+ "<span style='color:"+ Muscle.valueOf(getMuscle()).getColour().toWebHexString() + ";'>" +Muscle.valueOf(getMuscle()).getName(false) + "</span>"
							+ " body, giving [npc.herHim] <span style='color:"+ owner.getBodyShape().toWebHexStringColour() + ";'>[npc.a_bodyShape]</span> форма тела.");
		if(torso.getType().getTags().contains(BodyPartTag.ALLOWS_FLIGHT)) {
			sb.append(" [style.colourBlue(Уникальное строение тела [npc.namePos] позволяет [npc.herHim] летать!)]");
		}
		
		
		// Pregnancy:
		if(owner.isVisiblyPregnant()) {
			if(owner.getLegConfiguration()!=LegConfiguration.BIPEDAL) {
				sb.append(" Живот [npc.her] [style.colourFeral(дикий торс)]");
			} else {
				sb.append("живот [npc.Her]");
			}
			if(owner.hasStatusEffect(StatusEffect.PREGNANT_1)){
				sb.append(" слегка вздут, и любому, кто присмотрится, становится ясно, что <span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs] беременна</span>.");
				
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_2)){
				sb.append(" сильно раздут, и любому, кто бросит взгляд на [npc.her], сразу видно, что <span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs] беременна</span>.");
			
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_3)){
				sb.append(" сильно раздуто, и любому, кто бросит взгляд в [npc.her] сторону, будет совершенно очевидно, что <span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc.sheIs] скоро должна родить</span>.");
			}
		}
		sb.append("</p>");
		
		
		
		// Breasts:
		sb.append(getHeader("Грудь"));
		if(owner.isFeral() && !owner.getFeralAttributes().isBreastsPresent()) {
			sb.append("Поскольку [npc.nameHasFull] тело дикого [npc.race], у [npc.she] [npc.do] нет никаких грудей!");
			
		} else {
			Breast viewedBreast = breast;
			if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.MILK)) {
				viewedBreast = new Breast(breast.getType(),
						breast.getShape(),
						(int)(breast.getRawSizeValue()*(1.75f)),
						(int)((breast.getRawMilkStorageValue()+100)*(2.25f)),
						breast.getRows(),
						breast.getNipples().getNippleSizeValue(),
						breast.getNipples().getNippleShape(),
						breast.getNipples().getAreolaeSizeValue(),
						breast.getNipples().getAreolaeShape(),
						breast.getNippleCountPerBreast(),
						breast.getNipples().getOrificeNipples().getRawCapacityValue(),
						breast.getNipples().getOrificeNipples().getDepth(null).getValue(),
						breast.getNipples().getOrificeNipples().getElasticity().getValue(),
						breast.getNipples().getOrificeNipples().getPlasticity().getValue(),
						breast.getNipples().getOrificeNipples().isVirgin());
				sb.append(" <i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>Психоактивное молоко, которое ты недавно выпила, искажает твое восприятие "+(owner.isPlayer()?"твоей":"[npc.namePos]")+" груди!</i>");
			}
			if(viewedBreast.getRawSizeValue()>0){
				sb.append(" [npc.SheHasFull] " + Util.intToString(owner.getBreastRows()) + " пара" + (owner.getBreastRows() == 1 ? "" : "s") + " из ");
				if(Main.game.isVestigialMultiBreastsEnabled() && owner.getBreastRows()>1) {
					sb.append("[npc.breasts]");
				} else {
					sb.append(viewedBreast.getSize().getDescriptor()+" [npc.breasts]");
				}
				
				if(owner.getBreastRows()==1) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append(", которые вполне помещаются в спортивный лифчик.");
					} else {
						sb.append(", которые вполне помещаются в "+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+" "+viewedBreast.getSize().getCupSizeName()+"-го размера бюстгальтер.");
					}
					
				} else if(owner.getBreastRows()==2) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append(", при этом верхняя пара [npc.her] сидит уютно в спортивном лифчике,"
								+(Main.game.isVestigialMultiBreastsEnabled()
									?" а нижняя пара размером едва заметна."
									:" а нижняя пара чуть меньше."));
					} else {
						sb.append(", при этом верхняя пара [npc.her] сидит уютно в "
								+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+" "+viewedBreast.getSize().getCupSizeName()+"-го размера бюстгальтере,"
										+(Main.game.isVestigialMultiBreastsEnabled()
												?" а нижняя пара размером едва заметна."
												:" а нижняя пара чуть меньше."));
					}
					
				} else if(owner.getBreastRows()>2) {
					if (viewedBreast.getSize().isTrainingBraSize()) {
						sb.append(", при этом верхняя пара [npc.her] сидит уютно в спортивном лифчике,"
								+(Main.game.isVestigialMultiBreastsEnabled()
										?" а нижние пары размером едва заметны."
										:" причем каждая пара ниже будет чуть меньше предыдущей."));
					} else {
						sb.append(", при этом верхняя пара [npc.her] сидит уютно в "
									+UtilText.generateSingularDeterminer(viewedBreast.getSize().getCupSizeName())+" "+viewedBreast.getSize().getCupSizeName()+"-го размера бюстгальтере,"
											+(Main.game.isVestigialMultiBreastsEnabled()
													?" а нижние пары размером едва заметны."
													:" причем каждая пара ниже будет чуть меньше предыдущей."));
					}
				}
				
			} else {
				sb.append(" [npc.SheHasFull] совершенно плоская грудь");
				if(owner.getBreastRows()==1) {
					sb.append(", с одной парой грудных мышц.");
				} else {
					sb.append(", с "+Util.intToString(owner.getBreastRows())+" парами грудных мышц.");
				}
			}
			
			sb.append(" " + getBreastDescription(owner, viewedBreast));
		}
		sb.append("</p>");
		
		
		// BreastsCrotch:
		if(owner.isAreaKnownByCharacter(CoverableArea.BREASTS_CROTCH, Main.game.getPlayer())) {
			if(owner.hasBreastsCrotch()) {
				sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"Вымя":"Лобковые соски"));
				sb.append(getBreastCrotchDescription(owner, breastCrotch));
				sb.append("</p>");
				
			} else if(this.leg.getLegConfiguration()!=LegConfiguration.BIPEDAL
					|| this.isFeral()
					|| (this.getRaceStage()==RaceStage.GREATER && RacialBody.valueOfRace(this.getRace()).getBreastCrotchType()!=BreastType.NONE && Main.getProperties().getUddersLevel()==2)) {
				sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"Вымя":"Лобковые соски"));
				sb.append("[style.colourDisabled(У [npc.She] [npc.do] нет никаких лобковых молочных желез или вымени.)]");
				sb.append("</p>");
			}
			
		} else if(leg.getLegConfiguration()!=LegConfiguration.BIPEDAL
				|| this.isFeral()
				|| (this.getRaceStage()==RaceStage.GREATER && Main.getProperties().getUddersLevel()==2)) {
			sb.append(getHeader(owner.getBreastCrotchShape()==BreastShape.UDDERS?"Вымя":"Лобковые соски"));
			if(owner.hasBreastsCrotch() && owner.isBreastsCrotchVisibleThroughClothing() && leg.getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				sb.append("Хотя вы раньше не видели обнаженного живота [npc.her], [npc.her] [npc.crotchBoobsSize], [npc.crotchBoobsCups]-го размера [npc.crotchBoobs] довольно отчетливо выпирают из-под [npc.her] [npc.topClothing(STOMACH)].");
			} else {
				sb.append("[style.colourDisabled(Вы раньше не видели обнаженного живота [npc.her], поэтому не знаете, есть ли у [npc.sheHasFull] какие-либо лобковые молочные железы или вымя.)]");
			}
			sb.append("</p>");
		}
		
		
		// Arms and legs:

		// Arms:
		sb.append(getHeader("Руки"));
		if(owner.isFeral() && !owner.getFeralAttributes().isArmsOrWingsPresent()) {
			if(owner.getLegConfiguration()==LegConfiguration.AVIAN) {
				sb.append("В теле дикой [npc.race], [npc.nameHasFull] [npc.she] [npc.has] [npc.armRows] крылья, которые являются [npc.materialCompositionDescriptor] прекрасными [npc.armFullDescription(true)]!");
			} else {
				sb.append("В теле дикой [npc.race], [npc.nameHasFull] [npc.she] [npc.do] нет рук!");
			}
			
		} else {
			sb.append(arm.getType().getBodyDescription(owner));
			
			if(owner.getHandNailPolish().getPrimaryColour() != PresetColour.COVERING_NONE) {
				if(owner.getArmType().equals(ArmType.HARPY)) {
					sb.append(" Маленький коготок на [npc.her] большом пальце был покрашен в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+".");
				} else {
					sb.append(" Ногти [npc.Her] были покрашены в "+owner.getCovering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS).getFullDescription(owner, true)+".");
				}
			}
			
			if(Main.game.isBodyHairEnabled()) {
				if(owner.getUnderarmHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getUnderarmHair()) {
						case ZERO_NONE:
							sb.append(" Нет никаких следов грубой "+owner.getUnderarmHairType().getName(owner)+" в подмышках [npc.her].");
							break;
						case ONE_STUBBLE:
							sb.append(" [npc.SheHasFull] участок грубой кожи, "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case TWO_MANICURED:
							sb.append(" [npc.SheHasFull] участок грубой кожи, "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case THREE_TRIMMED:
							sb.append(" [npc.SheHasFull] участок грубой кожи, "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case FOUR_NATURAL:
							sb.append(" [npc.SheHasFull] естественное количество жестких "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case FIVE_UNKEMPT:
							sb.append(" [npc.SheHasFull] неопрятную массу жестких "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case SIX_BUSHY:
							sb.append(" [npc.SheHasFull] густую, жесткую массу "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case SEVEN_WILD:
							sb.append(" [npc.SheHasFull] дикую, жесткую массу "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
					}
				} else {
					switch(owner.getUnderarmHair()) {
						case ZERO_NONE:
							sb.append(" Нет и следа от "+owner.getUnderarmHairType().getName(owner)+" в подмышках [npc.her].");
							break;
						case ONE_STUBBLE:
							sb.append(" [npc.SheHasFull] клочок щетины на "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case TWO_MANICURED:
							sb.append(" [npc.SheHasFull] аккуратно подстриженной зоны "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case THREE_TRIMMED:
							sb.append(" [npc.SheHasFull] подстриженной зоны "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case FOUR_NATURAL:
							sb.append(" [npc.SheHasFull] естественного количества "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case FIVE_UNKEMPT:
							sb.append(" [npc.SheHasFull] неопрятной массы "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case SIX_BUSHY:
							sb.append(" [npc.SheHasFull] густой, пушистой массы "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
						case SEVEN_WILD:
							sb.append(" [npc.SheHasFull] дикой, пушистой массы "+owner.getUnderarmHairType().getFullDescription(owner, true)+" в каждой из [npc.her] подмышек.");
							break;
					}
				}
			}
			
			if (!(owner.isFeral() && owner.getArmType().allowsFlight())) {
				sb.append(" [npc.Her] [npc.arms] это "+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))
						+", и на вид они <span style='color:"+owner.getFemininity().getColour().toWebHexString()+";'>"+owner.getFemininity().getName(false)+" на вид.");
			}
			
			if(arm.getType().allowsFlight()) {
				if(this.getBodyMaterial() == BodyMaterial.SLIME) {
					sb.append(" [style.colourSlime(Поскольку они состоят из слайма, полет становится невозможным...)]");
				} else if(this.getBodyMaterial() == BodyMaterial.SILICONE) {
					sb.append(" [style.colourDoll(Поскольку они состоят из силикона, полет становится невозможным...)]");
				} else if(this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue()>WingSize.THREE_LARGE.getValue()) {
					sb.append(" Они недостаточно велики, чтобы позволить [npc.herHim] летать.");
				} else {
					sb.append(" [style.colourBlue(Они достаточно велики и сильны, чтобы позволить [npc.herHim] летать!)]");
				}
			}
		}
		sb.append("</p>");

		
		// Legs:
		if(owner.getLegType().isLegsReplacedByTentacles()) {
			sb.append(getHeader("Щупальца-ноги"));
			
			sb.append("Вместо ног [npc.name] [npc.has] [npc.tentacleCount] щупальца.");
			sb.append(owner.getTentacleType().getGirthDescription(owner));
			sb.append(" Их длина составляет [npc.tentacleLength(true)], а у основания диаметр каждого составляет [npc.tentacleBaseDiameter(true)] ([npc.tentacleBaseCircumference(true)] в обхвате).");
			for(BodyPartTag bpt : owner.getTentacleType().getTags()) {
				switch(bpt) {
					case TAIL_PREHENSILE:
						sb.append(" [npc.She] [npc.has] очень высокий уровень контроля над каждым из этих щупалец, что позволяет [npc.herHim] использовать их, чтобы поднимать и манипулировать предметами.");
						break;
					case TAIL_SLEEP_HUGGING:
						break;
					case TAIL_SUITABLE_FOR_PENETRATION:
						sb.append(" Каждое из них можно использовать как предмет для проникновения во время секса, и при проникновении в отверстие можно ввести максимум [npc.tentaclePenetrationLength(true)].");
						break;
					case TAIL_TAPERING_EXPONENTIAL:
						sb.append(" Толщина каждого щупальца быстро уменьшается от основания, поэтому диаметр [npc.tentacleTip] каждого из них составляет [npc.tentacleTipDiameter(true)] ([npc.tentacleTipCircumference(true)] в обхвате).");
						break;
					case TAIL_TAPERING_LINEAR:
						sb.append(" Толщина каждого из них равномерно уменьшается от основания, поэтому [npc.tentacleTip] имеет диаметр [npc.tentacleTipDiameter(true)] ([npc.tentacleTipCircumference(true)] в обхвате).");
						break;
					case TAIL_TAPERING_NONE:
						sb.append(" Толщина каждого из них не уменьшается от основания, поэтому каждый щупальце имеет постоянный диаметр по всей длине до самого [npc.tentacleTip].");
						break;
					case TAIL_TAPERING_BULBOUS:
						sb.append(" Толщина каждого из них сначала увеличивается, а затем равномерно уменьшается от основания, образуя луковицеобразную овальную форму.");
						break;
					default:
						break;
				}
			}
			sb.append("<br/>");
			
		} else {
			sb.append(getHeader("Ноги"));
		}
		if(owner.isFeral()) {
			String feralLegsPrefix = "Как и остальное тело [npc.her], [npc.her] ";
			switch(owner.getLegConfiguration()) {
				case ARACHNID:
				case CEPHALOPOD:
				case AVIAN:
					sb.append(feralLegsPrefix).append("[npc.legRace] [npc.legs] по своей природе полностью [style.colourFeral(дикие)]. ");
					break;
				case BIPEDAL:
					break;
				case TAIL:
					sb.append(feralLegsPrefix).append("Хвост [npc.legRace], который [npc.sheHasFull] вместо ног, по своей природе полностью [style.colourFeral(дикий)]. ");
					break;
				case TAIL_LONG:
					sb.append(feralLegsPrefix).append("длинный хвост [npc.legRace], который [npc.sheHasFull] вместо ног, по своей природе полностью [style.colourFeral(дикий)]. ");
					if(!owner.isFeral()) {
						sb.append(" Он составляет большую часть [npc.heightValue]-длины тела [npc.her],");
						sb.append(" и от головы [npc.her] он равномерно сужается к кончику, где заканчивается диаметром [npc.tailTipDiameter(true)] ([npc.tailTipCircumference(true)] в обхвате).");
						sb.append(" При использовании для проникновения в отверстие можно ввести максимум [npc.tailPenetrationLength(true)]. ");
					} else {
						sb.append(" Его [npc.tailLength]-длина составляет часть общей длины тела [npc.her], равной "+Units.size(owner.getHeightValue()+owner.getLegTailLength(false), Units.ValueType.NUMERIC, Units.UnitType.LONG)+".");
						sb.append(" Оно плавно сужается к кончику, диаметр которого составляет [npc.tailTipDiameter(true)] (при обхвате [npc.tailTipCircumference(true)]).");
						sb.append(" При использовании для проникновения в отверстие можно ввести как [npc.her] хвост, так и тело, на максимальную глубину до [npc.tailPenetrationLength(true)]. ");
					}
					break;
				case QUADRUPEDAL:
				case WINGED_BIPED:
					sb.append(feralLegsPrefix).append("[npc.legs], являясь частью тела [npc.her] [npc.legRace], полностью [style.colourFeral(дикого происхождения)]. ");
					break;
			}
			if(owner.getLegConfiguration().getNumberOfLegs()>0) {
				sb.append("[npc.Her] ног(и) [npc.materialCompositionDescriptor] — это [npc.legFullDescription(true)], а [npc.her] стопы представляют собой ").append(owner.getLegType().getFootType().getFootNamePlural()).append(".");
			} else {
				sb.append("Это [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].");
			}
			
		} else {
			switch(owner.getLegConfiguration()) {
				case ARACHNID:
				case CEPHALOPOD:
				case AVIAN:
					sb.append("[npc.Her] [npc.legs], являясь частью тела [npc.her] [npc.legRace], полностью [style.colourFeral(дикого происхождения)]. ");
					break;
				case BIPEDAL:
					break;
				case TAIL:
					sb.append("[npc.Her] хвост [npc.legRace], который [npc.sheHasFull] вместо ног, полностью [style.colourFeral(дикого происхождения)]. ");
					break;
				case TAIL_LONG:
					sb.append("Длинный [npc.Her] хвост [npc.legRace], который [npc.sheHasFull] вместо ног, полностью [style.colourFeral(дикого происхождения)].");
					sb.append(" Его длина составляет ").append(Units.size(owner.getLegTailLength(false))).append(" в длину,");
					sb.append(" и он плавно сужается к самому кончику, диаметр которого составляет [npc.tailTipDiameter(true)] ([npc.tailTipCircumference(true)] в обхвате).");
					sb.append(" При использовании для проникновения в отверстие можно ввести максимум [npc.tailPenetrationLength(true)].");
					break;
				case QUADRUPEDAL:
				case WINGED_BIPED:
					sb.append("[npc.Her] [npc.legs], являясь частью тела [npc.her] [npc.legRace], полностью [style.colourFeral(дикого происхождения)]. ");
					break;
			}
			if(owner.getLegConfiguration().getNumberOfLegs()>0) {
				sb.append(leg.getType().getBodyDescription(owner));
			} else {
				sb.append(" Это [npc.materialCompositionDescriptor] [npc.legFullDescription(true)].");
			}
		}

		if(owner.getLegConfiguration().getNumberOfLegs()>0) {
			switch(owner.getFootStructure()) {
				case NONE:
				case TENTACLED:
					break;
				case DIGITIGRADE:
					sb.append(" [npc.Her] [npc.legs] и [npc.feet] — это [style.colourTFGeneric("+owner.getFootStructure().getName()+")], что означает, что [npc.she] естественным образом [npc.verb(walk)] на [npc.her] пальцах.");
					break;
				case PLANTIGRADE:
					sb.append(" [npc.Her] [npc.legs] и [npc.feet] — это [style.colourTFGeneric("+owner.getFootStructure().getName()+")], что означает, что [npc.she] естественным образом [npc.verb(walk)], когда [npc.her] ступни плотно прижаты к земле.");
					break;
				case UNGULIGRADE:
					sb.append(" [npc.Her] [npc.legs] и [npc.feet] — это [style.colourTFGeneric("+owner.getFootStructure().getName()+")], что означает, что [npc.she] естественным образом [npc.verb(walk)] на [npc.her] копытах.");
					break;
				case ARACHNOID:
					sb.append(" [npc.Her] [npc.legs] и [npc.feet] — это [style.colourTFGeneric("+owner.getFootStructure().getName()+")], а это значит, что [npc.she] [npc.verb(walk)] на концах [npc.her] сегментированных паукообразных ног.");
					break;
			}
		}
		
		if(owner.getFootNailPolish().getPrimaryColour() != PresetColour.COVERING_NONE) {
			sb.append(owner.getLegType().getFootType().getFootNailPolishDescription(owner));
		}
		
		sb.append("<br/>");
		
		if(owner.getLegCount()>0) {
			sb.append(" [npc.Her] [npc.legs] — это "+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))+", и ");
			if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
				sb.append("[style.colourMasculineStrong(обладают очень мужественными формами)].");
				
			} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
				sb.append("[style.colourMasculine(обладают мужественными формами)].");
				
			} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
				sb.append("[style.colourAndrogynous(выглядят довольно андрогинно)].");
				
			} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
				sb.append("[style.colourFeminine(обладают женственными формами)].");
				
			} else {
				sb.append("[style.colourFeminineStrong(обладают чрезвычайно женственными формами)].");
			}
			
		} else if(!owner.isFeral()) {
			sb.append(" [npc.Her] [npc.leg(true)] — это "+(Util.randomItemFrom(owner.getBodyShape().getLimbDescriptors()))+", и ");
			if (femininity <= Femininity.MASCULINE_STRONG.getMaximumFemininity()) {
				sb.append("[style.colourMasculineStrong(имеет очень мужественные очертания)].");
				
			} else if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
				sb.append("[style.colourMasculine(имеет мужественные очертания)].");
				
			} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()) {
				sb.append("[style.colourAndrogynous(выглядит довольно андрогинно)].");
				
			} else if (femininity <= Femininity.FEMININE.getMaximumFemininity()) {
				sb.append("[style.colourFeminine(имеет женственные очертания)].");
				
			} else {
				sb.append("[style.colourFeminineStrong(имеет чрезвычайно женственные очертания)].");
			}
		}
		
		sb.append("</p>");
		
		
		// Wing:
		if(wing.getType()!=WingType.NONE) {
			sb.append(getHeader("Крылья"));
			if(owner.getLegConfiguration().isWingsOnLegConfiguration()) {
				sb.append("Растущие по обе стороны тела [npc.her] [npc.legConfiguration], ");
			} else {
				sb.append("Растущие из лопаток [npc.her], ");
			}
			sb.append(wing.getType().getBodyDescription(owner));
			if(wing.getType().allowsFlight()) {
				if(this.getBodyMaterial() == BodyMaterial.SLIME) {
					sb.append(" [style.colourSlime(Поскольку они состоят из слайма, полет становится невозможным...)]");
				} else if(this.getBodyMaterial() == BodyMaterial.SILICONE) {
					sb.append(" [style.colourDoll(Поскольку они состоят из силикона, полет становится невозможным...)]");
				} else if(wing.getSizeValue()>=owner.getLegConfiguration().getMinimumWingSizeForFlight(owner.getBody()).getValue()) {
					sb.append(" [style.colourBlue(Они достаточно велики и сильны, чтобы позволить [npc.herHim] летать!)]");
				} else {
					sb.append(" Они недостаточно велики, чтобы позволить [npc.herHim] летать.");
				}
				
			} else {
				sb.append(" Они совершенно неспособны к полету.");
			}
			sb.append("</p>");
		}
	
		
		// Tail:
		if(tail.getType()!=TailType.NONE) {
			sb.append(getHeader("Хвост"));
			sb.append(owner.getTailType().getBodyDescription(owner));
			sb.append(owner.getTailType().getGirthDescription(owner));
			if(owner.getTailCount()==1) {
				sb.append(" Его длина составляет [npc.tailLength(true)], а у основания диаметр равен [npc.tailBaseDiameter(true)] (окружность — [npc.tailBaseCircumference(true)]).");
				if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_EXPONENTIAL)) {
					sb.append(" Он стремительно сужается от основания, поэтому у [npc.tailTip] его диаметр составляет [npc.tailTipDiameter(true)] (окружность — [npc.tailTipCircumference(true)]).");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_LINEAR)) {
					sb.append(" Он равномерно сужается от основания, поэтому у [npc.tailTip] его диаметр составляет [npc.tailTipDiameter(true)] (окружность — [npc.tailTipCircumference(true)]).");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_BULBOUS)) {
					sb.append(" Его толщина плавно увеличивается, а затем равномерно убывает от основания, придавая ему луковичную овальную форму.");
					
				} else {
					sb.append(" Он не сужается от основания и имеет постоянный диаметр по всей длине до самого [npc.tailTip].");
				}
				
			} else {
				sb.append(" Их длина составляет [npc.tailLength(true)], а у основания каждый из них имеет диаметр [npc.tailBaseDiameter(true)] (окружность — [npc.tailBaseCircumference(true)]).");
				if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_EXPONENTIAL)) {
					sb.append(" Они стремительно сужаются от основания, поэтому у их [npc.tailTips] диаметр составляет [npc.tailTipDiameter(true)] (окружность — [npc.tailTipCircumference(true)]).");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_LINEAR)) {
					sb.append(" Они равномерно сужаются от основания, поэтому у их [npc.tailTips] диаметр составляет [npc.tailTipDiameter(true)] (окружность — [npc.tailTipCircumference(true)]).");
					
				} else if(owner.getTailType().getTags().contains(BodyPartTag.TAIL_TAPERING_BULBOUS)) {
					sb.append(" Их толщина плавно увеличивается, а затем равномерно убывает от основания, придавая им луковичную овальную форму.");
					
				} else {
					sb.append(" Они не сужаются у основания и имеют постоянный диаметр по всей длине до самого [npc.tailTips].");
				}
			}
			
			if(owner.isTailSuitableForPenetration()) {
				sb.append(" При использовании для проникновения в отверстие можно ввести максимум [npc.tailPenetrationLength(true)].");
			} else {
				sb.append(" Они не подходят для проникновения в отверстия.");
			}
			sb.append("</p>");
		}
		
		
		// Spinneret:
		if(owner.hasSpinneret()) {
			sb.append(getHeader("Паутинный аппарат"));
			sb.append(getSpinneretDescription(owner));
			sb.append("</p>");
		}
		
		
		// Cloaca:
		if(owner.isPlayer()
				|| owner.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer())
				|| owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())
				|| owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			switch(owner.getGenitalArrangement()) {
				case NORMAL:// Don't need to add a description for normal arrangement I think.
					break;
				case CLOACA:
				case CLOACA_BEHIND:
					sb.append(getHeader("Клоака"));
					sb.append("[style.colourFeral("+owner.getGenitalArrangement().getDescription()+")]");
					sb.append("</p>");
					break;
			}
		}
		
		
		// Ass & hips:
		sb.append(getHeader("Задница"));
		if(!owner.isFeral() && ass.isFeral(owner)) {
			sb.append("[npc.Her] [npc.hips+] и [npc.assSize] [npc.ass] — это [style.colourFeral(часть дикого низа [npc.her])], и они [npc.materialCompositionDescriptor] [npc.assFullDescription(true)].");
		} else {
			sb.append("[npc.Her] [npc.hips+] и [npc.assSize] [npc.ass] — это [npc.materialCompositionDescriptor] [npc.assFullDescription(true)].");
		}
		
		if(owner.isAreaKnownByCharacter(CoverableArea.ANUS, Main.game.getPlayer())) {
			sb.append(" " + getAssDescription(owner, false));
		} else {
			sb.append(" [style.colourDisabled(Вы еще не видели голую задницу [npc.her], поэтому не знаете, как выглядит [npc.her] анус.)]");
		}
		sb.append("</p>");
		
		if(owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer()) && owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			// Vagina, virgin/capacity, wetness:
			if (vagina.getType() == VaginaType.NONE && penis.getType() == PenisType.NONE) {
				sb.append(getHeader("Гениталии"));
				sb.append(getMoundDescription(owner));
				sb.append("</p>");
			}
		}

		if(owner.isAreaKnownByCharacter(CoverableArea.PENIS, Main.game.getPlayer())) {
			// Penises, cum production, testicle size, capacity:
			if (owner.hasPenis()) {
				sb.append(getHeader("Пенис"));
				sb.append(getPenisDescription(owner));
				sb.append("</p>");
			}
			
		} else {
			sb.append(getHeader("Пенис"));
			if (observant) {
				if(owner.hasPenis()) {
					sb.append("[style.colourDisabled(Благодаря вашему признаку '"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"', вы понимаете, что у [npc.sheHasFull] есть член, но так как вы раньше не видели [npc.her] обнаженное пах, вы не знаете, как это выглядит.)]");
				} else {
					sb.append("[style.colourDisabled(Вы раньше не видели обнаженного паха [npc.her], но благодаря вашему признаку '"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"', вы понимаете, что у [npc.she] нет члена.)]");
				}
				
			} else {
				sb.append("[style.colourDisabled(Вы раньше не видели обнаженного паха [npc.her], поэтому не знаете, как выглядит член [npc.her], и даже есть ли он у [npc.sheHasFull].)]");
			}
			sb.append("</p>");
		}
		
		if(owner.isAreaKnownByCharacter(CoverableArea.VAGINA, Main.game.getPlayer())) {
			// Vagina, virgin/capacity, wetness:
			if (vagina.getType() != VaginaType.NONE) {
				sb.append(getHeader("Киска"));
				sb.append(getVaginaDescription(owner));
				sb.append("</p>");
			}
		} else {
			sb.append(getHeader("Киска"));
			if (observant){
				if(vagina.getType() != VaginaType.NONE){
					sb.append("[style.colourDisabled(Благодаря вашему признаку '"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"', вы понимаете, что у [npc.sheHasFull] киска, но так как вы раньше не видели [npc.her] обнаженное пах, вы не знаете, как это выглядит.)]");
					
				} else {
					sb.append("[style.colourDisabled(Вы раньше не видели обнаженного паха [npc.her], но благодаря вашему признаку '"+Perk.OBSERVANT.getName(Main.game.getPlayer())+"' черту, можно понять, что у [npc.she] нет киски.)]");
				}
				
			} else {
				sb.append("[style.colourDisabled(Вы еще не видели лоно [npc.her] нагишом, поэтому не знаете, как выглядит киска [npc.her], и есть ли она у [npc.sheHasFull] вообще.)]");
			}
			sb.append("</p>");
		}
		
		// Tattoos:
		StringBuilder tattooSB = new StringBuilder();
		for(Entry<InventorySlot, Tattoo> tattoEntry : owner.getTattoos().entrySet()) {
			InventorySlot tattooSlot = tattoEntry.getKey();
			boolean knowsArea = owner.isPlayer();
			if(!knowsArea) {
				caLoop:
				for(CoverableArea ca : CoverableArea.values()) {
					for(InventorySlot slot : ca.getAssociatedInventorySlots(owner)) {
						if(tattooSlot==slot) {
							knowsArea = true;
							break caLoop;
						}
					}
				}
			}
			if(knowsArea) {
				if(tattooSB.length()>0) {
					tattooSB.append("<br/>");
				}
				Tattoo tattoo = tattoEntry.getValue();
				if(tattoo.getBodyOverviewDescription().isEmpty()) { // Old version support
					tattooSB.append("<span style='color:"+tattoo.getPrimaryColour().toWebHexString()+";'>"+Util.capitaliseSentence(tattooSlot.getTattooSlotName())+":</span> ");
					tattooSB.append(tattoo.getDescription(owner));
					tattooSB.append(".");
					
				} else {
					tattooSB.append("На [npc.her] [style.boldBlueSteel("+tattooSlot.getTattooSlotName()+")], [npc.sheHasFull] ");
					tattooSB.append(tattoo.getBodyOverviewDescription());
					if(tattoo.getType()!=TattooType.getTattooTypeFromId("innoxia_misc_none")) {
						tattooSB.append(", которая в основном окрашена в ");
						tattooSB.append("<span style='color:"+tattoo.getPrimaryColour().toWebHexString()+";'>"+tattoo.getPrimaryColour().getName()+"</span>");
					}
					tattooSB.append(".");
				}
				if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
					tattooSB.append(" Там вытатуированы слова: '"+tattoo.getFormattedWritingOutput()+"'");
				}
				if(tattoo.getCounter()!=null && tattoo.getCounter().getType()!=TattooCounterType.NONE) {
					String counterName = tattoo.getCounter().getType().getName();
					if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
						tattooSB.append(", и зачарована, чтобы сохранять ");
					} else {
						tattooSB.append(" Татуировка зачарована, чтобы сохранять ");
					}
					tattooSB.append(UtilText.generateSingularDeterminer(counterName)+" '"+counterName+"' количество, которое гласит: '"+tattoo.getFormattedCounterOutput(owner)+"'.");
				} else if(tattoo.getWriting()!=null && tattoo.getWriting().getText()!=null && !tattoo.getWriting().getText().isEmpty()) {
					tattooSB.append(".");
				}
			}
		}
		if(tattooSB.length()>0) {
			sb.append(getHeader("Татуировки"));
			sb.append(tattooSB.toString());
		}
		
		if(!owner.isPlayer()) {
			sb.append(getSexDetails(owner));
			
			sb.append(getPregnancyDetails(owner));

			sb.append(getIncubationPregnancyDetails(owner));
		}

		return UtilText.parse(owner, sb.toString());
	}

	private void addRaceWeight(Map<AbstractRace, Integer> raceWeightMap, AbstractRace race, int weight) {
		if(race!=null && race!=Race.NONE) {
			raceWeightMap.putIfAbsent(race, 0);
			raceWeightMap.put(race, raceWeightMap.get(race)+weight);
		}
	}

	
	/** To be called after every transformation. Returns the body's race. */
	public void calculateRace(GameCharacter target) {

		// Every time race is calculated, it's because parts have changed, so reset the body parts list:
		handleAllBodyPartsList();
		
//		if(target!=null) {
//			target.removeStatusEffect(StatusEffect.SUBSPECIES_BONUS);
//		}
		
		AbstractRace race = Race.HUMAN;
		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
			race = Race.SLIME;
			this.raceStage = RaceStage.GREATER;
			
		} else if(this.getBodyMaterial()==BodyMaterial.SILICONE) {
			race = Race.DOLL;
			this.raceStage = RaceStage.GREATER;
			
		} else if(target!=null && target.isElemental()) {
			race = Race.ELEMENTAL;
			this.raceStage = RaceStage.GREATER;
			
		} else {
			race = getRaceFromPartWeighting();
			this.raceStage = getRaceStageFromPartWeighting();
		}
		
		subspecies = AbstractSubspecies.getSubspeciesFromBody(this, race);
//		boolean overrideSubspecies = false;

		halfDemonSubspecies = null; // reset so it will be recalculated when accessed

		if(subspecies.getSubspeciesOverridePriority()>0 && (this.getSubspeciesOverride()==null || subspecies.getSubspeciesOverridePriority()>=this.getSubspeciesOverride().getSubspeciesOverridePriority())) {
			this.setSubspeciesOverride(subspecies);
		}
		
//		switch(subspecies) {
//			case IMP:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.IMP_ALPHA
//						&& this.getSubspeciesOverride()!=Subspecies.HALF_DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case IMP_ALPHA:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.HALF_DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case HALF_DEMON:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.DEMON
//						&& this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case DEMON:
//				overrideSubspecies = this.getSubspeciesOverride()!=Subspecies.LILIN
//						&& this.getSubspeciesOverride()!=Subspecies.ELDER_LILIN;
//				break;
//			case ELDER_LILIN:
//			case LILIN:
//				overrideSubspecies = true;
//				break;
//			default:
//				break;
//		}
//		
//		if(overrideSubspecies) {
//			this.setSubspeciesOverride(subspecies);
//		}
		
//		if(target!=null && target.getBody()!=null && Main.game.isStarted()) { // Apparently this is needed to stop Lyssieth from losing her status effect???
//			target.addStatusEffect(StatusEffect.SUBSPECIES_BONUS, -1);
//		}
	}

	public AbstractRace getRaceFromPartWeighting() {
		return getRaceFromPartWeighting(false);
	}
	
	public AbstractRace getRaceFromPartWeighting(boolean ignoreOverride) {
		AbstractRace race = Race.HUMAN;
		
		raceWeightMap.clear();
		
		addRaceWeight(raceWeightMap, torso.getType().getRace(), 4);
		addRaceWeight(raceWeightMap, face.getType().getRace(), 4);
		
		addRaceWeight(raceWeightMap, arm.getType().getRace(), 3);
		addRaceWeight(raceWeightMap, leg.getType().getRace(), 3);

		addRaceWeight(raceWeightMap, antenna.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, eye.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, ear.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, hair.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, tail.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, wing.getType().getRace(), 1);
		addRaceWeight(raceWeightMap, horn.getType().getRace(), 1);
		
		// Best to leave this out...
		// Breast, ass, penis, and vagina have very low weighting so that the more visible parts of a character's body are counted more towards their subspecies
//		addRaceWeight(raceWeightMap, breast.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, breastCrotch.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, ass.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, penis.getType().getRace(), 1);
//		addRaceWeight(raceWeightMap, vagina.getType().getRace(), 1);
		
		raceWeightMap.remove(Race.NONE);
		
		int max = 0;
		boolean demonPartFound = false;
		
		for(Entry<AbstractRace, Integer> e : raceWeightMap.entrySet()) {
			if(e.getKey()!=null && e.getKey()==Race.DEMON) {
				demonPartFound = true;
				
			} else if(e.getKey()!=null && e.getKey()!=Race.HUMAN && e.getValue()>max) {
				race = e.getKey();
				max = e.getValue();
			}
		}
		if(!ignoreOverride && demonPartFound) { // Just one demon part is enough to make any character a demon:
			return Race.DEMON;
		}
		
		return race;
	}
	
	public RaceStage getRaceStageFromPartWeighting() {
		if(this.isFeral()) {
			return RaceStage.FERAL;
		}
		if(raceWeightMap.containsKey(Race.DEMON) && !raceWeightMap.containsKey(Race.HUMAN) && raceWeightMap.size()==2) {
			return RaceStage.GREATER;
		}
		if(raceWeightMap.size()==1) {
			if(raceWeightMap.containsKey(Race.HUMAN)) {
				return RaceStage.HUMAN;
			} else {
				return RaceStage.GREATER;
			}
			
		} else {
			return RaceStage.LESSER;
		}
	}

	public Map<AbstractRace, Integer> getRaceWeightMap() {
		return raceWeightMap;
	}
	
	public AbstractRace getRace() {
		if(subspecies == null) {
			calculateRace(null);
		}
		return subspecies.getRace();
	}

	/**
	 * @return This body's true race. If this body does not have a subspecies override, this will be the same as getRace(). If they do have an override, however, it will return the race of that override.
	 */
	public AbstractRace getTrueRace() {
		if(this.getSubspeciesOverride()!=null) {
			return this.getSubspeciesOverride().getRace();
		}
		return getRace();
	}
	
	public AbstractSubspecies getSubspecies() {
		return subspecies;
	}
	
	public AbstractSubspecies getLoadedSubspecies() {
		if(loadedSubspecies==null) {
			return getSubspecies();
		}
		return loadedSubspecies;
	}

	/**
	 * @return This body's true subspecies. If this body does not have a subspecies override, this will be the same as getSubspecies(). If they do have an override, however, it will return that override.
	 */
	public AbstractSubspecies getTrueSubspecies() {
		if(this.getSubspeciesOverride()!=null) {
			return this.getSubspeciesOverride();
		}
		return getSubspecies();
	}
	
	public RaceStage getRaceStage() {
		return raceStage;
	}

	public AbstractSubspecies getSubspeciesOverride() {
		return subspeciesOverride;
	}

	public void setSubspeciesOverride(AbstractSubspecies subspeciesOverride) {
		this.subspeciesOverride = subspeciesOverride;
	}

	public AbstractSubspecies getHalfDemonSubspecies() {
		if (halfDemonSubspecies == null) {
			halfDemonSubspecies = AbstractSubspecies.getSubspeciesFromBody(this, getRaceFromPartWeighting(true));
		}
		return halfDemonSubspecies;
	}
	
	public boolean isDoll() {
		return this.getRace()==Race.DOLL;
	}

	public Antenna getAntenna() {
		return antenna;
	}

	public Arm getArm() {
		return arm;
	}

	public AbstractArmType getArmType() {
		return arm.getType();
	}

	public Ass getAss() {
		return ass;
	}

	public AbstractAssType getAssType() {
		return ass.getType();
	}
	
	public Breast getBreast() {
		return breast;
	}

	public AbstractBreastType getBreastType() {
		return breast.getType();
	}
	
	public boolean hasBreasts() {
		return getBreastType()!=BreastType.NONE;
	}
	
	public BreastCrotch getBreastCrotch() {
		return breastCrotch;
	}

	public AbstractBreastType getBreastCrotchType() {
		return breastCrotch.getType();
	}
	
	public boolean hasBreastsCrotch() {
		return getBreastCrotchType()!=BreastType.NONE;
	}
	
	public Face getFace() {
		return face;
	}

	public AbstractFaceType getFaceType() {
		return face.getType();
	}
	
	public Eye getEye() {
		return eye;
	}

	public AbstractEyeType getEyeType() {
		return eye.getType();
	}

	public Ear getEar() {
		return ear;
	}

	public AbstractEarType getEarType() {
		return ear.getType();
	}

	public Hair getHair() {
		return hair;
	}

	public AbstractHairType getHairType() {
		return hair.getType();
	}

	public Horn getHorn() {
		return horn;
	}

	public AbstractHornType getHornType() {
		return horn.getType();
	}

	public boolean hasGenericHorns() {
		return getHorn().getType().isGeneric();
	}
	
	public List<AbstractHornType> getAvailableHornTypes(boolean retainNone) {
		return HornType.getHornTypes(this.getRace(), retainNone);
	}
	
	public Leg getLeg() {
		return leg;
	}

	public AbstractLegType getLegType() {
		return leg.getType();
	}

	public LegConfiguration getLegConfiguration() {
		return leg.getLegConfiguration();
	}

	public boolean isTaur() {
		return !getLegConfiguration().isBipedalPositionedGenitals();
	}
	
	public Penis getPenis() {
		return penis;
	}

	public AbstractPenisType getPenisType() {
		return penis.getType();
	}

	public boolean hasPenis() {
		return penis.getType() != PenisType.NONE;
	}

	public boolean hasPenisIgnoreDildo() {
		return hasPenis() && penis.getType() != PenisType.DILDO;
	}

	public boolean hasPenisIgnoresDildo() {
		return hasPenisIgnoreDildo();
	}
	
	public OrificeSpinneret getSpinneret() {
		return spinneret;
	}
	
	public boolean hasTailSpinneret() {
		return getTailType().hasSpinneret();
	}
	
	public boolean hasLegSpinneret() {
		return getLegConfiguration()==LegConfiguration.ARACHNID && getLegType().hasSpinneret();
	}
	
	public boolean hasSpinneret() {
		return hasTailSpinneret() || hasLegSpinneret();
	}

	public Torso getTorso() {
		return torso;
	}

	public AbstractTorsoType getTorsoType() {
		return torso.getType();
	}

	public Tail getTail() {
		return tail;
	}

	public AbstractTailType getTailType() {
		return tail.getType();
	}

	public Tentacle getTentacle() {
		return tentacle;
	}

	public AbstractTongueType getTongueType() {
		return face.getTongue().getType();
	}

	public Vagina getVagina() {
		return vagina;
	}

	public AbstractVaginaType getVaginaType() {
		return vagina.getType();
	}

	public boolean hasVagina() {
		return vagina.getType() != VaginaType.NONE;
	}

	public boolean hasVaginaIgnoreOnahole() {
		return hasVagina() && vagina.getType() != VaginaType.ONAHOLE;
	}
	
	public Wing getWing() {
		return wing;
	}

	public int getWingSizeValue() {
		return wing.getSizeValue();
	}

	public int getMinimumWingSizeValueForFlight() {
		return leg.getLegConfiguration().getMinimumWingSizeForFlight(this).getValue();
	}

	public AbstractWingType getWingType() {
		return wing.getType();
	}

	public List<AbstractWingType> getAvailableWingTypes() {
		return WingType.getWingTypes(this.getRace());
	}
	
	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}

	public void setArm(Arm arm) {
		this.arm = arm;
	}

	public String setArmType(AbstractArmType type) {
		return this.arm.setType(null, type);
	}

	public String setArmType(GameCharacter owner, AbstractArmType type) {
		return this.arm.setType(owner, type);
	}

	public String setArmRows(int armRows) {
		return this.arm.setArmRows(null, armRows);
	}

	public String setArmRows(GameCharacter owner, int armRows) {
		return this.arm.setArmRows(owner, armRows);
	}

	public void setAss(Ass ass) {
		this.ass = ass;
	}

	public String setAssType(GameCharacter owner, AbstractAssType type) {
		return this.ass.setType(owner, type);
	}
	
	public void setBreast(Breast breast) {
		this.breast = breast;
	}

	public String setBreastType(GameCharacter owner, AbstractBreastType type) {
		return this.breast.setType(owner, type);
	}

	public void setBreastCrotch(BreastCrotch breastCrotch) {
		this.breastCrotch = breastCrotch;
	}

	public String setBreastCrotchType(GameCharacter owner, AbstractBreastType type) {
		return this.breastCrotch.setType(owner, type);
	}

	public void setFace(Face face) {
		this.face = face;
	}

	public String setFaceType(AbstractFaceType type) {
		return face.setType(null, type);
	}

	public String setFaceType(GameCharacter owner, AbstractFaceType type) {
		return face.setType(owner, type);
	}

	public void setEye(Eye eye) {
		this.eye = eye;
	}

	public String setEyeType(GameCharacter owner, AbstractEyeType type) {
		return this.eye.setType(owner, type);
	}

	public void setEar(Ear ear) {
		this.ear = ear;
	}

	public String setEarType(AbstractEarType type) {
		return this.ear.setType(null, type);
	}

	public String setEarType(GameCharacter owner, AbstractEarType type) {
		return this.ear.setType(owner, type);
	}

	public void setHair(Hair hair) {
		this.hair = hair;
	}

	public String setHairType(AbstractHairType type) {
		return this.hair.setType(null, type);
	}

	public String setHairType(GameCharacter owner, AbstractHairType type) {
		return this.hair.setType(owner, type);
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}

	public String setLegType(AbstractLegType type) {
		return this.leg.setType(null, type);
	}

	public String setLegType(GameCharacter owner, AbstractLegType type) {
		return this.leg.setType(owner, type);
	}

	public void setLegConfigurationForced(AbstractLegType type, LegConfiguration legConfiguration) {
		this.leg.setLegConfigurationForced(type, legConfiguration);
	}

	public void setTongueType(AbstractTongueType type) {
		this.face.getTongue().setType(type);
	}

	public void setTorso(Torso torso) {
		this.torso = torso;
	}
	
	public String setTorsoType(AbstractTorsoType type) {
		return this.torso.setType(null, type);
	}

	public String setTorsoType(GameCharacter owner, AbstractTorsoType type) {
		return this.torso.setType(owner, type);
	}

	public void setHorn(Horn horn) {
		this.horn = horn;
	}

	public String setHornType(AbstractHornType type) {
		return this.horn.setType(null, type);
	}

	public String setHornType(GameCharacter owner, AbstractHornType type) {
		return this.horn.setType(owner, type);
	}

	public void setPenis(Penis penis) {
		this.penis = penis;
	}

	public String setPenisType(AbstractPenisType type) {
		return this.penis.setType(null, type);
	}

	public String setPenisType(GameCharacter owner, AbstractPenisType type) {
		return this.penis.setType(owner, type);
	}

	public String addPenisModifier(PenetrationModifier modifier) {
		return this.penis.addPenisModifier(null, modifier);
	}

	public String addPenisModifier(GameCharacter owner, PenetrationModifier modifier) {
		return this.penis.addPenisModifier(owner, modifier);
	}

	public void setTail(Tail tail) {
		this.tail = tail;
	}

	public String setTailType(AbstractTailType type) {
		return this.tail.setType(null, type);
	}

	public String setTailType(GameCharacter owner, AbstractTailType type) {
		return this.tail.setType(owner, type);
	}

	public void setTentacle(Tentacle tentacle) {
		this.tentacle = tentacle;
	}

	public void setVagina(Vagina vagina) {
		this.vagina = vagina;
	}

	public String setVaginaType(AbstractVaginaType type) {
		return this.vagina.setType(null, type);
	}

	public String setVaginaType(GameCharacter owner, AbstractVaginaType type) {
		return this.vagina.setType(owner, type);
	}

	public void setWing(Wing wing) {
		this.wing = wing;
	}

	public String setWingSize(int size) {
		return this.wing.setSize(null, size);
	}

	public String setWingSize(GameCharacter owner, int size) {
		return this.wing.setSize(owner, size);
	}

	public String setWingSizeToMinimumWingSizeForFlight() {
		return wing.setSize(null, getMinimumWingSizeValueForFlight());
	}

	public String setWingSizeToMinimumWingSizeForFlight(GameCharacter owner) {
		return wing.setSize(owner, getMinimumWingSizeValueForFlight());
	}

	public String setWingType(AbstractWingType type) {
		return this.wing.setType(null, type);
	}

	public String setWingType(GameCharacter owner, AbstractWingType type) {
		return this.wing.setType(owner, type);
	}

	public void applyLegConfigurationTransformation(AbstractLegType legType, LegConfiguration legConfiguration, boolean applyFullEffects) {
		this.setLegType(legType);
		this.leg.getType().applyLegConfigurationTransformation(this, legConfiguration, applyFullEffects);
	}

	public Boolean hasTongueModifier(TongueModifier modifier) {
		return face.getTongue().hasTongueModifier(modifier);
	}

	public String addTongueModifier(TongueModifier modifier) {
		return addTongueModifier(null, modifier);
	}

	public String addTongueModifier(GameCharacter owner, TongueModifier modifier) {
		return face.getTongue().addTongueModifier(owner, modifier);
	}

	public void resetTongueModifiers() {
		face.getTongue().resetTongueModifiers();
	}

	public boolean hasWings() {
		return getWingType() != WingType.NONE;
	}
	
	public boolean hasGenericWings() {
		return getWingType().isGeneric();
	}

	public boolean isFaceHuman() {
		return face.getType().getRace() == Race.HUMAN;
	}

	// Descriptions:
	private StringBuilder descriptionSB;

	public String getEyeDescription(GameCharacter owner) {
		StringBuilder sb = new StringBuilder();
		
		if(owner.isFeral()) {
			sb.append(" [npc.SheHasFull] имеет [npc.eyePairs] [npc.eyeRace] глаза с [npc.irisShape] [npc.irisColour(true)] радужками, [npc.pupilShape] [npc.pupilColour(true)] зрачками и [npc.scleraColour(true)] белками.");
		} else {
			sb.append(" "+eye.getType().getBodyDescription(owner));
		}
		
		// Eye makeup:
		if(owner.getEyeLiner().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append(" Вокруг [npc.her] [npc.eyes], у [npc.sheHas] появился слой "+owner.getEyeLiner().getColourDescriptor(owner, true, false)+" подводки для глаз.");
		}
		if(owner.getEyeShadow().getPrimaryColour()!=PresetColour.COVERING_NONE) {
			sb.append(" [npc.SheIs] наносит умеренное количество "+owner.getEyeShadow().getFullDescription(owner, true)+".");
		}
		
		return sb.toString();
	}
	
	/**
	 * @param owner The person whose ass is to be described.
	 * @param locationSpecific Whether this description is specific to looking at the person's ass. If they have a cloaca, and you pass in true, it will say something along the lines of "there's no asshole here".
	 * @return A description of this character's asshole.
	 */
	public String getAssDescription(GameCharacter owner, boolean locationSpecific) {
		descriptionSB = new StringBuilder();
		
		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				if(locationSpecific) {
					return UtilText.parse(owner, "[style.italicsFeral(анус [npc.Her] расположен не между ягодицами [npc.her], а находится внутри передней клоаки [npc.her]!)]");
				} else {
					descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral(анус [npc.Her] расположен внутри передней щелевидной клоаки [npc.her].)] "));
				}
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral(анус [npc.Her] расположен внутри задней щелевидной клоаки [npc.her].)] "));
				break;
			case NORMAL:
			break;
		}
		
		descriptionSB.append(ass.getType().getBodyDescription(owner).trim());
		
		// Colour:
		if(ass.getAnus().isBleached()) {
			descriptionSB.append(", края которой были отбелены так, чтобы ободок не был темнее, чем окружающая [npc.assSkin].");
		} else {
			descriptionSB.append(", при этом ободок чуть темнее, чем окружающая [npc.assSkin].");
		}
		
		if(owner.isFeral()) {
			descriptionSB.append(" [style.colourFeral(Как и ожидалось, она имеет полностью звериную форму и ничем не отличается от обычной [npc.assRace].)]");
		} else if(ass.isFeral(owner)) {
			descriptionSB.append(" [style.colourFeral(Будучи частью звериного низа [npc.her], она имеет полностью звериную форму и ничем не отличается от звериной [npc.assRace].)]");
		}

		descriptionSB.append(" Оно "+Capacity.getCapacityFromValue(ass.getAnus().getOrificeAnus().getStretchedCapacity()).getDescriptor(true)+", а при смазывании может с комфортом вмещать объекты размером до [style.colourSex("+ Units.size(Capacity.getMaximumComfortableDiameter(ass.getAnus().getOrificeAnus().getElasticity(), ass.getAnus().getOrificeAnus().getRawCapacityValue(), true)) + ")] в диаметре.");

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getAssDepth()) {
				default:
					descriptionSB.append(" [npc.Her] зад — это <span style='color:"+owner.getAssDepth().getColour().toWebHexString()+";'>[npc.assDepth]</span>,");
					break;
				case TWO_AVERAGE:
					descriptionSB.append(" [npc.Her] зад как у <span style='color:"+owner.getAssDepth().getColour().toWebHexString()+";'>среднюю глубину</span>,");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"[npc.she] можно [style.colourMinorGood(комфортно)] растягивать для размещения объектов максимальной длины [style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthUncomfortable())+")].");
				} else {
					descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthComfortable())+")] проникающего объекта, и [style.colourMinorBad(с дискомфортом)] принимать [style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthUncomfortable())+")].");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", в сочетании с тем фактом, что тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorGood(комфортно)] растягивать, чтобы вмещать объекты [style.colourSex(любой длины)].");
				} else {
					descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getAssMaximumPenetrationDepthComfortable())+")] проникающего объекта, а так как тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorBad(с дискомфортом)] растягивать для размещения объектов [style.colourSex(любой длины)].");
				}
			}
		}
		
		if (ass.getAnus().getOrificeAnus().isVirgin()) {
			descriptionSB.append(" [style.colourExcellent([npc.Name] [npc.has] сохранила [npc.her] анальную девственность.)]");
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pt))!=null) {
						descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pt)) + "</span>");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] потеряла [npc.her] анальную девственность.</span>");
			}
		}
		
		// Ass wetness:
		switch (ass.getAnus().getOrificeAnus().getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append(" Оно [style.colourWetness(совершенно сухое)] и потребует смазки перед сексом.");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append(" Оно [style.colourWetness(слегка влажное)], но все же потребует смазки перед сексом.");
				break;
			case TWO_MOIST:
				descriptionSB.append(" Оно [style.colourWetness(влажное)], но все же потребует смазки перед сексом.");
				break;
			case THREE_WET:
				descriptionSB.append(" Его [style.colourWetness(поверхность постоянно покрыта каплями влаги)], что обеспечивает достаточно естественной смазки для секса.");
				break;
			case FOUR_SLIMY:
				descriptionSB.append(" Его поверхность всегда [style.colourWetness(влажная)] и готова к проникновению.");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append(" Его поверхность всегда [style.colourWetness(влажная и склизкая)], представляя собой отлично смазанное отверстие для проникновения.");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append(" Его поверхность всегда [style.colourWetness(совершенно мокрая)] от естественной смазки, представляя собой отлично смазанное отверстие для проникновения.");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append(" Оно постоянно [style.colourWetness(сочится)] естественной смазкой, а его [style.colourWetness(промокшее насквозь)] вход всегда готов к проникновению.");
				break;
		}
		// Ass elasticity & plasticity:
		switch (ass.getAnus().getOrificeAnus().getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно тугое)],");
				break;
			case ONE_RIGID:
				descriptionSB.append(" Его [style.colourElasticity(требуется растягивать с огромным трудом)],");
				break;
			case TWO_FIRM:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается не очень легко)],");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" Оно [style.colourElasticity(неохотно растягивается)], когда используется как сексуальное отверстие,");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" Оно [style.colourElasticity(довольно устойчиво к растяжению)],");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается довольно легко)],");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается очень легко)],");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно эластичное)],");
				break;
		}
		descriptionSB.append(" и после использования оно "+ass.getAnus().getOrificeAnus().getPlasticity().getDescription()+".");
		
		if(Main.game.isAssHairEnabled()) {
			switch(ass.getAnus().getAssHair()) {
				case ZERO_NONE:
					descriptionSB.append(" Нет и следа от "+owner.getAssHairType().getName(owner)+" вокруг ануса [npc.her].");
					break;
				case ONE_STUBBLE:
					descriptionSB.append(" [npc.She] [npc.has] несколько волосков "+owner.getAssHairType().getFullDescription(owner, true)+" вокруг ануса [npc.her].");
					break;
				case TWO_MANICURED:
					descriptionSB.append(" [npc.She] [npc.has] совсем немного "+owner.getAssHairType().getFullDescription(owner, true)+" вокруг ануса [npc.her].");
					break;
				case THREE_TRIMMED:
					descriptionSB.append(" [npc.She] [npc.has] небольшое количество "+owner.getAssHairType().getFullDescription(owner, true)+" вокруг ануса [npc.her].");
					break;
				case FOUR_NATURAL:
					descriptionSB.append(" [npc.She] [npc.has] естественное количество "+owner.getAssHairType().getFullDescription(owner, true)+" вокруг ануса [npc.her].");
					break;
				case FIVE_UNKEMPT:
					descriptionSB.append(" [npc.She] [npc.has] неопрятная масса "+owner.getAssHairType().getFullDescription(owner, true)+" вокруг ануса [npc.her].");
					break;
				case SIX_BUSHY:
					descriptionSB.append(" [npc.She] [npc.has] густая, пушистая масса "+owner.getAssHairType().getFullDescription(owner, true)+" вокруг ануса [npc.her].");
					break;
				case SEVEN_WILD:
					descriptionSB.append(" [npc.She] [npc.has] дикая, пушистая масса "+owner.getAssHairType().getFullDescription(owner, true)+" вокруг ануса [npc.her].");
					break;
			}
		}
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasAssOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append(" [npc.She] [npc.has] ряд внутренних мышц, выстилающих внутреннюю часть [npc.her] [npc.asshole], что позволяет [npc.herHim] умело сжимать и обхватывать любой проникший внутрь объект.");
						break;
					case PUFFY:
						descriptionSB.append(" Край [npc.her] [npc.asshole] разбух и превратился в пухлое кольцо, похожее на пончик.");
						break;
					case RIBBED:
						descriptionSB.append(" Внутренняя часть [npc.her] [npc.asshole] выстлана чувствительными мясистыми складками, которые дарят [npc.herHim] дополнительное наслаждение при стимуляции.");
						break;
					case TENTACLED:
						descriptionSB.append(" [npc.Her] [npc.asshole] усеян крошечными щупальцами, которые извиваются и шевелятся сами по себе.");
						break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getBreastDescription(GameCharacter owner) {
		return getBreastDescription(owner, breast);
	}
	
	public String getBreastDescription(GameCharacter owner, Breast viewedBreast) {
		descriptionSB = new StringBuilder();
		
		boolean playerKnowledgeOfBreasts = owner.isAreaKnownByCharacter(CoverableArea.NIPPLES, Main.game.getPlayer());
		
		if(!owner.isPlayer() && !playerKnowledgeOfBreasts) {
			descriptionSB.append("[style.colourDisabled(Вы никогда не видели [npc.her] с обнаженной грудью, поэтому не знаете, как выглядят [npc.her] соски.)]");
			
		} else {
			descriptionSB.append("На каждой из [npc.her] "+(owner.hasBreasts()?owner.getBreastShape().getDescriptor()+" груди":"грудные мышцы")+", [npc.she] [npc.has] "+Util.intToString(owner.getNippleCountPerBreast())+" [npc.nippleSize], ");
			
			switch(owner.getNippleShape()) {
				case NORMAL:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)]");
					break;
				case INVERTED:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)], инвертированные");
					break;
				case LIPS:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)]-губы");
					break;
				case VAGINA:
					descriptionSB.append(" [npc.nipplePrimaryColour(true)]-губы");
					break;
			}
			if(owner.getNippleCountPerBreast()>1) {
				descriptionSB.append(" [npc.nipples],");
			} else {
				descriptionSB.append(" [npc.nipple(true)],");
			}
			
			switch(owner.getAreolaeShape()) {
				case NORMAL:
					descriptionSB.append(" с [npc.areolaeSize], круглыми ареолами.");
					break;
				case HEART:
					descriptionSB.append(" с [npc.areolaeSize], ареолами в форме сердца.");
					break;
				case STAR:
					descriptionSB.append(" с [npc.areolaeSize], ареолами в форме звезды.");
					break;
			}

			if(breast.isFeral(owner)) {
				descriptionSB.append(" [style.colourFeral(Из-за того, что всё тело [npc.her] имеет дикую форму, [npc.her] [npc.nipples] идентичны таковым у дикого [npc.breastRace].)]");
			}
			
			if(owner.isPiercedNipple()) {
				descriptionSB.append(" Они проколоты.");
			}
			
			if(owner.getNippleCapacity() != Capacity.ZERO_IMPENETRABLE && Main.game.isNipplePenEnabled()) {
				if (viewedBreast.isFuckable()) {
					descriptionSB.append("<br/>[npc.Her] [npc.breasts] имеют внутренние [npc.nippleSecondaryColour(true)] каналы, позволяющие [npc.her] [npc.breastCapacity] [npc.nipples] с комфортом проникать объектами [style.colourSex(\""+ Units.size(Capacity.getCapacityFromValue(viewedBreast.getNipples().getOrificeNipples().getStretchedCapacity()).getMaximumValue(true)) + ")] в диаметре при смазывании.");

					if(Main.game.isPenetrationLimitationsEnabled()) {
						switch(owner.getNippleDepth()) {
							default:
								descriptionSB.append(" [npc.Her] доступные для траха [npc.nipples] составляют <span style='color:"+owner.getNippleDepth().getColour().toWebHexString()+";'>[npc.breastDepth]</span>,");
								break;
							case TWO_AVERAGE:
								descriptionSB.append(" [npc.Her] доступные для траха [npc.nipples] имеют <span style='color:"+owner.getNippleDepth().getColour().toWebHexString()+";'>среднюю глубину</span>,");
								break;
						}
						if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"[npc.she] можно [style.colourMinorGood(комфортно)] растягивать для размещения объектов максимальной длины [style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthUncomfortable())+")].");
							} else {
								descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthComfortable())+")] проникающего объекта, и [style.colourMinorBad(с дискомфортом)] принимать [style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthUncomfortable())+")].");
							}
							
						} else {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", в сочетании с тем фактом, что тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorGood(комфортно)] растягивать, чтобы вмещать объекты [style.colourSex(любой длины)].");
							} else {
								descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getNippleMaximumPenetrationDepthComfortable())+")] проникающего объекта, а так как тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorBad(с дискомфортом)] растягивать для размещения объектов [style.colourSex(любой длины)].");
							}
						}
					}
					
				} else {
					descriptionSB.append("<br/>[npc.Her] [npc.breasts] имеют внутренние, [npc.nippleSecondaryColour(вторичного цвета соски)] каналы, но [style.colourBad(им нужно быть как минимум C-чашки)] перед [npc.her] "
							+ Capacity.getCapacityFromValue(viewedBreast.getNipples().getOrificeNipples().getStretchedCapacity()).getDescriptor(true)+" [npc.nipples] можно было проникать.");
				}

				// Nipple elasticity & plasticity:
				switch (viewedBreast.getNipples().getOrificeNipples().getElasticity()) {
					case ZERO_UNYIELDING:
						descriptionSB.append(" Они [style.colourElasticity(чрезвычайно неподатливы)],");
						break;
					case ONE_RIGID:
						descriptionSB.append(" Это [style.colourElasticity(требует огромных усилий, чтобы растянуть их)],");
						break;
					case TWO_FIRM:
						descriptionSB.append(" Они [style.colourElasticity(растягиваются не очень легко)],");
						break;
					case THREE_FLEXIBLE:
						descriptionSB.append(" Они [style.colourElasticity(неохотно растягиваются)], когда используются как сексуальное отверстие,");
						break;
					case FOUR_LIMBER:
						descriptionSB.append(" Они [style.colourElasticity(довольно устойчивы к растягиванию)],");
						break;
					case FIVE_STRETCHY:
						descriptionSB.append(" Они [style.colourElasticity(довольно легко растягиваются)],");
						break;
					case SIX_SUPPLE:
						descriptionSB.append(" Они [style.colourElasticity(очень легко растягиваются)],");
						break;
					case SEVEN_ELASTIC:
						descriptionSB.append(" Они [style.colourElasticity(чрезвычайно эластичны)],");
						break;
				}
				descriptionSB.append(" и после использования они "+viewedBreast.getNipples().getOrificeNipples().getPlasticity().getDescriptionPlural()+".");
				
				for(OrificeModifier om : OrificeModifier.values()) {
					if(owner.hasNippleOrificeModifier(om)) {
						switch(om) {
							case MUSCLE_CONTROL:
								descriptionSB.append(" [npc.She] [npc.has] ряд мышц, выстилающих внутреннюю часть [npc.her] [npc.nipples], что позволяет [npc.herHim] мастерски сжимать и обхватывать любой проникший внутрь объект.");
								break;
							case PUFFY:
								descriptionSB.append(" [npc.Her] [npc.nipples] раздулись, став исключительно пухлыми и пышными.");
								break;
							case RIBBED:
								descriptionSB.append(" Внутренняя часть [npc.her] [npc.nipples] покрыта чувствительными мясными складками, которые дарят [npc.herHim] дополнительное удовольствие при стимуляции.");
								break;
							case TENTACLED:
								descriptionSB.append(" [npc.Her] [npc.nipples] наполнены крошечными щупальцами, которые извиваются и шевелятся сами по себе.");
								break;
						}
					}
				}
				
				if (!viewedBreast.getNipples().getOrificeNipples().isVirgin()) {
					boolean virginityLossFound = false;
					for(SexAreaPenetration pt : SexAreaPenetration.values()) {
						if(pt.isTakesVirginity()) {
							if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, pt))!=null) {
								descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, pt)) + "</span>");
								virginityLossFound = true;
								break;
							}
						}
					}
					if(!virginityLossFound) {
						descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] лишилась [npc.her] девственности соска.</span>");
					}
					
				} else {
					descriptionSB.append(" [style.colourExcellent([npc.Name] [npc.has] сохранили девственность сосков [npc.her].)]");
				}
				
			} else {
				if(owner.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
					descriptionSB.append(" [npc.Her] [npc.nipples] раздулись, став исключительно пухлыми и пышными.");
				}	
			}
			
			if (viewedBreast.getRawMilkStorageValue() > 0) {
				descriptionSB.append("<br/>[npc.SheIsFull] сейчас вырабатывает "+ Units.fluid(viewedBreast.getRawMilkStorageValue(), Units.UnitType.LONG) + " из [npc.milkPrimaryColour(true)] [npc.milk] ("+ Units.fluid(viewedBreast.getRawStoredMilkValue()) + " сейчас хранится) со скоростью [npc.a_milkRegen].");
				
				switch(viewedBreast.getMilk().getFlavour()) {
					case MILK:
						descriptionSB.append(" [npc.Her] [npc.milkColour(true)] [npc.milk] на вкус как обычное молоко.");
						break;
					case BUBBLEGUM:
						descriptionSB.append(" [npc.Her] [npc.milkColour(true)] [npc.milk] имеет фруктовый вкус жвачки.");
						break;
					case FLAVOURLESS:
						descriptionSB.append(" [npc.Her] [npc.milkColour(true)] [npc.milk] вообще не имеет никакого вкуса.");
						break;
					default:
						descriptionSB.append(" [npc.Her] [npc.milkColour(true)] [npc.milk] на вкус точь-в-точь как "+viewedBreast.getMilk().getFlavour().getName()+".");
						break;
				}
				
				for(FluidModifier fm : FluidModifier.values()) {
					if(owner.hasMilkModifier(fm)) {
						descriptionSB.append(" " + fm.getBriefDescription());
					}
				}
				
			} else {
				descriptionSB.append("<br/>[npc.SheIsFull] сейчас не вырабатывает молоко.");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getBreastCrotchDescription(GameCharacter owner) {
		return getBreastCrotchDescription(owner, breastCrotch);
	}

	public String getBreastCrotchDescription(GameCharacter owner, BreastCrotch viewedBreastCrotch) {
		descriptionSB = new StringBuilder();
		
//		boolean playerKnowledgeOfUdders = owner.isAreaKnownByCharacter(CoverableArea.NIPPLES_CROTCH, Main.game.getPlayer());
//		
//		if(owner.isPlayer() || playerKnowledgeOfUdders) {
			if(owner.isFeral()) {
				if(owner.getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
					descriptionSB.append("Глубоко под пахом дикого тела [npc.her],");
				} else {
					descriptionSB.append("Чуть выше паха дикого тела [npc.her],");
				}
			} else {
				descriptionSB.append(leg.getLegConfiguration().getCrotchBoobLocationDescription());
			}
			if(breastCrotch.getShape()==BreastShape.UDDERS) {
				if(breastCrotch.getRawSizeValue()>0){
					descriptionSB.append(" [npc.she] [npc.has] [npc.a_crotchBoobsSize]");
				} else {
					descriptionSB.append(" [npc.she] [npc.has] совершенно плоские");
				}
				if(breastCrotch.getRows()>0) {
					descriptionSB.append((breastCrotch.getRows()>1?" пары":" пара")+" вымен.");
					
				} else {
					descriptionSB.append(" вымя,");
				}
				
			} else {
				if(breastCrotch.getRawSizeValue()>0){
					descriptionSB.append(" [npc.she] [npc.has] [npc.crotchBoobsRows] [npc.crotchBoobsSize], [npc.crotchBoobsCups]-чашеобразная [npc.crotchBoobs]");
				} else {
					descriptionSB.append(" [npc.she] [npc.has] [npc.crotchBoobsRows] совершенно плоские [npc.crotchBoobs]");
				}
				if(leg.getLegConfiguration().isBipedalPositionedCrotchBoobs() && breastCrotch.getRows()>1) {
					descriptionSB.append(", при этом верхняя пара"+(breastCrotch.getRows()>2?"s":"")+" расположена выше на [npc.her] животе.");
					
				} else if(breastCrotch.getRows()>1) {
					descriptionSB.append(", при этом дополнительная пара"+(breastCrotch.getRows()>2?"s":"")+" расположена ниже, ближе к [npc.her] брюшку.");
					
				} else {
					descriptionSB.append(".");
				}
			}
			
			if(viewedBreastCrotch.getShape()==BreastShape.UDDERS) {
				if(breastCrotch.getRows()>0) {
					descriptionSB.append(" Они сформированы в виде [npc.totalCrotchBoobs] выступов, на каждом из которых [npc.she] [npc.has] [npc.crotchBoobsNipplesPerBreast] [npc.crotchNippleSize], ");
				} else {
					descriptionSB.append(" на которых [npc.sheHasFull] [npc.crotchBoobsNipplesPerBreast] [npc.crotchNippleSize], ");
				}
			} else {
				descriptionSB.append(" На каждом из [npc.her] [npc.totalCrotchBoobs] [npc.crotchBoobs], [npc.she] [npc.has] [npc.crotchBoobsNipplesPerBreast] [npc.crotchNippleSize], ");
			}
			
			switch(owner.getNippleCrotchShape()) {
				case NORMAL:
					descriptionSB.append(" [npc.crotchNipplePrimaryColour(true)]");
					break;
				case INVERTED:
					descriptionSB.append(" [npc.crotchNipplePrimaryColour(true)], вывернутых");
					break;
				case LIPS:
					descriptionSB.append(" [npc.crotchNipplePrimaryColour(true)]-губых");
					break;
				case VAGINA:
					descriptionSB.append(" [npc.crotchNipplePrimaryColour(true)]-губых");
					break;
			}
			if(owner.getNippleCrotchCountPerBreast()>1) {
				descriptionSB.append(" [npc.crotchNipples],");
			} else {
				descriptionSB.append(" [npc.crotchNipple(true)],");
			}
			
			switch(owner.getAreolaeCrotchShape()) {
				case NORMAL:
					descriptionSB.append(" с [npc.crotchBoobsAreolaSize] круглыми ареолами.");
					break;
				case HEART:
					descriptionSB.append(" с [npc.crotchBoobsAreolaSize] сердцевидными ареолами.");
					break;
				case STAR:
					descriptionSB.append(" с [npc.crotchBoobsAreolaSize], имеющими форму звезд.");
					break;
			}
			if(owner.isFeral()) {
				descriptionSB.append(" [style.colourFeral(Поскольку [npc.sheHasFull] — это полностью дикое тело, [npc.her] [npc.crotchBoobs] по форме идентичны тем, что встречаются у обычного [npc.crotchBoobRace].)]");
			} else if(breastCrotch.isFeral(owner)) {
				descriptionSB.append(" [style.colourFeral(Поскольку [npc.sheHasFull] — это нижняя часть тела животного, [npc.her] [npc.crotchBoobs] по форме идентичны тем, что встречаются у дикого [npc.crotchBoobRace].)]");
			}
			
			if(owner.isPiercedNippleCrotch()) {
				descriptionSB.append(" Они проколоты.");
			}
			
			if(owner.getNippleCrotchCapacity() != Capacity.ZERO_IMPENETRABLE && Main.game.isNipplePenEnabled()) {
				if (viewedBreastCrotch.isFuckable()) {
					descriptionSB.append("<br/>[npc.Her] [npc.crotchBoobs] имеют внутренние, [npc.crotchNippleSecondaryColour(true)] каналы, позволяющие [npc.her] [npc.crotchBoobsCapacity] [npc.crotchNipples] с комфортом проникать объектами [style.colourSex(\","+ Units.size(Capacity.getCapacityFromValue(viewedBreastCrotch.getNipples().getOrificeNipples().getStretchedCapacity()).getMaximumValue(true)) + ")] в диаметре при смазывании.");

					if(Main.game.isPenetrationLimitationsEnabled()) {
						switch(owner.getNippleCrotchDepth()) {
							default:
								descriptionSB.append(" [npc.Her] ебабельные [npc.crotchNipples] являются <span style='color:"+owner.getNippleCrotchDepth().getColour().toWebHexString()+";'>[npc.crotchBreastDepth]</span>,");
								break;
							case TWO_AVERAGE:
								descriptionSB.append(" [npc.Her] ебабельные [npc.crotchNipples] имеют <span style='color:"+owner.getNippleCrotchDepth().getColour().toWebHexString()+";'>среднюю глубину</span>,");
								break;
						}
						if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"[npc.she] можно [style.colourMinorGood(комфортно)] растягивать для размещения объектов максимальной длины [style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthUncomfortable())+")].");
							} else {
								descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthComfortable())+")] проникающего объекта, и [style.colourMinorBad(с дискомфортом)] принимать [style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthUncomfortable())+")].");
							}
							
						} else {
							if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
								descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", в сочетании с тем фактом, что тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorGood(комфортно)] растягивать, чтобы вмещать объекты [style.colourSex(любой длины)].");
							} else {
								descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getNippleCrotchMaximumPenetrationDepthComfortable())+")] проникающего объекта, а так как тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorBad(с дискомфортом)] растягивать для размещения объектов [style.colourSex(любой длины)].");
							}
						}
					}
					
				} else {
					descriptionSB.append("<br/>[npc.Her] [npc.crotchBoobs] имеют внутренние, [npc.crotchNippleSecondaryColour(true)] каналы, но [style.colourBad(им нужно быть как минимум размера C)] перед тем, как [npc.her] "
							+ Capacity.getCapacityFromValue(viewedBreastCrotch.getNipples().getOrificeNipples().getStretchedCapacity()).getDescriptor(true)+ " [npc.crotchNipples] можно было бы проникать.");
				}

				// Nipple elasticity & plasticity:
				switch (viewedBreastCrotch.getNipples().getOrificeNipples().getElasticity()) {
					case ZERO_UNYIELDING:
						descriptionSB.append(" Они [style.colourElasticity(чрезвычайно неподатливы)],");
						break;
					case ONE_RIGID:
						descriptionSB.append(" Это [style.colourElasticity(требует огромных усилий, чтобы растянуть их)],");
						break;
					case TWO_FIRM:
						descriptionSB.append(" Они [style.colourElasticity(растягиваются не очень легко)],");
						break;
					case THREE_FLEXIBLE:
						descriptionSB.append(" Они [style.colourElasticity(неохотно растягиваются)], когда используются как сексуальное отверстие,");
						break;
					case FOUR_LIMBER:
						descriptionSB.append(" Они [style.colourElasticity(довольно устойчивы к растягиванию)],");
						break;
					case FIVE_STRETCHY:
						descriptionSB.append(" Они [style.colourElasticity(довольно легко растягиваются)],");
						break;
					case SIX_SUPPLE:
						descriptionSB.append(" Они [style.colourElasticity(очень легко растягиваются)],");
						break;
					case SEVEN_ELASTIC:
						descriptionSB.append(" Они [style.colourElasticity(чрезвычайно эластичны)],");
						break;
				}
				descriptionSB.append(" и после использования они "+viewedBreastCrotch.getNipples().getOrificeNipples().getPlasticity().getDescriptionPlural()+".");
				
				for(OrificeModifier om : OrificeModifier.values()) {
					if(owner.hasNippleCrotchOrificeModifier(om)) {
						switch(om) {
							case MUSCLE_CONTROL:
								descriptionSB.append(" [npc.She] [npc.has] ряд мышц, выстилающих внутреннюю часть [npc.her] [npc.crotchNipples], что позволяет [npc.herHim] умело сжимать и обхватывать любой входящий объект.");
								break;
							case PUFFY:
								descriptionSB.append(" [npc.Her] [npc.crotchNipples] раздулись, став исключительно пухлыми и пышными.");
								break;
							case RIBBED:
								descriptionSB.append(" Внутренности [npc.her] [npc.crotchNipples] выстланы чувствительными плотскими ребрами, которые дарят [npc.herHim] дополнительное удовольствие при стимуляции.");
								break;
							case TENTACLED:
								descriptionSB.append(" [npc.Her] [npc.crotchNipples] наполнены крошечными щупальцами, которые извиваются и шевелятся сами по себе.");
								break;
						}
					}
				}
				
				if (!viewedBreastCrotch.getNipples().getOrificeNipples().isVirgin()) {
					boolean virginityLossFound = false;
					for(SexAreaPenetration pt : SexAreaPenetration.values()) {
						if(pt.isTakesVirginity()) {
							if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, pt))!=null) {
								descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE_CROTCH, pt)) + "</span>");
								virginityLossFound = true;
								break;
							}
						}
					}
					if(!virginityLossFound) {
						descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] потеряли [npc.her] [npc.crotchNipple] девственность.</span>");
					}
					
				} else {
					descriptionSB.append(" [style.colourExcellent([npc.Name] [npc.has] сохранили [npc.her] [npc.crotchNipple] девственность.)]");
				}
				
			} else {
				if(owner.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
					descriptionSB.append(" [npc.Her] [npc.crotchNipples] раздулись, став исключительно пухлыми и пышными.");
				}	
			}
			
			if (viewedBreastCrotch.getRawMilkStorageValue() > 0) {
				descriptionSB.append("<br/>[npc.SheIsFull] сейчас вырабатывает "
						+ Units.fluid(viewedBreastCrotch.getRawMilkStorageValue(), Units.UnitType.LONG) + " из [npc.crotchMilkPrimaryColour(true)] [npc.crotchMilk] ("
						+ Units.fluid(viewedBreastCrotch.getRawStoredMilkValue(), Units.UnitType.LONG) + " в данный момент хранится) со скоростью [npc.a_crotchMilkRegen].");
				
				switch(viewedBreastCrotch.getMilk().getFlavour()) {
					case MILK:
						descriptionSB.append(" [npc.Her] [npc.crotchMilkColour(true)] [npc.crotchMilk] на вкус как обычное молоко.");
						break;
					case BUBBLEGUM:
						descriptionSB.append(" [npc.Her] [npc.crotchMilkColour(true)] [npc.crotchMilk] имеет фруктовый вкус жвачки.");
						break;
					case FLAVOURLESS:
						descriptionSB.append(" [npc.Her] [npc.crotchMilkColour(true)] [npc.crotchMilk] вообще не имеет никакого вкуса.");
						break;
					default:
						descriptionSB.append(" [npc.Her] [npc.crotchMilkColour(true)] [npc.crotchMilk] на вкус точь-в-точь как "+viewedBreastCrotch.getMilk().getFlavour().getName()+".");
						break;
				}
				
				for(FluidModifier fm : FluidModifier.values()) {
					if(owner.hasMilkCrotchModifier(fm)) {
						descriptionSB.append(" " + fm.getBriefDescription());
					}
				}
				
			} else {
				descriptionSB.append("<br/>[npc.SheIsFull] сейчас не вырабатывает молоко.");
			}
//		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getPenisDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();
		
		Penis viewedPenis = owner.getCurrentPenis();
		
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.CUM)) {
			viewedPenis = new Penis(penis.getType(),
					(int) (penis.getRawLengthValue() * 2.25f),
					false,
					PenetrationGirth.FIVE_THICK.getValue(),
					penis.getTesticle().getTesticleSize().getValue()*2,
					(int) ((penis.getTesticle().getRawCumStorageValue()+100) * 3.25f),
					penis.getTesticle().getTesticleCount());
			descriptionSB.append("<i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>Психоактивная сперма, которую ты недавно проглотила, заставляет твой взгляд на "+(owner.isPlayer()?"твоей":"[npc.namePos]")+" член искажаться!</i> ");
		}

		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] член расположен внутри [npc.her] щелевидной, фронтальной клоаки.)] "));
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] член расположен внутри [npc.her] щелевидной, задней клоаки.)] "));
				break;
			case NORMAL:
			break;
		}
		
		String penisAppearance = owner.getPenisType().getBodyDescription(owner);
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.CUM)) {
			penisAppearance = penisAppearance.replaceAll("\\[npc\\.a_cockGirth\\]", UtilText.generateSingularDeterminer(viewedPenis.getGirth().getName())+" "+viewedPenis.getGirth().getName());
			penisAppearance = penisAppearance.replaceAll("\\[npc\\.cockLengthValue\\]", Units.size(viewedPenis.getRawLengthValue(), Units.UnitType.LONG_SINGULAR));
			descriptionSB.append(penisAppearance);
			descriptionSB.append(" Его длина составляет "+Units.size(viewedPenis.getDiameter(), Units.UnitType.LONG)+" в диаметре ("+Units.size(viewedPenis.getDiameter()* Math.PI, Units.UnitType.LONG)+" в обхвате).");
			
		} else {
			descriptionSB.append(penisAppearance);
			descriptionSB.append(" Его диаметр составляет [npc.penisDiameter(true)] ([npc.penisCircumference(true)] в обхвате).");
		}

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if(owner.isFeral()) {
				descriptionSB.append(" [style.colourFeral(Как и ожидалось, [npc.her] [npc.cock] имеет полностью дикую форму и ничем не отличается от обычного [npc.penisRace].)]");
			} else if(penis.isFeral(owner)) {
				descriptionSB.append(" [style.colourFeral(Так как [npc.her] гениталии расположены на звериной части тела [npc.her], [npc.her] [npc.penis] идентичен по функциям дикому [npc.penisRace].)]");
			}
		}
		
		for(PenetrationModifier pm : PenetrationModifier.getPenetrationModifiers()) {
			if(owner.hasPenisModifier(pm)) {
				switch(pm) {
					case RIBBED:
						descriptionSB.append(" Он выстлан твердыми мясными ребрами, которые наверняка принесут дополнительное удовольствие любому отверстию, в которое они проникнут.");
						break;
					case TENTACLED:
						descriptionSB.append(" Его поверхность покрыта множеством маленьких щупалец, которые извиваются и шевелятся сами по себе.");
						break;
					case BARBED:
						descriptionSB.append(" Вдоль всей его длины тянутся мясистые, направленные назад шипы.");
						break;
					case BLUNT:
						descriptionSB.append(" Головка плавно переходит в гладкую поверхность.");
						break;
					case FLARED:
						descriptionSB.append(" Головка широкая и расширяющаяся.");
						break;
					case KNOTTED:
						descriptionSB.append(" У основания расположено толстое утолщение.");
						break;
					case PREHENSILE:
						descriptionSB.append(" Он цепкий, им можно манипулировать и двигать почти так же, как хвостом примата.");
						break;
					case SHEATHED:
						descriptionSB.append(" Когда он не используется, он втягивается обратно в препуциальную сумку у основания.");
						break;
					case TAPERED:
						descriptionSB.append(" Ствол сужается к головке.");
						break;
					case VEINY:
						descriptionSB.append(" Из его поверхности выступают крупные вены.");
						break;
					case OVIPOSITOR:
						descriptionSB.append(" Его внутреннее строение позволяет использовать его как яйцеклад, что дает возможность [npc.name] откладывать [npc.her] оплодотворенных яиц в отверстия цели.");
						break;
				}
			}
		}

		if(owner.getCurrentPenis().getType()!=PenisType.DILDO) {
			if (!owner.getCurrentPenis().isVirgin()) {
				boolean virginityLossFound = false;
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					if(ot.isInternalOrifice()) {
						if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot))!=null) {
							descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot)) + "</span>");
							virginityLossFound = true;
							break;
						}
					}
				}
				if(!virginityLossFound) {
					descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] [npc.has] потерял [npc.her] пенисную девственность.</span>");
				}
				
			} else {
				descriptionSB.append(" [style.colourExcellent([npc.Name] [npc.has] сохранил [npc.her] девственную уретру пениса.)]");
			}
		}
		
		// Capacity:
		if (Capacity.getCapacityFromValue(viewedPenis.getOrificeUrethra().getStretchedCapacity()) != Capacity.ZERO_IMPENETRABLE) {
			descriptionSB.append("Уретра <br/>[npc.Her] настолько растянута, что представляет собой готовое отверстие для проникновения, и в нее можно с комфортом вводить объекты [style.colourSex(\","+ Units.size(Capacity.getCapacityFromValue(viewedPenis.getOrificeUrethra().getStretchedCapacity()).getMaximumValue(true)) + ")] в диаметре при смазывании.");

			if(Main.game.isPenetrationLimitationsEnabled()) {
				switch(owner.getUrethraDepth()) {
					default:
						descriptionSB.append(" [npc.Her] удобную для траха в уретру <span style='color:"+owner.getUrethraDepth().getColour().toWebHexString()+";'>[npc.penisUrethraDepth]</span>,");
						break;
					case TWO_AVERAGE:
						descriptionSB.append(" [npc.Her] удобная для траха уретра <span style='color:"+owner.getUrethraDepth().getColour().toWebHexString()+";'>среднюю глубину</span>,");
						break;
				}
				if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"[npc.she] можно [style.colourMinorGood(комфортно)] растягивать для размещения объектов максимальной длины [style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthUncomfortable())+")].");
					} else {
						descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthComfortable())+")] проникающего объекта, и [style.colourMinorBad(с дискомфортом)] принимать [style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthUncomfortable())+")].");
					}
					
				} else {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", в сочетании с тем фактом, что тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorGood(комфортно)] растягивать, чтобы вмещать объекты [style.colourSex(любой длины)].");
					} else {
						descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getUrethraMaximumPenetrationDepthComfortable())+")] проникающего объекта, а так как тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorBad(с дискомфортом)] растягивать для размещения объектов [style.colourSex(любой длины)].");
					}
				}
			}
			
			switch (viewedPenis.getOrificeUrethra().getElasticity()) {
				case ZERO_UNYIELDING:
					descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно тугое)],");
					break;
				case ONE_RIGID:
					descriptionSB.append(" Его [style.colourElasticity(требуется растягивать с огромным трудом)],");
					break;
				case TWO_FIRM:
					descriptionSB.append(" Оно [style.colourElasticity(растягивается не очень легко)],");
					break;
				case THREE_FLEXIBLE:
					descriptionSB.append(" Оно [style.colourElasticity(неохотно растягивается)], когда используется как сексуальное отверстие,");
					break;
				case FOUR_LIMBER:
					descriptionSB.append(" Оно [style.colourElasticity(довольно устойчиво к растяжению)],");
					break;
				case FIVE_STRETCHY:
					descriptionSB.append(" Оно [style.colourElasticity(растягивается довольно легко)],");
					break;
				case SIX_SUPPLE:
					descriptionSB.append(" Оно [style.colourElasticity(растягивается очень легко)],");
					break;
				case SEVEN_ELASTIC:
					descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно эластичное)],");
					break;
			}
			descriptionSB.append(" и после использования оно "+viewedPenis.getOrificeUrethra().getPlasticity().getDescription()+".");
			
			for(OrificeModifier om : OrificeModifier.values()) {
				if(owner.hasUrethraOrificeModifier(om)) {
					switch(om) {
						case PUFFY:
							descriptionSB.append(" [npc.Her] уретра превратилась в опухший, припухший ободок.");
							break;
						case MUSCLE_CONTROL:
							descriptionSB.append(" [npc.She] [npc.has] ряд мышц, выстилающих внутреннюю часть [npc.her] уретры, позволяющий [npc.herHim] умело сжимать и обхватывать любой проникающий объект.");
							break;
						case RIBBED:
							descriptionSB.append(" Внутренняя часть [npc.her] уретры выстлана чувствительными мясистыми складками, которые дарят [npc.herHim] дополнительное удовольствие при стимуляции.");
							break;
						case TENTACLED:
							descriptionSB.append(" [npc.Her] уретра заполнена крошечными щупальцами, которые извиваются и шевелятся сами по себе.");
							break;
					}
				}
			}
		}

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if (isPlayer && !owner.isUrethraVirgin()) {
				for(SexAreaPenetration pt : SexAreaPenetration.values()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, pt))!=null) {
						descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, pt)) + "</span>");
						break;
					}
				}
			}
			descriptionSB.append("<br/>");
		}
		

		if(viewedPenis.getType()!=PenisType.DILDO) {
			// Pubic Hair:
			if(Main.game.isPubicHairEnabled()) {
				if(owner.getPubicHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
					switch(owner.getPubicHair()) {
						case ZERO_NONE:
							descriptionSB.append(" Нет и следа какой-либо грубости  "+owner.getPubicHairType().getName(owner)+" вокруг основания [npc.her] члена.");
							break;
						case ONE_STUBBLE:
							descriptionSB.append(" [npc.She] [npc.has] немного грубой "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case TWO_MANICURED:
							descriptionSB.append(" [npc.She] [npc.has] грубую полоску "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case THREE_TRIMMED:
							descriptionSB.append(" [npc.She] [npc.has] грубую полоску "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case FOUR_NATURAL:
							descriptionSB.append(" [npc.She] [npc.has] естественное количество грубой "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case FIVE_UNKEMPT:
							descriptionSB.append(" [npc.She] [npc.has] неопрятную массу грубой "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case SIX_BUSHY:
							descriptionSB.append(" [npc.She] [npc.has] густую, грубую массу "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case SEVEN_WILD:
							descriptionSB.append(" [npc.She] [npc.has] дикую, грубую массу "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
					}
				} else {
					switch(owner.getPubicHair()) {
						case ZERO_NONE:
							descriptionSB.append(" Нет и следа от "+owner.getPubicHairType().getName(owner)+" вокруг основания [npc.her] члена.");
							break;
						case ONE_STUBBLE:
							descriptionSB.append(" [npc.She] [npc.has] колючий участок "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case TWO_MANICURED:
							descriptionSB.append(" [npc.She] [npc.has] аккуратный, ухоженный участок "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case THREE_TRIMMED:
							descriptionSB.append(" [npc.She] [npc.has] подстриженный участок "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case FOUR_NATURAL:
							descriptionSB.append(" [npc.She] [npc.has] естественные заросли "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case FIVE_UNKEMPT:
							descriptionSB.append(" [npc.She] [npc.has] неопрятные заросли "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case SIX_BUSHY:
							descriptionSB.append(" [npc.She] [npc.has] густая, пушистая масса "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
						case SEVEN_WILD:
							descriptionSB.append(" [npc.She] [npc.has] дикая, пушистая масса "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг основания [npc.her] члена.");
							break;
					}
				}
			}
	
			descriptionSB.append("<br/>");
		}
		
		// Testicle size and cum production:

		if(viewedPenis.getType()!=PenisType.DILDO) {
			if(owner.isInternalTesticles()) {
				descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] сместились внутрь [npc.her] тела, так что [npc.her] [npc.cock] — единственная видимая часть [npc.her] мужских половых органов.");
				
			} else {
				switch (viewedPenis.getTesticle().getTesticleSize()) {
					case ZERO_VESTIGIAL:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] — это [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], и они настолько малы, что едва видны как крошечные бугорки, притаившиеся под [npc.her] [npc.cock].");
						break;
					case ONE_TINY:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls] — это [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], и они достаточно малы, чтобы уютно располагаться под [npc.her] [npc.cock].");
						break;
					case TWO_AVERAGE:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] — это [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], и они свисают под [npc.her] [npc.cock].");
						break;
					case THREE_LARGE:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] — это [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], и они свисают под [npc.her] [npc.cock].");
						break;
					case FOUR_HUGE:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] — это [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], и они свисают под [npc.her] [npc.cock].");
						break;
					case FIVE_MASSIVE:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] — это [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], и они свисают под [npc.her] [npc.cock].");
						break;
					case SIX_GIGANTIC:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] — это [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], и они свисают под [npc.her] [npc.cock].");
						break;
					case SEVEN_ABSURD:
						descriptionSB.append(" [npc.Her] [npc.ballsCount] [npc.balls+] — это [npc.materialCompositionDescriptor] [npc.ballFullDescription(true)], и они свисают под [npc.her] [npc.cock].");
						break;
				}
			}
			
			switch (viewedPenis.getTesticle().getCumStorage()) {
				case ZERO_NONE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Несмотря на свои внушительные размеры, они");
					} else {
						descriptionSB.append(" Они");
					}
					descriptionSB.append(" совсем не выделяют [npc.cum+].");
					break;
				case ONE_TRICKLE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Несмотря на свои внушительные размеры, они");
					} else {
						descriptionSB.append(" Они");
					}
					descriptionSB.append(" выделяют лишь тонкую струйку [npc.cum+].");
					break;
				case TWO_SMALL_AMOUNT:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.THREE_LARGE.getValue()) {
						descriptionSB.append(" Несмотря на свои внушительные размеры, они");
					} else {
						descriptionSB.append(" Они");
					}
					descriptionSB.append(" выделяют лишь небольшое количество [npc.cum+].");
					break;
				case THREE_AVERAGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() > TesticleSize.FOUR_HUGE.getValue()) {
						descriptionSB.append(" Несмотря на свои огромные размеры, они лишь");
					} else {
						descriptionSB.append(" Они");
					}
					descriptionSB.append(" производят среднее количество [npc.cum+].");
					break;
				case FOUR_LARGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Несмотря на свои малые размеры, они");
					} else {
						descriptionSB.append(" Они");
					}
					descriptionSB.append(" производят большое количество [npc.cum+].");
					break;
				case FIVE_HUGE:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Несмотря на свои малые размеры, они");
					} else {
						descriptionSB.append(" Они");
					}
					descriptionSB.append(" производят огромное количество [npc.cum+].");
					break;
				case SIX_EXTREME:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Несмотря на свои малые размеры, они");
					} else {
						descriptionSB.append(" Они");
					}
					descriptionSB.append(" производят невероятное количество [npc.cum+].");
					break;
				case SEVEN_MONSTROUS:
					if (viewedPenis.getTesticle().getTesticleSize().getValue() < TesticleSize.TWO_AVERAGE.getValue()) {
						descriptionSB.append(" Несмотря на свои малые размеры, они");
					} else {
						descriptionSB.append(" Они");
					}
					descriptionSB.append(" производят чудовищное количество [npc.cum+].");
					break;
			}
			
			descriptionSB.append(" [npc.Her] [npc.cumColour(true)] [npc.cum]");
			
			switch(viewedPenis.getTesticle().getCum().getFlavour()) {
				case CUM:
					descriptionSB.append(", что никого не удивило, на вкус как сперма.");
					break;
				case BUBBLEGUM:
					descriptionSB.append(" имеет фруктовый вкус жвачки.");
					break;
				case FLAVOURLESS:
					descriptionSB.append(" абсолютно не имеет никакого вкуса.");
					break;
				default:
					descriptionSB.append(" на вкус точь-в-точь как "+viewedPenis.getTesticle().getCum().getFlavour().getName()+".");
					break;
			}
			
			for(FluidModifier fm : FluidModifier.values()) {
				if(owner.hasCumModifier(fm)) {
					descriptionSB.append(" " + fm.getBriefDescription());
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}
	
	public String getVaginaDescription(GameCharacter owner) {
		boolean isPlayer = owner.isPlayer();
		
		descriptionSB = new StringBuilder();

		Vagina viewedVagina = vagina;
		
		boolean hallucinating = false;
		if(Main.game.getPlayer().hasIngestedPsychoactiveFluidType(FluidTypeBase.GIRLCUM)) {
			viewedVagina = new Vagina(vagina.getType(),
					vagina.getRawLabiaSizeValue(),
					vagina.getClitoris().getRawClitorisSizeValue(),
					vagina.getClitoris().getRawGirthValue(),
					Wetness.SEVEN_DROOLING.getValue(),
					vagina.getOrificeVagina().getRawCapacityValue() *3,
					vagina.getOrificeVagina().getDepth(null).getValue(),
					vagina.getOrificeVagina().getElasticity().getValue(),
					vagina.getOrificeVagina().getPlasticity().getValue(),
					vagina.getOrificeVagina().isVirgin());
			viewedVagina.setPierced(owner, vagina.isPierced());
			descriptionSB.append("<i style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>Психоактивная девичья сперма, которую ты недавно проглотил, искажает твое восприятие "+(owner.isPlayer()?"твоей":"[npc.namePos]")+" киски!</i> ");
			hallucinating = true;
		}

		switch(owner.getGenitalArrangement()) {
			case CLOACA:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] киска расположена внутри [npc.her] щелевидной, направленной вперед клоаки.)] "));
				break;
			case CLOACA_BEHIND:
				descriptionSB.append(UtilText.parse(owner, "[style.italicsFeral([npc.Her] киска расположена внутри [npc.her] щелевидной, направленной назад клоаки.)] "));
				break;
			case NORMAL:
				break;
		}
		
		if(owner.hasPenis()) {
			descriptionSB.append("Под [npc.her] [npc.penis], ");
		} else if(owner.hasLegs()) {
			descriptionSB.append("Между [npc.her] [npc.legs], ");
		} else {
			descriptionSB.append("Внизу, в паху [npc.her], ");
		}
		
		descriptionSB.append(viewedVagina.getType().getBodyDescription(owner));
		
		if(owner.isImpregnationPhysicallyPossible()) {
			if(owner.isVaginaEggLayer()) {
				descriptionSB.append(" Из-за особенностей репродуктивных органов [npc.her], [npc.she] [style.colourEgg(откладывает [npc.verb(lay)] яиц вместо того, чтобы рожать живых детенышей)].");
			} else {
				descriptionSB.append(" Из-за особенностей репродуктивных органов [npc.her], [npc.she] [style.colourSex(производит на свет [npc.verb(give)] живых детенышей)].");
			}
		}
		
		if(owner.isFeral()) {
			descriptionSB.append(" [style.colourFeral(Как и ожидалось, [npc.her] [npc.pussy] полностью дикий по форме и ничем не отличается от обычного [npc.vaginaRace].)]");
		} else if(vagina.isFeral(owner)) {
			descriptionSB.append(" [style.colourFeral(Поскольку это находится на нижней, звериной части тела [npc.her], [npc.her] [npc.pussy] ничем не отличается от дикого [npc.vaginaRace].)]");
		}
		
		// Clit:
		descriptionSB.append("<br/>[npc.She] [npc.has] [npc.a_clitSize]"+(owner.getClitorisGirth()==PenetrationGirth.THREE_AVERAGE?"":", [npc.clitGirth]")
				+" клитор, длина которого составляет [npc.clitSizeValue], а диаметр — [npc.clitDiameter(true)] ([npc.clitCircumference(true)] в обхвате).");
		if(owner.isClitorisPseudoPenis()) {
			descriptionSB.append(" [style.colourSex(Его внушительный размер позволяет [npc.herHim] использовать его как подобие пениса)].");
		}
		
		for(PenetrationModifier pm : PenetrationModifier.getPenetrationModifiers()) {
			if(owner.hasClitorisModifier(pm)) {
				switch(pm) {
					case RIBBED:
						descriptionSB.append(" Он выстлан твердыми мясными ребрами, которые наверняка принесут дополнительное удовольствие любому отверстию, в которое они проникнут.");
						break;
					case TENTACLED:
						descriptionSB.append(" Его поверхность покрыта множеством маленьких щупалец, которые извиваются и шевелятся сами по себе.");
						break;
					case BARBED:
						descriptionSB.append(" Вдоль всей его длины тянутся мясистые, направленные назад шипы.");
						break;
					case BLUNT:
						descriptionSB.append(" Кончик плавно закругляется, переходя в гладкую поверхность.");
						break;
					case FLARED:
						descriptionSB.append(" Кончик широкий и расширяющийся, словно лошачий член.");
						break;
					case KNOTTED:
						descriptionSB.append(" У основания расположено толстое утолщение.");
						break;
					case PREHENSILE:
						descriptionSB.append(" Он цепкий, им можно манипулировать и двигать почти так же, как хвостом примата.");
						break;
					case SHEATHED:
						descriptionSB.append(" Ее капюшон клитора превратился в широкую складку, в которую [npc.her] [npc.clit] может втягивать свой орган, каким бы большим он ни становился.");
						break;
					case TAPERED:
						descriptionSB.append(" Ствол сужается к головке.");
						break;
					case VEINY:
						descriptionSB.append(" Из его поверхности выступают крупные вены.");
						break;
					case OVIPOSITOR:
						descriptionSB.append(" Его внутреннее строение позволяет использовать его как яйцеклад, что дает возможность [npc.name] откладывать [npc.her] оплодотворенных яиц в отверстия цели.");
						break;
				}
			}
		}
		
		
		// Virgin/capacity:
		descriptionSB.append("<br/>");
		if(viewedVagina.getOrificeVagina().isVirgin()) {
			if(isPlayer || !hallucinating) {
				if(viewedVagina.getOrificeVagina().hasHymen()) {
					descriptionSB.append(" Внутри [npc.her] " + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)
							+ " [npc.pussy], [style.colourMinorGood(девственная плева [npc.her] все еще цела)], и [style.colourExcellent([npc.she] [npc.has] сохранила [npc.her] вагинальную девственность)].");
					
				} else {
					if(owner.isDoll()) {
						descriptionSB.append(" Как секс-кукла, [npc.name] [npc.do] не имеет девственной плевы, и [style.colourExcellent([npc.has] сохранила [npc.her] вагинальную девственность)].");
						
					} else {
						descriptionSB.append(" Внутри [npc.her] " + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)
								+ " [npc.pussy], [style.colourMinorBad(девственная плева [npc.her] разорвана)], но, несмотря на это, [style.colourExcellent([npc.she] [npc.has] сохранила [npc.her] вагинальную девственность)].");
					}
				}
			}
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt))!=null) {
						descriptionSB.append(" [style.colourArcane("+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt)) + ")]");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append(" [style.colourArcane([npc.Name] [npc.has] потеряла [npc.her] девственность.)]");
			}
			if(viewedVagina.getOrificeVagina().hasHymen()) {
				descriptionSB.append(" Хотя [npc.sheIsFull] больше не девственница, [style.colourMinorGood([npc.she] [npc.has] имеет целую девственную плеву)] внутри [npc.her] киски.");
			} else {
				if(owner.isDoll()) {
					descriptionSB.append(" Как секс-кукла, [npc.name] [npc.do] не имеет девственной плевы.");
					
				} else {
					descriptionSB.append(" Как и ожидалось от той, кто уже не девственница, [style.colourMinorBad(девственная плева [npc.her] разорвана)].");
				}
			}
		}
		
		if (isPlayer || !hallucinating) {
			descriptionSB.append(" Киска [npc.Her] — это " + Capacity.getCapacityFromValue(viewedVagina.getOrificeVagina().getStretchedCapacity()).getDescriptor(true)+" и при смазывании может с комфортом вмещать объекты размером до [style.colourSex(\", "+ Units.size(Capacity.getMaximumComfortableDiameter(viewedVagina.orificeVagina.getElasticity(), viewedVagina.getOrificeVagina().getRawCapacityValue(), true)) + ")] в диаметре.");
		}

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getVaginaDepth()) {
				default:
					descriptionSB.append(" киска [npc.Her] — <span style='color:"+owner.getVaginaDepth().getColour().toWebHexString()+";'>[npc.pussyDepth]</span>,");
					break;
				case TWO_AVERAGE:
					descriptionSB.append(" киска [npc.Her] — <span style='color:"+owner.getVaginaDepth().getColour().toWebHexString()+";'>среднюю глубину</span>,");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"[npc.she] можно [style.colourMinorGood(комфортно)] растягивать для размещения объектов максимальной длины [style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthUncomfortable())+")].");
				} else {
					descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthComfortable())+")] проникающего объекта, и [style.colourMinorBad(с дискомфортом)] принимать [style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthUncomfortable())+")].");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", в сочетании с тем фактом, что тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorGood(комфортно)] растягивать, чтобы вмещать объекты [style.colourSex(любой длины)].");
				} else {
					descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getVaginaMaximumPenetrationDepthComfortable())+")] проникающего объекта, а так как тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorBad(с дискомфортом)] растягивать для размещения объектов [style.colourSex(любой длины)].");
				}
			}
		}
		
		// Wetness:
		switch (viewedVagina.getOrificeVagina().getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append(" Она [style.colourWetness(совершенно сухая и никогда не намокает)], как бы сильно ни возбуждалась [npc.she] [npc.is].");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append(" Она [style.colourWetness(слегка влажная)], и [npc.she] [npc.verb(need)] требуется много стимуляции, прежде чем [npc.she] [npc.verb(get)] станет мокрой.");
				break;
			case TWO_MOIST:
				descriptionSB.append(" Она [style.colourWetness(влажная)], но [npc.she] все равно нужно много [npc.verb(need)] стимуляции, прежде чем [npc.she] [npc.verb(get)] станет совсем мокрой.");
				break;
			case THREE_WET:
				descriptionSB.append(" У нее [style.colourWetness(средний уровень влажности)], и [npc.she] нужно лишь немного [npc.verb(need)] прелюдии, прежде чем [npc.sheIs] станет достаточно влажной для приятного проникновения.");
				break;
			case FOUR_SLIMY:
				descriptionSB.append(" Она всегда [style.colourWetness(скользкая и мокрая)], и [npc.sheIs] готова к проникновению в любой момент.");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append(" Ее поверхность всегда покрыта [style.colourWetness(скользкой влагой)], а внутри киска [npc.her] постоянно [style.colourWetness(текучая)] и практически умоляет, чтобы в нее трахнули.");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append(" Киска [npc.Her] никогда не бывает менее чем [style.colourWetness(промокшей насквозь)], и струйка [npc.her] естественной смазки постоянно сочится из [npc.her] щели.");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append(" [npc.Her] настолько [style.colourWetness(мокрая, что при каждом шаге [npc.she] [npc.verb(take)] слышно хлюпанье)], что из [npc.her] манящей киски постоянно течет струйка соков.");
				break;
		}
		
		if(viewedVagina.getOrificeVagina().isSquirter()) {
			descriptionSB.append(" [npc.She] [npc.is] — настоящая [style.colourArcane(сквиртер)], и [style.colourWetness([npc.verb(produce)] приличное количество женской эякуляции)] вырывается каждый раз, когда [npc.she] [npc.verb(orgasm)].");
		}
		
		// Elasticity & plasticity:
		switch (viewedVagina.getOrificeVagina().getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно тугое)],");
				break;
			case ONE_RIGID:
				descriptionSB.append(" Его [style.colourElasticity(требуется растягивать с огромным трудом)],");
				break;
			case TWO_FIRM:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается не очень легко)],");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" Она [style.colourElasticity(неохотно растягивается)], когда ее проникают,");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" Оно [style.colourElasticity(довольно устойчиво к растяжению)],");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается довольно легко)],");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается очень легко)],");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно эластичное)],");
				break;
		}
		descriptionSB.append(" и после использования оно "+viewedVagina.getOrificeVagina().getPlasticity().getDescription()+".");
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasVaginaOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append(" [npc.She] [npc.has] — это ряд внутренних мышц, выстилающих внутреннюю часть [npc.her] [npc.vagina], что позволяет [npc.herHim] умело сжимать и обхватывать любой входящий объект.");
						break;
					case PUFFY:
						descriptionSB.append(" Лабии [npc.Her] распухли, превратившись в большие, невероятно пухлые половые губы.");
						break;
					case RIBBED:
						descriptionSB.append(" Внутренняя часть [npc.her] [npc.vagina] покрыта чувствительными мясистыми складками, которые дарят [npc.herHim] дополнительное удовольствие при стимуляции.");
						break;
					case TENTACLED:
						descriptionSB.append(" [npc.Her] [npc.vagina] покрыта крошечными щупальцами, которые извиваются и шевелятся сами по себе.");
						break;
				}
			}
		}
		
		
		// Girlcum:
		
		descriptionSB.append(" [npc.Her] [npc.girlcumColour(true)] [npc.girlcum]");
		
		switch(viewedVagina.getGirlcum().getFlavour()) {
			case GIRL_CUM:
				descriptionSB.append(", что никого не удивляет, на вкус как обычная девичья смазка.");
				break;
			case BUBBLEGUM:
				descriptionSB.append(" имеет фруктовый вкус жвачки.");
				break;
			case FLAVOURLESS:
				descriptionSB.append(" абсолютно не имеет никакого вкуса.");
				break;
			default:
				descriptionSB.append(" на вкус точь-в-точь как "+viewedVagina.getGirlcum().getFlavour().getName()+".");
				break;
		}
		
		for(FluidModifier fm : FluidModifier.values()) {
			if(viewedVagina.getGirlcum().getFluidModifiers().contains(fm)) {
				descriptionSB.append(" " + fm.getBriefDescription());
			}
		}
		
		descriptionSB.append("<br/>");
		
		// Urethra:
		if (Capacity.getCapacityFromValue(viewedVagina.getOrificeUrethra().getStretchedCapacity()) != Capacity.ZERO_IMPENETRABLE) {
			descriptionSB.append("Уретра влагалища [npc.Her] достаточно расслаблена, чтобы служить готовым отверстием для проникновения, а при смазывании она может комфортно вмещать объекты размером до [style.colourSex(\""+ Units.size(Capacity.getMaximumComfortableDiameter(viewedVagina.getOrificeUrethra().getElasticity(), viewedVagina.getOrificeUrethra().getRawCapacityValue(), true)) + ")] в диаметре.");
			
			if(Main.game.isPenetrationLimitationsEnabled()) {
				switch(owner.getVaginaUrethraDepth()) {
					default:
						descriptionSB.append(" [npc.Her] удобную для траха в уретру <span style='color:"+owner.getVaginaUrethraDepth().getColour().toWebHexString()+";'>[npc.pussyUrethraDepth]</span>,");
						break;
					case TWO_AVERAGE:
						descriptionSB.append(" [npc.Her] удобная для траха уретра <span style='color:"+owner.getVaginaUrethraDepth().getColour().toWebHexString()+";'>среднюю глубину</span>,");
						break;
				}
				if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"[npc.she] можно [style.colourMinorGood(комфортно)] растягивать для размещения объектов максимальной длины [style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthUncomfortable())+")].");
					} else {
						descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthComfortable())+")] проникающего объекта, и [style.colourMinorBad(с дискомфортом)] принимать [style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthUncomfortable())+")].");
					}
					
				} else {
					if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
						descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", в сочетании с тем фактом, что тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorGood(комфортно)] растягивать, чтобы вмещать объекты [style.colourSex(любой длины)].");
					} else {
						descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getVaginaUrethraMaximumPenetrationDepthComfortable())+")] проникающего объекта, а так как тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorBad(с дискомфортом)] растягивать для размещения объектов [style.colourSex(любой длины)].");
					}
				}
			}
			
			// Elasticity & plasticity:
			switch (viewedVagina.getOrificeUrethra().getElasticity()) {
				case ZERO_UNYIELDING:
					descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно тугое)],");
					break;
				case ONE_RIGID:
					descriptionSB.append(" Его [style.colourElasticity(требуется растягивать с огромным трудом)],");
					break;
				case TWO_FIRM:
					descriptionSB.append(" Оно [style.colourElasticity(растягивается не очень легко)],");
					break;
				case THREE_FLEXIBLE:
					descriptionSB.append(" Она [style.colourElasticity(неохотно растягивается)], когда ее проникают,");
					break;
				case FOUR_LIMBER:
					descriptionSB.append(" Оно [style.colourElasticity(довольно устойчиво к растяжению)],");
					break;
				case FIVE_STRETCHY:
					descriptionSB.append(" Оно [style.colourElasticity(растягивается довольно легко)],");
					break;
				case SIX_SUPPLE:
					descriptionSB.append(" Оно [style.colourElasticity(растягивается очень легко)],");
					break;
				case SEVEN_ELASTIC:
					descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно эластичное)],");
					break;
			}
			descriptionSB.append(" и после использования оно "+viewedVagina.getOrificeUrethra().getPlasticity().getDescription()+".");
			
			for(OrificeModifier om : OrificeModifier.values()) {
				if(owner.hasVaginaUrethraOrificeModifier(om)) {
					switch(om) {
						case PUFFY:
							descriptionSB.append(" [npc.Her] уретра превратилась в опухший, припухший ободок.");
							break;
						case MUSCLE_CONTROL:
							descriptionSB.append(" [npc.She] [npc.has] ряд мышц, выстилающих внутреннюю часть [npc.her] уретры, позволяющий [npc.herHim] умело сжимать и обхватывать любой проникающий объект.");
							break;
						case RIBBED:
							descriptionSB.append(" Внутренняя часть [npc.her] уретры выстлана чувствительными мясистыми складками, которые дарят [npc.herHim] дополнительное удовольствие при стимуляции.");
							break;
						case TENTACLED:
							descriptionSB.append(" [npc.Her] уретра заполнена крошечными щупальцами, которые извиваются и шевелятся сами по себе.");
							break;
					}
				}
			}
		}
		
		// Pubic Hair:
		if(Main.game.isPubicHairEnabled()) {
			if(owner.getPubicHairType().getType()==BodyCoveringType.BODY_HAIR_SCALES_ALLIGATOR) {
				switch(owner.getPubicHair()) {
					case ZERO_NONE:
						descriptionSB.append(" Нет и следа какой-либо грубости  "+owner.getPubicHairType().getName(owner)+" вокруг [npc.her] [npc.pussy].");
						break;
					case ONE_STUBBLE:
						descriptionSB.append(" [npc.SheHasFull] немного грубой "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case TWO_MANICURED:
						descriptionSB.append(" [npc.SheHasFull] участок грубой кожи, "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case THREE_TRIMMED:
						descriptionSB.append(" [npc.SheHasFull] участок грубой кожи, "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case FOUR_NATURAL:
						descriptionSB.append(" [npc.SheHasFull] естественное количество жестких "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case FIVE_UNKEMPT:
						descriptionSB.append(" [npc.SheHasFull] неопрятную массу жестких "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case SIX_BUSHY:
						descriptionSB.append(" [npc.SheHasFull] густую, жесткую массу "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case SEVEN_WILD:
						descriptionSB.append(" [npc.SheHasFull] дикую, жесткую массу "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
				}
			} else {
				switch(owner.getPubicHair()) {
					case ZERO_NONE:
						descriptionSB.append(" Нет и следа от "+owner.getPubicHairType().getName(owner)+" вокруг [npc.her] [npc.pussy].");
						break;
					case ONE_STUBBLE:
						descriptionSB.append(" [npc.SheHasFull] клочок щетины на "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case TWO_MANICURED:
						descriptionSB.append(" [npc.SheHasFull] аккуратный, ухоженный островок "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case THREE_TRIMMED:
						descriptionSB.append(" [npc.SheHasFull] подстриженной зоны "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case FOUR_NATURAL:
						descriptionSB.append(" [npc.SheHasFull] естественный кустик "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case FIVE_UNKEMPT:
						descriptionSB.append(" [npc.SheHasFull] неопрятный кустик "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case SIX_BUSHY:
						descriptionSB.append(" [npc.SheHasFull] густой, пушистой массы "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
					case SEVEN_WILD:
						descriptionSB.append(" [npc.SheHasFull] дикой, пушистой массы "+owner.getPubicHairType().getFullDescription(owner, true)+" вокруг [npc.her] [npc.pussy].");
						break;
				}
			}
		}
		
		
		if (isPlayer && !owner.isVaginaUrethraVirgin()) {
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, pt))!=null) {
					descriptionSB.append(" <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>"+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_VAGINA, pt))+ "</span>");
					break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}

	public String getMoundDescription(GameCharacter owner) {
		return UtilText.parse(owner,
				"Поскольку у [npc.she] [npc.verb(possess)] нет гениталий, все, что представляет собой [npc.she] [npc.has], — это бесполое возвышение. Несмотря на [npc.her] отсутствие половых органов, это все еще невероятно чувствительная зона, и [npc.she] можно довести до состояния квази-оргазма путем стимуляции.");
	}
	
	public String getSpinneretDescription(GameCharacter owner) {
		if(!owner.hasSpinneret()) {
			return "";
		}
		
		descriptionSB = new StringBuilder();
		
		if(owner.hasLegSpinneret()) {
			descriptionSB.append("В нижней части живота [npc.her] [npc.legRace], ");
		} else {
			descriptionSB.append("В нижней части [npc.her] [npc.tail+], ");
		}
		descriptionSB.append("[npc.she] [npc.has] [npc.spinneretFullDescription(true)]. Она способна не только производить прочную паутину, но и может использоваться как сексуальное отверстие.");
		
		// Virgin/capacity:
		if(spinneret.isVirgin()) {
			descriptionSB.append(" [npc.She] [npc.has] [style.colourExcellent(сохранил(а) [npc.her] девственность паутинного органа)].");
			
		} else {
			boolean virginityLossFound = false;
			for(SexAreaPenetration pt : SexAreaPenetration.values()) {
				if(pt.isTakesVirginity()) {
					if(owner.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.SPINNERET, pt))!=null) {
						descriptionSB.append(" [style.colourArcane("+ owner.getVirginityLossDescription(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.SPINNERET, pt)) + ")]");
						virginityLossFound = true;
						break;
					}
				}
			}
			if(!virginityLossFound) {
				descriptionSB.append(" [style.colourArcane([npc.Name] [npc.has] потерял(а) [npc.her] девственность паутинного органа.)]");
			}
		}
		
		descriptionSB.append(" [npc.Her] паутинный орган — это " + Capacity.getCapacityFromValue(spinneret.getStretchedCapacity()).getDescriptor(true)+" и при смазывании может с комфортом вмещать объекты размером до [style.colourSex(\", "+ Units.size(Capacity.getMaximumComfortableDiameter(spinneret.getElasticity(), spinneret.getRawCapacityValue(), true)) + ")] в диаметре.");

		if(Main.game.isPenetrationLimitationsEnabled()) {
			switch(owner.getSpinneretDepth()) {
				default:
					descriptionSB.append(" Это <span style='color:"+owner.getSpinneretDepth().getColour().toWebHexString()+";'>[npc.spinneretDepth]</span>,");
					break;
				case TWO_AVERAGE:
					descriptionSB.append(" Это <span style='color:"+owner.getSpinneretDepth().getColour().toWebHexString()+";'>среднюю глубину</span>,");
					break;
			}
			if(owner.getBodyMaterial().isOrificesLimitedDepth()) {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+"[npc.she] можно [style.colourMinorGood(комфортно)] растягивать для размещения объектов максимальной длины [style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthUncomfortable())+")].");
				} else {
					descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthComfortable())+")] проникающего объекта, и [style.colourMinorBad(с дискомфортом)] принимать [style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthUncomfortable())+")].");
				}
				
			} else {
				if(owner.hasFetish(Fetish.FETISH_SIZE_QUEEN)) {
					descriptionSB.append(" и так как у [npc.sheIsFull], "+Fetish.FETISH_SIZE_QUEEN.getName(owner)+", в сочетании с тем фактом, что тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorGood(комфортно)] растягивать, чтобы вмещать объекты [style.colourSex(любой длины)].");
				} else {
					descriptionSB.append(" что позволяет [npc.herHim] [style.colourMinorGood(комфортно)] принимать [style.colourSex("+Units.size(owner.getSpinneretMaximumPenetrationDepthComfortable())+")] проникающего объекта, а так как тело [npc.her] состоит из [npc.bodyMaterial], [npc.she] можно [style.colourMinorBad(с дискомфортом)] растягивать для размещения объектов [style.colourSex(любой длины)].");
				}
			}
		}
		
		// Wetness:
		switch (spinneret.getWetness(owner)) {
			case ZERO_DRY:
				descriptionSB.append(" Она [style.colourWetness(совершенно сухая и никогда не намокает)], как бы сильно ни возбуждалась [npc.she] [npc.is].");
				break;
			case ONE_SLIGHTLY_MOIST:
				descriptionSB.append(" Она [style.colourWetness(слегка влажная)], и [npc.she] [npc.verb(need)] требуется много стимуляции, прежде чем [npc.she] [npc.verb(get)] станет мокрой.");
				break;
			case TWO_MOIST:
				descriptionSB.append(" Она [style.colourWetness(влажная)], но [npc.she] все равно нужно много [npc.verb(need)] стимуляции, прежде чем [npc.she] [npc.verb(get)] станет совсем мокрой.");
				break;
			case THREE_WET:
				descriptionSB.append(" У нее [style.colourWetness(средний уровень влажности)], и [npc.she] нужно лишь немного [npc.verb(need)] прелюдии, прежде чем [npc.sheIs] станет достаточно влажной для приятного проникновения.");
				break;
			case FOUR_SLIMY:
				descriptionSB.append(" Она всегда [style.colourWetness(скользкая и мокрая)], и [npc.sheIs] готова к проникновению в любой момент.");
				break;
			case FIVE_SLOPPY:
				descriptionSB.append(" Ее поверхность всегда покрыта [style.colourWetness(слизкой влагой)], а внутри [npc.her] паутинная железа постоянно [style.colourWetness(сочная)] и практически умоляет о том, чтобы в нее трахнулись.");
				break;
			case SIX_SOPPING_WET:
				descriptionSB.append(" [npc.Her] паутинная железа всегда [style.colourWetness(промокшая насквозь)], и из нее постоянно сочится струйка [npc.her] естественной смазки.");
				break;
			case SEVEN_DROOLING:
				descriptionSB.append(" [npc.Her] паутинная железа настолько [style.colourWetness(мокрая, что при каждом шаге [npc.she] [npc.verb(take)] слышится хлюпанье)], а из [npc.her] манящего отверстия непрерывно течет поток соков.");
				break;
		}
		
		// Elasticity & plasticity:
		switch (spinneret.getElasticity()) {
			case ZERO_UNYIELDING:
				descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно тугое)],");
				break;
			case ONE_RIGID:
				descriptionSB.append(" Его [style.colourElasticity(требуется растягивать с огромным трудом)],");
				break;
			case TWO_FIRM:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается не очень легко)],");
				break;
			case THREE_FLEXIBLE:
				descriptionSB.append(" Она [style.colourElasticity(неохотно растягивается)], когда ее проникают,");
				break;
			case FOUR_LIMBER:
				descriptionSB.append(" Оно [style.colourElasticity(довольно устойчиво к растяжению)],");
				break;
			case FIVE_STRETCHY:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается довольно легко)],");
				break;
			case SIX_SUPPLE:
				descriptionSB.append(" Оно [style.colourElasticity(растягивается очень легко)],");
				break;
			case SEVEN_ELASTIC:
				descriptionSB.append(" Оно [style.colourElasticity(чрезвычайно эластичное)],");
				break;
		}
		descriptionSB.append(" и после использования оно "+spinneret.getPlasticity().getDescription()+".");
		
		for(OrificeModifier om : OrificeModifier.values()) {
			if(owner.hasSpinneretOrificeModifier(om)) {
				switch(om) {
					case MUSCLE_CONTROL:
						descriptionSB.append(" [npc.She] [npc.has] ряд внутренних мышц, выстилающих внутреннюю часть [npc.her] паутинной железы, позволяющих [npc.herHim] умело сжимать и обхватывать любой проникший внутрь объект.");
						break;
					case PUFFY:
						descriptionSB.append(" Края распухли и стали особенно большими и мясистыми.");
						break;
					case RIBBED:
						descriptionSB.append(" Внутренняя часть [npc.her] паутинной железы выстлана чувствительными плотскими складками, которые дарят [npc.herHim] дополнительное удовольствие при стимуляции.");
						break;
					case TENTACLED:
						descriptionSB.append(" [npc.Her] паутинная железа наполнена крошечными щупальцами, которые извиваются и шевелятся сами по себе.");
						break;
				}
			}
		}
		
		return UtilText.parse(owner, descriptionSB.toString());
	}
	
	private String getSexDetails(GameCharacter owner) {
		
		if(owner.getTotalTimesHadSex(Main.game.getPlayer()) >=1) {
			descriptionSB = new StringBuilder();

			descriptionSB.append(getHeader("История секса"));
			
			// Amount of sex:
			
			descriptionSB.append(
					UtilText.parse(owner,
							"<span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>Вы занимались сексом с [npc.name] "+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+" "+(owner.getTotalTimesHadSex(Main.game.getPlayer())==1?"раз(а).":"раз(а).")
						+"</span>"));
			
			if(owner.getSexConsensualCount(Main.game.getPlayer())>=1) {
				if(owner.getSexConsensualCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," Тот единственный раз, когда вы занимались сексом с [npc.herHim], всё было по обоюдному согласию."));
					} else {
						descriptionSB.append(UtilText.parse(owner," Все "+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+" раз(а) всё было по обоюдному согласию."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," Тот единственный раз, когда вы занимались сексом с [npc.herHim], всё было по обоюдному согласию."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexConsensualCount(Main.game.getPlayer())))+" из этих раз(ов) всё было по обоюдному согласию."));
					}
				}
			}
			if(owner.getSexAsSubCount(Main.game.getPlayer())>=1) {
				if(owner.getSexAsSubCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," В тот единственный раз, когда вы занимались сексом с [npc.herHim], вы были доминирующим партнером."));
					} else {
						descriptionSB.append(UtilText.parse(owner," Все "+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+" раз вы были доминирующим партнером."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," В тот единственный раз, когда вы занимались сексом с [npc.herHim], вы были доминирующим партнером."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexAsSubCount(Main.game.getPlayer())))+" из этих раз вы были доминирующим партнером."));
					}
				}
			}
			if(owner.getSexAsDomCount(Main.game.getPlayer())>=1) {
				if(owner.getSexAsDomCount(Main.game.getPlayer()) == owner.getTotalTimesHadSex(Main.game.getPlayer())) {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," В тот единственный раз, когда вы занимались сексом с [npc.herHim], вы были подчиняющимся партнером."));
					} else {
						descriptionSB.append(UtilText.parse(owner," Все "+Util.intToString(owner.getTotalTimesHadSex(Main.game.getPlayer()))+" раз вы были подчиняющимся партнером."));
					}
					
				} else {
					if(owner.getTotalTimesHadSex(Main.game.getPlayer())==1) {
						descriptionSB.append(UtilText.parse(owner," В тот единственный раз, когда вы занимались сексом с [npc.herHim], вы были подчиняющимся партнером."));
					} else {
						descriptionSB.append(UtilText.parse(owner," "+Util.capitaliseSentence(Util.intToString(owner.getSexAsDomCount(Main.game.getPlayer())))+" из этих раз вы были подчиняющимся партнером."));
					}
				}
			}
			descriptionSB.append("</p>");

			return UtilText.parse(owner, descriptionSB.toString());
		
		} else {
			return "";
		}
	}
	
	private String getPregnancyDetails(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		
		boolean sectionAdded = false;
		// NPC is mother:
		
		if(owner.isVisiblyPregnant()) {
			descriptionSB.append(getHeader("Беременность"));
			GameCharacter father = owner.getPregnantLitter().getFather();
			if(father == null) {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>В результате одного из сексуальных контактов с [npc.her], [npc.name] оказалась забеременела.</span>");
			} else if(father.isPlayer()) {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>В результате одного из ваших сексуальных контактов, вы забеременели [npc.name].</span>");
			} else {
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>В результате одного из сексуальных контактов с [npc.her], [npc.name] оказалась забеременела от "+father.getName(true)+".</span>");
			}
			
			if(owner.hasStatusEffect(StatusEffect.PREGNANT_1)) {
				descriptionSB.append(" Живот [npc.Her] лишь слегка округлился, так как [npc.sheIs] находится лишь на первой стадии беременности.");
			} else if(owner.hasStatusEffect(StatusEffect.PREGNANT_2)) {
				descriptionSB.append(" Живот [npc.Her] заметно округлился, так как [npc.sheIs] уже на позднем сроке [npc.her] беременности.");
			} else {
				descriptionSB.append(" Живот [npc.Her] огромный, и хотя [npc.sheIs] явно готова к этому, [npc.sheHasFull] еще не решила рожать.");
			}
			descriptionSB.append("</p>");
			sectionAdded = true;
		}
		
		if(!owner.getLittersBirthed().isEmpty()) {
			if(!sectionAdded) {
				descriptionSB.append(getHeader("Беременность"));
			} else {
				descriptionSB.append("<p>");
			}
			descriptionSB.append(
					"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] родила "+Util.intToString(owner.getLittersBirthed().size())+" "+(owner.getLittersBirthed().size()==1?"раз":"раз")+".</span>");
			
			for(Litter litter : owner.getLittersBirthed()) {
				if(litter.getFather() == null) {
					descriptionSB.append("<br/>На "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+", [npc.she] забеременела, а затем на "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] родила ");
					
				} else if(litter.getFather().isPlayer()) {
					descriptionSB.append("<br/>На "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
							+", ты оплодотворила [npc.herHim], а затем "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] родила ");
					
				} else {
					descriptionSB.append("<br/>На "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)+", "+litter.getFather().getName(true)
							+" оплодотворила [npc.herHim], а затем "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] родила ");
				}
				
				descriptionSB.append(litter.getBirthedDescription());
				
				descriptionSB.append(".");
			}
			
			descriptionSB.append("</p>");
			sectionAdded = true;
		}
		
		// NPC is father:
		
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			for(PregnancyPossibility pp : Main.game.getPlayer().getPotentialPartnersAsMother()) {
				if(pp.getFather()!=null && pp.getFather().equals(owner)) {
					if(!sectionAdded) {
						descriptionSB.append(getHeader("Беременность"));
					} else {
						descriptionSB.append("<p>");
					}
					descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>В результате одной из ваших сексуальных встреч ты забеременела, и вполне возможно, что [npc.name] — отец.</span>");
					descriptionSB.append("</p>");
					sectionAdded = true;
					break;
				}
			}
		}
		
		if(!Main.game.getPlayer().getLittersBirthed().isEmpty()) {
			int fatheredLitters = 0;
			
			for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
				if(litter.getFather()!=null && litter.getFather().equals(owner)){
					fatheredLitters++;
				}
			}
			
			if(fatheredLitters!=0) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("Беременность"));
				} else {
					descriptionSB.append("<p>");
				}
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] является отцом некоторых твоих детей и всего лишь оплодотворил тебя "+Util.intToString(fatheredLitters)+" "+(fatheredLitters==1?"раз":"раз")+".</span>");
				
				for(Litter litter : Main.game.getPlayer().getLittersBirthed()) {
					if(litter.getFather()!=null && litter.getFather().equals(owner)){
						descriptionSB.append("<br/>На "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+", [npc.she] оплодотворил тебя, а затем "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", ты родила "+litter.getBirthedDescription()+".");
					}
				}
				
				descriptionSB.append("</p>");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}
	

	private String getIncubationPregnancyDetails(GameCharacter owner) {
		descriptionSB = new StringBuilder();
		boolean sectionAdded = false;
		
		if(!owner.getIncubatingLitters().isEmpty()) {
			sectionAdded = true;
			descriptionSB.append(getHeader("Инкубация яиц"));
			int i=0;
			for(Entry<SexAreaOrifice, Litter> entry : owner.getIncubatingLitters().entrySet()) {
				if(i>0) {
					descriptionSB.append("<br/>");
				}
				String areaEgged = "";
				String stage = "";
				switch(entry.getKey()) {
					case ANUS:
					case MOUTH:
						areaEgged = "stomach";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_1)) {
							stage = " Живот [npc.Her] лишь слегка вздут, так как яйца в желудке [npc.her] были имплантированы совсем недавно.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_2)) {
							stage = " Живот [npc.Her] заметно раздулся, так как у яиц в желудке [npc.her] было достаточно времени, чтобы созреть и вырасти.";
						} else {
							stage = " Живот [npc.Her] раздулся до невероятных размеров, и хотя [npc.sheIs] явно готова к этому, [npc.sheHasFull] еще не решила отложить яйца, которые [npc.sheHas] высиживал в желудке [npc.her].";
						}
						break;
					case NIPPLE:
						areaEgged = "[npc.breasts]";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_1)) {
							stage = " [npc.Her] [npc.breasts] лишь слегка раздуты, так как яйца в [npc.her] [npc.nipples] были имплантированы совсем недавно.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_2)) {
							stage = " [npc.Her] [npc.breasts] заметно раздуты, так как у яиц в [npc.her] [npc.nipples] было достаточно времени, чтобы созреть и вырасти.";
						} else {
							stage = " [npc.Her] [npc.breasts] массивно раздуты, и хотя [npc.sheIs] явно к этому готова, [npc.sheHasFull] еще не решила отложить яйца, которые [npc.sheHas] высиживала в [npc.her] [npc.nipples].";
						}
						break;
					case NIPPLE_CROTCH:
						areaEgged = "[npc.crotchBoobs]";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1)) {
							stage = " [npc.Her] [npc.crotchBoobs] лишь слегка раздуты, так как яйца в [npc.her] [npc.crotchNipples] были имплантированы совсем недавно.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2)) {
							stage = " [npc.Her] [npc.crotchBoobs] заметно раздуты, так как у яиц в [npc.her] [npc.crotchNipples] было достаточно времени, чтобы созреть и вырасти.";
						} else {
							stage = " [npc.Her] [npc.crotchBoobs] массивно раздуты, и хотя [npc.sheIs] явно к этому готова, [npc.sheHasFull] еще не решила отложить яйца, которые [npc.sheHas] высиживала в [npc.her] [npc.crotchNipples].";
						}
						break;
					case SPINNERET:
						areaEgged = "[npc.spinneret]";
						String spinneretArea = owner.hasTailSpinneret()?"[npc.tail]":"abdomen";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_1)) {
							stage = " [npc.Her] "+spinneretArea+" лишь слегка раздуто, так как яйца в паутинном аппарате [npc.her] были имплантированы совсем недавно.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_2)) {
							stage = " [npc.Her] "+spinneretArea+" заметно раздуто, так как у яиц в паутинном аппарате [npc.her] было достаточно времени, чтобы созреть и вырасти.";
						} else {
							stage = " [npc.Her] "+spinneretArea+" массивно раздуто, и хотя [npc.sheIs] явно к этому готова, [npc.sheHasFull] еще не решила отложить яйца, которые [npc.sheHas] высиживала в паутинном аппарате [npc.her].";
						}
						break;
					case VAGINA:
						areaEgged = "womb";
						if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_1)) {
							stage = " Живот [npc.Her] лишь слегка вздут, так как яйца в утробе [npc.her] были имплантированы совсем недавно.";
						} else if(owner.hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_2)) {
							stage = " Живот [npc.Her] заметно вздут, так как у яиц в утробе [npc.her] было достаточно времени, чтобы созреть и вырасти.";
						} else {
							stage = " Живот [npc.Her] раздулся до невероятных размеров, и хотя [npc.sheIs] явно к этому готова, [npc.sheHasFull] еще не решила отложить яйца, которые [npc.sheHas] вынашивала в [npc.her].";
						}
						break;
					case ARMPITS:
					case ASS:
					case BREAST:
					case BREAST_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						break;
				}
				GameCharacter mother = entry.getValue().getMother();
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>");
					if(mother == null) {
						descriptionSB.append("В результате одной из сексуальных встреч с [npc.her], [npc.name] [npc.her] "+areaEgged+" наполнила яйцами.");
					} else if(mother.isPlayer()) {
						descriptionSB.append("В результате одной из ваших сексуальных встреч, вы заставили [npc.namePos] "+areaEgged+" наполниться яйцами.");
					} else {
						descriptionSB.append("В результате одного из сексуальных контактов [npc.her], у [npc.name] появилась [npc.her] "+areaEgged+" наполненной яйцами после "+mother.getName(true)+".");
					}
				descriptionSB.append("</span>");
				descriptionSB.append(stage);
				i++;
			}
			descriptionSB.append("</p>");
		}
		
		if(!owner.getLittersIncubated().isEmpty()) {
			if(!sectionAdded) {
				descriptionSB.append(getHeader("Инкубация яиц"));
			} else {
				descriptionSB.append("<p>");
			}
			sectionAdded = true;
				descriptionSB.append(
						"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] выносила и отложила яйца "+Util.intToString(owner.getLittersIncubated().size())+" "+(owner.getLittersIncubated().size()==1?"раз":"раз")+"."
						+ "</span>");

				//Litter.getMother is the character who passed on the eggs to the incubator
				for(Litter litter : owner.getLittersIncubated()) {
					if(litter.getMother()==null) {
						descriptionSB.append("<br/>На "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+", в [npc.she] были вживлены "+litter.getTotalLitterCount()+" яйца, а затем "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] отложила и родила ");
						
					} else if(litter.getMother().isPlayer()) {
						descriptionSB.append("<br/>На "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)
								+", вы вживили в [npc.herHim] "+litter.getTotalLitterCount()+" яйца, а затем "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] отложила и родила ");
						
					} else {
						descriptionSB.append("<br/>На "+Units.date(litter.getConceptionDate(), Units.DateType.LONG)+", "+litter.getMother().getName(true)
								+" вживили в [npc.herHim] "+litter.getTotalLitterCount()+" яйца, а затем "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", [npc.she] отложила и родила ");
					}
					
					descriptionSB.append(litter.getBirthedDescription());
					
					descriptionSB.append(".");
				}
			descriptionSB.append("</p>");
		}
		
		// NPC is egg implanter:
		
		if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
			List<String> areasEgged = new ArrayList<>();
			for(Entry<SexAreaOrifice, Litter> entry : Main.game.getPlayer().getIncubatingLitters().entrySet()) {
				if(entry.getValue().getMother()!=null && entry.getValue().getMother().equals(owner)) {
					String areaEgged = "";
					switch(entry.getKey()) {
						case ANUS:
						case MOUTH:
							areaEgged = "stomach";
							break;
						case NIPPLE:
							areaEgged = "[pc.nipples]";
							break;
						case NIPPLE_CROTCH:
							areaEgged = "[pc.crotchNipples]";
							break;
						case SPINNERET:
							areaEgged = "spinneret";
							break;
						case VAGINA:
							areaEgged = "womb";
							break;
						case ARMPITS:
						case ASS:
						case BREAST:
						case BREAST_CROTCH:
						case THIGHS:
						case URETHRA_PENIS:
						case URETHRA_VAGINA:
							break;
					}
					areasEgged.add(areaEgged);
				}
			}
			if(!areasEgged.isEmpty()) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("Инкубация яиц"));
				} else {
					descriptionSB.append("<p>");
				}
				sectionAdded = true;
				descriptionSB.append(
						"<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.NameIsFull] — тот, кто вживил яйца, которые сейчас находятся в твоих "+Util.stringsToStringList(areasEgged, false)+"."
						+ "</span>");
				descriptionSB.append("</p>");
			}
		}
		
		if(!Main.game.getPlayer().getLittersIncubated().isEmpty()) {
			int incubatedLitters = 0;
			
			for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
				if(litter.getMother()!=null && litter.getMother().equals(owner)) {
					incubatedLitters++;
				}
			}
			
			if(incubatedLitters!=0) {
				if(!sectionAdded) {
					descriptionSB.append(getHeader("Инкубация яиц"));
				} else {
					descriptionSB.append("<p>");
				}
				descriptionSB.append("<span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>[npc.Name] несет ответственность за то, что ранее отложил в тебя [npc.her] яиц, и всего таких случаев было [npc.sheHasFull] "+Util.intToString(incubatedLitters)+" "+(incubatedLitters==1?"раз":"раз")+".</span>");
				
				for(Litter litter : Main.game.getPlayer().getLittersIncubated()) {
					if(litter.getMother()!=null && litter.getMother().equals(owner)) {
						descriptionSB.append("<br/>На "+Units.date(litter.getIncubationStartDate(), Units.DateType.LONG)
								+", [npc.she] имплантировал в тебя кладку яиц, а затем "+Units.date(litter.getBirthDate(), Units.DateType.LONG)+", ты отложила и вылупила "+litter.getBirthedDescription()+".");
					}
				}
				
				descriptionSB.append("</p>");
			}
		}

		return UtilText.parse(owner, descriptionSB.toString());
	}

	
	/**
	 * Gender is calculated based on body femininity and sexual organs.
	 * 
	 * @return gender of this body
	 */
	public Gender getGender() {
		boolean hasPenis = penis.getType() != PenisType.NONE;
		boolean hasVagina = vagina.getType() != VaginaType.NONE;
		boolean hasBreasts = breast.hasBreasts();
		if(this.isFeral() && this.getSubspecies().getFeralAttributes(this)!=null) {
			hasBreasts = this.getSubspecies().getFeralAttributes(this).isBreastsPresent() || this.getBreastCrotch().hasBreasts();
		}
		
		// Looks male:
		if (femininity <= Femininity.MASCULINE.getMaximumFemininity()) {
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.M_P_V_B_HERMAPHRODITE;
					} else {
						return Gender.M_P_V_HERMAPHRODITE;
					}
				} else {
					if(hasBreasts) {
						return Gender.M_P_B_BUSTYBOY;
					} else {
						return Gender.M_P_MALE;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.M_V_B_BUTCH;
					} else {
						return Gender.M_V_CUNTBOY;
					}
				} else {
					if(hasBreasts) {
						return Gender.M_B_MANNEQUIN;
					} else {
						return Gender.M_MANNEQUIN;
					}
				}
			}

		// Looks androgynous:
		} else if (femininity <= Femininity.ANDROGYNOUS.getMaximumFemininity()){
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.N_P_V_B_HERMAPHRODITE;
					} else {
						return Gender.N_P_V_HERMAPHRODITE;
					}
				} else {
					if(hasBreasts) {
						return Gender.N_P_B_SHEMALE;
					} else {
						return Gender.N_P_TRAP;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.N_V_B_TOMBOY;
					} else {
						return Gender.N_V_TOMBOY;
					}
				} else {
					if(hasBreasts) {
						return Gender.N_B_DOLL;
					} else {
						return Gender.N_NEUTER;
					}
				}
			}
		
		// Looks feminine:
		} else {
			if(hasPenis) {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.F_P_V_B_FUTANARI;
					} else {
						return Gender.F_P_V_FUTANARI;
					}
				} else {
					if(hasBreasts) {
						return Gender.F_P_B_SHEMALE;
					} else {
						return Gender.F_P_TRAP;
					}
				}
			} else {
				if(hasVagina) {
					if(hasBreasts) {
						return Gender.F_V_B_FEMALE;
					} else {
						return Gender.F_V_FEMALE;
					}
				} else {
					if(hasBreasts) {
						return Gender.F_B_DOLL;
					} else {
						return Gender.F_DOLL;
					}
				}
			}
			
		}
	}

	/**
	 * @return weight in kilograms
	 */
	public int getCalculatedWeight() {
		//TODO?
		// Was this ever going to be used?
		// If so, it should take into account BodySize and Muscle

		// Weight = 0.4 * height
		int weight = (int) (height * 0.4f);

		// If harpy wings, your bones & muscles are really light, so *0.5
		if (arm.getType().equals(ArmType.HARPY)) {
			weight *= 0.5;
		}

		// If tauric lower body, weight is based on horse-sized animal, so weight*0.6 + 250 (horses are 400kg at lightest and centaur bodies are smaller than horse's)
		if (leg.getLegConfiguration()==LegConfiguration.QUADRUPEDAL
				|| leg.getLegConfiguration()==LegConfiguration.ARACHNID
				|| leg.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
			weight *= 0.6;
			weight += 250;
		}

		return weight;
	}
	
	/**
	 * @return true if this character's Height value is less than Height.ZERO_TINY. This means that a fairy-sized body will also return true for this method.
	 */
	public boolean isShortStature() {
		return this.getHeight().isShortStature();
	}
	
	public boolean isFairySized() {
		return this.getHeight().isFairySized();
	}
	
	/** Height is measured in cm. **/
	public int getHeightValue() {
		return height;
	}
	
	public Height getHeight() {
		return Height.getHeightFromInt(height);
	}

	/**
	 * Sets height attribute. Bound between 15cm (5.9 inches) and 365cm (12 feet).
	 * 
	 * @param height Value to set height to.
	 * @return True if height was changed.
	 */
	public boolean setHeight(int height) {
		if (this.height == height) {
			return false;
		}
		
		this.height = Math.max(Height.NEGATIVE_THREE_MINIMUM.getMinimumValue(), Math.min(height, Height.SEVEN_COLOSSAL.getMaximumValue()));

		return true;
	}

	public boolean isFeminine() {
		return getFemininity() >= Femininity.ANDROGYNOUS.getMinimumFemininity();
	}
	
	public int getFemininity() {
		return femininity;
	}

	/**
	 * @param femininity
	 *            Value to set femininity to.
	 * @return True if femininity was changed.
	 */
	public boolean setFemininity(int femininity) {
		if (this.femininity == femininity) {
			return false;
		}
		
		if (femininity <= 0) {
			if (this.femininity == 0)
				return false;
			this.femininity = 0;
			return true;
		}
		if (femininity >= 100) {
			if (this.femininity == 100)
				return false;
			this.femininity = 100;
			return true;
		}
		
		this.femininity = femininity;
		return true;
	}
	
	public BodyHair getPubicHair() {
		return pubicHair;
	}
	
	public void setPubicHair(BodyHair pubicHair) {
		this.pubicHair = pubicHair;
	}
	
	public int getBodySize() {
		return bodySize;
	}

	/**
	 * @param bodySize Value to set femininity to.
	 * @return True if bodySize was changed.
	 */
	public boolean setBodySize(int bodySize) {
		if (this.bodySize == bodySize) {
			return false;
		}
		
		if (bodySize <= 0) {
			if (this.bodySize == 0)
				return false;
			this.bodySize = 0;
			return true;
		}
		if (bodySize >= 100) {
			if (this.bodySize == 100)
				return false;
			this.bodySize = 100;
			return true;
		}
		
		this.bodySize = bodySize;
		return true;
	}
	
	public int getMuscle() {
		return muscle;
	}

	/**
	 * @param muscle Value to set muscle to.
	 * @return True if muscle was changed.
	 */
	public boolean setMuscle(int muscle) {
		if (this.muscle == muscle) {
			return false;
		}
		
		if (muscle <= 0) {
			if (this.muscle == 0)
				return false;
			this.muscle = 0;
			return true;
		}
		if (muscle >= 100) {
			if (this.muscle == 100)
				return false;
			this.muscle = 100;
			return true;
		}
		
		this.muscle = muscle;
		return true;
	}
	
	public BodyShape getBodyShape() {
		return BodyShape.valueOf(Muscle.valueOf(getMuscle()), BodySize.valueOf(getBodySize()));
	}
	
	public BodyMaterial getBodyMaterial() {
		return bodyMaterial;
	}
	
	public boolean setBodyMaterial(BodyMaterial bodyMaterial) {
		if(this.bodyMaterial == bodyMaterial) {
			return false;
		}
		
		addDiscoveredBodyCoveringsFromMaterial(bodyMaterial);
		
		this.bodyMaterial = bodyMaterial;
		
		return true;
	}

	public GenitalArrangement getGenitalArrangement() {
		return genitalArrangement;
	}

	public void setGenitalArrangement(GenitalArrangement genitalArrangement) {
		this.genitalArrangement = genitalArrangement;
	}

	public boolean isFeral() {
		return feral;
	}

	public boolean isFeralOrHasLegConfiguration(LegConfiguration... values) {
		return feral || leg.getLegConfiguration().isOneOf(values);
	}

	/**
	 * @param subspecies Pass in the AbstractSubspecies to which this character should be transformed into a feral version of. Pass in null to transform back from feral to a standard anthro.
	 */
	public void setFeral(GameCharacter target, AbstractSubspecies subspecies) {
		LegConfiguration initialLegConfiguration = target.getLegConfiguration(); // For use in pregnant feral reversion (into taur)
		
		AbstractSubspecies targetSubspecies = subspecies == null ? getSubspecies() : subspecies;
		FeralAttributes attributes = targetSubspecies.getFeralAttributes(this);
		if(attributes==null) {
			System.err.println("Error in Body.setFeral(): subspecies '"+Subspecies.getIdFromSubspecies(targetSubspecies)+"' does not support FeralAttributes!");
			return;
		}
		
		this.feral = subspecies!=null;
		// Set body to full subspecies:
		Main.game.getCharacterUtils().reassignBody(
				target,
				this,
				this.getGender(),
				targetSubspecies,
				RaceStage.GREATER,
				false);
		
		if (subspecies == null) {
			// If the target is pregnant as a feral, then feral reversion should always go into the tauric (or equivalent) lower body to better handle taur offspring
			if(target.isPregnant() && target.getLegConfiguration()!=initialLegConfiguration) {
				target.setLegConfiguration(initialLegConfiguration, true);
			}
			
			return; 
		}
		
		attributes.applySpecialPreFeralTransformationChanges(this);
		
		// Set feral-specific attributes:
		this.getLeg().getType().applyLegConfigurationTransformation(this, attributes.getLegConfiguration(), true);
		
//		this.getLeg().setLegConfigurationForced(this.getLeg().getType(), attributes.getLegConfiguration());
		this.setHeight(attributes.getSize());
		this.getLeg().setLengthAsPercentageOfHeight(null, attributes.getSerpentTailLength());
		
		// Set breast and crotch-boob counts:
		this.getBreast().setRows(null, attributes.getBreastRowCount());
		this.getBreast().setNippleCountPerBreast(null, attributes.getNipplesPerBreastCount());
		this.getBreastCrotch().setRows(null, attributes.getCrotchBreastRowCount());
		this.getBreastCrotch().setNippleCountPerBreast(null, attributes.getNipplesPerCrotchBreastCount());
		if(attributes.getCrotchBreastRowCount() == 0) {
			this.getBreastCrotch().setType(null, BreastType.NONE);
		}
		
		// Set genital relative sizes:
		AbstractRacialBody rb = targetSubspecies.getRace().getRacialBody();
		float proportionSizeDifference = ((float)attributes.getSize())/(this.isFeminine()?rb.getFemaleHeight():rb.getMaleHeight());
		if(attributes.getLegConfiguration().isLargeGenitals()) {
			proportionSizeDifference += 1; // If large genitals, increase by 100%
		}
		this.getPenis().setPenisLength(null, (int) (rb.getPenisSize()*proportionSizeDifference));
		this.getPenis().setPenisGirth(null, (int) (rb.getPenisGirth()*proportionSizeDifference));
		this.getPenis().getTesticle().setTesticleSize(null, (int) (rb.getTesticleSize()*proportionSizeDifference));
		
		// Set hair:
		this.getHair().setStyle(null, HairStyle.NONE);
		if(attributes.isHairPresent()) {
			this.getHair().setLength(null, HairLength.TWO_SHORT.getMedianValue());
		} else {
			this.getHair().setLength(null, 0);
		}
		
		removeAllMakeup();
		
		CharacterModificationUtils.resetCoveringsToBeApplied();
	}

	public void removeAllMakeup() {
		for(AbstractBodyCoveringType makeup : BodyCoveringType.allMakeupTypes) {
			if(coverings.containsKey(makeup)) {
				coverings.put(makeup, new Covering(makeup, CoveringPattern.NONE, CoveringModifier.SMOOTH, PresetColour.COVERING_NONE, false, PresetColour.COVERING_NONE, false));
			}
		}
		CharacterModificationUtils.resetCoveringsToBeApplied();
	}
	
	public boolean isPiercedStomach() {
		return piercedStomach;
	}

	public void setPiercedStomach(boolean piercedStomach) {
		this.piercedStomach = piercedStomach;
	}

	public Set<AbstractBodyCoveringType> getHeavyMakeup() {
		return heavyMakeup;
	}

	public boolean isHeavyMakeup(AbstractBodyCoveringType type) {
		return heavyMakeup.contains(type);
	}
	
	public void addHeavyMakeup(AbstractBodyCoveringType type) {
		heavyMakeup.add(type);
	}
	
	public boolean removeHeavyMakeup(AbstractBodyCoveringType type) {
		return heavyMakeup.remove(type);
	}

	public Map<AbstractBodyCoveringType, Covering> getCoverings() {
		return coverings;
	}

	/**
	 * This should only be used in special cases, where the covering map needs to be overwritten for some reason!
	 */
	public void setCoverings(Map<AbstractBodyCoveringType, Covering> coverings) {
		this.coverings = coverings;
	}
	
	public String setCovering(AbstractBodyCoveringType coveringType, Covering newCovering) {
		return setCovering(null, coveringType, newCovering);
	}
	public String setCovering(GameCharacter target, AbstractBodyCoveringType coveringType, Covering newCovering) {
		StringBuilder sb = new StringBuilder();
		try {
			if(Main.game.isStarted() && target!=null) {
				Covering initialCovering = target.getCovering(coveringType);
				if(!initialCovering.equals(newCovering)) {
					sb.append("<p>");
						sb.append(UtilText.parse(target,
								"тело [npc.NamePos] начинает чесаться, и [npc.she] [npc.verb(let)] испуганный крик, когда [npc.her] "+coveringType.getName(target)+" стремительно "+(coveringType.isDefaultPlural()?"меняют":"меняет")+" цвет.<br/>[npc.She] теперь [npc.has] "+newCovering.getFullDescription(target, true)+"."));
					sb.append("</p>");
				}
			}
		} catch(Exception ex) {
			// Safely catch an exception that can be thrown when  creating a new GameCharacter - the character will call this method before initialisation of the Body is finished, which results in the following errors:
			/*
			Exception in thread "JavaFX Application Thread" java.lang.NullPointerException
				at com.lilithsthrone.game.character.GameCharacter.getBodyMaterial(GameCharacter.java:28013)
				at com.lilithsthrone.game.character.GameCharacter.getCovering(GameCharacter.java:31010)
				at com.lilithsthrone.game.character.body.Body.setCovering(Body.java:6697)
			*/
		}
		coverings.put(coveringType, newCovering);
		
		return sb.toString();
	}
	
	public String setCovering(AbstractBodyCoveringType coveringType, CoveringPattern pattern, CoveringModifier modifier, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		return setCovering(null, coveringType, pattern, modifier, primaryColor, primaryGlow, secondaryColor, secondaryGlow);
	}
	public String setCovering(GameCharacter target, AbstractBodyCoveringType coveringType, CoveringPattern pattern, CoveringModifier modifier, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		return setCovering(target, coveringType, new Covering(coveringType, pattern, modifier, primaryColor, primaryGlow, secondaryColor, secondaryGlow));
	}

	public String setCovering(AbstractBodyCoveringType coveringType, CoveringPattern pattern, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		return setCovering(null, coveringType, pattern, primaryColor, primaryGlow, secondaryColor, secondaryGlow);
	}
	public String setCovering(GameCharacter target, AbstractBodyCoveringType coveringType, CoveringPattern pattern, Colour primaryColor, boolean primaryGlow, Colour secondaryColor, boolean secondaryGlow) {
		return setCovering(target, coveringType, new Covering(coveringType, pattern, primaryColor, primaryGlow, secondaryColor, secondaryGlow));
	}

	public AbstractBodyCoveringType getCoveringType(BodyPartInterface bodyPart) {
		return bodyPart.getBodyCoveringType(this);
	}

	public Covering getCoveringFromType(BodyPartInterface bodyPart) {
		return getCovering(bodyPart.getBodyCoveringType(this), false);
	}
	
	public Covering getCovering(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		if(!accountForNonFleshMaterial) {
			return this.getCoverings().get(bodyCoveringType);
		}
		AbstractBodyCoveringType handledType = this.getBodyMaterial()!=BodyMaterial.FLESH
												?BodyCoveringType.getMaterialBodyCoveringType(this.getBodyMaterial(), bodyCoveringType.getCategory())
												:bodyCoveringType;
		return this.getCoverings().get(handledType);
	}

	public CoveringModifier getCoveringModifier(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		return getCovering(bodyCoveringType, accountForNonFleshMaterial).getModifier();
	}

	public void setCoveringModifier(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial, CoveringModifier modifier) {
		getCovering(bodyCoveringType, accountForNonFleshMaterial).setModifier(modifier);
	}

	public CoveringPattern getCoveringPattern(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial) {
		return getCovering(bodyCoveringType, accountForNonFleshMaterial).getPattern();
	}

	public void setCoveringPattern(AbstractBodyCoveringType bodyCoveringType, boolean accountForNonFleshMaterial, CoveringPattern pattern) {
		getCovering(bodyCoveringType, accountForNonFleshMaterial).setPattern(pattern);
	}

	public boolean isBodyCoveringTypesDiscovered(AbstractBodyCoveringType bct) {
		return coveringsDiscovered.contains(bct);
	}

	public boolean addBodyCoveringTypesDiscovered(AbstractBodyCoveringType bct) {
		return coveringsDiscovered.add(bct);
	}
	
//	public Set<BodyCoveringType> getBodyCoveringTypesDiscovered() {
//		return coveringsDiscovered;
//	}
	
	/**
	 * Updates this body's covering to more realistic values.
	 * @param updateEyes If true, human eyes will be set to non-heterochromatic.
	 * @param updateHair If true, human hair and body hair will be set to unpatterned.
	 * @param updateBodyHairColours If true, the colour of all body hair values will be set to the matching colour of the body's head hair.
	 * @param updateSkin If true, all values for anus, lips, vagina, nipples, and penis will be set to the matching colour of the body's skin.
	 */
	public void updateCoverings(boolean updateEyes, boolean updateHair, boolean updateBodyHairColours, boolean updateSkin) {
		
//		if(this.getBodyMaterial()==BodyMaterial.SLIME) {
//			coverings.put(BodyCoveringType.SLIME, new Covering
//					(BodyCoveringType.SLIME, coverings.get(BodyCoveringType.SLIME).getPattern(), coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false, coverings.get(BodyCoveringType.SLIME).getPrimaryColour(), false));
//		}
		
		// Make eyes normal:
		if(updateEyes) {
			coverings.put(BodyCoveringType.EYE_HUMAN, new Covering
					(BodyCoveringType.EYE_HUMAN, CoveringPattern.EYE_IRISES, coverings.get(BodyCoveringType.EYE_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.EYE_HUMAN).getPrimaryColour(), false));
		}
		
		// Make hair non-highlighted:
		if(updateHair) {
			coverings.put(BodyCoveringType.HAIR_HUMAN, new Covering
					(BodyCoveringType.HAIR_HUMAN, CoveringPattern.NONE, coverings.get(BodyCoveringType.HAIR_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.HAIR_HUMAN).getPrimaryColour(), false));
	
			coverings.put(BodyCoveringType.BODY_HAIR_HUMAN, new Covering
					(BodyCoveringType.BODY_HAIR_HUMAN, CoveringPattern.NONE, coverings.get(BodyCoveringType.BODY_HAIR_HUMAN).getPrimaryColour(), false, coverings.get(BodyCoveringType.BODY_HAIR_HUMAN).getPrimaryColour(), false));
			
		}
		
		if(updateBodyHairColours) {
			for(AbstractRace race : Race.getAllRaces()) {
				if(!HairType.getHairTypes(race).isEmpty()) {
					coverings.put(race.getRacialBody().getBodyHairType(), new Covering(race.getRacialBody().getBodyHairType(), coverings.get(HairType.getHairTypes(race).get(0).getBodyCoveringType(this)).getPrimaryColour()));
				}
			}
		}
		
		// Make all orifice colours the same as their surroundings:
		if(updateSkin) {
			for(BodyMaterial mat : BodyMaterial.values()) { // Update all non-flesh parts to be the same colour as main skin:
				if(mat!=BodyMaterial.FLESH) {
					AbstractBodyCoveringType coreSlimeCovering = BodyCoveringType.getMaterialBodyCoveringType(mat, BodyCoveringCategory.MAIN_SKIN);
					Covering currentCovering = this.getCovering(coreSlimeCovering, true);
					
					for(BodyCoveringCategory cat : BodyCoveringCategory.values()) {
						if(cat.isInfluencedByMaterialType()) {
							AbstractBodyCoveringType nonFleshCovering = BodyCoveringType.getMaterialBodyCoveringType(mat, cat);
							CoveringPattern pattern = currentCovering.getPattern();
							if(!nonFleshCovering.getAllPatterns().keySet().contains(pattern)) {
								pattern = nonFleshCovering.getNaturalPatterns().entrySet().iterator().next().getKey();
							}
							CoveringModifier modifier = currentCovering.getModifier();
							if(!nonFleshCovering.getAllModifiers().contains(modifier)) {
								modifier = nonFleshCovering.getNaturalModifiers().get(0);
							}
							
							coverings.put(nonFleshCovering,
									new Covering(nonFleshCovering,
											pattern, //nonFleshCovering.getNaturalPatterns().entrySet().iterator().next().getKey(),
											modifier,
											coverings.get(coreSlimeCovering).getPrimaryColour(),
											false,
											coverings.get(coreSlimeCovering).getPrimaryColour(),
											false));
						}
					}
				}
			}
			
			updateAnusColouring();

			updateNippleColouring();
			
			updateNippleCrotchColouring();

			updateMouthColouring();

			updateVaginaColouring();
			
			updatePenisColouring();
			
			updateSpinneretColouring();
		}
	}
	
	public void updateAnusColouring() {
		if(ass.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(ass.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.ANUS, new Covering(BodyCoveringType.ANUS, CoveringPattern.ORIFICE_ANUS, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateNippleColouring() {
		if(breast.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(breast.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.NIPPLES, new Covering(BodyCoveringType.NIPPLES, CoveringPattern.ORIFICE_NIPPLE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateNippleCrotchColouring() {
		if(breastCrotch.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(breastCrotch.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.NIPPLES_CROTCH,
					new Covering(BodyCoveringType.NIPPLES_CROTCH, CoveringPattern.ORIFICE_NIPPLE_CROTCH, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateMouthColouring() {
		if(face.getType().getRace()==Race.ANGEL) {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(face.getType().getRace()==Race.DEMON) {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.MOUTH, new Covering(BodyCoveringType.MOUTH, CoveringPattern.ORIFICE_MOUTH, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updateVaginaColouring() {
		AbstractRace race = vagina.getType()!=VaginaType.NONE?vagina.getType().getRace():getRace();
		if(race==Race.ANGEL) {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DEMON) {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.VAGINA, new Covering(BodyCoveringType.VAGINA, CoveringPattern.ORIFICE_VAGINA, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}
	
	public void updatePenisColouring() {
		AbstractRace race = penis.getType()!=PenisType.NONE?penis.getType().getRace():getRace();
		if(race==Race.ANGEL) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.ANGEL).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DEMON) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else if(race==Race.DOG_MORPH || race==Race.WOLF_MORPH || race==Race.FOX_MORPH) {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, PresetColour.SKIN_RED, false, PresetColour.ORIFICE_INTERIOR, false));
			
		} else {
			coverings.put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, CoveringPattern.NONE, coverings.get(BodyCoveringType.HUMAN).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
		}
	}

	public void updateSpinneretColouring() {
		coverings.put(BodyCoveringType.SPINNERET, new Covering(BodyCoveringType.SPINNERET, CoveringPattern.ORIFICE_SPINNERET, coverings.get(BodyCoveringType.VAGINA).getPrimaryColour(), false, PresetColour.ORIFICE_INTERIOR, false));
	}
	
	public boolean isAbleToFlyFromArms() {
		if(this.getBodyMaterial()==BodyMaterial.SLIME
				|| this.getBodyMaterial()==BodyMaterial.SILICONE
				|| this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue()>WingSize.THREE_LARGE.getValue()) {
			return false;
		}
		return arm.getType().allowsFlight();
	}
	
	public boolean isAbleToFlyFromWings() {
		if(this.getBodyMaterial()==BodyMaterial.SLIME || this.getBodyMaterial()==BodyMaterial.SILICONE) {
			return false;
		}
		return wing.getType().allowsFlight() && wing.getSize().getValue()>=this.getLeg().getLegConfiguration().getMinimumWingSizeForFlight(this).getValue();
	}

	public boolean isAbleToFlyFromExtraParts() {
		return getAllBodyParts().stream().anyMatch(bpi -> bpi.getType().getTags().contains(BodyPartTag.ALLOWS_FLIGHT));
	}

	/**
	 * Basically the opposite of isAbleToFly, but skips the minimumWingSizeForFlight-checks
	 * and the checks for slime bodies.
	 * @return true if the body is incapable of flight
	 */
	public Boolean isInCapableOfFlight() {
		return !(
			isAbleToFlyFromExtraParts() ||
			arm.getType().allowsFlight() ||
			wing.getType().allowsFlight());
	}

	public boolean isTakesAfterMother() {
		return takesAfterMother;
	}

	public void setTakesAfterMother(boolean takesAfterMother) {
		this.takesAfterMother = takesAfterMother;
	}

	/**
	 * Returns a randomly chosen BodyPart-type from the list of types (e. g. multiple WingTypes)
	 * provided as parameters. To give one or more types more weight these types can be repeated.
	 * @param values List of BodyPartTypes to choose from randomly
	 * @return The randomly chosen type of the corresponding BodyPart
	 */
	public BodyPartTypeInterface randomTypeFrom(BodyPartTypeInterface... values) {
		return Util.randomItemFrom(Util.newArrayListOfValues(values));
	}

	/**
	 * This is reset to null after every transformation, and is then recalculated in AbstractSubspecies.
	 * @return The subspecies which this character appears to be if they were made of flesh.
	 *  Use getTrueSubspecies() or do some checks with getSubspeciesOverride() to get their true Subspecies, but for 99.9% of the time, that won't be necessary and this method is fine to use.
	 */
	public AbstractSubspecies getFleshSubspecies() {
		if(fleshSubspecies==null) {
			fleshSubspecies = AbstractSubspecies.getSubspeciesFromBody(this, this.getRaceFromPartWeighting());
		}
		return fleshSubspecies;
	}

	public void setFleshSubspecies(AbstractSubspecies fleshSubspecies) {
		this.fleshSubspecies = fleshSubspecies;
	}

}
