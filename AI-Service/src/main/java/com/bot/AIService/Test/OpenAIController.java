package com.bot.AIService.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.bot.AIService.Test.OpenAiApiClient.getCompletion;

@RestController
public class OpenAIController {
    @GetMapping("/complete")
    public String completePrompt(@RequestParam String prompt) throws IOException {
        return getCompletion("how are you");
    }

    @GetMapping("/answer/{question}")
    public String getAnswer(@PathVariable String question) throws IOException {
        String prompt = "Q: " + question + "\nA:";
        String response = getCompletion(prompt);
        System.out.println(response);

        JsonObject responseObject = JsonParser.parseString(response).getAsJsonObject();

        return responseObject.getAsJsonArray("choices")
                .get(0).getAsJsonObject()
                .get("text").getAsString();
    }
}