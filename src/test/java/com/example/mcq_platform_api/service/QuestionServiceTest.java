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
    void getQuestionByTopicTest(){

        Question question1 = createQuestion("Algebra" , "Maths");
        Question question2 = createQuestion("Algebra" , "Maths");
        Question question3 = createQuestion("Algebra" , "Maths");
        List<Question> questionList = List.of(question1, question2, question3);
        Page<Question> questionPage = new PageImpl<>(questionList);
        when(questionRepo.findByTopic(eq("Maths"), any(Pageable.class))).thenReturn(questionPage);
        // Test the service method
        Page<Question> questions = questionService.getQuestionByTopic("Algebra", 3);
        assertThat(questions).hasSize(3);

    }
    @Test
    void getQuestionBySubjectTest(){
   Question question1 = createQuestion("Maths" , "Algebra");
        Question question2 = createQuestion("Maths" , "Algebra");
        Question question3 = createQuestion("Maths" , "Algebra");
        List<Question> questionList = List.of(question1, question2, question3);
        Page<Question> questionPage = new PageImpl<>(questionList);
        when(questionRepo.findBySubject(eq("Maths"), any(Pageable.class))).thenReturn(questionPage);
        // Test the service method
        Page<Question> questions = questionService.getQuestionBySubject("Maths", 3);
        assertThat(questions).hasSize(3);
    }
    @Test
    void getQuestionBySubjectAndTopicTest(){
        Question question1 = createQuestion("Algebra" , "Maths");
        Question question2 = createQuestion("Percentage" , "Maths");
        Question question3 = createQuestion("Algebra" , "Maths");
        List<Question> filteredList = List.of(question1, question3);
        Page<Question> questionPage = new PageImpl<>(filteredList);
        when(questionRepo.findBySubjectAndTopic(eq("Maths"), eq("Algebra"), any(Pageable.class))).thenReturn(questionPage);
        // Test the service method
        Page<Question> questions = questionService.getQuestionBySubjectAndTopic("Maths", "Algebra", 3);
        assertThat(questions).hasSize(2);

    }

    private Question createQuestion(String topic , String subject){
        Question question = new Question();
        question.setTopic(topic);
        question.setSubject(subject);
        question.setId(UUID.randomUUID().toString());
        question.setQuestionText("Sample question");
        Option option1 = new Option("1","Option 1", true , question);
        Option option2 = new Option("2","Option 2", false , question);
        question.setOptions(List.of(option1, option2));
        return question;
    }
}
