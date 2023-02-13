package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.*;

/**
 * Params to create a job that fine-tunes a specified model from a given dataset.
 */
@JsonDeserialize(builder = CreateFineTune.Builder.class)
public class CreateFineTune {

	private final String trainingFile;
	private final String validationFile;
	private final String model;
	private final Integer nEpochs;
	private final Integer batchSize;
	private final Double learningRateMultiplier;
	private final Double promptLossWeight;
	private final Boolean computeClassificationMetrics;
	private final Integer classificationNClasses;
	private final String classificationPositiveClass;
	private final List<Double> classificationBetas;
	private final String suffix;

	private CreateFineTune(Builder builder) {
		this.trainingFile = builder.trainingFile;
		this.validationFile = builder.validationFile;
		this.model = builder.model;
		this.nEpochs = builder.nEpochs;
		this.batchSize = builder.batchSize;
		this.learningRateMultiplier = builder.learningRateMultiplier;
		this.promptLossWeight = builder.promptLossWeight;
		this.computeClassificationMetrics = builder.computeClassificationMetrics;
		this.classificationNClasses = builder.classificationNClasses;
		this.classificationPositiveClass = builder.classificationPositiveClass;
		this.classificationBetas = builder.classificationBetas;
		this.suffix = builder.suffix;
	}

	/**
	 * {@link CreateFineTune} builder class.
	 */
	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder {

		private String trainingFile;
		private String validationFile;
		private String model;
		private Integer nEpochs;
		private Integer batchSize;
		private Double learningRateMultiplier;
		private Double promptLossWeight;
		private Boolean computeClassificationMetrics;
		private Integer classificationNClasses;
		private String classificationPositiveClass;
		private List<Double> classificationBetas;
		private String suffix;

		/**
		 * See <a href="https://platform.openai.com/docs/api-reference/files/upload">upload file</a> for how to upload a file.
		 * Your dataset must be formatted as a JSONL file, where each training example is a JSON object with the keys "prompt" and "completion".
		 * Additionally, you must upload your file with the purpose fine-tune.
		 * See the <a href="https://platform.openai.com/docs/guides/fine-tuning/creating-training-data">fine-tuning guide</a> for more details.
		 *
		 * @param trainingFile
		 * 		  The ID of an uploaded file that contains training data. Required.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("training_file")
		public Builder trainingFile(String trainingFile) {
			this.trainingFile = trainingFile;
			return this;
		}

		/**
		 * If you provide this file, the data is used to generate validation metrics periodically during fine-tuning.
		 * These metrics can be viewed in the fine-tuning results file. Your train and validation data should be mutually exclusive.
		 * Your dataset must be formatted as a JSONL file, where each validation example is a JSON object with the keys "prompt" and "completion".
		 * Additionally, you must upload your file with the purpose fine-tune.
		 *
		 * @param validationFile
		 * 		  The ID of an uploaded file that contains validation data. Optional.
		 * @see <a href="https://platform.openai.com/docs/guides/fine-tuning/creating-training-data">See the fine-tuning guide for more details.</a>
		 * @return {@link Builder} instance
		 */
		@JsonProperty("validation_file")
		public Builder validationFile(String validationFile) {
			this.validationFile = validationFile;
			return this;
		}

		/**
		 * You can select one of "ada", "babbage", "curie", "davinci", or a fine-tuned model created after 2022-04-21.
		 * To learn more about these models, see the Models documentation.
		 *
		 * @param model
		 * 		  The name of the base model to fine-tune. Optional. Defaults to curie.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("model")
		public Builder model(String model) {
			this.model = model;
			return this;
		}

		/**
		 * An epoch refers to one full cycle through the training dataset.
		 *
		 * @param nEpochs The number of epochs to train the model for. Optional. Defaults to 4.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("n_epochs")
		public Builder nEpochs(Integer nEpochs) {
			this.nEpochs = nEpochs;
			return this;
		}

		/**
		 * The batch size is the number of training examples used to train a single forward and backward pass.
		 * By default, the batch size will be dynamically configured to be ~0.2% of the number of examples in the training set, capped at 256 - in general, we've found that larger batch sizes tend to work better for larger datasets.
		 *
		 * @param batchSize
		 * 		  The batch size to use for training. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("batch_size")
		public Builder batchSize(Integer batchSize) {
			this.batchSize = batchSize;
			return this;
		}

		/**
		 * The fine-tuning learning rate is the original learning rate used for pretraining multiplied by this value.
		 * By default, the learning rate multiplier is the 0.05, 0.1, or 0.2 depending on final batch_size (larger learning rates tend to perform better with larger batch sizes).
		 * We recommend experimenting with values in the range 0.02 to 0.2 to see what produces the best results.
		 *
		 * @param learningRateMultiplier
		 * 		  The learning rate multiplier to use for training. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("learning_rate_multiplier")
		public Builder learningRateMultiplier(Double learningRateMultiplier) {
			this.learningRateMultiplier = learningRateMultiplier;
			return this;
		}

		/**
		 * This controls how much the model tries to learn to generate the prompt (as compared to the completion which always has a weight of 1.0), and can add a stabilizing effect to training when completions are short.
		 * If prompts are extremely long (relative to completions), it may make sense to reduce this weight so as to avoid over-prioritizing learning the prompt.
		 *
		 * @param promptLossWeight
		 * 		  The weight to use for loss on the prompt tokens. Optional. Defaults to 0.01.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("prompt_loss_weight")
		public Builder promptLossWeight(Double promptLossWeight) {
			this.promptLossWeight = promptLossWeight;
			return this;
		}

		/**
		 * These metrics can be viewed in the results file.
		 * In order to compute classification metrics, you must provide a validation_file. Additionally, you must specify classification_n_classes for multiclass classification or classification_positive_class for binary classification.
		 *
		 * @param computeClassificationMetrics
		 * 		  If set, we calculate classification-specific metrics such as accuracy and F-1 score using the validation set at the end of every epoch. Optional. Defaults to false.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("compute_classification_metrics")
		public Builder computeClassificationMetrics(Boolean computeClassificationMetrics) {
			this.computeClassificationMetrics = computeClassificationMetrics;
			return this;
		}

		/**
		 * This parameter is required for multiclass classification.
		 *
		 * @param classificationNClasses
		 * 		  The number of classes in a classification task. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("classification_n_classes")
		public Builder classificationNClasses(Integer classificationNClasses) {
			this.classificationNClasses = classificationNClasses;
			return this;
		}

		/**
		 * This parameter is needed to generate precision, recall, and F1 metrics when doing binary classification.
		 *
		 * @param classificationPositiveClass
		 * 		  The positive class in binary classification. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("classification_positive_class")
		public Builder classificationPositiveClass(String classificationPositiveClass) {
			this.classificationPositiveClass = classificationPositiveClass;
			return this;
		}

		/**
		 * The F-beta score is a generalization of F-1 score. This is only used for binary classification.
		 * With a beta of 1 (i.e. the F-1 score), precision and recall are given the same weight. A larger beta score puts more weight on recall and less on precision.
		 * A smaller beta score puts more weight on precision and less on recall.
		 *
		 * @param classificationBetas
		 * 		  If this is provided, we calculate F-beta scores at the specified beta values. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("classification_betas")
		public Builder classificationBetas(List<Double> classificationBetas) {
			Objects.requireNonNull(classificationBetas, "'classificationBetas' should not be null");
			this.classificationBetas = Collections.unmodifiableList(classificationBetas);
			return this;
		}

		/**
		 * For example, a suffix of "custom-model-name" would produce a model name like ada:ft-your-org:custom-model-name-2022-02-15-04-21-04.
		 *
		 * @param suffix
		 * 		  A string of up to 40 characters that will be added to your fine-tuned model name. Optional. Defaults to null.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("suffix")
		public Builder suffix(String suffix) {
			this.suffix = suffix;
			return this;
		}

		/**
		 * @return New instance of {@link CreateFineTune} class.
		 * @throws NullPointerException If any of required parameters not set.
		 */
		public CreateFineTune build() {
			Objects.requireNonNull(trainingFile, "'trainingFile' is required");
			if (classificationBetas == null) {
				classificationBetas = Collections.emptyList();
			}
			return new CreateFineTune(this);
		}
	}

	/**
	 * Creates a new instance of {@link Builder}.
	 * @return New instance of {@link Builder}.
	 */
	public static Builder newBuilder() {
		return new Builder();
	}

	/**
	 * @return value set by {@link Builder#trainingFile(String)}
	 */
	@JsonProperty("training_file")
	public String trainingFile() {
		return trainingFile;
	}

	/**
	 * @return value set by {@link Builder#validationFile(String)}
	 */
	@JsonProperty("validation_file")
	public Optional<String> validationFile() {
		return Optional.ofNullable(validationFile);
	}

	/**
	 * @return value set by {@link Builder#model(String)}
	 */
	@JsonProperty("model")
	public Optional<String> model() {
		return Optional.ofNullable(model);
	}

	/**
	 * @return value set by {@link Builder#nEpochs(Integer)}
	 */
	@JsonProperty("n_epochs")
	public Optional<Integer> nEpochs() {
		return Optional.ofNullable(nEpochs);
	}

	/**
	 * @return value set by {@link Builder#batchSize(Integer)}
	 */
	@JsonProperty("batch_size")
	public Optional<Integer> batchSize() {
		return Optional.ofNullable(batchSize);
	}

	/**
	 * @return value set by {@link Builder#learningRateMultiplier(Double)}
	 */
	@JsonProperty("learning_rate_multiplier")
	public Optional<Double> learningRateMultiplier() {
		return Optional.ofNullable(learningRateMultiplier);
	}

	/**
	 * @return value set by {@link Builder#promptLossWeight(Double)}
	 */
	@JsonProperty("prompt_loss_weight")
	public Optional<Double> promptLossWeight() {
		return Optional.ofNullable(promptLossWeight);
	}

	/**
	 * @return value set by {@link Builder#computeClassificationMetrics(Boolean)}
	 */
	@JsonProperty("compute_classification_metrics")
	public Optional<Boolean> computeClassificationMetrics() {
		return Optional.ofNullable(computeClassificationMetrics);
	}

	/**
	 * @return value set by {@link Builder#classificationNClasses(Integer)}
	 */
	@JsonProperty("classification_n_classes")
	public Optional<Integer> classificationNClasses() {
		return Optional.ofNullable(classificationNClasses);
	}

	/**
	 * @return value set by {@link Builder#classificationPositiveClass(String)}
	 */
	@JsonProperty("classification_positive_class")
	public Optional<String> classificationPositiveClass() {
		return Optional.ofNullable(classificationPositiveClass);
	}

	/**
	 * @return value set by {@link Builder#classificationBetas(List)}
	 */
	@JsonProperty("classification_betas")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public List<Double> classificationBetas() {
		return classificationBetas;
	}

	/**
	 * @return value set by {@link Builder#suffix(String)}
	 */
	@JsonProperty("suffix")
	public Optional<String> suffix() {
		return Optional.ofNullable(suffix);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateFineTune that = (CreateFineTune) o;
		return Objects.equals(trainingFile, that.trainingFile) && Objects.equals(validationFile, that.validationFile) && Objects.equals(model, that.model) && Objects.equals(nEpochs, that.nEpochs) && Objects.equals(batchSize, that.batchSize) && Objects.equals(learningRateMultiplier, that.learningRateMultiplier) && Objects.equals(promptLossWeight, that.promptLossWeight) && Objects.equals(computeClassificationMetrics, that.computeClassificationMetrics) && Objects.equals(classificationNClasses, that.classificationNClasses) && Objects.equals(classificationPositiveClass, that.classificationPositiveClass) && Objects.equals(classificationBetas, that.classificationBetas) && Objects.equals(suffix, that.suffix);
	}

	@Override
	public int hashCode() {
		return Objects.hash(trainingFile, validationFile, model, nEpochs, batchSize, learningRateMultiplier, promptLossWeight, computeClassificationMetrics, classificationNClasses, classificationPositiveClass, classificationBetas, suffix);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateFineTune.class.getSimpleName() + "[", "]")
				.add("trainingFile='" + trainingFile + "'")
				.add("validationFile='" + validationFile + "'")
				.add("model='" + model + "'")
				.add("nEpochs=" + nEpochs)
				.add("batchSize=" + batchSize)
				.add("learningRateMultiplier=" + learningRateMultiplier)
				.add("promptLossWeight=" + promptLossWeight)
				.add("computeClassificationMetrics=" + computeClassificationMetrics)
				.add("classificationNClasses=" + classificationNClasses)
				.add("classificationPositiveClass='" + classificationPositiveClass + "'")
				.add("classificationBetas=" + classificationBetas)
				.add("suffix='" + suffix + "'")
				.toString();
	}
}