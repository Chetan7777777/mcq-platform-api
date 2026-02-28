package com.example.mcq_platform_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcq_platform_api.entities.QuestionSession;

public interface QuestionSessionRepo extends JpaRepository<QuestionSession, String> {
    QuestionSession findByIdAndUserId(String id, String userId);
}
