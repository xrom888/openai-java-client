package com.lipcha.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateImageVariationTest {

	@Test
	void test_if_create_image_variation_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/request/create_image_variation.json");
		CreateImageVariation createImageVariation = ParseUtils.MAPPER.readValue(jsonString, CreateImageVariation.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(createImageVariation)));
	}

}