package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.*;

/**
 * Params to create an embedding vector representing the input text.
 */
@JsonDeserialize(builder = CreateEmbeddings.Builder.class)
public class CreateEmbeddings {

	private final String model;
	private final List<String> input;
	private final String user;

	private CreateEmbeddings(Builder builder) {
		this.model = builder.model;
		this.input = builder.input;
		this.user = builder.user;
	}

	/**
	 * {@link CreateEmbeddings} builder class.
	 */
	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder {

		private String model;
		private List<String> input = Collections.emptyList();
		private String user;

		/**
		 * ID of the model to use. You can use the List models API to see all of your available models,
		 * or see our Model overview for descriptions of them.
		 * Required.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("model")
		public Builder model(String model) {
			this.model = model;
			return this;
		}

		/**
		 * Input text to get embeddings for, encoded as a string or array of tokens.
		 * To get embeddings for multiple inputs in a single request, pass an array of strings or array of token arrays.
		 * Each input must not exceed 8192 tokens in length.
		 * Required.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("input")
		public Builder input(List<String> input) {
			Objects.requireNonNull(input, "'input' should not be null");
			this.input = Collections.unmodifiableList(input);
			return this;
		}

		/**
		 * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. Learn more.
		 * Optional.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("user")
		public Builder user(String user) {
			this.user = user;
			return this;
		}

		/**
		 * @return New instance of {@link CreateEmbeddings} class.
		 * @throws NullPointerException If any of required parameters not set.
		 */
		public CreateEmbeddings build() {
			Objects.requireNonNull(model, "'model' is required");
			Objects.requireNonNull(input, "'input' is required");
			return new CreateEmbeddings(this);
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
	 * @return value set by {@link Builder#model(String)}
	 */
	@JsonProperty("model")
	public String model() {
		return model;
	}

	/**
	 * @return value set by {@link Builder#input(List)}
	 */
	@JsonProperty("input")
	public List<String> input() {
		return input;
	}

	/**
	 * @return value set by {@link Builder#user(String)}
	 */
	@JsonProperty("user")
	public Optional<String> user() {
		return Optional.ofNullable(user);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateEmbeddings that = (CreateEmbeddings) o;
		return Objects.equals(model, that.model) && Objects.equals(input, that.input) && Objects.equals(user, that.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(model, input, user);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateEmbeddings.class.getSimpleName() + "[", "]")
				.add("model='" + model + "'")
				.add("input=" + input)
				.add("user='" + user + "'")
				.toString();
	}
}
