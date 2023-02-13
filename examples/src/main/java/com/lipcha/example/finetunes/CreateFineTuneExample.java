package com.lipcha.example.finetunes;

import com.lipcha.model.finetune.FineTune;
import com.lipcha.request.CreateFineTune;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CreateFineTuneExample {

	private static final Logger log = LoggerFactory.getLogger(CreateFineTuneExample.class);

	public static void main(String[] args) {
		// Request data
		FineTune fineTune = OPEN_AI_CLIENT.createFineTune(
				CreateFineTune.newBuilder()
						.trainingFile("... uploaded file id ...")
						.build()
		);

		// Print to console
		String prettyJson = prettyJson(fineTune);
		log.info("\n---- FineTune ----\n{}", prettyJson);
	}
}