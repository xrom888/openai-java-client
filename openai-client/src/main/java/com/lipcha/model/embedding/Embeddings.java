package com.lipcha.model.embedding;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lipcha.model.Usage;

import java.util.*;

public class Embeddings {

	private final String object;
	private final List<Embedding> data;
	private final String model;
	private final Usage usage;

	@JsonCreator
	public Embeddings(@JsonProperty("object") String object,
					  @JsonProperty("data") List<Embedding> data,
					  @JsonProperty("model") String model,
					  @JsonProperty("usage") Usage usage) {
		this.object = object;
		this.data = data;
		this.model = model;
		this.usage = usage;
	}

	private Embeddings(Builder builder) {
		this.object = builder.object;
		this.data = builder.data;
		this.model = builder.model;
		this.usage = builder.usage;
	}

	public static class Builder {

		private String object;
		private List<Embedding> data;
		private String model;
		private Usage usage;

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder data(List<Embedding> data) {
			Objects.requireNonNull(data, "'data' should not be null");
			this.data = Collections.unmodifiableList(data);
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		public Builder usage(Usage usage) {
			this.usage = usage;
			return this;
		}

		public Embeddings build() {
			if (data == null) {
				data = Collections.emptyList();
			}
			return new Embeddings(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@JsonProperty("object")
	public String object() {
		return object;
	}

	@JsonProperty("data")
	public List<Embedding> data() {
		return data;
	}

	@JsonProperty("model")
	public String model() {
		return model;
	}

	@JsonProperty("usage")
	public Usage usage() {
		return usage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Embeddings that = (Embeddings) o;
		return Objects.equals(object, that.object) && Objects.equals(data, that.data) && Objects.equals(model, that.model) && Objects.equals(usage, that.usage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(object, data, model, usage);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Embeddings.class.getSimpleName() + "[", "]")
				.add("object='" + object + "'")
				.add("data=" + data)
				.add("model='" + model + "'")
				.add("usage=" + usage)
				.toString();
	}
}