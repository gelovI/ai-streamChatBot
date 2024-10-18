package com.gelov.chatBot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class OllamaService {
    private static final String OLLAMA_API_URL = "http://localhost:11434/api/generate";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getAIResponse(String userMessage) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestJson = "{\"model\": \"llama3\", \"prompt\": \"" + userMessage + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        StringBuilder aiResponseBuilder = new StringBuilder();
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(OLLAMA_API_URL, HttpMethod.POST, entity, byte[].class);
            String responseBody = new String(response.getBody(), StandardCharsets.UTF_8);

            // Verarbeite die Antwort, um alle `response`-Segmente zu extrahieren und zu verbinden
            for (String line : responseBody.split("\\r?\\n")) {
                JsonNode responseJson = objectMapper.readTree(line);
                JsonNode responseNode = responseJson.get("response");
                if (responseNode != null) {
                    aiResponseBuilder.append(responseNode.asText());
                }
            }

            // Reduziere mehrfachen Leerzeichen auf eines und füge einen Abstand vor Großbuchstaben ein
            String finalResponse = aiResponseBuilder.toString()
                    .replaceAll("(?<=[a-zäöüß])(?=[A-ZÄÖÜ])", " ")  // Füge Leerzeichen vor Großbuchstaben ein
                    .replaceAll("\\s{2,}", " ")  // Reduziere mehrere Leerzeichen auf eines
                    .trim();

            return finalResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Es konnte keine Antwort generiert werden.";
    }
}