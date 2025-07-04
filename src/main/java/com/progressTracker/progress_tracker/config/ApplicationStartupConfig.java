package com.progressTracker.progress_tracker.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.progressTracker.progress_tracker.model.User;
import com.progressTracker.progress_tracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ApplicationStartupConfig {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner runAtStartup() {
        return args -> {
            long count = userRepo.count();
            if (count == 0) {
                log.info("No users found – creating default user 'alice'.");
                User alice = User.builder()
                        .username("alice")
                        .password(passwordEncoder.encode("password123"))
                        .build();
                userRepo.save(alice);
                log.info("Default user 'alice' created with password 'password123'.");
            } else {
                log.info("User table already contains {} entrie(s); skipping seeding.", count);
            }
        };
    }
}
