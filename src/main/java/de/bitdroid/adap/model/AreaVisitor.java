package de.bitdroid.adap.model;


public interface AreaVisitor<P, R> {

	public R visit(CircularArea area, P param);
	public R visit(RectangularArea area, P param);

}
