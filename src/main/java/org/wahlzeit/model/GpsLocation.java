package org.wahlzeit.model;


import com.google.common.base.Objects;

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


	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof GpsLocation)) return false;
		if (other == this) return true;
		GpsLocation location = (GpsLocation) other;
		return Objects.equal(latitude, location.latitude)
				&& Objects.equal(longitude, location.longitude);
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(latitude, longitude);
	}

}
