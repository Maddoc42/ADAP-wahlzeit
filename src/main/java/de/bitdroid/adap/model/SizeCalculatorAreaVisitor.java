package de.bitdroid.adap.model;


/**
 * Calculates the area of one {@link de.bitdroid.adap.model.Area}.
 */
public final class SizeCalculatorAreaVisitor implements AreaVisitor<Void, Double> {

	@Override
	public Double visit(CircularArea area, Void param) {
		return Math.PI * area.getRadius() * area.getRadius();
	}


	@Override
	public Double visit(RectangularArea area, Void param) {
		return area.getHeight() * area.getWidth();
	}

}
