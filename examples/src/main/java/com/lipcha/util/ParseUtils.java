package com.lipcha.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.lipcha.exception.OpenAIClientException;

public final class ParseUtils {

	private ParseUtils() {
	}

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
			.enable(DeserializationFeature.WRAP_EXCEPTIONS)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
			.registerModule(new Jdk8Module());

	public static String prettyJson(Object o) {
		try {
			return ParseUtils.MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new OpenAIClientException("Cannot parse object: " + o, e);
		}
	}

}