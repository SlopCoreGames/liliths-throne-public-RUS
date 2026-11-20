package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.util.List;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.90
 * @version 0.2.11
 * @author Innoxia
 */
public class SlaveAuctionBidder {

	private String name;
	private AbstractSubspecies subspecies;
	private Gender gender;
	private List<String> biddingComments;
	private List<String> failedBidComments;
	private List<String> successfulBidComments;
	
	public SlaveAuctionBidder(AbstractSubspecies subspecies, Gender gender, List<String> biddingComments, List<String> failedBidComments, List<String> successfulBidComments) {
		super();
		this.subspecies = subspecies;
		this.gender = gender;
		this.biddingComments = biddingComments;
		this.failedBidComments = failedBidComments;
		this.successfulBidComments = successfulBidComments;
		
		if(gender.isFeminine()) {
			name = subspecies.getSingularFemaleName(null);
		} else {
			name = subspecies.getSingularMaleName(null);
		}
	}

	public String getName(boolean withDeterminer) {
		if(withDeterminer) {
			return UtilText.generateSingularDeterminer(name)+" "+name;
		}
		return name;
	}
	
	public AbstractSubspecies getRace() {
		return subspecies;
	}

	public Gender getGender() {
		return gender;
	}

	public List<String> getBiddingComments() {
		return biddingComments;
	}

	public String getRandomBiddingComment() {
		return biddingComments.get(Util.random.nextInt(biddingComments.size()));
	}
	
	public List<String> getFailedBidComments() {
		return failedBidComments;
	}

	public String getRandomFailedBiddingComment() {
		return failedBidComments.get(Util.random.nextInt(failedBidComments.size()));
	}
	
	public List<String> getSuccessfulBidComments() {
		return successfulBidComments;
	}
	
	public String getRandomSuccessfulBiddingComment() {
		return successfulBidComments.get(Util.random.nextInt(successfulBidComments.size()));
	}
	
	public static SlaveAuctionBidder generateNewSlaveAuctionBidder(NPC slave) {
		
		List<AbstractSubspecies> races = Util.newArrayListOfValues(
				Subspecies.CAT_MORPH,
				Subspecies.COW_MORPH, 
				Subspecies.DEMON,
				Subspecies.DOG_MORPH,
				Subspecies.HARPY,
				Subspecies.HORSE_MORPH,
				Subspecies.HUMAN,
				Subspecies.SQUIRREL_MORPH,
				Subspecies.WOLF_MORPH);
		
		List<Gender> genders = Util.newArrayListOfValues(Gender.F_V_B_FEMALE, Gender.F_P_V_B_FUTANARI, Gender.M_P_MALE);
		
		AbstractSubspecies race = Util.randomItemFrom(races);
		Gender gender = Util.randomItemFrom(genders);
		
		List<String> biddingComments = Util.newArrayListOfValues(
				"Мне нужна новая игрушка для траха...",
				"Моим рабыням нужна новая игрушка...",
				UtilText.parse(slave, "Я мог бы отправить [npc.herHim] работать в бордель..."),
				UtilText.parse(slave, "Я мог бы отправить [npc.herHim] работать в доильные сараи..."),
				UtilText.parse(slave, "[npc.She] выглядит как отличная служанка, если [npc.she]..."));
		
		List<String> failedBidComments = Util.newArrayListOfValues(
				"Мне это не по карману...",
				"Для меня это слишком дорого...",
				"Может, сделаю ставку на следующую...");
		
		List<String> successfulBidComments = Util.newArrayListOfValues(
				UtilText.parse(slave, "Как только приду домой, сразу начну приручать [npc.herHim]..."),
				UtilText.parse(slave, "Я заставлю своих остальных рабов приручить [npc.herHim]..."),
				UtilText.parse(slave, "Уверен, [npc.she] будет в восторге от [npc.her] новой жизни в моем борделе..."),
				UtilText.parse(slave, "Уверен, [npc.she] будет в восторге от [npc.her] новой жизни в доильных сараях..."));
		
		return new SlaveAuctionBidder(race, gender, biddingComments, failedBidComments, successfulBidComments);
	}
}
