package de.bitdroid.adap.model;


import org.junit.Assert;
import org.junit.Test;

public final class AreaCalculatorTest {

	private final AreaVisitor<Void, Double> visitor = new AreaCalculator();


	@Test
	public void testCircularArea() {
		CircularArea area1 = new CircularArea(new GpsLocation(0, 0), 10);
		CircularArea area2 = new CircularArea(new GpsLocation(2, 2), 10);
		Assert.assertEquals(10 * 10 * Math.PI, area1.accept(visitor, null), 0);
		Assert.assertEquals(10 * 10 * Math.PI, area2.accept(visitor, null), 0);
	}


	@Test
	public void testRectangularArea() {
		Area area = new RectangularArea(new GpsLocation(0, 0), 10, 10);
		Assert.assertEquals(10 * 10, area.accept(visitor, null), 0);
	}

}
