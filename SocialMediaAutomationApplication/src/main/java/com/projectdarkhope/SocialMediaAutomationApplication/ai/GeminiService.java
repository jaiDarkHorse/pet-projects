package com.projectdarkhope.SocialMediaAutomationApplication.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    public String testGemini() {

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.6-flash:generateContent?key="
                        + apiKey;

        Map<String, Object> request = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of(
                                                "text",
                                                "Say hello and explain what Minecraft is in one sentence."
                                        )
                                }
                        )
                }
        );

        return restClient
                .post()
                .uri(url)
                .body(request)
                .retrieve()
                .body(String.class);
    }
    
    
    public String classifyArticleRaw(String title, String description) {

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.6-flash:generateContent?key="
                        + apiKey;

        String prompt = """
                You are a news classification system.

                Classify the following article into exactly one of these categories:

                - minecraft
                - anime
                - tamil_nadu_politics
                - ignore

                Article title:
                %s

                Article description:
                %s

                Return ONLY valid JSON in this exact format:

                {
                  "category": "minecraft",
                  "subcategory": "game_update",
                  "targetAccount": "minecraft",
                  "relevance": 95,
                  "confidence": 98
                }

                Rules:
                - category must be one of: minecraft, anime, tamil_nadu_politics, ignore
                - relevance must be an integer from 0 to 100
                - confidence must be an integer from 0 to 100
                - targetAccount should normally correspond to the category
                - Do not add markdown
                - Do not add explanations
                """.formatted(title, description);

        Map<String, Object> request = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of(
                                                "text",
                                                prompt
                                        )
                                }
                        )
                }
        );

        return restClient
                .post()
                .uri(url)
                .body(request)
                .retrieve()
                .body(String.class);
    }
    
    public GeminiClassification classifyArticle(
            String title,
            String description) {

        String response = classifyArticleRaw(title, description);

        try {

            JsonNode root = objectMapper.readTree(response);

            String jsonText = root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            return objectMapper.readValue(
                    jsonText,
                    GeminiClassification.class
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to parse Gemini classification response",
                    e
            );
        }
    }
}