package com.lipcha.model.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lipcha.parser.LocalDateTimeDeserializer;
import com.lipcha.parser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class Permission {

	private final String id;
	private final String object;
	private final LocalDateTime created;
	private final Boolean allowCreateEngine;
	private final Boolean allowSampling;
	private final Boolean allowLogprobs;
	private final Boolean allowSearchIndices;
	private final Boolean allowView;
	private final Boolean allowFineTuning;
	private final String organization;
	private final String group;
	private final Boolean isBlocking;

	@JsonCreator
	public Permission(@JsonProperty("id") String id,
					  @JsonProperty("object") String object,
					  @JsonProperty("created") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime created,
					  @JsonProperty("allow_create_engine") Boolean allowCreateEngine,
					  @JsonProperty("allow_sampling") Boolean allowSampling,
					  @JsonProperty("allow_logprobs") Boolean allowLogprobs,
					  @JsonProperty("allow_search_indices") Boolean allowSearchIndices,
					  @JsonProperty("allow_view") Boolean allowView,
					  @JsonProperty("allow_fine_tuning") Boolean allowFineTuning,
					  @JsonProperty("organization") String organization,
					  @JsonProperty("group") String group,
					  @JsonProperty("is_blocking") Boolean isBlocking) {
		this.id = id;
		this.object = object;
		this.created = created;
		this.allowCreateEngine = allowCreateEngine;
		this.allowSampling = allowSampling;
		this.allowLogprobs = allowLogprobs;
		this.allowSearchIndices = allowSearchIndices;
		this.allowView = allowView;
		this.allowFineTuning = allowFineTuning;
		this.organization = organization;
		this.group = group;
		this.isBlocking = isBlocking;
	}

	private Permission(Builder builder) {
		this.id = builder.id;
		this.object = builder.object;
		this.created = builder.created;
		this.allowCreateEngine = builder.allowCreateEngine;
		this.allowSampling = builder.allowSampling;
		this.allowLogprobs = builder.allowLogprobs;
		this.allowSearchIndices = builder.allowSearchIndices;
		this.allowView = builder.allowView;
		this.allowFineTuning = builder.allowFineTuning;
		this.organization = builder.organization;
		this.group = builder.group;
		this.isBlocking = builder.isBlocking;
	}

	public static class Builder {

		private String id;
		private String object;
		private LocalDateTime created;
		private Boolean allowCreateEngine;
		private Boolean allowSampling;
		private Boolean allowLogprobs;
		private Boolean allowSearchIndices;
		private Boolean allowView;
		private Boolean allowFineTuning;
		private String organization;
		private String group;
		private Boolean isBlocking;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder created(LocalDateTime created) {
			this.created = created;
			return this;
		}

		public Builder allowCreateEngine(Boolean allowCreateEngine) {
			this.allowCreateEngine = allowCreateEngine;
			return this;
		}

		public Builder allowSampling(Boolean allowSampling) {
			this.allowSampling = allowSampling;
			return this;
		}

		public Builder allowLogprobs(Boolean allowLogprobs) {
			this.allowLogprobs = allowLogprobs;
			return this;
		}

		public Builder allowSearchIndices(Boolean allowSearchIndices) {
			this.allowSearchIndices = allowSearchIndices;
			return this;
		}

		public Builder allowView(Boolean allowView) {
			this.allowView = allowView;
			return this;
		}

		public Builder allowFineTuning(Boolean allowFineTuning) {
			this.allowFineTuning = allowFineTuning;
			return this;
		}

		public Builder organization(String organization) {
			this.organization = organization;
			return this;
		}

		public Builder group(String group) {
			this.group = group;
			return this;
		}

		public Builder isBlocking(Boolean isBlocking) {
			this.isBlocking = isBlocking;
			return this;
		}

		public Permission build() {
			return new Permission(this);
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

	@JsonProperty("created")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime created() {
		return created;
	}

	@JsonProperty("allow_create_engine")
	public Boolean allowCreateEngine() {
		return allowCreateEngine;
	}

	@JsonProperty("allow_sampling")
	public Boolean allowSampling() {
		return allowSampling;
	}

	@JsonProperty("allow_logprobs")
	public Boolean allowLogprobs() {
		return allowLogprobs;
	}

	@JsonProperty("allow_search_indices")
	public Boolean allowSearchIndices() {
		return allowSearchIndices;
	}

	@JsonProperty("allow_view")
	public Boolean allowView() {
		return allowView;
	}

	@JsonProperty("allow_fine_tuning")
	public Boolean allowFineTuning() {
		return allowFineTuning;
	}

	@JsonProperty("organization")
	public String organization() {
		return organization;
	}

	@JsonProperty("group")
	public Optional<String> group() {
		return Optional.ofNullable(group);
	}

	@JsonProperty("is_blocking")
	public Boolean isBlocking() {
		return isBlocking;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Permission that = (Permission) o;
		return Objects.equals(id, that.id) && Objects.equals(object, that.object) && Objects.equals(created, that.created) && Objects.equals(allowCreateEngine, that.allowCreateEngine) && Objects.equals(allowSampling, that.allowSampling) && Objects.equals(allowLogprobs, that.allowLogprobs) && Objects.equals(allowSearchIndices, that.allowSearchIndices) && Objects.equals(allowView, that.allowView) && Objects.equals(allowFineTuning, that.allowFineTuning) && Objects.equals(organization, that.organization) && Objects.equals(group, that.group) && Objects.equals(isBlocking, that.isBlocking);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, object, created, allowCreateEngine, allowSampling, allowLogprobs, allowSearchIndices, allowView, allowFineTuning, organization, group, isBlocking);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Permission.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("object='" + object + "'")
				.add("created=" + created)
				.add("allowCreateEngine=" + allowCreateEngine)
				.add("allowSampling=" + allowSampling)
				.add("allowLogprobs=" + allowLogprobs)
				.add("allowSearchIndices=" + allowSearchIndices)
				.add("allowView=" + allowView)
				.add("allowFineTuning=" + allowFineTuning)
				.add("organization='" + organization + "'")
				.add("group='" + group + "'")
				.add("isBlocking=" + isBlocking)
				.toString();
	}
}