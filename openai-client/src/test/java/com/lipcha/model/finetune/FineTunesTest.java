package com.lipcha.model.finetune;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lipcha.model.GenericResponse;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FineTunesTest {

	@Test
	void test_if_fine_tunes_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/list_fine_tunes.json");
		GenericResponse<FineTune> fineTunes = ParseUtils.MAPPER.readValue(jsonString, new TypeReference<GenericResponse<FineTune>>(){});
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(fineTunes)));
	}

}