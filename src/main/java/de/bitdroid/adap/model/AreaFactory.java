package de.bitdroid.adap.model;


public final class AreaFactory {

	public Area createCircularArea(Location center, double radius) {
		return new CircularArea(center, radius);
	}


	public Area createRectangularArea(Location center, double width, double height) {
		return new RectangularArea(center, width, height);
	}

}
