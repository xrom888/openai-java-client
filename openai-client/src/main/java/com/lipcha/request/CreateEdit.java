package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Params to create an edit.
 */
@JsonDeserialize(builder = CreateEdit.Builder.class)
public class CreateEdit {

	private final String model;
	private final String input;
	private final String instruction;
	private final Integer n;
	private final Double temperature;
	private final Double topP;

	private CreateEdit(Builder builder) {
		this.model = builder.model;
		this.input = builder.input;
		this.instruction = builder.instruction;
		this.n = builder.n;
		this.temperature = builder.temperature;
		this.topP = builder.topP;
	}

	/**
	 * {@link CreateEdit} builder class.
	 */
	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder {

		private String model;
		private String input;
		private String instruction;
		private Integer n;
		private Double temperature;
		private Double topP;

		/**
		 * You can use the text-davinci-edit-001 or code-davinci-edit-001 model with this endpoint.
		 *
		 * @param model
		 * 		  ID of the model to use. <b>Required</b>.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("model")
		public Builder model(String model) {
			this.model = model;
			return this;
		}

		/**
		 * @param input
		 * 		  The input text to use as a starting point for the edit. Optional. Defaults to "".
		 * @return {@link Builder} instance
		 */
		@JsonProperty("input")
		public Builder input(String input) {
			this.input = input;
			return this;
		}

		/**
		 * @param instruction
		 * 		  The instruction that tells the model how to edit the prompt. <b>Required</b>.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("instruction")
		public Builder instruction(String instruction) {
			this.instruction = instruction;
			return this;
		}

		/**
		 * @param n
		 * 		  How many edits to generate for the input and instruction. Optional. Defaults to 1.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("n")
		public Builder n(Integer n) {
			this.n = n;
			return this;
		}

		/**
		 * Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
		 * We generally recommend altering this or top_p but not both.
		 *
		 * @param temperature
		 * 		  What sampling temperature to use. Higher values means the model will take more risks. Optional. Defaults to 1.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("temperature")
		public Builder temperature(Double temperature) {
			this.temperature = temperature;
			return this;
		}

		/**
		 * So 0.1 means only the tokens comprising the top 10% probability mass are considered.
		 * We generally recommend altering this or temperature but not both.
		 *
		 * @param topP
		 * 		  An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. Optional. Defaults to 1.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("top_p")
		public Builder topP(Double topP) {
			this.topP = topP;
			return this;
		}

		/**
		 * @return New instance of {@link CreateEdit} class.
		 * @throws NullPointerException If any of required parameters not set.
		 */
		public CreateEdit build() {
			Objects.requireNonNull(model, "'model' is required");
			Objects.requireNonNull(instruction, "'instruction' is required");
			return new CreateEdit(this);
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
	 * @return value set by {@link Builder#input(String)}
	 */
	@JsonProperty("input")
	public Optional<String> input() {
		return Optional.ofNullable(input);
	}

	/**
	 * @return value set by {@link Builder#instruction(String)}
	 */
	@JsonProperty("instruction")
	public String instruction() {
		return instruction;
	}

	/**
	 * @return value set by {@link Builder#n(Integer)}
	 */
	@JsonProperty("n")
	public Optional<Integer> n() {
		return Optional.ofNullable(n);
	}

	/**
	 * @return value set by {@link Builder#temperature(Double)}
	 */
	@JsonProperty("temperature")
	public Optional<Double> temperature() {
		return Optional.ofNullable(temperature);
	}

	/**
	 * @return value set by {@link Builder#topP(Double)}
	 */
	@JsonProperty("top_p")
	public Optional<Double> topP() {
		return Optional.ofNullable(topP);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateEdit that = (CreateEdit) o;
		return Objects.equals(model, that.model) && Objects.equals(input, that.input) && Objects.equals(instruction, that.instruction) && Objects.equals(n, that.n) && Objects.equals(temperature, that.temperature) && Objects.equals(topP, that.topP);
	}

	@Override
	public int hashCode() {
		return Objects.hash(model, input, instruction, n, temperature, topP);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateEdit.class.getSimpleName() + "[", "]")
				.add("model='" + model + "'")
				.add("input='" + input + "'")
				.add("instruction='" + instruction + "'")
				.add("n=" + n)
				.add("temperature=" + temperature)
				.add("topP=" + topP)
				.toString();
	}
}
