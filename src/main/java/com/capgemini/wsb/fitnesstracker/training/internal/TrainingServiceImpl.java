package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingFacade;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link TrainingFacade} interface for managing {@link Training} entities.
 * This service handles CRUD operations for training data and delegates database interactions to {@link TrainingRepository}.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingFacade {

    private final TrainingRepository trainingRepository;

    /**
     * Retrieves all training records from the database.
     *
     * @return List of all {@link Training} entities
     */
    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves all training records associated with a specific user ID from the database.
     *
     * @param userId ID of the user whose training records are to be fetched
     * @return List of {@link Training} entities associated with the given user ID
     */
    @Override
    public List<Training> findByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Retrieves all training records with start times after a specified date from the database.
     *
     * @param startTime Start time threshold for filtering training records
     * @return List of {@link Training} entities with start times after the specified date
     */
    @Override
    public List<Training> findByStartTimeAfter(Date startTime) {
        return trainingRepository.findByStartTimeAfter(startTime);
    }

    /**
     * Retrieves all training records with a specific activity type from the database.
     *
     * @param activityType Activity type to filter training records
     * @return List of {@link Training} entities with the specified activity type
     */
    @Override
    public List<Training> findByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Creates a new training record in the database.
     *
     * @param training Training entity containing data to be persisted
     * @return Created {@link Training} entity
     */
    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    /**
     * Retrieves a training record by its unique identifier.
     *
     * @param id ID of the training record to fetch
     * @return Optional containing the {@link Training} entity if found, otherwise empty
     */
    @Override
    public Optional<Training> getTrainingById(Long id) {
        return trainingRepository.findById(id);
    }

    /**
     * Updates an existing training record in the database.
     *
     * @param training Updated training entity with new data
     * @return Updated {@link Training} entity
     */
    @Override
    public Training updateTraining(Training training) {
        return trainingRepository.save(training);
    }

    /**
     * Placeholder method that will eventually retrieve the user associated with a training record.
     *
     * @param trainingId ID of the training record to fetch the associated user
     * @return Optional containing the associated {@link User} entity if implemented, otherwise empty
     * @throws UnsupportedOperationException when method is called, indicating it is not yet implemented
     */
    @Override
    public Optional<User> getTraining(Long trainingId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}