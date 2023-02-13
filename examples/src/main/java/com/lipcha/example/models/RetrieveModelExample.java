package com.lipcha.example.models;

import com.lipcha.model.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class RetrieveModelExample {

	private static final Logger log = LoggerFactory.getLogger(RetrieveModelExample.class);

	public static void main(String[] args) {
		// Request data
		Model model = OPEN_AI_CLIENT.model("text-embedding-ada-002");

		// Print to console
		String prettyJson = prettyJson(model);
		log.info("\n---- Model ----\n{}", prettyJson);
	}
}