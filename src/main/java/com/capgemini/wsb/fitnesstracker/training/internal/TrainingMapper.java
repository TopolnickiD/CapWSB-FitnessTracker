package com.capgemini.wsb.fitnesstracker.training.internal;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;
/**
 * Mapper class for converting between Training entities and various Training DTOs.
 * This class provides methods to map Training entities to their corresponding Data Transfer Objects (DTOs)
 * and vice versa, facilitating data transformation between different layers of the application.
 */
@Component
public class TrainingMapper {

    /**
     * Maps a Training entity to a TrainingDto.
     */
    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed(),
                userToDto(training.getUser()));
    }

    /**
     * Maps a User entity to a UserDto.
     */
    public UserDto userToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }

    /**
     * Converts a TrainingCreationDto to a Training entity.
     */
    public Training toEntityCreate(TrainingCreationDto dto) {
        return new Training(
                dto.startTime(),
                dto.endTime(),
                dto.activityType(),
                dto.distance(),
                dto.averageSpeed());
    }

    /**
     * Converts a TrainingDto to a Training entity.
     */
    public Training toEntity(TrainingDto dto) {
        return new Training(
                dto.startTime(),
                dto.endTime(),
                dto.activityType(),
                dto.distance(),
                dto.averageSpeed());
    }

    /**
     * Updates an existing Training entity based on data in TrainingUpdateDto.
     * Only updates fields that are not null or have valid values.
     */
    public Training updateTrainingFromDto(TrainingUpdateDto dto, Training training) {
        if (dto.startTime() != null) {
            training.setStartTime(dto.startTime());
        }
        if (dto.endTime() != null) {
            training.setEndTime(dto.endTime());
        }
        if (dto.averageSpeed() > 0) {
            training.setAverageSpeed(dto.averageSpeed());
        }
        if (dto.activityType() != null) {
            training.setActivityType(dto.activityType());
        }
        if (dto.distance() > 0) {
            training.setDistance(dto.distance());
        }
        return training;
    }
}