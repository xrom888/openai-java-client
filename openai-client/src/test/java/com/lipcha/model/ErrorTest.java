package com.lipcha.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorTest {

	@Test
	void test_if_error_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/error.json");
		JsonNode errorNode = ParseUtils.MAPPER.readTree(jsonString);
		Error error = ParseUtils.MAPPER.treeToValue(errorNode.get("error"), Error.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString).get("error"))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(error)));
	}

}