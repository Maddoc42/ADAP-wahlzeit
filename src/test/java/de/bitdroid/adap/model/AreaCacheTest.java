package de.bitdroid.adap.model;


import org.junit.Assert;
import org.junit.Test;

public final class AreaCacheTest {

	@Test
	public void testCaching() {
		Area area1 = AreaCache.getCircularArea(new GpsLocation(0, 0), 10);
		Area area2 = AreaCache.getCircularArea(new GpsLocation(0, 0), 10);
		Area area3 = AreaCache.getCircularArea(new GpsLocation(0, 0), 10);
		Area area4 = AreaCache.getCircularArea(new GpsLocation(0, 0), 20);

		Assert.assertTrue(area1.equals(area2));
		Assert.assertTrue(area1 == area2);
		Assert.assertTrue(area1 == area3);
		Assert.assertTrue(area1 != area4);

		AreaCache.releaseArea(area1);
		Area area5 = AreaCache.getCircularArea(new GpsLocation(0, 0), 10);

		Assert.assertTrue(area1.equals(area5));
		Assert.assertTrue(area1 != area5);
	}

}
