package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingFacade;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserFacade;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * REST controller for managing trainings.
 * This controller provides endpoints to handle trainings-related operations such as
 * creating, retrieving, updating, and deleting.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
@Slf4j
public class TrainingController {

    private final TrainingFacade trainingFacade;
    private final UserFacade userFacade;
    private final TrainingMapper mapper;

    /**
     * Retrieves all trainings.
     *
     * @return ResponseEntity containing a list of all trainings or an error response if retrieval fails.
     */
    @GetMapping
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        try {
            List<TrainingDto> trainings = trainingFacade.findAllTrainings()
                    .stream()
                    .map(mapper::toDto)
                    .toList();
            return ResponseEntity.ok(trainings);
        } catch (Exception e) {
            log.error("Error retrieving all trainings", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves trainings associated with a specific user ID.
     *
     * @param id the ID of the user.
     * @return ResponseEntity containing a list of trainings associated with the user or an error response if retrieval fails.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<List<TrainingDto>> getTrainingByUserId(@PathVariable Long id) {
        try {
            List<TrainingDto> trainings = trainingFacade.findByUserId(id)
                    .stream()
                    .map(mapper::toDto)
                    .toList();
            return ResponseEntity.ok(trainings);
        } catch (Exception e) {
            log.error("Error retrieving trainings with that user id", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves trainings starting after a specified date.
     *
     * @param dateStr the start date in yyyy-MM-dd'T'HH:mm format.
     * @return ResponseEntity containing a list of trainings starting after the specified date or an error response if retrieval fails.
     */
    @GetMapping("/finishAfter")
    public ResponseEntity<List<TrainingDto>> getFinishTrainingsAfterDate(@RequestParam("date") String dateStr){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = null;
        try {
            date = formatter.parse(dateStr);
            List<TrainingDto> trainings = trainingFacade.findByStartTimeAfter(date)
                    .stream()
                    .map(mapper::toDto)
                    .toList();
            return ResponseEntity.ok(trainings);
        } catch (ParseException e) {
            log.error("Error parsing requested starting date.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            log.error("Error retrieving trainings with that starting time", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves trainings of a specific activity type.
     *
     * @param activityType the activity type to filter by.
     * @return ResponseEntity containing a list of trainings of the specified activity type or an error response if retrieval fails.
     */
    @GetMapping("/activityType")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivityType(@RequestParam("activityType") ActivityType activityType) {
        try {
            List<TrainingDto> trainings = trainingFacade.findByActivityType(activityType)
                    .stream()
                    .map(mapper::toDto)
                    .toList();
            return ResponseEntity.ok(trainings);
        } catch (Exception e) {
            log.error("Error retrieving trainings with that type of activity", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Adds a new training record.
     *
     * @param dto the DTO containing training data to create.
     * @return ResponseEntity containing the created training or an error response if creation fails.
     */
    @PostMapping
    public ResponseEntity<Training> addTraining(@RequestBody TrainingCreationDto dto) {
        try {
            long userId = dto.userId();
            User user = userFacade.getUser(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            Training training = mapper.toEntityCreate(dto);
            training.setUser(user);
            Training createdTraining = trainingFacade.createTraining(training);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTraining);
        } catch (IllegalArgumentException e) {
            log.error("Invalid training data", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("Error creating training", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Updates an existing training record.
     *
     * @param id the ID of the training to update.
     * @param dto the DTO containing updated training data.
     * @return ResponseEntity containing the updated training or an error response if update fails.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Training> updateTraining(@PathVariable Long id, @RequestBody TrainingUpdateDto dto ) {
        try {
            Training training = trainingFacade.getTrainingById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            if (dto.userId() != null) {
                long userId = dto.userId();
                User user = userFacade.getUser(userId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                training.setUser(user);
            }
            Training updatedTraining = trainingFacade.updateTraining(mapper.updateTrainingFromDto(dto, training));
            return ResponseEntity.ok(updatedTraining);
        } catch (UserNotFoundException e) {
            log.error("User not found with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("Error updating training", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}