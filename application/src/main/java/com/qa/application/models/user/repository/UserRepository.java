package com.qa.application.models.user.repository;

import com.qa.application.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findUserById(Integer id);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
