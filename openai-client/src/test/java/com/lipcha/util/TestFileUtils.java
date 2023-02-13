package com.lipcha.util;

import com.lipcha.exception.OpenAIClientException;

import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class TestFileUtils {

    private TestFileUtils() {
    }

    public static String testFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources" + path)), UTF_8);
        } catch (Exception e) {
            throw new OpenAIClientException("Failed to load test file from resource path: " + path, e);
        }
    }

}