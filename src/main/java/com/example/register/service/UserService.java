package com.example.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.register.entity.User;
import com.example.register.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Validate required fields
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required");
        }

        // Additional validation and business logic before saving the user to the database
        // Check if the email is already registered
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email is already registered");
        }

        // Check if the phone number is already registered
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new PhoneNumberAlreadyRegisteredException("Phone number is already registered");
        }

        return userRepository.save(user);
    }

    // Custom exception for email already registered
    private static class EmailAlreadyRegisteredException extends RuntimeException {
        public EmailAlreadyRegisteredException(String message) {
            super(message);
        }
    }

    // Custom exception for phone number already registered
    private static class PhoneNumberAlreadyRegisteredException extends RuntimeException {
        public PhoneNumberAlreadyRegisteredException(String message) {
            super(message);
        }
    }
}

