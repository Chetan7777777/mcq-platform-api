package com.example.mcq_platform_api.repo;


import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.example.mcq_platform_api.entities.Option;
import com.example.mcq_platform_api.entities.Question;
import com.example.mcq_platform_api.repository.QuestionRepo;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class QuestionRepoTest {
    @Autowired
    private QuestionRepo questionRepo;

    @Test
    void testSaveQuestion(){
        // Create a new questions with options
        Question question = createQuestion("Q1", "Algebra", "Math");
        Question savedQuestion = questionRepo.save(question);

        Question found = questionRepo.findById(question.getId()).orElse(null);

        // Assert that the question is saved correctly
        assertThat(savedQuestion.getId()).isEqualTo(question.getId());
        assertThat(found.getId()).isEqualTo(question.getId());
    }
    @Test
    void testFindBySubject(){
        // Create and save a question
        questionRepo.save(createQuestion("Q1", "Algebra", "Math"));
        questionRepo.save(createQuestion("Q2", "Percentage", "Reasoning"));

        // Act
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Question> result = questionRepo.findBySubject("Math", pageable);

        // Assert
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getSubject()).isEqualTo("Math");
    }
    @Test
    void testFindByTopic(){
        // Create and save a question
        questionRepo.save(createQuestion("Q1", "Algebra", "Math"));
        questionRepo.save(createQuestion("Q2", "Algebra", "Math"));
        questionRepo.save(createQuestion("Q3", "Percentage", "Reasoning"));

        // Act
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Question> result = questionRepo.findByTopic("Algebra", pageable);

        // Assert
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getTopic()).isEqualTo("Algebra");
    }
    @Test
    void testFindBySubjectAndTopic(){
        // Create and save a question
        questionRepo.save(createQuestion("Q1", "Algebra", "Math"));
        questionRepo.save(createQuestion("Q2", "Algebra", "Math"));
        questionRepo.save(createQuestion("Q3", "Percentage", "Reasoning"));

        // Act
        PageRequest pageable = PageRequest.of(0, 2);
        Page<Question> result = questionRepo.findBySubjectAndTopic("Math", "Algebra", pageable);


        // Assert
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getSubject()).isEqualTo("Math");
        assertThat(result.getContent().get(0).getTopic()).isEqualTo("Algebra");
    }
    private Question createQuestion(String id , String topic , String subject){
        Question question = new Question();
        question.setId(id);
        question.setSubject(subject);
        question.setTopic(topic);
        question.setQuestionText("Sample question text");
        question.setOptions(List.of(new Option(UUID.randomUUID().toString(), "Option 1", true, question),
                                  new Option(UUID.randomUUID().toString(), "Option 2", false, question)
                                  ));
        return question;
    }
}
