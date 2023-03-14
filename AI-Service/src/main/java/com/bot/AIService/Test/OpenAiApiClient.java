package com.bot.AIService.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class OpenAiApiClient {
    private static final String API_KEY = "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";

    public static String getCompletion(String prompt) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://api.openai.com/v1/completions");

        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + API_KEY);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", "davinci");
        requestBody.addProperty("prompt", prompt);
        requestBody.addProperty("temperature", 0.5);
        requestBody.addProperty("max_tokens", 200);
        requestBody.addProperty("n", 1);
        requestBody.addProperty("stop", "\n");

        HttpEntity requestEntity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        String responseBody = EntityUtils.toString(response.getEntity());

        httpClient.close();
        return responseBody;
    }
}