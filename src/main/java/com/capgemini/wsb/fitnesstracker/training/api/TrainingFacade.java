package com.capgemini.wsb.fitnesstracker.training.api;
/**
 * Interface for providing unified access to training operations through the API.
 * Combines functionalities from {@link TrainingProvider} and {@link TrainingService}.
 */
public interface TrainingFacade extends TrainingProvider, TrainingService{
}
