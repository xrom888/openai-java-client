package com.lipcha.model.file;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lipcha.parser.LocalDateTimeDeserializer;
import com.lipcha.parser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class UploadFile {

	private final String id;
	private final String object;
	private final Integer bytes;
	private final LocalDateTime createdAt;
	private final String filename;
	private final String purpose;

	@JsonCreator
	public UploadFile(@JsonProperty("id,") String id,
					  @JsonProperty("object") String object,
					  @JsonProperty("bytes") Integer bytes,
					  @JsonProperty("created_at") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime createdAt,
					  @JsonProperty("filename") String filename,
					  @JsonProperty("purpose") String purpose) {
		this.id = id;
		this.object = object;
		this.bytes = bytes;
		this.createdAt = createdAt;
		this.filename = filename;
		this.purpose = purpose;
	}

	private UploadFile(Builder builder) {
		this.id = builder.id;
		this.object = builder.object;
		this.bytes = builder.bytes;
		this.createdAt = builder.createdAt;
		this.filename = builder.filename;
		this.purpose = builder.purpose;
	}

	public static class Builder {

		private String id;
		private String object;
		private Integer bytes;
		private LocalDateTime createdAt;
		private String filename;
		private String purpose;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder bytes(Integer bytes) {
			this.bytes = bytes;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder filename(String filename) {
			this.filename = filename;
			return this;
		}

		public Builder purpose(String purpose) {
			this.purpose = purpose;
			return this;
		}

		public UploadFile build() {
			return new UploadFile(this);
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

	@JsonProperty("bytes")
	public Integer bytes() {
		return bytes;
	}

	@JsonProperty("created_at")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime createdAt() {
		return createdAt;
	}

	@JsonProperty("filename")
	public String filename() {
		return filename;
	}

	@JsonProperty("purpose")
	public String purpose() {
		return purpose;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UploadFile that = (UploadFile) o;
		return Objects.equals(id, that.id) && Objects.equals(object, that.object) && Objects.equals(bytes, that.bytes) && Objects.equals(createdAt, that.createdAt) && Objects.equals(filename, that.filename) && Objects.equals(purpose, that.purpose);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, object, bytes, createdAt, filename, purpose);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", UploadFile.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("object='" + object + "'")
				.add("bytes=" + bytes)
				.add("createdAt=" + createdAt)
				.add("filename='" + filename + "'")
				.add("purpose='" + purpose + "'")
				.toString();
	}
}