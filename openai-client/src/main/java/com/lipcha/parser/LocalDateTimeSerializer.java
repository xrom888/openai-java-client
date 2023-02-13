package com.lipcha.parser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	public LocalDateTimeSerializer() {
		this(null);
	}

	public LocalDateTimeSerializer(Class<LocalDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
		jsonGenerator.writeNumber(value.toEpochSecond(ZoneOffset.UTC));
	}

}