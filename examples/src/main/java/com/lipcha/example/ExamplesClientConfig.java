package com.lipcha.example;

import com.lipcha.OpenAIClient;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public final class ExamplesClientConfig {

	private ExamplesClientConfig() {
	}

	// !!! Change me before run
	private static final String API_KEY = "...";

	// Extend timeout to upload images
	private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
			.connectTimeout(120, TimeUnit.SECONDS)
			.writeTimeout(60, TimeUnit.SECONDS)
			.readTimeout(60, TimeUnit.SECONDS)
			.build();

	// Create client with customized OkHttpClient
	public static final OpenAIClient OPEN_AI_CLIENT = OpenAIClient.newBuilder()
			.apiKey(API_KEY)
			.okHttpClient(OK_HTTP_CLIENT)
			.build();

}
