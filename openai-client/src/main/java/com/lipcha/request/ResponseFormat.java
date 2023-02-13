package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Objects;

/**
 * The format in which the generated images are returned.
 */
public enum ResponseFormat {

	URL("url"),
	B64_JSON("b64_json"),
	UNKNOWN("UNKNOWN");

	private final String value;

	ResponseFormat(String value) {
		this.value = Objects.requireNonNull(value);
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public ResponseFormat from(String value) {
		return Arrays.stream(values())
				.filter(v -> v.value.equals(value))
				.findFirst()
				.orElse(UNKNOWN);
	}
}