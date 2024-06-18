package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing operations on {@link Training} entities.
 * Implementing classes are responsible for executing changes within a database transaction.
 */
public interface TrainingService {

    /**
     * Retrieves all trainings.
     *
     * @return a list of all trainings.
     */
    List<Training> findAllTrainings();

    /**
     * Retrieves trainings associated with a specific user.
     *
     * @param userId the ID of the user.
     * @return a list of trainings associated with the user.
     */
    List<Training> findByUserId(Long userId);

    /**
     * Retrieves trainings with start times after a specified date.
     *
     * @param startTime the start time threshold.
     * @return a list of trainings with start times after the specified date.
     */
    List<Training> findByStartTimeAfter(Date startTime);

    /**
     * Retrieves trainings of a specific activity type.
     *
     * @param activityType the activity type.
     * @return a list of trainings of the specified activity type.
     */
    List<Training> findByActivityType(ActivityType activityType);

    /**
     * Creates a new training record.
     *
     * @param training the training to create.
     * @return the created training.
     */
    Training createTraining(Training training);

    /**
     * Retrieves a training by its ID.
     *
     * @param id the ID of the training to retrieve.
     * @return an {@link Optional} containing the retrieved training, or empty if not found.
     */
    Optional<Training> getTrainingById(Long id);

    /**
     * Updates an existing training record.
     *
     * @param training the updated training information.
     * @return the updated training.
     */
    Training updateTraining(Training training);
}
