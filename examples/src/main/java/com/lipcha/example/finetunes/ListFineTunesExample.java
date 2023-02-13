package com.lipcha.example.finetunes;

import com.lipcha.model.GenericResponse;
import com.lipcha.model.finetune.FineTune;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class ListFineTunesExample {

	private static final Logger log = LoggerFactory.getLogger(ListFineTunesExample.class);

	public static void main(String[] args) {
		// Request data
		GenericResponse<FineTune> fineTunes = OPEN_AI_CLIENT.fineTunes();

		// Print to console
		String prettyJson = prettyJson(fineTunes);
		log.info("\n---- FineTunes ----\n{}", prettyJson);
	}
}