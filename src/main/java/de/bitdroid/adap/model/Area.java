package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "class")
public interface Area {

	/**
	 * @return the center of this area.
	 */
	public Location getCenter();

	/**
	 * @return the total size of this area in square kilometers.
	 */
	public double calculateArea();

	/**
	 * Used by the visitor pattern of {@link de.bitdroid.adap.model.AreaVisitor}
	 * @param visitor The visitor to execute.
	 * @param param The parameter to pass to the visitor.
	 * @return The result of the visitor.
	 */
	public <P,R> R accept(AreaVisitor<P,R> visitor, P param);

}
