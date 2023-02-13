package com.lipcha.model.moderation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class ModerationResult {

	private final Categories categories;
	private final CategoryScores categoryScores;
	private final Boolean flagged;

	@JsonCreator
	public ModerationResult(@JsonProperty("categories") Categories categories,
							@JsonProperty("category_scores") CategoryScores categoryScores,
							@JsonProperty("flagged") Boolean flagged) {
		this.categories = categories;
		this.categoryScores = categoryScores;
		this.flagged = flagged;
	}

	private ModerationResult(Builder builder) {
		this.categories = builder.categories;
		this.categoryScores = builder.categoryScores;
		this.flagged = builder.flagged;
	}

	public static class Builder {

		private Categories categories;
		private CategoryScores categoryScores;
		private Boolean flagged;

		public Builder categories(Categories categories) {
			this.categories = categories;
			return this;
		}

		public Builder categoryScores(CategoryScores categoryScores) {
			this.categoryScores = categoryScores;
			return this;
		}

		public Builder flagged(Boolean flagged) {
			this.flagged = flagged;
			return this;
		}

		public ModerationResult build() {
			return new ModerationResult(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("categories")
	public Categories categories() {
		return categories;
	}

	@JsonProperty("category_scores")
	public CategoryScores categoryScores() {
		return categoryScores;
	}

	@JsonProperty("flagged")
	public Boolean flagged() {
		return flagged;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ModerationResult that = (ModerationResult) o;
		return Objects.equals(categories, that.categories) && Objects.equals(categoryScores, that.categoryScores) && Objects.equals(flagged, that.flagged);
	}

	@Override
	public int hashCode() {
		return Objects.hash(categories, categoryScores, flagged);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", ModerationResult.class.getSimpleName() + "[", "]")
				.add("categories=" + categories)
				.add("categoryScores=" + categoryScores)
				.add("flagged=" + flagged)
				.toString();
	}
}