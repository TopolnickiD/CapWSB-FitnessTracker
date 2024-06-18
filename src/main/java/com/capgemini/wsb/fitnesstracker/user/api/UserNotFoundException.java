package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link User} was not found.
 * <p>
 * This exception is thrown when a user with a specified ID or criteria
 * does not exist in the system.
 * </p>
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException with a message indicating the user ID that was not found.
     *
     * @param id the ID of the user that was not found.
     */
    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }
}

