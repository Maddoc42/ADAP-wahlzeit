package de.bitdroid.adap.model;


import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;


public final class CircularArea implements Area {

	private final Location center;
	private final double radius;


	public CircularArea(Location center, double radius) {
		Assert.assertNotNull(center);
		Assert.assertTrue(radius > 0, "radius must be > 0");
		this.center = center;
		this.radius = radius;
	}


	@Override
	public Location getCentroid() {
		return center;
	}


	public double getRadius() {
		return radius;
	}


	@Override
	public double calculateArea() {
		return Math.PI * radius * radius;
	}


	public boolean intersects(CircularArea area) {
		Assert.assertNotNull(area);
		double distance = center.computeDistanceTo(area.center);
		return distance <= radius + area.radius;
	}


	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof CircularArea)) return false;
		CircularArea area = (CircularArea) other;
		return Objects.equal(center, area.center) && radius == area.radius;
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(radius, center);
	}


	@Override
	public String toString() {
		return "(center = " + center.toString() + ", radius = " + radius + ")";
	}

}
