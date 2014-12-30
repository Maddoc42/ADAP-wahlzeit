package de.bitdroid.adap.model;


public interface LocationVisitor<P, R> {

	public R visit(GpsLocation location, P param);
	public R visit(CartesianLocation location, P param);

}
