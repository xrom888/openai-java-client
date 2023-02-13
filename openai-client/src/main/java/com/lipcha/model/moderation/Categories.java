package com.lipcha.model.moderation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class Categories {

	private final Boolean hate;
	private final Boolean hateThreatening;
	private final Boolean selfHarm;
	private final Boolean sexual;
	private final Boolean sexualMinors;
	private final Boolean violence;
	private final Boolean violenceGraphic;

	@JsonCreator
	public Categories(@JsonProperty("hate") Boolean hate,
					  @JsonProperty("hate/threatening") Boolean hateThreatening,
					  @JsonProperty("self-harm") Boolean selfHarm,
					  @JsonProperty("sexual") Boolean sexual,
					  @JsonProperty("sexual/minors") Boolean sexualMinors,
					  @JsonProperty("violence") Boolean violence,
					  @JsonProperty("violence/graphic") Boolean violenceGraphic) {
		this.hate = hate;
		this.hateThreatening = hateThreatening;
		this.selfHarm = selfHarm;
		this.sexual = sexual;
		this.sexualMinors = sexualMinors;
		this.violence = violence;
		this.violenceGraphic = violenceGraphic;
	}

	private Categories(Builder builder) {
		this.hate = builder.hate;
		this.hateThreatening = builder.hateThreatening;
		this.selfHarm = builder.selfHarm;
		this.sexual = builder.sexual;
		this.sexualMinors = builder.sexualMinors;
		this.violence = builder.violence;
		this.violenceGraphic = builder.violenceGraphic;
	}

	public static class Builder {

		private Boolean hate;
		private Boolean hateThreatening;
		private Boolean selfHarm;
		private Boolean sexual;
		private Boolean sexualMinors;
		private Boolean violence;
		private Boolean violenceGraphic;

		public Builder hate(Boolean hate) {
			this.hate = hate;
			return this;
		}

		public Builder hateThreatening(Boolean hateThreatening) {
			this.hateThreatening = hateThreatening;
			return this;
		}

		public Builder selfHarm(Boolean selfHarm) {
			this.selfHarm = selfHarm;
			return this;
		}

		public Builder sexual(Boolean sexual) {
			this.sexual = sexual;
			return this;
		}

		public Builder sexualMinors(Boolean sexualMinors) {
			this.sexualMinors = sexualMinors;
			return this;
		}

		public Builder violence(Boolean violence) {
			this.violence = violence;
			return this;
		}

		public Builder violenceGraphic(Boolean violenceGraphic) {
			this.violenceGraphic = violenceGraphic;
			return this;
		}

		public Categories build() {
			return new Categories(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("hate")
	public Boolean hate() {
		return hate;
	}

	@JsonProperty("hate/threatening")
	public Boolean hateThreatening() {
		return hateThreatening;
	}

	@JsonProperty("self-harm")
	public Boolean selfHarm() {
		return selfHarm;
	}

	@JsonProperty("sexual")
	public Boolean sexual() {
		return sexual;
	}

	@JsonProperty("sexual/minors")
	public Boolean sexualMinors() {
		return sexualMinors;
	}

	@JsonProperty("violence")
	public Boolean violence() {
		return violence;
	}

	@JsonProperty("violence/graphic")
	public Boolean violenceGraphic() {
		return violenceGraphic;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Categories that = (Categories) o;
		return Objects.equals(hate, that.hate) && Objects.equals(hateThreatening, that.hateThreatening) && Objects.equals(selfHarm, that.selfHarm) && Objects.equals(sexual, that.sexual) && Objects.equals(sexualMinors, that.sexualMinors) && Objects.equals(violence, that.violence) && Objects.equals(violenceGraphic, that.violenceGraphic);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hate, hateThreatening, selfHarm, sexual, sexualMinors, violence, violenceGraphic);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Categories.class.getSimpleName() + "[", "]")
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