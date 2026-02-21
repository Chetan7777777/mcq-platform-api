package com.example.mcq_platform_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcq_platform_api.entities.User;

public interface UserRepo  extends JpaRepository<User, String>{
    Optional<User> findByUsername(String username);
}
