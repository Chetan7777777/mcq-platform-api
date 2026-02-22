package com.example.mcq_platform_api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.mcq_platform_api.entities.Question;
import com.example.mcq_platform_api.repository.QuestionRepo;

public class QuestionService {
    @Autowired
    private QuestionRepo qustionRepo;
    public Page<Question>getQuestionByTopic(String topic , int size) {
        Pageable pageable = PageRequest.of(0 , size);
        return qustionRepo.findByTopic(topic , pageable);
    }

    public Page<Question> getQuestionBySubject(String subject , int size) {
        Pageable pageable = PageRequest.of(0 , size);
        return qustionRepo.findBySubject(subject , pageable);
    }

}
