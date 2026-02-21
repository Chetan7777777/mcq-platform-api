package com.example.mcq_platform_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcq_platform_api.entities.Question;

public interface QustionRepo extends JpaRepository<Question, String> {

    
}
