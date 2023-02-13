package com.lipcha.exception;

import com.lipcha.model.Error;

import java.util.Optional;

public class OpenAIClientException extends RuntimeException {

	private final Integer statusCode;
	private final transient Error error;

	public OpenAIClientException(String message, Integer statusCode, Error error) {
		super(message);
		this.statusCode = statusCode;
		this.error = error;
	}

	public OpenAIClientException(String message) {
		this(message, null, null);
	}

	public OpenAIClientException(String message, Throwable cause) {
		super(message, cause);
		this.statusCode = null;
		this.error = null;
	}

	public Optional<Integer> statusCode() {
		return Optional.ofNullable(statusCode);
	}

	public Optional<Error> error() {
		return Optional.ofNullable(error);
	}

}