package com.example.mcq_platform_api.service;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.mcq_platform_api.entities.Question;
import com.example.mcq_platform_api.repository.QuestionRepo;

public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;
    public Page<Question>getQuestionByTopic(String topic , int size) {
        Pageable pageable = PageRequest.of(0 , size);
        return questionRepo.findByTopic(topic , pageable);
    }

    public Page<Question> getQuestionBySubject(String subject , int size) {
        Pageable pageable = PageRequest.of(0 , size);
        return questionRepo.findBySubject(subject , pageable);
    }
    public Question saveQuestion(Question question){
        Objects.requireNonNull(question , "Question must not be null");
        return questionRepo.save(question);
    }

}
