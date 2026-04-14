package com.example.mcq_platform_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcq_platform_api.entities.PracticeSet;

public interface QuestionSessionRepo extends JpaRepository<PracticeSet, String> {
    PracticeSet findByIdAndUserId(String id, String userId);
}
