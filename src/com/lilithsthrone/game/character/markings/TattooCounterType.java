package com.lilithsthrone.game.character.markings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

/**
 * @since 0.2.6
 * @version 0.3.7.2
 * @author Innoxia
 */
public enum TattooCounterType {
	
	NONE("none", "Не добавлять счетчик к этой татуировке.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return 0;
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},

	
	VALUE_AS_SLAVE("цена как рабыни", "Показывает, сколько стоит обладательница как рабыня.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getValueAsSlave(false); 
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},
	
	SEX_SUB("саб-секс", "Считает, сколько раз обладательница занималась подчиняющим сексом.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalSexAsSubCount();
		}
	},

	SEX_DOM("дом-секс", "Считает, сколько раз обладательница занималась доминирующим сексом.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalSexAsDomCount();
		}
	},

	UNIQUE_SEX_PARTNERS("уникальные партнеры", "Считает количество разных людей, с которыми занималась сексом обладательница.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getUniqueSexPartnerCount();
		}
	},

	PUSSY_FUCKED("проникновение в киску", "Считает количество проникновений в киску носительницы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.VAGINA);
		}
	},

	ANUS_FUCKED("проникновений в анус", "Считает количество проникновений в анус носительницы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.ANUS);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	
	ORAL_FUCKED("проникновений в рот", "Считает количество проникновений в рот носительницы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.MOUTH);
		}
	},
	
	NIPPLES_FUCKED("проникновений в соски", "Считает количество проникновений в соски носительницы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.NIPPLE);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},
	
	NIPPLES_CROTCH_FUCKED("проникновений в соски на лобке", "Считает количество проникновений в соски на лобке носительницы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	PENIS_PENETRATIONS("проникновений в пенис", "Ведет счет тому, сколько раз пенис носителя проникал в отверстия.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.PENIS);
		}
	},

	TAIL_PENETRATIONS("проникновений хвостом", "Ведет счет тому, сколько раз хвост носителя проникал в отверстия.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.TAIL);
		}
	},

	TENTACLE_PENETRATIONS("проникновений щупальцами", "Ведет счет тому, сколько раз щупальца носителя проникали в отверстия.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.TENTACLE);
		}
	},
	
	CUM_GIVEN("всего сделанных кримпи", "Ведет счет тому, сколько раз носитель кончал внутрь кого-либо.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCount(true, false);
		}
	},

	CUM_GIVEN_PUSSY("сделанных в киску кримпи", "Ведет счет тому, сколько раз носитель кончал в чью-либо киску.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.VAGINA, true, false);
		}
	},

	CUM_GIVEN_ANUS("сделанных в анус кримпи", "Считает, сколько раз обладатель кончал кому-то в задницу.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.ANUS, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},

	CUM_GIVEN_ORAL("сделано оральных загрузок", "Считает, сколько раз обладатель кончал кому-то в глотку.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.MOUTH, true, false);
		}
	},

	CUM_GIVEN_NIPPLES("сделано кремпи в соски", "Считает, сколько раз обладатель кончал кому-то на соски.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	CUM_GIVEN_NIPPLES_CROTCH("сделано кремпи в пах-соски", "Считает, сколько раз обладатель кончал кому-то в пах-соски.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE_CROTCH, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	CUM_GIVEN_FEET("получено фидджобов", "Считает, сколько раз обладатель кончал на ноги партнёру.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaPenetration.FOOT, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isFootContentEnabled();
		}
	},
	
	CUM_TAKEN("всего получено кремпи", "Считает, сколько раз в отверстия носителя попадала порция спермы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCount(false, true);
		}

	},

	CUM_TAKEN_PUSSY("полученных вагинальных кримплеев", "Считает, сколько раз в киску носителя попадала порция спермы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.VAGINA, false, true);
		}
	},

	CUM_TAKEN_ANUS("полученных анальных кримплеев", "Считает, сколько раз в задницу носителя попадала порция спермы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.ANUS, false, true);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},

	CUM_TAKEN_ORAL("проглоченных порций", "Считает, сколько раз носитель проглатывал порцию спермы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.MOUTH, false, true);
		}
	},

	CUM_TAKEN_NIPPLES("полученных сосковых кримплеев", "Считает, сколько раз на соски носителя попадала порция спермы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE, false, true);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	CUM_TAKEN_NIPPLES_CROTCH("полученных кримплеев в пах и соски", "Считает, сколько раз носительница принимала порцию спермы в свои соски-клиторы.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE_CROTCH, false, true);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	CUM_TAKEN_FEET("количество футджоб-оргазмов", "Считает, сколько раз партнер кончал носителю на ноги.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaPenetration.FOOT, false, true); 
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isFootContentEnabled();
		}
	},
	

	VIRGINITIES_TAKEN_TOTAL("всего лишений девственности", "Считает, сколько девственных пленок всех типов (вагинальных, анальных, оральных, сосковых, уретральных) лишила носительница.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
						if(orifice.isInternalOrifice()) {
							for(SexAreaPenetration pen : SexAreaPenetration.values()) {
								if(pen.isTakesVirginity()
										&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen))!=null
										&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen)).getKey().equals(bearer.getId())) {
									count++;
									break;
								}
							}
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					if(orifice.isInternalOrifice()) {
						for(SexAreaPenetration pen : SexAreaPenetration.values()) {
							if(pen.isTakesVirginity()
									&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen))!=null
									&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen)).getKey().equals(bearer.getId())) {
								count++;
								break;
							}
						}
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_VAGINAL("вагинальные лишения девственности", "Считает, сколько вагинальных девственниц лишила носительница.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_ANAL("анальные лишения девственности", "Считает, сколько анальных девственниц лишила носительница.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	
	VIRGINITIES_TAKEN_ORAL("оральные лишения девственности", "Ведет счет тому, сколько оральных девственниц лишил носитель.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_PENIS("пенисная дефлорация", "Ведет счет тому, сколько пенисных девственниц лишил носитель.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
						if(orifice.isInternalOrifice()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					if(orifice.isInternalOrifice()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
	},

	CURRENT_PREGNANCY("размер помета", "Считает, сколько детей сейчас носит в себе носитель.") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(bearer.getPregnantLitter()==null) {
				return 0;
			}
			return bearer.getPregnantLitter().getTotalLitterCount();
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},

	OFFSPRING_BIRTHED("рожденное потомство", "Считает, сколько детей родил носитель.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersBirthed()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	OFFSPRING_FATHERED("отец потомства", "Считает, скольких детей произвел на свет носитель.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersFathered()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},
	
	PREGNANCY("pregnancy", "Ведет счет тому, сколько раз носитель был зачат.") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getLittersBirthed().size() + (bearer.isPregnant()?1:0);
		}
	},

	PREGNANCY_PARTNERS("партнеры по беременности", "Ведет учет количества уникальных партнеров, от которых носительница забеременела.") {
		@Override
		public int getCount(GameCharacter bearer) {
			Set<String> partners = new HashSet<>();
			for(Litter litter : bearer.getLittersBirthed()) {
				partners.add(litter.getFatherId());
			}
			return partners.size();
		}
	},
	
	IMPREGNATIONS("impregnation", "Ведет учет количества раз, когда носительница кого-то оплодотворяла.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int potentials = 0;
			for(PregnancyPossibility pp : bearer.getPotentialPartnersAsFather()) {
				if(pp.getMother()!=null && pp.getMother().isPregnant() && Objects.equals(pp.getFather(), bearer)) {
					potentials++;
				}
			}
			return potentials + bearer.getLittersFathered().size();
		}
	},

	IMPREGNATION_PARTNERS("партнеры по оплодотворению", "Ведет учет количества уникальных партнеров, которых оплодотворила носительница.") {
		@Override
		public int getCount(GameCharacter bearer) {
			Set<String> potentials = new HashSet<>();
			for(PregnancyPossibility pp : bearer.getPotentialPartnersAsFather()) {
				if(pp.getMother()!=null && pp.getMother().isPregnant() && Objects.equals(pp.getFather(), bearer)) {
					potentials.add(pp.getMotherId());
				}
			}
			Set<String> partners = new HashSet<>();
			for(Litter litter : bearer.getLittersFathered()) {
				partners.add(litter.getMotherId());
			}
			return potentials.size() + partners.size();
		}
	},

	EGGS_IMPLANTED("внедренных яиц", "Ведет учет количества яиц, которые носительница внедрила в других.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersImplanted()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	EGGS_INCUBATED("инкубированных яиц", "Ведет учет количества внедренных яиц, которые носительница полностью инкубировала и отложила.") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersIncubated()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	CUM_IN_VAGINA("сперма в утробе", "Показывает количество спермы в утробе носителя (в "+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+").") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.VAGINA));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.VAGINA)));
			}
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},
	
	CUM_IN_ASS("сперме в заднице", "Показывает количество спермы в заднице носителя (в "+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+").") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.ANUS));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.ANUS)));
			}
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},
	
	CUM_IN_STOMACH("сперме в заднице", "Показывает количество спермы в желудке носителя (в "+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+").") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.MOUTH));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.MOUTH)));
			}
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	};
	
	private String name;
	private String description;
	
	private TattooCounterType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract int getCount(GameCharacter bearer);

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public boolean isRetroactiveApplicationAvailable() {
		return true;
	}

	/**
	 * @return The negative int which should be applied in order to start this TattooCounter at 0 (i.e. a value for removing retroactive application)
	 */
	public int getNonRetroactiveOffset(GameCharacter bearer) {
		if(!isRetroactiveApplicationAvailable()) {
			return 0;
		}
		return -getCount(bearer);
	}
	
	public boolean isAvailable() {
		return true;
	}
	
	public static List<TattooCounterType> getTattooCounterTypesWithContentFiltersApplied() {
		List<TattooCounterType> returnList = new ArrayList<>();
		for(TattooCounterType tct : TattooCounterType.values()) {
			if(tct.isAvailable()) {
				returnList.add(tct);
			}
		}
		return returnList;
	}
}
