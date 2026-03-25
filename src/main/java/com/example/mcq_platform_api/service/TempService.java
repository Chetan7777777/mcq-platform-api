package com.example.mcq_platform_api.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.dto.AnswerListResponse;
import com.example.mcq_platform_api.dto.AnswerResponse;

@Service
public class TempService {
    private final Map<String,AnswerListResponse> sessionStore = new ConcurrentHashMap<>();
    public String createSession(List<AnswerResponse> answers){
        String sessionId = UUID.randomUUID().toString();
        AnswerListResponse tempSessionDTO = new AnswerListResponse(answers);
        sessionStore.put(sessionId, tempSessionDTO);
        return sessionId;
    }
    public AnswerListResponse getSession(String sessionId){
        return sessionStore.get(sessionId);
    }
    public void removeSession(String sessionId){
        sessionStore.remove(sessionId);
    }
}
