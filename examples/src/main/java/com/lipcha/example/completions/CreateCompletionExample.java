package com.lipcha.example.completions;

import com.lipcha.model.completion.Completion;
import com.lipcha.request.CreateCompletion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CreateCompletionExample {

	private static final Logger log = LoggerFactory.getLogger(CreateCompletionExample.class);

	public static void main(String[] args) {
		// Request data
		Completion completion = OPEN_AI_CLIENT.createCompletion(
				CreateCompletion.newBuilder()
						.model("text-davinci-003")
						.prompt(Collections.singletonList("Say this is a test"))
						.maxTokens(7)
						.temperature(0)
						.topP(1)
						.n(1)
						.stream(false)
						.stop(Collections.singletonList("\n"))
						.build()
		);

		// Print to console
		String prettyJson = prettyJson(completion);
		log.info("\n---- Completion ----\n{}", prettyJson);
	}

}