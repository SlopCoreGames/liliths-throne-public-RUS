package com.lilithsthrone.game.character.body.valueEnums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.4
 * @author Innoxia
 */
public enum PenetrationModifier {

	SHEATHED("sheathed", "Помогает скрыть выпуклость под одеждой, когда член не в состоянии эрекции."),
	
	RIBBED("ribbed", ""),
	
	TENTACLED("tentacled", ""),
	
	KNOTTED("knotted", "Дает возможность проталкиваться внутрь отверстия в момент оргазма, удваивая эффективный диаметр и намертво сцепляя партнеров. (Требуется, чтобы отверстие было достаточно глубоким для вхождения узловатого основания.)"),
	
	BLUNT("blunt", ""),

	TAPERED("tapered", "Уменьшает эффективный диаметр на 5%. (Взаимоисключающее с «расширяющимся».)") {
		@Override
		public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
			return Util.newArrayListOfValues(FLARED);
		}
	},
	
	FLARED("flared", "Увеличивает эффективный диаметр на 5%. (Взаимоисключающее с «сужающимся».)") {
		@Override
		public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
			return Util.newArrayListOfValues(TAPERED);
		}
	},
	
	BARBED("barbed", ""),
	
	VEINY("veiny", ""),
	
	PREHENSILE("prehensile", ""),
	
	OVIPOSITOR("ovipositor",
			"Дает возможность откладывать яйца в текущее проникаемое отверстие. (Требуется, чтобы яйца уже были оплодотворены. Нельзя откладывать яйца во влагалище уже беременной цели.)",
			Util.newArrayListOfValues(
					SexAreaPenetration.PENIS,
					SexAreaPenetration.CLIT));
	
	private String name;
	private String description;
	private List<SexAreaPenetration> restrictedPenetrationTypes;

	private PenetrationModifier(String name, String description) {
		this(name, description, null);
	}
	
	private PenetrationModifier(String name, String description, List<SexAreaPenetration> restrictedPenetrationTypes) {
		this.name = name;
		this.description = description;
		this.restrictedPenetrationTypes = restrictedPenetrationTypes;
	}
	
	public static List<PenetrationModifier> getPenetrationModifiers() {
		return getPenetrationModifiers(null);
	}
	
	public static List<PenetrationModifier> getPenetrationModifiers(SexAreaPenetration penetrationType) {
		List<PenetrationModifier> penetrationModifiers = new ArrayList<>(Arrays.asList(PenetrationModifier.values()));
		penetrationModifiers.removeIf(pm->pm.getRestrictedPenetrationTypes()!=null && penetrationType!=null && !pm.getRestrictedPenetrationTypes().contains(penetrationType));
		return penetrationModifiers;
	}

	public List<PenetrationModifier> getMutuallyExclusivePenetrationModifiers() {
		return new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isSpecialEffects() {
		return !description.isEmpty();
	}
	
	public String getDescription() {
		if(description.isEmpty()) {
			return "Не влияет на игровой процесс.";
		}
		return description;
	}

	public List<SexAreaPenetration> getRestrictedPenetrationTypes() {
		return restrictedPenetrationTypes;
	}
}
