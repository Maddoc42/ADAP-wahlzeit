package de.bitdroid.adap.model;


import de.bitdroid.adap.utils.Assert;

abstract class AbstractArea implements Area {

	protected final Location center;

	public AbstractArea(Location center) {
		Assert.assertNotNull(center);
		this.center = center;
	}

	@Override
	public final Location getCenter() {
		return center;
	}

}
