package de.bitdroid.adap.model;


import junit.framework.Assert;

import org.junit.Test;

public class RectangularAreaTest {

	@Test
	public void testEqualsAndHashCode() {
		Area area1 = new RectangularArea(new GpsLocation(0, 0), 10, 10);
		Area area2 = new RectangularArea(new GpsLocation(0, 0), 10, 10);
		Area area3 = new RectangularArea(new GpsLocation(1, 1), 10, 10);
		Area area4 = new RectangularArea(new GpsLocation(0, 0), 20, 20);
		Assert.assertTrue(area1.equals(area2));
		Assert.assertFalse(area1.equals(area3));
		Assert.assertFalse(area1.equals(area4));
	}


	@Test
	public void testCalculateArea() {
		Area area = new RectangularArea(new GpsLocation(0, 0), 10, 10);
		Assert.assertTrue(10 * 10 == area.calculateArea());
	}

}
