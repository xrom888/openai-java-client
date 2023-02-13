package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.*;

/**
 * Params to create a completion.
 */
@JsonDeserialize(builder = CreateCompletion.Builder.class)
public class CreateCompletion {

	private final String model;
	private final List<String> prompt;
	private final String suffix;
	private final Integer maxTokens;
	private final Integer temperature;
	private final Integer topP;
	private final Integer n;
	private final Boolean stream;
	private final Integer logprobs;
	private final Boolean echo;
	private final List<String> stop;
	private final Double presencePenalty;
	private final Double frequencyPenalty;
	private final Integer bestOf;
	private final Map<String, Integer> logitBias;
	private final String user;

	private CreateCompletion(Builder builder) {
		this.model = builder.model;
		this.prompt = builder.prompt;
		this.suffix = builder.suffix;
		this.maxTokens = builder.maxTokens;
		this.temperature = builder.temperature;
		this.topP = builder.topP;
		this.n = builder.n;
		this.stream = builder.stream;
		this.logprobs = builder.logprobs;
		this.echo = builder.echo;
		this.stop = builder.stop;
		this.presencePenalty = builder.presencePenalty;
		this.frequencyPenalty = builder.frequencyPenalty;
		this.bestOf = builder.bestOf;
		this.logitBias = builder.logitBias;
		this.user = builder.user;
	}

	/**
	 * {@link CreateCompletion} builder class.
	 */
	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder {

		private String model;
		private List<String> prompt = Collections.emptyList();
		private String suffix;
		private Integer maxTokens;
		private Integer temperature;
		private Integer topP;
		private Integer n;
		private Boolean stream;
		private Integer logprobs;
		private Boolean echo;
		private List<String> stop = Collections.emptyList();
		private Double presencePenalty;
		private Double frequencyPenalty;
		private Integer bestOf;
		private Map<String, Integer> logitBias = Collections.unmodifiableMap(new HashMap<>(0));
		private String user;

		/**
		 * You can use the <a href="https://platform.openai.com/docs/api-reference/models/list">List models</a> API to see all of your available models, or see our <a href="https://platform.openai.com/docs/models/overview">Model overview</a> for descriptions of them.
		 *
		 *  @param model
		 *  	   String ID of the model to use. Required.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("model")
		public Builder model(String model) {
			this.model = model;
			return this;
		}

		/**
		 * Note that &lt;|endoftext|&gt; is the document separator that the model sees during training, so if a prompt is not specified the model will generate as if from the beginning of a new document.
		 *
		 * @param prompt
		 * 		  The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens, or array of token arrays. Optional. Defaults to &lt;|endoftext|&gt;.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("prompt")
		public Builder prompt(List<String> prompt) {
			Objects.requireNonNull(prompt, "'prompt' should not be null");
			this.prompt = Collections.unmodifiableList(prompt);
			return this;
		}

		/**
		 * @param suffix
		 * 		  The suffix that comes after a completion of inserted text. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("suffix")
		public Builder suffix(String suffix) {
			this.suffix = suffix;
			return this;
		}

		/**
		 * The token count of your prompt plus max_tokens cannot exceed the model's context length.
		 * Most models have a context length of 2048 tokens (except for the newest models, which support 4096).
		 *
		 * @param maxTokens
		 * 		  The maximum number of tokens to generate in the completion. Optional. Defaults to 16.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("max_tokens")
		public Builder maxTokens(Integer maxTokens) {
			this.maxTokens = maxTokens;
			return this;
		}

		/**
		 * Higher values means the model will take more risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
		 * We generally recommend altering this or top_p but not both.
		 *
		 * @param temperature
		 * 		  What sampling temperature to use. Optional. Defaults to 1.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("temperature")
		public Builder temperature(Integer temperature) {
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
		public Builder topP(Integer topP) {
			this.topP = topP;
			return this;
		}

		/**
		 * Note: Because this parameter generates many completions, it can quickly consume your token quota.
		 * Use carefully and ensure that you have reasonable settings for max_tokens and stop.
		 *
		 * @param n
		 * 		  How many completions to generate for each prompt. Optional. Defaults to 1.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("n")
		public Builder n(Integer n) {
			this.n = n;
			return this;
		}

		/**
		 * If set, tokens will be sent as data-only server-sent events as they become available, with the stream terminated by a data: [DONE] message.
		 *
		 * @param stream
		 * 		  Whether to stream back partial progress. Optional. Defaults to false.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("stream")
		public Builder stream(Boolean stream) {
			this.stream = stream;
			return this;
		}

		/**
		 * For example, if logprobs is 5, the API will return a list of the 5 most likely tokens. The API will always return the logprob of the sampled token, so there may be up to logprobs+1 elements in the response.
		 * The maximum value for logprobs is 5. If you need more than this, please contact us through our @see <a href="https://help.openai.com">Help center</a> and describe your use case.
		 *
		 * @param logprobs
		 *	  	  Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("logprobs")
		public Builder logprobs(Integer logprobs) {
			this.logprobs = logprobs;
			return this;
		}

		/**
		 * @param echo
		 * 		  Echo back the prompt in addition to the completion. Optional. Defaults to false.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("echo")
		public Builder echo(Boolean echo) {
			this.echo = echo;
			return this;
		}

		/**
		 * @param stop
		 * 		  Up to 4 sequences where the API will stop generating further tokens. The returned text will not contain the stop sequence. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 * @throws NullPointerException If param has null value
		 */
		@JsonProperty("stop")
		public Builder stop(List<String> stop) {
			Objects.requireNonNull(stop, "'stop' should not be null");
			this.stop = Collections.unmodifiableList(stop);
			return this;
		}

		/**
		 * Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.
		 *
		 * @param presencePenalty
		 * 		  Double number between -2.0 and 2.0. Optional. Defaults to 0.
		 * @return {@link Builder} instance
		 * @see <a href="https://platform.openai.com/docs/api-reference/parameter-details">See more information about frequency and presence penalties.</a>
		 */
		@JsonProperty("presence_penalty")
		public Builder presencePenalty(Double presencePenalty) {
			this.presencePenalty = presencePenalty;
			return this;
		}

		/**
		 * Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
		 *
		 * @param frequencyPenalty
		 * 		  {@link Double} number between -2.0 and 2.0. Optional. Defaults to 0.
		 * @return {@link Builder} instance
		 * @see <a href="https://platform.openai.com/docs/api-reference/parameter-details">See more information about frequency and presence penalties.</a>
		 */
		@JsonProperty("frequency_penalty")
		public Builder frequencyPenalty(Double frequencyPenalty) {
			this.frequencyPenalty = frequencyPenalty;
			return this;
		}

		/**
		 * Results cannot be streamed. When used with n, best_of controls the number of candidate completions and n specifies how many to return – best_of must be greater than n.
		 * Note: Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop.
		 *
		 * @param bestOf
		 * 		  Generates best_of completions server-side and returns the "best" (the one with the highest log probability per token). Optional. Defaults to 1.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("best_of")
		public Builder bestOf(Integer bestOf) {
			this.bestOf = bestOf;
			return this;
		}

		/**
		 * Mathematically, the bias is added to the logits generated by the model prior to sampling.
		 * The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token.
		 * As an example, you can pass {"50256": -100} to prevent the &lt;|endoftext|&gt; token from being generated.
		 * Modify the likelihood of specified tokens appearing in the completion. Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this tokenizer tool (which works for both GPT-2 and GPT-3) to convert text to token IDs.
		 *
		 * @param logitBias
		 * 		  Map with tokens as keys and bias values as values. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 * @throws NullPointerException If param has null value
		 */
		@JsonProperty("logit_bias")
		public Builder logitBias(Map<String, Integer> logitBias) {
			Objects.requireNonNull(logitBias, "'logitBias' should not be null");
			this.logitBias = Collections.unmodifiableMap(logitBias);
			return this;
		}

		/**
		 * @param user
		 * 		  A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more</a>. Optional.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("user")
		public Builder user(String user) {
			this.user = user;
			return this;
		}

		/**
		 * @return New instance of {@link CreateCompletion} class.
		 * @throws NullPointerException If any of required parameters not set.
		 */
		public CreateCompletion build() {
			Objects.requireNonNull(model, "'model' is required");
			return new CreateCompletion(this);
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
	 * @return value set by {@link CreateCompletion.Builder#model(String)}
	 */
	@JsonProperty("model")
	public String model() {
		return model;
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#prompt(List)}
	 */
	@JsonProperty("prompt")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public List<String> prompt() {
		return prompt;
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#suffix(String)}
	 */
	@JsonProperty("suffix")
	public Optional<String> suffix() {
		return Optional.ofNullable(suffix);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#maxTokens(Integer)}
	 */
	@JsonProperty("max_tokens")
	public Optional<Integer> maxTokens() {
		return Optional.ofNullable(maxTokens);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#temperature(Integer)}
	 */
	@JsonProperty("temperature")
	public Optional<Integer> temperature() {
		return Optional.ofNullable(temperature);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#topP(Integer)}
	 */
	@JsonProperty("top_p")
	public Optional<Integer> topP() {
		return Optional.ofNullable(topP);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#n(Integer)}
	 */
	@JsonProperty("n")
	public Optional<Integer> n() {
		return Optional.ofNullable(n);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#stream(Boolean)}
	 */
	@JsonProperty("stream")
	public Optional<Boolean> stream() {
		return Optional.ofNullable(stream);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#logprobs(Integer)}
	 */
	@JsonProperty("logprobs")
	public Optional<Integer> logprobs() {
		return Optional.ofNullable(logprobs);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#echo(Boolean)}
	 */
	@JsonProperty("echo")
	public Optional<Boolean> echo() {
		return Optional.ofNullable(echo);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#stop(List)}
	 */
	@JsonProperty("stop")
	public List<String> stop() {
		return stop;
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#presencePenalty(Double)}
	 */
	@JsonProperty("presence_penalty")
	public Optional<Double> presencePenalty() {
		return Optional.ofNullable(presencePenalty);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#frequencyPenalty(Double)}
	 */
	@JsonProperty("frequency_penalty")
	public Optional<Double> frequencyPenalty() {
		return Optional.ofNullable(frequencyPenalty);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#bestOf(Integer)}
	 */
	@JsonProperty("best_of")
	public Optional<Integer> bestOf() {
		return Optional.ofNullable(bestOf);
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#logitBias(Map)}
	 */
	@JsonProperty("logit_bias")
	public Map<String, Integer> logitBias() {
		return logitBias;
	}

	/**
	 * @return value set by {@link CreateCompletion.Builder#user(String)}
	 */
	@JsonProperty("user")
	public Optional<String> user() {
		return Optional.ofNullable(user);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateCompletion that = (CreateCompletion) o;
		return Objects.equals(model, that.model) && Objects.equals(prompt, that.prompt) && Objects.equals(suffix, that.suffix) && Objects.equals(maxTokens, that.maxTokens) && Objects.equals(temperature, that.temperature) && Objects.equals(topP, that.topP) && Objects.equals(n, that.n) && Objects.equals(stream, that.stream) && Objects.equals(logprobs, that.logprobs) && Objects.equals(echo, that.echo) && Objects.equals(stop, that.stop) && Objects.equals(presencePenalty, that.presencePenalty) && Objects.equals(frequencyPenalty, that.frequencyPenalty) && Objects.equals(bestOf, that.bestOf) && Objects.equals(logitBias, that.logitBias) && Objects.equals(user, that.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(model, prompt, suffix, maxTokens, temperature, topP, n, stream, logprobs, echo, stop, presencePenalty, frequencyPenalty, bestOf, logitBias, user);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateCompletion.class.getSimpleName() + "[", "]")
				.add("model='" + model + "'")
				.add("prompt=" + prompt)
				.add("suffix='" + suffix + "'")
				.add("maxTokens=" + maxTokens)
				.add("temperature=" + temperature)
				.add("topP=" + topP)
				.add("n=" + n)
				.add("stream=" + stream)
				.add("logprobs=" + logprobs)
				.add("echo=" + echo)
				.add("stop=" + stop)
				.add("presencePenalty=" + presencePenalty)
				.add("frequencyPenalty=" + frequencyPenalty)
				.add("bestOf=" + bestOf)
				.add("logitBias=" + logitBias)
				.add("user='" + user + "'")
				.toString();
	}
}