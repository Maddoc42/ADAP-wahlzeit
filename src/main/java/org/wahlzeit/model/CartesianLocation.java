package org.wahlzeit.model;


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
		CartesianLocation location = (CartesianLocation) other;
		return x == location.x && y == location.y && z == location.z;
	}


	@Override
	public int hashCode() {
		// TODO
		return 0;
	}


}
