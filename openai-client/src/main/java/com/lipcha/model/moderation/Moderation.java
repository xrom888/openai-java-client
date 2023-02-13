package com.lipcha.model.moderation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Moderation {

	private final String id;
	private final String model;
	private final List<ModerationResult> results;

	@JsonCreator
	public Moderation(@JsonProperty("id") String id,
					  @JsonProperty("model") String model,
					  @JsonProperty("results") List<ModerationResult> results) {
		this.id = id;
		this.model = model;
		this.results = results;
	}

	private Moderation(Builder builder) {
		this.id = builder.id;
		this.model = builder.model;
		this.results = builder.results;
	}

	public static class Builder {

		private String id;
		private String model;
		private List<ModerationResult> results;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		public Builder results(List<ModerationResult> results) {
			Objects.requireNonNull(results, "'results' should not be null");
			this.results = Collections.unmodifiableList(results);
			return this;
		}

		public Moderation build() {
			if (results == null) {
				results = Collections.emptyList();
			}
			return new Moderation(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("id")
	public String id() {
		return id;
	}

	@JsonProperty("model")
	public String model() {
		return model;
	}

	@JsonProperty("results")
	public List<ModerationResult> results() {
		return results;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Moderation that = (Moderation) o;
		return Objects.equals(id, that.id) && Objects.equals(model, that.model) && Objects.equals(results, that.results);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, model, results);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Moderation.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("model='" + model + "'")
				.add("results=" + results)
				.toString();
	}
}
