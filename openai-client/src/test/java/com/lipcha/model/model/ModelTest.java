package com.lipcha.model.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ModelTest {

	@Test
	void test_if_model_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/model.json");
		Model model = ParseUtils.MAPPER.readValue(jsonString, Model.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(model)));
	}

}