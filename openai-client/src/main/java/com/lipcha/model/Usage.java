package com.lipcha.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class Usage {

	private final Integer promptTokens;
	private final Integer completionTokens;
	private final Integer totalTokens;

	@JsonCreator
	public Usage(@JsonProperty("prompt_tokens") Integer promptTokens,
				 @JsonProperty("completion_tokens") Integer completionTokens,
				 @JsonProperty("total_tokens") Integer totalTokens) {
		this.promptTokens = promptTokens;
		this.completionTokens = completionTokens;
		this.totalTokens = totalTokens;
	}

	private Usage(Builder builder) {
		this.promptTokens = builder.promptTokens;
		this.completionTokens = builder.completionTokens;
		this.totalTokens = builder.totalTokens;
	}

	public static class Builder {

		private Integer promptTokens;
		private Integer completionTokens;
		private Integer totalTokens;

		public Builder promptTokens(Integer promptTokens) {
			this.promptTokens = promptTokens;
			return this;
		}

		public Builder completionTokens(Integer completionTokens) {
			this.completionTokens = completionTokens;
			return this;
		}

		public Builder totalTokens(Integer totalTokens) {
			this.totalTokens = totalTokens;
			return this;
		}

		public Usage build() {
			return new Usage(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("prompt_tokens")
	public Integer promptTokens() {
		return promptTokens;
	}

	@JsonProperty("completion_tokens")
	public Optional<Integer> completionTokens() {
		return Optional.ofNullable(completionTokens);
	}

	@JsonProperty("total_tokens")
	public Integer totalTokens() {
		return totalTokens;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usage usage = (Usage) o;
		return Objects.equals(promptTokens, usage.promptTokens) && Objects.equals(completionTokens, usage.completionTokens) && Objects.equals(totalTokens, usage.totalTokens);
	}

	@Override
	public int hashCode() {
		return Objects.hash(promptTokens, completionTokens, totalTokens);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Usage.class.getSimpleName() + "[", "]")
				.add("promptTokens=" + promptTokens)
				.add("completionTokens=" + completionTokens)
				.add("totalTokens=" + totalTokens)
				.toString();
	}
}