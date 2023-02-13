package com.lipcha.model.finetune;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lipcha.model.file.File;
import com.lipcha.parser.LocalDateTimeDeserializer;
import com.lipcha.parser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.*;


public class FineTune {

	private final String id;
	private final String object;
	private final String model;
	private final LocalDateTime createdAt;
	private final List<FineTuneEvent> events;
	private final String fineTunedModel;
	private final HyperParameters hyperparams;
	private final String organizationId;
	private final List<File> resultFiles;
	private final String status;
	private final List<File> validationFiles;
	private final List<File> trainingFiles;
	private final LocalDateTime updatedAt;

	@JsonCreator
	public FineTune(@JsonProperty("id") String id,
					@JsonProperty("object") String object,
					@JsonProperty("model") String model,
					@JsonProperty("created_at") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime createdAt,
					@JsonProperty("events") List<FineTuneEvent> events,
					@JsonProperty("fine_tuned_model") String fineTunedModel,
					@JsonProperty("hyperparams") HyperParameters hyperparams,
					@JsonProperty("organization_id") String organizationId,
					@JsonProperty("result_files") List<File> resultFiles,
					@JsonProperty("status") String status,
					@JsonProperty("validation_files") List<File> validationFiles,
					@JsonProperty("training_files") List<File> trainingFiles,
					@JsonProperty("updated_at") @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime updatedAt) {
		this.id = id;
		this.object = object;
		this.model = model;
		this.createdAt = createdAt;
		this.events = events;
		this.fineTunedModel = fineTunedModel;
		this.hyperparams = hyperparams;
		this.organizationId = organizationId;
		this.resultFiles = resultFiles;
		this.status = status;
		this.validationFiles = validationFiles;
		this.trainingFiles = trainingFiles;
		this.updatedAt = updatedAt;
	}

	private FineTune(Builder builder) {
		this.id = builder.id;
		this.object = builder.object;
		this.model = builder.model;
		this.createdAt = builder.createdAt;
		this.events = builder.events;
		this.fineTunedModel = builder.fineTunedModel;
		this.hyperparams = builder.hyperparams;
		this.organizationId = builder.organizationId;
		this.resultFiles = builder.resultFiles;
		this.status = builder.status;
		this.validationFiles = builder.validationFiles;
		this.trainingFiles = builder.trainingFiles;
		this.updatedAt = builder.updatedAt;
	}

	public static class Builder {

		private String id;
		private String object;
		private String model;
		private LocalDateTime createdAt;
		private List<FineTuneEvent> events;
		private String fineTunedModel;
		private HyperParameters hyperparams;
		private String organizationId;
		private List<File> resultFiles;
		private String status;
		private List<File> validationFiles;
		private List<File> trainingFiles = Collections.emptyList();
		private LocalDateTime updatedAt;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder object(String object) {
			this.object = object;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder events(List<FineTuneEvent> events) {
			Objects.requireNonNull(events, "'events' should not be null");
			this.events = Collections.unmodifiableList(events);
			return this;
		}

		public Builder fineTunedModel(String fineTunedModel) {
			this.fineTunedModel = fineTunedModel;
			return this;
		}

		public Builder hyperparams(HyperParameters hyperparams) {
			this.hyperparams = hyperparams;
			return this;
		}

		public Builder organizationId(String organizationId) {
			this.organizationId = organizationId;
			return this;
		}

		public Builder resultFiles(List<File> resultFiles) {
			Objects.requireNonNull(resultFiles, "'resultFiles' should not be null");
			this.resultFiles = Collections.unmodifiableList(resultFiles);
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Builder validationFiles(List<File> validationFiles) {
			Objects.requireNonNull(validationFiles, "'validationFiles' should not be null");
			this.validationFiles = Collections.unmodifiableList(validationFiles);
			return this;
		}

		public Builder trainingFiles(List<File> trainingFiles) {
			Objects.requireNonNull(trainingFiles, "'trainingFiles' should not be null");
			this.trainingFiles = Collections.unmodifiableList(trainingFiles);
			return this;
		}

		public Builder updatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public FineTune build() {
			if (events == null) {
				events = Collections.emptyList();
			}
			if (resultFiles == null) {
				resultFiles = Collections.emptyList();
			}
			if (validationFiles == null) {
				validationFiles = Collections.emptyList();
			}
			if (trainingFiles == null) {
				trainingFiles = Collections.emptyList();
			}
			return new FineTune(this);
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

	@JsonProperty("model")
	public String model() {
		return model;
	}

	@JsonProperty("created_at")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime createdAt() {
		return createdAt;
	}

	@JsonProperty("events")
	public List<FineTuneEvent> events() {
		return events;
	}

	@JsonProperty("fine_tuned_model")
	public String fineTunedModel() {
		return fineTunedModel;
	}

	@JsonProperty("hyperparams")
	public HyperParameters hyperparams() {
		return hyperparams;
	}

	@JsonProperty("organization_id")
	public String organizationId() {
		return organizationId;
	}

	@JsonProperty("result_files")
	public List<File> resultFiles() {
		return resultFiles;
	}

	@JsonProperty("status")
	public String status() {
		return status;
	}

	@JsonProperty("validation_files")
	public List<File> validationFiles() {
		return validationFiles;
	}

	@JsonProperty("training_files")
	public List<File> trainingFiles() {
		return trainingFiles;
	}

	@JsonProperty("updated_at")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime updatedAt() {
		return updatedAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FineTune that = (FineTune) o;
		return Objects.equals(id, that.id) && Objects.equals(object, that.object) && Objects.equals(model, that.model) && Objects.equals(createdAt, that.createdAt) && Objects.equals(events, that.events) && Objects.equals(fineTunedModel, that.fineTunedModel) && Objects.equals(hyperparams, that.hyperparams) && Objects.equals(organizationId, that.organizationId) && Objects.equals(resultFiles, that.resultFiles) && Objects.equals(status, that.status) && Objects.equals(validationFiles, that.validationFiles) && Objects.equals(trainingFiles, that.trainingFiles) && Objects.equals(updatedAt, that.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, object, model, createdAt, events, fineTunedModel, hyperparams, organizationId, resultFiles, status, validationFiles, trainingFiles, updatedAt);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", FineTune.class.getSimpleName() + "[", "]")
				.add("id='" + id + "'")
				.add("object='" + object + "'")
				.add("model='" + model + "'")
				.add("createdAt=" + createdAt)
				.add("events=" + events)
				.add("fineTunedModel='" + fineTunedModel + "'")
				.add("hyperparams=" + hyperparams)
				.add("organizationId='" + organizationId + "'")
				.add("resultFiles=" + resultFiles)
				.add("status='" + status + "'")
				.add("validationFiles=" + validationFiles)
				.add("trainingFiles=" + trainingFiles)
				.add("updatedAt=" + updatedAt)
				.toString();
	}
}