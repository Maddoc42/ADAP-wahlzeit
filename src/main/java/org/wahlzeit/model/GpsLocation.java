package org.wahlzeit.model;


import org.wahlzeit.utils.Assert;

public final class GpsLocation implements Location {

	private final double latitude, longitude;

	public GpsLocation(double latitude, double longitude) {
		Assert.assertTrue(latitude >= -90 && latitude <= 90, "latitude must be >= -90 and <= 90");
		Assert.assertTrue(longitude >= -180 && longitude <= 180, "longitude must be >= -90 and <= 90");
		this.latitude = latitude;
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public double getLongitude() {
		return longitude;
	}

}
