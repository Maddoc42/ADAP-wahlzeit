package de.bitdroid.adap.model;


import org.junit.Assert;
import org.junit.Test;

public final class CircularAreaTest {

	@Test
	public void testEqualsAndHashCode() {
		CircularArea area1 = new CircularArea(new GpsLocation(0, 0), 10);
		CircularArea area2 = new CircularArea(new GpsLocation(0, 0), 10);
		CircularArea area3 = new CircularArea(new GpsLocation(1, 0), 10);
		CircularArea area4 = new CircularArea(new GpsLocation(0, 0), 11);
		Assert.assertTrue(area1.equals(area2));
		Assert.assertFalse(area1.equals(area3));
		Assert.assertFalse(area1.equals(area4));
		Assert.assertTrue(area1.hashCode() == area2.hashCode());
		Assert.assertFalse(area1.hashCode() == area3.hashCode());
		Assert.assertFalse(area1.hashCode() == area4.hashCode());
	}


	@Test
	public void testIntersects() {
		CircularArea area1 = new CircularArea(new GpsLocation(0, 0), 100);
		CircularArea area2 = new CircularArea(new GpsLocation(1, 1), 100);
		CircularArea area3 = new CircularArea(new GpsLocation(80, 80), 100);
		Assert.assertTrue(area1.intersects(area2));
		Assert.assertTrue(area2.intersects(area1));
		Assert.assertFalse(area1.intersects(area3));
	}

}
