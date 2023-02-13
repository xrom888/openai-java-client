package com.lipcha.model.completion;

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

public class Completion {

	private final String id;
	private final String object;
	private final LocalDateTime created;
	private final String model;
	private final List<Choice> choices;
	private final Usage usage;

	@JsonCreator
	public Completion(@JsonProperty("id") String id,
					  @JsonProperty("object") String object,
					  @JsonProperty("created") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime created,
					  @JsonProperty("model") String model,
					  @JsonProperty("choices") List<Choice> choices,
					  @JsonProperty("usage") Usage usage) {
		this.id = id;
		this.object = object;
		this.created = created;
		this.model = model;
		this.choices = choices;
		this.usage = usage;
	}

	private Completion(Builder builder) {
		this.id = builder.id;
		this.object = builder.object;
		this.created = builder.created;
		this.model = builder.model;
		this.choices = builder.choices;
		this.usage = builder.usage;
	}

	public static class Builder {

		private String id;
		private String object;
		private LocalDateTime created;
		private String model;
		private List<Choice> choices;
		private Usage usage;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder created(LocalDateTime created) {
			this.created = created;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
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

		public Completion build() {
			if (choices == null) {
				choices = Collections.emptyList();
			}
			return new Completion(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@JsonProperty("id")
	public String id() {
		return id;
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

	@JsonProperty("model")
	public String model() {
		return model;
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
		Completion that = (Completion) o;
		return Objects.equals(id, that.id) && Objects.equals(object, that.object) && Objects.equals(created, that.created) && Objects.equals(model, that.model) && Objects.equals(choices, that.choices) && Objects.equals(usage, that.usage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, object, created, model, choices, usage);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Completion.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("object='" + object + "'")
				.add("created=" + created)
				.add("model='" + model + "'")
				.add("choices=" + choices)
				.add("usage=" + usage)
				.toString();
	}
}