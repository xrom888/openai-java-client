package com.lipcha.model.finetune;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class HyperParameters {

	private final Integer batchSize;
	private final Double learningRateMultiplier;
	private final Integer nEpochs;
	private final Double promptLossWeight;

	@JsonCreator
	public HyperParameters(@JsonProperty("batch_size") Integer batchSize,
						   @JsonProperty("learning_rate_multiplier") Double learningRateMultiplier,
						   @JsonProperty("n_epochs") Integer nEpochs,
						   @JsonProperty("prompt_loss_weight") Double promptLossWeight) {
		this.batchSize = batchSize;
		this.learningRateMultiplier = learningRateMultiplier;
		this.nEpochs = nEpochs;
		this.promptLossWeight = promptLossWeight;
	}

	private HyperParameters(Builder builder) {
		this.batchSize = builder.batchSize;
		this.learningRateMultiplier = builder.learningRateMultiplier;
		this.nEpochs = builder.nEpochs;
		this.promptLossWeight = builder.promptLossWeight;
	}

	public static class Builder {

		private Integer batchSize;
		private Double learningRateMultiplier;
		private Integer nEpochs;
		private Double promptLossWeight;

		@JsonProperty("batch_size")
		public Builder batchSize(Integer batchSize) {
			this.batchSize = batchSize;
			return this;
		}

		@JsonProperty("learning_rate_multiplier")
		public Builder learningRateMultiplier(Double learningRateMultiplier) {
			this.learningRateMultiplier = learningRateMultiplier;
			return this;
		}

		@JsonProperty("n_epochs")
		public Builder nEpochs(Integer nEpochs) {
			this.nEpochs = nEpochs;
			return this;
		}

		@JsonProperty("prompt_loss_weight")
		public Builder promptLossWeight(Double promptLossWeight) {
			this.promptLossWeight = promptLossWeight;
			return this;
		}

		public HyperParameters build() {
			return new HyperParameters(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@JsonProperty("batch_size")
	public Integer batchSize() {
		return batchSize;
	}

	@JsonProperty("learning_rate_multiplier")
	public Double learningRateMultiplier() {
		return learningRateMultiplier;
	}

	@JsonProperty("n_epochs")
	public Integer nEpochs() {
		return nEpochs;
	}

	@JsonProperty("prompt_loss_weight")
	public Double promptLossWeight() {
		return promptLossWeight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HyperParameters that = (HyperParameters) o;
		return Objects.equals(batchSize, that.batchSize) && Objects.equals(learningRateMultiplier, that.learningRateMultiplier) && Objects.equals(nEpochs, that.nEpochs) && Objects.equals(promptLossWeight, that.promptLossWeight);
	}

	@Override
	public int hashCode() {
		return Objects.hash(batchSize, learningRateMultiplier, nEpochs, promptLossWeight);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", HyperParameters.class.getSimpleName() + "[", "]")
				.add("batchSize=" + batchSize)
				.add("learningRateMultiplier=" + learningRateMultiplier)
				.add("nEpochs=" + nEpochs)
				.add("promptLossWeight=" + promptLossWeight)
				.toString();
	}
}