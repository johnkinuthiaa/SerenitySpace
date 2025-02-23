package com.slippery.serenityspace.controller;

import com.slippery.serenityspace.dto.ResearchDto;
import com.slippery.serenityspace.services.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/serenity-AI")
public class GeminiController {
    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }
    @PostMapping("/chat")
    public ResponseEntity<ResearchDto> chatWithSerenityAi(@RequestBody String userRequest) throws IOException {
        return ResponseEntity.ok(geminiService.processContent(userRequest));
    }
}
