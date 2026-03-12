package com.example.mcq_platform_api.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.mcq_platform_api.dto.QuestionListResponse;
import com.example.mcq_platform_api.dto.QuestionResponse;
import com.example.mcq_platform_api.entities.Option;
import com.example.mcq_platform_api.entities.Question;
import com.example.mcq_platform_api.repository.QuestionRepo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private TempService tempService;
    @Mock
    private QuestionRepo questionRepo;

    @InjectMocks
    private QuestionService questionService;

    @Test
    void saveQuestionTest(){
        Question question = createQuestion("Algebra" , "Maths");
        when(questionRepo.save(question)).thenReturn(question);
        Question q = questionService.saveQuestion(question);
        assertThat(q).isNotNull();
    }
    @Test
    void getQuestionsTest(){
        Question question1 = createQuestion("Algebra" , "Maths");
        Question question2 = createQuestion("Geometry" , "Maths");
    
        List<Question> questions = List.of(question1, question2);
        Page<Question> questionPage = new PageImpl<>(questions);
        when(questionRepo.findBySubject(eq("Maths"), any(Pageable.class))).thenReturn(questionPage);
        QuestionListResponse response = questionService.getQuestions("Maths", null, 10);
        assertThat(response).isNotNull();
        assertThat(response.getQuestions()).hasSize(2);
        System.out.println("Subject: " + response.getSubject());
        System.out.println("Topic: " + response.getTopic());
        System.out.println("Size: " + response.getTotal());
        for (QuestionResponse questionResponse : response.getQuestions()) {
            System.out.println(questionResponse.getNumber());
            System.out.println(questionResponse.getQuestionText());
            for (var option : questionResponse.getOptions()) {
                System.out.println(option.getLabel() + " - " + option.getOptionText());
            }
        }
    }

    private Question createQuestion(String topic , String subject){
        Question question = new Question();
        question.setTopic(topic);
        question.setSubject(subject);
        question.setId(UUID.randomUUID().toString());
        question.setQuestionText("Sample question");
        Option option1 = new Option("1","Option 1", true , question);
        Option option2 = new Option("2","Option 2", false , question);
        Option option3 = new Option("3","Option 3", false , question);
        question.setOptions(List.of(option1, option2, option3));
        option1.setQuestion(question);
        option2.setQuestion(question);
        option3.setQuestion(question);
        return question;
    }
}
