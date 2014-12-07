package de.bitdroid.adap.model;


import com.google.common.collect.Sets;

import org.wahlzeit.utils.Assert;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * This factory allows the creation of new {@link FrogType} instances.
 * As those created instances are immutable, this factory also features a number of common frog
 * creation methods that will return the same instance when invoked twice.
 */
public final class FrogTypeFactory {


	private static final Map<String,  FrogType> frogTypes = new HashMap<>();


	static {
		FrogType type = new FrogType(
				"African Bullfrog",
				"Pyxicephalus adsperus",
				EnumSet.of(Food.FISH, Food.MICE, Food.FROGS, Food.LIZARDS),
				new NumberRange.Builder<>(12.0, 24.0).startInclusive(true).endInclusive(true).build(),
				Sets.newHashSet(
						AreaCache.getInstance().getCircularArea(new GpsLocation(-19.0169211, 29.1528018), 200),
						AreaCache.getInstance().getCircularArea(new GpsLocation(0.1768696, 37.9083264), 250),
						AreaCache.getInstance().getRectangularArea(new GpsLocation(-22.967062, 18.4929993), 400, 1000))
				);
		frogTypes.put(type.getCommonName(), type);

		type = new FrogType(
				"Banded Bullfrog",
				"Kaloula pulchra",
				EnumSet.of(Food.FLIES, Food.CRICKETS, Food.MOTHS, Food.GRASSHOPPERS, Food.MEALWORMS, Food.BUTTERWORMS, Food.WAXWORMS, Food.EARTHWORMS),
				new NumberRange.Builder<>(7.0, 8.0).startInclusive(true).endInclusive(true).build(),
				Sets.newHashSet(
						AreaCache.getInstance().getCircularArea(new GpsLocation(21.1289956, 82.7792201), 1000),
						AreaCache.getInstance().getCircularArea(new GpsLocation(13.03887, 101.490104), 300),
						AreaCache.getInstance().getRectangularArea(new GpsLocation(-7.3281874, 109.9076899), 1000,120))
				);
		frogTypes.put(type.getCommonName(), type);

		type = new FrogType(
				"American Green Tree Frog",
				"Hyla cinerea",
				EnumSet.of(Food.FLIES, Food.CRICKETS, Food.MOTHS),
				new NumberRange.Builder<>(6.0, 6.3).startInclusive(true).endInclusive(true).build(),
				Sets.newHashSet(
						AreaCache.getInstance().getRectangularArea(new GpsLocation(35.7675961, -89.4247656), 3000, 1000))
		);
		frogTypes.put(type.getCommonName(), type);
	}


	/**
	 * @param commonName the common name of the frog to search for.
	 * @return a frog type associated with this common name.
	 */
	public static FrogType getFrogTypeByCommonName(String commonName) {
		Assert.assertNotNull(commonName);
		return frogTypes.get(commonName);
	}


	/**
	 * @return all known frog types.
	 */
	public static Collection<FrogType> getAllFrogTypes() {
		return frogTypes.values();
	}


	private FrogTypeFactory() { }

}
