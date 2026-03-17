package com.secureapp.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Utility class for password hashing and verification.
 *
 * Uses BCrypt which is a secure adaptive hashing algorithm
 * recommended for password storage.
 */
public final class PasswordUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private PasswordUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Hashes a plain text password.
     *
     * @param password plain password
     * @return hashed password
     */
    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Verifies a password against a stored hash.
     *
     * @param rawPassword plain password
     * @param hashedPassword stored hash
     * @return true if password matches
     */
    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}