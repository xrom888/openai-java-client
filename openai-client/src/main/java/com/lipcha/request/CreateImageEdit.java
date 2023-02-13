package com.lipcha.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * Params to create an edited or extended image given an original image and a prompt.
 */
@JsonDeserialize(builder = CreateImageEdit.Builder.class)
public class CreateImageEdit {

	private final String image;
	private final String mask;
	private final String prompt;
	private final Integer n;
	private final ImageSize size;
	private final ResponseFormat responseFormat;
	private final String user;

	private CreateImageEdit(Builder builder) {
		this.image = builder.image;
		this.mask = builder.mask;
		this.prompt = builder.prompt;
		this.n = builder.n;
		this.size = builder.size;
		this.responseFormat = builder.responseFormat;
		this.user = builder.user;
	}

	/**
	 * {@link CreateImageEdit} builder class.
	 */
	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder {

		private String image;
		private String mask;
		private String prompt;
		private Integer n;
		private ImageSize size;
		private ResponseFormat responseFormat;
		private String user;

		/**
		 * @param image
		 * 		  The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have transparency, which will be used as the mask. Required.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("image")
		public Builder image(String image) {
			this.image = image;
			return this;
		}

		/**
		 * Must be a valid PNG file, less than 4MB, and have the same dimensions as image.
		 *
		 * @param mask
		 * 		  An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where image should be edited. Optional.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("mask")
		public Builder mask(String mask) {
			this.mask = mask;
			return this;
		}

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
		 * @return {@link Builder} instance
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
		 * @param responseFormat
		 * 		  The format in which the generated images are returned. Must be one of url or b64_json. Optional. Defaults to url.
		 * @return {@link Builder} instance
		 */
		@JsonProperty("response_format")
		public Builder responseFormat(ResponseFormat responseFormat) {
			this.responseFormat = responseFormat;
			return this;
		}

		/**
		 * @param user
		 * 		  A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. Optional.
		 * @return {@link Builder} instance
		 * @see <a href="https://platform.openai.com/docs/guides/safety-best-practices/end-user-ids">Learn more.</a>
		 */
		@JsonProperty("user")
		public Builder user(String user) {
			this.user = user;
			return this;
		}

		/**
		 * @return New instance of {@link CreateImageEdit} class.
		 * @throws NullPointerException If any of required parameters not set.
		 */
		public CreateImageEdit build() {
			Objects.requireNonNull(image, "'image' is required");
			Objects.requireNonNull(prompt, "'prompt' is required");
			return new CreateImageEdit(this);
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
	 * @return value set by {@link Builder#image(String)}
	 */
	@JsonProperty("image")
	public String image() {
		return image;
	}

	/**
	 * @return value set by {@link Builder#mask(String)}
	 */
	@JsonProperty("mask")
	public Optional<String> mask() {
		return Optional.ofNullable(mask);
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
		CreateImageEdit that = (CreateImageEdit) o;
		return Objects.equals(image, that.image) && Objects.equals(mask, that.mask) && Objects.equals(prompt, that.prompt) && Objects.equals(n, that.n) && size == that.size && responseFormat == that.responseFormat && Objects.equals(user, that.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(image, mask, prompt, n, size, responseFormat, user);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateImageEdit.class.getSimpleName() + "[", "]")
				.add("image='" + image + "'")
				.add("mask='" + mask + "'")
				.add("prompt='" + prompt + "'")
				.add("n=" + n)
				.add("size=" + size)
				.add("responseFormat=" + responseFormat)
				.add("user='" + user + "'")
				.toString();
	}
}