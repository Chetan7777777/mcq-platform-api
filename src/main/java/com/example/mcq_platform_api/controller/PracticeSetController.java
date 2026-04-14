package com.example.mcq_platform_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcq_platform_api.dto.PracticeSetResponse;
import com.example.mcq_platform_api.dto.PracticeStartRequest;

import com.example.mcq_platform_api.service.PracticeSetService;
import com.example.mcq_platform_api.service.QuestionService;


@RestController
@RequestMapping("/practice-set")
public class PracticeSetController {

    @Autowired
    private PracticeSetService practiceSetService;

    @PostMapping("/start")
    public ResponseEntity<PracticeSetResponse> getPracticeSet(@RequestBody PracticeStartRequest request) {
        PracticeSetResponse response = practiceSetService.generatePracticeSet(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/submit")
    public ResponseEntity<String> submitPracticeSet() {
        return ResponseEntity.ok("Practice set submitted successfully");
    }
    @GetMapping("/{sessionId}/save")
    public ResponseEntity<String> savePracticeSet(@PathVariable String sessionId) {
        return ResponseEntity.ok("Practice set saved successfully");
    }
    @GetMapping("/result")
    public ResponseEntity<String> getPracticeSetResult() {
        return ResponseEntity.ok("Practice set result retrieved successfully");
    }
}
