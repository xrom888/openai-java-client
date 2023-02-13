package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.*;

/**
 * Params to create a moderation on a given input.
 */
@JsonDeserialize(builder = CreateModeration.Builder.class)
public class CreateModeration {

	private final List<String> input;
	private final String model;

	private CreateModeration(Builder builder) {
		this.input = builder.input;
		this.model = builder.model;
	}

	/**
	 * {@link CreateModeration} builder class.
	 */
	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder {

		private List<String> input = Collections.emptyList();
		private String model;

		/**
		 * @param input
		 * 		  The input text to classify. Required.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("input")
		public Builder input(List<String> input) {
			Objects.requireNonNull(input, "'input' should not be null");
			this.input = Collections.unmodifiableList(input);
			return this;
		}

		/**
		 * The default is text-moderation-latest which will be automatically upgraded over time.
		 * This ensures you are always using our most accurate model.
		 * If you use text-moderation-stable, we will provide advanced notice before updating the model.
		 * Accuracy of text-moderation-stable may be slightly lower than for text-moderation-latest.
		 *
		 * @param model
		 * 		  Two content moderations models are available: text-moderation-stable and text-moderation-latest. Optional. Defaults to text-moderation-latest.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("model")
		public Builder model(String model) {
			this.model = model;
			return this;
		}

		/**
		 * @return New instance of {@link CreateModeration} class.
		 * @throws NullPointerException If any of required parameters not set.
		 */
		public CreateModeration build() {
			Objects.requireNonNull(input, "'input' is required");
			if (input.isEmpty()) {
				throw new IllegalArgumentException("'input' should have min 1 element");
			}
			return new CreateModeration(this);
		}
	}

	/**
	 * Creates a new instance of {@link Builder}.
	 * @return New instance of {@link Builder}.
	 */
	public static Builder newBuilder() {
		return new Builder();
	}

	/**
	 * @return value set by {@link Builder#input(List)}
	 */
	@JsonProperty("input")
	public List<String> input() {
		return input;
	}

	/**
	 * @return value set by {@link Builder#model(String)}
	 */
	@JsonProperty("model")
	public String model() {
		return model;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateModeration that = (CreateModeration) o;
		return Objects.equals(input, that.input) && Objects.equals(model, that.model);
	}

	@Override
	public int hashCode() {
		return Objects.hash(input, model);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateModeration.class.getSimpleName() + "[", "]")
				.add("input=" + input)
				.add("model='" + model + "'")
				.toString();
	}
}