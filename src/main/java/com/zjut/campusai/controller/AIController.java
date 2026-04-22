package com.zjut.campusai.controller;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final ChatModel chatModel;

    @Autowired
    public AIController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        return chatModel.chat(question);
    }
}