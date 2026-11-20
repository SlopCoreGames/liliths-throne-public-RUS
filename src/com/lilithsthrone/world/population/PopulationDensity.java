package com.lilithsthrone.world.population;

/**
 * @since 0.2.12
 * @version 0.4.4.5
 * @author Innoxia
 */
public enum PopulationDensity {

	ONE("одинокий"),
	
	OCCASIONAL("редкий"),
	
	COUPLE("пара"),
	
	FEW("few"),
	
	TRIO("тройка"),
	
	SPARSE("sparse"),
	
	SEVERAL("several"),
	
	HALF_DOZEN("полдюжины"),
	
	DOZEN("дюжина"),

	DOZENS("десятки"),
	
	MANY("много"),
	
	NUMEROUS("numerous"),
	
	DENSE("dense"),
	
	SMALL("небольшое количество"),
	
	HUNDREDS("сотни"),
	
	THOUSANDS("тысячи");

	String name;
	
	private PopulationDensity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
