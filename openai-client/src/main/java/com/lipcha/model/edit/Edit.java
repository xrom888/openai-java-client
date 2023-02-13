package com.lipcha.model.edit;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lipcha.model.Choice;
import com.lipcha.model.Usage;
import com.lipcha.parser.LocalDateTimeDeserializer;
import com.lipcha.parser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.*;

public class Edit {

	private final String object;
	private final LocalDateTime created;
	private final List<Choice> choices;
	private final Usage usage;

	@JsonCreator
	public Edit(@JsonProperty("object") String object,
				@JsonProperty("created") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime created,
				@JsonProperty("choices") List<Choice> choices,
				@JsonProperty("usage") Usage usage) {
		this.object = object;
		this.created = created;
		this.choices = choices;
		this.usage = usage;
	}

	private Edit(Builder builder) {
		this.object = builder.object;
		this.created = builder.created;
		this.choices = builder.choices;
		this.usage = builder.usage;
	}

	public static class Builder {

		private String object;
		private LocalDateTime created;
		private List<Choice> choices;
		private Usage usage;

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder created(LocalDateTime created) {
			this.created = created;
			return this;
		}

		public Builder choices(List<Choice> choices) {
			Objects.requireNonNull(choices, "'choices' should not be null");
			this.choices = Collections.unmodifiableList(choices);
			return this;
		}

		public Builder usage(Usage usage) {
			this.usage = usage;
			return this;
		}

		public Edit build() {
			if (choices == null) {
				choices = Collections.emptyList();
			}
			return new Edit(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@JsonProperty("object")
	public String object() {
		return object;
	}

	@JsonProperty("created")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime created() {
		return created;
	}

	@JsonProperty("choices")
	public List<Choice> choices() {
		return choices;
	}

	@JsonProperty("usage")
	public Usage usage() {
		return usage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Edit edit = (Edit) o;
		return Objects.equals(object, edit.object) && Objects.equals(created, edit.created) && Objects.equals(choices, edit.choices) && Objects.equals(usage, edit.usage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(object, created, choices, usage);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Edit.class.getSimpleName() + "[", "]")
				.add("object='" + object + "'")
				.add("created=" + created)
				.add("choices=" + choices)
				.add("usage=" + usage)
				.toString();
	}
}