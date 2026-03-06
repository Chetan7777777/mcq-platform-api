package com.example.mcq_platform_api.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import com.example.mcq_platform_api.entities.User;
import com.example.mcq_platform_api.repository.UserRepo;


@DataJpaTest
@ActiveProfiles("test")
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;
    
    @Test
    public void testFindByUsername() {
        // Given
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("testuser");
        user.setPassword(UUID.randomUUID().toString());
        userRepo.save(user);

        // When
        var result = userRepo.findByUsername("testuser");

        // Then
        // Assert the expected results, e.g., check if the user is found or not
        assertThat(result).isNotNull();
        assertThat(result.get().getUsername()).isEqualTo("testuser");
        assertThat(result.get().getPassword()).isEqualTo(user.getPassword());
    }
}
