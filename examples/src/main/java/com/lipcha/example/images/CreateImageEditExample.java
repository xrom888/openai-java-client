package com.lipcha.example.images;

import com.lipcha.model.image.Images;
import com.lipcha.request.CreateImageEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CreateImageEditExample {

	private static final Logger log = LoggerFactory.getLogger(CreateImageEditExample.class);

	public static void main(String[] args) {
		// Request data
		Images editedImages = OPEN_AI_CLIENT.createImageEdit(
				CreateImageEdit.newBuilder()
						.image("... path to image file ...")
						.mask("... path to image mask image file ...")
						.n(2)
						.prompt("Plain in sky on the right from the palm tree")
						.build()
		);

		// Print to console
		String prettyJson = prettyJson(editedImages);
		log.info("\n---- Edited images ----\n{}", prettyJson);
	}
}