package de.bitdroid.adap.model;


import junit.framework.Assert;

import org.junit.Test;

public final class NumberRangeTest {

	@Test
	public void testEqualsAndHashCode() {
		NumberRange<Double> range1 = new NumberRange<>(12.0, true, 231.1, false);
		NumberRange<Double> range2 = new NumberRange<>(12.0, true, 231.1, false);
		NumberRange<Double> range3 = new NumberRange<>(12.0, false, 231.1, false);
		NumberRange<Double> range4 = new NumberRange<>(12.1, true, 231.1, false);
		Assert.assertTrue(range1.equals(range2));
		Assert.assertFalse(range1.equals(range3));
		Assert.assertFalse(range1.equals(range4));
		Assert.assertTrue(range1.hashCode() == range2.hashCode());
		Assert.assertFalse(range1.hashCode() == range3.hashCode());
		Assert.assertFalse(range1.hashCode() == range4.hashCode());
	}


	@Test
	public void testIntersects() {
		NumberRange<Integer> range1 = new NumberRange<>(1, true, 3, true);
		NumberRange<Integer> range2 = new NumberRange<>(2, true, 4, true);
		NumberRange<Integer> range3 = new NumberRange<>(0, true, 2, true);
		Assert.assertTrue(range1.intersects(range2));
		Assert.assertTrue(range2.intersects(range1));
		Assert.assertTrue(range1.intersects(range3));
		Assert.assertTrue(range3.intersects(range1));

		NumberRange<Integer> range4 = new NumberRange<>(4, true, 5, true);
		NumberRange<Integer> range5 = new NumberRange<>(-1, true, 0, true);
		Assert.assertFalse(range1.intersects(range4));
		Assert.assertFalse(range1.intersects(range5));

		NumberRange<Integer> range6 = new NumberRange<>(3, true, 4, true);
		NumberRange<Integer> range7 = new NumberRange<>(3, false, 4, true);
		NumberRange<Integer> range8 = new NumberRange<>(0, true, 1, true);
		NumberRange<Integer> range9 = new NumberRange<>(0, false, 1, false);
		Assert.assertTrue(range1.intersects(range6));
		Assert.assertFalse(range1.intersects(range7));
		Assert.assertTrue(range1.intersects(range8));
		Assert.assertFalse(range1.intersects(range9));
	}


	@Test
	public void testContains() {
		NumberRange<Integer> range1 = new NumberRange<>(2, true, 5, true);
		NumberRange<Integer> range2 = new NumberRange<>(3, true, 4, true);
		NumberRange<Integer> range3 = new NumberRange<>(1, true, 6, true);
		NumberRange<Integer> range4 = new NumberRange<>(0, true, 3, true);
		NumberRange<Integer> range5 = new NumberRange<>(4, true, 6, true);
		Assert.assertTrue(range1.contains(range2));
		Assert.assertFalse(range2.contains(range1));
		Assert.assertFalse(range1.contains(range3));
		Assert.assertTrue(range3.contains(range1));
		Assert.assertFalse(range1.contains(range4));
		Assert.assertFalse(range4.contains(range1));
		Assert.assertFalse(range1.contains(range5));
		Assert.assertFalse(range5.contains(range1));
	}

}
