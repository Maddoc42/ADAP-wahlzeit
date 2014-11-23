package de.bitdroid.adap.model;


import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;

public final class Area {

	private final Location center;
	private final double radius;


	public Area(Location center, double radius) {
		Assert.assertNotNull(center);
		Assert.assertTrue(radius > 0, "radius must be > 0");
		this.center = center;
		this.radius = radius;
	}


	public Location getCenter() {
		return center;
	}


	public double getRadius() {
		return radius;
	}


	public double calculateArea() {
		return Math.PI * radius * radius;
	}


	public boolean intersects(Area area) {
		Assert.assertNotNull(area);
		double distance = center.computeDistanceTo(area.center);
		return distance <= radius + area.radius;
	}


	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Area)) return false;
		Area area = (Area) other;
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
