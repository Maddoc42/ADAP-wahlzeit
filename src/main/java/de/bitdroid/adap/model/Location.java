package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public interface Location {

	/**
	 * Computes the distance between this location and another.
	 * @param location Must be of the same type as this location! TODO not very pretty ...
	 * @return the distance between this location and the received location.
	 */
	public double computeDistanceTo(Location location);

	/**
	 * Used by the visitor pattern of {@link de.bitdroid.adap.model.LocationVisitor}
	 * @param visitor The visitor to execute.
	 * @param param The parameter to pass to the visitor.
	 * @return The result of the visitor.
	 */
	public <P,R> R accept(LocationVisitor<P,R> visitor, P param);

}
