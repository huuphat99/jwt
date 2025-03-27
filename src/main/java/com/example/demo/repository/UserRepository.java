package com.example.demo.repository;

import com.example.demo.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

  User getUserByUsername(
      @NotBlank(message = "Username is required") @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters") String username);
}
