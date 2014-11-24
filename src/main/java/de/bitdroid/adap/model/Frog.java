package de.bitdroid.adap.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import org.wahlzeit.utils.Assert;

import java.util.EnumSet;
import java.util.Set;

public final class Frog {

	private final String commonName, scientificName;
	private final ImmutableSet<Food> diet;
	private final NumberRange<Double> sizeRange;
	private final ImmutableSet<Area> habitats;

	@JsonCreator
	public Frog(
			@JsonProperty("commonName") String commonName,
			@JsonProperty("scientificName") String scientificName,
			@JsonProperty("diet") EnumSet<Food> diet,
			@JsonProperty("sizeRange") NumberRange<Double> sizeRange,
			@JsonProperty("habitats") Set<Area> habitats) {

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


	public ImmutableSet<Food> getDiet() {
		return diet;
	}


	public NumberRange<Double> getSizeRange() {
		return sizeRange;
	}


	public ImmutableSet<Area> getHabitats() {
		return habitats;
	}

}
