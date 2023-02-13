package com.lipcha.model.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UploadFileTest {

	@Test
	void test_if_upload_file_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/upload_file.json");
		UploadFile uploadFile = ParseUtils.MAPPER.readValue(jsonString, new TypeReference<UploadFile>(){});
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(uploadFile)));
	}

}