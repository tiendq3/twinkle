package com.yorra.twinkle.repository;

import com.yorra.twinkle.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByPhone(String username);
}
