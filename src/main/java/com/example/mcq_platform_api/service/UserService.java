package com.example.mcq_platform_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.mcq_platform_api.entities.User;
import com.example.mcq_platform_api.repository.UserRepo;

public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
