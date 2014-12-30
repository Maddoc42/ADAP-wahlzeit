package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public interface Location {

	public double computeDistanceTo(Location location);
	public <P,R> R accept(LocationVisitor<P,R> visitor, P param);

}
