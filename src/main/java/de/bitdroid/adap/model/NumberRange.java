package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;

public final class NumberRange<N extends Number> {

	private final N start, end;
	private final boolean startInclusive, endInclusive;


	@JsonCreator
	private NumberRange(
			@JsonProperty("start") N start,
			@JsonProperty("startInclusive") boolean startInclusive,
			@JsonProperty("end") N end,
			@JsonProperty("endInclusive") boolean endInclusive) {

		Assert.assertNotNull(start, end);
		Assert.assertFalse(end.doubleValue() < start.doubleValue(), "end must be >= start");
		if (start.equals(end)) {
			Assert.assertTrue(startInclusive && endInclusive, "when start == end range must be inclusive");
		}
		this.start = start;
		this.startInclusive = startInclusive;
		this.end = end;
		this.endInclusive = endInclusive;
	}


	public N getStart() {
		return start;
	}


	public N getEnd() {
		return end;
	}


	public boolean getStartInclusive() {
		return startInclusive;
	}


	public boolean getEndInclusive() {
		return endInclusive;
	}


	/**
	 * @param range another range to check for intersection.
	 * @return true if the two rectangles overlap / intersect, false otherwise.
	 * @throws java.lang.NullPointerException if any parameters were null.
	 */
	public <T extends Number> boolean intersects(NumberRange<T> range) {
		Assert.assertNotNull(range);
		if (isValue1BeforeValue2(end, endInclusive, range.start, range.startInclusive)) return false;
		if (isValue1BeforeValue2(range.end, range.endInclusive, start, startInclusive)) return false;
		return true;
	}


	/**
	 * @param range anther range.
	 * @return true if this range completely contains the other range, false otherwise.
	 * @throws java.lang.NullPointerException if any parameters were null.
	 */
	public <T extends Number> boolean contains(NumberRange<T> range) {
		Assert.assertNotNull(range);
		if (!intersects(range)) return false;
		if (isValue1BeforeValue2(start, startInclusive, range.start, range.startInclusive)
				&& isValue1BeforeValue2(range.end, range.endInclusive, end, endInclusive)) return true;
		return false;
	}


	private <T1 extends Number, T2 extends Number> boolean isValue1BeforeValue2(
			T1 value1,
			boolean value1Inclusive,
			T2 value2,
			boolean value2Inclusive) {

		double dv1 = value1.doubleValue();
		double dv2 = value2.doubleValue();
		if (dv1 < dv2) return true;
		if (dv1 > dv2) return false;
		if (value1Inclusive && value2Inclusive) return false;
		return true;
	}


	@Override
	@SuppressWarnings("rawTypes")
	public boolean equals(Object other) {
		if (other == null || !(other instanceof NumberRange)) return false;
		NumberRange range = (NumberRange) other;
		return Objects.equal(start, range.getStart())
				&& Objects.equal(end, range.getEnd())
				&& Objects.equal(startInclusive, range.getStartInclusive())
				&& Objects.equal(endInclusive, range.getEndInclusive());
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(start, end, startInclusive, endInclusive);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (startInclusive) builder.append("[");
		else builder.append("(");
		builder.append(start.toString());
		builder.append(", ");
		builder.append(end.toString());
		if (endInclusive) builder.append("]");
		else builder.append(")");
		return builder.toString();
	}


	public static final class Builder<N extends Number> {

		private final N start, end;
		private boolean startInclusive = false, endInclusive = false;


		/**
		 * Creates a new builder for constructing {@link de.bitdroid.adap.model.NumberRange} instances.
		 * By default start and end are not inclusive. For convenience all method return a this
		 * reference to allow method chaining.
		 * @param start the start of the interval.
		 * @param end the end of the interval.
		 * @throws java.lang.NullPointerException if any parameters were null.
		 * @throws java.lang.IllegalArgumentException if end is before start.
		 */
		public Builder(N start, N end) {
			Assert.assertNotNull(start, end);
			Assert.assertFalse(end.doubleValue() < start.doubleValue(), "end must be >= start");
			this.start = start;
			this.end = end;
		}


		/**
		 * @param startInclusive whether start belongs to the interval or should be excluded.
		 */
		public Builder<N> startInclusive(boolean startInclusive) {
			this.startInclusive = startInclusive;
			return this;
		}


		/**
		 * @param endInclusive whether end belongs to the interval or should be excluded.
		 */
		public Builder<N> endInclusive(boolean endInclusive) {
			this.endInclusive = endInclusive;
			return this;
		}


		public NumberRange<N> build() {
			return new NumberRange<>(start, startInclusive, end, endInclusive);
		}

	}

}
