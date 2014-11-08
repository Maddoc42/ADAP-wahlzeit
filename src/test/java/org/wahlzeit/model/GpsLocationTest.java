package org.wahlzeit.model;


import junit.framework.Assert;

import org.junit.Test;

public final class GpsLocationTest {

	@Test
	public void testStringConversion() {
		GpsLocation location = new GpsLocation(45.891, 42.112);
		String locationString = location.asString();
		Assert.assertEquals(GpsLocation.fromString(locationString), location);
	}
}
