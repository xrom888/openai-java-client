package com.lipcha.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class GenericResponse<T> {

	private final String object;
	private final List<T> data;

	@JsonCreator
	public GenericResponse(@JsonProperty("object") String object,
						   @JsonProperty("data") List<T> data) {
		this.object = object;
		this.data = data;
	}

	private GenericResponse(Builder<T> builder) {
		this.object = builder.object;
		this.data = builder.data;
	}

	public static class Builder<T> {

		private String object;
		private List<T> data;

		public Builder<T> object(String object) {
			this.object = object;
			return this;
		}

		public Builder<T> data(List<T> data) {
			Objects.requireNonNull(data, "'data' should not be null");
			this.data = Collections.unmodifiableList(data);
			return this;
		}

		public GenericResponse<T> build() {
			if (data == null) {
				data = Collections.emptyList();
			}
			return new GenericResponse<>(this);
		}
	}

	public static <T> Builder<T> newBuilder() {
		return new Builder<>();
	}
	
	@JsonProperty("object")
	public String object() {
		return object;
	}

	@JsonProperty("data")
	public List<T> data() {
		return data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GenericResponse<?> response = (GenericResponse<?>) o;
		return Objects.equals(object, response.object) && Objects.equals(data, response.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(object, data);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", GenericResponse.class.getSimpleName() + "[", "]")
				.add("object='" + object + "'")
				.add("data=" + data)
				.toString();
	}
}