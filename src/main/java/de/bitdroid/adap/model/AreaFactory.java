package de.bitdroid.adap.model;


public final class AreaFactory {

	public static Area createCircularArea(Location center, double radius) {
		return new CircularArea(center, radius);
	}


	public static Area createRectangularArea(Location center, double width, double height) {
		return new RectangularArea(center, width, height);
	}

	private AreaFactory() { }

}
