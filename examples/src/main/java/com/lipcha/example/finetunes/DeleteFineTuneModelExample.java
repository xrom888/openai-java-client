package com.lipcha.example.finetunes;

import com.lipcha.model.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class DeleteFineTuneModelExample {

	private static final Logger log = LoggerFactory.getLogger(DeleteFineTuneModelExample.class);

	public static void main(String[] args) {
		// Request data
		// You must have the Owner role in your organization.
		Delete delete = OPEN_AI_CLIENT.deleteFineTuneModel("... model id ...");

		// Print to console
		String prettyJson = prettyJson(delete);
		log.info("\n---- Delete ----\n{}", prettyJson);
	}
}