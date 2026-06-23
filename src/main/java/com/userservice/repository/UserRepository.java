package com.userservice.repository;

import com.userservice.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<@NonNull User, @NonNull Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
