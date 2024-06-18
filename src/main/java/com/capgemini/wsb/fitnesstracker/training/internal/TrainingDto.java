package com.capgemini.wsb.fitnesstracker.training.internal;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import java.util.Date;

/**
 * DTO representing a Training with simplified user information.
 */
record TrainingDto(
        @Nullable Long Id,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00") Date startTime,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00") Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed,
        UserDto user) {
}

/**
 * DTO for creating a new Training record.
 */
record TrainingCreationDto(
        @Nullable Long Id,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date startTime,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed,
        Long userId) {
}

/**
 * DTO for updating an existing Training record.
 */
record TrainingUpdateDto(
        @Nullable @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startTime,
        @Nullable @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endTime,
        @Nullable ActivityType activityType,
        @Nullable double distance,
        @Nullable double averageSpeed,
        @Nullable Long userId) {
}