package com.example.virtualBookStore.repository;

import com.example.virtualBookStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsUserByName(String name);
    Optional<User> findUserByName(String name);

    Optional<User> findByEmail(String email);

}
