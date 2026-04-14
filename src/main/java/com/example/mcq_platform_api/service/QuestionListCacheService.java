package com.example.mcq_platform_api.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.dto.QuestionListResponse;
import com.example.mcq_platform_api.exception.ResourceNotFoundException;

@Service
public class QuestionListCacheService {

    private final Map<String,QuestionListResponse> questionListCache = new ConcurrentHashMap<>();

    public void cacheQuestion(QuestionListResponse question) {
        questionListCache.put(question.getSessionId(), question);
    }

    public QuestionListResponse getQuestion(String sessionId) {
        if(!questionListCache.containsKey(sessionId)) return null;
        return questionListCache.get(sessionId);
    }

    public void removeQuestion(String sessionId) {
        if(!questionListCache.containsKey(sessionId)) throw new ResourceNotFoundException("Question not found");
        questionListCache.remove(sessionId);
    }
}
