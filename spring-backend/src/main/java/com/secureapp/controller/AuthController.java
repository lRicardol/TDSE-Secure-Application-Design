package com.secureapp.controller;

import com.secureapp.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST Controller responsible for authentication endpoints.
 *
 * Handles login and registration requests from the frontend.
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    /**
     * Constructor Injection (Best Practice)
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * User registration endpoint
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Username and password are required"));
        }

        boolean registered = authService.register(username, password);

        if (!registered) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "User already exists"));
        }

        return ResponseEntity.ok(
                Map.of("message", "User registered successfully")
        );
    }

    /**
     * Login endpoint
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Username and password are required"));
        }

        boolean authenticated = authService.authenticate(username, password);

        if (!authenticated) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("message", "Invalid credentials"));
        }

        return ResponseEntity.ok(
                Map.of("message", "Login successful")
        );
    }

}