package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "class")
public interface Area {

	/**
	 * @return the center of this area
	 */
	public Location getCenter();

	/**
	 * @return the total size of this area in square kilometers
	 */
	public double calculateArea();

}
