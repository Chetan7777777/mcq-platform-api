package com.example.mcq_platform_api.service;
import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.dto.AnswerResponse;
import com.example.mcq_platform_api.exception.ResourceNotFoundException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AnswerCacheService {


    private final Map<String,AnswerResponse> answerCache = new ConcurrentHashMap<>();

    public void cacheAnswer(AnswerResponse answer) {
        answerCache.put(answer.getQuestionId(), answer);
    }

    public AnswerResponse getAnswer(String questionId) {
        if(!answerCache.containsKey(questionId)) return null;
        return answerCache.get(questionId);
    }

    public void removeAnswer(String questionId) {
        if(!answerCache.containsKey(questionId)) throw new ResourceNotFoundException("Question not found");
        answerCache.remove(questionId);
    }
}
