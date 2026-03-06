package com.example.mcq_platform_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcq_platform_api.dto.LoginRequest;
import com.example.mcq_platform_api.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired  
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        var user = userService.findByUsername(loginRequest.getUsername());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return "Invalid username or password";
        }
        return "Login successful for user: " + loginRequest.getUsername();
    }
}
