package de.bitdroid.adap.model;


/**
 * Calculates the area of one {@link de.bitdroid.adap.model.Area}.
 */
public final class AreaCalculator implements AreaVisitor<Void, Double> {

	@Override
	public Double visit(CircularArea area, Void param) {
		return Math.PI * area.getRadius() * area.getRadius();
	}


	@Override
	public Double visit(RectangularArea area, Void param) {
		return area.getHeight() * area.getWidth();
	}


	@Override
	public Double visit(CompositeArea area, Void param) {
		double totalSize = 0;
		for (Area subArea : area) totalSize += subArea.accept(this, param);
		return totalSize;
	}

}
