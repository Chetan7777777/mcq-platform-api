package com.example.mcq_platform_api.service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.dto.OptionResponse;
import com.example.mcq_platform_api.dto.QuestionListResponse;
import com.example.mcq_platform_api.dto.QuestionResponse;
import com.example.mcq_platform_api.entities.Question;
import com.example.mcq_platform_api.repository.QuestionRepo;


@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    public Question saveQuestion(Question question) {
        if(question == null){
            throw new IllegalArgumentException("Question cannot be null");
        }
        return questionRepo.save(question);
    }
    public QuestionListResponse getQuestions(String subject, String topic, int limit) {

        Pageable pageable = PageRequest.of(0, limit);
        Page<Question> questionPage;

        if (subject != null && topic != null) {
            questionPage = questionRepo.findBySubjectAndTopic(subject, topic, pageable);
        } 
        else if (subject != null) {
            questionPage = questionRepo.findBySubject(subject, pageable);
        } 
        else if (topic != null) {
            questionPage = questionRepo.findByTopic(topic, pageable);
        } 
        else {
            questionPage = questionRepo.findAll(pageable);
        }

        return mapToQuestionListResponse(questionPage.getContent(), subject, topic , limit);
    }
    private QuestionListResponse mapToQuestionListResponse(List<Question> questions , String subject , String topic , int limit) {
        List<QuestionResponse> response = new ArrayList<>();
        int number = 1;
        for (Question question : questions) {
            QuestionResponse questionResponse = new QuestionResponse();
            questionResponse.setNumber(number++);
            questionResponse.setQuestionId(question.getId());
            questionResponse.setQuestionText(question.getQuestionText());
            AtomicInteger n = new AtomicInteger(1);
            List <OptionResponse> optionResponse = question.getOptions()
            .stream()
            .map(o -> new OptionResponse(n.getAndIncrement(), o.getOptionText()))
            .toList();
            
            questionResponse.setOptions(optionResponse);
            response.add(questionResponse);
        }
        QuestionListResponse listResponse = new QuestionListResponse();
        listResponse.setQuestions(response);
        listResponse.setSubject(subject);
        listResponse.setTopic(topic);
        listResponse.setTotal(limit);
        listResponse.setSessionId(number);

        if(subject != null && !questions.isEmpty() && topic != null){
            listResponse.setSubject(subject);
            listResponse.setTopic(topic);
        }

        else if(subject == null && !questions.isEmpty() && topic != null){
            listResponse.setSubject(questions.get(0).getSubject());
            listResponse.setTopic(topic);
        }
        
        else if(subject != null && !questions.isEmpty() && topic == null){
            listResponse.setSubject(subject);
            listResponse.setTopic("Mixed");
        }
        else{
            listResponse.setSubject("Mixed");
            listResponse.setTopic("Mixed");
        }
        return listResponse;
    }

}
