package com.slippery.serenityspace.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.slippery.serenityspace.services.GeminiService;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.slippery.serenityspace.dto.ResearchDto;

import java.io.IOException;
import java.util.Map;

@Service
public class GeminiServiceImpl implements GeminiService {
    @Value("${gemini.api}")
    private String geminiUrl;
    private final WebClient webClient;

    public GeminiServiceImpl(WebClient.Builder webclientBuilder) {
        this.webClient = webclientBuilder.build();
    }
    @Override
    public ResearchDto processContent(String userRequest) throws IOException {
        ResearchDto researchDto =new ResearchDto();

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> requestBody = Map.of(
                "contents",new Object[]{
                        Map.of("parts",new Object[]{
                                Map.of("text",userRequest)
                        })
                }
        );
        String response =webClient.post()
                .uri(geminiUrl)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode candidatesNode = rootNode.path("candidates");

            // Iterate through candidates
            for (JsonNode candidateNode : candidatesNode) {
                JsonNode contentNode = candidateNode.path("content");
                JsonNode partsNode = contentNode.path("parts");

                // Iterate through parts
                for (JsonNode partNode : partsNode) {
                    String text = partNode.path("text").asText();
                    researchDto.setMessage(text);
                    researchDto.setStatusCode(200);
                    System.out.println(text);
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return researchDto;
    }

}
