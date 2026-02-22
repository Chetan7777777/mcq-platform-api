package com.example.mcq_platform_api.repository;



import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcq_platform_api.entities.Question;

public interface QuestionRepo extends JpaRepository<Question, String> {
    Page<Question> findBySubject(String subject , Pageable pageable);
    Page<Question> findByTopic(String topic , Pageable pageable);
    Page<Question> findBySubjectAndTopic(String subject, String topic, Pageable pageable); 
}
