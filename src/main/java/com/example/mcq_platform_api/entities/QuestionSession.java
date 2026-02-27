package com.example.mcq_platform_api.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSession {
    @Id
    private String id;
    private String userId;
    private String topic;
    private String subject;
    private String dateAndTime;   
    
    @OneToMany(mappedBy = "questionSession", cascade = jakarta.persistence.CascadeType.ALL)
    private List<QuestionSessionQuestion> questionSessionQuestions;

}
