package de.bitdroid.adap.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import org.wahlzeit.utils.Assert;


public final class Frog {

	private final String name;
	private final double age;
	private final FrogType type;

	/**
	 * @param name What this frog is called, e.g. "Fred" or "My Favourite Frog".
	 * @param age How old the frog is.
	 */
	@JsonCreator
	Frog(
			@JsonProperty("name") String name,
			@JsonProperty("age") double age,
			@JsonProperty("type") FrogType type) {

		Assert.assertNotNull(name, type);
		Assert.assertTrue(age > 0, "age must be > 0");
		this.name = name;
		this.age = age;
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public double getAge() {
		return age;
	}


	public FrogType getType() {
		return type;
	}


	@Override
	public String toString() {
		return name + " (age: " + age + ", type:" + type + ")";
	}


	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Frog)) return false;
		if (other == this) return true;
		Frog frog = (Frog) other;
		return Objects.equal(name, frog.name)
				&& Objects.equal(age, frog.age)
				&& Objects.equal(type ,frog.type);
	}


	@Override
	public int hashCode() {
		return Objects.hashCode(name, age, type);
	}

}
