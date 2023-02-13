package com.lipcha.model.embedding;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Embedding {

	private final String object;
	private final Integer index;
	private final List<Double> embeddings;

	@JsonCreator
	public Embedding(@JsonProperty("object") String object,
					 @JsonProperty("index") Integer index,
					 @JsonProperty("embedding") List<Double> embeddings) {
		this.object = object;
		this.index = index;
		this.embeddings = embeddings;
	}

	private Embedding(Builder builder) {
		this.object = builder.object;
		this.index = builder.index;
		this.embeddings = builder.embeddings;
	}

	public static class Builder {

		private String object;
		private Integer index;
		private List<Double> embeddings;

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder index(Integer index) {
			this.index = index;
			return this;
		}

		public Builder embeddings(List<Double> embeddings) {
			Objects.requireNonNull(embeddings, "'embedding' should not be null");
			this.embeddings = Collections.unmodifiableList(embeddings);
			return this;
		}

		public Embedding build() {
			if (embeddings == null) {
				embeddings = Collections.emptyList();
			}
			return new Embedding(this);
		}
	}
	
	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("object")
	public String object() {
		return object;
	}

	@JsonProperty("index")
	public Integer index() {
		return index;
	}

	@JsonProperty("embedding")
	public List<Double> embeddings() {
		return embeddings;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Embedding embedding1 = (Embedding) o;
		return Objects.equals(object, embedding1.object) && Objects.equals(index, embedding1.index) && Objects.equals(embeddings, embedding1.embeddings);
	}

	@Override
	public int hashCode() {
		return Objects.hash(object, index, embeddings);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Embedding.class.getSimpleName() + "[", "]")
				.add("object='" + object + "'")
				.add("index=" + index)
				.add("embedding=" + embeddings)
				.toString();
	}
}