package com.example.mcq_platform_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.mcq_platform_api.entities.User;
import com.example.mcq_platform_api.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    
    public User saveUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return userRepo.save(user);
    }
    public User findByUsername(String username) {
        if(username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
    
    

}
