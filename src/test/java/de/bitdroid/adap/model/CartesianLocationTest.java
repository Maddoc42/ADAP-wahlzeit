package de.bitdroid.adap.model;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Test;

import de.bitdroid.adap.model.CartesianLocation;

public final class CartesianLocationTest {

	private final CartesianLocation location = new CartesianLocation(1.1, 2.2, 3.3);

	@Test
	public void testStringConversion() {
		String locationString = location.asString();
		Assert.assertEquals(CartesianLocation.fromString(locationString), location);
	}


	@Test
	public void testJacksonConversion() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.valueToTree(location);
		org.junit.Assert.assertEquals(location, mapper.treeToValue(json, CartesianLocation.class));
	}

}
