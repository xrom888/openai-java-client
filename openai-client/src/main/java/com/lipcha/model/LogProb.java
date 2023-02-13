package com.lipcha.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class LogProb {

	private final List<String> tokens;
	private final List<Double> tokenLogProbs;
	private final List<Map<String, Double>> topLogProbs;
	private final List<Integer> textOffset;

	@JsonCreator
	public LogProb(@JsonProperty("tokens") List<String> tokens,
				   @JsonProperty("token_logprobs") List<Double> tokenLogProbs,
				   @JsonProperty("top_logprobs") List<Map<String, Double>> topLogProbs,
				   @JsonProperty("text_offset") List<Integer> textOffset) {
		this.tokens = tokens;
		this.tokenLogProbs = tokenLogProbs;
		this.topLogProbs = topLogProbs;
		this.textOffset = textOffset;
	}

	private LogProb(Builder builder) {
		this.tokens = builder.tokens;
		this.tokenLogProbs = builder.tokenLogProbs;
		this.topLogProbs = builder.topLogProbs;
		this.textOffset = builder.textOffset;
	}

	public static class Builder {

		private List<String> tokens;
		private List<Double> tokenLogProbs;
		private List<Map<String, Double>> topLogProbs;
		private List<Integer> textOffset;

		public Builder tokens(List<String> tokens) {
			this.tokens = tokens;
			return this;
		}

		public Builder tokenLogProbs(List<Double> tokenLogProbs) {
			this.tokenLogProbs = tokenLogProbs;
			return this;
		}

		public Builder topLogProbs(List<Map<String, Double>> topLogProbs) {
			this.topLogProbs = topLogProbs;
			return this;
		}

		public Builder textOffset(List<Integer> textOffset) {
			this.textOffset = textOffset;
			return this;
		}

		public LogProb build() {
			if (tokens == null) {
				tokens = Collections.emptyList();
			}
			if (tokenLogProbs == null) {
				tokenLogProbs = Collections.emptyList();
			}
			if (topLogProbs == null) {
				topLogProbs = Collections.emptyList();
			}
			if (textOffset == null) {
				textOffset = Collections.emptyList();
			}
			return new LogProb(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("tokens")
	private List<String> tokens() {
		return tokens;
	}

	@JsonProperty("token_logprobs")
	private List<Double> tokenLogProbs() {
		return tokenLogProbs;
	}

	@JsonProperty("top_logprobs")
	private List<Map<String, Double>> topLogProbs() {
		return topLogProbs;
	}

	@JsonProperty("text_offset")
	private List<Integer> textOffset() {
		return textOffset;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LogProb logProb = (LogProb) o;
		return Objects.equals(tokens, logProb.tokens) && Objects.equals(tokenLogProbs, logProb.tokenLogProbs) && Objects.equals(topLogProbs, logProb.topLogProbs) && Objects.equals(textOffset, logProb.textOffset);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tokens, tokenLogProbs, topLogProbs, textOffset);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", LogProb.class.getSimpleName() + "[", "]")
				.add("tokens=" + tokens)
				.add("tokenLogProbs=" + tokenLogProbs)
				.add("topLogProbs=" + topLogProbs)
				.add("textOffset=" + textOffset)
				.toString();
	}
}
