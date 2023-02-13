package com.lipcha.example.finetunes;

import com.lipcha.model.finetune.FineTune;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CancelFineTuneExample {

	private static final Logger log = LoggerFactory.getLogger(CancelFineTuneExample.class);

	public static void main(String[] args) {
		// Request data
		FineTune fineTune = OPEN_AI_CLIENT.cancelFineTune("... fine tune id");

		// Print to console
		String prettyJson = prettyJson(fineTune);
		log.info("\n---- Canceled FineTune ----\n{}", prettyJson);
	}
}