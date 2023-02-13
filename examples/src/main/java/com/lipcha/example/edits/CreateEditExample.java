package com.lipcha.example.edits;

import com.lipcha.model.edit.Edit;
import com.lipcha.request.CreateEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CreateEditExample {

	private static final Logger log = LoggerFactory.getLogger(CreateEditExample.class);

	public static void main(String[] args) {
		// Request data
		Edit edit = OPEN_AI_CLIENT.createEdit(
				CreateEdit.newBuilder()
						.model("text-davinci-edit-001")
						.input("What day of the wek is it?")
						.instruction("Fix the spelling mistakes")
						.build()
		);

		// Print to console
		String prettyJson = prettyJson(edit);
		log.info("\n---- Edit ----\n{}", prettyJson);
	}

}