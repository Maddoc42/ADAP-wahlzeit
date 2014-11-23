package de.bitdroid.adap.model;


import com.google.common.collect.Sets;

import java.util.EnumSet;
import java.util.Set;

public final class FrogFactory {

	private static final Frog AFRICAN_BULLFROG;
	private static final Frog BANDED_BULLFROG;
	private static final Frog AMERICAN_GREEN_TREE_FROG;

	static {
		AFRICAN_BULLFROG = new Frog(
				"African Bullfrog",
				"Pyxicephalus adsperus",
				EnumSet.of(Animals.FISH, Animals.MICE, Animals.FROGS, Animals.LIZARDS),
				new NumberRange<>(12.0, true, 24.0, true),
				Sets.newHashSet(
						AreaFactory.createCircularArea(new GpsLocation(-19.0169211, 29.1528018), 200),
						AreaFactory.createCircularArea(new GpsLocation(0.1768696, 37.9083264), 250),
						AreaFactory.createRectangularArea(new GpsLocation(-22.967062, 18.4929993), 400, 1000))
				);

		BANDED_BULLFROG = new Frog(
				"Banded Bullfrog",
				"Kaloula pulchra",
				EnumSet.of(Animals.FLIES, Animals.CRICKETS, Animals.MOTHS, Animals.GRASSHOPPERS, Animals.MEALWORMS, Animals.BUTTERWORMS, Animals.WAXWORMS, Animals.EARTHWORMS),
				new NumberRange<>(7.0, true, 8.0, true),
				Sets.newHashSet(
						AreaFactory.createCircularArea(new GpsLocation(21.1289956, 82.7792201), 1000),
						AreaFactory.createCircularArea(new GpsLocation(13.03887, 101.490104), 300),
						AreaFactory.createRectangularArea(new GpsLocation(-7.3281874, 109.9076899), 1000,120))
				);

		AMERICAN_GREEN_TREE_FROG = new Frog(
				"American Green Tree Frog",
				"Hyla cinerea",
				EnumSet.of(Animals.FLIES, Animals.CRICKETS, Animals.MOTHS),
				new NumberRange<>(6.0, true, 6.3, true),
				Sets.newHashSet(
						AreaFactory.createRectangularArea(new GpsLocation(35.7675961, -89.4247656), 3000, 1000))
		);
	}


	public static Frog createAfricanBullfrog() {
		return AFRICAN_BULLFROG;
	}


	public static Frog createBandedBullfrog() {
		return BANDED_BULLFROG;
	}


	public static Frog createAmericanGreeTreenFrog() {
		return AMERICAN_GREEN_TREE_FROG;
	}


	public static Frog createFrog(
			String commonName,
			String scientificName,
			EnumSet<Animals> diet,
			NumberRange<Double> sizeRange,
			Set<Area> habitats) {

		return new Frog(commonName, scientificName, diet, sizeRange, habitats);
	}


	private FrogFactory() { }


}
