package com.example.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.register.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByPhoneNumber(String phoneNumber);

	boolean existsByEmail(String email);

    // Custom methods for user-related database operations

}

