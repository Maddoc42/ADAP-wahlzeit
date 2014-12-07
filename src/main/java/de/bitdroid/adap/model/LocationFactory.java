package de.bitdroid.adap.model;


public final class LocationFactory {

	public static Location createGpsLocation(double latitude, double longitude) {
		return new GpsLocation(latitude, longitude);
	}


	public static Location createCartesianLocation(double x, double y, double z) {
		return new CartesianLocation(x, y, z);
	}


	private LocationFactory() { }

}
