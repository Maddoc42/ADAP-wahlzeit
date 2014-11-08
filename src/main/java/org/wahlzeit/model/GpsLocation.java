package org.wahlzeit.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class GpsLocation extends AbstractLocation {

	private static final Pattern stringPattern;
	static {
		String doublePattern = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
		stringPattern = Pattern.compile("\\((" + doublePattern + "),(" + doublePattern + ")\\)");
	}

	private final double latitude, longitude;

	/**
	 * @param latitude in degrees
	 * @param longitude in degrees
	 */
	@JsonCreator
	public GpsLocation(
			@JsonProperty("latitude") double latitude,
			@JsonProperty("longitude") double longitude) {
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


	@Override
	public String asString() {
		return String.format("(%f,%f)", latitude, longitude);
	}


	/**
	 * Creates a new {@link GpsLocation} instance based on a String returned from {@link #asString}.
	 */
	public static GpsLocation fromString(String location) {
		Assert.assertNotNull(location);
		Matcher matcher = stringPattern.matcher(location);
		Assert.assertTrue(matcher.matches(), "could not parse location");
		double latitude = Double.parseDouble(matcher.group(1));
		double longitude = Double.parseDouble(matcher.group(3));
		return new GpsLocation(latitude, longitude);
	}


	protected double doComputeDistanceTo(Location location) {
		GpsLocation gpsLocation = (GpsLocation) location;
		// courtesy to http://stackoverflow.com/a/837957/3892030
		double earthRadius = 6371; //kilometers
		double dLat = Math.toRadians(gpsLocation.getLatitude() - latitude);
		double dLng = Math.toRadians(gpsLocation.getLongitude() - longitude);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(gpsLocation.getLatitude())) *
						Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return earthRadius * c;
	}
}
