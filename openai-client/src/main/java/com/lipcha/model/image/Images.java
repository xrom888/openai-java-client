package com.lipcha.model.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lipcha.parser.LocalDateTimeDeserializer;
import com.lipcha.parser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.*;

public class Images {

	private final LocalDateTime created;
	private final List<Image> data;

	@JsonCreator
	public Images(@JsonProperty("created") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime created,
				  @JsonProperty("data") List<Image> data) {
		this.created = created;
		this.data = data;
	}

	private Images(Builder builder) {
		this.created = builder.created;
		this.data = builder.data;
	}

	public static class Builder {

		private LocalDateTime created;
		private List<Image> data;

		public Builder created(LocalDateTime created) {
			this.created = created;
			return this;
		}

		public Builder data(List<Image> data) {
			Objects.requireNonNull(data, "'data' should not be null");
			this.data = Collections.unmodifiableList(data);
			return this;
		}

		public Images build() {
			if (data == null) {
				data = Collections.emptyList();
			}
			return new Images(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("created")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime created() {
		return created;
	}

	@JsonProperty("data")
	public List<Image> data() {
		return data;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Images image = (Images) o;
		return Objects.equals(created, image.created) && Objects.equals(data, image.data);
	}

	@Override
	public int hashCode() {
		return Objects.hash(created, data);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Images.class.getSimpleName() + "[", "]")
				.add("created=" + created)
				.add("data=" + data)
				.toString();
	}
}