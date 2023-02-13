package com.lipcha.model.moderation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class CategoryScores {

	private final Double hate;
	private final Double hateThreatening;
	private final Double selfHarm;
	private final Double sexual;
	private final Double sexualMinors;
	private final Double violence;
	private final Double violenceGraphic;

	@JsonCreator
	public CategoryScores(@JsonProperty("hate") Double hate,
						  @JsonProperty("hate/threatening") Double hateThreatening,
						  @JsonProperty("self-harm") Double selfHarm,
						  @JsonProperty("sexual") Double sexual,
						  @JsonProperty("sexual/minors") Double sexualMinors,
						  @JsonProperty("violence") Double violence,
						  @JsonProperty("violence/graphic") Double violenceGraphic) {
		this.hate = hate;
		this.hateThreatening = hateThreatening;
		this.selfHarm = selfHarm;
		this.sexual = sexual;
		this.sexualMinors = sexualMinors;
		this.violence = violence;
		this.violenceGraphic = violenceGraphic;
	}

	private CategoryScores(Builder builder) {
		this.hate = builder.hate;
		this.hateThreatening = builder.hateThreatening;
		this.selfHarm = builder.selfHarm;
		this.sexual = builder.sexual;
		this.sexualMinors = builder.sexualMinors;
		this.violence = builder.violence;
		this.violenceGraphic = builder.violenceGraphic;
	}

	public static class Builder {

		private Double hate;
		private Double hateThreatening;
		private Double selfHarm;
		private Double sexual;
		private Double sexualMinors;
		private Double violence;
		private Double violenceGraphic;

		public Builder hate(Double hate) {
			this.hate = hate;
			return this;
		}

		public Builder hateThreatening(Double hateThreatening) {
			this.hateThreatening = hateThreatening;
			return this;
		}

		public Builder selfHarm(Double selfHarm) {
			this.selfHarm = selfHarm;
			return this;
		}

		public Builder sexual(Double sexual) {
			this.sexual = sexual;
			return this;
		}

		public Builder sexualMinors(Double sexualMinors) {
			this.sexualMinors = sexualMinors;
			return this;
		}

		public Builder violence(Double violence) {
			this.violence = violence;
			return this;
		}

		public Builder violenceGraphic(Double violenceGraphic) {
			this.violenceGraphic = violenceGraphic;
			return this;
		}

		public CategoryScores build() {
			return new CategoryScores(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("hate")
	public Double hate() {
		return hate;
	}

	@JsonProperty("hate/threatening")
	public Double hateThreatening() {
		return hateThreatening;
	}

	@JsonProperty("self-harm")
	public Double selfHarm() {
		return selfHarm;
	}

	@JsonProperty("sexual")
	public Double sexual() {
		return sexual;
	}

	@JsonProperty("sexual/minors")
	public Double sexualMinors() {
		return sexualMinors;
	}

	@JsonProperty("violence")
	public Double violence() {
		return violence;
	}

	@JsonProperty("violence/graphic")
	public Double violenceGraphic() {
		return violenceGraphic;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CategoryScores that = (CategoryScores) o;
		return Objects.equals(hate, that.hate) && Objects.equals(hateThreatening, that.hateThreatening) && Objects.equals(selfHarm, that.selfHarm) && Objects.equals(sexual, that.sexual) && Objects.equals(sexualMinors, that.sexualMinors) && Objects.equals(violence, that.violence) && Objects.equals(violenceGraphic, that.violenceGraphic);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hate, hateThreatening, selfHarm, sexual, sexualMinors, violence, violenceGraphic);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CategoryScores.class.getSimpleName() + "[", "]")
				.add("hate=" + hate)
				.add("hateThreatening=" + hateThreatening)
				.add("selfHarm=" + selfHarm)
				.add("sexual=" + sexual)
				.add("sexualMinors=" + sexualMinors)
				.add("violence=" + violence)
				.add("violenceGraphic=" + violenceGraphic)
				.toString();
	}
}