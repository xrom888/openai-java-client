package com.lipcha.model.image;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImagesTest {

	@Test
	void test_if_images_with_url_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/create_image__url.json");
		Images images = ParseUtils.MAPPER.readValue(jsonString, Images.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(images)));
	}

	@Test
	void test_if_images_with_b64_json_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/create_image__b64_json.json");
		Images images = ParseUtils.MAPPER.readValue(jsonString, Images.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(images)));
	}

}