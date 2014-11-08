package org.wahlzeit.model;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Test;

public final class GpsLocationTest {

	private final GpsLocation location = new GpsLocation(45.891, 42.112);

	@Test
	public void testStringConversion() {
		String locationString = location.asString();
		Assert.assertEquals(GpsLocation.fromString(locationString), location);
	}


	@Test
	public void testJacksonConversion() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.valueToTree(location);
		org.junit.Assert.assertEquals(location, mapper.treeToValue(json, GpsLocation.class));
	}

}
