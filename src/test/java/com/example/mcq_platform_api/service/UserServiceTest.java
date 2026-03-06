package com.example.mcq_platform_api.service;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mcq_platform_api.entities.User;
import com.example.mcq_platform_api.repository.UserRepo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void saveUserTest() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userRepo.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isEqualTo(user.getId());
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
    }
    @Test
    void findByUsernameTest() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userRepo.findByUsername("testuser")).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.findByUsername("testuser");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(user.getId());
        assertThat(foundUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
    }



}
