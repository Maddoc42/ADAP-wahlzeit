package de.bitdroid.adap.model;


import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import org.wahlzeit.utils.Assert;

import java.util.EnumSet;
import java.util.Set;

public final class Frog {

	private final String commonName, scientificName;
	private final ImmutableSet<Animals> diet;
	private final NumberRange<Double> sizeRange;
	private final ImmutableSet<Area> habitats;

	public Frog(
			String commonName,
			String scientificName,
			EnumSet<Animals> diet,
			NumberRange<Double> sizeRange,
			Set<Area> habitats) {

		Assert.assertNotNull(commonName, scientificName, diet, sizeRange, habitats);
		this.commonName = commonName;
		this.scientificName = scientificName;
		this.diet = Sets.immutableEnumSet(diet);
		this.sizeRange = sizeRange;
		this.habitats = ImmutableSet.copyOf(habitats);
	}


	public String getCommonName() {
		return commonName;
	}


	public String getScientificName() {
		return scientificName;
	}


	public ImmutableSet<Animals> getDiet() {
		return diet;
	}


	public NumberRange<Double> getSizeRange() {
		return sizeRange;
	}


	public ImmutableSet<Area> getHabitats() {
		return habitats;
	}

}
