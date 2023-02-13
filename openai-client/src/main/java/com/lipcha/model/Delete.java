package com.lipcha.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Delete {

	private final String id;
	private final String object;
	private final Boolean deleted;

	@JsonCreator
	public Delete(@JsonProperty("id") String id,
				  @JsonProperty("object") String object,
				  @JsonProperty("deleted") Boolean deleted) {
		this.id = id;
		this.object = object;
		this.deleted = deleted;
	}

	private Delete(Builder builder) {
		this.id = builder.id;
		this.object = builder.object;
		this.deleted = builder.deleted;
	}

	public static class Builder {

		private String id;
		private String object;
		private Boolean deleted;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder deleted(Boolean deleted) {
			this.deleted = deleted;
			return this;
		}

		public Delete build() {
			return new Delete(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("id")
	public String id() {
		return id;
	}

	@JsonProperty("object")
	public String object() {
		return object;
	}

	@JsonProperty("deleted")
	public Boolean deleted() {
		return deleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Delete that = (Delete) o;
		return Objects.equals(id, that.id) && Objects.equals(object, that.object) && Objects.equals(deleted, that.deleted);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, object, deleted);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Delete.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("object='" + object + "'")
				.add("deleted=" + deleted)
				.toString();
	}
}