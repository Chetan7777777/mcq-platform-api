package com.example.mcq_platform_api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TempSessionResponse {
    private List<AnswerResponse> answers;
}
