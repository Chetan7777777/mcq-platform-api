package com.example.mcq_platform_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class QuestionSessionQuestion {
    @Id
    private String id;
    
    @ManyToOne
    private QuestionSession questionSession;
    
    @ManyToOne
    private Question question;

}
