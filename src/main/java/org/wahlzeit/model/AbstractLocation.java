package org.wahlzeit.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.wahlzeit.utils.Assert;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
public abstract class AbstractLocation implements Location {


	@Override
	public double computeDistanceTo(Location location) {
		Assert.assertNotNull(location);
		// not super pretty, but hey, better than having a generic parameter in the class definition
		Assert.assertTrue(
				location.getClass().equals(this.getClass()),
				"cannot compute distance between difference location systems");
		return doComputeDistanceTo(location);
	}


	protected abstract double doComputeDistanceTo(Location location);

}
