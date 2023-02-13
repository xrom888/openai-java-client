package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Objects;

/**
 * The size of the generated images.
 */
public enum ImageSize {

	S_256_256("256x256"),
	S_512_512("512x512"),
	S_1024_1024("1024x1024"),
	UNKNOWN("UNKNOWN");

	private final String value;

	ImageSize(String value) {
		this.value = Objects.requireNonNull(value);
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public ImageSize from(String value) {
		return Arrays.stream(values())
				.filter(v -> v.value.equals(value))
				.findFirst()
				.orElse(UNKNOWN);
	}

}
