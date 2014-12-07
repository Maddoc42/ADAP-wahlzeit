package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CartesianLocation extends AbstractLocation {

	private static final Pattern stringPattern;
	static {
		String doublePattern = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
		stringPattern = Pattern.compile("\\((" + doublePattern + "),(" + doublePattern + "),(" + doublePattern + ")\\)");
	}

	private final double x, y, z;

	@JsonCreator
	CartesianLocation(
			@JsonProperty("x") double x,
			@JsonProperty("y") double y,
			@JsonProperty("z") double z) {
		this.x = x;
		this.y = y;
		this.z =  z;
	}


	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}


	public double getZ() {
		return z;
	}


	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof CartesianLocation)) return false;
		if (other == this) return true;
		CartesianLocation location = (CartesianLocation) other;
		return Objects.equal(x, location.x)
				&& Objects.equal(y, location.y)
				&& Objects.equal(z, location.z);
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(x, y, z);
	}


	@Override
	public String asString() {
		return String.format("(%f,%f,%f)", x, y, z);
	}


	/**
	 * Creates a new {@link CartesianLocation} instance based on a String returned from {@link #asString}.
	 */
	public static CartesianLocation fromString(String location) {
		Assert.assertNotNull(location);
		Matcher matcher = stringPattern.matcher(location);
		Assert.assertTrue(matcher.matches(), "could not parse location");
		double x = Double.parseDouble(matcher.group(1));
		double y = Double.parseDouble(matcher.group(3));
		double z = Double.parseDouble(matcher.group(5));
		return new CartesianLocation(x, y, z);
	}


	protected double doComputeDistanceTo(Location location) {
		CartesianLocation carLocation = (CartesianLocation) location;
		return Math.sqrt(
				Math.pow(carLocation.getX() - this.x, 2) +
				Math.pow(carLocation.getY() - this.y, 2) +
				Math.pow(carLocation.getZ() - this.z, 2));
	}

}
