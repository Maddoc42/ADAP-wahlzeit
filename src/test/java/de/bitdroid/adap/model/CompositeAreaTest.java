package de.bitdroid.adap.model;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.Test;

public class CompositeAreaTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testJson() throws Exception {
		CompositeArea area = new CompositeArea.Builder()
				.area(new RectangularArea(new GpsLocation(0, 0), 12, 34))
				.area(new RectangularArea(new GpsLocation(12, 34), 56, 78))
				.build();

		JsonNode json = mapper.valueToTree(area);
		CompositeArea parsedArea = mapper.treeToValue(json, CompositeArea.class);

		Assert.assertEquals(area, parsedArea);
	}

}
