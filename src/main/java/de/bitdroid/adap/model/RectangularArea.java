package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import de.bitdroid.adap.utils.Assert;

final class RectangularArea extends AbstractArea {

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

		super(center);
		Assert.assertTrue(width > 0 && height > 0, "width and height bust be > 0");
		this.width = width;
		this.height = height;
	}


	public double getHeight() {
		return height;
	}


	public double getWidth() {
		return width;
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


	@Override
	public <P,R> R accept(AreaVisitor<P,R> visitor, P param) {
		return visitor.visit(this, param);
	}

}
