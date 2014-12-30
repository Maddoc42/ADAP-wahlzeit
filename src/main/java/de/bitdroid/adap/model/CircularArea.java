package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;


final class CircularArea extends AbstractArea {

	private final double radius;


	/**
	 * @param center the center of this circle
	 * @param radius the radius of this circle
	 * @throws java.lang.NullPointerException if any parameters were null.
	 */
	@JsonCreator
	public CircularArea(
			@JsonProperty("center") Location center,
			@JsonProperty("radius") double radius) {

		super(center);
		Assert.assertTrue(radius > 0, "radius must be > 0");
		this.radius = radius;
	}


	public double getRadius() {
		return radius;
	}


	/**
	 * @param area another circular area
	 * @return true if these two areas intersect and false otherwise.
	 * @throws java.lang.NullPointerException if any parameters were null.
	 */
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


	@Override
	public <P,R> R accept(AreaVisitor<P,R> visitor, P param) {
		return visitor.visit(this, param);
	}

}
