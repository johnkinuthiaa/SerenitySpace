package com.slippery.serenityspace.services;

import com.slippery.serenityspace.dto.ResearchDto;

import java.io.IOException;

public interface GeminiService {
    ResearchDto processContent(String userRequest) throws IOException;
}
