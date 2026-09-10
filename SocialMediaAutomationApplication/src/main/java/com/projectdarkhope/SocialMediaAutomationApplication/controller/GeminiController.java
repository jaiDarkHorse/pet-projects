package com.projectdarkhope.SocialMediaAutomationApplication.controller;

import com.projectdarkhope.SocialMediaAutomationApplication.ai.GeminiClassification;
import com.projectdarkhope.SocialMediaAutomationApplication.ai.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;

    @GetMapping("/test")
    public String testGemini() {

        return geminiService.testGemini();
    }
    
    @GetMapping("/classify")
    public GeminiClassification classifyTest(
            @RequestParam String title,
            @RequestParam String description) {

        return geminiService.classifyArticle(
                title,
                description
        );
    }
}