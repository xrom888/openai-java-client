package com.lipcha.example.models;

import com.lipcha.model.GenericResponse;
import com.lipcha.model.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class ListModelsExample {

	private static final Logger log = LoggerFactory.getLogger(ListModelsExample.class);

	public static void main(String[] args) {
		// Request data
		GenericResponse<Model> models = OPEN_AI_CLIENT.models();

		// Print to console
		String prettyJson = prettyJson(models);
		log.info("\n---- Models ----\n{}", prettyJson);
	}
}