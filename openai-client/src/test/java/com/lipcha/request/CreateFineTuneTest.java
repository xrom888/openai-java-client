package com.lipcha.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateFineTuneTest {

	@Test
	void test_if_create_fine_tune_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/request/create_fine_tune.json");
		CreateFineTune createFineTune = ParseUtils.MAPPER.readValue(jsonString, CreateFineTune.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(createFineTune)));
	}

}