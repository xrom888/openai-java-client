package com.lipcha.example.moderations;

import com.lipcha.model.moderation.Moderation;
import com.lipcha.request.CreateModeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CreateModerationExample {

	private static final Logger log = LoggerFactory.getLogger(CreateModerationExample.class);

	public static void main(String[] args) {
		// Request data
		Moderation moderation = OPEN_AI_CLIENT.createModeration(
				CreateModeration.newBuilder()
						.input(Collections.singletonList("Some bad text here ..."))
						.build()
		);
		// Log to console
		String prettyJson = prettyJson(moderation);
		log.info("\n---- Moderation ----\n{}", prettyJson);
	}
}