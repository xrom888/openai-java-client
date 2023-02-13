package com.lipcha.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

	public LocalDateTimeDeserializer() {
		this(null);
	}

    public LocalDateTimeDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext context) throws IOException {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(jp.getLongValue()), ZoneOffset.UTC);
	}

}