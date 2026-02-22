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
public class Question {
    @Id
    private String id;
    private String questionText;
    private String subject; 
    private String topic;
    private String explaination;

    @OneToMany(mappedBy = "question", cascade = jakarta.persistence.CascadeType.ALL)
    private List<Option> options; 
}
