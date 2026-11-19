package com.example.springai.controller;

import com.example.springai.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        String ask = aiService.ask(question);
        System.out.println("the answer is : "+ask);
        return ask;
    }

    @GetMapping("/info")
    public AiService.Person getInfo(@RequestParam String infos) {
        AiService.Person ask = aiService.extractInfos(infos);
        return ask;
    }

    @GetMapping("/askViaTool")
    public String askViaTool(@RequestParam String question) {
        String ask = aiService.askViaTool(question);
        return ask;
    }
}