package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Params to create an image given a prompt.
 */
@JsonDeserialize(builder = CreateImage.Builder.class)
public class CreateImage {

	private final String prompt;
	private final Integer n;
	private final ImageSize size;
	private final ResponseFormat responseFormat;
	private final String user;

	private CreateImage(Builder builder) {
		this.prompt = builder.prompt;
		this.n = builder.n;
		this.size = builder.size;
		this.responseFormat = builder.responseFormat;
		this.user = builder.user;
	}

	/**
	 * {@link CreateImage} builder class.
	 */
	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder {

		private String prompt;
		private Integer n;
		private ImageSize size;
		private ResponseFormat responseFormat;
		private String user;

		/**
		 * @param prompt
		 * 		  A text description of the desired image(s). The maximum length is 1000 characters. Required.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("prompt")
		public Builder prompt(String prompt) {
			this.prompt = prompt;
			return this;
		}

		/**
		 * @param n
		 * 		  The number of images to generate. Must be between 1 and 10. Optional. Defaults to 1.
		 * return {@link Builder} instance
		 */
		@JsonProperty("n")
		public Builder n(Integer n) {
			this.n = n;
			return this;
		}

		/**
		 * @param size
		 * 		  The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024. Optional. Defaults to 1024x1024.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("size")
		public Builder size(ImageSize size) {
			this.size = size;
			return this;
		}

		/**
		 * The format in which the generated images are returned. Must be one of url or b64_json.
		 *
		 * @param responseFormat
		 * 		  Optional. Defaults to url.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("response_format")
		public Builder responseFormat(ResponseFormat responseFormat) {
			this.responseFormat = responseFormat;
			return this;
		}

		/**
		 * @param user
		 * 		  A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. Learn more. Optional.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("user")
		public Builder user(String user) {
			this.user = user;
			return this;
		}

		/**
		 * @return New instance of {@link CreateImage} class.
		 * @throws NullPointerException If any of required parameters not set.
		 */
		public CreateImage build() {
			Objects.requireNonNull(prompt, "'prompt' is required");
			return new CreateImage(this);
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
	 * @return value set by {@link Builder#prompt(String)}
	 */
	@JsonProperty("prompt")
	public String prompt() {
		return prompt;
	}

	/**
	 * @return value set by {@link Builder#n(Integer)}
	 */
	@JsonProperty("n")
	public Optional<Integer> n() {
		return Optional.ofNullable(n);
	}

	/**
	 * @return value set by {@link Builder#size(ImageSize)}
	 */
	@JsonProperty("size")
	public Optional<ImageSize> size() {
		return Optional.ofNullable(size);
	}

	/**
	 * @return value set by {@link Builder#responseFormat(ResponseFormat)}
	 */
	@JsonProperty("response_format")
	public Optional<ResponseFormat> responseFormat() {
		return Optional.ofNullable(responseFormat);
	}

	/**
	 * @return value set by {@link Builder#user(String)}
	 */
	@JsonProperty("user")
	public Optional<String> user() {
		return Optional.ofNullable(user);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CreateImage that = (CreateImage) o;
		return Objects.equals(prompt, that.prompt) && Objects.equals(n, that.n) && size == that.size && responseFormat == that.responseFormat && Objects.equals(user, that.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(prompt, n, size, responseFormat, user);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateImage.class.getSimpleName() + "[", "]")
				.add("prompt='" + prompt + "'")
				.add("n=" + n)
				.add("size=" + size)
				.add("responseFormat=" + responseFormat)
				.add("user='" + user + "'")
				.toString();
	}
}