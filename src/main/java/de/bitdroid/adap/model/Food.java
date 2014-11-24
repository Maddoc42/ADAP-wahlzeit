package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * A collection of common food items for frogs.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Food {
	MOTHS,
	FLIES,
	FROGS,
	CRICKETS,
	GRASSHOPPERS,
	MICE,
	WORMS,
	FISH,
	LIZARDS,
	MEALWORMS,
	EARTHWORMS,
	WAXWORMS,
	BUTTERWORMS
}
