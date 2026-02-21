package com.example.mcq_platform_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.mcq_platform_api.entities.Question;
import com.example.mcq_platform_api.repository.QustionRepo;

public class QuestionService {
    @Autowired
    private QustionRepo qustionRepo;
    public List<Question>getQuestionByTopic(String topic) {
        return null;
    }

    public List<Question> getQuestionBySubject(String subject) {
        return null;
    }

}
