package de.bitdroid.adap.model;


public final class GpsLocationAdapter implements LocationVisitor<Void, GpsLocation> {

	private static final double EARTH_RADIUS = 6371d;

	@Override
	public GpsLocation visit(GpsLocation location, Void param) {
		return location;
	}


	/**
	 * Courtesy to <a href="http://stackoverflow.com/a/1185413">stackoverflow</a>.
	 */
	@Override
	public GpsLocation visit(CartesianLocation location, Void param) {
		double lat = Math.asin(location.getZ() / EARTH_RADIUS);
		double lon = Math.atan2(location.getY(), location.getX());
		return new GpsLocation(lat, lon);
	}

}
