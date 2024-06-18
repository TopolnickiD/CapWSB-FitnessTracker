package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link Training} was not found.
 */
@SuppressWarnings("squid:S110")
public class TrainingNotFoundException extends NotFoundException {

    /**
     * Constructs a TrainingNotFoundException with a custom error message.
     *
     * @param message the error message.
     */
    public TrainingNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a TrainingNotFoundException for a specific Training ID.
     *
     * @param id the ID of the Training that was not found.
     */
    public TrainingNotFoundException(Long id) {
        this("Training with ID=" + id + " was not found");
    }
}