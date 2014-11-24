package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;

final class RectangularArea implements Area {

	private final Location center;
	private final double height;
	private final double width;

	/**
	 * @param center the center of this rectangle
	 * @param width the width of this rectangle
	 * @param height the height of this rectangle
	 * @throws java.lang.NullPointerException if any parameters were null.
	 */
	@JsonCreator
	public RectangularArea(
			@JsonProperty("center") Location center,
			@JsonProperty("width") double width,
			@JsonProperty("height") double height) {

		Assert.assertNotNull(center);
		Assert.assertTrue(width > 0 && height > 0, "width and height bust be > 0");
		this.center = center;
		this.width = width;
		this.height = height;
	}


	@Override
	public Location getCenter() {
		return center;
	}


	/**
	 * @return height * width
	 */
	@Override
	public double calculateArea() {
		return height * width;
	}


	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof RectangularArea)) return false;
		RectangularArea area = (RectangularArea) other;
		return Objects.equal(center, area.center)
				&& Objects.equal(width, area.width)
				&& Objects.equal(height, area.height);
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(center, width, height);
	}


	@Override
	public String toString() {
		return  "(center = " + center.toString() + ", width = " + width + ", height = " + height + ")";
	}

}
