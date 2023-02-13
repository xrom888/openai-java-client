package com.lipcha.model.edit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EditTest {

	@Test
	void test_if_edit_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/edit.json");
		Edit edit = ParseUtils.MAPPER.readValue(jsonString, Edit.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(edit)));
	}

}