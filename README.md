# OpenAI API Java Client
[Official API Reference](https://platform.openai.com/docs/api-reference/introduction)

### Client Initialization
```
// Extend timeouts to upload/download large files
OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build();

// Create client with customized OkHttpClient
OpenAIClient openAIClient = OpenAIClient.newBuilder()
        .apiKey("... API KEY ...")
        .okHttpClient(okHttpClient)
        .build();
```

### Supported OpenAI APIs
- [List models](https://platform.openai.com/docs/api-reference/models/list)
```
// Lists the currently available models, and provides basic information about each one such as the owner and availability.
GenericResponse<Model> models = openAIClient.models();
```

- [Retrieve model](https://platform.openai.com/docs/api-reference/models/retrieve)
```
// Retrieves a model instance, providing basic information about the model such as the owner and permissioning.
Model model = openAIClient.model("... Model ID  ...");
```

- [Create completion](https://platform.openai.com/docs/api-reference/completions/create)
```
// Creates a completion for the provided prompt and parameters.
Completion completion = openAIClient.createCompletion(
        CreateCompletion.newBuilder()
                .model("text-davinci-003")
                .prompt(Collections.singletonList("Say this is a test"))
                .maxTokens(7)
                .temperature(0)
                .topP(1)
                .n(1)
                .stream(false)
                .stop(Collections.singletonList("\n"))
                .build()
);
```

- [Create edit](https://platform.openai.com/docs/api-reference/edits/create)
```
// Creates a new edit for the provided input, instruction, and parameters.
Edit edit = openAIClient.createEdit(
        CreateEdit.newBuilder()
                .model("text-davinci-edit-001")
                .input("What day of the wek is it?")
                .instruction("Fix the spelling mistakes")
                .build()
);
```

- [Create image](https://platform.openai.com/docs/api-reference/images/create)
```
// Creates an image given a prompt.
Images images = openAIClient.createImage(
        CreateImage.newBuilder()
                .prompt("Small plain in a sky")
                .n(1)
                .build()
);
```

- [Create image edit](https://platform.openai.com/docs/api-reference/images/create-edit)
```
// Creates an edited or extended image given an original image and a prompt.
Images editedImages = openAIClient.createImageEdit(
        CreateImageEdit.newBuilder()
                .image("... path to image file ...")
                .mask("... path to image mask image file ...")
                .n(2)
                .prompt("Plain in sky on the right from the palm tree")
                .build()
);
```

- [Create image variation](https://platform.openai.com/docs/api-reference/images/create-variation)
```
// Creates a variation of a given image.
Images images = openAIClient.createImageVariation(
        CreateImageVariation.newBuilder()
                .image("... path to image file ...")
                .n(2)
                .responseFormat(ResponseFormat.URL)
                .size(ImageSize.S_1024_1024)
                .build()
);
```

- [Create embeddings](https://platform.openai.com/docs/api-reference/embeddings/create)
```
// Creates an embedding vector representing the input text.
Embeddings embeddings = openAIClient.createEmbeddings(
        CreateEmbeddings.newBuilder()
                .model("text-embedding-ada-002")
                .input(Collections.singletonList("The food was delicious and the waiter..."))
                .build()
);
```

- [List files](https://platform.openai.com/docs/api-reference/files/list)
```
// Returns a list of files that belong to the user's organization.
GenericResponse<File> files = openAIClient.files();
```

- [Upload file](https://platform.openai.com/docs/api-reference/files/upload)
```
// Upload a file that contains document(s) to be used across various endpoints/features.
// Currently, the size of all the files uploaded by one organization can be up to 1 GB.
UploadFile uploadFile = openAIClient.uploadFile("fine-tune", "... path to jsonl file ...");
```

- [Delete file](https://platform.openai.com/docs/api-reference/files/delete)
```
// Delete a file.
Delete delete = openAIClient.deleteFile("... file id ...");
```

- [Retrieve file](https://platform.openai.com/docs/api-reference/files/retrieve)
```
// Returns information about a specific file.
File file = openAIClient.file("... file id ...");
```

- [Retrieve file content](https://platform.openai.com/docs/api-reference/files/retrieve-content)
```
// Returns the contents of the specified file
String fileContent = openAIClient.fileContent("... file id ...");
```

- [Create fine-tune](https://platform.openai.com/docs/api-reference/fine-tunes/create)
```
// Creates a job that fine-tunes a specified model from a given dataset.
// Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.
FineTune fineTune = openAIClient.createFineTune(
        CreateFineTune.newBuilder()
                .trainingFile("... uploaded file id ...")
                .build()
);
```

- [List fine-tunes](https://platform.openai.com/docs/api-reference/fine-tunes/list)
```
// List your organization's fine-tuning jobs
GenericResponse<FineTune> fineTunes = openAIClient.fineTunes();
```

- [Retrieve fine-tune](https://platform.openai.com/docs/api-reference/fine-tunes/retrieve)
```
// Gets info about the fine-tune job.
FineTune fineTune = openAIClient.fineTune("... fine tune id ...");
```

- [Cancel fine-tune](https://platform.openai.com/docs/api-reference/fine-tunes/cancel)
```
// Immediately cancel a fine-tune job.
FineTune fineTune = openAIClient.cancelFineTune("... fine tune id");
```

- [List fine-tune events](https://platform.openai.com/docs/api-reference/fine-tunes/events)
```
// Get fine-grained status updates for a fine-tune job.
GenericResponse<FineTuneEvent> fineTuneEvents = openAIClient.fineTuneEvents("... fine tune id...", false);
```

- [Delete fine-tune model](https://platform.openai.com/docs/api-reference/fine-tunes/delete-model)
```
// Get fine-grained status updates for a fine-tune job.
Delete delete = openAIClient.deleteFineTuneModel("... model id ...");
```

- [Create moderation](https://platform.openai.com/docs/api-reference/moderations/create)
```
// Classifies if text violates OpenAI's Content Policy
Moderation moderation = openAIClient.createModeration(
        CreateModeration.newBuilder()
                .input(Collections.singletonList("Some bad text here ..."))
                .build()
);
```

#### All examples can be found in examples folder
[Examples](https://github.com/xrom888/openai-client/tree/main/examples/src/main/java/com/lipcha/example)

#### Logging
Switch to debug to see insights (request/response details)
logback.xml
```
<!-- OpenAIClient -->
<logger name="com.lipcha" level="DEBUG" />
```

log4j.properties
```
log4j.logger.com.lipcha=debug
```

Log example
```
2023-02-13 | 12:38:07.585 | DEBUG | [main] | OpenAIClient | 
---- REQUEST TIMESTAMP ID 697289552211106, GET https://api.openai.com/v1/models/text-embedding-ada-002
---- REQUEST BODY ----
<empty>
2023-02-13 | 12:38:08.415 | DEBUG | [main] | OpenAIClient | 
---- RESPONSE CODE 200 FOR REQUEST WITH TIMESTAMP ID 697289552211106
---- RESPONSE BODY ----
{
  "id" : "text-embedding-ada-002",
  "object" : "model",
  "created" : 1671217299,
  "owned_by" : "openai-internal",
  "permission" : [ {
    "id" : "modelperm-Ad4J5NsqPbNJy0CMGNezXaeo",
    "object" : "model_permission",
    "created" : 1672848112,
    "allow_create_engine" : false,
    "allow_sampling" : true,
    "allow_logprobs" : true,
    "allow_search_indices" : true,
    "allow_view" : true,
    "allow_fine_tuning" : false,
    "organization" : "*",
    "group" : null,
    "is_blocking" : false
  } ],
  "root" : "text-embedding-ada-002",
  "parent" : null
}
```