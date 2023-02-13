package com.lipcha.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Choice {

	private final String text;
	private final Integer index;
	private final LogProb logProbs;
	private final String finishReason;

	@JsonCreator
	public Choice(@JsonProperty("text") String text,
				  @JsonProperty("index") Integer index,
				  @JsonProperty("logprobs") LogProb logProbs,
				  @JsonProperty("finish_reason") String finishReason) {
		this.text = text;
		this.index = index;
		this.logProbs = logProbs;
		this.finishReason = finishReason;
	}

	private Choice(Builder builder) {
		this.text = builder.text;
		this.index = builder.index;
		this.logProbs = builder.logProbs;
		this.finishReason = builder.finishReason;
	}

	public static class Builder {

		private String text;
		private Integer index;
		private LogProb logProbs;
		private String finishReason;

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public Builder index(Integer index) {
			this.index = index;
			return this;
		}

		public Builder logProbs(LogProb logProbs) {
			this.logProbs = logProbs;
			return this;
		}

		public Builder finishReason(String finishReason) {
			this.finishReason = finishReason;
			return this;
		}

		public Choice build() {
			return new Choice(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("text")
	public String text() {
		return text;
	}

	@JsonProperty("index")
	public Integer index() {
		return index;
	}

	@JsonProperty("logprobs")
	public LogProb logProbs() {
		return logProbs;
	}

	@JsonProperty("finish_reason")
	public String finishReason() {
		return finishReason;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Choice choice = (Choice) o;
		return Objects.equals(text, choice.text) && Objects.equals(index, choice.index) && Objects.equals(logProbs, choice.logProbs) && Objects.equals(finishReason, choice.finishReason);
	}

	@Override
	public int hashCode() {
		return Objects.hash(text, index, logProbs, finishReason);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Choice.class.getSimpleName() + "[", "]")
				.add("text='" + text + "'")
				.add("index=" + index)
				.add("logprobs=" + logProbs)
				.add("finishReason='" + finishReason + "'")
				.toString();
	}
}