package de.bitdroid.adap.model;


import junit.framework.Assert;

import org.junit.Test;

public final class NumberRangeTest {

	@Test
	public void testEqualsAndHashCode() {
		NumberRange<Double> range1 = new NumberRange.Builder<>(12.0, 231.1).startInclusive(true).endInclusive(false).build();
		NumberRange<Double> range2 = new NumberRange.Builder<>(12.0, 231.1).startInclusive(true).endInclusive(false).build();
		NumberRange<Double> range3 = new NumberRange.Builder<>(12.0, 231.1).startInclusive(false).endInclusive(false).build();
		NumberRange<Double> range4 = new NumberRange.Builder<>(12.1, 231.1).startInclusive(true).endInclusive(false).build();
		Assert.assertTrue(range1.equals(range2));
		Assert.assertFalse(range1.equals(range3));
		Assert.assertFalse(range1.equals(range4));
		Assert.assertTrue(range1.hashCode() == range2.hashCode());
		Assert.assertFalse(range1.hashCode() == range3.hashCode());
		Assert.assertFalse(range1.hashCode() == range4.hashCode());
	}


	@Test
	public void testIntersects() {
		NumberRange<Integer> range1 = new NumberRange.Builder<>(1, 3).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range2 = new NumberRange.Builder<>(2, 4).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range3 = new NumberRange.Builder<>(0, 2).startInclusive(true).endInclusive(true).build();
		Assert.assertTrue(range1.intersects(range2));
		Assert.assertTrue(range2.intersects(range1));
		Assert.assertTrue(range1.intersects(range3));
		Assert.assertTrue(range3.intersects(range1));

		NumberRange<Integer> range4 = new NumberRange.Builder<>(4,5).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range5 = new NumberRange.Builder<>(-1, 0).startInclusive(true).endInclusive(true).build();
		Assert.assertFalse(range1.intersects(range4));
		Assert.assertFalse(range1.intersects(range5));

		NumberRange<Integer> range6 = new NumberRange.Builder<>(3, 4).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range7 = new NumberRange.Builder<>(3, 4).startInclusive(false).endInclusive(true).build();
		NumberRange<Integer> range8 = new NumberRange.Builder<>(0, 1).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range9 = new NumberRange.Builder<>(0, 1).startInclusive(false).endInclusive(false).build();
		Assert.assertTrue(range1.intersects(range6));
		Assert.assertFalse(range1.intersects(range7));
		Assert.assertTrue(range1.intersects(range8));
		Assert.assertFalse(range1.intersects(range9));
	}


	@Test
	public void testContains() {
		NumberRange<Integer> range1 = new NumberRange.Builder<>(2, 5).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range2 = new NumberRange.Builder<>(3, 4).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range3 = new NumberRange.Builder<>(1, 6).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range4 = new NumberRange.Builder<>(0, 3).startInclusive(true).endInclusive(true).build();
		NumberRange<Integer> range5 = new NumberRange.Builder<>(4, 6).startInclusive(true).endInclusive(true).build();
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
