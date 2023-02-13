package com.lipcha.example.images;

import com.lipcha.model.image.Images;
import com.lipcha.request.CreateImageVariation;
import com.lipcha.request.ImageSize;
import com.lipcha.request.ResponseFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CreateImageVariationExample {

	private static final Logger log = LoggerFactory.getLogger(CreateImageVariationExample.class);

	public static void main(String[] args) {
		// Request data
		Images images = OPEN_AI_CLIENT.createImageVariation(
				CreateImageVariation.newBuilder()
						.image("... path to image file ...")
						.n(2)
						.responseFormat(ResponseFormat.URL)
						.size(ImageSize.S_1024_1024)
						.build()
		);

		// Print to console
		String prettyJson = prettyJson(images);
		log.info("\n---- Images ----\n{}", prettyJson);
	}
}