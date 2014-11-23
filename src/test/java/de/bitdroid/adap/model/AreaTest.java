package de.bitdroid.adap.model;


import org.junit.Assert;
import org.junit.Test;

public final class AreaTest {

	@Test
	public void testEqualsAndHashCode() {
		Area area1 = new Area(new GpsLocation(0, 0), 10);
		Area area2 = new Area(new GpsLocation(0, 0), 10);
		Area area3 = new Area(new GpsLocation(1, 0), 10);
		Area area4 = new Area(new GpsLocation(0, 0), 11);
		Assert.assertTrue(area1.equals(area2));
		Assert.assertFalse(area1.equals(area3));
		Assert.assertFalse(area1.equals(area4));
		Assert.assertTrue(area1.hashCode() == area2.hashCode());
		Assert.assertFalse(area1.hashCode() == area3.hashCode());
		Assert.assertFalse(area1.hashCode() == area4.hashCode());
	}


	@Test
	public void testCalculateArea() {
		Area area1 = new Area(new GpsLocation(0, 0), 10);
		Area area2 = new Area(new GpsLocation(2, 2), 10);
		Assert.assertEquals(10 * 10 * Math.PI, area1.calculateArea(), 0);
		Assert.assertEquals(10 * 10 * Math.PI, area2.calculateArea(), 0);
	}


	@Test
	public void testIntersects() {
		Area area1 = new Area(new GpsLocation(0, 0), 100);
		Area area2 = new Area(new GpsLocation(1, 1), 100);
		Area area3 = new Area(new GpsLocation(80, 80), 100);
		Assert.assertTrue(area1.intersects(area2));
		Assert.assertTrue(area2.intersects(area1));
		Assert.assertFalse(area1.intersects(area3));
	}

}
