package de.bitdroid.adap.model;


import com.google.common.collect.Sets;

import java.util.EnumSet;
import java.util.Set;

/**
 * This factory allows the creation of new {@link de.bitdroid.adap.model.Frog} instances.
 * As those created instances are immutable, this factory also features a number of common frog
 * creation methods that will return the same instance when invoked twice.
 */
public final class FrogFactory {

	private static final Frog AFRICAN_BULLFROG;
	private static final Frog BANDED_BULLFROG;
	private static final Frog AMERICAN_GREEN_TREE_FROG;

	static {
		AFRICAN_BULLFROG = new Frog(
				"African Bullfrog",
				"Pyxicephalus adsperus",
				EnumSet.of(Food.FISH, Food.MICE, Food.FROGS, Food.LIZARDS),
				new NumberRange<>(12.0, true, 24.0, true),
				Sets.newHashSet(
						AreaFactory.createCircularArea(new GpsLocation(-19.0169211, 29.1528018), 200),
						AreaFactory.createCircularArea(new GpsLocation(0.1768696, 37.9083264), 250),
						AreaFactory.createRectangularArea(new GpsLocation(-22.967062, 18.4929993), 400, 1000))
				);

		BANDED_BULLFROG = new Frog(
				"Banded Bullfrog",
				"Kaloula pulchra",
				EnumSet.of(Food.FLIES, Food.CRICKETS, Food.MOTHS, Food.GRASSHOPPERS, Food.MEALWORMS, Food.BUTTERWORMS, Food.WAXWORMS, Food.EARTHWORMS),
				new NumberRange<>(7.0, true, 8.0, true),
				Sets.newHashSet(
						AreaFactory.createCircularArea(new GpsLocation(21.1289956, 82.7792201), 1000),
						AreaFactory.createCircularArea(new GpsLocation(13.03887, 101.490104), 300),
						AreaFactory.createRectangularArea(new GpsLocation(-7.3281874, 109.9076899), 1000,120))
				);

		AMERICAN_GREEN_TREE_FROG = new Frog(
				"American Green Tree Frog",
				"Hyla cinerea",
				EnumSet.of(Food.FLIES, Food.CRICKETS, Food.MOTHS),
				new NumberRange<>(6.0, true, 6.3, true),
				Sets.newHashSet(
						AreaFactory.createRectangularArea(new GpsLocation(35.7675961, -89.4247656), 3000, 1000))
		);
	}


	/**
	 * @return a instance of an African Bullfrog, see <a href="https://en.wikipedia.org/wiki/African_bullfrog">Wikipedia</a>.
	 */
	public static Frog createAfricanBullfrog() {
		return AFRICAN_BULLFROG;
	}


	/**
	 * @return a instance of an Banded Bullfrog, see <a href="https://en.wikipedia.org/wiki/Banded_bull_frog">Wikipedia</a>.
	 */
	public static Frog createBandedBullfrog() {
		return BANDED_BULLFROG;
	}


	/**
	 * @return a instance of an American Green Frog, see <a href="https://en.wikipedia.org/wiki/American_green_tree_frog">Wikipedia</a>.
	 */
	public static Frog createAmericanGreenTreeFrog() {
		return AMERICAN_GREEN_TREE_FROG;
	}


	/**
	 * Creates a new completely customizable frog instance. See {@link de.bitdroid.adap.model.Frog} for details.
	 */
	public static Frog createFrog(
			String commonName,
			String scientificName,
			EnumSet<Food> diet,
			NumberRange<Double> sizeRange,
			Set<Area> habitats) {

		return new Frog(commonName, scientificName, diet, sizeRange, habitats);
	}


	private FrogFactory() { }

}
