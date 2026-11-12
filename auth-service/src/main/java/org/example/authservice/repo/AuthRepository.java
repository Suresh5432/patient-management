package org.example.authservice.repo;

import org.example.authservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);
}
