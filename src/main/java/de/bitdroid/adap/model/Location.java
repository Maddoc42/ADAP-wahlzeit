package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public interface Location extends LocationVisitable {

	/**
	 * Computes the distance between this location and another.
	 * @param location Must be of the same type as this location! TODO not very pretty ...
	 * @return the distance between this location and the received location.
	 */
	public double computeDistanceTo(Location location);

}
