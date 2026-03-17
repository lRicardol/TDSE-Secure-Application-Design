package com.secureapp.service;

import com.secureapp.model.User;
import com.secureapp.repository.UserRepository;
import com.secureapp.security.PasswordUtil;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Authentication service responsible for
 * handling user registration and login logic.
 *
 * This class acts as the business logic layer
 * between the controller and the repository.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;

    /**
     * Constructor injection (best practice).
     */
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user in the system.
     *
     * @param username username
     * @param password plain password
     * @return true if registration successful
     */
    public boolean register(String username, String password) {

        if (userRepository.existsByUsername(username)) {
            return false;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);

        userRepository.save(user);

        return true;
    }

    /**
     * Authenticates a user.
     *
     * @param username username
     * @param password plain password
     * @return true if credentials are valid
     */
    public boolean authenticate(String username, String password) {

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();

        return PasswordUtil.verifyPassword(password, user.getPasswordHash());
    }

}