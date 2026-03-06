package com.example.mcq_platform_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.entities.QuestionSession;
import com.example.mcq_platform_api.repository.QuestionSessionRepo;

@Service
public class QuestionSessionService {
    @Autowired
    private QuestionSessionRepo questionSessionRepo;

    public QuestionSession getQuestionSessionByIdAndUserId(String practiceSetId, String userId) {
        return questionSessionRepo.findByIdAndUserId(practiceSetId, userId);
    }

    

    
}
