package org.example.authservice.service;

import lombok.RequiredArgsConstructor;
import org.example.authservice.entity.Users;
import org.example.authservice.repo.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;

    public Optional<Users> findByEmail(String email){
        return authRepository.findByEmail(email);
    }
}
