package com.lipcha.example.embeddings;

import com.lipcha.model.embedding.Embeddings;
import com.lipcha.request.CreateEmbeddings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CreateEmbeddingsExample {

	private static final Logger log = LoggerFactory.getLogger(CreateEmbeddingsExample.class);

	public static void main(String[] args) {
		// Request data
		Embeddings embeddings = OPEN_AI_CLIENT.createEmbeddings(
				CreateEmbeddings.newBuilder()
						.model("text-embedding-ada-002")
						.input(Collections.singletonList("The food was delicious and the waiter..."))
						.build()
		);

		// Log to console
		String prettyJson = prettyJson(embeddings);
		log.info("\n---- Embeddings ----\n{}", prettyJson);
	}
}