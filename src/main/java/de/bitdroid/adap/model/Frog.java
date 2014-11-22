package de.bitdroid.adap.model;


import org.wahlzeit.utils.Assert;

import java.util.EnumSet;

public abstract class Frog {

	private final String commonName, scientificName;
	private final EnumSet<Animals> diet;
	private final NumberRange<Double> sizeRange;

	public Frog(
			String commonName,
			String scientificName,
			EnumSet<Animals> diet,
			NumberRange<Double> sizeRange) {

		Assert.assertNotNull(commonName, scientificName, diet, sizeRange);
		this.commonName = commonName;
		this.scientificName = scientificName;
		this.diet = diet;
		this.sizeRange = sizeRange;
	}


	public String getCommonName() {
		return commonName;
	}


	public String getScientificName() {
		return scientificName;
	}


	public EnumSet<Animals> getDiet() {
		return diet;
	}


	public NumberRange<Double> getSizeRange() {
		return sizeRange;
	}

}
