package org.example.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.authservice.entity.Users;
import org.example.authservice.repo.AuthRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServiceApplication {

    @Bean
    @ConditionalOnProperty(name = "app.seed-demo-user", havingValue = "true")
    CommandLineRunner seedDemoUser(AuthRepository repository, PasswordEncoder passwordEncoder) {
        return args -> repository.findByEmail("testuser@test.com").orElseGet(() ->
                repository.save(new Users(null, "testuser@test.com",
                        passwordEncoder.encode("password123"), "USER")));
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
