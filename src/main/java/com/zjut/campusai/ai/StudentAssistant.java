package com.zjut.campusai.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface StudentAssistant {
    @SystemMessage("你是校园学生管理助手，只根据工具返回的数据回答，查不到就说查不到，不要瞎编。")
    String chat(String userMessage);
}
