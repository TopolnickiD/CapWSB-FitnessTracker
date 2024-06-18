package com.capgemini.wsb.fitnesstracker.training;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingFacade;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrainingServiceImplUnitTests {
    @Mock
    private TrainingRepository trainingRepository;
    @InjectMocks
    private TrainingServiceImpl trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testFindAllTrainings() {
        // Given
        Training training1 = new Training(new Date(), new Date(), ActivityType.RUNNING, 10.0, 5.0);
        Training training2 = new Training(new Date(), new Date(), ActivityType.CYCLING, 15.0, 7.5);
        List<Training> expectedTrainings = Arrays.asList(training1, training2);
        when(trainingRepository.findAll()).thenReturn(expectedTrainings);
        // When
        List<Training> actualTrainings = trainingService.findAllTrainings();
        // Then
        assertEquals(expectedTrainings.size(), actualTrainings.size());
        assertEquals(expectedTrainings.get(0).getActivityType(), actualTrainings.get(0).getActivityType());
        assertEquals(expectedTrainings.get(1).getActivityType(), actualTrainings.get(1).getActivityType());
        verify(trainingRepository, times(1)).findAll();
    }
    @Test
    void testFindByUserId() {
        // Given
        Long userId = 1L;
        Training training1 = new Training(new Date(), new Date(), ActivityType.RUNNING, 10.0, 5.0);
        Training training2 = new Training(new Date(), new Date(), ActivityType.CYCLING, 15.0, 7.5);
        List<Training> expectedTrainings = Arrays.asList(training1, training2);
        when(trainingRepository.findByUserId(userId)).thenReturn(expectedTrainings);
        // When
        List<Training> actualTrainings = trainingService.findByUserId(userId);
        // Then
        assertEquals(expectedTrainings.size(), actualTrainings.size());
        assertEquals(expectedTrainings.get(0).getActivityType(), actualTrainings.get(0).getActivityType());
        assertEquals(expectedTrainings.get(1).getActivityType(), actualTrainings.get(1).getActivityType());
        verify(trainingRepository, times(1)).findByUserId(userId);
    }
    @Test
    void testFindByActivityType() {
        // Given
        ActivityType activityType = ActivityType.RUNNING;
        Training training1 = new Training(new Date(), new Date(), activityType, 10.0, 5.0);
        Training training2 = new Training(new Date(), new Date(), activityType, 15.0, 7.5);
        List<Training> expectedTrainings = Arrays.asList(training1, training2);
        when(trainingRepository.findByActivityType(activityType)).thenReturn(expectedTrainings);
        // When
        List<Training> actualTrainings = trainingService.findByActivityType(activityType);
        // Then
        assertEquals(expectedTrainings.size(), actualTrainings.size());
        assertEquals(expectedTrainings.get(0).getDistance(), actualTrainings.get(0).getDistance());
        assertEquals(expectedTrainings.get(1).getDistance(), actualTrainings.get(1).getDistance());
        verify(trainingRepository, times(1)).findByActivityType(activityType);
    }
    @Test
    void testFindByStartTimeAfter() {
        // Given
        Date startTime = new Date();
        Training training1 = new Training(new Date(), new Date(), ActivityType.RUNNING, 10.0, 5.0);
        Training training2 = new Training(new Date(), new Date(), ActivityType.CYCLING, 15.0, 7.5);
        List<Training> expectedTrainings = Arrays.asList(training1, training2);
        when(trainingRepository.findByStartTimeAfter(startTime)).thenReturn(expectedTrainings);
        // When
        List<Training> actualTrainings = trainingService.findByStartTimeAfter(startTime);
        // Then
        assertEquals(expectedTrainings.size(), actualTrainings.size());
        assertEquals(expectedTrainings.get(0).getActivityType(), actualTrainings.get(0).getActivityType());
        assertEquals(expectedTrainings.get(1).getActivityType(), actualTrainings.get(1).getActivityType());
        verify(trainingRepository, times(1)).findByStartTimeAfter(startTime);
    }
    @Test
    void testCreateTraining() {
        // Given
        Training trainingToCreate = new Training(new Date(), new Date(), ActivityType.RUNNING, 10.0, 5.0);
        when(trainingRepository.save(trainingToCreate)).thenReturn(trainingToCreate);
        // When
        Training createdTraining = trainingService.createTraining(trainingToCreate);
        // Then
        assertEquals(trainingToCreate.getActivityType(), createdTraining.getActivityType());
        verify(trainingRepository, times(1)).save(trainingToCreate);
    }
    @Test
    void testUpdateTraining() {
        // Given
        Long trainingId = 1L;
        Training existingTraining = new Training(new Date(), new Date(), ActivityType.RUNNING, 10.0, 5.0);
        Training updatedTraining = new Training(new Date(), new Date(), ActivityType.CYCLING, 15.0, 7.5);
        when(trainingRepository.findById(trainingId)).thenReturn(Optional.of(existingTraining));
        when(trainingRepository.save(updatedTraining)).thenReturn(updatedTraining);
        // When
        Training result = trainingService.updateTraining(updatedTraining);
        // Then
        assertEquals(updatedTraining.getActivityType(), result.getActivityType());
        verify(trainingRepository, times(1)).save(updatedTraining);
    }
}
