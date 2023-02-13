package com.lipcha.example.files;

import com.lipcha.model.file.UploadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class UploadFileExample {

	private static final Logger log = LoggerFactory.getLogger(UploadFileExample.class);

	public static void main(String[] args) {
		// Request data
		// File example: examples/src/main/resources/file/json_lines_example.jsonl
		UploadFile uploadFile = OPEN_AI_CLIENT.uploadFile("fine-tune", "... path to jsonl file ...");

		// Print to console
		String prettyJson = prettyJson(uploadFile);
		log.info("\n---- UploadFile ----\n{}", prettyJson);
	}
}