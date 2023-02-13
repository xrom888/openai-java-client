package com.lipcha.model.image;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class Image {

	private final URL url;
	private final String b64Json;

	@JsonCreator
	public Image(@JsonProperty("url") URL url,
				 @JsonProperty("b64_json") String b64Json) {
		this.url = url;
		this.b64Json = b64Json;
	}

	private Image(Builder builder) {
		this.url = builder.url;
		this.b64Json = builder.b64Json;
	}

	public static class Builder {

		private URL url;
		private String b64Json;

		public Builder url(URL url) {
			this.url = url;
			return this;
		}

		public Builder b64Json(String b64Json) {
			this.b64Json = b64Json;
			return this;
		}

		public Image build() {
			if (url == null && b64Json == null) {
				throw new NullPointerException("Only one of 'url', 'b64Json' should be set to non null value");
			} else if(url != null && b64Json != null) {
				throw new NullPointerException("Only one of 'url', 'b64Json' should not be set to non null value");
			}
			return new Image(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@JsonProperty("url")
	public Optional<URL> url() {
		return Optional.ofNullable(url);
	}

	@JsonProperty("b64_json")
	public Optional<String> b64Json() {
		return Optional.ofNullable(b64Json);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Image image = (Image) o;
		return Objects.equals(url, image.url) && Objects.equals(b64Json, image.b64Json);
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, b64Json);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Image.class.getSimpleName() + "[", "]")
				.add("url=" + url)
				.add("b64Json='" + b64Json + "'")
				.toString();
	}
}