package com.example.mcq_platform_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.dto.PracticeSetResponse;
import com.example.mcq_platform_api.dto.PracticeStartRequest;
import com.example.mcq_platform_api.dto.QuestionListResponse;

@Service
public class PracticeSetService {
    @Autowired
    private QuestionService questionService;
    
    public PracticeSetResponse generatePracticeSet(PracticeStartRequest request) {
        String time = request.getTime();
        if(time == null || time == "") time = "10";
         if(request.getSubject() != null){
            request.setSubject(request.getSubject().toLowerCase());
        }
        if(request.getTopic() != null){
            request.setTopic(request.getTopic().toLowerCase());
        }
        QuestionListResponse questionListResponse = questionService.getQuestions(request.getSubject(), request.getTopic(), request.getLimit());
        return new PracticeSetResponse(time , questionListResponse);
    }
}
