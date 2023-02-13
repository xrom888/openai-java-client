package com.lipcha.example.files;

import com.lipcha.model.GenericResponse;
import com.lipcha.model.file.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class ListFilesExample {

	private static final Logger log = LoggerFactory.getLogger(ListFilesExample.class);

	public static void main(String[] args) {
		// Request data
		GenericResponse<File> files = OPEN_AI_CLIENT.files();

		// Print to console
		String prettyJson = prettyJson(files);
		log.info("\n---- Files ----\n{}", prettyJson);
	}
}