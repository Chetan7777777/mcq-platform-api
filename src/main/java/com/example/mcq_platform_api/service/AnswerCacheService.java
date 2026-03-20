package com.example.mcq_platform_api.service;
import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.dto.AnswerResponse;
import com.example.mcq_platform_api.exception.ResourceNotFoundException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AnswerCacheService {

    private static final int MAX_CACHE_SIZE = 20;

    private final Map<String, AnswerResponse> answerCache =
            Collections.synchronizedMap(
                    new LinkedHashMap<String, AnswerResponse>(MAX_CACHE_SIZE, 0.75f, true) {
                        @Override
                        protected boolean removeEldestEntry(Map.Entry<String, AnswerResponse> eldest) {
                            return size() > MAX_CACHE_SIZE;
                        }
                    }
            );

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
