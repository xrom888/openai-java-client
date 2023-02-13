package com.lipcha.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public final class ParseUtils {

	private ParseUtils() {
	}

	public static final ObjectMapper MAPPER = new ObjectMapper()
			.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
			.enable(DeserializationFeature.WRAP_EXCEPTIONS)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
			.registerModule(new Jdk8Module());

}