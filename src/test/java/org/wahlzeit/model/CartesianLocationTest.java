package org.wahlzeit.model;


import junit.framework.Assert;

import org.junit.Test;

public final class CartesianLocationTest {

	@Test
	public void testStringConversion() {
		CartesianLocation location = new CartesianLocation(1.1, 2.2, 3.3);
		String locationString = location.asString();
		Assert.assertEquals(CartesianLocation.fromString(locationString), location);
	}
}
