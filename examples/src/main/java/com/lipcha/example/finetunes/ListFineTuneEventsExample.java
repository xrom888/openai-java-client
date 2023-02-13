package com.lipcha.example.finetunes;

import com.lipcha.model.GenericResponse;
import com.lipcha.model.finetune.FineTuneEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class ListFineTuneEventsExample {

	private static final Logger log = LoggerFactory.getLogger(ListFineTuneEventsExample.class);

	public static void main(String[] args) {
		// Request data
		GenericResponse<FineTuneEvent> fineTuneEvents = OPEN_AI_CLIENT.fineTuneEvents("... fine tune id...", false);

		// Print to console
		String prettyJson = prettyJson(fineTuneEvents);
		log.info("\n---- FineTuneEvents ----\n{}", prettyJson);
	}
}