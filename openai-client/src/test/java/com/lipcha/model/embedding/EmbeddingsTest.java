package com.lipcha.model.embedding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lipcha.util.ParseUtils;
import com.lipcha.util.TestFileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmbeddingsTest {

	@Test
	void test_if_embeddings_parsed_and_serialized() throws JsonProcessingException {
		String jsonString = TestFileUtils.testFile("/model/create_embeddings.json");
		Embeddings embeddings = ParseUtils.MAPPER.readValue(jsonString, Embeddings.class);
		assertThat(ParseUtils.MAPPER.readTree(jsonString))
				.isEqualTo(ParseUtils.MAPPER.readTree(ParseUtils.MAPPER.writeValueAsString(embeddings)));
	}

}