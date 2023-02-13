package com.lipcha.example.files;

import com.lipcha.model.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class DeleteFileExample {

	private static final Logger log = LoggerFactory.getLogger(DeleteFileExample.class);

	public static void main(String[] args) {
		// Request data
		Delete delete = OPEN_AI_CLIENT.deleteFile("... file id ...");

		// Print to console
		String prettyJson = prettyJson(delete);
		log.info("\n---- Delete ----\n{}", prettyJson);
	}
}