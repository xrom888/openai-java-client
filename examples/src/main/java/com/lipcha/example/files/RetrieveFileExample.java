package com.lipcha.example.files;

import com.lipcha.model.file.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class RetrieveFileExample {

	private static final Logger log = LoggerFactory.getLogger(RetrieveFileExample.class);

	public static void main(String[] args) {
		// Request data
		File file = OPEN_AI_CLIENT.file("... file id ...");

		// Print to console
		String prettyJson = prettyJson(file);
		log.info("\n---- File ----\n{}", prettyJson);
	}
}