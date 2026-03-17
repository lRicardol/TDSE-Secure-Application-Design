package com.secureapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Secure Application.
 *
 * This class bootstraps the Spring Boot application and initializes
 * all components including controllers, services, repositories,
 * and security configurations.
 *
 * The application exposes REST endpoints that will be consumed by
 * an asynchronous HTML + JavaScript client served by Apache.
 */
@SpringBootApplication
public class SecureAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureAppApplication.class, args);
    }

}