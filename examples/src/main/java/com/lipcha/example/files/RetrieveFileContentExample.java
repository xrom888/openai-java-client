package com.lipcha.example.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;

public class RetrieveFileContentExample {

	private static final Logger log = LoggerFactory.getLogger(RetrieveFileContentExample.class);

	public static void main(String[] args) {
		// Request data
		// Not allowed for free accounts
		String fileContent = OPEN_AI_CLIENT.fileContent("... file id ...");

		// Print to console
		log.info("\n---- File content ----\n{}", fileContent);
	}
}