package com.lipcha;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.lipcha.exception.OpenAIClientException;
import com.lipcha.model.Error;
import com.lipcha.model.file.UploadFile;
import com.lipcha.model.edit.Edit;
import com.lipcha.model.finetune.FineTune;
import com.lipcha.model.completion.Completion;
import com.lipcha.model.embedding.Embeddings;
import com.lipcha.model.finetune.FineTuneEvent;
import com.lipcha.model.image.Images;
import com.lipcha.model.model.Model;
import com.lipcha.model.moderation.Moderation;
import com.lipcha.request.CreateCompletion;
import com.lipcha.request.*;
import com.lipcha.model.*;
import okhttp3.*;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public final class OpenAIClient {

	private static final Logger log = LoggerFactory.getLogger(OpenAIClient.class);

	private static final URI BASE_URL = createURI("https://api.openai.com/v1");
	private static final Map<String, String> EMPTY_QUERY_PARAMS = Collections.emptyMap();
	private static final MediaType MEDIA_TYPE_IMAGE_PNG = MediaType.parse("image/png");
	private static final MediaType MEDIA_TYPE_TEXT_CSV = MediaType.parse("text/csv");
	private static final MediaType APPLICATION_JSON = MediaType.parse("application/json");

	private final ObjectMapper mapper = new ObjectMapper()
			.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
			.enable(DeserializationFeature.WRAP_EXCEPTIONS)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
			.setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
			.registerModule(new Jdk8Module());

	private final URI baseUrl;
	private final String apiKey;
	private final String organization;
	private final OkHttpClient okHttpClient;

	private OpenAIClient(Builder builder) {
		this.baseUrl = builder.baseUrl;
		this.apiKey = builder.apiKey;
		this.organization = builder.organization;
		this.okHttpClient = builder.okHttpClient;
	}

	public static class Builder {

		private URI baseUrl = null;
		private String apiKey;
		private String organization = null;
		private OkHttpClient okHttpClient = null;

		public Builder baseUrl(String baseUrl) {
			this.baseUrl = createURI(baseUrl);
			return this;
		}

		public Builder apiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}

		public Builder organization(String organization) {
			this.organization = organization;
			return this;
		}

		public Builder okHttpClient(OkHttpClient okHttpClient) {
			this.okHttpClient = okHttpClient;
			return this;
		}

		public OpenAIClient build() {
			baseUrl = baseUrl != null ? baseUrl : BASE_URL;
			Objects.requireNonNull(apiKey, "'API_KEY' is required");
			if (okHttpClient == null) {
				okHttpClient = new OkHttpClient();
			}
			return new OpenAIClient(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	/**
	 * Lists the currently available models, and provides basic information about each one such as the owner
	 * and availability.
	 * @return List of currently available models
	 * @throws OpenAIClientException If request failed
	 */
	public GenericResponse<Model> models() {
		return executeAndParse(get(Resource.MODELS.path()), null, new TypeReference<GenericResponse<Model>>(){});
	}

	/**
	 * Retrieves a model instance, providing basic information about the model such as the owner and permission.
	 * @param id Model id to retrieve
	 * @return Model instance
	 * @throws OpenAIClientException If request failed
	 */
	public Model model(String id) {
		return executeAndParse(get(Resource.MODELS.path() + "/" + id), null, new TypeReference<Model>(){});
	}

	/**
	 * Creates a completion for the provided prompt and parameters.
	 * @param createCompletion Request params
	 * @return Completion instance
	 * @throws OpenAIClientException If request failed
	 */
	public Completion createCompletion(CreateCompletion createCompletion) {
		return executeAndParse(post(Resource.COMPLETIONS.path(), createCompletion), createCompletion, new TypeReference<Completion>(){});
	}

	/**
	 * Creates a new edit for the provided input, instruction, and parameters.
	 * @param createEdit Request params
	 * @return Edited instance: edited version of the prompt
	 * @throws OpenAIClientException If request failed
	 */
	public Edit createEdit(CreateEdit createEdit) {
		return executeAndParse(post(Resource.EDITS.path(), createEdit), createEdit, new TypeReference<Edit>(){});
	}

	/**
	 * Creates an image given a prompt.
	 * @param createImage Request params
	 * @return Image/images created based on text description
	 * @throws OpenAIClientException If request failed
	 */
	public Images createImage(CreateImage createImage) {
		return executeAndParse(post(Resource.IMAGES.path() + "/generations", createImage), createImage, new TypeReference<Images>(){});
	}

	/**
	 * Creates an edited or extended image given an original image and a prompt.
	 * @param createImageEdit Request params
	 * @return Image/images edited or extended
	 * @throws OpenAIClientException If request failed
	 */
	public Images createImageEdit(CreateImageEdit createImageEdit) {
		File image = new File(createImageEdit.image());
		MultipartBody.Builder formBuilder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("prompt", createImageEdit.prompt())
				.addFormDataPart("image", image.getName(), RequestBody.create(image, MEDIA_TYPE_IMAGE_PNG));
		createImageEdit.mask().ifPresent(m -> {
			File mask = new File(m);
			formBuilder.addFormDataPart("mask", mask.getName(), RequestBody.create(mask, MEDIA_TYPE_IMAGE_PNG));
		});
		createImageEdit.n().ifPresent(n -> formBuilder.addFormDataPart("n", String.valueOf(n)));
		createImageEdit.size().ifPresent(s -> formBuilder.addFormDataPart("size", s.value()));
		createImageEdit.responseFormat().ifPresent(f -> formBuilder.addFormDataPart("response_format", f.value()));
		createImageEdit.user().ifPresent(u -> formBuilder.addFormDataPart("user", u));
		Request request = new Request.Builder()
				.url(url(Resource.IMAGES.path() + "/edits"))
				.headers(headers())
				.post(formBuilder.build())
				.build();
		return executeAndParse(request, createImageEdit, new TypeReference<Images>(){});
	}

	/**
	 * Creates a variation of a given image.
	 * @param createImageVariation Request params
	 * @return Original image variation/variations
	 * @throws OpenAIClientException If request failed
	 */
	public Images createImageVariation(CreateImageVariation createImageVariation) {
		File image = new File(createImageVariation.image());
		MultipartBody.Builder formBuilder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("image", image.getName(), RequestBody.create(image, MEDIA_TYPE_IMAGE_PNG));
		createImageVariation.n().ifPresent(n -> formBuilder.addFormDataPart("n", String.valueOf(n)));
		createImageVariation.size().ifPresent(s -> formBuilder.addFormDataPart("size", s.value()));
		createImageVariation.responseFormat().ifPresent(f -> formBuilder.addFormDataPart("response_format", f.value()));
		createImageVariation.user().ifPresent(u -> formBuilder.addFormDataPart("user", u));
		Request request = new Request.Builder()
				.url(url(Resource.IMAGES.path() + "/variations"))
				.headers(headers())
				.post(formBuilder.build())
				.build();
		return executeAndParse(request, createImageVariation, new TypeReference<Images>(){});
	}

	/**
	 * Creates an embedding vector representing the input text.
	 * @param createEmbeddings Request params.
	 * @return Embedding vector representing the input text.
	 * @throws OpenAIClientException If request failed
	 */
	public Embeddings createEmbeddings(CreateEmbeddings createEmbeddings) {
		return executeAndParse(post(Resource.EMBEDDINGS.path(), createEmbeddings), createEmbeddings, new TypeReference<Embeddings>(){});
	}

	/**
	 * Returns a list of files that belong to the user's organization.
	 * @return Info about existing files that were previously uploaded.
	 * @throws OpenAIClientException If request failed.
	 */
	public GenericResponse<com.lipcha.model.file.File> files() {
		return executeAndParse(get(Resource.FILES.path()), null, new TypeReference<GenericResponse<com.lipcha.model.file.File>>(){});
	}

	/**
	 * Upload a file that contains document(s) to be used across various endpoints/features.
	 * Currently, the size of all the files uploaded by one organization can be up to 1 GB.
	 * Please contact us if you need to increase the storage limit.
	 * @param purpose The intended purpose of the uploaded documents.
	 * @param filepath Path to a file.
	 * @return Uploaded file metadata
	 * @throws OpenAIClientException If request failed
	 */
	public UploadFile uploadFile(String purpose, String filepath) {
		File file = new File(filepath);
		RequestBody body = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("purpose", purpose)
				.addFormDataPart("file", file.getName(), RequestBody.create(file, MEDIA_TYPE_TEXT_CSV))
				.build();
		Request.Builder builder = new Request.Builder()
				.url(url(Resource.FILES.path()))
				.headers(headers());
		Map<String, String> form = null;
		if (log.isDebugEnabled()) {
			form = new HashMap<>(2);
			form.put("purpose", purpose);
			form.put("file", filepath);
		}
		return executeAndParse(builder.post(body).build(), form, new TypeReference<UploadFile>(){});
	}

	/**
	 * Delete a file.
	 * @param fileId File id of existing file.
	 * @return Delete result.
	 * @throws OpenAIClientException If request failed
	 */
	public Delete deleteFile(String fileId) {
		return executeAndParse(delete(Resource.FILES.path() + "/" + fileId), new TypeReference<Delete>(){});
	}

	/**
	 * Returns information about a specific file.
	 * @param fileId File id of existing file.
	 * @return File metadata
	 * @throws OpenAIClientException If request failed
	 */
	public com.lipcha.model.file.File file(String fileId) {
		return executeAndParse(get(Resource.FILES.path() + "/" + fileId), new TypeReference<com.lipcha.model.file.File>(){});
	}

	/**
	 * Returns the contents of the specified file.
	 * @param fileId File id of existing file.
	 * @return File content
	 * @throws OpenAIClientException If request failed
	 */
	public String fileContent(String fileId) {
		return executeAndParse(get(Resource.FILES.path() + "/" + fileId + "/content"), new TypeReference<String>(){});
	}

	/**
	 * Creates a job that fine-tunes a specified model from a given dataset.
	 * Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.
	 * @param createFineTune Request params.
	 * @return The enqueued job details.
	 * @throws OpenAIClientException If request failed
	 */
	public FineTune createFineTune(CreateFineTune createFineTune) {
		return executeAndParse(post(Resource.FINE_TUNES.path(), createFineTune), createFineTune, new TypeReference<FineTune>(){});
	}

	/**
	 * List your organization's fine-tuning jobs
	 * @return List of fine-tune jobs descriptions
	 * @throws OpenAIClientException If request failed
	 */
	public GenericResponse<FineTune> fineTunes() {
		return executeAndParse(get(Resource.FINE_TUNES.path()), new TypeReference<GenericResponse<FineTune>>(){});
	}

	/**
	 * Gets info about the fine-tune job.
	 * @param fineTuneId The ID of the fine-tune job
	 * @return Fine-tune job description
	 * @throws OpenAIClientException If request failed
	 */
	public FineTune fineTune(String fineTuneId) {
		return executeAndParse(get(Resource.FINE_TUNES.path() + "/" + fineTuneId), new TypeReference<FineTune>(){});
	}

	/**
	 * Immediately cancel a fine-tune job.
	 * @param fineTuneId The ID of the fine-tune job
	 * @return Fine-tune job description
	 * @throws OpenAIClientException If request failed
	 */
	public FineTune cancelFineTune(String fineTuneId) {
		return executeAndParse(post(Resource.FINE_TUNES.path() + "/" + fineTuneId + "/cancel"), new TypeReference<FineTune>(){});
	}

	/**
	 * Get fine-grained status updates for a fine-tune job.
	 * @param fineTuneId The ID of the fine-tune job
	 * @param stream
	 * 		  Whether to stream events for the fine-tune job.
	 *        If set to true, events will be sent as data-only server-sent events as they become available.
	 *        The stream will terminate with a data: [DONE] message when the job is finished (succeeded, cancelled, or failed).
	 *        If set to false, only events generated so far will be returned.
	 * @return List of fine-tune events
	 * @throws OpenAIClientException If request failed
	 */
	public GenericResponse<FineTuneEvent> fineTuneEvents(String fineTuneId, boolean stream) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("stream", String.valueOf(stream));
		return executeAndParse(get(Resource.FINE_TUNES.path() + "/" + fineTuneId + "/events", queryParams), new TypeReference<GenericResponse<FineTuneEvent>>(){});
	}

	/**
	 * Delete a fine-tuned model. You must have the Owner role in your organization.
	 * @param model
	 * 		  The model ID to delete
	 * @return Delete result
	 * @throws OpenAIClientException If request failed
	 */
	public Delete deleteFineTuneModel(String model) {
		return executeAndParse(delete(Resource.MODELS.path() + "/" + model), new TypeReference<Delete>(){});
	}

	/**
	 * Classifies if text violates OpenAI's Content Policy.
	 * @param createModeration
	 * 		  {@link CreateModeration} request params.
	 * @return Moderation result.
	 * @throws OpenAIClientException If request failed.
	 */
	public Moderation createModeration(CreateModeration createModeration) {
		return executeAndParse(post(Resource.MODERATIONS.path(), createModeration), createModeration, new TypeReference<Moderation>(){});
	}

	private <R> R executeAndParse(Request request, TypeReference<R> typeRef) {
		return executeAndParse(request, null, typeRef);
	}

	private <T, R> R executeAndParse(Request request, T body, TypeReference<R> typeRef) {
		long requestTimestampId = System.nanoTime();
		String requestMethod = request.method();

		log.debug("\n---- REQUEST TIMESTAMP ID {}, {} {}\n---- REQUEST BODY ----\n{}",
				requestTimestampId, requestMethod, request.url(),
				(body != null) ? prettyJson(body) : "<empty>");

		try (Response response = okHttpClient.newCall(request).execute()) {
			Optional<String> responseBody = response.body() != null ? Optional.of(response.body().string()) : Optional.empty();

			log.debug("\n---- RESPONSE CODE {} FOR REQUEST WITH TIMESTAMP ID {}\n---- RESPONSE BODY ----\n{}",
					response.code(), requestTimestampId,
					responseBody.isPresent() ? mapper.readTree(responseBody.get()).toPrettyString() : "<empty>");

			if (responseBody.isPresent()) {
				if (response.isSuccessful()) {
					return mapper.readValue(responseBody.get(), typeRef);
				} else {
					JsonNode errorNode = mapper.readTree(responseBody.get());
					Error error = mapper.treeToValue(errorNode.get("error"), Error.class);
					throw new OpenAIClientException("Request did not succeed, statusCode: " + response.code() + ", error: " + error, response.code(), error);
				}
			} else {
				throw new OpenAIClientException("Response body is null, statusCode: " + response.code(), response.code(), null);
			}
		} catch (IOException e) {
			throw new OpenAIClientException("Cannot execute request because of exception", e);
		}
	}

	private enum Resource {
		MODELS("/models"),
		COMPLETIONS("/completions"),
		EDITS("/edits"),
		IMAGES("/images"),
		EMBEDDINGS("/embeddings"),
		FILES("/files"),
		FINE_TUNES("/fine-tunes"),
		MODERATIONS("/moderations");

		private final String path;

		Resource(String path) {
			this.path = Objects.requireNonNull(path);
		}

		public String path() {
			return path;
		}
	}

	private Request get(String path) {
		return get(path, EMPTY_QUERY_PARAMS);
	}

	private Request get(String path, Map<String, String> queryParams) {
		return new Request.Builder()
				.url(url(path, queryParams))
				.headers(headers())
				.get()
				.build();
	}

	private Request post(String path) {
		return post(path, null);
	}

	private <T> Request post(String path, T body) {
		Request.Builder builder = new Request.Builder()
				.url(url(path))
				.headers(headers());
		try {
			RequestBody requestBody = (body != null)
					? RequestBody.create(mapper.writeValueAsString(body), APPLICATION_JSON)
					: RequestBody.create(new byte[]{}, APPLICATION_JSON);
			return builder.post(requestBody).build();
		} catch (JsonProcessingException e) {
			throw new OpenAIClientException("Cannot parse post request body: " + body);
		}
	}

	private Request delete(String path) {
		return new Request.Builder()
				.url(url(path))
				.headers(headers())
				.delete()
				.build();
	}

	private Headers headers() {
		Map<String, String> headers = new HashMap<>(3);
		headers.put("Authorization", "Bearer " + apiKey);
		headers.put("Content-Type", APPLICATION_JSON.toString());
		if (organization != null) {
			headers.put("OpenAI-Organization", organization);
		}
		return Headers.of(headers);
	}

	private static URI createURI(String url) {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			throw new OpenAIClientException("Cannot parse URL: " + url, e);
		}
	}

	private HttpUrl url(String path) {
		return url(path, EMPTY_QUERY_PARAMS);
	}

	private HttpUrl url(String path, Map<String, String> queryParams) {
		HttpUrl.Builder builder = new HttpUrl.Builder()
				.scheme(baseUrl.getScheme())
				.host(baseUrl.getHost())
				.encodedPath(baseUrl.getPath() + path);
		queryParams.forEach(builder::addQueryParameter);
		return builder.build();
	}

	private <T> String prettyJson(T body) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
		} catch (JsonProcessingException e) {
			throw new OpenAIClientException("Cannot parse body to pretty json string", e);
		}
	}
}