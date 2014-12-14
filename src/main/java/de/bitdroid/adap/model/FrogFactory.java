package de.bitdroid.adap.model;


public final class FrogFactory {

	public static Frog createFrog(String name, double age, FrogType type) {
		return new Frog(name, age, type);
	}

	private FrogFactory() { }

}
