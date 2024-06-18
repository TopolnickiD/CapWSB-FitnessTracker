package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Repository interface for managing {@link Training} entities in the database.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Retrieves a list of trainings associated with a specific user ID.
     *
     * @param userId The ID of the user to retrieve trainings for.
     * @return A list of {@link Training} entities associated with the given user ID.
     */
    List<Training> findByUserId(Long userId);

    /**
     * Retrieves a list of trainings that start after a specified date and time.
     *
     * @param startTime The starting date and time to filter trainings.
     * @return A list of {@link Training} entities starting after the specified time.
     */
    List<Training> findByStartTimeAfter(Date startTime);

    /**
     * Retrieves a list of trainings with a specific activity type.
     *
     * @param activityType The type of activity to filter trainings.
     * @return A list of {@link Training} entities with the specified activity type.
     */
    List<Training> findByActivityType(ActivityType activityType);

}
