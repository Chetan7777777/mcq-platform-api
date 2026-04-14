package com.example.mcq_platform_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PracticeSetItem {
    @Id
    private String id;
    
    @ManyToOne
    private PracticeSet practiceSet;
    
    @ManyToOne
    private Question question;

}
