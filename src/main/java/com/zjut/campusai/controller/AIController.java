package com.zjut.campusai.controller;

import com.zjut.campusai.ai.StudentAssistant;
import com.zjut.campusai.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final StudentAssistant studentAssistant;

    @Autowired
    public AIController(StudentAssistant studentAssistant) {
        this.studentAssistant = studentAssistant;
    }

    @GetMapping("/ask")
    public ApiResponse<String> ask(@RequestParam String question) {
        return ApiResponse.success(studentAssistant.chat(question));
    }
}