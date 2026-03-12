package com.example.mcq_platform_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mcq_platform_api.appconstants.Constant;
import com.example.mcq_platform_api.dto.QuestionListResponse;
import com.example.mcq_platform_api.dto.QuestionResponse;
import com.example.mcq_platform_api.service.QuestionService;


@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
   
    @GetMapping("/question")
    public ResponseEntity<QuestionListResponse> getQuestions(@RequestParam(required = false) String subject ,
        @RequestParam(required = false) String topic , @RequestParam(defaultValue = Constant.DEFAULT_QUESTION_LIMIT) int limit) {
        if(subject != null){
            subject = subject.toLowerCase();
        }
        if(topic != null){
            topic = topic.toLowerCase();
        }
        QuestionListResponse response = questionService.getQuestions(subject, topic, limit);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/question/{id}")
public ResponseEntity<QuestionResponse> getQuestion(@PathVariable String id) {

    QuestionResponse response = questionService.getQuestionById(id);   
    return ResponseEntity.ok(response);
}
}
