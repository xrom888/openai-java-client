package com.lipcha.example.images;

import com.lipcha.model.image.Images;
import com.lipcha.request.CreateImage;
import com.lipcha.request.ImageSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.lipcha.example.ExamplesClientConfig.OPEN_AI_CLIENT;
import static com.lipcha.util.ParseUtils.prettyJson;

public class CreateImageExample {

	private static final Logger log = LoggerFactory.getLogger(CreateImageExample.class);

	public static void main(String[] args) {
		// Request data
		Images images = OPEN_AI_CLIENT.createImage(
				CreateImage.newBuilder()
						.prompt("Small plain in a sky")
						.n(1)
						.size(ImageSize.S_1024_1024)
						.build()
		);

		// Print to console
		String prettyJson = prettyJson(images);
		log.info("\n---- Images ----\n{}", prettyJson);
	}
}