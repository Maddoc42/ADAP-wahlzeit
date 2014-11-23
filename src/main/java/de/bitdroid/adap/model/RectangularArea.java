package de.bitdroid.adap.model;


import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;

final class RectangularArea implements Area {

	private final Location centroid;
	private final double height;
	private final double width;

	public RectangularArea(Location centroid, double width, double height) {
		Assert.assertNotNull(centroid);
		Assert.assertTrue(width > 0 && height > 0, "width and height bust be > 0");
		this.centroid = centroid;
		this.width = width;
		this.height = height;
	}


	@Override
	public Location getCentroid() {
		return centroid;
	}


	@Override
	public double calculateArea() {
		return height * width;
	}


	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof RectangularArea)) return false;
		RectangularArea area = (RectangularArea) other;
		return Objects.equal(centroid, area.centroid)
				&& Objects.equal(width, area.width)
				&& Objects.equal(height, area.height);
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(centroid, width, height);
	}


	@Override
	public String toString() {
		return  "(center = " + centroid.toString() + ", width = " + width + ", height = " + height + ")";
	}

}
