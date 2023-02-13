package com.lipcha.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public class Error implements Serializable {

	private static final long serialVersionUID = -6917933989457521699L;

	private final String message;
	private final String type;
	private final String param;
	private final String code;

	@JsonCreator
	public Error(@JsonProperty("message") String message,
				 @JsonProperty("type") String type,
				 @JsonProperty("param") String param,
				 @JsonProperty("code") String code) {
		this.message = message;
		this.type = type;
		this.param = param;
		this.code = code;
	}

	private Error(Builder builder) {
		this.message = builder.message;
		this.type = builder.type;
		this.param = builder.param;
		this.code = builder.code;
	}

	public static class Builder {

		private String message;
		private String type;
		private String param;
		private String code;

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder param(String param) {
			this.param = param;
			return this;
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Error build() {
			return new Error(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}
	
	@JsonProperty("message")
	public String message() {
		return message;
	}

	@JsonProperty("type")
	public String type() {
		return type;
	}

	@JsonProperty("param")
	public String param() {
		return param;
	}

	@JsonProperty("code")
	public String code() {
		return code;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Error error = (Error) o;
		return Objects.equals(message, error.message) && Objects.equals(type, error.type) && Objects.equals(param, error.param) && Objects.equals(code, error.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, type, param, code);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Error.class.getSimpleName() + "[", "]")
				.add("message='" + message + "'")
				.add("type='" + type + "'")
				.add("param='" + param + "'")
				.add("code='" + code + "'")
				.toString();
	}
}