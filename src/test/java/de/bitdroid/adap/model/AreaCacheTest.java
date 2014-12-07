package de.bitdroid.adap.model;


import org.junit.Assert;
import org.junit.Test;

public final class AreaCacheTest {

	@Test
	public void testCaching() {
		AreaCache cache = new AreaCache(new AreaFactory());
		Area area1 = cache.getCircularArea(new GpsLocation(0, 0), 10);
		Area area2 = cache.getCircularArea(new GpsLocation(0, 0), 10);
		Area area3 = cache.getCircularArea(new GpsLocation(0, 0), 10);
		Area area4 = cache.getCircularArea(new GpsLocation(0, 0), 20);

		Assert.assertTrue(area1.equals(area2));
		Assert.assertTrue(area1 == area2);
		Assert.assertTrue(area1 == area3);
		Assert.assertTrue(area1 != area4);

		cache.releaseArea(area1);
		Area area5 = cache.getCircularArea(new GpsLocation(0, 0), 10);

		Assert.assertTrue(area1.equals(area5));
		Assert.assertTrue(area1 != area5);
	}

}
