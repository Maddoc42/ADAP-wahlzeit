package org.wahlzeit.model;


import com.google.common.base.Objects;

public final class CartesianLocation implements Location {

	private final double x, y, z;

	public CartesianLocation(double x, double y, double z) {
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


}
