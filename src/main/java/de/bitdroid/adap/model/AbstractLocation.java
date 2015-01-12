package de.bitdroid.adap.model;


import de.bitdroid.adap.utils.Assert;


public abstract class AbstractLocation implements Location {


	@Override
	public double computeDistanceTo(Location location) {
		Assert.assertNotNull(location);
		// not super pretty, but hey, better than having a generic parameter in the class definition
		Assert.assertTrue(
				location.getClass().equals(this.getClass()),
				"cannot compute distance between difference location systems");
		return doComputeDistanceTo(location);
	}


	protected abstract double doComputeDistanceTo(Location location);

}
