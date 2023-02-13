package com.lipcha.model.finetune;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lipcha.parser.LocalDateTimeDeserializer;
import com.lipcha.parser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class FineTuneEvent {

	private final String object;
	private final LocalDateTime createdAt;
	private final String level;
	private final String message;

	@JsonCreator
	public FineTuneEvent(@JsonProperty("object") String object,
						 @JsonProperty("created_at") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime createdAt,
						 @JsonProperty("level") String level,
						 @JsonProperty("message") String message) {
		this.object = object;
		this.createdAt = createdAt;
		this.level = level;
		this.message = message;
	}

	private FineTuneEvent(Builder builder) {
		this.object = builder.object;
		this.createdAt = builder.createdAt;
		this.level = builder.level;
		this.message = builder.message;
	}

	public static class Builder {

		private String object;
		private LocalDateTime createdAt;
		private String level;
		private String message;

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder level(String level) {
			this.level = level;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public FineTuneEvent build() {
			return new FineTuneEvent(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("object")
	public String object() {
		return object;
	}

	@JsonProperty("created_at")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime createdAt() {
		return createdAt;
	}

	@JsonProperty("level")
	public String level() {
		return level;
	}

	@JsonProperty("message")
	public String message() {
		return message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FineTuneEvent event = (FineTuneEvent) o;
		return Objects.equals(object, event.object) && Objects.equals(createdAt, event.createdAt) && Objects.equals(level, event.level) && Objects.equals(message, event.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(object, createdAt, level, message);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", FineTuneEvent.class.getSimpleName() + "[", "]")
				.add("object='" + object + "'")
				.add("createdAt=" + createdAt)
				.add("level='" + level + "'")
				.add("message='" + message + "'")
				.toString();
	}
}