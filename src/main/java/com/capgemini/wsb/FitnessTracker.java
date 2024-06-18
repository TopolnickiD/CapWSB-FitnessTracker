package com.capgemini.wsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for FitnessTracker.
 * This class initializes and starts the Spring Boot application.
 */
@SpringBootApplication
public class FitnessTracker {

    /**
     * Main method to start the FitnessTracker application.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(FitnessTracker.class, args);
    }

}

