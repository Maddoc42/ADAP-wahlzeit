package org.wahlzeit.model;


public interface Location {

	public String asString();
	public double computeDistanceTo(Location location);

}
