package com.example.mcq_platform_api.service;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.dto.AnswerResponse;
import com.example.mcq_platform_api.dto.OptionResponse;
import com.example.mcq_platform_api.dto.QuestionListResponse;
import com.example.mcq_platform_api.dto.QuestionResponse;
import com.example.mcq_platform_api.entities.Option;
import com.example.mcq_platform_api.entities.Question;
import com.example.mcq_platform_api.exception.ResourceNotFoundException;
import com.example.mcq_platform_api.repository.QuestionRepo;


@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    private final TempService tempService;
    
    private final QuestionRepo questionRepo;

    public QuestionService(TempService tempService , QuestionRepo questionRepo){
        this.questionRepo = questionRepo;
        this.tempService = tempService;
    }

    public Question saveQuestion(Question question) {
        if(question == null){
            throw new IllegalArgumentException("Question cannot be null");
        }
        return questionRepo.save(question);
    }
    public QuestionResponse getQuestionById(String questionId){
        Question q = questionRepo.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question not found with id:"+questionId));
        QuestionResponse qr = new QuestionResponse();
        qr.setNumber(1);
        qr.setQuestionId(questionId);
        qr.setQuestionText(q.getQuestionText());
        List<Option> optionList = q.getOptions();
        List<OptionResponse> optionResponses = new ArrayList<>();
        char c = 'a';
        for(Option option : optionList){
            optionResponses.add(new OptionResponse(c++,option.getOptionText()));      
        }
        qr.setOptions(optionResponses);
        return qr;
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
        if(questionPage == null) throw new ResourceNotFoundException("Question not found");

        return mapToQuestionListResponse(questionPage.getContent(), subject, topic , limit);
    }
    private QuestionListResponse mapToQuestionListResponse(List<Question> questions , String subject , String topic , int limit) {
        List<QuestionResponse> response = new ArrayList<>();
        int number = 1;
        List<AnswerResponse> answers = new ArrayList<>();
        for (Question question : questions) {
            QuestionResponse questionResponse = new QuestionResponse();
            questionResponse.setNumber(number++);
            questionResponse.setQuestionId(question.getId());
            questionResponse.setQuestionText(question.getQuestionText());
            List <OptionResponse> optionResponses = new ArrayList<>();
            char c = 'a';
            for(Option option : question.getOptions()){
                logger.info("options : "+option.getId());
                OptionResponse optionResponse = new OptionResponse();
                optionResponse.setLabel(c);
                optionResponse.setOptionText(option.getOptionText());
                optionResponses.add(optionResponse);
                if(option.isCorrect()){
                    answers.add(new AnswerResponse(question.getId(), c, option.getOptionText()));
                }
                c++;
            }
            questionResponse.setOptions(optionResponses);
            response.add(questionResponse);
        }    
        QuestionListResponse listResponse = new QuestionListResponse();
        listResponse.setQuestions(response);
        listResponse.setSubject(subject);
        listResponse.setTopic(topic);
        listResponse.setTotal(questions.size());
        listResponse.setSessionId(tempService.createSession(answers));
        logger.info("ANswers size -: "+answers.size());

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
