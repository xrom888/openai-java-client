package com.lipcha.model.moderation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ModerationTest {

	@Test
	void test_if_moderation_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/moderation.json");
		Moderation moderation = ParseUtils.MAPPER.readValue(jsonString, Moderation.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(moderation)));
	}

}