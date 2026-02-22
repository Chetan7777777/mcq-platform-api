package com.example.mcq_platform_api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mcq_platform_api.repository.QuestionRepo;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    @Mock
    private QuestionRepo qustionRepo;

    @InjectMocks
    private QuestionService questionService;

    @Test
    void getQuestionByTopicTest(){
        
    }
}
